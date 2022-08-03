package thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/8/3 14:37
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */

class T {
    public void print() {
        System.out.println(Thread.currentThread().getName());
    }
}

public class Demo19 {

    static T a = new T();
    static T b = new T();
    static T c = new T();

    public static void main(String[] args) throws InterruptedException {
        Thread tc = new Thread(() -> {
            synchronized (c) {
                try {
                    c.wait();
                    c.print();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            synchronized (b) {
                b.notify();
            }
        }, "c");
        Thread tb = new Thread(() -> {
            synchronized (b) {
                try {
                    b.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            b.print();
            synchronized (a) {
                a.notify();
            }

        }, "b");
        Thread ta = new Thread(() -> {
            synchronized (a) {
                try {
                    a.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                a.print();
            }
        }, "a");




        ta.start();
        tb.start();
        tc.start();
        Thread.sleep(1000);
        synchronized (c) {
            c.notify();
        }
    }

}
