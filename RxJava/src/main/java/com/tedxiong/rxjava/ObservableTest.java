package com.tedxiong.rxjava;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava
 */
public class ObservableTest {



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

        mObservable.subscribe(getSubscriber());

        String[] words = {"Hello", "Hi", "Ted"};
        Observable<String> fromObservable = Observable.from(words);
        fromObservable.subscribe(getSubscriber());

        Observable<String> justObservable = Observable.just("Hello","Hi","Wei");
        justObservable.subscribe(getSubscriber());



    }

    private static Subscriber<String> getSubscriber(){
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext---" + s);
            }
        };
    }
}
