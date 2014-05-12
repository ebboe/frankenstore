/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ebbe
 */
public final class ProfileBean {
    
    private String url;
    private String username;
    private int identifier;
    private ArrayList<String> names;
    
    public ProfileBean (String _url) {
        url = _url;
        
        try {
            this.getUsers();
        } catch (Exception ex) {
            Logger.getLogger(ProfileBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getUser(String userName) throws Exception {
        Connection conn =null;
        Statement stmt = null;
        ResultSet rs=null;
 
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn=DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            String sql = "SELECT * from CUSTOMERS WHERE NAME = " + "'" + userName + "'";
            rs = stmt.executeQuery(sql);

	    // analyze the result set
	    
            rs.next();
            username = rs.getString("NAME");
            identifier = rs.getInt("CUSTOMER_ID");
            
	} catch(SQLException sqle) {
            throw new Exception(sqle);
	} finally {
 	    try{
		rs.close();
            } catch(Exception e) {}
            
            try {
		stmt.close();
            } catch(Exception e) {}
            
            try {
		conn.close();
            } catch(Exception e){}
        }
    }
    
    public void getUsers() throws Exception {
        Connection conn =null;
        Statement stmt = null;
        ResultSet rs=null;
 
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/frankenstore?user=pvark&password=pvark");
            
            stmt = conn.createStatement();
            String sql;
            sql ="SELECT * from CUSTOMERS where CUSTOMER_ID = 1";
            rs= stmt.executeQuery(sql);

	    // analyze the result set
	    
            while (!rs.isLast()) {
                rs.next();
                names.add(rs.getString("NAME"));
            }
	}   
	catch(SQLException sqle){
            throw new Exception(sqle);
	}
        finally{
 	    try{
		rs.close();
            }
            catch(Exception e) {}
            try{
		stmt.close();
            }
	    catch(Exception e) {}
            try {
		conn.close();
            }
            catch(Exception e){}
        }
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String _username) {
        username = _username;
    }

    public int getId() {
        return identifier;
    }
}
