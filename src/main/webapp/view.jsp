<!--
Copyright 2016 Google Inc.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-->
<!-- [START view] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-red.css">
<div class="container">
  <h3>Person</h3>
  <div class="btn-group">
    <a href="/update?id=${person.id}" class="btn btn-primary btn-sm">
      <i class="glyphicon glyphicon-edit"></i>
      Edit person
    </a>
    <a href="/delete?id=${person.id}" class="btn btn-danger btn-sm">
      <i class="glyphicon glyphicon-trash"></i>
      Delete person
    </a>
      <a href="/addtocollection?id=${person.id}" class="btn btn-success btn-sm">
          <i class="glyphicon glyphicon glyphicon-plus-sign"></i>
          Add to collection
      </a>
  </div>
  <br/>
  <br/>

<div class="w3-card-2" style="width:100%;">
        <header class="w3-container w3-pale-red">
        <h3>Profile of ${fn:escapeXml(person.first)} </h3>
        </header>
        <div class="w3-container">
          <div class="media">
            <div class="media-left">
              <img class="person-image" height="200" src="${fn:escapeXml(not empty person.imageUrl?person.imageUrl:'http://placekitten.com/g/128/192')}">
            </div>
            <div class="media-body">
              <h4 class="person-first">
                ${fn:escapeXml(person.first)}
              </h4>
              <h5 class="person-last">${fn:escapeXml(not empty person.last?person.last:'Unknown')}</h5>
              <p class="person-gender">Gender: ${fn:escapeXml(person.gender)}</p>
              <p class="person-job-title">Job Title: ${fn:escapeXml(person.jobTitle)}</p>
              <p class="person-interest">Interest: ${fn:escapeXml(person.interest)}</p>
              <p class="person-description">Description: ${fn:escapeXml(person.description)}</p>
              <small class="person-added-by">Added by
                ${fn:escapeXml(not empty person.createdBy?person.createdBy:'Anonymous')}</small>
            </div>
          </div>
        </div>
</div>
<br/>

    <div class="w3-card-2" style="width:100%;">
        <header class="w3-container w3-pale-red">
        <h3>Social Media Link of ${fn:escapeXml(person.first)} </h3>
        </header>
        <div class="w3-container">
         <c:choose>
             <c:when test="${empty person.linkedIn}">
                 <p>No LinkedIn Link</p>
             </c:when>
             <c:otherwise>
                 <p class="person-linkedIn"><a href="https://${fn:escapeXml(person.linkedIn)}">LinkedIn</a></p>
             </c:otherwise>
         </c:choose>
         <c:choose>
             <c:when test="${empty person.facebook}">
                 <p>No Facebook Link</p>
             </c:when>
             <c:otherwise>
                 <p class="person-facebook"><a href="https://${fn:escapeXml(person.facebook)}">Facebook</a></p>
             </c:otherwise>
         </c:choose>
         <c:choose>
             <c:when test="${empty person.twitter}">
                 <p>No Twitter Link</p>
             </c:when>
             <c:otherwise>
                 <p class="person-twitter"><a href="https://${fn:escapeXml(person.twitter)}">Twitter</a></p>
             </c:otherwise>
         </c:choose>
         <c:choose>
             <c:when test="${empty person.instagram}">
                 <p>No Instagram Link</p>
             </c:when>
             <c:otherwise>
                 <p class="person-instagram"><a href="https://${fn:escapeXml(person.instagram)}">Instagram</a></p>
             </c:otherwise>
         </c:choose>
        </div>
    </div>
    <br/>
     <div class="w3-card-2" style="width:100%;">
            <header class="w3-container w3-pale-red">
            <h3>Collections including ${fn:escapeXml(person.first)} </h3>
            </header>
            <div class="w3-container">

                <c:choose>
                    <c:when test="${empty collectionsofperson}">
                        <p>No collections found</p>
                    </c:when>
                    <c:otherwise>
                      <ul class="w3-ul" style="width:100%">
                        <c:forEach items="${collectionsofperson}" var="collection">
                            <li class="w3-display-container">
                                <a href="/readcollection?collectionid=${collection.id}">
                                        <p>${collection.collectionName}</p>
                                </a>
                            </li>
                        </c:forEach>
                        <c:if test="${not empty cursor}">
                            <nav>
                                <ul class="pager">
                                    <li><a href="?cursor=${cursor}&id=${person.id}">More</a></li>
                                </ul>
                            </nav>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </div>
     </div>
     <br/>

     <div class="w3-card-2" style="width:100%;">
            <header class="w3-container w3-pale-red">
            <h3>Posts including ${fn:escapeXml(person.first)} </h3>
            </header>
            <div class="w3-container">

                    <c:choose>
                        <c:when test="${empty postsofperson}">
                            <p>No posts found</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${postsofperson}" var="post">
                                <div class="w3-panel w3-border w3-round-xxlarge">
                                    <a href="/readpost?postid=${post.id}">
                                            <h3>${post.title}</h3>
                                    </a>
                                    <h5>person tag</h5>
                                    <c:choose>
                                        <c:when test="${empty persontagmap[post.id]}">
                                            <small>No persontags found</small>
                                        </c:when>
                                        <c:otherwise>
                                                <c:forEach items="${persontagmap[post.id]}" var="persontag">
                                                    <span class="w3-button w3-round-large w3-pale-red"><a href="/read?id=${persontag.id}"><p>${persontag.first} ${persontag.last}</p></a></span>


                                                </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                    <h5>collection tag</h5>
                                    <c:choose>
                                        <c:when test="${empty collectiontagmap[post.id]}">
                                            <small>No collectiontags found</small>
                                        </c:when>
                                        <c:otherwise>
                                                <c:forEach items="${collectiontagmap[post.id]}" var="collectiontag">
                                                    <span class="w3-button w3-round-large w3-pale-red"><a href="/readcollection?collectionid=${collectiontag.id}"><p>${collectiontag.collectionName}</p></a></span>
                                                </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                    <br/>
                                    <br/>
                                </div>
                            </c:forEach>
                            <c:if test="${not empty postcursor}">
                                <nav>
                                    <ul class="pager">
                                        <li><a href="?postcursor=${fn:escapeXml(postcursor)}&id=${person.id}">More</a></li>
                                    </ul>
                                </nav>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
            </div>
     </div>
     </div>

</div>

<!-- [END view] -->
