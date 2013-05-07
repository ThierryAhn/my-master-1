<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cv="http://www.univ-rouen.fr/cv">
	<xsl:template match="/cv:cv">
		<!--  civilite -->
		  		<xsl:template match="cv:Civilite">
		  			<div style="float: left;">
		  				<!-- nom, prenom, age -->
		  				<b>
		  					<xsl:value-of select="cv:Civilite/cv:Prenom"/> 
		  					<xsl:text>&#160;</xsl:text>
			  				<xsl:value-of select="cv:Civilite/cv:Nom" />
			  			</b>
		  				<br/>
		  				<xsl:value-of select="cv:Civilite/cv:Age" /> ans <br/>
		  				<!--  adresse -->
		  				<xsl:template match="cv:Civilite/cv:Adresse">
		  					<xsl:value-of select="cv:Civilite/cv:Adresse/cv:NumeroRue" />
		  					<xsl:text>&#160;</xsl:text>
		  					<xsl:value-of select="cv:Civilite/cv:Adresse/cv:NomRue" /> <br/>
		  					<xsl:value-of select="cv:Civilite/cv:Adresse/cv:CodePostal" />
		  					<xsl:text>&#160;</xsl:text>
		  					<xsl:value-of select="cv:Civilite/cv:Adresse/cv:Ville" /> <br/>
		  				</xsl:template>
		  				
		  				<!-- telephone, mail -->
		  				<xsl:value-of select="cv:Civilite/cv:Telephone" /><br/>
		  				<xsl:value-of select="cv:Civilite/cv:Email" />
		  			</div>
		  			<!-- image -->
		  			<div style="float: right;">
		  				<xsl:value-of select="cv:Civilite/cv:Image" />
		  			</div>
		  		</xsl:template>
		  		<!--  titre du cv -->
		  		<div style="clear: left;"></div>
		  		<div style="text-align: center;">
		  			<h1> <xsl:value-of select="cv:Titre"/> </h1>
		  		</div>
		  		
		  		
		  		<!-- formations -->
		  		<div>
		  			<h2>Formations</h2>
		  			<xsl:apply-templates select="cv:Formations"/>
		  		</div>
		  		
		  		<!-- experiences -->
		  		<div>
		  			<h2>Experiences</h2>
		  			<xsl:apply-templates select="cv:Experiences"/>
		  		</div>
		  		
		  		<!-- competences -->
		  		<div>
		  			<xsl:apply-templates select="cv:Competences"/>
		  		</div>
		  		
		  		<!-- loisirs -->
		  		<div>
		  			<h2>Loisirs</h2>
		  			<xsl:apply-templates select="cv:Loisirs"/>
		  		</div>
		  		
		 
		
		
	</xsl:template>
	
	<!-- template formations -->
	<xsl:template match="cv:Formations">
		<xsl:for-each select="cv:Formation">
			<p>
				
				<span style="margin-right:30px;"> 
					<b> <xsl:value-of select="cv:Date" /> </b>
				</span>
				
				<b><xsl:value-of select="@titre" /></b>
				<xsl:text>&#160;</xsl:text>
				<xsl:value-of select="@ecole" />,
				<xsl:text>&#160;</xsl:text>
				<xsl:value-of select="cv:Location" />
				<br/>
				<p style="margin-left:100px;"> <xsl:value-of select="cv:Description" /> </p>
			</p>
		</xsl:for-each>
	</xsl:template>
	
	<!-- template experiences -->
	<xsl:template match="cv:Experiences">
		<xsl:for-each select="cv:Experience">
			<p>
				
				<span style="margin-right:30px;"> 
					<b> <xsl:value-of select="cv:Date" /> </b>
				</span>
				
				<b><xsl:value-of select="@titre" /></b>,
				<xsl:text>&#160;</xsl:text>
				<xsl:value-of select="cv:Location" />
				<br/>
				<p style="margin-left:100px;"> <xsl:value-of select="cv:Description" /> </p>
			</p>
		</xsl:for-each>
	</xsl:template>
	
	<!-- template competences -->
	<xsl:template match="cv:Competences">
		<h2><xsl:value-of select="@titre" /></h2>
		<xsl:for-each select="cv:Competence">
			<p>
				<b><xsl:value-of select="@titre" /></b>,
				<xsl:text>&#160;</xsl:text>
				<xsl:value-of select="." />
			</p>
		</xsl:for-each>
	</xsl:template>
	
	<!-- template loisirs -->
	<xsl:template match="cv:Loisirs">
		<xsl:for-each select="cv:Loisir">
			<p>
				<span style="margin-right:10px;"> 
					<b><xsl:value-of select="@titre" /></b>,
				</span>
				<xsl:text>&#160;</xsl:text>
				<xsl:value-of select="." />
			</p>
		</xsl:for-each>
	</xsl:template>
	
	
</xsl:stylesheet>
