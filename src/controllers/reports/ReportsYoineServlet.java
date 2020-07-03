package controllers.reports;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Count;
import models.Employee;
import models.Report;
import models.Yoine;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsYoineServlet
 */
@WebServlet("/reports/yoine")
public class ReportsYoineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsYoineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	        EntityManager em = DBUtil.createEntityManager();
	        
	        Report r = em.find(Report.class,  Integer.parseInt(request.getParameter("id")));
	        
	        Count c = (Count)request.getAttribute("count");
	        
	        if(c == null) {
	            c = new Count();
	            request.setAttribute("count", c);
	        }
	        
	        String plus = request.getParameter("count");
	        
	        if(plus != null) {
                int i = c.getCount();
                i++;
                c.setCount(i);
               
                System.out.println(i);
            }
	        
	        Yoine y = new Yoine();
	        
		    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		    y.setCreated_at(currentTime);
		    y.setUpdated_at(currentTime);
		    
		    y.setReport(r);
		    
		    y.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
		    
		    em.getTransaction().begin();
		    em.persist(y);
		    em.getTransaction().commit();
		    em.close();
		    
		    request.setAttribute("count", y);
		    request.setAttribute("report", r);
            request.setAttribute("_token", request.getSession().getId());
            
		    
		    response.sendRedirect(request.getContextPath() + "/reports/index");
		}
    }
