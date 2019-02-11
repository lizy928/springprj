package com.lizy.springprj.thread;

import java.util.concurrent.*;

/**
 * Created By Lizhengyuan on 19-2-11
 */
public class ThreadPool {

    //后台业务公用线程池
    private static final ExecutorService exec = new ThreadPoolExecutor(30, 30, 5 * 60, TimeUnit.SECONDS, new
            LinkedBlockingQueue<Runnable>());

    //发送短信专用
    private static ExecutorService smsExec = new ThreadPoolExecutor(3, 5, 5 * 60, TimeUnit.SECONDS, new
            LinkedBlockingDeque<Runnable>());


    private static ThreadPool instance = new ThreadPool();

    private ThreadPool() {
    }

    public static ThreadPool getInstance() {
        return instance;
    }

    public void executeRunnable(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        exec.execute(runnable);
    }

    public ExecutorService getSmsExecutorService() {
        return smsExec;
    }


}
