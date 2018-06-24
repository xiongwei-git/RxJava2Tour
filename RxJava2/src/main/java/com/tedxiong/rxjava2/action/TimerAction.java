package com.tedxiong.rxjava2.action;

import android.util.Log;

import com.tedxiong.rxjava2.util.TimeUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * timer 很有意思，相当于一个定时任务。在 1.x 中它还可以执行间隔逻辑，
 * 但在 2.x 中此功能被交给了 interval，下一个会介绍。
 * 但需要注意的是，timer 和 interval 均默认在新线程。
 */
public class TimerAction extends BaseAction {

    public TimerAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        updateUI("timer start : " + TimeUtil.getNowStrTime() + "\n");
        Log.e(TAG, "timer start : " + TimeUtil.getNowStrTime() + "\n");
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // timer 默认在新线程，所以需要切换回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        updateUI("timer :" + aLong + " at " + TimeUtil.getNowStrTime() + "\n");
                        Log.e(TAG, "timer :" + aLong + " at " + TimeUtil.getNowStrTime() + "\n");
                    }
                });
    }


}
