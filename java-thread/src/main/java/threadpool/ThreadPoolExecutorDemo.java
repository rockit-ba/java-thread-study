package threadpool;

import java.util.concurrent.*;

/**
 * @author lucky
 * 通常 注册JVM钩子函数自动关闭线程池
 * 混合型的线程数一般 核数*10即可
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
                // LinkedBlockingQueue 默认无界
                new LinkedBlockingQueue<>(10),
                r -> new Thread(),
                new ThreadPoolExecutor.AbortPolicy());


        threadPoolExecutor.allowCoreThreadTimeOut(true);
        /**
         *  （1）如果当前工作线程数量小于核心线程数量，执行器总是优先创建一个任务线程，而不是从线程队列中获取一个空闲线程。
         *
         *  （2）如果线程池中总的任务数量大于核心线程池数量，新接收的任务将被加入阻塞队列中，一直到阻塞队列已满。
         *  在核心线程池数量已经用完、阻塞队列没有满的场景下，线程池不会为新任务创建一个新线程。
         *
         *  （3）当完成一个任务的执行时，执行器总是优先从阻塞队列中获取下一个任务，并开始执行，
         *  一直到阻塞队列为空，其中所有的缓存任务被取光。
         *
         *  （4）在核心线程池数量已经用完、阻塞队列也已经满了的场景下，如果线程池接收到新的任务，
         *  将会为新任务创建一个线程（非核心线程），并且立即开始执行新任务。
         *
         *  （5）在核心线程都用完、阻塞队列已满的情况下，一直会创建新线程去执行新任务，直到池内的线程总数超出maximumPoolSize。
         *  如果线程池的线程总数超过maximumPoolSize，线程池就会拒绝接收任务，当新任务过来时，会为新任务执行拒绝策略。
        **/


    }


}
