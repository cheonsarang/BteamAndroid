package com.example.chota.common;


import com.example.chota.myInfo.MemberVO;

public class CommonVal {
    //공유 자원 : 공통으로 사용할 객체, 변수, enum 등등을 모아놓은 클래스



    public static MemberVO loginInfo = null;// null== 로그인 안된 상태,




    /* loginInfo가 null이 아니게 바꾸고 로그인 된 상태인지 판단해보기.
    *   LoginActivity에서 Intent이용 MainActivity로 이동 후 ↑
    * */

}
