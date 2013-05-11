<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="modifyModal" class="reveal-modal">
	<h1>Modifier la BD : ${bd.informations.titre}</h1>
	
	<form method="post" action="ModifyBd">
		<label for="titreBd">Titre </label>
		<input type="text" id="titreBd" name="titreBd" value="${bd.informations.titre}" size="30" maxlength="30" 
			required/>
		<br/>
		
		<label for="serieBd">Serie </label>
		<input type="text" id="serieBd" name="serieBd" value="${bd.informations.serie}" size="30" maxlength="30" 
			required/>
		<br/>
		
		<label for="scenarioBd">Scenario </label>
		<input type="text" id="scenarioBd" name="scenarioBd" value="${bd.informations.scenario}" size="30" 
			maxlength="30" required/>
		<br/>
		
		<label for="dessinBd">Dessin </label>
		<input type="text" id="dessinBd" name="dessinBd" value="${bd.informations.dessin}" size="30" 
			maxlength="30" required/>
		<br/>
		
		<label for="couleursBd">Couleurs </label>
		<input type="text" id="couleursBd" name="couleursBd" value="${bd.informations.couleurs}" size="30" 
			maxlength="30" required/>
		<br/>
		
		<label for="editeurBd">Editeur </label>
		<input type="text" id="editeurBd" name="editeurBd" value="${bd.informations.editeur}" size="30" 
			maxlength="30" required/>
		<br/>
		
		<label for="formatBd">Format </label>
		<select id="formatBd" name="formatBd" required>
			<c:set scope="session" var="format" value="${bd.informations.format}" />
			
			<option value="">Please select</option>
			
			
			<!-- Petit format -->
			<c:choose>
				<c:when test="${format==\"Petit Format\"}">
					<option value="Petit Format" selected="selected">Petit Format</option>
				</c:when>
				<c:otherwise>
					<option value="Petit Format">Petit Format</option>
				</c:otherwise>
			</c:choose>
			
			<!-- Moyen format -->
			<c:choose>
				<c:when test="${format==\"Moyen Format\"}">
					
					<option value="Moyen Format" selected="selected">Moyen Format</option>
				</c:when>
				<c:otherwise>
					<option value="Moyen Format">Moyen Format</option>
				</c:otherwise>
			</c:choose>
			 
			<!-- Grand format -->
			<c:choose>
				<c:when test="${format==\"Grand Format\"}">
					<option value="Grand Format" selected="selected">Grand Format</option>
				</c:when>
				<c:otherwise>
					<option value="Grand Format">Grand Format</option>
				</c:otherwise>
			</c:choose>
			
			
		</select>
		<br/> <br/>
		
		<label for="isbnBd">ISBN </label>
		<input type="text" id="isbnBd" name="isbnBd" value="${bd.informations.ISBN}" size="30" maxlength="30" 
			required/>
		<br/>
		
		<label for="imageBd">Lien image </label>
		<input type="text" id="imageBd" name="imageBd" value="${bd.image.value}" size="30" required/>
		<br/>
		
		<textarea id="descriptionBd" name="descriptionBd" rows="3" cols="47">${bd.description.value}
		</textarea>
		<br/>
		
		<input type="submit" value="Modifier" />
		
		<!-- input pour cacher l'identifiant du bd -->
		<input name="identifiantBd" type="hidden" value="${bd.informations.identifiant}" /> 
	</form>
	
	<a class="close-reveal-modal">&#215;</a>
</div>