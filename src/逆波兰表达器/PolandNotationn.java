package 逆波兰表达器;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class PolandNotationn {
    public static void main(String[] args) {

        //完成将中缀表达式转后缀表达式
        //1.先将中缀表达式放入list中
        //2.将中缀表达式对应的List转成后缀表达式对应的

        String expression="1+((2+3)*4)-5";
        List<String> infixExpressionList=toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        System.out.println(parseSuffixExpressionList(infixExpressionList));


        //定义一个逆波兰表达式
        //（3+4）*5-6  => 3 4 + 5 * 6 -
        //逆波兰表达式数字和符号用空格隔开
        String suffixExpression="3 4 + 5 * 6 -";//前后不要有空格
        //思路
        //1.先将后缀放入ArrayList中
        //2.将ArrayList传递给一个方法配合站完成计算，遍历ArrayList
       /* List<String>  list=getListString(suffixExpression);
        System.out.println(list);

        int res=calculate(list);
        System.out.println(res);*/
    }

    //转后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1=new Stack<String>();
        //因为s2栈在整个转换过程中没有pop的操作，因此比较麻烦，所以使用List代替这个栈
        //Stack<String> s2=new Stack<String>();
        List<String> s2=new ArrayList<String>();

        //遍历ls
        for (String item:ls){
            //如果是一个数就入栈
            if (item.matches("\\d+")){//这说明它是一个数
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将小括号弹出
            }else {
                //当item优先级<=s1栈顶的运算符
                while (s1.size()!=0&& Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符加入s2中
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;//按顺序输出就是对应的后缀表达式
    }

    //将中缀表达式转成List
    public static List<String> toInfixExpressionList(String s){
        //定义一个List，存放中缀表达式对应的内容
        List<String> ls=new ArrayList<String>();
        int i=0;//是一个指针用于遍历中缀
        String str;//做多位数的拼接工作
        char c;//每遍历到一个字符就放入c
        do{
            //如果c是一个非数字，需要加入到ls
            if ((c=s.charAt(i))<48||(c=s.charAt(i))>57){//charAt表示从几开始索引(从0开始）
               ls.add(""+c);//转换成字符
               i++;
            }else {//如果是一个数，考虑多位数
                str="";
                while (i<s.length()&&(c=s.charAt(i))>=48 && (c=s.charAt(i))<=57){
                    str+=c;
                    i++;
                }
                ls.add(str);
            }

        }while (i<s.length());
        return ls;
    }

    //将后缀表达式依次入栈
    public static List<String> getListString(String suffixExpression){
        //按空格分割数组
        String[] split=suffixExpression.split(" ");
        List<String> list=new ArrayList<String>();
        for (String ele:split){
            list.add(ele);
        }
        return list;
    }

    //运算
    public static int calculate(List<String> ls){
        //创建一个栈，只需要一个栈
        Stack<String> stack =new Stack<String>();
        //遍历list
        for (String item:ls){
            //使用正则表达式来取出数
            if (item.matches("\\d+")){//匹配多位数
                //入栈
                stack.push(item);
            }else {//是运算符则弹出两个数运算
                int num2=Integer.parseInt(stack.pop());
                int num1=Integer.parseInt(stack.pop());
                int res=0;
                if (item.equals("+")){
                    res=num1+num2;
                }else if(item.equals("-")){
                    res=num1-num2;
                }else if(item.equals("*")){
                    res=num1*num2;
                }else if(item.equals("/")){
                    res=num1/num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res+"");//把正数转换成字符串
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

//判断优先级
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation){
        int result=0;
        switch (operation){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("不存在该运算");
                break;
        }
        return result;
    }
}
