package com.example.chota.education;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chota.R;
import com.example.chota.common.CommonVal;
import com.example.chota.conn.CommonConn;
import com.example.chota.myInfo.MemberVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class AcademyAdapter extends RecyclerView.Adapter<AcademyAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<AcademyVO> list;

    ArrayList<AcademyVO> temp_list = new ArrayList<>();
    Context context;
    AcademyActivity.MapOnclickEventCSR event;
    int cnt = 0;
    //MemberVO vo = CommonVal.loginInfo;
    AcademyVO vo = new AcademyVO();
    int[] randomArr;

    public AcademyAdapter(LayoutInflater inflater, ArrayList<AcademyVO> list, Context context, int[] randomArr) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
        this.randomArr = randomArr;

    }

    public AcademyAdapter(LayoutInflater inflater, ArrayList<AcademyVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    public AcademyAdapter(LayoutInflater inflater, ArrayList<AcademyVO> list, Context context, AcademyActivity.MapOnclickEventCSR event) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
        this.event = event;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_btm_dialog_recv,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView academy_name, addr, detail_addr, field;
        ImageView heart_edu_full, heart_edu_empty;
        FrameLayout heart_aca;
        Button btn_map;
        public ViewHolder(@NonNull View v) {
            super(v);
            academy_name = v.findViewById(R.id.academy_name);
            addr = v.findViewById(R.id.addr);
            detail_addr = v.findViewById(R.id.detail_addr);
            field = v.findViewById(R.id.field);
            heart_edu_full = v.findViewById(R.id.heart_edu_full);
            heart_edu_empty = v.findViewById(R.id.heart_edu_empty);
            heart_aca = v.findViewById(R.id.heart_aca);
            btn_map = v.findViewById(R.id.btn_map);
        }
        public void bind(@NonNull ViewHolder h, int i){
            h.academy_name.setText(list.get(i).getAcademy_name());
            h.addr.setText(list.get(i).getAddr());
            h.detail_addr.setText(list.get(i).getDetail_addr());
            if(vo.getHeart_aca() == 1) {
                h.heart_edu_full.setVisibility(View.VISIBLE);
                h.heart_edu_empty.setVisibility(View.INVISIBLE);
            }else {
                h.heart_edu_full.setVisibility(View.INVISIBLE);
                h.heart_edu_empty.setVisibility(View.VISIBLE);
            }
            //if(h.field == null || h.field.equals("")) {
            if(list.get(i).getField() == null || list.get(i).equals("null") || list.get(i).equals("")) {
                h.field.setText("정보 없음");
            }else {
                h.field.setText(list.get(i).getField());
            }

            h.btn_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("눌렀", "onClick: 다");
                    String addr = list.get(i).getAddr() + list.get(i).getDetail_addr();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            requestGeocode(addr);
                        }
                    }).start();

                }
            });

            h.heart_aca.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cnt == 0 ) {
                        heart_edu_full.setVisibility(View.VISIBLE);
                        heart_edu_empty.setVisibility(View.INVISIBLE);
                        vo.setUserid(CommonVal.loginInfo.getUserid());
                        vo.setAcademy_name(list.get(i).getAcademy_name());
                        vo.setAddr(list.get(i).getAddr());
                        vo.setDetail_addr(list.get(i).getDetail_addr());
                        vo.setStatus(list.get(i).getStatus());
                        vo.setField(list.get(i).getField());
                        vo.setOffice_code(CommonVal.loginInfo.getOffice_code());
                        vo.setHeart_aca(1);
                        cnt ++;
                        CommonConn likeconn = new CommonConn("academylike", context);
                        String tempVO = new Gson().toJson(vo);
                        likeconn.addParams("tempVO", tempVO);
                        likeconn.excuteConn(new CommonConn.ConnCallback() {
                            @Override
                            public void onResult(boolean isResult, String data) {

                            }
                        });
                    }else if(cnt == 1){
                        heart_edu_full.setVisibility(View.INVISIBLE);
                        heart_edu_empty.setVisibility(View.VISIBLE);
                        vo.setUserid(CommonVal.loginInfo.getUserid());
                        vo.setAcademy_name(list.get(i).getAcademy_name());
                        vo.setAddr(list.get(i).getAddr());
                        vo.setDetail_addr(list.get(i).getDetail_addr());
                        vo.setStatus(list.get(i).getStatus());
                        vo.setField(list.get(i).getField());
                        vo.setOffice_code(CommonVal.loginInfo.getOffice_code());
                        cnt = 0;
                        CommonConn delikeconn = new CommonConn("academydelike", context);
                        String tempVO = new Gson().toJson(vo);
                        delikeconn.addParams("tempVO", tempVO);
                        delikeconn.excuteConn(new CommonConn.ConnCallback() {
                            @Override
                            public void onResult(boolean isResult, String tempVO) {

                            }
                        });
                    }
                }
            });
        }
    }

    public void requestGeocode(String addr) {
        try {
            BufferedReader bufferedReader;
            StringBuilder stringBuilder = new StringBuilder();
            String query = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + URLEncoder.encode(addr, "UTF-8");
            URL url = new URL(query);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn != null) {
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "cqbq53cj4b");
                conn.setRequestProperty("X-NCP-APIGW-API-KEY", "7Bo4LnUXUasQGhPrbuoWl0uwUAnxdzr2aLmbhb89");
                conn.setDoInput(true);

                int responseCode = conn.getResponseCode();

                if (responseCode == 200) {
                    bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    bufferedReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }

                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                    Log.d("TAG", "requestGeocode: " + line);
                    JSONObject json = new JSONObject(line);
                    JSONArray jarr = json.getJSONArray("addresses");
                    Log.d("json", "json: " + json);
                    Log.d("jarr", "jarr: " + jarr);
                    json = jarr.getJSONObject(0);
                    Log.d("jsonjson", "json: " + json);
                    String x = json.getString("y");
                    String y = json.getString("x");
                    event.cameraUpdate(Double.parseDouble(x), Double.parseDouble(y));
                }
                bufferedReader.close();
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}


