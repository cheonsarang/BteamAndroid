package com.example.chota.conn;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonConn {

    private String url; //생성 시 URL만 입력받게끔 만들예정
    private HashMap<String, Object> params;
    private ConnCallback callback;
    private Context context;
    private ProgressDialog dialog;


    public CommonConn(String url, Context context) {
        this.context = context;
        this.url = url;
        this.params = new HashMap<>();  //생성자가 만들어졌을떄만 초기화되게끔 따로 입력
        this.dialog = new ProgressDialog(context);
    }

    public void addParams(String key, Object value){
        params.put(key, value);

    }

    protected void onPreExecute() {
        if(dialog == null) return;
        dialog.setProgress(ProgressDialog.STYLE_SPINNER);   //0쓰지 말고 상수로 쓰기
        dialog.setTitle("데이터 처리");
        dialog.setMessage("데이터를 가져오는 중...");
        Log.d("시작", "로딩");
        dialog.show();  //<= 실제 보이게 처리 ※
    }

    protected void onPostExecute() {
        if(dialog == null) return;
        dialog.dismiss();

    }


    public void excuteConn(ConnCallback callback){//실행후 콜백 ==> 비동기 작업
        this.callback = callback;


        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<String> call = apiInterface.getData(url, params);
      /*  if(dialog == null) return;
        dialog.setProgress(ProgressDialog.STYLE_SPINNER);   //0쓰지 말고 상수로 쓰기
        dialog.setTitle("데이터 처리");
        dialog.setMessage("데이터를 가져오는 중...");
        Log.d("시작", "로딩");
        dialog.show();  //<= 실제 보이게 처리 ※*/

        onPreExecute();

        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onResult(true, response.body());
              /*  if(dialog == null) return;
                dialog.dismiss();*/
                onPostExecute();
            }

            //로그찍고, 토스트 창으로 서버이상 이라고 메세지 나오게 해보기!
            @Override
            public void onFailure(Call<String> call, Throwable t ) {
                callback.onResult(false, t.getMessage());
                Toast.makeText(context, "서버 이상!", Toast.LENGTH_SHORT).show();
              /*  if(dialog == null) return;
                dialog.dismiss();*/
                onPostExecute();
            }
        });



    }


    //onResponse <- 결과가 성공이고 데이터가 있을때 true, data
    //onFailure <- 결과가 실패이고 오류메세지가 있음. false, throwble

    public interface ConnCallback{  //onResponse와 onFailure 묶어서 한번에 보이게
        public void onResult(boolean isResult, String data);

    }
}
