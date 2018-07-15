package com.nan.androidreview.designpattern.composite;

import java.util.List;

/**
 * 叶子构件：叶子对象，其下再也没有其他的分支，也就是遍历的最小单位。
 * 例子：文件
 */
public class Leaf extends Component {

    public Leaf(String name) {
        super(name);
    }

    @Override
    public void doSomething() {
        System.out.println("文件：" + this.mName);
    }

    @Override
    public void addChildren(Component component) {
        throw new UnsupportedOperationException("叶子结点不支持该操作");
    }

    @Override
    public void removeChildren(Component component) {
        throw new UnsupportedOperationException("叶子结点不支持该操作");
    }

    @Override
    public List<Component> getChildrens() {
        throw new UnsupportedOperationException("叶子结点不支持该操作");
    }
}
