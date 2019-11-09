<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
    <h3>Choose collection to add</h3>
<c:choose>
    <c:when test="${empty collections}">
        <p>No collections found</p>
    </c:when>
    <c:otherwise>
        <c:forEach items="${collections}" var="collection">
            <div class="media">
                <a href="/finishaddtocollection?collectionid=${collection.id}">
                    <div class="media-body">
                        <h4>${collection.collectionName}</h4>
                    </div>
                </a>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>
</div>