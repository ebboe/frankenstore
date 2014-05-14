<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : shoppingcart_xslt.xsl
    Created on : den 14 maj 2014, 11:05
    Author     : Ebbe
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="shoppingcart">
        <br /> <br />
        <table border="0" cellspacing="0">
            <tr>
            <td colspan="2">
                <strong>Shoppingcart</strong>
            </td>
            <tr>
                <td>Title</td>
                <td>Remove</td>
            </tr>
            </tr>

            <xsl:apply-templates/>

            <tr>
                <td>
                    <a href="shop?action=checkout">Checkout</a>
                </td>
            </tr>
        </table>
    </xsl:template>
    
    <xsl:template match="order">
        <form method="post" action="shop">
            <tr>
                
                <td>
                    <xsl:value-of select="franken/name"/>
                </td>

                <td>
                    <input type="submit" value="Remove"/>
                </td>
                
                <xsl:for-each select="franken/bodyparts/bodypart">
                    <tr>
                        <td colspan="1">* <i><xsl:value-of select="name"/></i></td>
                    </tr>
                </xsl:for-each>

                <xsl:element name="input"> <!--A ordinary input in XSLT-->
                    <xsl:attribute name="type">hidden</xsl:attribute>
                    <xsl:attribute name="value"><xsl:value-of select="franken/id"/></xsl:attribute>
                    <xsl:attribute name="name">frankenid</xsl:attribute>
                </xsl:element>
                <xsl:element name="input"> <!--A ordinary input in XSLT-->
                    <xsl:attribute name="type">hidden</xsl:attribute>
                    <xsl:attribute name="value">remove</xsl:attribute>
                    <xsl:attribute name="name">action</xsl:attribute>
                </xsl:element>

            </tr>
        </form>
    </xsl:template>

</xsl:stylesheet>
