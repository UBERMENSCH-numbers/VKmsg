package com.example.user.vkmsg;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class RxBus {

    public RxBus() {
    }

    private PublishSubject<Object> bus = PublishSubject.create();

    public void send(Object o) {
        Log.e("ONNEXT", o.getClass().getSimpleName());
        bus.onNext(o);
    }

    public void unsubscribe () {
        bus.unsubscribeOn(Schedulers.io());
    }

    public void onComplete () {
        bus.onComplete();
    }

    public Observable<Object> toObservable() {
        return bus;
    }

}
