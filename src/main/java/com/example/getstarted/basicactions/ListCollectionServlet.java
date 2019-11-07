package com.example.getstarted.basicactions;

import com.example.getstarted.daos.CloudSqlDao;
import com.example.getstarted.daos.CollectionDao;
import com.example.getstarted.daos.DatastoreDao;
import com.example.getstarted.daos.PersonDao;
import com.example.getstarted.objects.Collection;
import com.example.getstarted.objects.Person;
import com.example.getstarted.objects.Result;
import com.example.getstarted.util.CloudStorageHelper;
import com.google.common.base.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListCollectionServlet")
public class ListCollectionServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        PersonDao dao = null;

        CloudStorageHelper storageHelper = new CloudStorageHelper();

        // Creates the DAO based on the Context Parameters
        String storageType = this.getServletContext().getInitParameter("personshelf.storageType");
        switch (storageType) {
            case "datastore":
                dao = new DatastoreDao();
                break;
            case "cloudsql":
                try {
                    // Use this url when using dev appserver, but connecting to Cloud SQL
                    String connect = this.getServletContext().getInitParameter("sql.urlRemote");
                    if (connect.contains("localhost")) {
                        // Use this url when using a local mysql server
                        connect = this.getServletContext().getInitParameter("sql.urlLocal");
                    } else if (System.getProperty("com.google.appengine.runtime.version")
                            .startsWith("Google App Engine/")) {
                        // Use this url when on App Engine, connecting to Cloud SQL.
                        // Uses a special adapter because of the App Engine sandbox.
                        connect = this.getServletContext().getInitParameter("sql.urlRemoteGAE");
                    }
                    dao = new CloudSqlDao(connect);

                } catch (SQLException e) {
                    throw new ServletException("SQL error", e);
                }
                break;
            default:
                throw new IllegalStateException(
                        "Invalid storage type. Check if personshelf.storageType property is set.");
        }
        this.getServletContext().setAttribute("dao", dao);
        this.getServletContext().setAttribute("storageHelper", storageHelper);
        this.getServletContext().setAttribute(
                "isCloudStorageConfigured",  // Hide upload when Cloud Storage is not configured.
                !Strings.isNullOrEmpty(getServletContext().getInitParameter("personshelf.bucket")));
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CollectionDao dao = (CollectionDao) this.getServletContext().getAttribute("dao");
        String startCursor = req.getParameter("cursor");
        List<Collection> collections = null;
        String endCursor = null;
        try {
            Result<Collection> result = dao.listCollections(startCursor);
            collections = result.result;
            endCursor = result.cursor;
        } catch (Exception e) {
            throw new ServletException("Error listing persons", e);
        }
        req.getSession().getServletContext().setAttribute("collections", collections);
        req.setAttribute("cursor", endCursor);
        req.setAttribute("page", "collectionslist");
        req.getRequestDispatcher("/base.jsp").forward(req, resp);
    }
}
