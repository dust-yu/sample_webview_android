package android.space.lingen.webviewdemo.Main.webview.fragment;

import android.os.Bundle;
import android.space.lingen.webviewdemo.R;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xwalk.core.XWalkView;

/**
 * Created by lingen on 5/15/16.
 */
public class CrosswalkWebViewFragment extends Fragment {

    private XWalkView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_corsswalk_webview,container,false);
        findView(view);
        return view;
    }

    private void findView(View view){
        this.webView = view.findViewById(R.id.webview_crosswalk);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    public void onWebViewLoad() {
        this.webView.loadUrl("http://html5test.com");
    }
}
