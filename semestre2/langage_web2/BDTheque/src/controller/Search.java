package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DataBinding;
import model.Xquery;
import util.Bds;
import util.Bds.Bd;

/**
 * Servlet implementation class Search
 */
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Bds bds = null;
	private Xquery xquery;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
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
		//recuperation du dispatcher
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/search.jsp");
		//envoie a la jsp
		dispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bds resultat = new Bds();
		Method[] allmethode = Bd.Informations.class.getDeclaredMethods();
		for(Bd bd : bds.getBd()){
			boolean searched = true;
			for(String param: request.getParameterMap().keySet()){
				for (Method m : allmethode) {
					if(m.getName().equals("get"+param)){
						String val = null;
						try {
							val = (String) m.invoke(bd.getInformations(), (Object[])m.getParameterTypes());
						} catch (Exception e) {
							e.printStackTrace();
						} 
						for(String s:request.getParameterMap().get(param))
							if(!val.toLowerCase().contains(s.toLowerCase()) && !request.getParameter(param).equals(null)){
								searched=false;
							}
					}
				}
			}
			if (searched){
				resultat.getBd().add(bd);
			}
		}	

		// injection des bean
		request.setAttribute("resultat", resultat);

		//recuperation du dispatcher
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/resultSearch.jsp");

		//envoie a la jsp
		dispatcher.include(request, response);
	}

}
