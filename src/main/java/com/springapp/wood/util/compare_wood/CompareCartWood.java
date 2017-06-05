package com.springapp.wood.util.compare_wood;

import java.util.HashSet;
import java.util.Set;

public class CompareCartWood {
    
    private Set<String> ids;
    
    public CompareCartWood() {
        ids = new HashSet<>();
    }
    
    public synchronized void addId(String id) {
        ids.add(id);
    }
    
    public synchronized void removeId(String id) {
        ids.remove(id);
    }
    
    public synchronized int getNumberOfItems() {
        return ids.size();
    }
    
    public String getIds() {
        String result = "";
        for (String id : ids) {
            if (!result.isEmpty()) 
                result += ",";
            result += id;
        }
        return result;
    }
}
