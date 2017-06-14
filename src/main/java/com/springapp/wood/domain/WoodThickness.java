/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springapp.wood.domain;

import com.springapp.wood.util.Utils;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="wood_thickness", schema = "", catalog = Utils.SCHEMA_NAME)
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "WoodThickness.findAll", query = "SELECT l FROM WoodThickness l"),
//    @NamedQuery(name = "WoodThickness.findByPower", query = "SELECT l FROM WoodThickness l WHERE l.thickness = :thickness"),
//    @NamedQuery(name = "WoodThickness.findByNum", query = "SELECT l FROM WoodThickness l WHERE l.num = :num")})
public class WoodThickness implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "thickness")
    private Integer thickness;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num")
    private Long num;

    public WoodThickness() {
    }

    public WoodThickness(Integer power) {
        this.thickness = power;
    }

    public WoodThickness(Integer power, Long num) {
        this.thickness = power;
        this.num = num;
    }

    public Integer getThickness() {
        return thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    @Override
    public int hashCode() {
        return thickness != null ? thickness.hashCode() : 0;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WoodThickness)) {
            return false;
        }
        WoodThickness other = (WoodThickness) object;
        if ((this.thickness == null && other.thickness != null) || (this.thickness != null && !this.thickness.equals(other.thickness))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.springapp.wood.domain.WoodThickness[ thickness=" + thickness + " ]";
    }
    
}
