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
 * Servlet implementation class BD
 */
public class BD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Bds bds;
	private Xquery xquery;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BD() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
			IOException {
		int identifiant = Integer.parseInt(request.getParameter("Identifiant"));
		
		xquery = null;
		bds = null;
		try {
			xquery = new Xquery();
			bds = DataBinding.deserialise(xquery.getXMLResource());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Bds.Bd bd = null;
		for(Bds.Bd temp : bds.getBd()){
			if(temp.getInformations().getIdentifiant() == identifiant){
				bd = temp;
				break;
			}
		}
		
		// injection des bean
		request.setAttribute("bd", bd);
				
		//recuperation du dispatcher
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/bd.jsp");
				
		//envoie a la jsp
		dispatcher.include(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
			IOException {
		
		int identifiant = Integer.parseInt(request.getParameter("supprimer hide"));
		
		try {
			xquery = new Xquery();
			xquery.delete(identifiant);
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

}
