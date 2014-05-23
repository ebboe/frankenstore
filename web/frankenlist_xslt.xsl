<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : frankenlist_xslt.xsl
    Created on : den 12 maj 2014, 10:43
    Author     : Ebbe
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="frankenlist">
        <table border="0">
            <tr cellspacing="0">
                <td colspan="2">
                    <strong>Name</strong>
                </td>
            </tr>
            <xsl:apply-templates/>
        </table>
    </xsl:template>
    
    <xsl:template match="franken">
        <form method="post" action="store">
            <tr>
                <td>
                    <xsl:value-of select="name"/>
                </td>
                <td>
                    <input type="submit" value="BUY"/>
                </td>
            </tr>
            
            <xsl:for-each select="bodyparts/bodypart">
              <tr>
                 <td colspan="1">* <i><xsl:value-of select="name"/></i></td>
              </tr>
           </xsl:for-each>
            
            <xsl:element name="input">
                <xsl:attribute name="type">hidden</xsl:attribute>
                <xsl:attribute name="value"><xsl:value-of select="id"/></xsl:attribute>
                <xsl:attribute name="name">frankenid</xsl:attribute>
            </xsl:element>
            
            <xsl:element name="input"> <!--A ordinary input in XSLT-->
                <xsl:attribute name="type">hidden</xsl:attribute>
                <xsl:attribute name="value">add_to_cart</xsl:attribute>
                <xsl:attribute name="name">action</xsl:attribute>
            </xsl:element>
        </form>
    </xsl:template>

</xsl:stylesheet>
