/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

/**
 *
 * @author farshid
 */
public class BodyPartBean {
    private int bodyPartId;
    private String name;
    
    public BodyPartBean() {
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
            xmlOut.append("]]</name>");
        xmlOut.append("</bodypart>");
        
        return xmlOut.toString();
    }
}
