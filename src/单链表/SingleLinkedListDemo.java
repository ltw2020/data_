package 单链表;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args){
        //先创建节点
        HeroNode hero1=new HeroNode(1,"松江","及时雨");
        HeroNode hero2=new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3=new HeroNode(3,"吴用","智多星");
        HeroNode hero4=new HeroNode(4,"林冲","豹子头");

        //创建一个链表
        SingleLinkedList singleLinkedList=new SingleLinkedList();
        //加入
        //singleLinkedList.add(hero1);
        //singleLinkedList.add(hero2);
        //singleLinkedList.add(hero3);
        //singleLinkedList.add(hero4);

        singleLinkedList.addOrder(hero1);
        singleLinkedList.addOrder(hero3);
        singleLinkedList.addOrder(hero2);
        singleLinkedList.addOrder(hero4);

        //删除一个节点
        singleLinkedList.del(1);

        //有效节点
        System.out.println(singleLinkedList.getLength(singleLinkedList.getHead()));

        //倒数第k个
        HeroNode res=singleLinkedList.findLastIndexNode(singleLinkedList.getHead(),2);
        System.out.println(res);

        //显示
        singleLinkedList.list();

    }

    //定义SingleLinkedList管理英雄,操作链表--------------------------------------------------------------------------
    static class SingleLinkedList{
        //初始化一个头节点
        private HeroNode head=new HeroNode(0,"","");

        public HeroNode getHead() {
            return head;
        }//获取私有变量

        //添加节点到单向链表
        //当不考虑编号顺序时
        //1.找到当前链表的最后节点
        //2。将最后这个节点的next指向新的节点
        public void add(HeroNode heroNode){
            //因为头节点不能变，所以需要一个辅助遍历temp
            HeroNode temp=head;
            //遍历链表，找到最后
            while (true){
                //找到链表的最后
                if (temp.next==null){//temp.next意思可能是头节点的下一个节点（对象的下一个对象）
                    break;
                }
                //如果没有找到，将temp后移
                temp=temp.next;
            }
            //当退出while循环，temp就指向了链表的最后
            //将最后这个节点的next指向新的节点
            temp.next=heroNode;
        }

        //第二种添加英雄的方式，根据排名将英雄插入到指定位置
        public void addOrder(HeroNode heroNode){
            HeroNode temp=head;
            //我们找到的temp是添加处的前一个节点
            boolean flag=false;//标志添加的编号是否存在
            while (true){
                if(temp.next==null){//说明temp已经在链表的最后
                    break;
            }
                if (temp.next.no>heroNode.no) {//位置找到
                    break;
                }else if (temp.next.no==heroNode.no) {//说明新节点的编号已经存在
                    flag=true;
                    break;
                }
                temp=temp.next;
                }
            //判断flag的值
            if (flag==true){//不能添加
                System.out.println("待插入的英雄编号已经存在了");
            }else{
                //插入到temp后面
                heroNode.next=temp.next;
                temp.next=heroNode;
            }
        }

        //根据no编号修改节点信息
        public void update(HeroNode newHeroNode){
            //判断是否为空
            if (head.next==null){
                System.out.println("链表为空");
                return;
            }
            //找到需要修改的节点
            HeroNode temp=head.next;
            boolean flag=false;//表示是否找到该节点
            while (true){
                if (temp==null){
                    break;
                }
                if (temp.no== newHeroNode.no){
                    flag=true;
                    break;
                }
                temp=temp.next;
            }
            //根据flag判断是否找到
            if (flag){
                temp.name=newHeroNode.name;
                temp.nickname=newHeroNode.nickname;
            }else {
                System.out.println("没有找到该节点");
            }

        }

        //删除节点
        //1.找到删除点的前一个节点
        public void del(int no){
            HeroNode temp=head;
            boolean flag=false;//是否找到
            while (true){
                if (temp.next==null){//已经到最后了
                    break;
                }
                if (temp.next.no==no){
                    //找到了待删除结点的前一个结点temp
                    flag=true;
                    break;
                }
                temp=temp.next;
            }
            if (flag){
                temp.next=temp.next.next;
            }else {
                System.out.println("要删除的节点不存在");
            }
        }

        //新浪面试：获取到单链表节点的个数
        public static int getLength(HeroNode head){
            if (head.next==null){
                return 0;
            }
            int length=0;
            HeroNode cur=head;
            while (cur.next!=null){
                length++;
                cur=cur.next;
            }
            return length;
        }

        //查找倒数第k个节点
        //1.编写一个方法，同时接受head节点和index
        //2.index表示倒数第index个节点
        //3.先从头到尾遍历，得到链表的总长的，再用总长度-倒数第几个，就是正数第几个，再从头开始遍历
        public static  HeroNode findLastIndexNode(HeroNode head,int index){
            if (head.next==null){
                return null;
            }
            //第一次遍历得到长度
            int size=getLength(head);
            //第二次遍历size-index位置，就是要找的节点
            if(index<=0||index>size){
                return null;
            }
            HeroNode cur=head.next;
            for (int i=0;i<size-index;i++){
                cur=cur.next;
            }
            return cur;
        }

        //单链表反转
        public static void reversetList(HeroNode head){
            if (head.next==null||head.next.next==null){
                return;
            }
            HeroNode cur=head.next;
            HeroNode next=null;//指向当前节点【cur】的下一个节点；
            HeroNode reverseHead=new HeroNode(0,"","");
            //遍历原来的链表，每遍历一遍取出一个，插入到新表头节点后面
            while (cur!=null){
                next=cur.next;//?为什么两个next可以同名呢
                cur.next=reverseHead.next;//将cur的下一个节点指向新的链表的最前端
                reverseHead.next=cur;//将cur连接到新的链表上
                cur=next;//cur后移
            }
            head.next=reverseHead.next;
        }

        //逆序打印链表
        public static void reversePrint(HeroNode head){
            if (head.next==null){
                return;
            }
            //创建一个栈，将各个节点压入栈
            Stack<HeroNode> stack=new Stack<HeroNode>();
            HeroNode cur=head.next;
            //将链表的所有节点压入栈
            while (cur!=null){
                stack.push(cur);
                cur=cur.next;
            }
            //打印
            while (stack.size()>0){
                System.out.println(stack.pop());
            }
        }

        //显示链表
        public void list(){
            //判断链表是否为空
            if (head.next==null){
                System.out.println("链表为空");
                return;
            }
            //因为头节点不能动，需要一个辅助变量
            HeroNode temp=head.next;
            while (true){
                //判断是否到链表最后,防止死循环
                if (temp==null){
                    break;
                }
                //输出节点的信息
                System.out.println(temp);
                //将temp后移
                temp=temp.next;

            }

        }
    }

    //定义HeroNode，每个HeroNode对象就是一个节点----------------------------------------------------------------------------------
    static class HeroNode{
        public int no;
        public String name;
        public String nickname;
        public HeroNode next;

        public HeroNode(int no,String hName,String hNickname){
            this.no=no;
            this.name=hName;
            this.nickname=hNickname;
        }

        //右键 generate toString（）

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", next=" + next +
                    '}';
        }
    }
}


