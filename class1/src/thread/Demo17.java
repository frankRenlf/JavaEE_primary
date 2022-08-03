package thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/8/3 13:47
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */


public class Demo17 {

    public static void main(String[] args) throws InterruptedException {
        // 创建三个线程
        Thread tc = new Thread(() -> {
            // 打印c
            System.out.print(Thread.currentThread().getName() + " ");
        }, "c");

        Thread tb = new Thread(() -> {
            try {
                // 等待c 执行完成
                tc.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 打印b
            System.out.print(Thread.currentThread().getName() + " ");
        }, "b");

        Thread ta = new Thread(() -> {
            try {
                // 等待b 执行完成
                tb.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 打印a
            System.out.print(Thread.currentThread().getName() + " ");
        }, "a");

        // 需要让他们同时启动，并按 c，b，a的顺序打印
        ta.start();
        tb.start();
        tc.start();
    }
}
