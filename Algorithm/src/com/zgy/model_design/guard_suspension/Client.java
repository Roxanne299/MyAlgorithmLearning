package com.zgy.model_design.guard_suspension;

public class Client {
    public static void main(String[] args) throws Exception {

                AlarmAgent alarmAgent = new AlarmAgent();
                alarmAgent.init();
                alarmAgent.sendAlarm("alarm:test1");
                alarmAgent.onDisconnected();//模拟意外断开连接
                alarmAgent.sendAlarm("alarm:test2");

    }
}
