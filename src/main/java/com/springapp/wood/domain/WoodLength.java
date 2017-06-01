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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="wood_length", schema = "", catalog = "fanera")
@XmlRootElement
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
    private Integer num;

    public WoodLength() {
    }

    public WoodLength(Integer length) {
        this.length = length;
    }

    public WoodLength(Integer length, Integer num) {
        this.length = length;
        this.num = num;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public int hashCode() {
        return length != null ? length.hashCode() : 0;
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
