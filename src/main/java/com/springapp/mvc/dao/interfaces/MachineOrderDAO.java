package com.springapp.mvc.dao.interfaces;



import java.util.List;

public interface MachineOrderDAO {
//    public List<MachineOrder> listMachineOrder();
//    public void addMachineOrder(MachineOrder machineOrder);
//    public String getMachineOrderStatus(String orderId);
    public void setMachineOrderStatus(String orderId, String orderStatus);
}
