<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:bds="http://www.univ-rouen.fr/bd" xmlns:fo="http://www.w3.org/1999/XSL/Format"
        exclude-result-prefixes="fo" >
       
        <xsl:template match="/bds:bd">
                <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
                       
                        <!-- Declaration de la mise en page -->
                        <fo:layout-master-set>
                <fo:simple-page-master master-name="bd-page" page-height="18cm" >
                    <fo:region-body margin="1in 1in"/>
                    <fo:region-before extent="1in" display-align="after"/>
                    <fo:region-after extent="1in" display-align="after"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
                       
                        <!-- Corps du document -->
                        <fo:page-sequence master-reference="bd-page">
                               
                                <!-- Entete -->
                                <fo:static-content flow-name="xsl-region-before">
                    <fo:block font="10pt Arial" text-align="center" padding="0.5in">
                        <fo:inline font-size="250%">
                            <fo:inline font-style="italic">
                                Bedetheque - M1GIL
                            </fo:inline>
                        </fo:inline>
                    </fo:block>
                </fo:static-content>
                       
                               
                                <!-- Pied de page -->
                                <fo:static-content flow-name="xsl-region-after" font-size="80%" text-align="center">
                    <fo:block>
                        <fo:leader leader-length="80%" leader-pattern="rule"
                            alignment-baseline="middle" rule-thickness="0.5pt" color="black"/>
                    </fo:block>
                    <fo:block>Copyright - AHOUNOU Folabi Thierry - ABALINE Rachid </fo:block>
                </fo:static-content>
                               
                                <!-- Contenu de la page -->
                                <fo:flow flow-name="xsl-region-body" font="12pt Times">
                               
                                        <fo:table table-layout="fixed" width="100%" padding="0.1in">
                                                <fo:table-body>
                                                        <fo:table-row>
                                                                <!-- Affichage de l'image -->
                                                                <fo:table-cell>
                                                                        <fo:block>
                                                                                <xsl:template match="bds:Images">
                                                                                        <fo:external-graphic>
                                                                                                <xsl:attribute name="src">
                                                                                                        <xsl:value-of select="bds:Images/bds:Image" />
                                                                                                </xsl:attribute>
                                                                                        </fo:external-graphic>
                                                                                </xsl:template>
                                                                        </fo:block>
                                                                </fo:table-cell>
                                                               
                                                                <!-- Affichage des infos sur la bd -->
                                                                <fo:table-cell>
                                                                        <xsl:template match="bds:Informations">
                                                                                <!-- Titre -->
                                                                                <fo:block color="blue">
                                                                                        <xsl:value-of select="bds:Informations/bds:Titre" />
                                                                                </fo:block>
                                                                               
                                                                                <!-- ISBN -->
                                                                                <fo:block>
                                                                                        ISBN : <xsl:value-of select="bds:Informations/bds:ISBN" />
                                                                                </fo:block>
                                                                               
                                                                                <!-- Editeur -->
                                                                                <fo:block>
                                                                                        Editeur : <xsl:value-of select="bds:Informations/bds:Editeur" />
                                                                                </fo:block>
                                                                               
                                                                                <!-- Date de creation -->
                                                                                <fo:block>
                                                                                        Crée le  : <xsl:value-of select="bds:Informations/bds:Editeur" />
                                                                                </fo:block>
                                                                               
                                                                                <fo:block>
                                                                <fo:leader/>
                                                            </fo:block>
                                                                               
                                                                                <!-- Description -->
                                                                                <fo:block text-align="justify">
                                                                                        Résumé de la série : <xsl:value-of select="bds:Description" />
                                                                                </fo:block>
                                                                               
                                                                        </xsl:template>
                                                                </fo:table-cell>
                                                        </fo:table-row>
                                                </fo:table-body>
                                        </fo:table>
                               
                                       
                                       
                                       
                                </fo:flow>
                               
                               
                        </fo:page-sequence>
                </fo:root>
        </xsl:template>
</xsl:stylesheet>
