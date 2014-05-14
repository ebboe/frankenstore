/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.*;
import java.sql.*;

/**
 *
 * @author Ebbe
 */
public class FrankenListBean {
    private Collection frankenList;
    private String url;
    
    public FrankenListBean() throws Exception {
        this("jdbc:mysql://localhost/frankenstore?user=pvark&password=pvark");
    }
    
    public FrankenListBean(String _url) throws Exception {
        url = _url;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        frankenList = new ArrayList();
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM PRODUCTS";
            
            rs = stmt.executeQuery(sql); 
            
            while (rs.next()) {
                FrankenBean frankenBean = new FrankenBean(rs.getInt("PRODUCT_ID"), rs.getString("NAME"), url);

                frankenList.add(frankenBean);
            }
            
        } catch (SQLException e) {
            throw new Exception(e);
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
    
    java.util.Collection getBodyPartList() {
        return frankenList;
    }
    
    public FrankenBean getFrankenBean(int id) {
        Iterator iter = frankenList.iterator();
        
        FrankenBean frankenBean;
        
        while (iter.hasNext()) {
            frankenBean = (FrankenBean) iter.next();
            if (frankenBean.getFrankenId() == id) {
                return frankenBean;
            }
        }
        return null;
    }
    
    public String getXml() {
        FrankenBean frankenBean;
        Iterator iter = frankenList.iterator();
        StringBuilder sb = new StringBuilder();
        
        sb.append("<frankenlist>");
            while (iter.hasNext()) {
                frankenBean = (FrankenBean)iter.next();
                sb.append(frankenBean.getXml());
            }
        sb.append("</frankenlist>");
        
        return sb.toString();
    }
}
