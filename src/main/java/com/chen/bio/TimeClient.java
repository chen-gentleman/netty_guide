package com.chen.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author @Chenxc
 * @Date 2022/5/9 15:39
 */
public class TimeClient {

    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            socket = new Socket("127.0.0.1",8888);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            System.out.println("send order 2 time server");
            out.println("QUERY TIME ORDER");
            System.out.println("now is :" + in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null){
                    out.close();
                }
                if(in != null){
                    in.close();
                }
                if(null != socket){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
