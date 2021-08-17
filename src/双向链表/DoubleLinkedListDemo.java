package 双向链表;

import 单链表.SingleLinkedListDemo;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode2 hero1=new HeroNode2(1,"松江","及时雨");
        HeroNode2 hero2=new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3=new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4=new HeroNode2(4,"林冲","豹子头");

        DoubleLinkedList doubleLinkedList=new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();
    }
}

//创建一个双向链表
class DoubleLinkedList {
    private HeroNode2 head=new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }
    //添加
    public void add(HeroNode2 heroNode){
        //因为头节点不能变，所以需要一个辅助遍历temp
        HeroNode2 temp=head;
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
        //形成一个双向链表
        temp.next=heroNode;
        heroNode.pre=temp;
    }

    //修改一个节点的内容
    public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点
       HeroNode2 temp=head.next;
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
    public void del(int no){
        if (head.next==null){
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp=head;
        boolean flag=false;//是否找到
        while (true){
            if (temp==null){//已经到最后了
                break;
            }
            if (temp.no==no){
                //找到了待删除结点temp
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            //如果是最后一个节点就不需要执行下面这句话，否则会出现空指针异常
            if (temp.next!=null){
                temp.next.pre=temp.pre;
            }
            temp.pre.next=temp.next;
        }else {
            System.out.println("要删除的节点不存在");
        }
    }

    //遍历
    public void list(){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，需要一个辅助变量
        HeroNode2 temp=head.next;
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

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//默认为null
    public HeroNode2 pre;

    public HeroNode2(int no, String hName, String hNickname) {
        this.no = no;
        this.name = hName;
        this.nickname = hNickname;
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