package com.jesuslcorominas.nasa.app.model;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Representacion en la capa de presentacion del objeto {@link com.jesuslcorominas.nasa.model.entity.PhotoModelEntity}
 *
 * @author Jesús López Corominas
 */
@SuppressWarnings("unused")
public class Photo implements Serializable {

    private Long id;

    private Integer sol;

    private Camera camera;

    private String imgSrc;

    private DateTime earthDate;

    private Rover rover;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSol() {
        return sol;
    }

    public void setSol(Integer sol) {
        this.sol = sol;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public DateTime getEarthDate() {
        return earthDate;
    }

    public void setEarthDate(DateTime earthDate) {
        this.earthDate = earthDate;
    }

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }

}
