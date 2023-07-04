package com.example.broadcast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.broadcast.Bean.WeatherBean;
import com.example.broadcast.Bean.futureBean;
import com.example.broadcast.adapter.FutureWeatherAdapt;
import com.example.broadcast.util.NetUtil;
import com.example.broadcast.util.ToastUtil;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String  cityid=null;//储存输入的城市ID
    int count = 0; //储存次数
    int flag=0;//是否有的标志(默认没有)
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
        ivWeather.setImageResource(getImgResOfWeather(today.getType()));

        futureWeather.remove(0);  //去掉今天的
        //未来天气的展示
        mWeatherAdapter = new FutureWeatherAdapt(this, futureWeather);
        rlvFutureWeather.setAdapter(mWeatherAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rlvFutureWeather.setLayoutManager(layoutManager);

    }
//获得天气图片
    private int getImgResOfWeather(String type) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        initView();
    }

    private void initView() {
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
                String weul="\"citykey\":"+"\""+cityid+"\"";
                if (weatherStriing.contains(weul)) {
                    //开始解析本地数据
                    Gson gson = new Gson();
                    WeatherBean weatherBean = gson.fromJson(weatherStriing, WeatherBean.class);
                    Log.d("fan", "---本地解析后的天气---" + weatherBean.toString());
                    updateUiOfWeather(weatherBean);
                    flag=1;//找到了
                    break;//找到就退出循环
                }flag=0;//没找到
            }
        }
         if(flag==0){
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
        if(cityid!=null){
            getWeatherOfCityId(cityid);} //重新请求在线数据
        else
            ToastUtil.toastLong(MainActivity.this,"目前还没有显示天气数据！");
    }

}