package thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/8/5 12:22
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class Demo22 {
    public static Object locker1 = new Object();
    public static Object locker2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (locker1) {
                System.out.println("t1 start");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 finish");
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (locker1) {
                System.out.println("t2 start");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 finish");
            }
        });
        t2.start();
    }
}
