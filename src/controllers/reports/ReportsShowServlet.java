package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    EntityManager em = DBUtil.createEntityManager();
	    
	    Report r = em.find(Report.class,  Integer.parseInt(request.getParameter("id")));
	    
	    System.out.println("レポートIDは" + r.getId());
	    
	    Employee emp = (Employee)request.getSession().getAttribute("login_employee");
	    
	    System.out.println("ユーザーIDは" + emp.getId());
	    
	    Report y = null;
	    String p = "plus";
	    String m = "mainasu";
	    
	    try{
	            y = em.createNamedQuery("getYoine", Report.class)
	                         .setParameter("id", r)
	                         .setParameter("emp", emp)
	                         .getSingleResult();
	    
	            System.out.println("いいね済レポートIDは" + y.getId());
	            
	            Integer y_id = em.createNamedQuery("getId", Integer.class)
                        .setParameter("id", r)
                        .setParameter("emp", emp)
                        .getSingleResult();

                System.out.println("いいねIDは" + y_id);
                
                request.getSession().setAttribute("yoine_id", y_id);
	            
	    } catch(Exception e){
	            
	            request.setAttribute("yoine", p);
	    }
	    
	    if( y == r ){
	        request.setAttribute("yoine", m);
	    } else {
	        request.setAttribute("yoine", p);
	    }
	    
	    System.out.println(p + ":" + m);
	    
	    try{
            long yoineCount = (long)em.createNamedQuery("getCount", Long.class)
                    .setParameter("id", r)
                    .getSingleResult();

            System .out.println("いいねカウントは" + yoineCount);

            request.setAttribute("yoineCount", yoineCount);

	    } catch(Exception e) {
	        
	        request.setAttribute("yoineCount", "0");
	        
	        em.close();
	    }
	    
	    request.setAttribute("report", r);
	    request.setAttribute("_token", request.getSession().getId());
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
	    rd.forward(request, response);
	}
}
