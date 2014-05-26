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

    public FrankenBean() {
        url = "jdbc:mysql://localhost/frankenstore?user=pvark&password=pvark";
    }

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
            } catch (Exception e) {
            }

            try {
                stmt.close();
            } catch (Exception e) {
            }

            try {
                conn.close();
            } catch (Exception e) {
            }
        }

    }

    public void save() throws SQLException, Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);

            stmt = conn.createStatement();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO PRODUCTS(NAME) VALUES('" + name + "');";

            int success = stmt.executeUpdate(sql);

            conn.commit();
            
            sql = "SELECT * FROM PRODUCTS WHERE NAME = '" + name + "';";
            
            rs = stmt.executeQuery(sql);
            
            rs.next();
            frankenId = rs.getInt("PRODUCT_ID");
            
            for (BodyPartBean bean : bodyParts) {
                sql = "INSERT INTO COMPONENTS_IN_PRODUCT(COMPONENT_ID, PRODUCT_ID)";
                sql += "VALUES (" + bean.getBodyPartId() + "," + frankenId + ");";
                int dummy = stmt.executeUpdate(sql);
            }
            conn.commit();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrankenBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }

            try {
                stmt.close();
            } catch (Exception e) {
            }

            try {
                conn.close();
            } catch (Exception e) {
            }
        }

    }

    public void addBodyPart(BodyPartBean bodyPart) {
        if (bodyParts == null) {
            bodyParts = new ArrayList();
        }
        bodyParts.add(bodyPart);
    }

    public void removeBodyPart(BodyPartBean bodyPart) {
        bodyParts.remove(bodyPart);
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
