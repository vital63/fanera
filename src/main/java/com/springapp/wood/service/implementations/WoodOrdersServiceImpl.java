package com.springapp.wood.service.implementations;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springapp.wood.service.interfaces.WoodOrdersService;
import com.springapp.wood.dao.interfaces.WoodOrdersDao;
import com.springapp.wood.domain.WoodOrder;

@Service
public class WoodOrdersServiceImpl implements WoodOrdersService{

    @Autowired
    private WoodOrdersDao ordersDao ;
    
    @Override
    @Transactional
    public void addOrder(WoodOrder order) {
        ordersDao.addOrder(order);
    }
    
    @Override
    @Transactional
    public int getAmountOfOrders() {
        return ordersDao.getAmountOfOrders();
    }
    
    @Override
    @Transactional
    public List<WoodOrder>  getListOrders () {
        return ordersDao.getListOrders();
    }
}