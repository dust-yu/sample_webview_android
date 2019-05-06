package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.tbsx5.BuildConfig;

public class X5WebView extends com.tencent.smtt.sdk.WebView {

	public X5WebView(Context arg0) {
		super(arg0);
		setBackgroundColor(85621);

		/**
		 * 缺少setDataDirectorySuffix接口导致出现Android 9.0出现crash。
		 * 我们将会在下个版本sdk中补充部分新API level接口，您可以先把您的targetAPI降到28以下或者通过如下代码
		 */
		View view = getView();
		if(view instanceof android.webkit.WebView){
			android.webkit.WebView.setDataDirectorySuffix("x5");
		}
	}

	@SuppressLint("SetJavaScriptEnabled")
	public X5WebView(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
		/*
		  防止加载网页时调起系统浏览器
		 */
		com.tencent.smtt.sdk.WebViewClient client = new com.tencent.smtt.sdk.WebViewClient() {
			/**
			 * 防止加载网页时调起系统浏览器
			 */
			public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		};
		this.setWebViewClient(client);
		com.tencent.smtt.sdk.WebSettings webSetting = this.getSettings();
		webSetting.setJavaScriptEnabled(true);
		// this.setWebChromeClient(chromeClient);
		// WebStorage webStorage = WebStorage.getInstance();
//		initWebViewSettings();
//		this.getView().setClickable(true);
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initWebViewSettings() {
		com.tencent.smtt.sdk.WebSettings webSetting = this.getSettings();
		webSetting.setJavaScriptEnabled(true);
		webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
		webSetting.setAllowFileAccess(true);
		webSetting.setLayoutAlgorithm(com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
		webSetting.setSupportZoom(true);
		webSetting.setBuiltInZoomControls(true);
		webSetting.setUseWideViewPort(true);
		webSetting.setSupportMultipleWindows(true);
		// webSetting.setLoadWithOverviewMode(true);
		webSetting.setAppCacheEnabled(true);
		// webSetting.setDatabaseEnabled(true);
		webSetting.setDomStorageEnabled(true);
		webSetting.setGeolocationEnabled(true);
		webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
		// webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
		webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
		// webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
		webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);

		// this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
		// settings 的设计
	}

	@Override
	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
		if(!BuildConfig.DEBUG) return super.drawChild(canvas, child, drawingTime);
		boolean ret = super.drawChild(canvas, child, drawingTime);
		canvas.save();
		Paint paint = new Paint();
		paint.setColor(0x7fff0000);
		paint.setTextSize(24.f);
		paint.setAntiAlias(true);
		if (getX5WebViewExtension() != null) {
			canvas.drawText(this.getContext().getPackageName() + "-pid:"
					+ android.os.Process.myPid(), 10, 50, paint);
			canvas.drawText(
					"X5  Core:" + com.tencent.smtt.sdk.QbSdk.getTbsVersion(this.getContext()), 10,
					100, paint);
		} else {
			canvas.drawText(this.getContext().getPackageName() + "-pid:"
					+ android.os.Process.myPid(), 10, 50, paint);
			canvas.drawText("Sys Core", 10, 100, paint);
		}
		canvas.drawText(Build.MANUFACTURER, 10, 150, paint);
		canvas.drawText(Build.MODEL, 10, 200, paint);
		canvas.restore();
		return ret;
	}
}
