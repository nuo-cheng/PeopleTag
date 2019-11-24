<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
    <h3>Post</h3>
    <div class="btn-group">
        <a href="/updatepost?postid=${post.id}" class="btn btn-primary btn-sm">
            <i class="glyphicon glyphicon-edit"></i>
            Edit post
        </a>
        <a href="/deletepost?postid=${post.id}" class="btn btn-danger btn-sm">
            <i class="glyphicon glyphicon-trash"></i>
            Delete post
        </a>
        <a href="/addperontag?postid=${post.id}" class="btn btn-success btn-sm">
            <i class="glyphicon glyphicon glyphicon-plus-sign"></i>
            Add person tag
        </a>
        <a href="/addcollectiontag?postid=${post.id}" class="btn btn-success btn-sm">
            <i class="glyphicon glyphicon glyphicon-plus-sign"></i>
            Add collection tag
        </a>
    </div>

    <div class="media">
        <div class="media-body">
            <h4 class="post-title">
                ${fn:escapeXml(post.title)}
            </h4>
            <h5 class="post-content">${fn:escapeXml(not empty post.content?post.content:'No Content')}</h5>
            <small class="post-added-by">Added by
                ${fn:escapeXml(not empty post.createdBy?post.createdBy:'Anonymous')}</small>
        </div>
    </div>
    <h3>Collection Tags</h3>
    <c:choose>
        <c:when test="${empty collectiontags}">
            <p>No collectiontags found</p>
        </c:when>
        <c:otherwise>
            <c:forEach items="${collectiontags}" var="collection">
                <div class="media">
                    <a href="/readcollection?collectionid=${collection.id}">
                        <h4>${collection.collectionName}</h4>
                    </a>
                    <a href="/deletecollectiontag?collectionid=${collection.id}&postid=${post.id}">
                        <h4>delete tag</h4>
                    </a>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    <h3>Person Tags</h3>
    <c:choose>
        <c:when test="${empty persontags}">
            <p>No persontags found</p>
        </c:when>
        <c:otherwise>
            <c:forEach items="${persontags}" var="person">
                <div class="media">
                    <a href="/read?id=${person.id}">
                        <h4>${person.first} ${person.last}</h4>
                    </a>
                    <a href="/deletepersontag?personid=${person.id}&postid=${post.id}">
                        <h4>delete tag</h4>
                    </a>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>

</div>