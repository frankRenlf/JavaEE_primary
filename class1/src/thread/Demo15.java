package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/8/2 15:34
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class Demo15 {

    static class Counter {
        volatile int flag = 0;

        public void increase() {
            flag++;
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            while (counter.flag == 0) {
//                System.out.println("running");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }
        });
        Thread t2 = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter : ");
            counter.flag = sc.nextInt();
        });
        t1.start();
        t2.start();
    }

    public static void main1(String[] args) {
    }

}
