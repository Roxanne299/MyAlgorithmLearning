package com.zgy.learn.socket;

import com.zgy.model_design.guard_suspension.ConditionVarBlocker;
import com.zgy.model_design.guard_suspension.GuardedAction;
import com.zgy.model_design.guard_suspension.Predicate;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * 负责连接告警服务器，并发送告警信息至告警服务器
 */
public class AlarmAgent {

    public volatile Socket socket;
    private InetAddress ip;
    private int port;
    public AlarmAgent(InetAddress ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    /*
    This function blocks.
    */
    public String readLine() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            return reader.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    /*
     * Ready for use.
     */
    public void close() {
        try {
            if (socket != null && !socket.isClosed())
                socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //

    //用于记录AlarmAgent是否连接上告警服务器
    //模式角色:GuardedSuspension.Predicate
    public final Predicate agentConnected = new Predicate(){
        @Override
        public boolean evaluate(){
            return socket == null ? false : !socket.isClosed();
        }
    };
    //模式角色:GuardedSuspension.Blocker
    public final ConditionVarBlocker blocker = new ConditionVarBlocker();
    //心跳定时器
    public final Timer heartbeatTimer = new Timer(true);
    /**
     * 发送告警信息
     * @param alarm 告警信息
     * @throws Exception
     */
    public void sendAlarm(final String alarm)throws Exception{
        //可能需要等待，直到AlarmAgent 连接上告警服务器(或者连接中断后重新连上服务器)
        // 模式角色:GuardedSuspension.GuardedAction
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected){
            public Void call()throws Exception{
                println(alarm);
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }
    //发送目标动作
    public void println(String message) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream()), true);
            writer.println(MessageFlag.pureMessage + message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void init(){
        //告警连接线程
        Thread connectingThread = new Thread(new ConnectingTask());
        connectingThread.start();
        heartbeatTimer.schedule(new HeartbeatTask(),10000,6000);
    }

    protected void onConnected(){
        try{
            blocker.signalAfter(new Callable<Boolean>(){
                @Override
                public Boolean call() throws IOException {
                    socket = new Socket(ip, port);
                    System.out.println("connected to server\n");
                    return socket.isConnected();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onDisconnected(){
        try{
            blocker.disconnection(new Callable<Boolean>(){
                @Override
                public Boolean call() throws IOException {
                    socket.close();
                    System.out.println("The server is disconnected");
                    return Boolean.TRUE;
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //负责与告警服务器建立网络连接
    private class ConnectingTask implements Runnable{
        @Override
        public void run() {
            //省略其他代码
            onConnected();
        }
    }
    /**
     * 心跳定时任务:定时检查与告警服务器的连接是否正常，发现连接异常后自动重新连接
     */
    private class HeartbeatTask extends TimerTask{
        @Override
        public void run() {
            //省略其他代码
            if (!testConnection()) {
                reconnect();
            }
        }
        private boolean testConnection(){
            boolean isConnected = true;
            try{
                isConnected = blocker.testConnection(new Callable<Boolean>(){
                    @Override
                    public Boolean call(){
                        return socket == null ? false : !socket.isClosed();
                    }
                });
            }catch(Exception e){e.printStackTrace();}
            return isConnected;
        }
        private void reconnect(){
            ConnectingTask connectingThread = new ConnectingTask();
            System.out.println("try to reconnect.....");
            Thread thread = new Thread(connectingThread);
            thread.start();
        }
    }
}
