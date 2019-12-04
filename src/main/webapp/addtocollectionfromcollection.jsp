<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
    <h3>Choose person to add</h3>
<c:choose>
    <c:when test="${empty personlist}">
        <p>No persons found</p>
    </c:when>
    <c:otherwise>
        <form action="/finishaddtocollectionfromcollection">
        <c:forEach items="${personlist}" var="person">
            <div class="media">
                <input type="checkbox" name="personid" value="${person.id}"> ${person.first}&nbsp${person.last}<br>
                </c:forEach>
                    <button type="submit">Submit</button><br>
                </form>
<%--                <a href="/finishaddtocollectionfromcollection?collectionid=${collection.id}">--%>
<%--                    <div class="media-body">--%>
<%--                        <h4>${collection.collectionName}</h4>--%>
<%--                    </div>--%>
<%--                </a>--%>
            </div>
        <c:if test="${not empty cursor}">
            <nav>
                <ul class="pager">
                    <li><a href="?cursor=${cursor}&collectionid=${collectionidtoadd}">More</a></li>
                </ul>
            </nav>
        </c:if>
    </c:otherwise>
</c:choose>
</div>