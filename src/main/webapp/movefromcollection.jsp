<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
    <h3>Choose person to move</h3>
<c:choose>
    <c:when test="${empty persons}">
        <p>No person found</p>
    </c:when>
    <c:otherwise>
        <c:forEach items="${persons}" var="person">
            <div class="media">
                <a href="/finishmovefromcollection?personid=${person.id}">
                    <div class="media-body">
                        <h4>${person.last} ${person.first}</h4>
                    </div>
                </a>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>
</div>