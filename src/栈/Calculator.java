package 栈;

public class Calculator {
    public static void main(String[] args) {
        String expression="30+2*6-2";
        ArrayStack2 numstack=new ArrayStack2(10);
        ArrayStack2 operstack=new ArrayStack2(10);
        //定义需要的相关变量
        int index=0;//用于扫描
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch=' ';//将每次扫描得到的char保存到ch
        String keepnum="";//用于拼接多位数
        //开始扫描
        while (true){
            //依次得到expression中每个字符
            ch=expression.substring(index,index+1).charAt(0);
            //判断ch是什么
            if (operstack.isOper(ch)){
                //判断当前符号栈是否为空
                if (!operstack.isEmpty()){
                    //不为空
                    if (operstack.priority(ch)<=operstack.priority(operstack.peek())){
                        num1=numstack.pop();
                        num2=numstack.pop();
                        oper=operstack.pop();
                        res=numstack.cal(num1,num2,oper);
                        numstack.push(res);
                        operstack.push(ch);
                    }else{
                        operstack.push(ch);
                    }
                }else {//如果为空直接入
                    operstack.push(ch);
                }
            }else {//如果是数直接入数栈
                //当处理多位数时不能发现是一个数就立即入栈，因为可能是多位数
                //所以应该在index后再看一位是不是数
                //因此需要定义一个变量字符串，用于拼接

                keepnum+=ch;

                //如果ch是最后一位则不需要判断直接入栈
                if (index==expression.length()-1){
                    numstack.push(Integer.parseInt(keepnum));
                }else {
                    //判断下一个字符是不是数字，如果是数字就继续扫描，如果是运算符则入栈
                    //只是往后面看一位，index值不要变
                    if (operstack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //如果后一位是运算符则入栈
                        numstack.push(Integer.parseInt(keepnum));
                        //keepnum清空
                        keepnum="";
                    }
                }
            }
            //让index+1，并判断是否扫描到expression最后
            index++;
            if (index>=expression.length()){
                break;
            }
        }
        //当表达式扫描完，就顺序的从数栈和符号栈中pop出相应的数和符号，并运算
        while (true){
            //如果符号栈为空则计算到最后的结果
            if (operstack.isEmpty()){
                break;
            }
            num1=numstack.pop();
            num2=numstack.pop();
            oper=operstack.pop();
            res=numstack.cal(num1,num2,oper);
            numstack.push(res);
        }
        System.out.println("结果"+numstack.pop());

    }
}

class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈
    private int top=-1;//top表示栈底

    public ArrayStack2(int maxSize){
        this.maxSize=maxSize;
        stack=new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top==maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top==-1;
    }
    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }
    //出栈
    public int pop(){
        if (isEmpty()){
            //因为要return一个值所以这里抛出异常
            throw new RuntimeException("栈空");
        }
        int value=stack[top];
        top--;
        return value;
    }

    //返回当前栈顶的值，但不是真正pop
    public int peek(){
        return stack[top];
    }

    //遍历栈
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i=top;i>=0;i--){
            System.out.println(stack[i]);
        }
    }

    //返回运算符的优先级，优先级使用数字表示，数字越大优先级越高
    public int priority(int oper){
        if (oper=='*'||oper=='/'){
            return 1;
        }else if(oper=='+'||oper=='-'){
            return 0;
        }else{
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res=0;//用于存放计算的结果
        switch (oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res=num2-num1;//注意顺序
                break;
            case '*':
                res=num1*num2;
                break;
            case '/':
                res=num2/num1;
                break;
        }
        return res;
    }

}


