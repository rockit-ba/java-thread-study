package threadlocal;

/**
 * @author lucky
 */
public class ThreadLocalDemo {
    private static final ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(() -> "hello");

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(THREAD_LOCAL.get()+Thread.currentThread().getName());
            THREAD_LOCAL.set(Thread.currentThread().getName() + THREAD_LOCAL.get());
            System.out.println(THREAD_LOCAL.get());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println(THREAD_LOCAL.get()+Thread.currentThread().getName());
            THREAD_LOCAL.set(Thread.currentThread().getName() + THREAD_LOCAL.get());
            System.out.println(THREAD_LOCAL.get());
        });


        thread.start();
        thread2.start();
        System.out.println(THREAD_LOCAL.get());


    }








}
