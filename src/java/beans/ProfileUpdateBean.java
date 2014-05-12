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
    
    public void setProfile(ProfileBean prb) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        int rsdummy;
        
        try {
            String sql1, sql2, sql3;
            Class.forName("com.myql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
            
            // the sql query goes here
            sql1 = "UPDATE CUSTOMERS SET NAME = " + "'" + prb.getName();
            sql1 += "'" + "WHERE CUSTOMER_ID =" + prb.getCustomerId() + ";";
            rsdummy = stmt.executeUpdate(sql1);
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
    
    public void saveUser(ProfileBean prb) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        int rsdummy;
        
        try {
            String sql1, sql2;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            
            conn.setAutoCommit(false);
            sql1 = "INSERT INTO CONSUMERS (NAME) VALUES ( ";
            sql1 += "'" + prb.getName() + "';";
            rsdummy = stmt.executeUpdate(sql1);
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
