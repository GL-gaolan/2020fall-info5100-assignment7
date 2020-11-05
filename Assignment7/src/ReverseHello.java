public class ReverseHello implements Runnable{
    /*
    Write a program called ReverseHello.java that creates a thread (let's call it Thread 1).
    Thread 1 creates another thread (Thread 2);
    Thread 2 creates Thread 3; and so on, up to Thread 50.
    Each thread should print `Hello from Thread num!`
    but you should structure your program such that the threads print their greetings in reverse order.
*/
    private static int index=1;

    @Override
    public void run(){
        while (index<=50){
            index+=1;
            Thread thread=new Thread(new ReverseHello(),index +"");
            thread.start();
            try{
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }System.out.println("Hello from Thread "+Thread.currentThread().getName()+"!");
        }
    }

    public static void main(String[] args){
        new Thread(new ReverseHello(),index+"").start();
    }
}
