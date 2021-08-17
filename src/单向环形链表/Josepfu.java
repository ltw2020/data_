package 单向环形链表;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
    }
}

    //创建一个环形单向链表
    class CircleSingleLinkedList {
        //创建first节点，当前没有编号
        private Boy first = new Boy(-1);

        //添加小孩节点
        public void addBoy(int nums) {
            if (nums < 1) {
                System.out.println("nums的值不正确");
                return;
            }
            Boy curBoy = null;//辅助指针
            //使用for来创建我们的环形链表
            for (int i = 1; i <= nums; i++) {
                //创建节点
                Boy boy = new Boy(i);
                //如果是第一个小孩
                if (i == 1) {
                    first = boy;
                    first.setNext(first);//构成环
                    curBoy = first;//让curBoy指向第一个
                } else {
                    curBoy.setNext(boy);//因为这里boy节点是private，所以要用set
                    boy.setNext(first);
                    curBoy = boy;
                }

            }
        }

        //遍历当前环形列表
        public void showBoy() {
            if (first.getNext()== null) {
                System.out.println("链表为空");
                return;
            }
            //因为first不能动
            Boy curBoy = first;
            while (true) {
                System.out.println("小孩编号" + curBoy.getNo());
                if (curBoy.getNext() == first) {//说明已经遍历完毕
                    break;
                }
                curBoy = curBoy.getNext();
            }
        }

        //小孩出圈顺序
        public void countBoy(int starNo,int countNum,int nums){
            if (first.getNext()==null||starNo<1||starNo>nums){
                System.out.println("参数输入有误");
                return;
            }
            Boy helper=first;
            while (true){
                if (helper.getNext()==first){//helper移到最后
                    break;
                }
                helper=helper.getNext();
            }
            //包数前先移动k-1次
            for (int j=0;j<starNo-1;j++){
                first=first.getNext();
                helper=helper.getNext();
            }
            //报数
            while (true){
                //直到圈中只有一个人
                if (helper==first){
                    break;
                }
                for (int j=0;j<countNum-1;j++){
                    first=first.getNext();
                    helper=helper.getNext();
                }
                //这时first是要出圈节点
                first=first.getNext();
                helper.setNext(first);//注意！！！！！！
            }
        }
    }

        //创建一个boy类表示一个节点
        class Boy {
            private int no;//编号
            private Boy next;//指向下一个节点

            public Boy(int no) {
                this.no = no;
            }

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public Boy getNext() {
                return next;
            }

            public void setNext(Boy next) {
                this.next = next;
            }
        }


