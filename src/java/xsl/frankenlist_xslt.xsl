<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : frankenlist_xslt.xsl
    Created on : den 12 maj 2014, 10:43
    Author     : Ebbe
    Description:
        Purpose of transformation follows.
-->

<!--
Example:
<franken>
    <id>
        37
    </id>
    <name>
        Awesome Franken
    </name>
    <bodyparts>
        <bodypart>
            <part>
                    <id>
                        1337
                    </id>
                    <name>
                        Rotten arm
                    </name>
            </part>
            <quantity>
                2
            </quantity>
        </bodypart>
        <bodypart>
            <part>
                    <id>
                        42
                    </id>
                    <name>
                        Robot torso
                    </name>
            </part>
            <quantity>
                1
            </quantity>
        </bodypart>

    </bodyparts>   
</franken>
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="frankenlist">
        <table border="0">
            <th cellspacing="0">
                <td>
                    <strong>Name</strong>
                </td>
            </th>
            <xsl:apply-templates/>
        </table>
    </xsl:template>
    
    <xsl:template match="franken">
        <form method="post" action="shop">
            <tr>
                <td>
                    <xsl:value-of select="name"/>
                </td>
                <td>
                    <xsl:element name="input">
                        <xsl:attribute name="type">text</xsl:attribute>
                        <xsl:attribute name="value">1</xsl:attribute>
                        <xsl:attribute name="name">quantity</xsl:attribute>
                    </xsl:element>
                </td>
                <td>
                    <input type="submit" value="BUY"/>
                </td>
            </tr>
            
            <xsl:element name="input">
                <xsl:attribute name="type">hidden</xsl:attribute>
                <xsl:attribute name="value"><xsl:value-of select="id"/></xsl:attribute>
                <xsl:attribute name="name">frankenid</xsl:attribute>
            </xsl:element>
        </form>
    </xsl:template>

</xsl:stylesheet>
