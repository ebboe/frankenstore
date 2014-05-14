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
 * @author Lewyn
 */
public class ProfileUpdateBean {
    
    private String url = null;
    
    public ProfileUpdateBean (String _url) {
        url = _url;
    }
    
    public void updateProfile(ProfileBean prb) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        int rsdummy;
        
        try {
            String sql;
            Class.forName("com.myql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
            
            // the sql query goes here
            sql = "UPDATE CUSTOMERS SET NAME = " + "'" + prb.getUsername();
            sql += "'" + " WHERE CUSTOMER_ID = " + prb.getId() + ";";
            rsdummy = stmt.executeUpdate(sql);
            // user roles?
            conn.commit();
        }
        catch (SQLException sqle) {
            conn.rollback();
            throw new Exception(sqle);
        }
        finally {
            try {
                stmt.close();
            }
            catch(Exception e) {}
            try {
                conn.close();
            }
            catch(Exception e) {}
        }
    }
    // save new user
    
    public void insertUser(ProfileBean prb) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        int rsdummy;
        
        try {
            String sql;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            
            conn.setAutoCommit(false);
            sql = "INSERT INTO CONSUMERS (NAME) VALUES ( ";
            sql += "'" + prb.getUsername() + "';";
            rsdummy = stmt.executeUpdate(sql);
            //user role stuff
            conn.commit();
        }
        catch (SQLException sqle) {
            conn.rollback();
            throw new Exception(sqle);
        }
        finally {
            try {
                stmt.close();
            }
            catch(Exception e) {}
            try {
                conn.close();
            }
            catch(Exception e) {}
        }
    }
}
