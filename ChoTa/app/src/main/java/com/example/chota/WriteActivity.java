package com.example.chota;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chota.board.BoardVO;
import com.example.chota.common.CommonMethod;
import com.example.chota.common.CommonVal;
import com.example.chota.conn.ApiClient;
import com.example.chota.conn.ApiInterface;
import com.example.chota.conn.CommonConn;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteActivity extends AppCompatActivity {
    TextView tv_cancel, tv_register, tv_spinner, tv_photo;
    Spinner spinner;
    EditText edt_title, edt_content;
    LinearLayout linear_photo;
    ImageView image_photo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        checkDangerousPermissions();

        tv_cancel = findViewById(R.id.tv_cancel);
        tv_register = findViewById(R.id.tv_register);
        tv_photo = findViewById(R.id.tv_photo);
        spinner = findViewById(R.id.spinner);
        tv_spinner = findViewById(R.id.tv_spinner);
        edt_title = findViewById(R.id.edt_title);
        edt_content = findViewById(R.id.edt_content);
        linear_photo = findViewById(R.id.linear_photo);
        image_photo = findViewById(R.id.image_photo);




        spinner.setSelection(0);
        //게시판선택 눌렀을때
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(i == 1){
                    tv_spinner.setText("자유게시판");
                }else if(i == 2){
                    tv_spinner.setText("공부게시판");
                }else if(i == 3){
                    tv_spinner.setText("관심게시판");
                }else if(i == 4){
                    tv_spinner.setText("고민상담게시판");
                }else if(i == 5){
                    tv_spinner.setText("우리학교게시판");
                }else if(i == 6){
                    tv_spinner.setText("우리반게시판");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //취소 눌렀을때
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //등록 눌렀을때
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CommonMethod.isCheckEditText(edt_title)&&CommonMethod.isCheckEditText(edt_content) &&!tv_spinner.getText().toString().equals("")){
                    BoardVO vo = new BoardVO();
                    vo.setBoard_title(edt_title.getText()+"");
                    vo.setBoard_content(edt_content.getText()+"");
                    vo.setBoard_id(spinner.getSelectedItemPosition());

                    CommonConn conn = new CommonConn("detail", WriteActivity.this);
                    conn.addParams("vo", new Gson().toJson(vo));
                    conn.excuteConn(new CommonConn.ConnCallback() {
                        @Override
                        public void onResult(boolean isResult, String data) {
                            Log.d("detail", "onResult: 값 : " + data);
                        }
                    });

                }


                onBackPressed();
            }
        });



        //사진업로드 눌렀을때
        image_photo.setOnClickListener( v -> {
            tv_photo.setVisibility(View.GONE);
            // 카메라로 사진을 업데이트 할건지 (실시간 찍기)
            // 갤러리로 사진을 찍어놓은걸 할건지 ( 저장된것사용 )
            showDialog();

        });

    }//oncreate()


    //사진관련 메소드들
    String[] dialog_item = { "카메라" , "갤러리" };
    public final int LOAD_IMG = 1000;// startActivityForResult라는 메소드로 인텐트가 실행이 되면
    // 해당하는 메소드로 실행한 액티비티가 종료가 되었을때 결과를 얻어올 수가 있음.
    // 모든 결과는 하나의 메소드인 onActivityResult라는 메소드에서 처리해야함.
    // 어떤 작업이 끝났는지에 따라서 코드분기를 해줘야함
    AlertDialog dialog;
    public final int CAMERA_CODE = 2000;
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" 선택하세요 ")
                .setSingleChoiceItems(dialog_item, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        if (dialog_item[i].equals("카메라")) {
                            Log.d("다이얼로그", "onClick: 카메라" + i);
                            Intent pickIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            //임시 파일 만들기. <-프로바이터로 임시파일을 채워서 가져오게 만들기.
                            //일단 이 인텐트가 사용가능한지 체크
                            if(pickIntent.resolveActivity(getPackageManager()) != null){
                                //임시파일
                                File file = createFile();
                                if( file != null){
                                    //API24 부터는 프로바이더를 통해서 사진을 캡쳐한걸 가져와야함(임시파일)
                                    Uri imgUri = FileProvider.getUriForFile(getApplicationContext()
                                            , getApplicationContext().getPackageName()+".fileprovider"
                                            , file);
                                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){ //API 24
                                        pickIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                                    }else{
                                        pickIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

                                    }

                                }
                                startActivityForResult(pickIntent, CAMERA_CODE);
                            }


                        } else {
                            Log.d("다이얼로그", "onClick: 갤러리" + i);
                            //카메라, 갤러리 OS에서 만들어둔 액티비티. (액션)
                            Intent intent = new Intent();//생성자에 어떤 작업을 할 건지 액션을 지정해도됨, setter

                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);//갤러리에서 사진선택.
                            startActivityForResult(
                                    Intent.createChooser(intent, "Select Picture") , LOAD_IMG
                            );
                        }
                    }
                });
        dialog = builder.create();
        dialog.show();

    }// showDialog


    public String imgFilePath;
    //임시파일저장소 메소드
    private File createFile(){
        //파일 이름을 동적으로 만들기
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "Pj03My" + timeStamp;
        //사진파일을 저장하기 위한 경로
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File rtnFile = null;//리턴에 필요한것

        try {
            rtnFile = File.createTempFile(fileName, ".jpg", storageDir);

        } catch (IOException e) {
            e.printStackTrace();
        }

        imgFilePath = rtnFile.getAbsolutePath();    //절대파일?

        return rtnFile;

    }//createFile()

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dialog.dismiss();
        if( requestCode == LOAD_IMG && resultCode == RESULT_OK){    //resultCode 작업선택 Result_ok(-1)
            // Intent Data 형태로 주는 경로는 사실 사진파일의 실제 경로가 x
            Log.d("갤러리", "onActivityResult: " + data.getData());
            Log.d("갤러리", "onActivityResult: " + data.getData().getPath());
            String img_path = getRealPath(data.getData());
            Glide.with(WriteActivity.this).load(img_path).into(image_photo);    //사진붙히기

            //MultiPart 형태로 전송 ~ File ~
            submit(img_path);

        }else if( requestCode == CAMERA_CODE && resultCode == RESULT_OK ){
            Glide.with(WriteActivity.this).load(imgFilePath).into(image_photo);

            submit(imgFilePath);
        }
        // resultCode <= startActivityForResult를 실행할때 보내줬던 코드( 어떤 작업이 끈나고 이메소드가 왔는지를 구분할 수 있는 키)
    }//onActivityResult()

    //스프링전송메소드
    public void submit(String path){
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(path));
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.jpg", fileBody);
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        apiInterface.sendFile(filePart).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }//submit

    //리얼패스
    public String getRealPath(Uri contentUri){
        String res = null;  //리턴용
        String[] proj = {MediaStore.Images.Media.DATA}; //저장소에 있는 이미지들을 일단 전부가져옴.
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);   //파일을 가져오고 셀렉트할수 있는 쿼리
        if(cursor.moveToFirst()){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);//미디어형태의 이미지실제경로
            res = cursor.getString(column_index); //실제 가져오는
        }
        cursor.close();

        return res;
    }

    // 권한레벨 - 낮음 : 인터넷 - 사용하겠다고 메니페스트에 명시만하면 OK
    // 권한레벨 - 중간 : 유튜브 - 사용하겠다고 메니페스트에 명시 후 queries 로 재명시 해줘야함.
    // 권한레벨 - 높음 : 위치 , 카메라 , 갤러리(파일저장소) - 사용하겠다고 메니페스트에 명시 후 , 쿼리스로도 명시 후 사용자 동의.
    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            for (int i = 0 ; i< permissions.length ; i++){
                if ( grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d("TAG", "권한 승인 됨: " + permissions[i]);
                }else{
                    Log.d("TAG", "권한 승인 안됨: " + permissions[i]);
                }
            }
        }
    }




}