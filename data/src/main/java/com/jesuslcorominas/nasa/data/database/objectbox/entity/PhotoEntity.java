package com.jesuslcorominas.nasa.data.database.objectbox.entity;

import com.jesuslcorominas.nasa.data.database.objectbox.converter.DateConverter;

import org.joda.time.DateTime;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.relation.ToOne;


/**
 * @author Jesús López Corominas
 */
@Entity
public class PhotoEntity extends AbstractBaseEntity {
    private Integer sol;

    private String imgSrc;

    @Convert(converter = DateConverter.class, dbType = Long.class)
    private DateTime earthDate;

    private ToOne<CameraEntity> cameraRelation;

    private ToOne<RoverEntity> roverRelation;

    // =====================================
    // Getters y Setters
    // =====================================
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

    public ToOne<CameraEntity> getCameraRelation() {
        return cameraRelation;
    }

    public void setCameraRelation(ToOne<CameraEntity> cameraRelation) {
        this.cameraRelation = cameraRelation;
    }

    public void setCamera(CameraEntity camera) {
        this.cameraRelation.setAndPutTarget(camera);
    }

    public void setCameraId(Long idCamera) {
        this.cameraRelation.setTargetId(idCamera);
    }

    public CameraEntity getCamera() {
        return cameraRelation.getTarget();
    }

    public ToOne<RoverEntity> getRoverRelation() {
        return roverRelation;
    }

    public void setRoverRelation(ToOne<RoverEntity> roverRelation) {
        this.roverRelation = roverRelation;
    }

    public void setRover(RoverEntity rover) {
        this.roverRelation.setAndPutTarget(rover);
    }

    public void setRoverId(Long idRover) {
        this.roverRelation.setTargetId(idRover);
    }

    public RoverEntity getRover() {
        return roverRelation.getTarget();
    }
}
