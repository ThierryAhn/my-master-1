<div id="myModal" class="reveal-modal">
	<h1>Ajouter une nouvelle BD</h1>
	
	<form method="post" action="AddNewBd">
		<label for="titreBd">Titre </label>
		<input type="text" id="titreBd" name="titreBd" value="" size="20" maxlength="20" required/>
		<br/>
		
		<label for="serieBd">Serie </label>
		<input type="text" id="serieBd" name="serieBd" value="" size="20" maxlength="20" required/>
		<br/>
		
		<label for="scenarioBd">Scenario </label>
		<input type="text" id="scenarioBd" name="scenarioBd" value="" size="20" maxlength="20" required/>
		<br/>
		
		<label for="dessinBd">Dessin </label>
		<input type="text" id="dessinBd" name="dessinBd" value="" size="20" maxlength="20" required/>
		<br/>
		
		<label for="couleursBd">Couleurs </label>
		<input type="text" id="couleursBd" name="couleursBd" value="" size="20" maxlength="20" required/>
		<br/>
		
		<label for="editeurBd">Editeur </label>
		<input type="text" id="editeurBd" name="editeurBd" value="" size="20" maxlength="20" required/>
		<br/>
		
		<label for="formatBd">Format </label>
		<select id="formatBd" name="formatBd" required>
			<option value="">Please select</option>
			<option value="petitFormat">Petit Format</option>
			<option value="moyenFormat">Moyen Format</option>
			<option value="grandFormat">Grand Format</option>
		</select>
		<br/>
		
		<label for="isbnBd">ISBN </label>
		<input type="text" id="isbnBd" name="isbnBd" value="" size="20" maxlength="20" required/>
		<br/>
		
		<textarea id="editeurBd" name="editeurBd" rows="3" cols="40">Entrez votre description ici ...
		</textarea>
		<br/>
		
		<input type="submit" value="Valider" />
	</form>
	
	<a class="close-reveal-modal">&#215;</a>
</div>