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
    
    private ArrayList<FrankenBean> cart;
    
    public ShoppingCartBean() {
        cart = new ArrayList();
    }
    
    public void addFranken(FrankenBean franken) {
        cart.add(franken);
    }
    
    public void addFranken(int id) {
        
    }
    
    public void removeFranken(FrankenBean franken) {
        cart.remove(franken);
    }
    
    public void clear() {
        cart.clear();
    }
    
    public String getXml() {
        StringBuilder out = new StringBuilder();
        FrankenBean tempFranken;
        
        Iterator iter = cart.iterator();
        
        out.append("<shoppingcart>");
        
        while (iter.hasNext()) {
            out.append("<order>");
            
            tempFranken = (FrankenBean) iter.next();
            out.append(tempFranken.getXml());
            
            out.append("</order>");
        }
        
        out.append("</shoppingcart>");
        
        return out.toString();
        
    }
    
    public Iterator getIterator() {
        return cart.iterator();
    }
}
