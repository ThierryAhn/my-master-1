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
<script type="text/javascript" src="js/dom.js"></script>
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
				<div id="resultat"></div>
				<div id="main" class="col-left">
					<div class="fix"></div>

					<div id="advancedSearch">
						<h1>Upload fichier Xml :</h1>
						<br />
						<form action="Upload" method="post" enctype="multipart/form-data">
							<label for="fichier">Fichier </label> <input type="file"
								name="fichier" /> <br /> <input class="button" type="submit"
								value="upload" />
						</form>
					</div>
					<div class="pagination woo-pagination"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>