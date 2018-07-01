package com.nan.androidreview.designpattern.singleton.demo6;

import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过容器来实现
 * 特点：通过特定时机（例如程序初始化）将单例注入到容器当中，使用的时候通过key来获取；降低了耦合度，提高易用性
 */
public class Singleton {

    public static class SingletonManager {

        private SingletonManager() {

        }

        private static Map<String, Singleton> sSingletonMap = new HashMap<>();

        public static void register(String key, Singleton value) {
            if (!sSingletonMap.containsKey(key)) {
                sSingletonMap.put(key, value);
            }
        }

        public static void unregister(String key) {
            if (sSingletonMap.containsKey(key)) {
                sSingletonMap.remove(key);
            }
        }


        public static Singleton getSingleton(String key) {
            return sSingletonMap.get(key);
        }

    }

}
