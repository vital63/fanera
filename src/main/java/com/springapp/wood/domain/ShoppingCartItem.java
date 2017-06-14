package com.springapp.wood.domain;

import com.springapp.wood.util.Utils;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_item", schema = "", catalog = Utils.SCHEMA_NAME)
public class ShoppingCartItem
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne
    private Wood wood;
    
    @ManyToOne
    private WoodOrder order;
    
    @Column(name = "quantity")
    private short quantity;

    public ShoppingCartItem() {
    }
    
    public ShoppingCartItem(Wood wood) {
        this.wood =  wood;
        quantity = 1;
    }
    
    public Wood getWood() {
        return  wood;
    }

    public void setWood(Wood wood) {
        this.wood = wood;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WoodOrder getOrder() {
        return order;
    }

    public void setOrder(WoodOrder order) {
        this.order = order;
    }

    public short getQuantity() {
        return quantity;
    }
    
    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }
    
    public void incrementQuantity() {
        quantity++;
    }
    
    public void decrementQuantity() {
        quantity--;
    }
    
    public double getTotal(){
        return this.getQuantity() * wood.getPrice();
    }
}