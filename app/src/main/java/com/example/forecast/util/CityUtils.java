package com.example.forecast.util;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class CityUtils {
    public static String findCityId(String cityName, String jsonData) {
        try {
            // 创建JSONArray对象
            JSONArray jsonArray = new JSONArray(jsonData);

            // 遍历JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // 获取城市名称
                String name = jsonObject.getString("city_name");

                // 判断城市名称是否匹配
                if (cityName.equals(name)) {
                    return jsonObject.getString("city_code");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null; // 如果未找到匹配的城市名称，则返回null
    }


}
