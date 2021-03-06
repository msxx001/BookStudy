package com.sxt.udp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author: Li Tian
 * @contact: litian_cup@163.com
 * @software: IntelliJ IDEA
 * @file: UDPServer.java
 * @time: 2019/11/14 14:14
 * @desc: 接收端
 */

public class UDPObjServer {
    public static void main(String[] args) throws Exception{
        System.out.println("接收方启动中...");
        //  1. 使用DatagramSocket指定端口，创建接收端
        DatagramSocket server = new DatagramSocket(9999);
        //  2. 准备容器，封装成DatagramPacket包裹
        byte[] container = new byte[1024*60];
        DatagramPacket packet = new DatagramPacket(container, 0, container.length);
        //  3. 阻塞式接受包裹receeive(DatagramPacket p)
        //  阻塞式
        server.receive(packet);
        //  4. 分析数据，将字节数组还原为对应的类型即可
        byte[] datas = packet.getData();
        int len = packet.getLength();
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
        // 顺序与写出一致
        String msg = dis.readUTF();
        boolean flag = dis.readBoolean();
        System.out.println(msg + "-->" + flag);
        //  5. 释放资源
        server.close();
    }
}
