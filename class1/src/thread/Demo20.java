package thread;

import java.lang.reflect.Array;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/8/4 12:03
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */

class MyBlockQueue<T> {
    private T[] item;
    private int head = 0;
    private int tail = 0;
    private volatile int size = 0;


    public MyBlockQueue(Class<T> clazz, int length) {
        item = (T[]) Array.newInstance(clazz, length);
    }

    public void put(T val) throws InterruptedException {
        synchronized (this) {
            while (size == item.length) {
                this.wait();
            }
            item[tail] = val;
            tail++;
            if (tail == item.length) {
                tail = 0;
            }
            size++;
            this.notify();
        }
    }

    public T take() throws InterruptedException {
        synchronized (this) {
            while (size == 0) {
                this.wait();
            }
            T ret = item[head];
            head++;
            if (head == item.length) {
                head = 0;
            }
            size--;
            this.notify();
            return ret;
        }
    }

     

}

public class Demo20 {

    public static void main(String[] args) throws InterruptedException {
        MyBlockQueue<Integer> myBlockQueue = new MyBlockQueue<>(Integer.class, 5);
        Thread t1 = new Thread(() -> {
            try {
                int index = 0;
                while (true) {
                    System.out.println("put: " + index);
                    myBlockQueue.put(index);
                    index++;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("take: " + myBlockQueue.take());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
    }

}
