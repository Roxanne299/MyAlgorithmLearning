import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Test{
    public static int N = 1510,idx;
    public static int[] e = new int[N],ne = new int[N],h = new int[N],stu = new int[N];
    public static int[][] dp = new int[N][2];
    public static Scanner sc = new Scanner(System.in);

    public static void insert(int a,int b){
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    public static void main(String[] args){
        String pat = "(\\d+)";

        String s = "0:(1) 1";
        Pattern compile = Pattern.compile(pat);
        Matcher matcher = compile.matcher(s);
        while(matcher.find()){
            System.out.println(matcher.group());
        }
    }
}