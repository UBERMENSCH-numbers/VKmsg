package com.example.user.vkmsg.mvp;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.ReplaySubject;

public class RxBus {

    public RxBus() {
    }

    private BehaviorSubject<Object> bus = BehaviorSubject.create();

    public void send(Object o) {
        bus.onNext(o);
    }

    public void complete() {
        bus.onComplete();
    }

    public Observable<Object> toObservable() {
        return bus;
    }

}
