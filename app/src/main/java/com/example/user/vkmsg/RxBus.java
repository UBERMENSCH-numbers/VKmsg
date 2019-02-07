package com.example.user.vkmsg;

import android.util.Log;

import com.example.user.vkmsg.POJO.RecyclerItem;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

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
