<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>{

</style>
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
        <a href="/addpersontag?postid=${post.id}" class="btn btn-success btn-sm">
            <i class="glyphicon glyphicon glyphicon-plus-sign"></i>
            Add person tag
        </a>
        <a href="/addcollectiontag?postid=${post.id}" class="btn btn-success btn-sm">
            <i class="glyphicon glyphicon glyphicon-plus-sign"></i>
            Add collection tag
        </a>
    </div>
    <br/>
    <br/>

    <div class="w3-card-2" style="width:100%;">
            <header class="w3-container w3-pale-red">
            <h1 class="post-title">
                ${fn:escapeXml(post.title)}
            </header>
            <div class="w3-container">
            <h5 class="post-content">${fn:escapeXml(not empty post.content?post.content:'No Content')}</h5>
            </div>
            <footer class="w3-container w3-pale-red">
                        <small class="post-added-by w3-right">Added by
                            ${fn:escapeXml(not empty post.createdBy?post.createdBy:'Anonymous')}</small>
            </footer>
    </div>
    <br/>
    <div class="w3-card-2" style="width:100%">
    <header class="w3-container w3-pale-red">
    <h5>Related Links:</h5>
    </header>
    <div class="w3-container">
            <a href="https://${post.url1}">${post.url1}</a><br/>
            <a href="https://${post.url2}">${post.url2}</a><br/>
            <a href="https://${post.url3}">${post.url3}</a><br/>
    </div>
    </div>
    <br/>

    <div class="w3-cell-row">

    <div class="w3-container w3-cell">

    <div class="w3-card-2" style="width:87%;">
    <header class="w3-container w3-pale-red">
    <h5>Collection Tags</h5>
    </header>
    <div class="w3-container">
    <c:choose>
        <c:when test="${empty collectiontags}">
            <p>No collectiontags found</p>
        </c:when>
        <c:otherwise>
          <ul class="w3-ul w3-card-2 w3-col s12">
            <c:forEach items="${collectiontags}" var="collection">
                <li class="w3-display-container">
                    <a href="/readcollection?collectionid=${collection.id}">
                        <p>${collection.collectionName}</p>
                    </a>
                    <span class="w3-button w3-transparent w3-display-right">
                    <a href="/deletecollectiontag?collectionid=${collection.id}&postid=${post.id}">
                        x
                    </a>
                    </span>
                </li>
            </c:forEach>
          </ul>
        </c:otherwise>
    </c:choose>
    </div>
    </div>
    </div>

    <div class="w3-container w3-cell">

    <div class="w3-card-2" style="width:100%;">
    <header class="w3-container w3-pale-red">
    <h5>Person Tags</h5>
    </header>
    <div class="w3-container">
    <c:choose>
        <c:when test="${empty persontags}">
            <p>No persontags found</p>
        </c:when>
        <c:otherwise>
          <ul class="w3-ul w3-card-2 w3-col s12">
            <c:forEach items="${persontags}" var="person">
                <li class="w3-display-container">
                    <a href="/read?id=${person.id}">
                        <p>${person.first} ${person.last}</p>
                    </a>
                    <span class="w3-button w3-transparent w3-display-right">
                    <a href="/deletepersontag?personid=${person.id}&postid=${post.id}">
                        x
                    </a>
                    </span>
                </li>
            </c:forEach>
          </ul>
        </c:otherwise>
    </c:choose>
    </div>
    </div>

    </div>

    </div>

    <br/>
    <h4>Number of scores: ${post.numOfScores}</h4>
    <h3>Average Score: ${post.averageScore}</h3>
    <form action="/updatescore?postid=${post.id}" method="post">
        <select id="score" name="score">
            <option>0</option>
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
            <option>6</option>
            <option>7</option>
            <option>8</option>
            <option>9</option>
            <option>10</option>
        </select>
        <button type="submit" class="btn btn-success">Score</button>
    </form>
    <a href="/createcomment?postid=${post.id}">new comment</a>
    <c:choose>
        <c:when test="${empty comments}">
            <p>No comments found</p>
        </c:when>
        <c:otherwise>
            <c:forEach items="${comments}" var="comment">
                <div class="media">
                    <h4>${comment.createdBy} </h4>
                    <h4> ${comment.content}</h4>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>

</div>