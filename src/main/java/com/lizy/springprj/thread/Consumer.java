package com.lizy.springprj.thread;

import com.lizy.springprj.entity.bo.Task;

import java.util.concurrent.DelayQueue;

/**
 * Created By Lizhengyuan on 19-2-11
 */
public class Consumer implements Runnable{

    private DelayQueue queue;

    public Consumer(DelayQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Task task = (Task) queue.take();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
