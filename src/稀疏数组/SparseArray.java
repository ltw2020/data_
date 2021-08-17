package 稀疏数组;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0表示没有棋子，1表示黑棋，2表示蓝棋
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //增强for循环输出二维数组
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);//每输出一个空格八位
            }
            System.out.println();
        }

        //将二维数组转稀疏数组的思想------------------------------------------------------------------------------------------
        //1.先遍历二维数组
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0值存到稀疏数组中
        int count = 0;//count用于记录是第几个非零数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //稀疏数组输出
        System.out.println();
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将稀疏数组恢复成二维数组-------------------------------------------------------------------------------------------
        //1.先读取稀疏数组第一行
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.
        for (int i = 1; i <sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println();
        for (int[] row:chessArr2){
            for (int data:row){
                System.out.printf("%d\t",data);//每输出一个空格八位
            }
            System.out.println();
        }

        //io流保存读取文件-------------------------------------------------------------------------------------------------
        //利用IO流将稀疏数组写入save1文件
        FileWriter writer = null;

        try {
            writer = new FileWriter("Save1.txt");//如果是保存在电脑里则写路径文件
            for(int i =0; i < sparseArr.length; i++) {
                for(int j = 0; j < 3; j++) {
                    writer.write(sparseArr[i][j]);
                }
  //              writer.write("\r");
//				写入的时候不需要换行！！我在这里摔倒了就不希望有人再在同一个地方摔倒。
//				如果你发现写入和读取的数字不对，第一件事情请看看你有没有把换行符之类的也写入了
//				导致reader把你的换行符也读取了。
            }
            writer.flush();
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {
                if(writer != null) {
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        //读取稀疏数组---------------------------------------------------------------------------------------------
        FileReader reader = null;
        int[][] sparseArr2 = new int[sum+1][3];
        int getNum = 0;
        try {
            reader = new FileReader("Save1.txt");

            for(int i =0; i < sparseArr2.length; i++) {
                for(int j =0; j < 3; j++) {
                    getNum = reader.read();
                    sparseArr2[i][j] = getNum;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //利用IO流将save1文件读取成稀疏数组
        //输出一下的稀疏数组

        System.out.println();
        System.out.println("读取后稀疏数组为");
        for(int i =0; i < sparseArr2.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArr2[i][0],sparseArr2[i][1],sparseArr2[i][2]);
            //格式化输出
        }
        System.out.println();

    }
}
