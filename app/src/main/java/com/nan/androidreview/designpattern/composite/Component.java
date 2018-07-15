package com.nan.androidreview.designpattern.composite;

import java.util.List;

/**
 * 抽象构件角色:定义参加组合对象的共有方法和属性，可以定义一些默认的行为或属性
 * 例子：文件夹、文件的抽象
 */
public abstract class Component {

    /**
     * 结点名。例子：文件夹/文件的名字
     */
    protected String mName;

    public Component(String name) {
        mName = name;
    }

    /**
     * 业务逻辑
     */
    public abstract void doSomething();

    /**
     * 添加结点。例子：添加文件夹/文件
     * @param component
     */
    public abstract void addChildren(Component component);

    /**
     * 删除结点。例子：删除文件夹/文件
     * @param component
     */
    public abstract void removeChildren(Component component);

    /**
     * 获取所有子结点。例子：获取所有子文件夹/文件
     * @return
     */
    public abstract List<Component> getChildrens();

}
