package com.example.getstarted.basicactions;

import com.example.getstarted.daos.DatastoreDao;
import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Person;
import com.example.getstarted.objects.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddPersonTagServlet")
public class AddPersonTagServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postId=Long.decode(request.getParameter("postId"));
        DatastoreDao dao=(DatastoreDao) this.getServletContext().getAttribute("dao");
        String startCursor=request.getParameter("cursor");
        List<Person> persons=null;
        String endCursor=null;
        try{
            Result<Person> result=dao.listPersons(startCursor);
            persons=result.result;
            endCursor=result.cursor;
        }catch (Exception e){
            throw new ServletException("Error listing persons", e);
        }
        request.getSession().getServletContext().setAttribute("persons",persons);;
        request.getSession().getServletContext().setAttribute("postidtoadd",postId);
        request.setAttribute("cursor",endCursor);
        request.getSession().setAttribute("page","addpersontag");
        request.getRequestDispatcher("/base.jsp").forward(request,response);
    }
}
