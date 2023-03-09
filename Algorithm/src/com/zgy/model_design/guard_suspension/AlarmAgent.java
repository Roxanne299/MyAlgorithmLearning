package com.zgy.model_design.guard_suspension;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * 负责连接告警服务器，并发送告警信息至告警服务器
 */
public class AlarmAgent {
    //用于记录AlarmAgent是否连接上告警服务器
    private volatile boolean connectedToServer = false;
    //模式角色:GuardedSuspension.Predicate
    private final Predicate agentConnected = new Predicate(){
        @Override
        public boolean evaluate(){
            return connectedToServer;
        }
    };
    //模式角色:GuardedSuspension.Blocker
    private final  ConditionVarBlocker blocker = new ConditionVarBlocker();
    //心跳定时器
    private final Timer heartbeatTimer = new Timer(true);
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
                doSendAlarm(alarm);
                return null;
            }
        };

        blocker.callWithGuard(guardedAction);
    }
    //通过网络连接将告警信息发送给告警服务器
    private void doSendAlarm(String alarm){
        //省略其他代码
        System.out.println("sending alarm " + alarm);
        //模拟发送告警器至服务器的耗时
        try{
            Thread.sleep(50);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void init(){
        //告警连接线程
        Thread connectingThread = new Thread(new ConnectingTask());
        connectingThread.start();
        heartbeatTimer.schedule(new HeartbeatTask(),10000,2000);
    }
    protected void onConnected(){
        try{
            blocker.signalAfter(new Callable<Boolean>(){
                @Override
                public Boolean call(){
                    connectedToServer = true;
                    System.out.println("connected to server");

                    return Boolean.TRUE;
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    protected void onDisconnected(){
        try{
            blocker.disconnection(new Callable<Boolean>(){
                @Override
                public Boolean call(){
                    connectedToServer = false;
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

            //模拟连接耗时
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
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
                        return connectedToServer;
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
