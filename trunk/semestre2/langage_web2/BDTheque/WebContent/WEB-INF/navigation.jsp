<script type='text/javascript' src='js/jquery/search.js'></script>
<div id="navigation" class="col-full">
	<ul id="main-nav" class="nav fl">
		<li><a href="Index">Accueil</a></li>
		
		<li>
			<a href="#" data-reveal-id="addModal" data-animation="fadeAndPop" data-animationspeed="300" 
				data-closeonbackgroundclick="true" data-dismissmodalclass="close-reveal-modal">
				Ajouter Bd
			</a>
		</li>
		
		<li><a href="Search">Recherche avancée</a></li>
		
		<li><a href="Upload">Upload Xml</a></li>
		
	</ul>
	<div id="search">
		<form method="post" action="Search">
			<input id="recherche" class="text" maxlength="64" size="27" name="Titre"/> 
			<input class="button" type="submit" value="Chercher"/>
		</form>
	</div>
</div>