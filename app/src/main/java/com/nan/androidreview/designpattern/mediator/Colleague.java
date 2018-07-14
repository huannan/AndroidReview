package com.nan.androidreview.designpattern.mediator;

/**
 * 同事类
 */
public abstract class Colleague {

    protected Mediator mMediator;

    public Colleague(Mediator mediator) {
        mMediator = mediator;
    }
}
