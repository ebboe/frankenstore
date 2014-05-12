/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.*;
import java.sql.*;
import java.io.*;

/**
 *
 * @author farshid
 */
public class BodyPartListBean {
    private Collection bodyPartList;
    private String url;
    
    public BodyPartListBean() throws Exception {
        this("jdbc:mysql://localhost/frankenstore?user=pvark&password=pvark");
    }
    
    public BodyPartListBean(String _url) throws Exception {
        url = _url;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        bodyPartList = new ArrayList();
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            String sql = "SELECT COMPONENT_ID, NAME, QUANTITY AS AUTHOR_NAME FROM COMPONENTS";
            
            rs = stmt.executeQuery(sql); 
            
            while (rs.next()) {
                BodyPartBean partBean = new BodyPartBean();
                
                partBean.setBodyPartId(rs.getInt("COMPONENT_ID"));
                partBean.setName(rs.getString("NAME"));
                
                bodyPartList.add(partBean);
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
        return bodyPartList;
    }
    
    public String getXml() {
        BodyPartBean partBean;
        Iterator iter = bodyPartList.iterator();
        StringBuilder sb = new StringBuilder();
        
        sb.append("<bodypartlist>");
            while (iter.hasNext()) {
                partBean = (BodyPartBean)iter.next();
                sb.append(partBean.getXml());
            }
        sb.append("</bodypartlist>");
        
        return sb.toString();
    }
    
}
