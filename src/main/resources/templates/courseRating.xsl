<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output
		encoding="UTF-8"
		media-type="text/html"
		method="html"
		indent="no"
		omit-xml-declaration="yes"
	/>

	<xsl:include href="include.xsl"/>

	<xsl:template match="/">
		<xsl:text disable-output-escaping="yes">&#60;!DOCTYPE HTML&#62;</xsl:text>
		<HTML lang="zh-TW">
			<HEAD>
				<TITLE>課程</TITLE>
			</HEAD>
			<BODY>
				<xsl:apply-templates select="document"/>
			</BODY>
		</HTML>
	</xsl:template>

	<xsl:template match="document">
		<DIV>
			<H4 class="page-title font-weight-bold">課程</H4>
			<TABLE class="table table-hover table-sm table-striped text-center">
				<THEAD class="thead-dark">
					<TR>
						<TH>學生</TH>
						<TH>課程</TH>
					</TR>
				</THEAD>
				<xsl:apply-templates select="data"/>
			</TABLE>
		</DIV>
	</xsl:template>

	<xsl:template match="data">
		<TBODY>
			<xsl:for-each select="datum">
				<TR>
					<TD>
						<xsl:value-of select="student"/>
					</TD>
					<TD>
						<xsl:value-of select="course"/>
					</TD>
				</TR>
			</xsl:for-each>
		</TBODY>
	</xsl:template>
</xsl:stylesheet>