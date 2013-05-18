package controller;

import java.io.IOException;
import java.util.List;

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
	private Bds bdss;
	private Xquery xquery;
	
    /**
     * Default constructor. 
     */
    public Index() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
			IOException {
		try {
			xquery = new Xquery();
			bdss = DataBinding.deserialise(xquery.getResource("BD.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		int page = 1;
        int recordsPerPage = 2;
        
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        
        //Bds bds = new Bds();
        int count = ((page-1)*recordsPerPage)+2;
        if(page == 1){
        	count = 2;
        }
        
        if(count > bdss.getBd().size())
        	count = bdss.getBd().size();
        
        //System.out.println("de "+(page-1)*recordsPerPage +" a " +count);
        
        List<Bds.Bd> bds = bdss.getBd().subList((page-1)*recordsPerPage, count);
        
        int noOfRecords = bdss.getBd().size();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		
		// injection des bean
		request.setAttribute("noOfRecords", noOfRecords);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("bds", bds);
		request.setAttribute("bdss", bdss);
		request.setAttribute("currentPage", page);
		
		
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
	}

}
