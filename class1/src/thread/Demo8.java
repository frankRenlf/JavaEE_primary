package thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/7/31 11:00
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class Demo8 {

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"frank");
        t.start();
        System.out.println(t.getId());
        System.out.println(t.getName());
        System.out.println(t.getState());
        System.out.println(t.isInterrupted());
        System.out.println(t.getPriority());
        System.out.println(t.isAlive());
    }


}
