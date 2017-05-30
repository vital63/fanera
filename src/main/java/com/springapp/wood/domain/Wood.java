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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="wood", schema = "", catalog = "fanera")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "LightOffice.findAll",                       query = "SELECT l FROM LightOffice l"),
//    @NamedQuery(name = "LightOffice.findById",                      query = "SELECT l FROM LightOffice l WHERE l.id = :id"),
//    @NamedQuery(name = "LightOffice.findByType",                    query = "SELECT l FROM LightOffice l WHERE l.type = :type"),
//    @NamedQuery(name = "LightOffice.findByModel",                   query = "SELECT l FROM LightOffice l WHERE l.model = :model"),
//    @NamedQuery(name = "LightOffice.findByUrl",                     query = "SELECT l FROM LightOffice l WHERE l.url = :url"),
//    @NamedQuery(name = "LightOffice.findBySize",                    query = "SELECT l FROM LightOffice l WHERE l.size = :size"),
//    @NamedQuery(name = "LightOffice.findByPrice",                   query = "SELECT l FROM LightOffice l WHERE l.price = :price"),
//    @NamedQuery(name = "LightOffice.findByPhoto1",                  query = "SELECT l FROM LightOffice l WHERE l.photo1 = :photo1"),
//    @NamedQuery(name = "LightOffice.findByPhoto2",                  query = "SELECT l FROM LightOffice l WHERE l.photo2 = :photo2"),
//    @NamedQuery(name = "LightOffice.findByPhoto3",                  query = "SELECT l FROM LightOffice l WHERE l.photo3 = :photo3"),
public class Wood implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Size(max = 255)
    @Column(name = "id")
    private String id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 245)
    @Column(name = "type")
    private String type;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "model")
    private String model;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "url")
    private String url;

    @Basic(optional = false)
    @NotNull
    @Column(name = "length")
    private int length;

    @Basic(optional = false)
    @NotNull
    @Column(name = "width")
    private int width;

    @Basic(optional = false)
    @NotNull
    @Column(name = "thickness")
    private int thickness;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "laminated_color")
    private String laminatedColor;

    @Column(name = "price")
    private Double price;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_boards_in_package")
    private int numBoardsInPackage;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_packages_in_vehicle")
    private int numPackagesInVehicle;

    @Size(max = 245)
    @Column(name = "photo1")
    private String photo1;

    @Size(max = 245)
    @Column(name = "photo2")
    private String photo2;

    @Size(max = 245)
    @Column(name = "photo3")
    private String photo3;

    @Size(max = 245)
    @Column(name = "photo4")
    private String photo4;

    @Size(max = 245)
    @Column(name = "photo5")
    private String photo5;

    @Lob
    @Size(max = 65535)
    @Column(name = "description_en")
    private String descriptionEn;

    @Lob
    @Size(max = 65535)
    @Column(name = "description_ru")
    private String descriptionRu;

    public Wood() {
    }

    public Wood(String model) {
        this.model = model;
    }

    public Wood(String type, String model, String url, int length, int width, int thickness, String laminatedColor) {
        this.type = type;
        this.model = model;
        this.url = url;
        this.length = length;
        this.width = width;
        this.thickness = thickness;
        this.laminatedColor = laminatedColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhoto1() {
        return "wood/office/" +   photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return "wood/office/" +   photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return "wood/office/" +   photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto4() {
        return "light/office/" + photo4;
    }

    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }

    public String getPhoto5() {
        return "light/office/" + photo5;
    }

    public void setPhoto5(String photo5) {
        this.photo5 = photo5;
    }
    
    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public String getLaminatedColor() {
        return laminatedColor;
    }

    public void setLaminatedColor(String laminatedColor) {
        this.laminatedColor = laminatedColor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public int getNumBoardsInPackage() {
        return numBoardsInPackage;
    }

    public void setNumBoardsInPackage(int numBoardsInPackage) {
        this.numBoardsInPackage = numBoardsInPackage;
    }

    public int getNumPackagesInVehicle() {
        return numPackagesInVehicle;
    }

    public void setNumPackagesInVehicle(int numPackagesInVehicle) {
        this.numPackagesInVehicle = numPackagesInVehicle;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (model != null ? model.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wood)) {
            return false;
        }
        Wood other = (Wood) object;
        if ((this.model == null && other.model != null) || (this.model != null && !this.model.equals(other.model))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.springapp.wood.domain.Wood[ model=" + model + " ]";
    }
}