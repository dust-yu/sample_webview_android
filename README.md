## Hybrid技术开发之 Android WebView的可选替换方案。 

随着 Web 技术和移动设备的快速发展，Hybrid 技术目前已经成为一种主流常见的方案。一套好的Hybrid架构方案能让 App 既能拥有极致的体验和性能，同时也能拥有 Web技术灵活的开发模式、跨平台能力以及热更新机制，想想是不是都鸡冻不已。 

<!--more-->

Hybrid 技术开发，当前的趋势是HTML占据了越来越重要的位置，且H5不再是简单的浏览网页的行为，它承担着许多原本原生APP开发的功能。然后，当实际开发才发现Android平台H5的支持/渲染效率，是个非常麻烦的事情。

<!--more-->

当前WebView在Android各平台上表现的不一样，由于机型过多且集成的WebView内核版本不一致，不同手机的WebView兼容性和性能差异也较大。
如果APP对H5有较高的依赖性，则需要一个较好的、综合性能优异、各平台统一的WebView方案。

因此，就需要对各种WebView替换方案做优选比对。

> PS：iOS Hybrid开发方案的WebView没有太大问题。因为，UIWebView没有碎片化问题，且性能极佳，也不需要考虑太多兼容性的问题。



##### 目前已知 Android WebView的可选替换方案： 

1. Android系统默认的WebView
2. [腾讯 X5内核的X5WebView](https://x5.tencent.com/)
3. [Crosswalk 基于Chromium/Blink的WebView](https://github.com/crosswalk-project)
4. [Mozilla Gecko浏览器引擎的Geckoview](https://mozilla.github.io/geckoview/)


如上述几种方案的对照如下表：

##### 测试对比性能指标：(未设置浏览器缓存)

| WebView方案                | 实际效果 | html5test评测分数| 手机系统 | 方案说明 | 优缺点 |
|----------              |---------|---------      |----     |-------  |-------|
| OriginalWebView(系统默认)  | 最弱     | 485             | Honor10Lite/Android 9.0| Android默认                                               | 优：不需要依赖额外的JAR或so库，系统原生API。 缺：兼容性不好，性能在不同手机上差异较大。 |
| X5 WebView                 | 一般     | 494             | Honor10Lite/Android 9.0 | X5内核为QQ浏览器、微信、手机QQ,提供稳定安全的增强浏览服务 | 优：各平台兼容性一致，支持动态下载内核、且共享宿主内核方案，SDK占用工程大小不到400K，且腾讯系产品QQ浏览器、微信、手机QQ正在使用，可信度高/持续维护。  缺：Cordova支持不友好，不支持arm64位的so库,不支持静态工程依赖的方式，不支持海外版本(由于GooglePlay限制，不允许二进制代码的动态下发，导致审核会出问题)。 | 
| Crosswalk                  | 最佳     | 498             | Honor10Lite/Android 9.0 | Intel 开源技术中心发起的基于 Chromium/Blink的方案         | 优：各平台兼容性一致，性能较好，Corodva支持友好，支持静态工程依赖方式。 缺：占用工程大小JAR包3.6M+|so库armeabi-v7a下36M+，区分不同的arm/arm64/x86等CPU架构，不支持动态下载内核|宿主共享支持不友好。 |
| GeckoView                  | 一般     | 492             | Honor10Lite/Android 9.0 | Mozilla  | |

<!--笔者有一个较老的华为荣耀3C手机，购置于3年前，分别使用系统自带的WebView,X5 WebView,Crosswalk三种模式访问html5test网站，得出的评分结果分别是:-->

<!--![系统默认](https://raw.githubusercontent.com/dust-yu/sample_webview_android/master/screenshot/Screenshot_20190506_193808_android.space.lingen.webviewdemo.jpg){:height="100" width="400" zoom: 50%;}-->
> OriginalWebView
<div align="center">    
<img src="https://raw.githubusercontent.com/dust-yu/sample_webview_android/master/screenshot/Screenshot_20190506_193808_android.space.lingen.webviewdemo.jpg" width="300" height="560" alt="系统默认" align="center" />
</div>

> X5 WebView
<div align="center">    
<img src="https://github.com/dust-yu/sample_webview_android/blob/master/screenshot/Screenshot_20190506_193824_android.space.lingen.webviewdemo.jpg?raw=true" width="300" height="560" alt="X5 WebView" align="center" />
</div>

> Crosswalk WebView
<div align="center">    
<img src="https://github.com/dust-yu/sample_webview_android/blob/master/screenshot/Screenshot_20190506_193831_android.space.lingen.webviewdemo.jpg?raw=true" width="300" height="560" alt="Crosswalk" align="center" />
</div>

> GeckoView
<div align="center">    
<img src="https://github.com/dust-yu/sample_webview_android/blob/master/screenshot/Screenshot_20190506_193841_android.space.lingen.webviewdemo.jpg?raw=true" width="300" height="560" alt="GeckoView" align="center" />
</div>


参见以上测试结果：

Crosswalk 效果最好：

1. 基于Chromium/Blink内核的方案，兼容性不存在任何问题
2. 性能佳
3. Corodva支持友好
4. 支持前端人员可以在PC Chrome上联调
5. 支持静态工程依赖方式

不足：

1. 目前已经不在继续维护
2. 占用工程大小JAR包3.6M+|so库armeabi-v7a下36M+
3. 区分不同的arm/arm64/x86等CPU架构，如果需要要支持arm64 X86 CPU，还需额外增加加so库。
4. 不支持动态下载内核，宿主共享支持不友好


TBS X5 WebView 效果中等：

1. 腾讯系产品QQ浏览器、微信、手机QQ正在使用，可信度高/持续维护
2. SDK占用工程大小不到400K，支持动态下载内核、共享宿主内核的方案
3. TBS X5最新更新内容：
    TBS 4.5版本Blink内核基线提升至Chromium for Android M66，并且适配Android主流平台，最新支持Android P版本； 
    具有更高的JS性能及更多的ES6特性支持；更全的W3C标准、CSS特性支持；内存占用更小；

不足：

1. 不支持arm64位的so库
2. 不支持静态工程依赖的方式
3. Cordova支持不友好
4. 不支持海外版本(由于GooglePlay限制，不允许二进制代码的动态下发，导致审核会出问题)



因此，结论：

1. 考虑使用方便，以及维护问题。 建议选择TBS X5，比较腾讯系各产品早已内置这个方案，且支持共享内核方案。  如果，国内APP则可以选择该方案，既能保证H5性能，又能不占用过多工程空间。
2. 类似笔者公司这样的APP，对H5有非常高的要求，大量的业务系统是由H5完成，且需要cordova与原生进行大量的交互 ，那这种情况下,crosswalk是我们唯一的选择，虽然加大了18M，但带来的好处是显而易见的

