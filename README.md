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


