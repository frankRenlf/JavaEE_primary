package thread;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/7/31 11:28
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class demo10 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().isInterrupted());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Catch exception");
                    System.out.println("1.continue. 2.exit");
                    System.out.println("Select: ");
                    Scanner sc = new Scanner(System.in);
                    if (sc.nextInt() == 1) {
                        System.out.println("continue");
                    } else {
                        break;
                    }
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(5000);
        t.interrupt();

    }

}
