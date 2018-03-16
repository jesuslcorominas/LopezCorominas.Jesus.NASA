package com.jesuslcorominas.nasa.common.model;

import java.io.Serializable;

/**
 * @author Jesús López Corominas
 */
public abstract class AbstractBaseVo implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
