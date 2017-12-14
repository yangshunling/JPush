package com.example.anonymous.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Anonymous on 2017/12/14.
 */

public class PushReceiver extends BroadcastReceiver {

    private static final String TAG = "JPush";

    @Override
    public void onReceive(Context context, Intent intent) {

        //广播接受事件
        Bundle bundle = intent.getExtras();

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            //获取到设备的clientId
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);

        }else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            // 获取透传消息
            String message = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "收到了自定义消息。消息内容是：" + message);
            EventBus.getDefault().post(new MessageEvent(message));

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            // 收到推送通知，可以做统计分析等
            Log.d(TAG, "收到了通知");

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            // 自定义用户点击通知栏的后续操作，根据type不同跳转到不同的界面
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);//通知标题
            String content = bundle.getString(JPushInterface.EXTRA_ALERT);//通知内容
            String type = bundle.getString(JPushInterface.EXTRA_EXTRA);//通知类型
            Log.d(TAG, "用户点击了通知"+title+"---"+content);

            //自定义打开的界面
            if (type.equals("1")){
                Intent event = new Intent(context, TwoActivity.class);
                event.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(event);
            }else {
                Intent event = new Intent(context, MainActivity.class);
                event.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(event);
            }


        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

}
