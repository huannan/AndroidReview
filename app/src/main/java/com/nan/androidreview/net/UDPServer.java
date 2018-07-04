package com.nan.androidreview.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        //服务器端，实现基于UDP的用户登录
        //1、创建服务器端DatagramSocket，指定端口
        DatagramSocket socket = new DatagramSocket(10010);
        //2、创建数据报，用于接受客户端发送的数据
        byte[] data = new byte[1024];//
        DatagramPacket packet = new DatagramPacket(data, data.length);
        //3、接受客户端发送的数据
        socket.receive(packet);//此方法在接受数据报之前会一致阻塞
        //4、读取数据
        String info = new String(data, 0, data.length);
        System.out.println("我是服务器，客户端告诉我" + info);

        //=========================================================
        //向客户端响应数据
        //1、定义客户端的地址、端口号、数据
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        byte[] data2 = "欢迎您！".getBytes();
        //2、创建数据报，包含响应的数据信息
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
        //3、响应客户端
        socket.send(packet2);
        //4、关闭资源
        socket.close();
    }
}
