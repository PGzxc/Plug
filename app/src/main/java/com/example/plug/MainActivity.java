package com.example.plug;

import android.Manifest;
import android.os.Build;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.morgoo.droidplugin.pm.PluginManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    String filepath = "/storage/emulated/0/Download/2.apk";
    String packageName = "com.example.second";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 23) {
            requestExternalStoragePermission();
        }
    }

    /**
     * 添加插件
     *
     * @param view
     */
    public void addPlug(View view) {
        PluginUtils.installApk(this, filepath, packageName);
    }

    /**
     * 启动插件
     *
     * @param view
     */

    public void startPlug(View view) {
        PluginUtils.startActivity(this, packageName);
    }

    /**
     * 删除插件
     *
     * @param view
     */
    public void delPlug(View view) {
        PluginUtils.unInstallApk(this, packageName);
    }

    private void requestExternalStoragePermission() {
        RxPermissions rxPermissions = new RxPermissions(this); // where this is an Activity instance
        rxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Log.d(TAG, "被允许了");
                        } else {
                            Log.d(TAG, "被否决了");
                        }
                    }
                });
    }
}
