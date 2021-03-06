package com.springapp.wood.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue(value = "LPB")
public class LaminatedParticleBoard extends Wood {
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "laminated_color")
    private String laminatedColor;

    public LaminatedParticleBoard() {
    }

    public LaminatedParticleBoard(String laminatedColor, String id, String url, int length, int width, int thickness) {
        super(id, url, length, width, thickness);
        this.laminatedColor = laminatedColor;
    }

    public String getLaminatedColor() {
        return laminatedColor;
    }

    public void setLaminatedColor(String laminatedColor) {
        this.laminatedColor = laminatedColor;
    }

    public static final String TYPE_NAME = "Laminated Particle Board";
    
    @Override
    public String getType() {
        return TYPE_NAME;
    }
}
