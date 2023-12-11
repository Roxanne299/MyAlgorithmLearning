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
        Dinner dinner=new Person("张三");
        // 通过Porxy动态代理获得一个代理对象,在代理对象中,对某个方法进行增强
//        ClassLoader loader,被代理的对象的类加载器 这个可以写成 Dinner.class.getclassLoader()
        ClassLoader classLoader = dinner.getClass().getClassLoader();
//        Class<?>[] interfaces,被代理对象所实现的所有接口 这个不可以写成 Dinner.class.getInterfaces()
//        Class[] interaces= dinner.getClass().getInterfaces();
        Class[] interfaces = Person.class.getInterfaces();
//        InvocationHandler h,执行处理器对象,专门用于定义增强的规则
        InvocationHandler handler = new InvocationHandler(){
            // invoke 当我们让代理对象调用任何方法时,都会触发invoke方法的执行
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                Object proxy, 代理对象
//                Method method,被代理的方法
//                Object[] args,被代理方法运行时的实参
                Object res=null;
                if(method.getName().equals("eat")){
                    System.out.println("饭前洗手");
                    // 让原有的eat的方法去运行
                    res =method.invoke(dinner, args);
                    System.out.println("饭后刷碗");
                }else{
                    // 如果是其他方法,那么正常执行就可以了
                    res =method.invoke(dinner, args);
                }
                return res;
            }
        };
        Dinner dinnerProxy =(Dinner) Proxy.newProxyInstance(classLoader,interfaces,handler);
        //dinnerProxy.eat("包子");
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
        System.out.println(name+"正在吃"+foodName);
    }
    @Override
    public void drink( ) {
        System.out.println(name+"正在喝茶");
    }
}
class Student implements Dinner{
    private String name;
    public Student(String name) {
        this.name = name;
    }
    @Override
    public void eat(String foodName) {
        System.out.println(name+"正在食堂吃"+foodName);
    }
    @Override
    public void drink( ) {
        System.out.println(name+"正在喝可乐");
    }
}