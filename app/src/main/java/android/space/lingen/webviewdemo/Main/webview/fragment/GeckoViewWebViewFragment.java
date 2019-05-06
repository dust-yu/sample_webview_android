package android.space.lingen.webviewdemo.Main.webview.fragment;

import android.os.Bundle;
import android.space.lingen.webviewdemo.R;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoRuntimeSettings;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoSessionSettings;
import org.mozilla.geckoview.GeckoView;

/**
 * Created by lingen on 5/15/16.
 */
public class GeckoViewWebViewFragment extends Fragment {

    private GeckoSession session;
    private GeckoView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_geckoview_webview,container,false);
        findView(view);
        return view;
    }

    private void findView(View view){
        this.webView = view.findViewById(R.id.webview_geckoview);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initGeckoSession();
        this.webView.setSession(session);
        session.loadUri("http://html5test.com"); // Or any other URL...
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(session!=null) session.close();
        session = null;
    }

    private void initGeckoSession() {
        if(session != null) return;
        boolean mUseMultiprocess = true;
        boolean mEnableRemoteDebugging = true;
        boolean mFullAccessibilityTree = false;
        boolean mUseTrackingProtection = false;
        boolean mUsePrivateBrowsing = false;

        session = new GeckoSession(new GeckoSessionSettings.Builder()
                .useMultiprocess(mUseMultiprocess)
                .usePrivateMode(mUsePrivateBrowsing)
                .useTrackingProtection(mUseTrackingProtection)
                .fullAccessibilityTree(mFullAccessibilityTree)
                .build());
        final GeckoRuntimeSettings.Builder runtimeSettingsBuilder = new GeckoRuntimeSettings.Builder();
        runtimeSettingsBuilder
                .useContentProcessHint(mUseMultiprocess)
                .remoteDebuggingEnabled(mEnableRemoteDebugging)
                .consoleOutput(true)
                .build();
        //runtime = GeckoRuntime.create(getContext(), runtimeSettingsBuilder.build());
        session.open(GeckoRuntime.getDefault(getContext()));
    }
}
