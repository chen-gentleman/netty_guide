package com.chen.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @Author @Chenxc
 * @Date 2022/5/9 14:42
 */
public class TimeServerHandel implements Runnable {
    private Socket socket;
    public TimeServerHandel(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            String currentTime;
            String body = in.readLine();
            if(body == null){
                return;
            }
            System.out.println("this time server receive order :" + body);
            currentTime = "QUERY TIME ORDER".equals(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
            out.println(currentTime);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(null != in){
                    in.close();
                }
                if(null != out){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
