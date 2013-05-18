<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:bds="http://www.univ-rouen.fr/bd">
	<xsl:template match="/bds:bd">
		<html>
			<head>
				<title>XML BD</title>
				
				<style type="text/css">
					/* image de la BD */
					#image img{
						float : left;
						height : 316px;
						width : 230px;
						margin-right : 10px;
					}
					
					/* description */
					#description{
						width : 800px;
						text-align : justify;
					}
					
					#clear{
						clear : both;
					}
					
				</style>
			</head>

			<body>
				<!-- Affichage de l'image de la BD -->
				<div id="image">
					<xsl:element name="img">
						<xsl:attribute name="src">
							<xsl:value-of select="bds:Image" />
						</xsl:attribute>
						
						<xsl:attribute name="alt">
							<xsl:value-of select="bds:Image" />
						</xsl:attribute>
					</xsl:element>
				</div>

				<!-- Affichage des infos sur la BD -->
				<div>
					<table>
						<xsl:template match="bds:Informations">
							<!-- Titre -->
							<tr>
								<td>
									<b>
										<xsl:value-of select="bds:Informations/bds:Titre" />
									</b>
								</td>
							</tr>
							
							<!-- Serie -->
							<xsl:if test="bds:Informations/bds:Serie">
								<tr>
									<td class="label">Serie : </td>
									<td>
										<xsl:value-of select="bds:Informations/bds:Serie" />
									</td>
								</tr>
							</xsl:if>
							<!-- Identifiant -->
							<tr>
								<td class="label">Identifiant : </td>
								<td>
									<xsl:value-of select="bds:Informations/bds:Identifiant" />
								</td>
							</tr>
							
							<!-- Scenario -->
							<tr>
								<td class="label">Scenario : </td>
								<td>
									<xsl:value-of select="bds:Informations/bds:Scenario" />
								</td>
							</tr>
							
							<!-- Dessin -->
							<tr>
								<td class="label">Dessin : </td>
								<td>
									<xsl:value-of select="bds:Informations/bds:Dessin" />
								</td>
							</tr>
							
							<!-- Couleurs -->
							<tr>
								<td class="label">Couleurs : </td>
								<td>
									<xsl:value-of select="bds:Informations/bds:Couleurs" />
								</td>
							</tr>
							
							<!-- Editeur -->
							<tr>
								<td class="label">Editeur : </td>
								<td>
									<xsl:value-of select="bds:Informations/bds:Editeur" />
								</td>
							</tr>
							
							<!-- ISBN -->
							<tr>
								<td class="label">ISBN : </td>
								<td>
									<xsl:value-of select="bds:Informations/bds:ISBN" />
								</td>
							</tr>
							
							<!-- Format -->
							<tr>
								<td class="label">Format : </td>
								<td>
									<xsl:value-of select="bds:Informations/bds:Format" />
								</td>
							</tr>

							<!-- Date de creation -->
							<tr>
								<td class="label">Crée le : </td>
								<td>
									<xsl:value-of select="bds:Informations/bds:Date" />
								</td>
							</tr>

						</xsl:template>
					</table>
					
					<div id="clear"></div>
					
					<!-- Description de la BD -->
					<p id="description">
						<b>Résumé de la série : </b>
						<xsl:value-of select="bds:Description" />
					</p>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
