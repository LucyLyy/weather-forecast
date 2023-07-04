package com.example.broadcast.Bean;

import com.google.gson.annotations.SerializedName;

public class cityBean {
    /*
  "cityInfo":{
        "city":"天津市",
        "citykey":"101030100",
        "parent":"天津",
        "updateTime":"11:31"
    },
*/
    @SerializedName("parent")
    private String parent;
    @SerializedName("city")
    private String city;
    @SerializedName("updateTime")
    private String updateTime;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "InBean{" +
                "parent='" + parent + '\'' +
                ", city='" + city + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
