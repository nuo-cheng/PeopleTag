<%--jsp showing all collections of one user--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
    <h3>Posts</h3>
    <a href="/createpost" class="btn btn-success btn-sm">
        <i class="glyphicon glyphicon-plus"></i>
        Add Post
    </a>
    <c:choose>
        <c:when test="${empty posts}">
            <p>No posts found</p>
        </c:when>
        <c:otherwise>
            <c:forEach items="${posts}" var="singlepost">
                <div class="media">
                    <a href="/readpost?postid=${singlepost.id}">
                        <div class="media-body">
                            <h4>${singlepost.title}</h4>
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
