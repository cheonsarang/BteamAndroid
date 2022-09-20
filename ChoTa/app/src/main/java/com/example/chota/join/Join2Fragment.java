package com.example.chota.join;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.chota.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Join2Fragment extends Fragment {
    EditText edt_name, edt_birth, edt_phone1, edt_phone2, edt_school, edt_grade, edt_class, edt_nickname;
    ImageView imgv_profile;
    RadioGroup rdo_grp;
    RadioButton rdo_man, rdo_woman;
    Button btn_next, btn_prev;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_join2, container, false);

        edt_name = v.findViewById(R.id.edt_name);
        edt_birth = v.findViewById(R.id.edt_birth);
        edt_phone1 = v.findViewById(R.id.edt_phone1);
        edt_phone2 = v.findViewById(R.id.edt_phone2);
        edt_school = v.findViewById(R.id.edt_school);
        edt_grade = v.findViewById(R.id.edt_grade);
        edt_class = v.findViewById(R.id.edt_class);
        edt_nickname = v.findViewById(R.id.edt_nickname);
        imgv_profile = v.findViewById(R.id.imgv_profile);
        rdo_grp = v.findViewById(R.id.rdo_grp);
        rdo_man = v.findViewById(R.id.rdo_man);
        rdo_woman = v.findViewById(R.id.rdo_woman);
        btn_next = v.findViewById(R.id.btn_next);
        btn_prev = v.findViewById(R.id.btn_prev);


        //생년월일 눌렀을때
        edt_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //다음 버튼 눌렀을때
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new Join3Fragment()).commit();
            }
        });


        //뒤로가기 버튼 눌렀을때
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new Join1Fragment()).commit();
            }
        });

        //프로필 눌렀을때
        imgv_profile.setOnClickListener( view -> {
            // 카메라로 사진을 업데이트 할건지 (실시간 찍기)
            // 갤러리로 사진을 찍어놓은걸 할건지 ( 저장된것사용 )
            //showDialog();

        });


        return v;
    }


    String[] dialog_item = { "카메라" , "갤러리" };
    public final int LOAD_IMG = 1000;// startActivityForResult라는 메소드로 인텐트가 실행이 되면
    // 해당하는 메소드로 실행한 액티비티가 종료가 되었을때 결과를 얻어올 수가 있음.
    // 모든 결과는 하나의 메소드인 onActivityResult라는 메소드에서 처리해야함.
    // 어떤 작업이 끝났는지에 따라서 코드분기를 해줘야함
    AlertDialog dialog;
    public final int CAMERA_CODE = 2000;
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(" 선택하세요 ")
                .setSingleChoiceItems(dialog_item, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        if (dialog_item[i].equals("카메라")) {
                            Log.d("다이얼로그", "onClick: 카메라" + i);
                            Intent pickIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            //임시 파일 만들기. <-프로바이터로 임시파일을 채워서 가져오게 만들기.
                            //일단 이 인텐트가 사용가능한지 체크
                            if(pickIntent.resolveActivity(getContext().getPackageManager()) != null){
                                //임시파일
                                File file = createFile();
                                if( file != null){
                                    //API24 부터는 프로바이더를 통해서 사진을 캡쳐한걸 가져와야함(임시파일)
                                    Uri imgUri = FileProvider.getUriForFile(getContext().getApplicationContext()
                                            , getContext().getApplicationContext().getPackageName()+".fileprovider"
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
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File rtnFile = null;//리턴에 필요한것

        try {
            rtnFile = File.createTempFile(fileName, ".jpg", storageDir);

        } catch (IOException e) {
            e.printStackTrace();
        }

        imgFilePath = rtnFile.getAbsolutePath();    //절대파일?

        return rtnFile;
    }

// 프로필 관련 메소드 권한!!!

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        dialog.dismiss();
//        if( requestCode == LOAD_IMG && resultCode == RESULT_OK){    //resultCode 작업선택 Result_ok(-1)
//            // Intent Data 형태로 주는 경로는 사실 사진파일의 실제 경로가 x
//            Log.d("갤러리", "onActivityResult: " + data.getData());
//            Log.d("갤러리", "onActivityResult: " + data.getData().getPath());
//            String img_path = getRealPath(data.getData());
//            Glide.with(JoinActivity.this).load(img_path).into(imgv_profile);    //사진붙히기
//
//            //MultiPart 형태로 전송 ~ File ~
//            submit(img_path);
//
//        }else if( requestCode == CAMERA_CODE && resultCode == RESULT_OK ){
//            Glide.with(JoinActivity.this).load(imgFilePath).into(imgv_profile);
//
//            submit(imgFilePath);
//        }
//        // resultCode <= startActivityForResult를 실행할때 보내줬던 코드( 어떤 작업이 끈나고 이메소드가 왔는지를 구분할 수 있는 키)
//    }
//
//    //스프링전송메소드
//    public void submit(String path){
//        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(path));
//        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.jpg", fileBody);
//        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
//        apiInterface.sendFile(filePart).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//    }
//
//
//    public String getRealPath(Uri contentUri){
//        String res = null;  //리턴용
//        String[] proj = {MediaStore.Images.Media.DATA}; //저장소에 있는 이미지들을 일단 전부가져옴.
//        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);   //파일을 가져오고 셀렉트할수 있는 쿼리
//        if(cursor.moveToFirst()){
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);//미디어형태의 이미지실제경로
//            res = cursor.getString(column_index); //실제 가져오는
//        }
//        cursor.close();
//
//        return res;
//    }
//
//    // 권한레벨 - 낮음 : 인터넷 - 사용하겠다고 메니페스트에 명시만하면 OK
//    // 권한레벨 - 중간 : 유튜브 - 사용하겠다고 메니페스트에 명시 후 queries 로 재명시 해줘야함.
//    // 권한레벨 - 높음 : 위치 , 카메라 , 갤러리(파일저장소) - 사용하겠다고 메니페스트에 명시 후 , 쿼리스로도 명시 후 사용자 동의.
//    private void checkDangerousPermissions() {
//        String[] permissions = {
//                Manifest.permission.CAMERA,
//                Manifest.permission.ACCESS_MEDIA_LOCATION,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE
//        };
//
//        int permissionCheck = PackageManager.PERMISSION_GRANTED;
//        for (int i = 0; i < permissions.length; i++) {
//            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
//            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
//                break;
//            }
//        }
//
//        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
//                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
//            } else {
//                ActivityCompat.requestPermissions(this, permissions, 1);
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == 1){
//            for (int i = 0 ; i< permissions.length ; i++){
//                if ( grantResults[i] == PackageManager.PERMISSION_GRANTED){
//                    Log.d("TAG", "권한 승인 됨: " + permissions[i]);
//                }else{
//                    Log.d("TAG", "권한 승인 안됨: " + permissions[i]);
//                }
//            }
//        }
//    }


}





        //라디오그룹 선택할때
//        rdo_grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if(checkedId == R.id.rdo_man){
//                    //vo.setGender("남");
//                }else{
//                   // vo.setGender("여");
//                }
//            }
//        });