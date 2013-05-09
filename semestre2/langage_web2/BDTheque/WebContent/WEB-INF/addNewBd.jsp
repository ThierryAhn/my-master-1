<div id="myModal" class="reveal-modal">
	<h1>Ajouter une nouvelle BD</h1>
	
	<form method="post" action="AddNewBd">
		<label for="titreBd">Titre </label>
		<input type="text" id="titreBd" name="titreBd" value="" size="20" maxlength="20" />
		<br/>
		
		<label for="serieBd">Serie </label>
		<input type="text" id="serieBd" name="serieBd" value="" size="20" maxlength="20" />
		<br/>
		
		<label for="isbnBd">ISBN </label>
		<input type="text" id="isbnBd" name="isbnBd" value="" size="20" maxlength="20" />
		<br/>
		
		<label for="editeurBd">Editeur </label>
		<input type="text" id="editeurBd" name="editeurBd" value="" size="20" maxlength="20" />
		<br/>
		
		<textarea id="editeurBd" name="editeurBd" rows="3" cols="40">Entrez votre description ici ...
		</textarea>
		<br/>
		
		<input type="submit" value="Valider" />
	</form>
	
	<a class="close-reveal-modal">&#215;</a>
</div>