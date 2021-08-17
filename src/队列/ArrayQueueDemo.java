package 队列;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args){
        ArrayQueue queue=new ArrayQueue(3);
        char key=' ';
        Scanner scanner=new Scanner(System.in);
        boolean loop=true;
        //输出菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");

            key=scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value=scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    //可能会有异常
                    try {
                        int res=queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");

    }

}

//使用数组模拟队列
class ArrayQueue{
    private  int maxSize;//数组的最大容量
    private int front;//队列头
    private int rear;//队列尾,数据的最后一个
    private int[] arr;//该数组用于模拟队列

    //创建队列构造器
    public  ArrayQueue(int arrMaxSise){
        maxSize=arrMaxSise;
        arr=new int[maxSize];
        front=-1;//指向队列头部，此时下标为0；
        rear=-1;
    }

    //判断数列是否满
    public  boolean isFull(){
        return rear==maxSize-1;
    }

    //判断数列是否空
    public  boolean isEmpty(){
        return rear==front;
    }

    //添加数据到队列
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        rear++;
        arr[rear]=n;
    }
    //数据出队列
    public int getQueue(){
        if (isEmpty()){
            //通过抛出异常来处理,throw会return
            throw new RuntimeException("队列空不能取数据");
        }
        front++;
        return arr[front];
    }

    //显示队列所有数据
    public void showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        for (int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

}
