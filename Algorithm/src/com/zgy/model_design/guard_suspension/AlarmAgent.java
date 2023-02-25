package com.zgy.model_design.guard_suspension;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * �������Ӹ澯�������������͸澯��Ϣ���澯������
 */
public class AlarmAgent {
    //���ڼ�¼AlarmAgent�Ƿ������ϸ澯������
    private volatile boolean connectedToServer = false;
    //ģʽ��ɫ:GuardedSuspension.Predicate
    private final Predicate agentConnected = new Predicate(){
        @Override
        public boolean evaluate(){
            return connectedToServer;
        }
    };
    //ģʽ��ɫ:GuardedSuspension.Blocker
    private final  ConditionVarBlocker blocker = new ConditionVarBlocker();
    //������ʱ��
    private final Timer heartbeatTimer = new Timer(true);
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
                doSendAlarm(alarm);
                return null;
            }
        };

        blocker.callWithGuard(guardedAction);
    }
    //ͨ���������ӽ��澯��Ϣ���͸��澯������
    private void doSendAlarm(String alarm){
        //ʡ����������
        System.out.println("sending alarm " + alarm);
        //ģ�ⷢ�͸澯�����������ĺ�ʱ
        try{
            Thread.sleep(50);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void init(){
        //�澯�����߳�
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
    //������澯������������������
    private class ConnectingTask implements Runnable{
        @Override
        public void run() {
            //ʡ����������

            //ģ�����Ӻ�ʱ
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
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
