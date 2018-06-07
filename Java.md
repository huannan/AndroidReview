### Java网络编程（Socket）

#### 1. 网络编程相关基础知识

* IP地址：为实现网络中不同计算机之间的通信，每台计算机都必须有一个唯一的标识-IP地址。它是识别网络通讯的实体，可以理解为主机，也可以理解为每个路由器的端口。
* 端口号：一个通讯实体他可以拥有很多的通讯程序同时提供网络服务，这个时候就要通过端口号来区分通讯程序，一个通讯实体不能有两个通讯程序使用同一个端口号。端口号范围为0-65535，其中0-1023位为系统保留。
* TCP/UDP协议：

    * TCP：面向链接的保证可靠传输的协议，通过TCP协议传输，得到的是一个顺序的无差错的数据流，能提供两台计算机之间的可靠数据传输。
    * UDP协议：无链接的协议，每个数据包都是一个独立的信息，包括原地址和目的地址，他在网络上可以通过任何的途径把信息传递到目的地。至于能否达到目的地、达到目的地的时间、信息的准确性都不能得到保证。
    
* URL：统一的资源定位器，他指向的是我们互联网中的一些资源，主要是从输入流中读取数据，再通过输出流写入文件，执行的结果就是我们输入的浏览器地址的服务器返回的内容。

#### 2. 既然有可靠的TCP协议，为什么还需要不可靠的UDP协议？

* TCP协议是面向连接的、可靠的、有序的、以字节流的方式发送数据，通过**三次握手**方式建立连接，形成传输数据的通道，在连接中进行大量数据的传输，效率会稍低。可靠的协议肯定需要付出代价，因此TCP的传输效率远远不如UDP高。例如对数据的时间和检验必然会消耗计算机的计算时间和网络带宽。
* 不是所有的程序都需要保证数据传递的可靠性，例如视频聊天、音频传输、游戏、直播技术等只要保证数据连贯性即可，不考虑数据是否安全等问题，所以这些场景下用UDP更合适。

#### 3. URI与URL的区别

* URI是抽象的定义，不管用什么方法表示，只要能定位一个资源，就叫URI
* URI使用两种方法定位：

    * URL：用地址定位
    * URN：用名称定位
    
* URL与URN都是URI的子集，URI属于URL和URN更高层次的抽象

#### 4. Socket及其通信原理？

Socket是什么？

* Socket，又叫做套接字。网络上的两个程序通过一个双向的通信连接实现数据的交换，这个连接的一端称为一个socket。       
* Socket没有一个具体的实体，只是描述计算机之间完成通讯的一种抽象功能，可以理解为交通工具，有了这个交通工具，你的数据就可以在各个城市（主机）之间穿梭。

Socket的通信原理：

