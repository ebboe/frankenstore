/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.sql.*;

/**
 *
 * @author farshid
 */
public class BodyPartBean {
    private int bodyPartId;
    private String name;
    private String url;
    
    public BodyPartBean() {
        url = "jdbc:mysql://localhost/frankenstore?user=pvark&password=pvark";
    }
    
    public BodyPartBean(int id) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        url = "jdbc:mysql://localhost/frankenstore?user=pvark&password=pvark";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM COMPONENTS WHERE COMPONENT_ID =" + id + "";
            
            rs = stmt.executeQuery(sql); 
            
            rs.next();
            this.bodyPartId = rs.getInt("COMPONENT_ID");
            this.name = rs.getString("NAME");
            
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
            
    public int getBodyPartId() {
        return bodyPartId;
    }
    
    public void setBodyPartId(int partId) {
        bodyPartId = partId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String partName) {
        name = partName;
    }
    
    public String getXml() {
        StringBuilder xmlOut = new StringBuilder();
        
        xmlOut.append("<bodypart>");
        
            xmlOut.append("<id>");
                xmlOut.append(bodyPartId);
            xmlOut.append("</id>");
            
            xmlOut.append("<name><![CDATA[");
                xmlOut.append(name);
            xmlOut.append("]]></name>");
            
        xmlOut.append("</bodypart>");
        
        return xmlOut.toString();
    }
    
    public String getHtml() {
        String html = "<option value=" + bodyPartId + ">" + name + "</option>";
        
        return html;
    }
}
