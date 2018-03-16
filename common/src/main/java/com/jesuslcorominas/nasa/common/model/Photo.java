package com.jesuslcorominas.nasa.common.model;

import org.joda.time.DateTime;

/**
 * POJO del objeto Photo
 *
 * @author Jesús López Corominas
 */
@SuppressWarnings("unused")
public class Photo extends AbstractBaseVo {

    private Integer sol;

    private String imgSrc;

    private DateTime earthDate;

    private Camera camera;

    private Rover rover;

    public Integer getSol() {
        return sol;
    }

    public void setSol(Integer sol) {
        this.sol = sol;
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

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }
}
