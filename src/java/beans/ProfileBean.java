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
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ebbe
 */
public final class ProfileBean {
    
    private String url;
    private String username;
    private String role;
    private int identifier;
    
    public ProfileBean(String _url, String _name) {
        url = _url;
        
        try {
            this.getUser(_name);
        } catch (Exception e) {
            
        }
    }
    
    public ProfileBean(String _url) {
        url = _url;
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
    
    public void updateProfile() throws Exception {
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
            sql = "UPDATE CUSTOMERS SET NAME = " + "'" + username;
            sql += "'" + " WHERE CUSTOMER_ID = " + identifier + ";";
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
    
    public void insertUser() throws Exception {
        Statement stmt = null;
        Connection conn = null;
        int rsdummy;
        
        try {
            String sql;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            
            conn.setAutoCommit(false);
            sql = "INSERT INTO CUSTOMERS (NAME, ROLE) VALUES ( ";
            sql += "'" + username + "', '" + role + "');";
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
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String _username) {
        username = _username;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String _role) {
        role = _role;
    }

    public int getId() {
        return identifier;
    }
    
    public void checkout(ShoppingCartBean shoppingCart) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        int rsdummy;
        
        try {
            String sql;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
            
            sql = "INSERT INTO ORDERS (CUSTOMER) VALUES ( ";
            sql += "'" + identifier + "');";
            rsdummy = stmt.executeUpdate(sql);
            conn.commit();
            
            sql = "SELECT ORDER_ID from ORDERS WHERE CUSTOMER = ";
            sql += "'" + identifier + "';";
            ResultSet resultSet = null;
            resultSet = stmt.executeQuery(sql);
            conn.commit();
            
            resultSet.last();
            int orderId = resultSet.getInt(1);
            
            Iterator iter = shoppingCart.getIterator();
            int productId;
            
            while (iter.hasNext()) {
                
                
                productId = ((FrankenBean) iter.next()).getFrankenId();
                
                sql = "INSERT INTO PRODUCTS_IN_ORDER (PRODUCT_ID, ORDER_ID) VALUES (";
                sql += "" + productId + ", " + orderId + ");";
                
                rsdummy = stmt.executeUpdate(sql);
            }
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
