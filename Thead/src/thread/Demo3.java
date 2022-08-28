package thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/7/30 12:52
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */


public class Demo3 {

    Thread thread3 = new Thread() {
        @Override
        public void run() {
            System.out.println("123");
        }
    };

    Thread thread4 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("frank");
        }
    });

    Thread thread5 = new Thread(() -> {
        while (true) {
            System.out.println("frank");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    });

}
