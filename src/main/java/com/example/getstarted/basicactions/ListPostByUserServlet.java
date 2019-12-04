/*
 * Copyright 2016 Google Inc.
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
import com.example.getstarted.objects.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * list posts by user
 */
@SuppressWarnings("serial")
public class ListPostByUserServlet extends HttpServlet {

  /**
   * do get
   * @param req
   * @param resp
   * @throws IOException
   * @throws ServletException
   */
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
        ServletException {
    PostDao dao = (PostDao) this.getServletContext().getAttribute("postdao");
    String startCursor = req.getParameter("cursor");
    List<Post> posts = null;
    String endCursor = null;
    try {
      Result<Post> result =
          dao.listPostByUser((String) req.getSession().getAttribute("userId"), startCursor);
      posts = result.result;
      endCursor = result.cursor;
    } catch (Exception e) {
      throw new ServletException("Error listing collections", e);
    }
    req.getSession().getServletContext().setAttribute("posts", posts);
    req.setAttribute("cursor", endCursor);
    req.getSession().setAttribute("page", "postlist");
    req.getRequestDispatcher("/base.jsp").forward(req, resp);
  }
}
// [END example]
