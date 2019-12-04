<jsp:useBean id="person" scope="request" type="com.gargoylesoftware.htmlunit.html.DomElement"/>
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
<!-- [START form] -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="container">
  <h3>
    <c:out value="${action}" /> collection
  </h3>

  <form method="POST" action="${destination}" enctype="multipart/form-data">

    <div class="form-group">
      <label for="collectionName">Collection Name</label>
      <input type="text" name="collectionName" id="collectionName" value="${fn:escapeXml(collection.collectionName)}" class="form-control" />
    </div>

    <div class="form-group">
      <label for="description">Description</label>
      <input type="text" name="description" id="description" value="${fn:escapeXml(collection.description)}" class="form-control" />
    </div>

    <div class="form-group hidden">
      <label for="imageUrl">Cover Image URL</label>
      <input type="hidden" name="id" value="${person.id}" />
      <input type="text" name="imageUrl" id="imageUrl" value="${fn:escapeXml(person.imageUrl)}" class="form-control" />
    </div>

    <button type="submit" class="btn btn-success">Save</button>
  </form>
</div>
<!-- [END form] -->
