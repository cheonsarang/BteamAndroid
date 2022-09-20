package com.example.chota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.chota.common.CommonMethod;
import com.example.chota.common.CommonVal;
import com.example.chota.conn.CommonConn;
import com.example.chota.find.FindId1Fragment;
import com.example.chota.find.FindPw1Fragment;
import com.example.chota.myInfo.MemberVO;
import com.google.gson.Gson;
import com.navercorp.nid.oauth.view.NidOAuthLoginButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    NidOAuthLoginButton btn_naver;
    EditText edt_id, edt_pw;
    TextView tv_find_id, tv_find_pw, tv_join;
    Button btn_login;
    ImageView btn_kakao;
    FrameLayout container, frame;
    CheckBox chk_login;





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
        tv_join.setOnClickListener(this);



        //saveLoginInfo();

        //saveLoginInfo();
        //자동로그인 공유자원)  //단점 : 사용자가 앱정보에서 스토리지&캐시를 맘대로 지울수 있는 부분이다
        SharedPreferences preferences = getPreferences(MODE_PRIVATE); //해당하는 액티비티에서만 쓰는것 private
        String userid = preferences.getString("id", "--");  //공유자원에 데이터를 저장을 해놨을때 읽는 방법 (지금은 디폴트값 들어옴)
        String userpw = preferences.getString("pw", "--");  //공유자원에 데이터를 저장을 해놨을때 읽는 방법 (지금은 디폴트값 들어옴)
        Log.d("공유자원", "onCreate: " + userid + " : " + userpw);

        //자동 로그인기능
        if(!userid.equals("--") && !userpw.equals("--")) {
            chk_login.setChecked(true);
            edt_id.setText(userid);
            edt_pw.setText(userpw);
            login();    //<- 만들어진 기능은 로직에 따라서 다시 사용이 가능하게 한다.
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent;

        if(v == btn_login){ //로그인
            if(CommonMethod.isCheckEditText(edt_id)&& CommonMethod.isCheckEditText(edt_pw)) {//체크된 값이 true라면 로그인해라

                //미들웨어 접근
                login();

            }else {
                Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();

            if(edt_id.getText().toString().equals("") || edt_pw.getText().toString().equals("")){
                Toast.makeText(LoginActivity.this, "빈칸을 채워주세요", Toast.LENGTH_SHORT).show();

            }else if(!edt_id.getText().toString().equals("admin") || !edt_pw.getText().toString().equals("admin01")){
                Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호가 틀림", Toast.LENGTH_SHORT).show();
                edt_id.setText("");//pw
                edt_pw.setText("");//id
            }else {
                intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);


            }
        }else if(v == tv_find_id){  //아이디 찾기 페이지 이동
            frame.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new FindId1Fragment()).commit();



        }else if(v == tv_find_pw){  //비밀번호 찾기 페이지 이동
            frame.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new FindPw1Fragment()).commit();


        }else if(v == tv_join){  //회원가입 페이지 이동
            intent = new Intent(LoginActivity.this, JoinActivity.class);
            startActivity(intent);

        }




        //NaverIdLoginSDK.initialize(context, {OAUTH_CLIENT_ID}, {OAUTH_CLIENT_SECRET}, {OAUTH_CLIENT_NAME})
        //코틀린은 객체를 인스턴스화 안해도 자동으로 안에 있는 인스턴스 멤버를 접근해서 쓸수가 잇음
        //함수지향.

//        getHashKey();
//
//
//        NaverIdLoginSDK.INSTANCE.initialize(this, "1jkHpH3sEc_PXBYYJJrr",
//                "VIPvHBi6RL", "Project03_Last" );
//
//        btn_naver.setOAuthLoginCallback(new OAuthLoginCallback() {
//            @Override
//            public void onSuccess() {
//                Log.d("네이버", "onSuccess : " + NaverIdLoginSDK.INSTANCE.getAccessToken());
//                naver_profile();
//
//            }
//
//            @Override
//            public void onFailure(int i, @NonNull String s) {
//                Log.d("네이버", "onFailure : " + s);
//            }
//
//            @Override
//            public void onError(int i, @NonNull String s) {
//                Log.d("네이버", "onError : "+ s);
//            }
//        });
//
//
//
//
//
//        KakaoSdk.init(this, "74d995a1e84df45fae0154ce25f287de");
//
////        findViewById(R.id.btn_kakao).setOnClickListener(v -> {
////
////        }); lamda식 자바코드를 함수형으로 간편하게 줄여서 사용한것
//
//        //카카오버튼 눌렀을때
//        btn_kakao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // (OAuthToken?, Throwable?) -> Unit
//                Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
//                    @Override
//                    public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
//                        if(oAuthToken !=null){
//                            Log.d("토큰", "invoke : 받아옴");
//                            kakao_profile();
//
//                        }if(throwable !=null){
//                            Log.d("토큰", "invoke : 오류있음");
//
//                        }
//                        return null;
//                    }
//                };
//
//
//
//// 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
//                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)) {
//                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callback);
//                }else {
//                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this, callback);
//                }
//
//            }
//        });
//
//
//
    }
