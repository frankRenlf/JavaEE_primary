package thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/8/3 15:15
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
class TN {
    public synchronized void print() {
        System.out.println(Thread.currentThread().getName());
    }
}

public class Demo20 {

    static TN a = new TN();
    static TN b = new TN();
    static TN c = new TN();

    public static void main(String[] args) throws InterruptedException {
        Thread tc = new Thread(() -> {
                try {
                    c.wait();
                    c.print();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                b.notify();

        }, "c");
        Thread tb = new Thread(() -> {

                try {
                    b.wait();
                    b.print();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);

            }

                a.notify();


        }, "b");
        Thread ta = new Thread(() -> {

                try {
                    a.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                a.print();

        }, "a");




        ta.start();
        tb.start();
        tc.start();
        Thread.sleep(1000);
            c.notify();

    }

}
