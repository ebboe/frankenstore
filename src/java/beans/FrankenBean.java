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
public class FrankenBean {
    private int frankenId;
    private String name;
    private ArrayList bodyParts;
    
    public void addBodyPart(BodyPartBean bodyPart, int quantity) {
        Object newItem[];
        
        if (bodyParts.isEmpty()) {
            newItem = new Object[2];
            newItem[0] = bodyPart;
            newItem[1] = quantity;
            bodyParts.add(newItem);
        }
        else {
            Iterator iter = bodyParts.iterator();
            Object tempArr[];
            boolean found = false;
            
            while (iter.hasNext()) {
                tempArr = (Object[]) iter.next();
                
                if (((BodyPartBean) tempArr[0]).getBodyPartId() == bodyPart.getBodyPartId()) {
                    Integer tempQuantity = (Integer) tempArr[1];
                    tempArr[1] = tempQuantity + quantity;
                    found = true;
                }
            }
            
            if (!found) {
                newItem = new Object[2];
                newItem[0] = bodyPart;
                newItem[1] = quantity;
                bodyParts.add(newItem);
            }
        }
    }
    
    public void removeBodyPart(BodyPartBean bodyPart, int quantity) {
        if (!bodyParts.isEmpty()) {
            Iterator iter = bodyParts.iterator();
            Object tempArr[];
            
            while (iter.hasNext()) {
                tempArr = (Object[]) iter.next();
                if (((BodyPartBean) tempArr[0]).getBodyPartId() == bodyPart.getBodyPartId()) {
                    
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
    
    public String getName() {
        return name;
    }
    
    public ArrayList getBodyParts() {
        return bodyParts;
    }
    
    public int getFrankenId() {
        return frankenId;
    }
    
    public void clear() {
        bodyParts.clear();
    }
    
    public String getXml() {
        StringBuilder out = new StringBuilder();
        
        out.append("<franken>");
        
            out.append("<id>");
                out.append(frankenId);
            out.append("</id>");

            out.append("<name><![CDATA[");
                out.append(name);
            out.append("]]></name>");

            Iterator iter = bodyParts.iterator();
            Object objBuff[];

            out.append("<bodyparts>");

            while (iter.hasNext()) {
                objBuff = (Object[]) iter.next();

                out.append("<part>");
                    out.append(((BodyPartBean) objBuff[0]).getXml());

                    out.append("<quantity>");
                        out.append(((Integer) objBuff[1]).intValue());
                    out.append("</quantity>");

                out.append("</part>");
            }

            out.append("</bodyparts>");
        
        out.append("</franken>");
        
        return out.toString();
    }
}
