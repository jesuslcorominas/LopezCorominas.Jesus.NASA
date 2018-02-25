package com.jesuslcorominas.nasa.model.entity;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Representacion de la entidad Photo
 *
 * @author Jesús López Corominas
 */
@SuppressWarnings("unused")
public class PhotoModelEntity implements Serializable {

    private Long id;

    private Integer sol;

    private CameraModelEntity camera;

    private String imgSrc;

    private DateTime earthDate;

    private RoverModelEntity rover;

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

    public CameraModelEntity getCamera() {
        return camera;
    }

    public void setCamera(CameraModelEntity camera) {
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

    public RoverModelEntity getRover() {
        return rover;
    }

    public void setRover(RoverModelEntity rover) {
        this.rover = rover;
    }
}
