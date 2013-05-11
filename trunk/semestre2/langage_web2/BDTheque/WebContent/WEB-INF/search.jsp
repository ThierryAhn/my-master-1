<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META http-equiv="Content-Language" content="fr-FR">
<link rel="stylesheet" type="text/css" href="css/style.css" media="all" />
<script type='text/javascript' src='js/jquery/jquery.js'></script>
<script type='text/javascript' src='js/general.js'></script>
<title>Bdthèque</title>
<link rel="shortcut icon" href="img/icon.jpg" />

<!-- fenetre modale css -->
<link rel="stylesheet" href="js/reveal/reveal.css">
<!-- scripts fenetre modale -->
<script type="text/javascript" src="js/reveal/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/reveal/jquery.reveal.js"></script>
</head>

<body class="two-col-left width-940"
    style="-moz-user-select: none; cursor: default;">
    <div id="wrapper">

        <!-- header -->
        <%@ include file="header.jsp"%>

        <!-- menu -->
        <%@ include file="navigation.jsp"%>

        <!-- boite modale pour ajouter une bd -->
        <%@ include file="addNewBd.jsp"%>
    
        <!-- Contenu de la page -->
        <div id="content" class="col-full">
            <div id="main-sidebar-container">
                <div id="main" class="col-left">
                    <div class="fix"></div>

                    <!-- Parcours et affichage des BD -->
                    <c:forEach var="bd" items="${bds.bd}">
                        <div
                            class="post-17396 post type-post status-publish format-standard hentry">
                            <c:out value="" />
                            <!-- Image bd -->
                            <a title="${bd.informations.titre}" href="#"> <img
                                src="${bd.image.value}" alt="${bd.informations.titre}"
                                class="woo-image thumbnail alignright" width="300" height="200" />
                            </a>
                            
                            <!-- Titre dans le coin a gauche -->
                            <h2 class="title">
                                <a href="#" rel="bookmark" title="${bd.informations.titre}">
                                    ${bd.informations.titre} </a>
                            </h2>

                            <div class="post-meta"></div>
                            <!-- Informations bd -->
                            <div class="entry">
                                <!-- <p> -->
                                    <table>
    
                                        <tr>
                                            <td>Titre :</td>
                                            <td><c:out value="${bd.informations.titre}" /></td>
                                        </tr>
    
                                        <tr>
                                            <td>Serie :</td>
                                            <td><c:out value="${bd.informations.serie}" /></td>
                                        </tr>
    
                                        <tr>
                                            <td>Identifiant :</td>
                                            <td><c:out value="${bd.informations.identifiant}" /></td>
                                        </tr>
    
                                        <tr>
                                            <td>Scenario :</td>
                                            <td><c:out value="${bd.informations.scenario}" /></td>
                                        </tr>
    
                                        <tr>
                                            <td>Dessin :</td>
                                            <td><c:out value="${bd.informations.dessin}" /></td>
                                        </tr>
    
                                        <tr>
                                            <td>Couleurs :</td>
                                            <td><c:out value="${bd.informations.couleurs}" /></td>
                                        </tr>
    
                                        <tr>
                                            <td>Editeur :</td>
                                            <td><c:out value="${bd.informations.editeur}" /></td>
                                        </tr>
    
                                        <tr>
                                            <td>Format :</td>
                                            <td><c:out value="${bd.informations.format}" /></td>
                                        </tr>
    
                                        <tr>
                                            <td>ISBN :</td>
                                            <td><c:out value="${bd.informations.ISBN}" /></td>
                                        </tr>
    
                                        <tr>
                                            <td>Crée le :</td>
                                            <td><c:out value="${bd.informations.date}" /></td>
                                        </tr>
                                    </table>
                                <!-- </p> -->
                            </div>
                            
                            <div class="fix"></div>
                            
                            <span class="fbreplace"> </span>

                            <!-- Description bd -->
                            <div class="post-more">
                                <c:out value="${bd.description.value}" />
                            </div>
                            
                            <!-- Actions Bd : supprimer, modifier -->
                            <div>
                                <form method="post" action="">
                                    <%-- <a href="?Identifiant=${bd.informations.identifiant}" id="toto">
                                        Supprimer
                                    </a> --%>
                                    <input class="button" type="submit" value="Supprimer"/>
                                </form>                         
                            </div>

                        </div>
                        
                        <!-- /.post -->
                    </c:forEach>

                    <div class="pagination woo-pagination"></div>
                </div>
                <!--  -->
                <%@ include file="sideBar.jsp"%>
            </div>
        </div>
    </div>
</body>
</html>