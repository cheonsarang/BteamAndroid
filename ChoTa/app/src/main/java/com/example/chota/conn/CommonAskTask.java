package com.example.chota.conn;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

//백그라운드에서 작업을 비동기로 함=> 결과가 나올때까지 서브 쓰레드가 대기상태여서 메인도 같이 기다림.
//데이터 건수가 많을때랑 꼭 데이터를 받아야할때 ↓ 사용할 예정
public class CommonAskTask extends AsyncTask<String, String, String> {
    public AsyncktaskCallBack callBack;

    Context context;    //토스트 메세지나, 프로그레스 다이얼로그 사용 시 필요함.
    ProgressDialog dialog;
    HashMap<String, Object> params;
    String url;

    public CommonAskTask(Context context, String url) {
        this.context = context;
        this.url = url;
        this.dialog = new ProgressDialog(context);
        this.params = new HashMap<>();
    }

    public void addParams(String key, Object value){
        this.params.put(key, value);

    }

    public void excuteAsk(AsyncktaskCallBack callBack){
        this.callBack = callBack;
        this.execute();
    }

    @Override
    protected void onPreExecute() {
        if(dialog == null) return;
        dialog.setProgress(ProgressDialog.STYLE_SPINNER);   //0쓰지 말고 상수로 쓰기
        dialog.setTitle("데이터 처리");
        dialog.setMessage("데이터를 가져오는 중...");
        Log.d("시작", "로딩");
        dialog.show();  //<= 실제 보이게 처리 ※
    }

    @Override
    protected String doInBackground(String... strings) {
        String rtnData = null;
        ApiInterface apiInterface = ApiClient.getApiclient().create(ApiInterface.class);

        try {
            rtnData = apiInterface.getData(url, params).execute().body();


        } catch (IOException e) {
            e.printStackTrace();
        }


        return rtnData;
    }


    @Override
    protected void onPostExecute(String data) {
        if(dialog == null) return;
        dialog.dismiss();
        Log.d("콜백데이터", "onPostExecute : " + data);
        //3.실행부 ( 구현해놓은 구현부를 실행해야하는 시점에 넣어주는 코드)

        if(data == null && data.length() == 0 && data.equals("null")){
            callBack.onResult(data, false);
        }else{

            callBack.onResult(data, true);
        }
    }

    public interface AsyncktaskCallBack{
        void onResult(String data, boolean isResult);
    }

}



