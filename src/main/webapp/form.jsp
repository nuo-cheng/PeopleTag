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
    <c:out value="${action}" /> person
  </h3>

  <form method="POST" action="${destination}" enctype="multipart/form-data">

    <div class="form-group">
      <label for="first">First</label>
      <input type="text" name="first" id="first" value="${fn:escapeXml(person.first)}" class="form-control" />
    </div>

    <div class="form-group">
      <label for="last">Last</label>
      <input type="text" name="last" id="last" value="${fn:escapeXml(person.last)}" class="form-control" />
    </div>

    <div class="form-group">
          <label for="gender">Gender</label>
          <input type="text" name="gender" id="gender" value="${fn:escapeXml(person.gender)}" class="form-control" />
    </div>

    <div class="form-group">
          <label for="jobTitle">Job Title</label>
          <input type="text" name="jobTitle" id="jobTitle" value="${fn:escapeXml(person.jobTitle)}" class="form-control" />
    </div>

    <div class="form-group">
          <label for="interest">Interest</label>
          <input type="text" name="interest" id="interest" value="${fn:escapeXml(person.interest)}" class="form-control" />
    </div>

    <div class="form-group">
          <label for="linkedIn">LinkedIn</label>
          <input type="text" name="linkedIn" id="linkedIn" value="${fn:escapeXml(person.linkedIn)}" class="form-control" />
    </div>

    <div class="form-group">
          <label for="facebook">Facebook</label>
          <input type="text" name="facebook" id="facebook" value="${fn:escapeXml(person.facebook)}" class="form-control" />
    </div>

    <div class="form-group">
          <label for="twitter">Twitter</label>
          <input type="text" name="twitter" id="twitter" value="${fn:escapeXml(person.twitter)}" class="form-control" />
    </div>

    <div class="form-group">
          <label for="instagram">Interest</label>
          <input type="text" name="instagram" id="instagram" value="${fn:escapeXml(person.instagram)}" class="form-control" />
    </div>

    <div class="form-group">
      <label for="description">Description</label>
      <textarea name="description" id="description" class="form-control">${fn:escapeXml(person.description)}</textarea>
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
