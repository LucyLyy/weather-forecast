package com.example.forecast;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.forecast.Bean.WeatherBean;
import com.example.forecast.Bean.futureBean;
import com.example.forecast.adapter.FutureWeatherAdapt;
import com.example.forecast.util.CityUtils;
import com.example.forecast.util.ImgUtil;
import com.example.forecast.util.NetUtil;
import com.example.forecast.util.ToastUtil;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String cityid = null;//储存输入的城市ID
    int count = 0; //储存次数
    int flag = 0;//是否有的标志(默认没有)
    private AppCompatSpinner mSpinner;
    private ArrayAdapter<String> mSpAdapter;
    private String[] mCities;
    public static MainActivity mainActivity;
    public static EditText editCityId;
    private TextView tvWeather, tvTem, tvUpdateTime, tvHumidity, tvpm25, tvAir, tvprovince;
    private ImageView ivWeather;
    private FutureWeatherAdapt mWeatherAdapter;
    private RecyclerView rlvFutureWeather;
    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String weather = (String) msg.obj;

                Log.d("fan", "---主线程已收到天气数据---" + weather);
                //加入缓存
                if (weather != null) {
                    if (count == 3) count = 0;//缓存最后三次，自动更新最旧的那条
                    count++;
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
                    editor.putString("weatherBean" + count, weather);
                    editor.commit();

                    Gson gson = new Gson();
                    WeatherBean weatherBean = gson.fromJson(weather, WeatherBean.class);
                    Log.d("fan", "---在线解析后的天气---" + weatherBean.toString());
                    updateUiOfWeather(weatherBean);
                }
            }
        }
    };

    private void updateUiOfWeather(WeatherBean weatherBean) {
        if (weatherBean == null) {
            return;
        }
        String wendu = weatherBean.getData().getWendu();
        String shidu = weatherBean.getData().getShidu();
        String city = weatherBean.getCityinfo().getCity();
        String pm25 = weatherBean.getData().getPm25();
        String quality = weatherBean.getData().getQuality();
        String province = weatherBean.getCityinfo().getParent();
        String updatetime = weatherBean.getCityinfo().getUpdateTime();
        if (city.contains(province)) {
            province = "";
        }
        List<futureBean> futureWeather = weatherBean.getData().getCast();
        futureBean today = futureWeather.get(0);
        if (today == null) return;
        tvprovince.setText(province + " " + city + "(" + today.getYmd() + today.getWeek() + ")");
        tvTem.setText(wendu + "°");
        tvpm25.setText("PM2.5:\t" + pm25);
        tvAir.setText("空气:\t" + quality + "\n" + weatherBean.getData().getGanmao() + "\n");
        tvWeather.setText(today.getType());
        tvUpdateTime.setText("更新时间:\t" + updatetime);
        tvHumidity.setText("湿度:\t" + shidu);
        ivWeather.setImageResource(ImgUtil.getImgResOfWeather(today.getType()));//更新天气图片

        futureWeather.remove(0);  //去掉今天的
        //未来天气的展示
        mWeatherAdapter = new FutureWeatherAdapt(this, futureWeather);
        rlvFutureWeather.setAdapter(mWeatherAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rlvFutureWeather.setLayoutManager(layoutManager);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        initView();
    }

    private void initView() {
        mSpinner = findViewById(R.id.sp_city);
        mCities = getResources().getStringArray(R.array.cities);
        mSpAdapter = new ArrayAdapter<>(this, R.layout.sp_item_layout, mCities);
        mSpinner.setAdapter(mSpAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = mCities[position];
                String cityid = getCityid(selectedCity);
                getWeatherOfCityId(cityid);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvWeather = findViewById(R.id.tv_weather);
        tvAir = findViewById(R.id.tv_air);
        tvTem = findViewById(R.id.tv_tem);
        tvUpdateTime = findViewById(R.id.tv_updateTime);
        tvHumidity = findViewById(R.id.tv_humidity);
        tvpm25 = findViewById(R.id.tv_pm25);
        tvprovince = findViewById(R.id.tv_province);
        rlvFutureWeather = findViewById(R.id.rlv_future_weather);
        editCityId = findViewById(R.id.editCityId);
        ivWeather = findViewById(R.id.iv_weather);
    }

    private String getCityid(String cityname) {
        Context context = MainActivity.this;
        String cityId = "";
        try {
            // 读取JSON文件内容为字符串
            String json = readJsonFromAssets(context, "new_city.json");
            // 查找城市ID
            cityId = CityUtils.findCityId(cityname, json);
            if (cityId != null) {
                System.out.println("城市ID为：" + cityId);
            } else {
                System.out.println("未找到匹配的城市名称");
            }
        } catch (IOException e) {
            System.out.println("读取JSON文件失败：" + e.getMessage());
//            e.printStackTrace();
        }
        return cityId;
    }

    private String readJsonFromAssets(Context context, String fileName) throws IOException {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    public void search(View view) {
        cityid = editCityId.getText().toString();
        //点击搜索后，收起软键盘
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im != null) {
            im.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        //先看缓存有没有数据
        if (count != 0) {
            String weatherStriing = "";
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            for (int i = 1; i <= count; i++) {  //查找三次缓存
                weatherStriing = sharedPreferences.getString("weatherBean" + i, null);
                String weul = "\"citykey\":" + "\"" + cityid + "\"";
                if (weatherStriing.contains(weul)) {
                    //开始解析本地数据
                    Gson gson = new Gson();
                    WeatherBean weatherBean = gson.fromJson(weatherStriing, WeatherBean.class);
                    Log.d("fan", "---本地解析后的天气---" + weatherBean.toString());
                    updateUiOfWeather(weatherBean);
                    flag = 1;//找到了
                    break;//找到就退出循环
                }
                flag = 0;//没找到
            }
        }
        if (flag == 0) {
            //在线查找
            getWeatherOfCityId(cityid);
        }
        editCityId.setText("");
    }

    //子线程
    private void getWeatherOfCityId(String cityid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络
                String weatherofcity = NetUtil.getWeatherOfCity(cityid);
                Message message = Message.obtain();
                message.what = 0;
                message.obj = weatherofcity;

                mHandler.sendMessage(message);
            }
        }).start();
    }

    public void refresh(View view) {
        if (cityid != null) {
            getWeatherOfCityId(cityid);
        } //重新请求在线数据
        else
            ToastUtil.toastLong(MainActivity.this, "目前还没有显示天气数据！");
    }

}