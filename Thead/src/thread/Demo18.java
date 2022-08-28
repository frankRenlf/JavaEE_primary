package thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/8/3 14:35
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */


// [作业] 3 个线程按顺序打印 10 次 ABC
public class Demo18 {
    private static Object locker1 = new Object();
    private static Object locker2 = new Object();
    private static Object locker3 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    synchronized (locker1) {
                        locker1.wait();
                    }
                    System.out.print("A");
                    synchronized (locker2) {
                        locker2.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    synchronized (locker2) {
                        locker2.wait();
                    }
                    System.out.print("B");
                    synchronized (locker3) {
                        locker3.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    synchronized (locker3) {
                        locker3.wait();
                    }
                    System.out.println("C");
                    synchronized (locker1) {
                        locker1.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(1000);

        // 从线程 t1 启动
        synchronized (locker1){
            locker1.notify();
        }
    }
}
