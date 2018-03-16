package com.jesuslcorominas.nasa.data.net.dto;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Collection;

/**
 * Representacion de la {@link com.jesuslcorominas.nasa.common.model.Rover} en la api Rest
 *
 * @author Jesús López Corominas
 */
@SuppressWarnings("unused")
public class RoverDto implements Serializable {

    private Long id;

    private String name;

    @SerializedName("landing_date")
    private DateTime landingDate;

    @SerializedName("launch_date")
    private DateTime launchDate;

    private String status;

    @SerializedName("max_sol")
    private Integer maxSol;

    @SerializedName("max_date")
    private DateTime maxDate;

    @SerializedName("total_photos")
    private Integer totalPhotos;

    private Collection<CameraDto> cameras;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Collection<CameraDto> getCameras() {
        return cameras;
    }

    public void setCameras(Collection<CameraDto> cameras) {
        this.cameras = cameras;
    }
}
