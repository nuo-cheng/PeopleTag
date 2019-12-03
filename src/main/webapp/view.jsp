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
    <h3>Collections including ${fn:escapeXml(person.first)} </h3>
    <c:choose>
        <c:when test="${empty collectionsofperson}">
            <p>No collections found</p>
        </c:when>
        <c:otherwise>
            <c:forEach items="${collectionsofperson}" var="collection">
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
                        <li><a href="?cursor=${cursor}&id=${person.id}">More</a></li>
                    </ul>
                </nav>
            </c:if>
        </c:otherwise>
    </c:choose>

    <h3>Posts including ${fn:escapeXml(person.first)} </h3>
        <c:choose>
            <c:when test="${empty postsofperson}">
                <p>No posts found</p>
            </c:when>
            <c:otherwise>
                <c:forEach items="${postsofperson}" var="post">
                    <div class="media">
                        <a href="/readpost?postid=${post.id}">
                            <div class="media-body">
                                <h4>${post.title}</h4>
                            </div>
                        </a>
                    </div>
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

<!-- [END view] -->
