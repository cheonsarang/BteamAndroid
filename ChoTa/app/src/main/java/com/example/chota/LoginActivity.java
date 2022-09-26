package com.example.chota;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chota.common.CommonMethod;
import com.example.chota.common.CommonVal;
import com.example.chota.conn.CommonConn;
import com.example.chota.find.FindId1Fragment;
import com.example.chota.find.FindPw1Fragment;
import com.example.chota.myInfo.MemberVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.navercorp.nid.NaverIdLoginSDK;
import com.navercorp.nid.oauth.NidOAuthLogin;
import com.navercorp.nid.oauth.OAuthLoginCallback;
import com.navercorp.nid.oauth.view.NidOAuthLoginButton;
import com.navercorp.nid.profile.NidProfileCallback;
import com.navercorp.nid.profile.data.NidProfileResponse;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_id, edt_pw;
    TextView tv_find_id, tv_find_pw, tv_join;
    FrameLayout container, frame;
    CheckBox chk_login;

    Button btn_login;
    NidOAuthLoginButton btn_naver;
    ImageView btn_kakao;

    String social_email;
    String social_name;

    MemberVO vo = new MemberVO();
    Gson gson = new GsonBuilder().create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        tv_find_id = findViewById(R.id.tv_find_id);
        tv_find_pw = findViewById(R.id.tv_find_pw);
        tv_join = findViewById(R.id.tv_join);
        btn_login = findViewById(R.id.btn_login);
        btn_kakao = findViewById(R.id.btn_kakao);
        btn_naver = findViewById(R.id.btn_naver);
        container = findViewById(R.id.container);
        frame = findViewById(R.id.frame);
        chk_login = findViewById(R.id.chk_login);

        tv_find_id.setOnClickListener(this);
        tv_find_pw.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_kakao.setOnClickListener(this);
        tv_join.setOnClickListener(this);

        //자동로그인(일반)
        //saveLoginInfo();
        //자동로그인 공유자원)  //단점 : 사용자가 앱정보에서 스토리지&캐시를 맘대로 지울수 있는 부분이다
        SharedPreferences preferences = getPreferences(MODE_PRIVATE); //해당하는 액티비티에서만 쓰는것 private
        String userid = preferences.getString("id", "--");  //공유자원에 데이터를 저장을 해놨을때 읽는 방법 (지금은 디폴트값 들어옴)
        String userpw = preferences.getString("pw", "--");  //공유자원에 데이터를 저장을 해놨을때 읽는 방법 (지금은 디폴트값 들어옴)
        Log.d("공유자원", "onCreate: " + userid + " : " + userpw);

        //자동로그인 기능
        if (!userid.equals("--") && !userpw.equals("--")) {
            chk_login.setChecked(true);
            edt_id.setText(userid);
            edt_pw.setText(userpw);
            login(userid, userpw, "N");    //<- 만들어진 기능은 로직에 따라서 다시 사용이 가능하게 한다.
        }


        //NaverIdLoginSDK.initialize(context, {OAUTH_CLIENT_ID}, {OAUTH_CLIENT_SECRET}, {OAUTH_CLIENT_NAME})
        //btn_naver 선언 전에 써줘야 함
        //코틀린은 객체를 인스턴스화 안해도 자동으로 안에있는 인스턴스 멤버를 접근해서 쓸 수 있음
        //함수지향
        //초기화 - 오류가 나면 해결이 쉽지 않다
        NaverIdLoginSDK.INSTANCE.initialize(this,
                "5SDjyvoaDLoO29NsDmOz",
                "uiXWTp5ha7",
                "ChoTa");

        //binding 처리는 메모리 소비가 크고 좋지 않다다
        btn_naver = findViewById(R.id.btn_naver);
        //소셜 로그인 - 네이버 토큰 받고 프로필 리스트 연결
        btn_naver.setOAuthLoginCallback(new OAuthLoginCallback() {
            @Override
            public void onSuccess() {
                Log.d("네이버", "onSuccess : " + NaverIdLoginSDK.INSTANCE.getAccessToken());
                naver_profile();
            }

            @Override
            public void onFailure(int i, @NonNull String s) {
                Log.d("네이버", "onFailure : " + s);
            }

            @Override
            public void onError(int i, @NonNull String s) {
                Log.d("네이버", "onError : " + s);
            }
        });
    }

    //db 연결 로그인메소드
    public void login(String id, String pw, String social) {
        CommonConn conn = new CommonConn("login", LoginActivity.this);
        conn.addParams("userid", id);
        conn.addParams("userpw", pw+"");
        conn.addParams("social", social);
        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if (isResult) {
                    Log.d("data", "onResult: " + data);
                    CommonVal.loginInfo = new Gson().fromJson(data, MemberVO.class);//연결된 데이터값을 공통값에 초기화
                    Log.d("디버깅용 로그", "onResult: " + data);
                    if (social.equals("N") && CommonVal.loginInfo == null) {
                        Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호 틀림!", Toast.LENGTH_SHORT).show();
                    } else if (social.equals("Y") && CommonVal.loginInfo == null) {
                        Toast.makeText(LoginActivity.this, "소셜로그인을 위한 회원가입 창으로 이동합니다!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                        intent.putExtra("socialID", id);
                        startActivity(intent);
                    } else if (CommonVal.loginInfo != null) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Log.d("로그", "onResult: 모두 실패");
                    }
                } else {
                    //자동로그인은 유저가 선택하기 때문에 자동로그인이 체크가 되었는지를 판단하고 체크가 되었을때만! 저장이 되어야함.
                    if (chk_login.isChecked()) {
                        saveLoginInfo();
                    }
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }//login()


    public void saveLoginInfo() {//공유자원 메소드
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();//edit() <- Editor객체를 리턴하는 메소드
        editor.putString("id", CommonVal.loginInfo.getUserid());  //아이디 자동 저장
        editor.putString("pw", CommonVal.loginInfo.getUserpw());  //비밀번호 자동 저장
        editor.apply(); //확정지어줘야함

    }//saveLoginInfo

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.btn_login) { //일반 로그인
            //아이디 비밀번호 입력체크 나중에 넣어야 함
            //아이디 비밀번호가 null이 아니게 넘어가게 작업

            Log.d("user_id", edt_id.getText().toString());
            Log.d("user_pw", edt_pw.getText().toString());

            //if(loginck(edt_id)||loginck(edt_pw)){ }
            if (CommonMethod.isCheckEditText(edt_id) && CommonMethod.isCheckEditText(edt_pw)) {//체크된 값이 true라면 로그인해라
                login(edt_id.getText().toString(), edt_pw.getText().toString(), "N");
            } else {
                edt_id.setText("");
                edt_pw.setText("");
                Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        } else if (v == tv_find_id) {  //아이디 찾기 페이지 이동
            frame.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new FindId1Fragment()).commit();


        } else if (v == tv_find_pw) {  //비밀번호 찾기 페이지 이동
            frame.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new FindPw1Fragment()).commit();


        } else if (v.getId() == R.id.btn_join) {  //회원가입 페이지 이동
            intent = new Intent(LoginActivity.this, JoinActivity.class);
            startActivity(intent);
        }

    }

    //setOAuthLoginCallback을 이용해서 success가 되었을떄 (token이 있을 때) 정보를 받아올 수 있는 객체를
    //사용해서 정보를 얻어오면 된다.
    public void naver_profile() {
        //NidOAuthLogin().callProfileApi(nidProfileCallback) //kotiln
        NidOAuthLogin authLogin = new NidOAuthLogin();
        authLogin.callProfileApi(new NidProfileCallback<NidProfileResponse>() {
            @Override
            public void onSuccess(NidProfileResponse res) {
                Log.d("프로필", "onSuccess: ");
                Log.d("프로필", "onSuccess: " + res.getProfile().getEmail().toString());
                Log.d("프로필", "onSuccess: " + res.getProfile().getName().toString());
                Log.d("프로필", "onSuccess: " + res.getProfile().getMobile());

                //회원가입 시도도 해보기
                social_email = res.getProfile().getEmail().toString();
                social_name = res.getProfile().getName().toString();

                login(res.getProfile().getEmail().toString(), null, "Y");
            }

            @Override
            public void onFailure(int i, @NonNull String s) {
                Log.d("프로필", "onSuccess: " + s);
            }

            @Override
            public void onError(int i, @NonNull String s) {
                Log.d("프로필", "onSuccess: " + s);
            }
        });
    }

    //소셜 - 네이버 로그아웃
    public void naver_logout() {
        NaverIdLoginSDK.INSTANCE.logout();
    }






}