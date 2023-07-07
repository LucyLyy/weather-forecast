package com.example.forcast.Bean;


import com.google.gson.annotations.SerializedName;

public class futureBean {
    /*
     "forecast":[
                {
                    "date":"29",
                    "high":"高温 34℃",
                    "low":"低温 23℃",
                    "ymd":"2023-06-29",
                    "week":"星期四",
                    "sunrise":"04:48",
                    "sunset":"19:41",
                    "aqi":94,
                    "fx":"东南风",
                    "fl":"2级",
                    "type":"晴",
                    "notice":"愿你拥有比阳光明媚的心情"
                },
    */
    @SerializedName("notice")
    private String notice;
    @SerializedName("fx")
    private String fx;
    @SerializedName("fl")
    private String fl;
    @SerializedName("date")
    private String date;
    @SerializedName("type")
    private String type;
    @SerializedName("ymd")
    private String ymd;
    @SerializedName("week")
    private String week;
    @SerializedName("high")
    private String hightem;
    @SerializedName("aqi")
    private String aqi;
    @SerializedName("low")
    private String lowtem;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getHightem() {
        return hightem;
    }

    public void setHightem(String hightem) {
        this.hightem = hightem;
    }

    public String getLowtem() {
        return lowtem;
    }

    public void setLowtem(String lowtem) {
        this.lowtem = lowtem;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    @Override
    public String toString() {
        return "futureBean{" +
                "notice='" + notice + '\'' +
                ", fx='" + fx + '\'' +
                ", fl='" + fl + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", ymd='" + ymd + '\'' +
                ", week='" + week + '\'' +
                ", hightem='" + hightem + '\'' +
                ", aqi='" + aqi + '\'' +
                ", lowtem='" + lowtem + '\'' +
                '}';
    }
}
