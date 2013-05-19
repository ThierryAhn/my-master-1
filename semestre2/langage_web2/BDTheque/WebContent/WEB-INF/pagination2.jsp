<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- Bouton précédent -->
<c:if test="${currentPage != 1}">
	<td><a href="Search?page=${currentPage - 1}">Precedent</a></td>
</c:if>

<!-- page courante, pas de lien -->
<c:forEach begin="1" end="${noOfPages}" var="i">
	<c:choose>
		<c:when test="${currentPage eq i}">
			<span class='page-numbers current'>${i}</span>
		</c:when>

		<c:otherwise>
			<a class='page-numbers' href="Search?page=${i}">${i}</a>
		</c:otherwise>
	</c:choose>
</c:forEach>

<!-- bouton suivant -->
<c:if test="${currentPage lt noOfPages}">
	<td><a href="Search?page=${currentPage + 1}">Suivant &rarr;</a></td>
</c:if>