package com.jesuslcorominas.nasa.data.net.dto;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Representacion de la {@link com.jesuslcorominas.nasa.model.entity.PhotoModelEntity} en la api Rest
 *
 * @author Jesús López Corominas
 */
@SuppressWarnings("unused")
public class PhotoDto implements Serializable {

    private Long id;

    private Integer sol;

    private CameraDto camera;

    @SerializedName("img_src")
    private String imgSrc;

    @SerializedName("earth_date")
    private DateTime earthDate;

    private RoverDto rover;

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

    public CameraDto getCamera() {
        return camera;
    }

    public void setCamera(CameraDto camera) {
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

    public RoverDto getRover() {
        return rover;
    }

    public void setRover(RoverDto rover) {
        this.rover = rover;
    }
}
