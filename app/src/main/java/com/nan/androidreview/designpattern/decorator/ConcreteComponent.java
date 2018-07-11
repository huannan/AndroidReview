package com.nan.androidreview.designpattern.decorator;

public class ConcreteComponent extends Component {

    @Override
    public void operate() {
        System.out.println("operate");
    }
}
