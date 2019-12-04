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
      <input type="text" name="title" id="title" value="${post.title}" class="form-control" />
    </div>

    <div class="form-group">
      <label for="url1">URL1</label>
      <input type="text" name="url1" id="url1" value="${post.url1}" class="form-control" />
    </div>

    <div class="form-group">
      <label for="url2">URL2</label>
      <input type="text" name="url2" id="url2" value="${post.url2}" class="form-control" />
    </div>

    <div class="form-group">
      <label for="url3">URL</label>
      <input type="text" name="url3" id="url3" value="${post.url3}" class="form-control" />
    </div>

    <div class="form-group">
          <label for="content">Content</label>
          <textarea cols="10" rows="6" name="content" id="content" value="${post.content}" class="form-control" >
          </textarea>


<!--          <input type="text" name="content" id="content" value="${post.content}" class="form-control" />
-->
    </div>

    <div class="form-group ${isCloudStorageConfigured ? '' : 'hidden'}">
      <label for="image">Cover Image</label>
      <input type="file" name="file" id="file" class="form-control" />
    </div>

    <div class="form-group hidden">
      <label for="imageUrl">Cover Image URL</label>
      <input type="hidden" name="id" value="${person.id}" />
      <input type="text" name="imageUrl" id="imageUrl" value="${person.imageUrl}" class="form-control" />
    </div>

    <button type="submit" class="btn btn-success">Save</button>
  </form>
</div>
<!-- [END form] -->
