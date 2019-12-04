package com.example.getstarted.basicactions;

import com.example.getstarted.daos.DatastoreDao;
import com.example.getstarted.daos.PPAssocDao;
import com.example.getstarted.daos.PPAssocDaoImplement;
import com.example.getstarted.objects.Person;
import com.example.getstarted.objects.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * add person tag
 */
@WebServlet(name = "AddPersonTagServlet")
public class AddPersonTagServlet extends HttpServlet {
    /**
     * after submit navigate to here
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] personIds=request.getParameterValues("personid");
        Long postId=(Long)request.getSession().getServletContext().getAttribute("postidtoadd");
        if(personIds==null||personIds.length==0){
            response.sendRedirect("/addpersontag?postid="+postId);
        }else {
            PPAssocDao ppAssocDao=new PPAssocDaoImplement();
            try {
                for(int i=0;i<personIds.length;i++){
                    Long personId=Long.decode(personIds[i]);
                    if(!ppAssocDao.isAlreadyIn(postId,personId)){
                        ppAssocDao.createPPAssoc(personId,postId);
                    }
                }
                response.sendRedirect("/readpost?postid="+postId);
            }catch (Exception e){
                throw new ServletException("Error adding person tag", e);
            }
        }
    }

    /**
     * go to add person tag page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postId=Long.decode(request.getParameter("postid"));
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
