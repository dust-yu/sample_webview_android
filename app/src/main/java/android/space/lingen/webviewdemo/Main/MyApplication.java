package android.space.lingen.webviewdemo.Main;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

public class MyApplication extends MultiDexApplication {

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    private void tt() {
//        boolean mUseMultiprocess = true;
//        boolean mEnableRemoteDebugging = true;
//        final GeckoRuntimeSettings.Builder runtimeSettingsBuilder = new GeckoRuntimeSettings.Builder();
//        runtimeSettingsBuilder
//                .useContentProcessHint(mUseMultiprocess)
//                .remoteDebuggingEnabled(mEnableRemoteDebugging)
//                .consoleOutput(true)
//                .build();
//        runtime = GeckoRuntime.create(this, runtimeSettingsBuilder.build());
    }
}
