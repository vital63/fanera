package com.springapp.wood.dao.implementations;

import com.springapp.wood.dao.interfaces.WoodOrdersDao;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.springapp.wood.domain.WoodOrder;

@Repository
public class WoodOrdersDaoImpl implements WoodOrdersDao
{
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @SuppressWarnings("unchecked")
    public void addOrder(WoodOrder order) {
        sessionFactory.getCurrentSession().saveOrUpdate(order);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public int getAmountOfOrders() {
        return getListOrders().size();
    }
    
    @Override  
    @SuppressWarnings("unchecked")
    public List<WoodOrder> getListOrders(){
        return (List<WoodOrder> ) sessionFactory.getCurrentSession().createQuery("from WoodOrder where status='in'").list();
    }
}
