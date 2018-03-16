package com.jesuslcorominas.nasa.data.net.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Representacion de la {@link com.jesuslcorominas.nasa.common.model.Camera} en la api Rest
 *
 * @author Jesús López Corominas
 */
@SuppressWarnings("unused")
public class CameraDto implements Serializable {

    private Long id;

    @SerializedName("rover_id")
    private Long roverId;

    private String name;

    @SerializedName("full_name")
    private String fullName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoverId() {
        return roverId;
    }

    public void setRoverId(Long roverId) {
        this.roverId = roverId;
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
}
