/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springapp.wood.dao;


import com.springapp.wood.domain.LaminatedParticleBoard;
import com.springapp.wood.domain.RawPlywood;
import com.springapp.wood.domain.Wood;
import com.springapp.wood.domain.WoodLength;
import com.springapp.wood.domain.WoodWidth;
import com.springapp.wood.domain.WoodThickness;
import com.springapp.wood.domain.WoodType;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;


import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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
       return (List<WoodType> )sessionFactory.getCurrentSession().createCriteria(WoodType.class)
               .addOrder(Order.desc("type")).list();
    }
    
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Wood> getListWood() {
        return sessionFactory.getCurrentSession().createCriteria(Wood.class).addOrder(Order.desc("id")).list();
    }
    
    @Override
    @SuppressWarnings("unchecked") //TODO refactor params
    public List<Wood> getListWood(String[] arLengths, String[] arWidths, String[] arThicknesses, String arType)
    {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Wood.class);
        
        if (arLengths != null && !arLengths[0].isEmpty())   
            crit.add(Restrictions.in("length", arLengths));
        
        if (arWidths != null && !arWidths[0].isEmpty())   
            crit.add(Restrictions.in("width", arWidths));
        
        if (arThicknesses != null && !arThicknesses[0].isEmpty())   
            crit.add(Restrictions.in("thickness", arThicknesses));
        
        if (arType != null && !arType.isEmpty()) 
            crit.add(Restrictions.eq("type", arType));
        
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
        Map<Object, Long> m = clearWoodFilter(WoodLength.class.getSimpleName(), "length");
        for(Map.Entry<Object, Long> e : m.entrySet())
            sessionFactory.getCurrentSession().save(new WoodLength((Integer)e.getKey(), e.getValue()));
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void renewWoodWidth(){
        Map<Object, Long> m = clearWoodFilter(WoodWidth.class.getSimpleName(), "width");
        for(Map.Entry<Object, Long> e : m.entrySet())
            sessionFactory.getCurrentSession().save(new WoodWidth((Integer)e.getKey(), e.getValue()));
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void renewWoodThickness() {
        Map<Object, Long> m = clearWoodFilter(WoodThickness.class.getSimpleName(), "thickness");
        for(Map.Entry<Object, Long> e : m.entrySet())
            sessionFactory.getCurrentSession().save(new WoodThickness((Integer)e.getKey(), e.getValue()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void renewWoodType() {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from " + WoodType.class.getSimpleName()).executeUpdate();
        
        saveTypeNum(RawPlywood.class, RawPlywood.TYPE_NAME);
        saveTypeNum(LaminatedParticleBoard.class, LaminatedParticleBoard.TYPE_NAME);
    }
    
    private void saveTypeNum(Class clazz, String typeName){
        Session session = sessionFactory.getCurrentSession();
        
        Integer count = (Integer) session.createCriteria(clazz).
                setProjection(Projections.rowCount()).uniqueResult();

        session.save(new WoodType(typeName, count));
    }

    @SuppressWarnings("unchecked")
    private Map<Object, Long> clearWoodFilter(String className, String checkBoxName) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from " + className).executeUpdate();
        
        List<Map<String, Long>> listM = session.createQuery(
                "select new map(" + checkBoxName + " as value, count(*) as count) from Wood group by " + checkBoxName).list();

        Map<Object, Long> result = new HashMap<>();
        for(Map<String, Long> map: listM)
            result.put(map.get("value"), map.get("count"));
        
        return result;
    }
}
