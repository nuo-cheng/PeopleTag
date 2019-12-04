<%--jsp showing persons in one collection--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
.grid-container {
  display: grid;
  grid-template-columns: 360px 360px 360px ;
  grid-gap: 10px;
  background-color: rgba(240, 128, 128, 0.3);
  padding: 10px;
}

.grid-container > div {
  background-color: rgba(240, 128, 128, 0.4);
  text-align: center;
  padding: 20px 0;
  font-size: 30px;
}
    .media{
        margin-top: 0px;
    }
</style>

<div class="container">
    <h3>${collection.collectionName}</h3>
    <h4>${collection.description}</h4>
    <div class="btn-group">
        <a href="/addtocollectionfromcollection?collectionid=${collection.id}" class="btn btn-primary btn-sm">
            <i class="glyphicon glyphicon-edit"></i>
            Add person
        </a>
        <a href="/deletecollection?id=${collection.id}" class="btn btn-danger btn-sm">
            <i class="glyphicon glyphicon-trash"></i>
            Delete collection
        </a>
        <a href="/movefromcollection?id=${collection.id}" class="btn btn-danger btn-sm">
            <i class="glyphicon glyphicon-trash"></i>
            Remove person from collection
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
<h3>Posts including ${fn:escapeXml(collection.collectionName)} </h3>
<c:choose>
            <c:when test="${empty posts}">
                <p>No posts found</p>
            </c:when>
            <c:otherwise>
                <c:forEach items="${posts}" var="post">
                    <div class="media">
                        <a href="/readpost?postid=${post.id}">
                            <div class="media-body">
                                <h4>${fn:escapeXml(post.title)}</h4>
                            </div>
                        </a>
                        <h5>person tag</h5>
                        <c:choose>
                            <c:when test="${empty persontagmap[post.id]}">
                                <p>No persontags found</p>
                            </c:when>
                            <c:otherwise>
                                <ul class="w3-ul w3-card-2 w3-col s12">
                                    <c:forEach items="${persontagmap[post.id]}" var="persontag">
                                        <li class="w3-display-container">
                                            <a href="/read?id=${persontag.id}">
                                                <p>${persontag.first} ${persontag.last}</p>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:otherwise>
                        </c:choose>
                        <h5>collection tag</h5>
                        <c:choose>
                            <c:when test="${empty collectiontagmap[post.id]}">
                                <p>No collectiontags found</p>
                            </c:when>
                            <c:otherwise>
                                <ul class="w3-ul w3-card-2 w3-col s12">
                                    <c:forEach items="${collectiontagmap[post.id]}" var="collectiontag">
                                        <li class="w3-display-container">
                                            <a href="/readcollection?collectionid=${collectiontag.id}">
                                                <p>${collectiontag.collectionName}</p>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:forEach>
                <c:if test="${not empty postcursor}">
                    <nav>
                        <ul class="pager">
                            <li><a href="?postcursor=${fn:escapeXml(postcursor)}&collectionid=${collection.id}">More</a></li>
                        </ul>
                    </nav>
                </c:if>
            </c:otherwise>
        </c:choose>
