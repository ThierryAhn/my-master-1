<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="sidebar">
	<div id="tabs">
		<c:forEach var="i" begin="0" end="3">
			<div class="boxes box inside">
				
				<c:if test="${not empty bdss.bd[fn:length(bdss.bd)-i-1].informations.titre}">
					<!-- infos bd -->
					<div class="ensNew">
						<table>
							<tr>
								<!-- <td>Titre :</td> -->
								<td colspan="2"><c:out
										value="${bdss.bd[fn:length(bdss.bd)-i-1].informations.titre}" /></td>
							</tr>
							<tr>
								<td>Serie :</td>
								<td><c:out
										value="${bdss.bd[fn:length(bdss.bd)-i-1].informations.serie}" /></td>
							</tr>
							<tr>
								<td>Crée le :</td>
								<td><c:out
										value="${bdss.bd[fn:length(bdss.bd)-i-1].informations.date}" /></td>
							</tr>
						</table>
						<img alt="nouveau" src="img/nouveau.jpg" class="new" />
					</div>
					<!-- image nouveau -->
					<a title="${bdss.bd[fn:length(bdss.bd)-i-1].informations.titre}"
						href="BD?Identifiant=${bdss.bd[fn:length(bdss.bd)-i-1].informations.identifiant}">
						<img class="floatRight" src="${bdss.bd[fn:length(bdss.bd)-i-1].image.value}"
						alt="${bdss.bd[fn:length(bdss.bd)-i-1].informations.titre}" width="100" height="130" /> 
					</a>
				</c:if>
				
			</div>
		</c:forEach>
	</div>
</div>