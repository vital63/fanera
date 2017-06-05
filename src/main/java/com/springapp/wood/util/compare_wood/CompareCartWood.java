package com.springapp.wood.util.compare_wood;

import com.springapp.wood.domain.Wood;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CompareCartWood {
    
    private Set<Wood> compareSet = new HashSet<>();
    
    public synchronized void add(Wood wood) {
        compareSet.add(wood);
    }
    
    public synchronized void reload(Collection<Wood> woods) {
        compareSet.clear();
        compareSet.addAll(woods);
    }
    
    public synchronized void remove(Wood wood) {
        compareSet.remove(wood);
    }
    
    public synchronized int getNumberOfItems() {
        return compareSet.size();
    }

    public Set<Wood> getCompareSet() {
        return Collections.unmodifiableSet(compareSet);
    }
    
    public String getIds() {
        String result = "";
        for (Wood wood : compareSet) {
            if (!result.isEmpty()) 
                result += ",";
            result += wood.getId();
        }
        return result;
    }
    
    public boolean isEmpty(){
        return compareSet.isEmpty();
    }
    
    public void calculatePreferences(){
        clearPreferences();
        calculatePreferenceByProperty(Wood.LENGTH, false);
        calculatePreferenceByProperty(Wood.WIDTH, false);
        calculatePreferenceByProperty(Wood.THICKNESS, false);
    }
    
    private void clearPreferences(){
        for (Wood wood : compareSet)
            wood.getPreferences().clear();
    }
    
    private void calculatePreferenceByProperty(String property, boolean isLessGood){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(Wood wood: compareSet)
        {
            int i = wood.getIntPropertyByName(property);
            if (i > max)
                max = i;
            if(i < min)
                min = i;
        }
        
        if(max <= min)
            return;
         
        if(isLessGood)
            max = min;
        
        for(Wood wood : compareSet)
            if(wood.getIntPropertyByName(property)== max)
                wood.getPreferences().setByName(property, true);
    }
}
