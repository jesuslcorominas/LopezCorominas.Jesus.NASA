package com.jesuslcorominas.nasa.app.model;

import java.io.Serializable;

/**
 * Representacion en la capa de presentacion del objeto {@link com.jesuslcorominas.nasa.model.entity.CameraModelEntity}
 *
 * @author Jesús López Corominas
 */
@SuppressWarnings("unused")
public class Camera implements Serializable {

    private Long id;

    private Long roverId;

    private String name;

    private String fullName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoverId() {
        return roverId;
    }

    public void setRoverId(Long roverId) {
        this.roverId = roverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
