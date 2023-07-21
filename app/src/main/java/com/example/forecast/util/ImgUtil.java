package com.example.forecast.util;

import com.example.forecast.R;

public class ImgUtil {
    public static int getImgResOfWeather(String type) {
        int res = 0;
        switch (type) {
            case "晴":
                res = R.drawable.riqing;
                break;
            case "多云":
                res = R.drawable.rijianduoyun;
                break;
            case "阴":
                res = R.drawable.yin;
                break;
            case "扬沙":
                res = R.drawable.yangsha;
                break;
            case "沙尘暴":
                res = R.drawable.shachenbao;
                break;
            case "浮尘":
                res = R.drawable.fuchen;
                break;
            case "雾":
                res = R.drawable.wu;
                break;
            case "霾":
                res = R.drawable.mai;
                break;
            case "强沙尘暴":
                res = R.drawable.qiangshachenbao;
                break;
            case "小雨":
                res = R.drawable.xiaoyu;
                break;
            case "小到中雨":
                res = R.drawable.xiaodaozhongyu;
                break;
            case "中雨":
                res = R.drawable.zhongyu;
                break;
            case "中到大雨":
                res = R.drawable.zhongdaodayu;
                break;
            case "大雨":
                res = R.drawable.dayu;
                break;
            case "大到暴雨":
                res = R.drawable.dadaobaoyu;
                break;
            case "暴雨":
                res = R.drawable.baoyu;
                break;
            case "暴雨到大暴雨":
                res = R.drawable.baodaodabaoyu;
                break;
            case "大暴雨":
                res = R.drawable.dabaoyu;
                break;
            case "大暴雨到特大暴雨":
                res = R.drawable.dabaoyudaotedabaoyu;
                break;
            case "特大暴雨":
                res = R.drawable.tedabaoyu;
                break;
            case "冻雨":
                res = R.drawable.dongyu;
                break;
            case "阵雨":
                res = R.drawable.zhenyusvg;
                break;
            case "雷阵雨":
                res = R.drawable.leizhenyu;
                break;
            case "雨夹雪":
                res = R.drawable.yujiaxue;
                break;
            case "雷阵雨伴有冰雹":
                res = R.drawable.leizhenyubanyoubinbao;
                break;
            case "小雪":
                res = R.drawable.xiaoxue;
                break;
            case "小到中雪":
                res = R.drawable.xiaodaozhongxue;
                break;
            case "中雪":
                res = R.drawable.zhongxue;
                break;
            case "中到大雪":
                res = R.drawable.zhongdaodayu;
                break;
            case "大雪":
                res = R.drawable.daxue;
                break;
            case "大到暴雪":
                res = R.drawable.dadaobaoxue;
                break;
            case "暴雪":
                res = R.drawable.baoxue;
                break;
            case "阵雪":
                res = R.drawable.zhenxue;
                break;
            default:
                res = R.drawable.null_wu;

        }
        return res;
    }
}
