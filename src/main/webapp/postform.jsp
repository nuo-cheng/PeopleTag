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
    <c:out value="${action}" /> post
  </h3>

  <form method="POST" action="${destination}" enctype="multipart/form-data">

    <div class="form-group">
      <label for="title">Title</label>
      <input type="text" name="title" id="title" value="${fn:escapeXml(post.title)}" class="form-control" />
    </div>

    <div class="form-group">
      <label for="url">URL</label>
      <input type="text" name="url" id="url" value="${fn:escapeXml(post.url)}" class="form-control" />
    </div>

    <div class="form-group">
          <label for="content">Content</label>
          <input type="text" name="content" id="content" value="${fn:escapeXml(post.content)}" class="form-control" />
    </div>

    <div class="form-group">
          <label for="personTag">Person Tag</label>
          <input type="text" name="personTag" id="personTag" value="${fn:escapeXml(post.personTag)}" class="form-control" />
    </div>

    <div class="form-group">
          <label for="collectionTag">Collection Tag</label>
          <input type="text" name="collectionTag" id="collectionTag" value="${fn:escapeXml(post.collectionTag)}" class="form-control" />
    </div>

    <div class="form-group ${isCloudStorageConfigured ? '' : 'hidden'}">
      <label for="image">Cover Image</label>
      <input type="file" name="file" id="file" class="form-control" />
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
