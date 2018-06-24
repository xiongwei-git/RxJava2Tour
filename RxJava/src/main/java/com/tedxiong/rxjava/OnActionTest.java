package com.tedxiong.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava
 */
public class OnActionTest {


    public static void main(String[] args) {


        Observable<String> mObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });

        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {
                System.out.println("onCall---" + s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                System.out.println("onCompleted");
            }
        };

        mObservable.subscribe(onNextAction);

        Util.printLine();

        mObservable.subscribe(onNextAction, onErrorAction);

        Util.printLine();

        mObservable.subscribe(onNextAction, onErrorAction, onCompletedAction);

    }


}
