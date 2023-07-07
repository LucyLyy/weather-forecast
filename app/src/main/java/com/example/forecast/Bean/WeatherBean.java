package com.example.forecast.Bean;

import com.google.gson.annotations.SerializedName;

public class WeatherBean {
    /*
      "message":"success感谢又拍云(upyun.com)提供CDN赞助",
        "status":200,
        "date":"20230629",
        "time":"2023-06-29 14:35:31",
        "cityInfo":{
            "city":"天津市",
            "citykey":"101030100",
            "parent":"天津",
            "updateTime":"11:31"
        },
        "data":Object{...}
     */
    @SerializedName("date")
    private String date;
    @SerializedName("cityInfo")
    private cityBean cityinfo;
    @SerializedName("data")
    private dayBean data;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public cityBean getCityinfo() {
        return cityinfo;
    }

    public void setCityinfo(cityBean cityinfo) {
        this.cityinfo = cityinfo;
    }

    public dayBean getData() {
        return data;
    }

    public void setData(dayBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "date='" + date + '\'' +
                ", cityinfo=" + cityinfo +
                ", data=" + data +
                '}';
    }
}
