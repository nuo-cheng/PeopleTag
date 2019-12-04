
package com.example.getstarted.basicactions;

import com.example.getstarted.daos.*;
import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Person;
import com.example.getstarted.objects.Post;
import com.example.getstarted.objects.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * read collection
 */
public class ReadCollectionServlet extends HttpServlet {

    /**
     * do get
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startCursor = request.getParameter("cursor");
        String postStartCursor=request.getParameter("postcursor");
        Long collectionId=Long.decode(request.getParameter("collectionid"));
        AssocDao assocDao=(AssocDao) this.getServletContext().getAttribute("assocdao");
        DatastoreDao dao=(DatastoreDao) this.getServletContext().getAttribute("dao");
        CollectionDao collectionDao=(CollectionDao) this.getServletContext().getAttribute("collectiondao");
        PCAssocDao pcAssocDao=new PCAssocDaoImplement();
        PPAssocDao ppAssocDao=new PPAssocDaoImplement();
        PostDao postDao=new PostDaoImplement();
        Collection collection;
        List<Long> personIds;
        List<Person> persons=new ArrayList<>();
        List<Long> postIds;
        List<Post> posts=new ArrayList<>();
        String endCursor = null;
        String postEndCursor;
        Map<Long,List> personTagmap=new HashMap<>();
        Map<Long,List> collectionTagmap=new HashMap<>();
        try{
            collection=collectionDao.readCollection(collectionId);
            Result<Long> personIdsAndCursor=assocDao.readPersons(collectionId,startCursor);
            personIds=personIdsAndCursor.result;
            endCursor=personIdsAndCursor.cursor;
            for(int i=0;i<personIds.size();i++){
                Long id=personIds.get(i);
                Person person=dao.readPerson(id);
                persons.add(person);
            }
            Result<Long> postIdsAndCursor=pcAssocDao.readPosts(collectionId, postStartCursor);
            postIds=postIdsAndCursor.result;
            postEndCursor=postIdsAndCursor.cursor;
            for(int i=0;i<postIds.size();i++){
                Long postId=postIds.get(i);
                System.out.println(postId);
                Post post=postDao.readPost(postId);
                Result<Long> personTagIdsAndCursor=ppAssocDao.readPersons(postId,null);
                List<Long> personTagIds=personTagIdsAndCursor.result;
                List<Person> personTags=new ArrayList<>();
                for(int j=0;j<personTagIds.size();j++){
                    Person personTag=dao.readPerson(personTagIds.get(j));
                    personTags.add(personTag);
                }
                Result<Long> collectionTagIdsAndCursor=pcAssocDao.readCollections(postId,null);
                List<Long> collectionTagIds=collectionTagIdsAndCursor.result;
                List<Collection> collectionTags=new ArrayList<>();
                for(int j=0;j<collectionTagIds.size();j++){
                    Collection collectionTag=collectionDao.readCollection(collectionTagIds.get(j));
                    collectionTags.add(collectionTag);
                }
                collectionTagmap.put(postId,collectionTags);
                personTagmap.put(postId,personTags);

                posts.add(post);
            }

        } catch (Exception e) {
            throw new ServletException("Error listing persons", e);
        }
        request.setAttribute("collection",collection);
        request.getSession().getServletContext().setAttribute("persons", persons);
        request.getSession().getServletContext().setAttribute("posts", posts);
        request.getSession().getServletContext().setAttribute("persontagmap", personTagmap);
        request.getSession().getServletContext().setAttribute("collectiontagmap", collectionTagmap);
        request.setAttribute("cursor", endCursor);
        request.setAttribute("postcursor",postEndCursor);
        request.getSession().setAttribute("page", "collectionview");
        request.getRequestDispatcher("/base.jsp").forward(request, response);
    }
}

