package 队列;

import java.util.Scanner;

public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        CircleArray queue=new CircleArray(4);//其数列有效数据最大是3
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

    class CircleArray {
        private int maxSize;//数组的最大容量
        private int front;//队列头
        private int rear;//队列尾,数据的最后一个
        private int[] arr;//该数组用于模拟队列

        public CircleArray(int arrMaxsize) {
            maxSize = arrMaxsize;
            arr = new int[maxSize];
            rear = 0;
            front = 0;
        }

        //判断数列是否满
        public boolean isFull() {

            return (rear + 1) % maxSize == front;
        }

        //判断数列是否空
        public boolean isEmpty() {

            return rear == front;
        }

        //添加数据到队列
        public void addQueue(int n) {
            if (isFull()) {
                System.out.println("队列满，不能加入数据");
                return;
            }
            //rear++;不需要了
            arr[rear] = n;
            //将rear后移，必须考虑取模
            rear = (rear + 1) % maxSize;
        }

        //数据出队列
        public int getQueue() {
            if (isEmpty()) {
                //通过抛出异常来处理,throw会return
                throw new RuntimeException("队列空不能取数据");
            }
            //front++;
            //需要分析出front指向队列的第一个元素
            //1.先把front对应的值保留到一个临时变量
            //2.将front后移,考虑取模
            //3.将临时保存的变量返回
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }

        //显示队列所有数据
        public void showQueue() {
            //遍历
            if (isEmpty()) {
                System.out.println("队列空，没有数据");
                return;
            }
            //从front开始遍历，遍历多少个元素
            for (int i = front; i < front+size(); i++) {
                System.out.printf("arr[%d]=%d\n", i%maxSize, arr[i%maxSize]);
            }

        }

        //求出当前队列有效数据的个数
        public int size(){
            return (rear+maxSize-front)%maxSize;
        }

        //显示队列的头数据
        public int headQueue(){
            if (isEmpty()){
                throw new RuntimeException("队列空");
            }
            return arr[front];
        }
    }

