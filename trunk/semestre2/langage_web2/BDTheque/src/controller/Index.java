package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DataBinding;
import model.Xquery;
import util.Bds;

/**
 * Servlet implementation class index
 */
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Index() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
			IOException {
		
		Bds bds = null;
		try {
			Xquery xquery = new Xquery();
			bds = DataBinding.deserialise(xquery.getXMLResource());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// injection des bean
		request.setAttribute("bds", bds);
		
		//recuperation du dispatcher
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
		
		//envoie a la jsp
		dispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
			IOException {
		// TODO Auto-generated method stub
		System.out.println("valu");
	}

}