//
//
//
//
//
//    //setOAuthLoginCallback 을 이용을 해서 success가 되었을떄 (token이 있을때) 정보를 받아올수있는 객체를
//    //사용해서 정보를 얻어오면 된다.
//    public void naver_profile(){
//        //NidOAuthLogin().callProfileApi(nidProfileCallback) Kotlin
//        NidOAuthLogin authLogin = new NidOAuthLogin();
//        authLogin.callProfileApi(new NidProfileCallback<NidProfileResponse>() {
//            @Override
//            public void onSuccess(NidProfileResponse res) {
//                Log.d("프로필", "onSuccess : ");
//                Log.d("프로필", "onSuccess : " + res.getProfile().getEmail());
//                Log.d("프로필", "onSuccess : " + res.getProfile().getMobile());
//                Log.d("프로필", "onSuccess : " + res.getProfile().getName());
//
//                //소셜로그인했을때 회원가입이 되어있는 소셜계정인지 아닌지를 판단해서
//                //회원가입이되어있으면 => MainActivity
//                //안되어있으면 해당하는 정보로 => JoinActivity
//               // login(res.getProfile().getEmail() , null, "Y");
//                // AlertDialog <-
//
//            }
//
//            @Override
//            public void onFailure(int i, @NonNull String s) {
//                Log.d("프로필", "onFailure : " + s);
//            }
//
//            @Override
//            public void onError(int i, @NonNull String s) {
//                Log.d("프로필", "onError : " + s);
//            }
//        });
//    }
//
//
//
//
//
//    //카카오프로필
//    public void kakao_profile() {
//        UserApiClient.getInstance().me((user, throwable) -> {
//            if (throwable != null) {
//                //오류가 났을때 어떤 오류인지 코드로 줌 KDE + 숫자 (단문지가 있음)
//
//            } else {
//                Log.d("카카오", "kakao_profile : " + user.getKakaoAccount().getProfile().getNickname());
//                Log.d("카카오", "kakao_profile : " + user.getKakaoAccount().getProfile().getThumbnailImageUrl());
//                Log.d("카카오", "kakao_profile : " + user.getKakaoAccount().getEmail());
//                Log.d("카카오", "kakao_profile : " + user.getKakaoAccount().getName());
//                Log.d("카카오", "kakao_profile : " + user.getKakaoAccount().getPhoneNumber());
//
//            }
//
//            return null;
//        });
//
//    }

    // 메소드안에 바뀌어야될부분이 고정되어있으면 재활용이 힘든 메소드.
    // => editText로 고정되어있던부분을 파라메터로 빼주기만하면 재활용이 가능한구조가됨   소셜로그인은 아이디가 이메일형식으로 저장
//    public void login(String email, String pw, String social_yn){
//        CommonConn conn = new CommonConn("andlogin", LoginActivity.this);
//        conn.addParams("email", email);
//        conn.addParams("pw", pw);
//        conn.addParams("social", social_yn);
//        conn.excuteConn(new CommonConn.ConnCallback() {
//            @Override
//            public void onResult(boolean isResult, String data) {
//
//                Log.d("로그인", "onResult : " + data);
//                CommonVal.loginInfo = new Gson().fromJson(data, AndMemberVO.class);
//                if(social_yn.equals("N") && CommonVal.loginInfo==null){
//                    Log.d("로그인", "onResult : 아디비번틀림");
//                }else if(social_yn.equals("Y") && CommonVal.loginInfo==null){
//                    // 회원가입으로 보내줘야함
//                    Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
//                    intent.putExtra("email", email);
//                    startActivity(intent);
//                }else if(CommonVal.loginInfo != null){
//                    //메인으로 보내줘야함.
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                }else{
//                    Log.d("로그", "onResult : 세개의 이프문 모두 실패??");
//                }
//
//            }
//        });
//  }




//    //해쉬키
//    private void getHashKey(){
//        PackageInfo packageInfo = null;
//        try {
//            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (packageInfo == null)
//            Log.e("KeyHash", "KeyHash:null");
//
//        for (Signature signature : packageInfo.signatures) {
//            try {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            } catch (NoSuchAlgorithmException e) {
//                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
//            }
//        }


    //db 연결 로그인메소드
    public void login(){
        CommonConn conn = new CommonConn("login", LoginActivity.this);
        conn.addParams("userid", edt_id.getText()+"");
        conn.addParams("userpw", edt_pw.getText()+"");
        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if(isResult) {
                    Log.d("data", "onResult: " + data);
                    CommonVal.loginInfo = new Gson().fromJson(data, MemberVO.class);//연결된 데이터값을 공통값에 초기화
                    if(CommonVal.loginInfo==null){
                        Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호가 틀림", Toast.LENGTH_SHORT).show();
                    }else {
                        //자동로그인은 유저가 선택하기 때문에 자동로그인이 체크가 되었는지를 판단하고 체크가 되었을때만! 저장이 되어야함.
                        if(chk_login.isChecked()){
                            Log.d("정보", "onResult: " + CommonVal.loginInfo.getUserid());
                            Log.d("정보", "onResult: " + CommonVal.loginInfo.getUserpw());
                            Log.d("정보", "onResult: " + CommonVal.loginInfo.getSchool_name());
                            saveLoginInfo();
                        }

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                }

            }
        });

    }//login()


    public void saveLoginInfo(){//공유자원 메소드
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();//edit() <- Editor객체를 리턴하는 메소드
        editor.putString("id", CommonVal.loginInfo.getUserid());  //아이디 자동 저장
        editor.putString("pw", CommonVal.loginInfo.getUserpw());  //비밀번호 자동 저장

        editor.apply(); //확정지어줘야함

    }//saveLoginInfo



}