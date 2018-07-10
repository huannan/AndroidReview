package com.nan.androidreview.designpattern.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {

    //池容器
    private static Map<String, Flyweight> sPool = new HashMap<>();

    /**
     * 通过外部状态结合池来管理对象
     * @param extrinsic
     * @return
     */
    public static Flyweight getFlyweight(String extrinsic) {

        Flyweight result = null;

        if (sPool.containsKey(extrinsic)) {
            result = sPool.get(extrinsic);
        } else {
            result = new ConcreteFlyweight(extrinsic);
            sPool.put(extrinsic, result);
        }

        return result;
    }

}
