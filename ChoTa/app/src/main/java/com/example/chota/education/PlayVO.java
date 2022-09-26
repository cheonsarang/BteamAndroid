package com.example.chota.education;

public class PlayVO {
  //  @SerializedName("모집시작")
    private String mojibstart;

   // @SerializedName("모집마감")
    private String mojibend;

   // @SerializedName("창의적체험활동제목")
    private String hwaldong;

   // @SerializedName("창의적체험활동장소")
    private String jangso;

   // @SerializedName("주소")
    private String addr;

    public String getMojibstart() {
        return mojibstart;
    }

    public void setMojibstart(String mojibstart) {
        this.mojibstart = mojibstart;
    }

    public String getMojibend() {
        return mojibend;
    }

    public void setMojibend(String mojibend) {
        this.mojibend = mojibend;
    }

    public String getHwaldong() {
        return hwaldong;
    }

    public void setHwaldong(String hwaldong) {
        this.hwaldong = hwaldong;
    }

    public String getJangso() {
        return jangso;
    }

    public void setJangso(String jangso) {
        this.jangso = jangso;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
