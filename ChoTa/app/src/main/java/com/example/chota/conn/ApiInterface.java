package com.example.chota.conn;

import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiInterface {

    @FormUrlEncoded
    @POST
    Call<String> getData(@Url String url, @FieldMap HashMap<String, Object> parameters);//@FieldMap 접속 되고 추가

    //현재 상태에서는 재사용 가능한게 POST방식만 만들어둠. (URL에 파라메터 노출이 없는 형태 보안높음)
    //하지만 공공데이터 포털이나 공공의 목적으로 만들어진 API들을 GET방식이 많음.(URL에 파라메터 노출이 있음 보안낮음)

    @GET("{url}")//GET방식은 BASE URL + URL(맵핑) +?뒤에 나오는 파라메터
    Call<String> getDataGET(@Path("url") String url, @QueryMap HashMap<String, String> parameters);

    @POST("file.f")
    @Multipart
    Call<String> sendFile(@Part MultipartBody.Part file); //@FieldMap 접속 되고 추가
}
