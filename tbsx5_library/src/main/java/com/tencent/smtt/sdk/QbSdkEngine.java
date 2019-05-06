package com.tencent.smtt.sdk;

import android.content.Context;
import android.util.Log;

import com.tencent.smtt.export.external.TbsCoreSettings;

import java.util.HashMap;

public class QbSdkEngine {

    //App 在启动后（例如在 Application 的 onCreate 中）立刻调用 QbSdk 的预加载接口 initX5Environment
    //当本地没有宿主x5内核可用，此时需要在wifi条件下下载x5内核(23M左右，耗时90秒左右)，如果在此之前打开webview可能导致无内核可用而使用系统内核的情况
    public static void init(Context context) {
        QbSdk.initX5Environment(context, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                Log.i("tbsx5", "initX5Environment onCoreInitFinished");
            }
            @Override
            public void onViewInitFinished(boolean b) {
                Log.i("tbsx5", "initX5Environment onViewInitFinished b="+b);
            }
        });
        HashMap map = new HashMap();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_PRIVATE_CLASSLOADER, true);
        QbSdk.initTbsSettings(map);
        QbSdk.preInit(context, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                Log.i("tbsx5", "preInit onCoreInitFinished");
            }
            @Override
            public void onViewInitFinished(boolean b) {
                Log.i("tbsx5", "preInit onViewInitFinished b="+b);
            }
        });
    }

}
