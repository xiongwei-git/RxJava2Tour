package com.tedxiong.rxjava2.action;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * buffer 操作符接受两个参数，buffer(count,skip)，
 * 作用是将 Observable 中的数据按 skip (步长) 分成最大不超过 count 的 buffer ，
 * 然后生成一个  Observable 。
 */
public class BufferAction extends BaseAction {

    public BufferAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(@NonNull List<Integer> integers) throws Exception {
                        updateUI("buffer size : " + integers.size() + "\n");
                        Log.e(TAG, "buffer size : " + integers.size() + "\n");
                        updateUI("buffer value : ");
                        Log.e(TAG, "buffer value : " );
                        for (Integer i : integers) {
                            updateUI(i + "");
                            Log.e(TAG, i + "");
                        }
                        updateUI("\n");
                        Log.e(TAG, "\n");
                    }
                });
    }


}
