
package com.example.getstarted.basicactions;

import com.example.getstarted.daos.CollectionDao;
import com.example.getstarted.daos.PostDao;
import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Post;
import com.example.getstarted.objects.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * list collection
 */
public class ListPostServlet extends HttpServlet {
    /**
     * do get
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostDao dao = (PostDao) this.getServletContext().getAttribute("postdao");
        String startCursor = req.getParameter("cursor");
        List<Post> posts = null;
        String endCursor = null;
        try {
            Result<Post> result = dao.listPost(startCursor);
            posts = result.result;
            endCursor = result.cursor;
        } catch (Exception e) {
            throw new ServletException("Error listing collections", e);
        }
        req.getSession().getServletContext().setAttribute("posts", posts);
        req.setAttribute("cursor", endCursor);
        req.setAttribute("page", "postlist");
        req.getRequestDispatcher("/base.jsp").forward(req, resp);
    }
}

