package com.example.anonymous.jpush;

import android.*;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        mTextView = findViewById(R.id.textview);
        requestPermission();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        mTextView.setText(event.getMessge());
    }

    //动态申请权限
    private void requestPermission() {
        PermissionsUtil.TipInfo tip = new PermissionsUtil.TipInfo("提示：", "使用此功能需要使用SD卡权限，你也可以去手机的权限管理中打开相应的权限", "取消", "前往");
        if (PermissionsUtil.hasPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        } else {
            PermissionsUtil.requestPermission(MainActivity.this, new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permission) {
                }

                @Override
                public void permissionDenied(@NonNull String[] permission) {

                }
            }, new String[]{android.Manifest.permission.CAMERA}, true, tip);
        }
    }
}
