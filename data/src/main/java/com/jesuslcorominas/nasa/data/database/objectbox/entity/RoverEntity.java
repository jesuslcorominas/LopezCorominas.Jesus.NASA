package com.jesuslcorominas.nasa.data.database.objectbox.entity;

import com.jesuslcorominas.nasa.data.database.objectbox.converter.DateConverter;

import org.joda.time.DateTime;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.relation.ToMany;

/**
 * @author Jesús López Corominas
 */
@Entity
public class RoverEntity extends AbstractBaseEntity {

    private String name;

    @Convert(converter = DateConverter.class, dbType = Long.class)
    private DateTime landingDate;

    @Convert(converter = DateConverter.class, dbType = Long.class)
    private DateTime launchDate;

    private String status;

    private Integer maxSol;

    @Convert(converter = DateConverter.class, dbType = Long.class)
    private DateTime maxDate;

    private Integer totalPhotos;

    private ToMany<CameraEntity> camerasRelation;

    // =====================================
    // Getters y Setters
    // =====================================
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

    public ToMany<CameraEntity> getCamerasRelation() {
        return camerasRelation;
    }

    public void setCamerasRelation(ToMany<CameraEntity> camerasRelation) {
        this.camerasRelation = camerasRelation;
    }
}
