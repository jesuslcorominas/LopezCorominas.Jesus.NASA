package com.jesuslcorominas.nasa.data.database;

import java.util.concurrent.Callable;

/**
 * @author Jesús López Corominas
 */
public interface TransactionManager {

    <R> R callInTx(Callable<R> callable) throws Exception;
}
