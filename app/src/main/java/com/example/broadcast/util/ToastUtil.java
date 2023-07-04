package com.example.broadcast.util;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

public class ToastUtil {

    public static void toastShort(Context context, String msg) {
        try {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();}
        catch (Exception e){ //解决在子线程中调用Toast的异常情况处理
            Looper.prepare();
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    public static void toastLong(Context context, String msg) {
        try {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();}
        catch (Exception e){
            Looper.prepare();
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            Looper.loop();
        }
    }
}
