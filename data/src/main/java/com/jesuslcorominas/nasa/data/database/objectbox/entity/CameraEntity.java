package com.jesuslcorominas.nasa.data.database.objectbox.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

/**
 * @author Jesús López Corominas
 */
@Entity
public class CameraEntity extends AbstractBaseEntity {

    private String name;

    private String fullName;

    private ToOne<RoverEntity> roverRelation;

    private ToMany<PhotoEntity> photosRelation;

    // =====================================
    // Getters y Setters
    // =====================================
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

    public ToOne<RoverEntity> getRoverRelation() {
        return roverRelation;
    }

    public void setRoverRelation(ToOne<RoverEntity> roverRelation) {
        this.roverRelation = roverRelation;
    }

    public RoverEntity getRover() {
        return roverRelation.getTarget();
    }

    public void setRover(RoverEntity rover) {
        roverRelation.setAndPutTarget(rover);
    }

    public void setRoverId(Long roverId) {
        this.roverRelation.setTargetId(roverId);
    }

    public ToMany<PhotoEntity> getPhotosRelation() {
        return photosRelation;
    }

    public void setPhotosRelation(ToMany<PhotoEntity> photosRelation) {
        this.photosRelation = photosRelation;
    }
}
