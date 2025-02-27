package com.java3y.austin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
@SpringBootTest

public class SocketTest {
    @Test
    public void tesServer() throws IOException {
        //创建接收端的Socket对象
        ServerSocket ss = new ServerSocket(8888);

        //监听客户端连接，返回一个对应的Socket对象
        //侦听并接受到此套接字的连接，此方法会阻塞
        Socket s = ss.accept();

        //获取输入流，读取数据
        InputStream is = s.getInputStream();

        byte[] bys = new byte[1024];
        int len = is.read(bys);
        String str = new String (bys,0,len);

        String ip = s.getInetAddress().getHostAddress();
        System.out.println(ip + "    ---" +str);

        //释放资源
        s.close();
    }
}
