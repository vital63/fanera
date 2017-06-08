package com.springapp.wood.service.interfaces;

import java.util.List;
import com.springapp.wood.domain.WoodOrder;

public interface WoodOrdersService 
{
    public void  addOrder (WoodOrder order);
    public int  getAmountOfOrders ();
    public List<WoodOrder>  getListOrders ();
}
