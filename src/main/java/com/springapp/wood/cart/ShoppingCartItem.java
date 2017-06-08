package com.springapp.wood.cart;

import com.springapp.wood.domain.Wood;

public class ShoppingCartItem
{
    private Wood wood;
    private short quantity;
    
    public ShoppingCartItem(Wood wood) {
        this.wood =  wood;
        quantity = 1;
    }
    
    public Wood getWood() {
        return  wood;
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