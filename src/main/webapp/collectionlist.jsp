<%--jsp showing all collections of one user--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-red.css">
<div class="container">
    <h3>Collections</h3>
    <a href="/createcollection" class="btn btn-success btn-sm">
        <i class="glyphicon glyphicon-plus"></i>
        Add Collection
    </a>
    <br/>
    <br/>

    <c:choose>
        <c:when test="${empty collections}">
            <p>No collections found</p>
        </c:when>
        <c:otherwise>
          <ul class="w3-ul w3-card-4" style="width:100%">
            <c:forEach items="${collections}" var="collection">
                <li class="w3-display-container">
                    <a href="/readcollection?collectionid=${collection.id}">
                            <h4>${collection.collectionName}</h4>
                    </a>
                </li>
            </c:forEach>
          </ul>
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
