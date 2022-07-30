package thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/7/30 11:29
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello thread!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Demo1 {

    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.start();
        System.out.println(123);
    }

}
