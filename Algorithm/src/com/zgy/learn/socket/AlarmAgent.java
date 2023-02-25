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
 * �������Ӹ澯�������������͸澯��Ϣ���澯������
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

    //���ڼ�¼AlarmAgent�Ƿ������ϸ澯������
    //ģʽ��ɫ:GuardedSuspension.Predicate
    public final Predicate agentConnected = new Predicate(){
        @Override
        public boolean evaluate(){
            return socket == null ? false : !socket.isClosed();
        }
    };
    //ģʽ��ɫ:GuardedSuspension.Blocker
    public final ConditionVarBlocker blocker = new ConditionVarBlocker();
    //������ʱ��
    public final Timer heartbeatTimer = new Timer(true);
    /**
     * ���͸澯��Ϣ
     * @param alarm �澯��Ϣ
     * @throws Exception
     */
    public void sendAlarm(final String alarm)throws Exception{
        //������Ҫ�ȴ���ֱ��AlarmAgent �����ϸ澯������(���������жϺ��������Ϸ�����)
        // ģʽ��ɫ:GuardedSuspension.GuardedAction
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected){
            public Void call()throws Exception{
                println(alarm);
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }
    //����Ŀ�궯��
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
        //�澯�����߳�
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
    //������澯������������������
    private class ConnectingTask implements Runnable{
        @Override
        public void run() {
            //ʡ����������
            onConnected();
        }
    }
    /**
     * ������ʱ����:��ʱ�����澯�������������Ƿ����������������쳣���Զ���������
     */
    private class HeartbeatTask extends TimerTask{
        @Override
        public void run() {
            //ʡ����������
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
