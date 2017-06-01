package com.springapp.wood.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue(value = "RP")
public class RawPlywood extends Wood {
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "grade")
    private String grade;
    
    @Column(name = "sanded")
    private Boolean isSanded;

    @Column(name = "water_resistance")
    private Boolean isWaterResistance;

    public RawPlywood() {
    }

    public RawPlywood(String grade, Boolean isSanded, Boolean isWaterResistance, String id, String url, int length, int width, int thickness) {
        super(id, url, length, width, thickness);
        this.grade = grade;
        this.isSanded = isSanded;
        this.isWaterResistance = isWaterResistance;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Boolean getIsSanded() {
        return isSanded;
    }

    public void setIsSanded(Boolean isSanded) {
        this.isSanded = isSanded;
    }

    public Boolean getIsWaterResistance() {
        return isWaterResistance;
    }

    public void setIsWaterResistance(Boolean isWaterResistance) {
        this.isWaterResistance = isWaterResistance;
    }

    @Override
    public String getType() {
        return "Raw Plywood";
    }
}
