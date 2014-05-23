/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ebbe
 */
public class FrankenBean {
    private int frankenId;
    private String name;
    private String url;
    private ArrayList<BodyPartBean> bodyParts;
    
    public FrankenBean(String _url) {
        url = _url;
    }
    
    public FrankenBean(int _frankenId, String _name, String _url) throws SQLException, Exception {
        frankenId = _frankenId;
        name = _name;
        url = _url;
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        bodyParts = new ArrayList();
            
        try {
            Class.forName("com.mysql.jdbc.Driver");
        
            conn = DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM COMPONENTS_IN_PRODUCT WHERE PRODUCT_ID = " + frankenId + "";
            
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                BodyPartBean bodyPart = new BodyPartBean(rs.getInt("COMPONENT_ID"));

                bodyParts.add(bodyPart);
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrankenBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {} 
            
            try {
                stmt.close();
            } catch (Exception e) {}
            
            try {
                conn.close();
            } catch (Exception e) {}
        }
        
    }
    
    public void addBodyPart(BodyPartBean bodyPart) {
        
        //if (bodyParts.isEmpty()) {
            bodyParts.add(bodyPart);
        //}
        /*
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
        */
    }
    
    public void removeBodyPart(BodyPartBean bodyPart) {
        bodyParts.remove(bodyPart);
        
        /*if (!bodyParts.isEmpty()) {
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
        }*/
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String newname) {
        name = newname;
    }
    
    public ArrayList getBodyParts() {
        return bodyParts;
    }
    
    public int getFrankenId() {
        return frankenId;
    }
    
    public void setFrankenId(int i) {
        frankenId = i;
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

            out.append("<bodyparts>");

            while (iter.hasNext()) {
                BodyPartBean tempBodyPart = (BodyPartBean) iter.next();
                
                out.append(tempBodyPart.getXml());
            }

            out.append("</bodyparts>");
        
        out.append("</franken>");
        
        return out.toString();
    }
}
