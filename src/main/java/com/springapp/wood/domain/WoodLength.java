/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springapp.wood.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


 ////     @Table(name="light_office", schema = "", catalog = "qjvmhoia_hmc")

////     "light/office/" +   


//@Entity
//@Table(name="light_office_power", schema = "", catalog = "light_test")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "WoodThickness.findAll", query = "SELECT l FROM WoodThickness l"),
//    @NamedQuery(name = "WoodThickness.findByPower", query = "SELECT l FROM WoodThickness l WHERE l.thickness = :thickness"),
//    @NamedQuery(name = "WoodThickness.findByNum", query = "SELECT l FROM WoodThickness l WHERE l.num = :num")})
public class WoodLength implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "length")
    private Integer length;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num")
    private int num;

    public WoodLength() {
    }

    public WoodLength(Integer length) {
        this.length = length;
    }

    public WoodLength(Integer length, int num) {
        this.length = length;
        this.num = num;
    }

    public Integer getPower() {
        return length;
    }

    public void setPower(Integer power) {
        this.length = power;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (length != null ? length.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WoodLength)) {
            return false;
        }
        WoodLength other = (WoodLength) object;
        if ((this.length == null && other.length != null) || (this.length != null && !this.length.equals(other.length))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.springapp.wood.domain.WoodLength[ length=" + length + " ]";
    }
    
}
