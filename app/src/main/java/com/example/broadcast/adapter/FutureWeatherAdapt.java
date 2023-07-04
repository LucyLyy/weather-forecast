package com.example.broadcast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.broadcast.Bean.futureBean;
import com.example.broadcast.R;

import java.util.List;

public class FutureWeatherAdapt extends RecyclerView.Adapter<FutureWeatherAdapt.WeatherViewaHolder> {
    private Context mContext;
    private List<futureBean> mdaybeans;

    public FutureWeatherAdapt(Context Context, List<futureBean> daybeans) {
        mContext = Context;
        this.mdaybeans = daybeans;
    }

    @NonNull
    @Override
    public WeatherViewaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.futureweather_layout,parent,false);
        WeatherViewaHolder weatherViewaHolder=new WeatherViewaHolder(view);
        return weatherViewaHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewaHolder holder, int position) {
        futureBean dayBean = mdaybeans.get(position);
            holder.tvtemH.setText(dayBean.getHightem());
            holder.tvtemL.setText(dayBean.getLowtem());
            holder.tvAir.setText("空气指数: "+dayBean.getAqi());
            holder.tvWeather.setText(dayBean.getType());
            holder.tvDay.setText("("+dayBean.getYmd()+dayBean.getWeek()+")");
            holder.tvfx.setText("风向:"+dayBean.getFx());
            holder.tvfl.setText("风力:"+dayBean.getFl());
            holder.tvnotice.setText(dayBean.getNotice());
            holder.ivWeather.setImageResource(getImgResOfWeather(dayBean.getType()));
    }

    @Override
    public int getItemCount() {
        return (mdaybeans == null) ? 0 : mdaybeans.size();
    }

    class WeatherViewaHolder extends RecyclerView.ViewHolder {
        TextView tvWeather,tvDay,tvfx,tvfl,tvAir,tvtemH,tvtemL,tvnotice;
        ImageView ivWeather;
        public WeatherViewaHolder(@NonNull View itemView) {
            super(itemView);
            tvtemH=itemView.findViewById(R.id.tv_temH);
            tvtemL=itemView.findViewById(R.id.tv_temL);
            tvWeather=itemView.findViewById(R.id.tv_weather);
            tvDay=itemView.findViewById(R.id.tv_day);
            tvAir=itemView.findViewById(R.id.tv_air);
            tvfl=itemView.findViewById(R.id.tv_fl);
            tvfx=itemView.findViewById(R.id.tv_fx);
            tvnotice=itemView.findViewById(R.id.tv_notice);
            ivWeather=itemView.findViewById(R.id.iv_weather);
        }
    }

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
}
