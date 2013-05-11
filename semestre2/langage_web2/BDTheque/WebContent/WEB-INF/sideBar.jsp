<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="sidebar">
	<div id="tabs">
		<c:forEach var="i" begin="0" end="5" step="1">
			<div class="boxes box inside">

				<div class="ensNew">
					<table>
						<tr>
							<td>Titre :</td>
							<td><c:out
									value="${bds.bd[fn:length(bds.bd)-i-1].informations.titre}" /></td>
						</tr>
						<tr>
							<td>Serie :</td>
							<td><c:out
									value="${bds.bd[fn:length(bds.bd)-i-1].informations.serie}" /></td>
						</tr>
						<tr>
							<td>Crée le :</td>
							<td><c:out
									value="${bds.bd[fn:length(bds.bd)-i-1].informations.date}" /></td>
						</tr>
					</table>
					<img alt="nouveau" src="img/nouveau.jpg" class="new" />
				</div>
				<a title="${bds.bd[fn:length(bds.bd)-i-1].informations.titre}"
					href="#"><img class="floatRight"
					src="${bds.bd[fn:length(bds.bd)-i-1].image.value}"
					alt="${bds.bd[fn:length(bds.bd)-i-1].informations.titre}"
					width="100" height="130" /> </a>
			</div>
		</c:forEach>
	</div>
</div>