package com.jesuslcorominas.nasa.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO del objeto Camera
 *
 * @author Jesús López Corominas
 */
@SuppressWarnings("unused")
public class Camera extends AbstractBaseVo {

    private Rover rover;

    private String name;

    private String fullName;

    private List<Photo> photos = new ArrayList<>();

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
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

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
