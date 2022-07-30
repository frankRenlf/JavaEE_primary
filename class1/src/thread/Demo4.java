package thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/7/30 13:29
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class Demo4 {

    private static final long len = 20_0000_0000;

    public static void serial() {
        long begin = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < len; i++) {
            a++;
        }
        a = 0;
        for (int i = 0; i < len; i++) {
            a++;
        }
        long end = System.currentTimeMillis();
        System.out.println("Time cost: " + (end - begin));
    }

    public static void concurrency() {
        long begin = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            int a = 0;
            for (int i = 0; i < len; i++) {
                a++;
            }
        });

        Thread thread2 = new Thread(() -> {
            int a = 0;
            for (int i = 0; i < len; i++) {
                a++;
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        System.out.println("Time cost: " + (end - begin));
    }

    public static void main(String[] args) {
//        test1();
        concurrency();
    }

}
