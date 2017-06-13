package com.springapp.wood.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="order", schema = "", catalog = "fanera")
public class WoodOrder implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @NotEmpty 
    @Column(name = "username")
    private String username;
    
    @Lob
    @Column(name = "request")
    private String request;
    
    @Column(name = "datatime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datatime;
    
    @Column(name = "status")
    private String status;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<ShoppingCartItem> cartItems = new HashSet<>();
    
    public WoodOrder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Date getDatatime() {
        return datatime;
    }

    public void setDatatime(Date datatime) {
        this.datatime = datatime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<ShoppingCartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<ShoppingCartItem> cartItems) {
        this.cartItems = cartItems;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WoodOrder)) {
            return false;
        }
        WoodOrder other = (WoodOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", request=" + request + ", datatime=" + datatime + ", status=" + status + '}';
    }
}
