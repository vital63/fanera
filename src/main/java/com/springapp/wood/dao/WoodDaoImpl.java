/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springapp.wood.dao;


import com.springapp.wood.domain.Wood;
import com.springapp.wood.domain.WoodLength;
import com.springapp.wood.domain.WoodWidth;
import com.springapp.wood.domain.WoodThickness;
import com.springapp.wood.domain.WoodType;
import java.util.Collections;
import java.util.HashSet;


import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;


@Repository 
public class WoodDaoImpl implements WoodDao{

    @Autowired private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public Wood getWoodByUrl(String url) { 
        Query query = sessionFactory.getCurrentSession().createQuery("from Wood where url='" + url + "'" );
        return (Wood) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Wood getWoodById(String id) { 
        Query query = sessionFactory.getCurrentSession().createQuery("from Wood where id='" + id+ "'" );
        return (Wood) query.uniqueResult();
    }
    
    @Override 
    @SuppressWarnings("unchecked")
    public void saveWood(Wood wood) { 
        sessionFactory.getCurrentSession().saveOrUpdate(wood);
    }
    
    @Override 
    @SuppressWarnings("unchecked")
    public List<WoodLength> getListWoodLength(){ 
        return (List<WoodLength> )sessionFactory.getCurrentSession().createCriteria(WoodLength.class)
                                                    .addOrder(Order.desc("length")) .list();  
    }
 
    @Override 
    @SuppressWarnings("unchecked")
    public List<WoodWidth> getListWoodWidth(){ 
        return (List<WoodWidth> )sessionFactory.getCurrentSession().createCriteria(WoodWidth.class)
                                                    .addOrder(Order.desc("width")) .list();  
    }
        
    @Override 
    @SuppressWarnings("unchecked")
    public List<WoodThickness> getListWoodThickness(){ 
        return (List<WoodThickness> )sessionFactory.getCurrentSession().createCriteria(WoodThickness.class)
                                                    .addOrder(Order.desc("thickness")) .list();  
    }
        
    @Override
    @SuppressWarnings("unchecked")
    public List<WoodType> getListWoodType(){ 
//       return sessionFactory.getCurrentSession().createCriteria(WoodPower.class).addOrder(Order.desc("power")).list();
       return (List<WoodType> )sessionFactory.getCurrentSession().createCriteria(WoodType.class)
               .addOrder(Order.desc("type")).list();
//        return  (List<Vmc> ) sessionFactory.getCurrentSession().createCriteria(Vmc.class).list();
    }
    
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Wood> getListWood() {
        return sessionFactory.getCurrentSession().createCriteria(Wood.class).addOrder(Order.desc("id")).list();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Wood> getListWood(String[] lengths, String[] widths, String[] thicknesses, String type)
    {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Wood.class);
//        if (emergency != null)  crit.add(Restrictions.ne("luminousFluxEmergency", 0));
//        if (arrPowers != null && !arrPowers[0].equals(""))   crit.add(Restrictions.in("power", arrPowers));
//        if (size != null && !size.equals(""))  crit.add(Restrictions.eq("size", size));
//        if (type != null && !type.equals(""))  crit.add(Restrictions.eq("type", type));
        return crit.list();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Wood> getListWoodByIds(String[] idsArr) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Wood.class);
        if (idsArr != null && !idsArr[0].equals("")) {
            criteria.add(Restrictions.in("id", idsArr));
        }
        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Wood> getListWoodFromSearch(String word) {
        return sessionFactory.getCurrentSession().createQuery("from Wood where id LIKE '%" + word + "%' ORDER BY id DESC").list();
    }

    
    @Override
    @SuppressWarnings("unchecked")
    public void renewWoodLength(){
        renewWoodFilter(WoodLength.class.getSimpleName(), "length");
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void renewWoodWidth(){
        renewWoodFilter(WoodWidth.class.getSimpleName(), "width");
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void renewWoodThickness(){
        renewWoodFilter(WoodThickness.class.getSimpleName(), "thickness");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void renewWoodType() {
//        renewWoodFilter(WoodType.class.getSimpleName(), "all", "type");
    }

    @SuppressWarnings("unchecked")
    private void renewWoodFilter(String className, String checkBoxName) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from " + className).executeUpdate();
        
        List<Integer> list = session.createQuery("select M." + checkBoxName + " from Wood M").list();
        Set<Integer> set = new HashSet<>(list);
        
        if(className.equals(WoodLength.class.getSimpleName())) {
            for (Integer i : set) 
                session.save(new WoodLength(i, Collections.frequency(list, i)));
        } 
        else if(className.equals(WoodWidth.class.getSimpleName())) {
            for (Integer i : set) 
                session.save(new WoodWidth(i, Collections.frequency(list, i)));
        }
        else if(className.equals(WoodThickness.class.getSimpleName())){
            for (Integer i : set) 
                session.save(new WoodThickness(i, Collections.frequency(list, i)));
        }
    }
}
