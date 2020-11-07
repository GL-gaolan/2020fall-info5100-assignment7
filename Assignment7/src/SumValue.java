import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SumValue {
    /*
     *Write a program that calculate the sum value in an array of ints using 4 threads.
     * You should construct an 4,000,000 long array of random numbers and return the sum of the array.
     * Please finish those two method: generateRandomArray and sum.
     */
    /*generate array of random numbers*/
    static void generateRandomArray(int[] arr){
        Random r=new Random();
        for(int i = 0;i<arr.length;i++){

            arr[i]=r.nextInt();
        }
    }
    /*get sum of an array using 4 threads*/
    /*
    static long sum(int[] arr){
        List<countSubSumThread>list=new ArrayList<>();
        int interval =arr.length/4;

        for (int i=0;i<4;i++){
            countSubSumThread thread=new countSubSumThread(i*interval,(i+1)*interval,arr);
            list.add(thread);
            thread.start();
        }

        for (int i=0;i<4;i++){
            try{
                list.get(i).join();//this join is important, if not call join, threads cannot finish tasks when main is finished
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        long finalSum=0;
        for(int i=0;i<4;i++){
            finalSum+=list.get(i).getSum();
        }
        return finalSum;
    }*/
    static long sum(int[] arr){
        List<countSubSumThread>list=new ArrayList<>();
        int interval =arr.length/4;
        long finalSum=0;

        for (int i=0;i<4;i++){
            countSubSumThread thread=new countSubSumThread(i*interval,(i+1)*interval,arr);
            list.add(thread);
            thread.start();
            try{
                list.get(i).join();//this join is important, if not call join, threads cannot finish tasks when main is finished
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            finalSum+=list.get(i).getSum();}
        return finalSum; }



    public static void main(String[] args){
        int[] arr = new int[4000000];
        generateRandomArray(arr);
        long sum = sum(arr);
        System.out.println("Sum of four threads: " + sum);
    }
}

class countSubSumThread extends Thread{
    private int startIndex;
    private int endIndex;
    private long sum=0;
    private int[]arr;

    public countSubSumThread(int startIndex,int endIndex,int[]arr){
        this.startIndex=startIndex;
        this.endIndex=endIndex;
        this.arr=arr;
    }

    public long getSum(){
        return sum;
    }

    @Override
    public void run(){
        for(int i=startIndex;i<endIndex;++i){
            sum+=arr[i];
        }
        System.out.println(Thread.currentThread().getName()+" finished, sum=" + sum);
    }
}
