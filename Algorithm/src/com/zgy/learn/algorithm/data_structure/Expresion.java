package com.zgy.learn.algorithm.data_structure;




import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;



public class Expresion {

    public static Stack<Integer> as = new Stack<Integer>();
    public static Stack<Character> cs = new Stack<Character>();
    public static HashMap<Character, Integer> judge = new HashMap() {
        {
            put('(', 0);
            put('+', 1);
            put('-', 1);
            put('*', 2);
            put('/', 2);
        }

    };
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String e = in.next();
        e = "(" + e + ")";//防止最后一个数字是多位数 然后会越界
        int num = 0;
        int i = 0;
        while (i<e.length()) {

            Character c = e.charAt(i);
            if(c=='(')
            {
                cs.push(c);
            }else if(Character.isDigit(c)) {
                int sum=0;
                while(Character.isDigit(e.charAt(i))) {
                    sum = sum * 10 + (int)e.charAt(i) - 48;
                    i++;
                }
                as.push(sum);
                i--;

            }else if(c==')') {

                while(cs.peek()!='(')
                {
                    cul();
                }
                cs.pop();
            }
            else {
                while(!cs.isEmpty()&&judge.get(c)<=judge.get(cs.peek())) cul();
                cs.push(c);
            }

            i++;
        }
        while(!cs.isEmpty()) {
            cul();
        }
        System.out.print(as.pop());
    }



    public static void cul() {
        int a = as.pop();
        int b = as.pop();
        char a1 = cs.pop();
        switch (a1) {
            case '+':
                as.push(b+a);
                return ;
            case '-':
                as.push(b-a);
                return;
            case '*':
                as.push(b * a);
                return ;
            case '/':
                as.push(b / a);
                return ;

            default:
                break;
        }

        return ;
    }


}
