package com.nan.androidreview.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 树枝构件：树枝对象，它的作用是组合树枝节点和叶子节点形成一个树形结构。
 * 例子：文件夹
 */
public class Composite extends Component {

    private List<Component> mChildrens = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void doSomething() {
        System.out.println("文件夹：" + this.mName);
    }

    @Override
    public void addChildren(Component component) {
        this.mChildrens.add(component);
    }

    @Override
    public void removeChildren(Component component) {
        this.mChildrens.remove(component);
    }

    @Override
    public List<Component> getChildrens() {
        return this.mChildrens;
    }
}
