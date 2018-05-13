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

![image.png](https://upload-images.jianshu.io/upload_images/2570030-bb960a5fce263a3f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](https://upload-images.jianshu.io/upload_images/2570030-9ca614fe1d9416b0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

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

![image.png](https://upload-images.jianshu.io/upload_images/2570030-9cc42d42d66337e4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

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

![image.png](https://upload-images.jianshu.io/upload_images/2570030-6b1cca250e64e07a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

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
    removeJavascriptInterface("searchBoxJavaBridge_") 
    removeJavascriptInterface("accessibility")；
    removeJavascriptInterface("accessibilityTraversal")；
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


