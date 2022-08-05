package thread;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/8/5 12:17
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */

class MyTask implements Comparable<MyTask> {
    // 任务要干啥
    private Runnable command;
    // 任务啥时候干
    private long time;

    public MyTask(Runnable command, long after) {
        this.command = command;
        // 此处记录的时间是一个绝对的时间戳. 不是 "多长时间之后能执行"
        this.time = System.currentTimeMillis() + after;
    }

    // 执行任务的方法, 直接在内部调用 Runnable 的 run 即可.
    public void run() {
        command.run();
    }

    public long getTime() {
        return time;
    }

    @Override
    public int compareTo(MyTask o) {
        // 希望时间小的在前面, 时间大的在后面
        // 这里谁减谁 才能达到时间小的在前~ 大家不要刻意背.
        // 写代码验证一下.
        return (int) (this.time - o.time);
    }
}

// 咱们自己创建的定时器类.
class MyTimer {
    // 这个是用来阻塞等待的锁对象
    private Object locker = new Object();

    // 使用优先级队列来保存若干个任务.
    private PriorityBlockingQueue<MyTask> queue = new PriorityBlockingQueue<>();

    // command 要执行的任务是啥
    // after 多长时间之后来执行这个任务
    public void schedule(Runnable command, long after) {
        MyTask myTask = new MyTask(command, after);
        synchronized (locker) {
            queue.put(myTask);
            locker.notify();
        }
    }

    public MyTimer() {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    synchronized (locker) {
                        while (queue.isEmpty()) {
                            locker.wait();
                        }
                        MyTask myTask = queue.take();
                        long current = System.currentTimeMillis();
                        if (myTask.getTime() <= current) {
                            myTask.run();
                        } else {
                            queue.put(myTask);
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
    }
}

public class Demo23 {
}
