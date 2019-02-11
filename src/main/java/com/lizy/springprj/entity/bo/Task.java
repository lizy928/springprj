package com.lizy.springprj.entity.bo;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
public class Task implements Delayed {

    private Long gameId;
    private String goodId;//消息体
    private Long periodId;//消息体
    private JSONArray jsonArrayList;//消息体
    private long trigger;// 时间触发器
    private long excuteTime;//执行时间

    public Task(Long gameId, String goodId, Long periodId,
                JSONArray jsonArrayList,
                long delayTime) {
        this.gameId = gameId;
        this.goodId = goodId;
        this.periodId = periodId;
        this.jsonArrayList = jsonArrayList;
        this.excuteTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }


    public void setExcuteTime(long excuteTime) {
        this.excuteTime = excuteTime;
    }

    @Override
    public int compareTo(Delayed delayed) {
        Task that = (Task) delayed;
        if (excuteTime < that.excuteTime) {
            return -1;
        }
        if (excuteTime > that.excuteTime) {
            return 1;
        }
        return 0;
    }
    public void setDelay(long delay) {
        //此处delay取值为Long.MAX_VALUE的时候，triger的值变为负值，会被放在DelayQueue的最开始位置，从而阻塞队列中的所有元素。
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delay, TimeUnit.MILLISECONDS);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long delay = unit.convert(this.excuteTime - System.nanoTime(), TimeUnit.NANOSECONDS);
        System.out.println("---  getDelay  ----" +delay);
        return delay;
    }
}
