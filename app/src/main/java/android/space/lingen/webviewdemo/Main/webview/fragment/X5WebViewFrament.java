package android.space.lingen.webviewdemo.Main.webview.fragment;

import android.os.Bundle;
import android.space.lingen.webviewdemo.R;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.smtt.sdk.QbSdkEngine;
import com.tencent.smtt.sdk.X5WebView;

/**
 * Created by lingen on 5/15/16.
 */
public class X5WebViewFrament extends Fragment {

    private X5WebView webView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QbSdkEngine.init(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_x5_webview,container,false);
        findView(view);
        return view;
    }

    private void findView(View view){
        this.webView = view.findViewById(R.id.webview_x5);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.webView.loadUrl("http://html5test.com");
    }
}
