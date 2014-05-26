<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : create_franken_xslt.xsl
    Created on : May 23, 2014, 11:37 AM
    Author     : farshid
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:for-each select="bodyparts/bodypart">
        <option value="<xsl:value-of select="id"/>><xsl:value-of select="name"/></option>
    </xsl:for-each>
</xsl:stylesheet>
