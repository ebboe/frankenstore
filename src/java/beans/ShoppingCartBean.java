/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ebbe
 */
public class ShoppingCartBean {
    
    private ArrayList cart;
    
    public void addFranken(FrankenBean franken, int quantity) {
        Object newItem[];
        
        if (cart.isEmpty()) {
            newItem = new Object[2];
            newItem[0] = franken;
            newItem[1] = quantity;
            cart.add(newItem);
        }
        else {
            Iterator iter = cart.iterator();
            Object tempArr[];
            boolean found = false;
            
            while (iter.hasNext()) {
                tempArr = (Object[]) iter.next();
                
                if (((FrankenBean) tempArr[0]).getFrankenId() == franken.getFrankenId()) {
                    Integer tempQuantity = (Integer) tempArr[1];
                    tempArr[1] = tempQuantity + quantity;
                    found = true;
                }
            }
            
            if (!found) {
                newItem = new Object[2];
                newItem[0] = franken;
                newItem[1] = quantity;
                cart.add(newItem);
            }
        }
    }
    
    public void removeFranken(int id, int quantity) {
        if (!cart.isEmpty()) {
            Iterator iter = cart.iterator();
            Object tempArr[];
            
            while (iter.hasNext()) {
                tempArr = (Object[]) iter.next();
                if (((FrankenBean) tempArr[0]).getFrankenId() == id) {
                    
                    Integer tempQuantity = (Integer) tempArr[1];
                    
                    if (tempQuantity <= quantity) {
                        iter.remove();
                    }
                    else {
                        tempArr[1] = tempQuantity - quantity;
                    }
                    
                    break;
                }
            }
        }
    }
    
    public void clear() {
        cart.clear();
    }
    
    public String getXml() {
        StringBuilder out = new StringBuilder();
        
        Iterator iter = cart.iterator();
        Object objBuff[];
        
        out.append("<shoppingcart>");
        
        while (iter.hasNext()) {
            objBuff = (Object[]) iter.next();
            
            out.append("<order>");
            out.append(((FrankenBean) objBuff[0]).getXml());
            
            out.append("<quantity>");
            out.append(((Integer) objBuff[1]).intValue());
            out.append("</quantity>");
            
            out.append("</order>");
        }
        
        out.append("</shoppingcart>");
        
        return out.toString();
        
    }
}
