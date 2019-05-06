package android.space.lingen.webviewdemo.Main.webview.activity;

import android.os.Bundle;
import android.space.lingen.webviewdemo.Main.BaseFragmentActivity;
import android.space.lingen.webviewdemo.Main.webview.fragment.CrosswalkWebViewFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import org.xwalk.core.XWalkActivityDelegate;

/**
 * Created by lingen on 5/15/16.
 */
public class CrosswalkWebViewActivity extends BaseFragmentActivity {

    @Override
    public Fragment getFragment() {
        return new CrosswalkWebViewFragment();
    }

    private XWalkActivityDelegate mActivityDelegate;

//    protected abstract void onXWalkReady();

    public boolean isXWalkReady() {
        return this.mActivityDelegate.isXWalkReady();
    }

    public boolean isSharedMode() {
        return this.mActivityDelegate.isSharedMode();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Runnable cancelCommand = new Runnable() {
            public void run() {
                CrosswalkWebViewActivity.this.finish();
            }
        };
        Runnable completeCommand = new Runnable() {
            public void run() {
                //CrosswalkWebViewActivity.this.onXWalkReady();
                ((CrosswalkWebViewFragment)findFragmentById()).onWebViewLoad();
            }
        };
        this.mActivityDelegate = new XWalkActivityDelegate(this, cancelCommand, completeCommand);
    }

    protected void onResume() {
        super.onResume();
        this.mActivityDelegate.onResume();
    }

}
