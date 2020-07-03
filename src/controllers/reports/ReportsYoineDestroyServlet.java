package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Yoine;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsYoineDestroyServlet
 */
@WebServlet("/reports/yoine/destroy")
public class ReportsYoineDestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsYoineDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
		if(_token != null && _token.equals(request.getSession().getId())) {
		    EntityManager em = DBUtil.createEntityManager();
		    
		    Yoine y = em.find(Yoine.class, (Integer)(request.getSession().getAttribute("yoine_id")));
		    
		    em.getTransaction().begin();
            em.remove(y);
            em.getTransaction().commit();
            em.close();
            
            request.getSession().removeAttribute("yoine_id");
            
            response.sendRedirect(request.getContextPath() + "/reports/index");
		}
	}

}
