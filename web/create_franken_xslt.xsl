<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : create_franken_xslt.xsl
    Created on : May 23, 2014, 11:37 AM
    Author     : farshid
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes"/>
    
    <xsl:template match="/bodypartlist">
        <table border="0">
            <tr>
                <strong>Select the body parts that should be part of this Franken.</strong>
            </tr>
            <tr>
                <td>Id </td>
                <td>Name </td>
                <td>Select</td>
            </tr>
            <xsl:for-each select="bodypart">
                <tr>
                    <td>
                        <input type="checkbox" name="bodyPart" value="${franken.addBodyPart}"/> 
                    </td>
                    <td>
                        <xsl:value-of select="id"/>
                    </td>
                    <td>
                        <xsl:value-of select="name"/>
                    </td>
                </tr>
                <br></br>
            </xsl:for-each>
            <xsl:apply-templates/>
        </table>
    </xsl:template>
    <!--
    <xsl:template match="bodypartlist/bodypart">
        <tr>
            <td>
                <input type="checkbox" name="bodyPart" value="${franken.addBodyPart}"/> 
            </td>
            <td>
                <xsl:value-of select="id"/>
            </td>
            <td>
                <xsl:value-of select="name"/>
            </td>
        </tr>
        <br></br>  
    </xsl:template>
    -->
</xsl:stylesheet>
