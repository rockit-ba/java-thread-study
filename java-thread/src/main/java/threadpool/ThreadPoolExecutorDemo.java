package threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lucky
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        /**
         *  corePoolSize        :核心线程数，即使空闲也不会被回收(threadPoolExecutor.allowCoreThreadTimeOut(true);可以将
         *  keepAliveTime作用于corePoolSize 线程)
         *  maximumPoolSize     ：可创建的最大线程数
         *  keepAliveTime  unit     ：线程最大空闲时长，时长单位
         *  workQueue            ：任务的排队队列，只能是阻塞队列（BlockingQueue<Runnable>）
         *  threadFactory       ：新线程创建方式
         *  handler             ：拒绝策略
         *
         *  （1）当在线程池接收到新任务，并且当前工作线程数少于corePoolSize时，
         *  即使其他工作线程处于空闲状态，也会创建一个新线程来处理该请求，直到线程数达到corePoolSize。
         *  （2）如果当前工作线程数多于corePoolSize数量，但小于maximumPoolSize数量，
         *  那么仅当任务排队队列已满时才会创建新线程。通过设置corePoolSize和maximumPoolSize相同，
         *  可以创建一个固定大小的线程池。
         *  （3）当maximumPoolSize被设置为无界值（如Integer.MAX_VALUE）时，线程池可以接收任意数量的并发任务。
         *
         *
         *
        **/
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
                2,
                10, TimeUnit.DAYS,
                new ArrayBlockingQueue<Runnable>(10),
                r -> new Thread());

        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

}
