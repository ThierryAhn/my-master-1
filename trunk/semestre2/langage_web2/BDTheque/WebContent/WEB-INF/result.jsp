<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- resultat recherche live -->
<c:choose>
	<c:when test="${fn:length(resultat.bd)>0}">
		<ul class="list">
			<c:forEach var="bd" items="${resultat.bd}">
				<li>
					<div>
						<a href="BD?Identifiant=${bd.informations.identifiant}"> <span>
								<img src="${bd.image.value}" alt="${bd.informations.titre}"
								width="30" height="30" /> ${bd.informations.titre}
						</span>
						</a>
					</div>
				</li>
			</c:forEach>
		</ul>
	</c:when>
	<c:otherwise>
		<ul>
			<li>
				<div>
					<a href="#"> <span>
						Aucun resulat
					</span>
					</a>
				</div>
			</li>
		</ul>
	</c:otherwise>
</c:choose>

