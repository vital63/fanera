package com.springapp.wood.cart;

import com.springapp.wood.domain.ShoppingCartItem;
import com.springapp.wood.domain.Wood;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart 
{
    private final List<ShoppingCartItem> items;
    private int numberOfItems;
    private double total;
 
    public ShoppingCart() {
        items = new ArrayList<>();
        numberOfItems = 0;
        total = 0;
    }
  
    public synchronized void addItem(Wood wood)
    {
        for (ShoppingCartItem scItem : items) 
            if (scItem.getWood().getId().equals(wood.getId())) 
            {
                scItem.incrementQuantity();
                return;
            }
        
        ShoppingCartItem scItem = new ShoppingCartItem(wood);
        items.add(scItem);
    }


    public synchronized void update(Wood wood, String quantity) 
    {
        short qty = -1;
        qty = Short.parseShort(quantity);
        if (qty < 0) 
            return;

        ShoppingCartItem itemToRemove = null;
        for (ShoppingCartItem scItem : items) 
        {
            if (scItem.getWood().getId().equals(wood.getId())) 
            {
                if (qty > 0) 
                    scItem.setQuantity(qty); // set item quantity to new value
                else 
                    itemToRemove = scItem; // if quantity equals 0, save item and break
                
                break;
            }
        }

        if (itemToRemove != null) 
            items.remove(itemToRemove);
    }
    
    public synchronized List<ShoppingCartItem> getItems() {
        return items;
    }
    
    public synchronized int getNumberOfItems() {
        numberOfItems = 0;
        for (ShoppingCartItem scItem : items) 
            numberOfItems += scItem.getQuantity();
        
        return numberOfItems;
    }

    public synchronized double getSubtotal() {
        double amount = 0;
        for (ShoppingCartItem scItem : items) 
            amount += scItem.getTotal();
        
        return amount;
    }

    public synchronized void calculateTotal() {
        total = getSubtotal();
    }

    public synchronized double getTotal() {
        return total;
    }
    
    public synchronized void clear() {
        items.clear();
        numberOfItems = 0;
        total = 0;
    }
    
    @Override
    public String toString() {
        return "ShoppingCart{" + "items=" + items + ", numberOfItems=" + numberOfItems + ", total=" + total + '}';
    }
}
