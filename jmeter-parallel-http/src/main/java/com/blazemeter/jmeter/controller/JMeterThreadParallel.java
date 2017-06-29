package com.blazemeter.jmeter.controller;

import org.apache.jmeter.threads.*;
import org.apache.jorphan.collections.HashTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;

public class JMeterThreadParallel extends JMeterThread {
    private static final Logger log = LoggerFactory.getLogger(ParallelSampler.class);

    public JMeterThreadParallel(HashTree test, JMeterThreadMonitor monitor, ListenerNotifier notifier) {
        super(test, monitor, notifier);
        setThreadGroup(new DummyThreadGroup());
        try {
            copyCompilerFromParent();
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    protected void copyCompilerFromParent() throws IllegalAccessException, NoSuchFieldException {
        Field field = JMeterThread.class.getDeclaredField("compiler");
        field.setAccessible(true);
        JMeterThread parentThread = JMeterContextService.getContext().getThread();
        TestCompiler parentCompiler = (TestCompiler) field.get(parentThread);
        TestCompiler cmp = cloneTestCompiler(parentCompiler);
        field.set(this, cmp);
    }

    private TestCompiler cloneTestCompiler(TestCompiler parent) throws NoSuchFieldException, IllegalAccessException {
        TestCompiler cloned = new TestCompiler(new HashTree());

        Field samplerConfigMap = TestCompiler.class.getDeclaredField("samplerConfigMap");
        samplerConfigMap.setAccessible(true);
        samplerConfigMap.set(cloned, ((HashMap) (samplerConfigMap.get(parent))).clone());

        Field transactionControllerConfigMap = TestCompiler.class.getDeclaredField("transactionControllerConfigMap");
        transactionControllerConfigMap.setAccessible(true);
        transactionControllerConfigMap.set(cloned, ((HashMap) (transactionControllerConfigMap.get(parent))).clone());

        return cloned;
    }
}
