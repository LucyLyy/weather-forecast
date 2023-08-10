package com.example.forecast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forecast.Bean.futureBean;
import com.example.forecast.R;
import com.example.forecast.util.ImgUtil;

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
           // holder.tvfx.setText("风向:"+dayBean.getFx());
            holder.tvfl.setText("风力:"+dayBean.getFl());
           // holder.tvnotice.setText(dayBean.getNotice());
            holder.ivWeather.setImageResource(ImgUtil.getImgResOfWeather(dayBean.getType()));
    }

    @Override
    public int getItemCount() {
        return (mdaybeans == null) ? 0 : mdaybeans.size();
    }

    class WeatherViewaHolder extends RecyclerView.ViewHolder {
        TextView tvWeather,tvDay,tvAir,tvtemH,tvtemL,tvfl;
//        TextView tvfx,tvnotice;
        ImageView ivWeather;
        public WeatherViewaHolder(@NonNull View itemView) {
            super(itemView);
            tvtemH=itemView.findViewById(R.id.tv_temH);
            tvtemL=itemView.findViewById(R.id.tv_temL);
            tvWeather=itemView.findViewById(R.id.tv_weather);
            tvDay=itemView.findViewById(R.id.tv_day);
            tvAir=itemView.findViewById(R.id.tv_air);
            tvfl=itemView.findViewById(R.id.tv_fl);
//            tvfx=itemView.findViewById(R.id.tv_fx);
//            tvnotice=itemView.findViewById(R.id.tv_notice);
            ivWeather=itemView.findViewById(R.id.iv_weather);
        }
    }

}
