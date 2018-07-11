package com.nan.androidreview.designpattern.bridge;

/**
 * Abstraction是抽象化角色，保持实现化角色的引用
 * 这里的Abstraction代表咖啡
 */
public abstract class Abstraction {

    //抽象化角色，保持实现化角色的引用，是桥接模式的核心
    //这里的实现化角色代表调料
    private Implementor mImplementor;

    public Abstraction(Implementor implementor) {
        mImplementor = implementor;
    }

    public Implementor getImplementor() {
        return mImplementor;
    }

    /**
     * 冲咖啡，具体实现由子类实现
     */
    public abstract void operator();

}
