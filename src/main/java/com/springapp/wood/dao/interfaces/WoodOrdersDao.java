package com.springapp.wood.dao.interfaces;

import com.springapp.wood.domain.ShoppingCartItem;
import java.util.List;
import com.springapp.wood.domain.WoodOrder;


public interface WoodOrdersDao {
    public void addOrder(WoodOrder order);
    public int getAmountOfOrders();
    public List<WoodOrder> getListOrders();
}
