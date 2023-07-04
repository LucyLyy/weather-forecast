package com.example.broadcast.util;

import android.util.Log;
import android.widget.Toast;

import com.example.broadcast.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {
    public static final String URL_WEATHER_WITH_FUTURE = "http://t.weather.itboy.net/api/weather/city/";
    public static String doGet(String urlStr) {
        String result = "";
        HttpURLConnection connection = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        InputStream inputStream;
        // 连接网络
        try {
            URL urL = new URL(urlStr);

            connection = (HttpURLConnection) urL.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);

            // 从连接中读取数据
            if(HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                inputStream = connection.getInputStream(); //获取异常数据
            }else {
                inputStream = connection.getErrorStream();//获取正常数据
            }
            inputStreamReader=new InputStreamReader(inputStream);
            // 二进制流送入缓冲区
            bufferedReader = new BufferedReader(inputStreamReader);

            // 从缓存区中一行行读取字符串
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String getWeatherOfCity(String cityId) {
        // 拼接出获取天气数据的URL
        // http://t.weather.itboy.net/api/weather/city/101030100
        String weatherUrl = URL_WEATHER_WITH_FUTURE  + cityId;
        Log.d("fan", "----weatherUrl----" + weatherUrl);
        String weatherResult = doGet(weatherUrl);
        if(weatherResult.contains("\"status\":404")){
            ToastUtil.toastLong(MainActivity.mainActivity, "参数位数不对！请输入九位城市ID");
            weatherResult="";
        }
        if(weatherResult.contains("\"status\":403")){
            ToastUtil.toastLong(MainActivity.mainActivity, "城市ID错误，不存在对应城市！");
            weatherResult="";
        }
        Log.d("fan", "----weatherResult----" + weatherResult);
        return weatherResult;
    }
}
