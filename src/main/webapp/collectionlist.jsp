<%--jsp showing all collections of one user--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
    <h3>Collections</h3>
    <a href="/createcollection" class="btn btn-success btn-sm">
        <i class="glyphicon glyphicon-plus"></i>
        Add Collection
    </a>
    <c:choose>
        <c:when test="${empty collections}">
            <p>No collections found</p>
        </c:when>
        <c:otherwise>
            <c:forEach items="${collections}" var="collection">
                <div class="media">
                    <a href="/readcollection?collectionid=${collection.id}">
                        <div class="media-body">
                            <h4>${collection.collectionName}</h4>
                        </div>
                    </a>
                </div>
            </c:forEach>
            <c:if test="${not empty cursor}">
                <nav>
                    <ul class="pager">
                        <li><a href="?cursor=${cursor}">More</a></li>
                    </ul>
                </nav>
            </c:if>
        </c:otherwise>
    </c:choose>
</div>
