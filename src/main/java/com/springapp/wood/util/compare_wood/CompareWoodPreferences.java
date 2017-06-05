package com.springapp.wood.util.compare_wood;

import static com.springapp.wood.domain.Wood.LENGTH;
import static com.springapp.wood.domain.Wood.THICKNESS;
import static com.springapp.wood.domain.Wood.WIDTH;

public class CompareWoodPreferences 
{
    private boolean length = false;
    private boolean width = false;
    private boolean thickness = false;
    private boolean price = false;

    public boolean isLength() {
        return length;
    }

    public void setLength(boolean length) {
        this.length = length;
    }

    public boolean isWidth() {
        return width;
    }

    public void setWidth(boolean width) {
        this.width = width;
    }

    public boolean isThickness() {
        return thickness;
    }

    public void setThickness(boolean thickness) {
        this.thickness = thickness;
    }

    public boolean isPrice() {
        return price;
    }

    public void setPrice(boolean price) {
        this.price = price;
    }
    
    public void clear(){
        length = false;
        width = false;
        thickness = false;
        price = false;
    }
    
    public boolean getByName(String name){
        switch (name) {
            case LENGTH:
                return length;
            case WIDTH:
                return width;
            case THICKNESS:
                return thickness;
            default:
                throw new Error("No such property");
        }
    }
    
    public void setByName(String name, boolean value){
    switch (name){
            case LENGTH: 
                length = value;
                return;
            case WIDTH:
                width = value;
                return;
            case THICKNESS:
                thickness = value;
                return;
            default:
                throw new Error("No such property");
        }
    }
}
