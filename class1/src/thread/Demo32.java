package thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/8/7 12:19
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
import java.util.concurrent.CountDownLatch;

public class Demo32 {
    public static void main(String[] args) throws InterruptedException {
        // 模拟跑步比赛
        // 构造方法中设定有几个选手参赛
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 9; i++) {
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    System.out.println("到达终点");
                    // countDown 相当于 "撞线"
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
        // await 在等待所有的线程 "撞线"
        // 调用 countDown 的次数达到初始化的时候设定的值
        // await 就返回. 否则 await 就阻塞等待!
        latch.await();

        System.out.println("比赛结束!");
    }
}
