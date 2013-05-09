<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<head>
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
	
	<body class="two-col-left width-940" style="-moz-user-select: none; cursor: default;">
		<div id="wrapper">
	
			<!-- header -->
			<%@ include file="header.jsp"%>
	
			<!-- menu -->
			<%@ include file="navigation.jsp"%>
	
			<!-- boite modale pour ajouter une bd -->
			<%@ include file="addNewBd.jsp"%>
	
			<div id="content" class="col-full">
				<div id="main-sidebar-container">
					<div id="main" class="col-left">
						<div class="fix"></div>
						
						<!-- Parcours et affichage des BD -->
						<c:forEach var="bd" items="${bds.bd}">
							<div class="post-17396 post type-post status-publish format-standard hentry">
								<c:out value="" />
								<a title="${bd.informations.titre}" href="#"> 
									<img src="${bd.image.value}" alt="${bd.image.value}"
										class="woo-image thumbnail alignright" width="300" height="200" />
								</a>
								
								<h2 class="title">
									<a href="#" rel="bookmark" title="${bd.informations.titre}">
										${bd.informations.titre}
									</a>
								</h2>
								
								<div class="post-meta"></div>
								
								<div class="entry">
									<p>
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
									</p>
								</div>
								<div class="fix"></div>
								<span class="fbreplace"> </span>
	
								<div class="post-more">
									<c:out value="${bd.description.value}" />
								</div>
							</div>
							<!-- /.post -->
						</c:forEach>
						
						<div class="pagination woo-pagination">
							<span class='page-numbers current'>1</span> <a
								class='page-numbers' href='page/2/index.html'>2</a> <span
								class="page-numbers dots">&hellip;</span> <a class='page-numbers'
								href='page/138/index.html'>138</a> <a class="next page-numbers"
								href="#">Suivant &rarr;</a>
						</div>
					</div>
					<div id="sidebar">
						<div id="tabs">
							<div class="boxes box inside">
								<a title="Millenium" href="#"><img src="img/Millenium.JPG"
									alt="Millenium" width="100" height="130" /> </a>
							</div>
							<div class="boxes box inside">
								<a title="Millenium" href="#"><img src="img/Millenium.JPG"
									alt="Millenium" width="100" height="130" /> </a>
							</div>
							<div class="boxes box inside">
								<a title="Millenium" href="#"><img src="img/Millenium.JPG"
									alt="Millenium" width="100" height="130" /> </a>
							</div>
							<div class="boxes box inside">
								<a title="Millenium" href="#"><img src="img/Millenium.JPG"
									alt="Millenium" width="100" height="130" /> </a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>