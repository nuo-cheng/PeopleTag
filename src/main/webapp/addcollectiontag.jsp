<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
<h3>Choose collection to add tag</h3>
<c:choose>
    <c:when test="${empty collections}">
        <p>No collections found</p>
    </c:when>
    <c:otherwise>
        <form action="/addcollectiontag">
            <c:forEach items="${collections}" var="collection">
            <div class="media">
                <input type="checkbox" name="collectionid" value="${collection.id}"> ${collection.collectionName}<br>
                </c:forEach>
                <button type="submit">Submit</button><br>
        </form>
        <%--                <a href="/finishaddtocollection?collectionid=${collection.id}">--%>
        <%--                    <div class="media-body">--%>
        <%--                        <h4>${collection.collectionName}</h4>--%>
        <%--                    </div>--%>
        <%--                </a>--%>
        </div>
        <c:if test="${not empty cursor}">
            <nav>
                <ul class="pager">
                    <li><a href="?cursor=${cursor}&postid=${postidtoadd}">More</a></li>
                </ul>
            </nav>
        </c:if>
    </c:otherwise>
</c:choose>
</div>