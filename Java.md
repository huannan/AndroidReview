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


### IO

#### 1. Java中的IO

#### 2. BIO（阻塞型IO）

#### 3. NIO（非阻塞型IO）

#### 参考文章

[深入分析 Java I/O 的工作机制](https://www.ibm.com/developerworks/cn/java/j-lo-javaio/)

[Java NIO 系列教程](http://ifeve.com/java-nio-all/)

[Java NIO原理图文分析及代码实现](http://weixiaolu.iteye.com/blog/1479656)

### 多线程

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