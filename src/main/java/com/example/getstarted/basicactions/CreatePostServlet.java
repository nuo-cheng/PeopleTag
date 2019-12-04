/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.getstarted.basicactions;

import com.example.getstarted.daos.PostDao;
import com.example.getstarted.objects.Post;
import com.example.getstarted.util.CloudStorageHelper;
import com.google.common.base.Strings;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// [START example]

/**
 * create post
 */
@SuppressWarnings("serial")
public class CreatePostServlet extends HttpServlet {

  // go to post form
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    req.setAttribute("action", "Add");          // Part of the Header in form.jsp
    req.setAttribute("destination", "createpost");  // The urlPattern to invoke (this Servlet)
    req.setAttribute("page", "postform");           // Tells base.jsp to include form.jsp
    req.getRequestDispatcher("/base.jsp").forward(req, resp);
  }
  // [END setup]

  // [START formpost]

  /**
   * finish add post
   * @param req
   * @param resp
   * @throws ServletException
   * @throws IOException
   */
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    assert ServletFileUpload.isMultipartContent(req);
    CloudStorageHelper storageHelper =
        (CloudStorageHelper) getServletContext().getAttribute("storageHelper");

    String newImageUrl = null;
    Map<String, String> params = new HashMap<String, String>();
    try {
      FileItemIterator iter = new ServletFileUpload().getItemIterator(req);
      while (iter.hasNext()) {
        FileItemStream item = iter.next();
        if (item.isFormField()) {
          params.put(item.getFieldName(), Streams.asString(item.openStream()));
        } else if (!Strings.isNullOrEmpty(item.getName())) {
          newImageUrl = storageHelper.uploadFile(
              item, getServletContext().getInitParameter("personshelf.bucket"));
        }
      }
    } catch (FileUploadException e) {
      throw new IOException(e);
    }

    // [START createdBy]
    String createdByString = "";
    String createdByIdString = "";
    HttpSession session = req.getSession();
    if (session.getAttribute("userEmail") != null) { // Does the user have a logged in session?
      createdByString = (String) session.getAttribute("userEmail");
      createdByIdString = (String) session.getAttribute("userId");
    }
    // [END createdBy]

    // [START personBuilder]
    Post post = new Post.Builder()
        .title(params.get("title"))
        .content(params.get("content"))
        .url1(params.get("url1"))
        .url2(params.get("url2"))
        .url3(params.get("url3"))
        .imageUrl(null == newImageUrl ? params.get("imageUrl") : newImageUrl)
        // [START auth]
        .createdBy(createdByString)
        .createdById(createdByIdString)
        // [END auth]
        .build();
    // [END personBuilder]

    PostDao dao = (PostDao) this.getServletContext().getAttribute("postdao");
    try {
      Long id = dao.createPost(post);
      resp.sendRedirect("/post");   // read what we just wrote
    } catch (Exception e) {
      throw new ServletException("Error creating person", e);
    }
  }
  // [END formpost]
}
// [END example]
