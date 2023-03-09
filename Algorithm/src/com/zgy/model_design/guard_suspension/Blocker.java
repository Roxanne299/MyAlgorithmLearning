package com.zgy.model_design.guard_suspension;

import java.util.concurrent.Callable;

public interface Blocker {
    /**
     * �ڱ�����������ʱִ��Ŀ�궯��������������ǰ�̣߳�ֱ����������������
     * @param guardedAction ������������Ŀ�궯��
     * @return
     * @throws Exception
     */
    <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception;
    /**
     * ִ��stateOperation��ָ���Ĳ����󣬾����Ƿ��ѱ�Blocker
     * ���ݹҵ������߳��е�һ���̡߳�
     * @param stateOperation
     *          ����״̬�Ĳ�������call�����ķ���ֵΪtrueʱ���÷����Żỽ�ѱ��ݹҵ��߳�
     */
    void signalAfter(Callable<Boolean> stateOperation) throws Exception;
    void signal() throws InterruptedException;

    /**
     * ִ��stateOperation��ָ���Ĳ����󣬾����Ƿ��ѱ�Blocker
     * ���ݹҵ������̡߳�
     * @param stateOperation
     *          ����״̬�Ĳ�������call�����ķ���ֵΪtrueʱ���÷����Żỽ�ѱ��ݹҵ��߳�
     */
    void broadcastAfter(Callable<Boolean> stateOperation) throws Exception;
}
