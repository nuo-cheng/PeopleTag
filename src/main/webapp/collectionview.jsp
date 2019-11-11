<%--jsp showing persons in one collection--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
.grid-container {
  display: grid;
  grid-template-columns: 360px 360px 360px ;
  grid-gap: 10px;
  background-color: #FFFFFF;
  padding: 10px;
}

.grid-container > div {
  background-color: rgba(240, 128, 128, 0.3);
  text-align: center;
  padding: 20px 0;
  font-size: 30px;
}
</style>

<div class="container">
    <h3>${collection.collectionName}</h3>
    <div class="btn-group">
        <a href="/updatecollection?id=${collection.id}" class="btn btn-primary btn-sm">
            <i class="glyphicon glyphicon-edit"></i>
            Add person
        </a>
        <a href="/deletecollection?id=${collection.id}" class="btn btn-danger btn-sm">
            <i class="glyphicon glyphicon-trash"></i>
            Delete collection
        </a>
        <a href="/movefromcollection?id=${collection.id}" class="btn btn-danger btn-sm">
            <i class="glyphicon glyphicon-trash"></i>
            Move person from collection
        </a>
    </div>

    <br />
    <br />
    <div class="grid-container">
    <c:choose>
        <c:when test="${empty persons}">
            <p>No persons found</p>
        </c:when>
        <c:otherwise>
            <c:forEach items="${persons}" var="person">
                <div class="media">
                    <a href="/read?id=${person.id}">
                        <div class="media-left">
                            <img alt="ahhh" hspace="15"height="200"src="${fn:escapeXml(not empty person.imageUrl?person.imageUrl:'http://placekitten.com/g/128/192')}">
                        </div>
                        <div class="media-body">
                            <h4>${fn:escapeXml(person.first)}</h4>
                            <p>${fn:escapeXml(person.last)}</p>
                        </div>
                    </a>
                </div>
            </c:forEach>
            <c:if test="${not empty cursor}">
                <nav>
                    <ul class="pager">
                        <li><a href="?cursor=${fn:escapeXml(cursor)}&collectionid=${collection.id}">More</a></li>
                    </ul>
                </nav>
            </c:if>
        </c:otherwise>
    </c:choose>
</div>
