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

/**
 *
 * @author Ebbe
 */
public class ProfileBean {
    
    private String url;
    private String username;
    private ArrayList<String> names;
    
    public ProfileBean (String _url) {
        url = _url;
    }
    
    public void getUsers() throws Exception {
        Connection conn =null;
        Statement stmt = null;
        ResultSet rs=null;
 
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            String sql;
            sql ="SELECT * from CUSTOMERS";
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
    
    public int getId() {
        return id;
    }
}
