<!-- Actions Bd : supprimer, modifier -->
<div>
	<form method="post" action="">
		<!-- input pour cacher l'identifiant du bd -->
		<input name="identifiantBd" type="hidden"
			value="${bd.informations.identifiant}" />

		<!-- bouton supprimer -->
		<input name="actionButton" class="button" type="submit"
			value="Supprimer" />

		<!-- bouton modifier -->
		<a href="#" data-reveal-id="modifyModal" data-animation="fadeAndPop"
			data-animationspeed="300" data-closeonbackgroundclick="true"
			data-dismissmodalclass="close-reveal-modal"> <input
			class="button" type="button" value="Modifier" />
		</a>

		<!-- Export PDF -->
		<input name="actionButton" class="button" type="submit"
			value="Export PDF" />

		<!-- Visu Html -->
		<input name="actionButton" class="button" type="submit"
			value="Export HTML" />

	</form>
</div>