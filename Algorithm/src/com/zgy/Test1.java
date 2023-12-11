package com.zgy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
/**
 * @Author: Ma HaiYang
 * @Description: MircoMessage:Mark_7001
 */
public class Test1 {
    public static void main(String[] args) {
        Dinner dinner=new Person("����");
        // ͨ��Porxy��̬������һ���������,�ڴ��������,��ĳ������������ǿ
//        ClassLoader loader,������Ķ����������� �������д�� Dinner.class.getclassLoader()
        ClassLoader classLoader = dinner.getClass().getClassLoader();
//        Class<?>[] interfaces,�����������ʵ�ֵ����нӿ� ���������д�� Dinner.class.getInterfaces()
//        Class[] interaces= dinner.getClass().getInterfaces();
        Class[] interfaces = Person.class.getInterfaces();
//        InvocationHandler h,ִ�д���������,ר�����ڶ�����ǿ�Ĺ���
        InvocationHandler handler = new InvocationHandler(){
            // invoke �������ô����������κη���ʱ,���ᴥ��invoke������ִ��
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                Object proxy, �������
//                Method method,������ķ���
//                Object[] args,������������ʱ��ʵ��
                Object res=null;
                if(method.getName().equals("eat")){
                    System.out.println("��ǰϴ��");
                    // ��ԭ�е�eat�ķ���ȥ����
                    res =method.invoke(dinner, args);
                    System.out.println("����ˢ��");
                }else{
                    // �������������,��ô����ִ�оͿ�����
                    res =method.invoke(dinner, args);
                }
                return res;
            }
        };
        Dinner dinnerProxy =(Dinner) Proxy.newProxyInstance(classLoader,interfaces,handler);
        //dinnerProxy.eat("����");
        dinnerProxy.drink();
    }
}
interface Dinner{
    void eat(String foodName);
    void drink();
}
class Person implements Dinner{
    private String name;
    public Person(String name) {
        this.name = name;
    }
    @Override
    public void eat(String foodName) {
        System.out.println(name+"���ڳ�"+foodName);
    }
    @Override
    public void drink( ) {
        System.out.println(name+"���ںȲ�");
    }
}
class Student implements Dinner{
    private String name;
    public Student(String name) {
        this.name = name;
    }
    @Override
    public void eat(String foodName) {
        System.out.println(name+"����ʳ�ó�"+foodName);
    }
    @Override
    public void drink( ) {
        System.out.println(name+"���ںȿ���");
    }
}