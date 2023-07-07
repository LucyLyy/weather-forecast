package com.example.forcast.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class dayBean {
    /*
 "data":{
        "shidu":"55%",
        "pm25":15,
        "pm10":22,
        "quality":"优",
        "wendu":"32",
        "ganmao":"各类人群可自由活动",
        "forecast":[
            {
                "date":"29",
                ...
 */
    @SerializedName("shidu")
    private String shidu;
    @SerializedName("pm25")
    private String pm25;
    @SerializedName("wendu")
    private String wendu;

    @SerializedName("quality")
    private String quality;
    @SerializedName("ganmao")
    private String ganmao;
    @SerializedName("forecast")
    private List<futureBean> cast;

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public List<futureBean> getCast() {
        return cast;
    }
    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public void setCast(List<futureBean> cast) {
        this.cast = cast;
    }

    @Override
    public String toString() {
        return "dayBean{" +
                "shidu='" + shidu + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", wendu='" + wendu + '\'' +
                ", quality='" + quality + '\'' +
                ", ganmao='" + ganmao + '\'' +
                ", cast=" + cast +
                '}';
    }
}
