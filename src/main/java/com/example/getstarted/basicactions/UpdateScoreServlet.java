package com.example.getstarted.basicactions;

import com.example.getstarted.daos.PostDao;
import com.example.getstarted.daos.PostDaoImplement;
import com.example.getstarted.objects.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateScoreServlet")
public class UpdateScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int score=Integer.parseInt(request.getParameter("score"));
        Long id=Long.decode(request.getParameter("postid"));
        PostDao postDao=new PostDaoImplement();
        try{
            Post post=postDao.readPost(id);
            double averageScore=post.getAverageScore();
            int numOfScores=post.getNumOfScores();
            post.setNumOfScores(numOfScores+1);
            post.setAverageScore((averageScore*numOfScores+score)/(numOfScores+1));
            postDao.updatePost(post);
            response.sendRedirect("/readpost?postid="+id);
        }catch (Exception e){
            throw new ServletException("Error updating score", e);
        }
    }
}
