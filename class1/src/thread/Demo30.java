package thread;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : class1
 * @Package : thread
 * @createTime : 2022/8/7 11:37
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class Demo30 {
    public static void main(String[] args) {
        // Thread实现
        methods_01();
        // 线程池实现
        methods_02();
    }

    // methods_02 用线程池的方式实现
    // 把1000拆成10组，分别用10个线程去执行，最后汇总结果
    private static void methods_02() {
        // 定义一个Callable描述任务
        class MyCallable implements Callable<Integer> {
            // 每组数据的开始值
            private int start;
            // 每组数据的结束值
            private int end;

            // 构造方法，传入开始与结束值，明确累加区间
            public MyCallable(int start, int end) {
                this.start = start;
                this.end = end;
            }

            // 实现call方法
            @Override
            public Integer call() throws Exception {
                // 完成累加操作并返回结果
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            }
        }
        // 将0 ~ 1000拆成果10组数据分别用10个线程去执行，最后统计结果
        int count = 10;
        // 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        // 定义一个Future数组
        Future<Integer>[] futures = new Future[count];
        // 拆分大数，并创建任务，提交到线程池
        for (int i = 0; i < count; i++) {
            int start = i * 100 + 1;
            int end = (i + 1) * 100;
            // 提交到线程池，并保存返回的Futuer
            Future<Integer> future = pool.submit(new MyCallable(start, end));
            futures[i] = future;
        }

        // 先定义返回的结果，初始为0
        int result = 0;
        for (int i = 0; i < futures.length; i++) {
            // 获取Future
            Future<Integer> future = futures[i];
            try {
                // 从Future中获取每条线程执行的结果
                int sum = future.get();
                // 累加
                result += sum;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        // 停止线程池
        pool.shutdown();
        System.out.println("methods_02结果为：" + result);


    }

    // 使用一条线程去执行任务
    public static void methods_01() {
        // 定义callable描述任务
        Callable<Integer> callable = new Callable<Integer>() {
            int sum = 0;

            @Override
            public Integer call() throws Exception {
                for (int i = 1; i <= 1000; i++) {
                    sum += i;
                }
                return sum;
            }
        };

        // 创建FutureTask并绑定Callable任务
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        // FutureTask也实现了Runnable可以做为入参
        Thread t = new Thread(futureTask);
        // 启动线程
        t.start();


        try {
            // 从Future中获取结果并打印
            Integer result = futureTask.get();
            System.out.println("methods_01结果为：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
