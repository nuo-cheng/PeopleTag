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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * update post
 */
@SuppressWarnings("serial")
public class UpdatePostServlet extends HttpServlet {
  /**
   * do get
   * @param req
   * @param resp
   * @throws ServletException
   * @throws IOException
   */
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    PostDao dao = (PostDao) this.getServletContext().getAttribute("postdao");
    try {
      Post post = dao.readPost(Long.decode(req.getParameter("postid")));
      req.setAttribute("post", post);
      req.setAttribute("action", "Edit");
      req.setAttribute("destination", "updatepost?postid="+post.getId());
      req.setAttribute("page", "postform");
      req.getRequestDispatcher("/base.jsp").forward(req, resp);
    } catch (Exception e) {
      throw new ServletException("Error loading person for editing", e);
    }
  }

  /**
   * do post
   * @param req
   * @param resp
   * @throws ServletException
   * @throws IOException
   */
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    PostDao dao = (PostDao) this.getServletContext().getAttribute("postdao");

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

    try {
      Post oldPost = dao.readPost(Long.decode(req.getParameter("postid")));

      // [START personBuilder]
      Post post = new Post.Builder()
          .title(params.get("title"))
          .content(params.get("content"))
          .url1(params.get("url1"))
          .url2(params.get("url2"))
          .url3(params.get("url3"))
          .imageUrl(null == newImageUrl ? params.get("imageUrl") : newImageUrl)
          .id(Long.decode(req.getParameter("postid")))
          .createdBy(oldPost.getCreatedBy())
          .createdById(oldPost.getCreatedById())
          .build();
      // [END personBuilder]

      dao.updatePost(post);
      resp.sendRedirect("/readpost?postid=" + req.getParameter("postid"));
    } catch (Exception e) {
      throw new ServletException("Error updating person", e);
    }
  }
}
// [END example]
