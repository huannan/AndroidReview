package com.nan.androidreview.designpattern.observer;

import java.util.Vector;

public abstract class Subject {

    private Vector<Observer> mObservers = new Vector<>();

    public void addObserver(Observer o) {
        this.mObservers.add(o);
    }

    public void removeObserver(Observer o) {
        this.mObservers.remove(o);
    }

    public void notifyObservers() {
        for (Observer o : this.mObservers) {
            o.update();
        }
    }

}
