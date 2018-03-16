package com.jesuslcorominas.nasa.data.database.objectbox;

import com.jesuslcorominas.nasa.data.database.TransactionManager;

import java.util.concurrent.Callable;

import io.objectbox.BoxStore;

/**
 * @author Jesús López Corominas
 */
public class ObjectBoxTransactionManager implements TransactionManager {

    private final BoxStore boxStore;

    public ObjectBoxTransactionManager(BoxStore boxStore) {
        this.boxStore = boxStore;
    }

    @Override
    public <R> R callInTx(Callable<R> callable) throws Exception {
        return boxStore.callInTx(callable);
    }
}
