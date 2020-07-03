package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Yoine;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsYoineEditServlet
 */
@WebServlet("/reports/yoine/edit")
public class ReportsYoineEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsYoineEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    EntityManager em = DBUtil.createEntityManager();
	    
	    Yoine y = em.find(Yoine.class, Integer.parseInt(request.getParameter("id")));
	    
	    em.close();
	    
	    request.setAttribute("yoine", y);
	    request.setAttribute("_token", request.getSession().getId());
	    request.getSession().setAttribute("yoine_id", y.getId());
	    
	    System.out.println(y.getId());
	    
	    
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/yoineEdit.jsp");
	    rd.forward(request, response);
	}

}
