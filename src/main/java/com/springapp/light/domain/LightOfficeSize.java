/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springapp.light.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Home
 */
@Entity
@Table(name="light_office_size", schema = "", catalog = "light_test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LightOfficeSize.findAll", query = "SELECT l FROM LightOfficeSize l"),
    @NamedQuery(name = "LightOfficeSize.findBySize", query = "SELECT l FROM LightOfficeSize l WHERE l.size = :size"),
    @NamedQuery(name = "LightOfficeSize.findByNum", query = "SELECT l FROM LightOfficeSize l WHERE l.num = :num")})
public class LightOfficeSize implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "size")
    private String size;
    
    @Column(name = "num")
    private Integer num;

    public LightOfficeSize() {
    }

    public LightOfficeSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (size != null ? size.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LightOfficeSize)) {
            return false;
        }
        LightOfficeSize other = (LightOfficeSize) object;
        if ((this.size == null && other.size != null) || (this.size != null && !this.size.equals(other.size))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.springapp.light.domain.LightOfficeSize[ size=" + size + " ]";
    }
    
}