![Socket通信原理](https://upload-images.jianshu.io/upload_images/2570030-1ae38c53b852d838.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 主机A的应用程序要能和主机B的应用程序通信，必须通过Socket建立连接，而建立Socket连接必须需要底层TCP/IP协议来建立TCP连接。
* 建立TCP连接需要底层IP协议来寻址网络中的主机。网络层使用的IP协议可以帮助我们根据IP地址来找到目标主机。
* 一台主机上可能运行着多个应用程序，与指定的应用程序通信就要通过端口号来指定。
* 这样就可以通过一个Socket实例**唯一代表一个主机上的一个应用程序**的通信链路了。

#### 5. Socket编程基础（TCP编程为例子）

![Socket编程](https://upload-images.jianshu.io/upload_images/2570030-0724e056ac6142f5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Server端：

```java
public class TCPServer {
    public static void main(String[] args) throws Exception {
        /**
         * 基于TCP协议的Socket通信，实现用户登录，服务端
         */
        //1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
        ServerSocket serverSocket = new ServerSocket(10086);//1024-65535的某个端口
        //2、调用accept()方法开始监听，等待客户端的连接
        Socket socket = serverSocket.accept();
        //3、获取输入流，并读取客户端信息
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String info = null;
        while ((info = br.readLine()) != null) {
            System.out.println("我是服务器，客户端说：" + info);
        }
        socket.shutdownInput();//关闭输入流
        //4、获取输出流，响应客户端的请求
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write("欢迎您！");
        pw.flush();
        //5、关闭资源
        pw.close();
        os.close();
        br.close();
        isr.close();
        is.close();
        socket.close();
        serverSocket.close();
    }
}
```

Client端：

```java
public class TCPClient {
    public static void main(String[] args) throws Exception {
        //客户端
        //1、创建客户端Socket，指定服务器地址和端口
        Socket socket = new Socket("localhost", 10086);
        //2、获取输出流，向服务器端发送信息
        OutputStream os = socket.getOutputStream();//字节输出流
        PrintWriter pw = new PrintWriter(os);//将输出流包装成打印流
        pw.write("用户名：admin；密码：123");
        pw.flush();
        socket.shutdownOutput();
        //3、获取输入流，并读取服务器端的响应信息
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String info = null;
        while ((info = br.readLine()) != null) {
            System.out.println("我是客户端，服务器说：" + info);
        }
        //4、关闭资源
        br.close();
        is.close();
        pw.close();
    }
}
```

#### 6. 参考文章

有关Socket的多线程改进、UDP编程，可以参考：

[Java Socket编程----通信是这样炼成的](https://www.cnblogs.com/rocomp/p/4790340.html)

[面试记录第二十三节——（java网络编程、BIO/NIO/AI0）](https://blog.csdn.net/bobo89455100/article/details/78250104)


### Java中的BIO，NIO，AIO分别是什么，应用场景是什么？

* BIO：同步并阻塞，服务器实现模式是一个连接对应一个线程，即客户端有连接请求时，服务器就会开启一个线程进行处理，如果这个连接不做任何事情时，会造成不必要的线程开销，可以使用线程池进行改善。其应用场景适用于连接数目比较小且固定的架构，这种方式对服务器资源要求较高，对线程并发有局限性。
* NIO：同步非阻塞，服务器实现模式是一个请求对应一个线程，即客户端的连接请求都会注册在多路复用器上，当多路复用器轮询到有I/O请求时才启动一个线程进行处理。其应用场景适用于连接数目多且连接短的架构，对线程并发有局限性。IO是面向流的，NIO是面向缓冲区的。
* AIO：异步非阻塞，服务器实现模式是一个有效请求对应一个线程，即客户端的I/O请求完成之后，再通知服务器去启动一个线程进行处理。其应用场景适用于连接数目多且连接长的架构，充分体现出并发性。

#### 参考文章

[深入分析 Java I/O 的工作机制](https://www.ibm.com/developerworks/cn/java/j-lo-javaio/)

[Java NIO 系列教程](http://ifeve.com/java-nio-all/)

[Java NIO原理图文分析及代码实现](http://weixiaolu.iteye.com/blog/1479656)

### 多线程基础

#### 1. Java中实现多线程的两种方式

两种方式：

1. 实现Runnable接口，然后添加到Thread中，通过Thread的对象开启线程。（实际开发比较常用）
2. 继承Thread类，复写run方法，创建对象然后开启线程。

联系与区别：

* 相同点：不管是用Thread还是用Runnable，都必须创建Thread对象产生线程，然后调用Thread对象的start方法来开启线程。
* 不同点1：Thread它有一个缺点，java当中是单继承的模式，它不同于C++,所以说为了弥补这一缺陷，我们java当中通过实现Runnable接口来弥补次缺点，同时用接口这个方式要比继承Thread更灵活。
* 不同点2：继承Thread，如果想执行多个任务，就必须产生多个相应的线程。但是实现Runnable不同，只需要创建一个这个类的实例，然后用这个实例对象产生多个线程，你就实现了整个资源的共享。

#### 2. Thread的start方法和run方法的区别

* start方法：它是开启一个线程的方法，这个时候你不需要等待run方法体中执行完毕，你就可以继续执行其它代码。并且通过start方法后，线程变成了可运行状态，而不是执行状态，什么时候运行线程代码，这就需要操作系统自己决定。
* run方法：不会有多线程效果，就是一般的方法调用。run方法结束了，这个线程也就结束了。

#### 3. synchronized关键字

基本用法

线程间的同步就是使用synchronized来实现的。synchronized关键字的基本用法如下：

* 锁住某个对象
* 锁住某个类及其所有对象

```java
//修饰代码块（给对象加锁），修饰一个代码块，被修饰的代码块称为同步语句块，其作用的范围是大括号{}括起来的代码，作用的对象是调用这个代码块的对象。
synchronized (this/obj) {

}

//修饰一个方法，被修饰的方法称为同步方法，其作用的范围是整个方法，作用的对象是调用这个方法的对象。等价于synchronized (this)
public synchronized void test() {

}

//修饰一个静态的方法，其作用的范围是整个静态方法，作用的对象是这个类的所有对象。
public synchronized static void test(){
    
}

//修饰一个类，其作用的范围是synchronized后面括号括起来的部分，作用的对象是这个类的所有对象。
synchronized(ClassName.class) {
    
}
``` 

synchronized原理：

synchronized获取和释放都有一个监听器，如果两个线程都是用同一个监听器（即相同锁），这个监听器就可以强制在在同一时间只有一个线程处理这个代码块。

#### 4. volatile关键字

volatile只能在线程内存和主内存之间同步一个变量的值。与不使用volatile、使用synchronized的对比如下：

```java
//线程内存和主内存都有一份变量的值，线程不安全
int i1; int geti1() { return i1; }

//volatile类型的变量不允许线程从主内存中将变量的值拷贝到自己的存储空间。volatile类型的变量的值在所有线程同步。
//由于线程存取或更改自己的数据拷贝有更高的效率，所以volatile类型变量在性能上有所消耗。
volatile int i2; int geti2() { return i2; }

//synchronized除了代码块同步，synchronized还能使内存同步。
//synchronized则同步在线程内存和主内存之间的所有变量的值。
int i3; synchronized int geti3() { return i3; }
```

#### 5. volatile和synchronized区别
        
* volatile只能在线程内存和主内存之间同步一个变量值，而synchronized可以再线程内存和主内存之间同步所有的值，并通过锁管理所有的变量。但是synchronized更消耗内存。
* volatile只能使用在变量上、synchronized则可以使用在对象、类、方法上面等。

#### 6. synchronized和lock区别
        
* 用法上：synchronized是需要在同步中加入这个控制，lock需要指定起始位置和终止位置。
* 性能上：synchronized是脱离我们Java虚拟机执行的，lock是我们java自己写的代码，所以synchronized相比lock性能差，因为synchronized是一个重量级操作，有些耗性能。
* 采用机制方面：synchronized采用的是cpu的悲观锁机制，也就是线程获取的是独占的锁，意味着其他线程只能依靠阻塞等线程释放锁。而我们的lock是cpu的乐观锁机制，也就是每一个不加锁，而是假设没有冲突的情况下去完成某项操作。如果有冲突、失败，他就重试直到操作成功位置。

#### 7. sleep和wait区别

* 在等待时wait会释放锁；而sleep一直持有锁，不会改变线程持有锁的情况。
* Wait通常被用于线程间交互，sleep通常被用于暂停执行。

#### 8. wait和notify机制区别
        
wait是定义在我们object大类当中，需要在同步代码块中来调用，调用完之后他会释放锁，并进入锁对象的等待中，他需要其他线程调用notify这个方法释放锁之后，他才能重新去竞争锁。

#### 参考文章

[关于volatile和synchronized](https://blog.csdn.net/majorboy/article/details/475811)

[Java中Synchronized的用法](https://blog.csdn.net/luoweifu/article/details/46613015)

### 线程池

#### 1. 线程池的好处

1. 降低资源的消耗，因为可以重复利用我们已经创建好的线程，降低不断创建和销毁线程所带来的资源消耗。
2. 提高响应速度，当任务达到一定的数量时，任务不需要等到线程创建就立即执行（因为有创建好的可以循环利用）。
3. 提高线程的可管理性，毕竟线程还是比较稀缺的资源，尤其是手机当中，如果你无限制的创建线程，不仅仅会消耗系统资源，同时还会降低系统的稳定性，使用线程池可以进行统一的分配，可以合理的的利用线程池，也提高了线程池的管理性。

#### 2. 线程池的参数

```java
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler) {

}
```

传递参数讲解：

* corePoolSize：线程池的大小
* maximumPoolSize：最大线程池大小
* keepAliveTime：线程池中超过corePoolSize数目的空闲线程最大存活时间；可以allowCoreThreadTimeOut(true)使得核心线程有效时间
* unit：keepAliveTime时间单位
* workQueue：阻塞任务队列
* threadFactory：新建线程工厂
* RejectedExecutionHandler：当提交任务数超过maximumPoolSize+workQueue之和时，任务会交给RejectedExecutionHandler来处理(保护策略)

重点讲解：

其中比较容易让人误解的是：corePoolSize，maximumPoolSize，workQueue之间关系。

1. 当线程池小于corePoolSize时，新提交任务将创建一个新线程执行任务，即使此时线程池中存在空闲线程。 
2. 当线程池达到corePoolSize时，新提交任务将被放入workQueue中，等待线程池中任务调度执行 
3. 当workQueue已满，且maximumPoolSize>corePoolSize时，新提交任务会创建新线程执行任务 
4. 当提交任务数超过maximumPoolSize时，新提交任务由RejectedExecutionHandler处理 
5. 当线程池中超过corePoolSize线程，空闲时间达到keepAliveTime时，关闭空闲线程 
6. 当设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭
7. 最后调用.execute()提交任务，不过.execute()没有返回值，所以不能判断这个任务是否被线程池执行成功，这是隐患之处。

#### 3. 线程池的工作流程

1. 首先线程池判断，基本线程池是否已满如果线程池已满，进入下个流程。如果没有满，我们就会创建一个工作（子线程）线程来执行该任务。
2. 如果线程池工作队列满了，如果没有满，我们就会将提交的任务存储到该工作队列中，来进行相应的策略处理；如果工作队列满了，进入下一个流程。
3. 最后线程池判断整个线程池是否已满。如果整个线程池已经满了，就会交给我们的RejectedExecutionHandler处理，可以抛出异常也可以忽略这个问题。如果没有满就会创建一个新的工作线程。


### Android异常体系

#### 1. Android异常体系

![Android异常体系](https://upload-images.jianshu.io/upload_images/2570030-448912ef324750c3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 常见的Android崩溃有两类，一类是Java Exception异常，一类是Native Signal异常。我们将围绕这两类异常进行。对于很多基于Unity、Cocos平台的游戏，还会有C#、JavaScript、Lua等的异常，这里不做讨论。
* Throwable类是所有Java异常和错误的父类，有两个子类Error（错误）和Exception（异常）。
* Error是程序无法处理的错误，虚拟机一般会选择线程终止。这种错误无法恢复或不可能捕获，将导致应用程序中断，通常应用程序无法处理这些错误，因此应用程序不应该捕获Error对象，也无须在其throws子句中声明该方法抛出任何Error或其子类。
* Exception是程序本身可以处理的异常，这种异常分两大类运行时异常和非运行时异常。程序中应当尽可能去处理这些异常。
* 运行时异常都是RuntimeException类及其子类异常，这些异常是编译器不检查的异常，程序中可以选择捕获处理，也可以不处理。这些异常一般是由程序逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生。 
* 非运行时异常是RuntimeException以外的异常，类型上都属于Exception类及其子类。从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过。 

#### 2. 列举常见的异常

* 常见的Error：StackOverflowError、OutOfMemoryError、ThreadDeath、ClassFormatError、AbstractMethodError、AssertionError
* 常见的RuntimeException：NullPointerException、ClassCastException、IllegalArgumentException、ArithmeticException、IndexOutOfBoundsException、SecurityException、NumberFormatException
* 常见的非RuntimeException：IOException、FileNotFoundException、、一般用户自定义的异常

#### 3. 异常处理机制关键字的运用

异常处理机制的运用：

* try···catch语句
* finally语句：任何情况下都必须执行的代码，保持程序的健壮性
* throws子句：声明可能会出现的异常
* throw语句：抛出异常

throw与throws关键字的区别？

* throw关键字是用于方法体内部，用来抛出一个Throwable类型的异常，需要调用者进行捕获处理。
* throws关键字用于方法体外部的方法声明部分，用来声明方法可能会抛出某些异常，表示本方法无法处理本异常。

final、finally和finalize关键字的区别？

* final修饰符（关键字）
    
    * 被final修饰的类，就意味着不能再派生出新的子类，不能作为父类而被子类继承。因此一个类不能既被abstract声明，又被final声明。
    * 将变量或方法声明为final，可以保证他们在使用的过程中不被修改。被声明为final的变量必须在声明时给出变量的初始值，而在以后的引用中只能读取。被final声明的方法也同样只能使用，不能被覆写。
    
* finally是在异常处理时提供finally块来执行任何清除操作。不管有没有异常被抛出、捕获，finally块都会被执行，保持了程序的健壮性。
* finalize是Object类中的方法。Java技术允许使用finalize方法在垃圾收集器将对象从内存中清除出去之前做必要的清理工作。这个方法是由垃圾收集器在确定这个对象没有被引用时，被垃圾收集器清楚之前对这个对象调用的。

#### 4. 异常处理机制的原理：

* Java虚拟机用方法调用栈（method invocation stack）来跟踪每个线程中一系列的方法调用过程。该堆栈保存了每个调用方法的本地信息（比如方法的局部变量）。
* 每个线程都有一个独立的方法调用栈。对于Java应用程序的主线程，堆栈底部是程序的入口方法main()。
* 当一个新方法被调用时，Java虚拟机把描述该方法的栈结构置入栈顶，位于栈顶的方法为正在执行的方法。
* 当一个方法正常执行完毕，Java虚拟机会从调用栈中弹出该方法的栈结构，然后继续处理前一个方法。
* 如果在执行方法的过程中抛出异常，则Java虚拟机必须找到能捕获该异常的catch代码块。它首先查看当前方法是否存在这样的catch代码块，如果存在，那么就执行该catch代码块；否则，Java虚拟机会从调用栈中弹出该方法的栈结构，继续到前一个方法中查找合适的catch代码块。在回溯过程中，如果Java虚拟机在某个方法中找到了处理该异常的代码块，则该方法的栈结构将成为栈顶元素，程序流程将转到该方法的异常处理代码部分继续执行。
* 当Java虚拟机追溯到调用栈的底部的方法时，如果仍然没有找到处理该异常的代码块，按以下步骤处理：

    1. 调用异常对象的printStackTrace()方法，打印来自方法调用栈的异常信息。
    2. 如果该线程不是主线程，那么终止这个线程，其他线程继续正常运行。如果该线程是主线程（即方法调用栈的底部为main()方法），那么整个应用程序被终2。

#### 5. 特殊的异常处理流程

* finally语句块不被执行的特殊情况：

    * try语句之前就已经返回了
    * 在finally执行之前如果通过System.exit退出了程序，finally语句块不被执行：
    
        ```java
        public static void test1() {
            try {
                //System.exit(0);
                int i = 1/0;
            } catch (Exception e) {
                System.exit(0);
            }finally {
                System.out.println("finally");//不被打印
            }
        }
        ```
        
    * 当所有的非守护线程中止时，守护线程被kill掉，守护线程中未被执行的finally代码块是不会执行的。

* try中有return，finally会被执行：try中通过return语句返回，先执行return语句的表达式计算结果，然后执行finally语句块，最后才返回。
* finally不能通过变量赋值的方式改变返回结果：return语句已经将表达式计算完成并且将结果赋值给了一个不知名的临时变量，finally语句块中即使改变了return中相关表达式的值，但是没有通过return改变临时返回变量的值，但是对最终的返回结果没有任何影响。

    ```java
    public static int test2() {
        int a = 0;
        try {
            return a = 1;
        } catch (Exception e) {
        }finally {
            a = 2;
            System.out.println("finally");//return表达式计算之后，结果返回之前，这里被打印
        }
        return 0;
    }
    //程序最终返回1
    ```

* 建议不要在finally代码块中使用return语句，以为它会导致以下两种潜在的严重错误。

    * finally中的return会覆盖try或catch代码块的return语句，造成程序的不安全。
    
        ```java
        public static int test3() {
            int a = 0;
            try {
                return a = 1;
            } catch (Exception e) {
            }finally {
                a = 2;
                System.out.println("finally");
                return a;
            }
        }
        //程序返回2
        ```
    
    * 丢失异常：如果catch代码块中有throw语句抛出异常，由于先执行了finally代码块，又因为finally代码块中有return语句，所以方法退栈，catch代码块中的异常就没有被捕获处理。
    
        ```java
        public static void test4() {
            try {
                int a = 1/0;
            } catch (Exception e) {
                System.out.println("catch");
                int b = 1 / 0;
            }finally {
                System.out.println("finally");
                return;
            }
        }
        ```

#### 6. Android平台的崩溃捕获机制及实现

使用UncaughtExceptionHandler捕获Uncaught异常：

* 没有捕获住的异常，即Uncaught异常，都会导致应用程序崩溃。那么面对崩溃，我们是否可以做些什么呢？比如程序退出前，弹出个性化对话框，而不是默认的强制关闭对话框，或者弹出一个提示框安慰一下用户，甚至重启应用程序等。其实Java提供了一个接口给我们，可以完成这些，这就是UncaughtExceptionHandler。

    ```java
    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            //...
        }
    });
    ```

对Native代码的崩溃，可以通过调用sigaction()注册信号处理函数来完成捕获：

* 熟悉Linux开发的人都知道，so库一般通过gcc/g++编译，崩溃时会产生信号异常。Android底层是Linux系统，所以so库崩溃时也会产生信号异常。通过调用sigaction()注册信号处理函数可以捕获Android Native崩溃。

#### 参考文章

[java学习笔记《面向对象编程》——异常处理](https://blog.csdn.net/dnxyhwx/article/details/6975087)

[开发中的异常和错误总结](https://blog.csdn.net/zhouxingxing1992/article/details/70236540)

[Java中final、finally和finalize的区别](https://blog.csdn.net/cyl101816/article/details/67640843)

[finally代码块不被执行的情况总结](https://www.cnblogs.com/fudashi/p/6498205.html)

[Android平台的崩溃捕获机制及实现](https://blog.csdn.net/tangxiaoyin/article/details/80121547)

### 注解

#### 1. 注解相关的基本概念

什么是注解？

* 概念：注解就是Java提供了一种元程序中的元素关系任何信息和任何元数据(metadata)的途径和方法。注解是一个接口，程序可以通过反射来获取指定程序元素的Annotation对象，然后通过Annotation对象来获取注解里面的元数据。
* 基本作用：注解是JDK5.0及以后版本引入的。它可以用于创建文档，跟踪代码中的依赖性，甚至执行基本编译时检查等。
* 基本原则：Annotation不能影响程序代码的执行，无论增加、删除Annotation，代码都始终如一的执行。另外，尽管一些annotation通过java的反射api方法在运行时被访问，而java语言解释器在工作时忽略了这些annotation。

什么是metadata(元数据)？

* 元数据是描述数据的数据，以标签的形式存在于Java代码中。
* 通过元数据可以编写文档、代码分析、编译检查
* 元数据描述的信息是类型安全的，即元数据内部的字段都是有明确类型的。
* 元数据需要编译器之外的工具额外的处理来生成其它的程序部件。
* 元数据可以只存在于Java源代码级别，也可以存在于编译之后的Class文件内部。

#### 2. 注解的分类

根据注解使用方法和用途，我们可以将Annotation分为三类：

* JDK内置系统注解

    * @Override：用于修饰此方法覆盖了父类的方法
    * @Deprecated：用于修饰已经过时的方法
    * @SuppressWarnings:用于通知Java编译器禁止特定的编译警告
    
* 元注解

    * @Target：用于描述注解的使用范围。Annotation可被用于packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。
    * @Retention：表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）。取值如下：
    
        * SOURCE：在源文件中有效，仅出现在源代码中，而被编译器丢弃
        * CLASS：在class文件中有效，可能会被虚拟机忽略
        * RUNTIME：在运行时有效，class被装载时将被读取（请注意并不影响class的执行，因为Annotation与class在使用上是被分离的）
    
    * @Documented：用于描述其它类型的Annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。
    * @Inherited：元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的（注解可以传递到子类）。

* 自定义注解

#### 3. 注解处理器

注解如何被处理？

* 通过注解处理器来获取和处理注解（核心原理是反射机制）
* 先通过反射API获取class的元素（域、方法等），然后通过反射API中与注解相关的4个核心API来获取、处理注解：

    * boolean is AnnotationPresent(Class<?extends Annotation> annotationClass):判断该程序元素上是否包含指定类型的注解，存在则返回true，否则返回false.
    * <T extends Annotation> T getAnnotation(Class<T> annotationClass): 返回改程序元素上存在的、指定类型的注解，如果该类型注解不存在，则返回null。
    * Annotation[] getAnnotations():返回该程序元素上存在的所有注解。
    * Annotation[] getDeclaredAnnotations()：返回直接存在于此元素上的所有注解。与此接口中的其他方法不同，该方法将忽略继承的注解（@Inherited）。

#### 4. Android Support Annotation

* 空类型安全注解：@Nullable、@NonNull
* 资源类型注解：主要用于标记某个整型参数是某某资源的ID
* 类型定义注解：@IntDef，主要用于取代枚举，保证了调用函数的时候必须传入指定参数，若是非法在编译时就会报异常。=
* 线程注解：@MainThread（@UiThread）、@WorkerThread、@BinderThread
* 值范围注解：当函数参数的取值在一定范围时，可以使用注解来防止调用者传入错误的参数
* 权限注解：为了在编译时及时发现权限的缺失，可以使用@RequiresPermission注解。
* 重写函数注解：如果API允许重写某个函数的时候，可以加注解@CallSuper来提示开发者若是重写不调用super就会报错。
* 混淆注解：@keep是用来标记在Proguard混淆过程中不需要混淆的类或者方法。

#### 5. 参考文章

[深入理解Java：注解（Annotation）基本概念](http://www.cnblogs.com/peida/archive/2013/04/23/3036035.html)

[深入理解Java：注解（Annotation）自定义注解入门](http://www.cnblogs.com/peida/archive/2013/04/24/3036689.html)

[深入理解Java：注解（Annotation）--注解处理器](http://www.cnblogs.com/peida/archive/2013/04/26/3038503.html)

[Android进阶系列之Support Annotation Library使用详解](https://blog.csdn.net/sw5131899/article/details/53842362)

### 类加载器

#### 1. 类加载器是什么？

Java程序并不是一个原生的可执行文件，而是由许多独立的class文件组成，每一个class文件对应一个Java类。这些类文件并非立即全部装入内存的，而是根据程序需要**动态**装入内存。

**类加载器是用来动态加载class文件到内存当中的。**

#### 2. Java原生类加载器的分类

![Java类加载器](https://upload-images.jianshu.io/upload_images/2570030-d34d3823bdd2b3c2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

从上图我们就可以看出类加载器之间的父子关系(**注意不是类的继承关系**)和管辖范围。

* BootStrap ClassLoader是最顶层的类加载器，站在虚拟机的角度来说属于启动类加载器，它是由C++编写而成,并且已经内嵌到JVM中了，主要用来读取Java的核心类库JRE/lib/rt.jar
* Extension ClassLoader是是用来读取Java的扩展类库，读取JRE/lib/ext/*.jar
* App ClassLoader是用来读取CLASSPATH指定的所有jar包或目录的类文件
* Custom ClassLoader是用户自定义编写的，可以自己增加一些例如字节码加密解密等功能，它用来读取指定类文件

#### 3. Android类加载器的分类

![Android类加载器](https://upload-images.jianshu.io/upload_images/2570030-355155ea4054628b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Android中的ClassLoader的整体架构**继承关系**如上图所示。

* BootClassLoader：与Java中的Bootstrap ClassLoader类似，主要加载Android Framework中的字节码文件。
* BaseDexClassLoader是PathClassLoader以及DexClassLoader的父类，PathClassLoader以及DexClassLoader的逻辑都在这个父类中实现。
* PathClassLoader：与Java中的App ClassLoader类似，主要加载已经安装到系统中的APK中的字节码文件。
* DexClassLoader：与Java中的Customer ClassLoader类似，主要加载自定义路径下的APK或者JAR中的字节码文件（Android中主要是指dex文件，即classes.dex）。通过DexClassLoader可以实现插件化。

Android与Java原生Java类加载器最大的不同是什么？

* Java原生类加载器是加载class文件
* Android类加载器是加载dex文件

#### 4. 双亲委托机制

ClassLoader的主要特性是双亲委托机制：

1. 即加载一个类的时候，先判断已经存在的类是否被加载过，如果没有，先去委托父亲、祖宗类加载器去加载。
2. 如果连父亲、祖宗所有类加载器都没有加载到该类的话，那么最终由自己加载。
3. 最终如果这个类都没有合适的CLassLoader加载，那么就会抛出ClassNotFoundException异常。

双亲委托机制的优点：

* 实现类加载的共享功能，提升类加载的效率。
* 实现类加载的隔离功能，提升系统的安全性。比如，通过这种方式，系统的String类只能由系统的ClassLoader加载。

#### 5. 类加载过程

![类加载过程](https://upload-images.jianshu.io/upload_images/2570030-4a5ecc15b5c2e506.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

1. 加载：虚拟机外部的二进制字节流就按照虚拟机所需的格式存储在方法区之中，而且在Java堆中也创建一个java.lang.Class类的对象，这样便可以通过该对象访问方法区中的这些数据。
2. 验证：为了确保Class文件中的字节流包含的信息符合当前虚拟机的要求，而且不会危害虚拟机自身的安全。不同的虚拟机对类验证的实现可能会有所不同，但大致都会完成以下四个阶段的验证：文件格式的验证、元数据的验证、字节码验证和符号引用验证。
3. 准备：为类变量分配内存并设置类变量初始值的阶段，这些内存都将在方法区中分配。
4. 解析：虚拟机将常量池中的符号引用替换为直接引用的过程。
5. 初始化：执行初始化程序，把静态变量初始化为指定的值，执行static块、构造器等等。

#### 6. 参考文章

[类加载](https://www.jianshu.com/p/37cad7a901b1)

[JVM 类加载机制详解](http://www.importnew.com/25295.html)

[【深入Java虚拟机】之四：类加载机制](https://blog.csdn.net/ns_code/article/details/17881581)

### 虚拟机的内存管理

#### 1. 虚拟机内存划分

下图为虚拟机的整体结构：

![虚拟机](https://upload-images.jianshu.io/upload_images/2570030-2b393f6c52a19217.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 静态存储区（方法区）：主要存放**静态数据、全局静态数据和常量**。这块内存在程序编译时就已经分配好，并且在**程序整个运行期间都存在**。
* 栈区：当方法被执行时，**方法体内的局部变量，其中包括基础数据类型、对象或者数组的引用**都在栈上创建，并在方法执行结束时这些局部变量所持有的内存将会自动被释放。因为栈内存分配运算内置于处理器的指令集中，**效率很高，但是分配的内存容量有限**。
* 堆区：又称**动态内存分配**，通常就是指在**程序运行时直接new出来的内存**，也就是对象或者数组的实例。这部分内存在不使用时将会**由Java垃圾回收器来负责回收**。
* 本地方法栈：专门为native方法服务的，例如C、C++方法。

#### 2. 堆区和栈区的区别

* 栈：存放局部变量的基本数据类型和引用。生命周期随方法而结束。内存空间有限，存取速度快。
* 堆：存放成员变量（包括基本数据类型，引用和引用的对象实体）、局部变量引用指向的对象。运行时动态分配的内存，由Java垃圾回收器来自动管理，存取速度比栈慢。
* 堆是不连续的内存区域，堆空间比较灵活也特别大；栈是一块连续的内存区域，大小是有操作系统决定的。
* 堆的管理很麻烦，频繁地new/remove会造成大量的内存碎片，这样就会慢慢导致效率低下；栈是一种先进后出的数据结构，进出完全不会产生碎片，运行效率高且稳定。

#### 3. 一些基本概念

* 引用变量：局部变量中的引用变量在栈中分配，引用变量的取值等于数组或者对象在堆内存中的首地址，可以通过引用变量来访问堆中的对象或者数组。（类的成员变量的引用变量存放在堆）
* 栈溢出（StackOverflow）：当方法栈的深度大于JVM最大深度的时候，就会栈溢出。例如写一个没有退出的递归就会导致栈溢出（StackOverflow）。
* 内存泄漏：长生命周期的对象持有短生命周期的对象的引用很有可能发生内存泄漏。
* 内存溢出（OOM）：当新生代、老生代都满了的话，就会导致内存溢出（OOM）。
* 新、老生代的动态调整：服务端开发需要掌握的一门技术，比如做即时通信服务端，Message临时对象比较多，那么适当增加新生代，便于内存分配，加快对象的创建。做大型服务类程序，不需要频繁创建，就可以扩大老生代，达到对象常驻效果，保证服务稳定性。

#### 4. 面试常见例题

```java
public class Sample {

    //因为类被new出来之后是存放在堆中的，所有成员变量全部存储于堆中（包括基本数据类型，引用和引用的对象实体）
    //因为它们属于类，类对象终究是要被new出来使用的
    int i1 = 0;
    Sample s1 = new Sample();

    public void method() {
        //局部变量和引用变量都是存在于栈中，但引用变量指向的对象是存在于堆中
        //因为它们属于方法中的变量，生命周期随方法而结束
        int i2 = 1;
        Sample s2 = new Sample();
    }

    public static void main(String[] args) {
        //局部变量和引用变量都是存在于栈中，但引用变量指向的对象是存在于堆中
        Sample s3 = new Sample();
    }

}
```

#### 5. 参考文章

[内存泄漏与内存溢出总结](https://blog.csdn.net/u012792686/article/details/69666498)

[JVM内存管理机制和垃圾回收机制](https://blog.csdn.net/u011225629/article/details/49000311)

### 垃圾收集与回收算法

#### 1. 垃圾收集算法

* 引用计数法：被引用1次，计数器加1，没有被引用的时候，则回收。但是引用计数法无法解决对象之前相互引用的问题，因此已经废弃。
* 可达性算法（根搜索算法）：通过GC ROOT对象开始搜索，不可达的对象则回收。这时候可以提到引用的类型，主要用得最多就是强引用和弱引用。当存在强引用的时候，内存不足宁愿抛出OOM也不会回收。但是是弱引用的话，就有可能会被回收，这样就防止了内存泄漏。

#### 2. 垃圾回收算法

分代的垃圾回收策略：

分代的垃圾回收策略是基于这样一个事实：不同的对象的生命周期是不一样的。因此，不同生命周期的对象可以采取不同的回收算法，以便提高回收效率。

* 新生代
* 老生代
* 持久代

常见的垃圾回收算法有：

* 复制算法：搜索、扫描没有引用的对象。开辟新的内存空间，将存活的对象复制到新的内存，旧的内存直接清除。由于需要多次交换内存空间，因此在对象数量比较少的时候效率比较高。适用于新生代。
* 标记算法，旧生代与新生代不同，对象存活的时间比较长，比较稳定，因此采用标记（Mark）算法来进行回收。

    * 标记-清除算法：搜索、发现没有引用的对象，直接回收，但是会导致内存碎片过多。
    * 标记-整理算法：在标记-清除算法的基础上，清除掉不存活的对象之后，把后面的存活对象搬移过来。使得内存连续，解决了内存碎片的问题。

#### 3. 触发GC的条件是什么？

* 当应用程序空闲时（即没有应用线程在运行时），GC低优先级的守护线程会被调用。当应用忙时，GC线程就不会被调用，但以下条件两个除外。
* 手动调用System.gc()方法，但是并不会导致GC马上执行，反而会增加了虚拟机的负担，因此不推荐直接使用。
* Java堆内存不足时（当年轻代或者老年代满了），Java虚拟机无法为新的对象分配内存空间，虚拟机会再多行尝试进行GC，如果还是不能满足新对象的分配就会触发OOM。

#### 4. 如何降低GC压力？

频繁的触发GC操作导致线程暂停、内存抖动，会使得安卓系统在16ms内无法完成绘制，造成界面卡顿等现象。通过下面的方法可以降低GC压力：

* 不要显示的调用System.gc()
* 尽量减少临时对象的使用，可以使用享元设计模式
* 对象不用的时候最好显式置空
* 尽量使用StringBuilder、StringBuffer，不使用String累加字符串（String的特性有关）
* 能使用基本数据类型就不要使用装箱类
* 尽量减少静态对象变量的使用
* GC在回收对象之前调用finalize方法，不建议在该方法中做繁重的非内存释放工作

#### 参考文章

[JVM内存管理机制和垃圾回收机制](https://blog.csdn.net/u011225629/article/details/49000311)

[java垃圾回收 - 为什么要进行垃圾回收](https://www.cnblogs.com/zedosu/p/6514457.html)

[java finalize方法总结、GC执行finalize的过程](https://blog.csdn.net/pi9nc/article/details/12374049)

[深入理解 Java 垃圾回收机制](https://www.cnblogs.com/andy-zcx/p/5522836.html)

### Android虚拟机
 
#### 1. Dalvik VM（DVM）与JVM有什么不同？

为了更加适合移动端，Android基于JVM创造了DVM：

* JVM执行的是class文件，DVM执行的是dex文件。dex格式是专为Dalvik应用设计的一种压缩格式，适合于内存和处理器速度有限的系统。

    * .dex文件相对于.class文件来说去掉了冗余信息，较少了硬件的I/O操作次数，提高了类的查找速度。（另外，odex文件是dex文件的进一步优化）

* 可以同时存在多个DVM，每个进程就是一个独立的虚拟机。独立的进程可以防止在虚拟机崩溃的时候所有程序都被关闭。
* JVM是基于栈的，DVM是基于寄存器的，基于寄存器的Dalvik实现虽然牺牲了一些平台无关性，但是寻址速度更加快，代码执行效率更高。
* 类加载系统与JVM区别比较大。
* 有一个特殊的虚拟机进程Zygote，他是虚拟机实例的孵化器。它在系统启动的时候就会启动，它会完成虚拟机的初始化，库的加载，预制类库和初始化的操作。如果系统需要一个新的虚拟机实例，它会迅速复制自身，以最快的数据提供给系统。对于一些只读的系统库，所有虚拟机实例都和Zygote 共享一块内存区域。

#### 2. ART与DVM有什么不同？

ART虚拟机是DVM的改进版本：

* DVM运行的时候，采用JIT来将字节码转换成机器码，效率比较低，但让应用安装比较快，而且更容易在不同硬件和架构上运行。
* ART采用了AOT预编译技术，APP安装的时候就完成了字节码预编译为机器码，移除解释代码这一过程后，执行速度更加快，效率高，改善电池续航。
* 但是ART会占用更多的安装时间以及存储空间，这是典型的以空间换区时间的策略。

#### 3. 参考文章

[虚拟机](https://www.jianshu.com/p/e00971e07e14)

[Android开发——JVM、Dalvik以及ART的区别](https://blog.csdn.net/seu_calvin/article/details/52354964)

### 反射

#### 1. 反射的理解

* 动态获取信息：在运行状态中，对于任意一个类，都可以知道这个类的所有方法和属性，包括构造方法。
* 动态访问方法和属性：在运行状态中，对于任意一个对象，都可以访问它的任意一个方法和属性。

#### 2. Android中反射机制的运用

* 四大组件的构造
* XML文件中控件的构造
* 通过反射调用Framework中的隐藏API
* 插件化开发中通过反射调用DexClassLoader加载的APK中的代码
* 反射获取类中的运行时注解
* SDK开发中，SDK的jar文件通过反射调用主项目中的资源和id
* ORM数据库框架通过反射来实现
* ……

#### 3. 反射的优缺点

* 优点：可以动态的创建对象和实现一些动态功能，最大限度发挥了java的灵活性。
* 缺点：Android开发中大量使用反射对性能有影响。使用反射基本上一种解释操作，对反射的性能损失最大的出现在getType()和getMethod()等操作上面，因为是通过Native方法对字节码的相关字符串遍历程序集寻找的，没有直接调用的快。

#### 4. 反射的性能问题

提高反射的性能：

* 善用反射API：尽量不要getMethods()后再遍历筛选，而直接用getMethod(methodName)来根据方法名获取方法。
* 缓存结果：如果需要大量调用反射，请考虑缓存字节码、或者缓存一些通过反射获取的常量结果。
* 提前反射：在APP启动阶段使用反射，缓存相关结果到内存当中，避免运行的时候再调用反射API。
* 使用开源库：使用一些成熟的高性能反射库，例如ReflectASM（使用字节码生成的方式实现了更为高效的反射机制）、jOOR（封装了Java的反射API，并且实现了缓存）。

关于反射的性能问题的总结：

* 反射大概比直接调用慢50~100倍，但是需要你在执行100万遍的时候才会有所感觉。
* 如果你只是偶尔调用一下反射，请忘记反射带来的性能影响。如果你需要大量调用反射，请考虑缓存。
* 你的编程的思想和能力才是限制你程序性能的最主要的因素。

#### 5. 参考文章

[Android 反射调用资源和id]([https://blog.csdn.net/u013045971/article/details/42462985])

[java面试题--java反射机制](https://blog.csdn.net/snn1410/article/details/44978457)

[java面试题：如何提高反射效率？](https://segmentfault.com/q/1010000003004720)

[反射为什么会造成性能丢失](https://q.cnblogs.com/q/86360/)

[反射是否真的会让你的程序性能降低?](https://www.cnblogs.com/marvin/archive/2014/12/05/ShallWeUseReflect.html)

#### Java四大引用

#### 1. Java四大引用分别是什么？

* 强引用（StrongReference）

    * 生命周期：如果一个对象具有强引用，即使是内存空间不足时，垃圾回收器绝不会回收它来解决内存不足问题，虚拟机宁愿抛出OOM错误，程序异常终止。显式地设置为null，或超出对象的生命周期范围，则gc认为该对象不存在引用，这时就可以回收这个对象。
    * 使用场景：对象的一般保存，通常都是使用强引用。

* 软引用（SoftReference）

    * 生命周期：一个对象只具有软引用，则内存空间足够，垃圾回收器就不会回收它；如果内存空间不足了，就会回收这些对象的内存。（只要垃圾回收器没有回收它，该对象就可以被程序使用。）
    * 使用场景：可以结合ReferenceQueue使用；适合做缓存，比如图片缓存，解决OOM问题。（但是Android2.3之后虚拟机更加倾向于回收软引用的对象，因此更加推荐使用LRUCache做缓存）

* 弱引用（WeakReference）

    * 生命周期：不管当前内存空间足够与否，垃圾回收GC运行的适合会被回收。
    * 使用场景：可以结合ReferenceQueue使用；在使用Handler、WebView、AsyncTask的适合，需要持有Context或者Activity的适合。为了解决内存泄漏问题，需要将类设为静态类，但是由于静态内部类无法持有外部类的引用，因此推荐弱引用的方法来持有。

* 虚引用（PhantomReference）

    * 生命周期：有效期完全随机于GC的回收，在任何一个不确定的时间内，都可能会被回收
    * 使用场景：可以结合ReferenceQueue使用；主要用于跟踪对象被垃圾回收期回收的过程，比如LeakCanary；内存的精准控制。

注意要点：

* 强、软、弱、虚引用的回收级别一个比一个弱。
* 软、弱、虚引用都可以结合ReferenceQueue使用，如果软引用所引用的对象被垃圾回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中。
* 软、弱引用相对来说用得比较多，虚引用随时都会被回收。相对用得比较少。
* 开发时，为了防止内存溢出，处理一些比较占用内存大并且生命周期长的对象的时候，可以尽量使用软引用和弱引用。
* 在Android2.3以后，软引用比LRU算法更加任性，回收量是比较大的，无法控制回收哪些对象。因此推荐使用LRU算法，例如LruCache。

#### 2. 参考文章

[Java/Android中的强引用、软引用、弱引用、虚引用](https://www.jianshu.com/p/017009abf0cf)

[Android 性能优化之旅1--基本概念](https://www.jianshu.com/p/921dc3d54918)

[Java 7之基础 - 强引用、弱引用、软引用、虚引用](https://blog.csdn.net/mazhimazh/article/details/19752475)