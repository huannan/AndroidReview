package com.nan.androidreview.designpattern.decorator;

public abstract class Decorator extends Component {

    private Component mComponent;

    public Decorator(Component component) {
        mComponent = component;
    }

    @Override
    public void operate() {
        //委托给被修饰者去执行对应的方法
        this.mComponent.operate();
    }
}
