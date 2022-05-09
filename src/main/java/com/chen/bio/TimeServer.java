package com.chen.bio;

import javafx.scene.Parent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author @Chenxc
 * @Date 2022/5/9 14:36
 */
public class TimeServer {
    private final static int PORT = 8888;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            TimeServerHandelExecutePool pool = new TimeServerHandelExecutePool(50,100);
            while (true){
                Socket accept = serverSocket.accept();
                //new Thread(new TimeServerHandel(accept)).start();
                pool.execute(new TimeServerHandel(accept));
            }
        } catch (IOException e) {

        }finally {
            if(serverSocket != null){
                System.out.println("close server");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
