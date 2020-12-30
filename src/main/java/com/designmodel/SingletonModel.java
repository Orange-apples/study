package com.designmodel;

import ch.qos.logback.core.net.SyslogOutputStream;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class SingletonModel {
    //1.饿汉式
    private SingletonModel() {
    }

    private static final SingletonModel model = new SingletonModel();

    public static SingletonModel getInstance() {
        return model;
    }

    //2.懒汉式
    private static SingletonModel model2;

    public static SingletonModel genInstance2() {
        if (model2 == null) {
//            synchronized (SingletonModel.class) {
//                if (model2 == null) {
                    model2 = new SingletonModel();
//                }
//            }
        }
        System.out.println(model2);
        return model2;
    }

    enum S {
        INSTANCE;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ArrayList<Callable<Integer>> callables = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            callables.add(() -> {
                SingletonModel.genInstance2();
                return 1;
            });
        }
        executorService.invokeAll(callables,1,TimeUnit.SECONDS);
    }
}





