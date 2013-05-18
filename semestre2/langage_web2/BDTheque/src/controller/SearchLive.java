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
 * Servlet implementation class Sr
 */
public class SearchLive extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Bds bds;
	private Xquery xquery;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchLive() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			xquery = new Xquery();
			bds = DataBinding.deserialise(xquery.getResource("BD.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		if (request.getParameter("recherche").length()>0){
			Bds result = new Bds();
			for(Bds.Bd temp : bds.getBd()){
				if(temp.getInformations().getTitre().toLowerCase().contains(request.getParameter("recherche").toLowerCase())){
					result.getBd().add(temp);
				}
			}
			
			// injection des bean
			request.setAttribute("resultat", result);
			
			//recuperation du dispatcher
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/result.jsp");

			//envoie a la jsp
			dispatcher.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
