package com.allen.learn.channel;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestFileChannel {


    public static void main(String[] args) throws IOException {
        String fileName = "E:\\DOWNLOAD\\CHROME\\f.txt";
        StringBuilder sb = new StringBuilder();
        RandomAccessFile accessFile = new RandomAccessFile(fileName, "rw");
        FileChannel inChannel = accessFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1){
            System.out.println("Read "+bytesRead);
            buf.flip(); //从写模式切换到读模式
            while (buf.hasRemaining()){
                sb.append((char)buf.get());
                //System.out.println((char)buf.get());
            }
            buf.compact();
            bytesRead = inChannel.read(buf);
        }
        accessFile.close();
        System.out.println(sb.toString());
    }
}
