package com.jesuslcorominas.nasa.common.model;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO del objeto Rover
 *
 * @author Jesús López Corominas
 */
@SuppressWarnings("unused")
public class Rover extends AbstractBaseVo {

    private String name;

    private DateTime landingDate;

    private DateTime launchDate;

    private String status;

    private Integer maxSol;

    private DateTime maxDate;

    private Integer totalPhotos;

    private List<Camera> cameras = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getLandingDate() {
        return landingDate;
    }

    public void setLandingDate(DateTime landingDate) {
        this.landingDate = landingDate;
    }

    public DateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(DateTime launchDate) {
        this.launchDate = launchDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMaxSol() {
        return maxSol;
    }

    public void setMaxSol(Integer maxSol) {
        this.maxSol = maxSol;
    }

    public DateTime getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(DateTime maxDate) {
        this.maxDate = maxDate;
    }

    public Integer getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(Integer totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    public List<Camera> getCameras() {
        return cameras;
    }

    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }

}
