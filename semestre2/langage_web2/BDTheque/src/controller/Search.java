package controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

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
	private Bds resultat = null;
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
		if(request.getParameter("page") == null){	
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
		}else{
			System.out.println("result " +resultat);
			System.out.println("taille : "+resultat.getBd().size());

			// pagination
			int page = 1;
			int recordsPerPage = 2;
			if(request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));

			int count = ((page-1)*recordsPerPage)+2;
			if(page == 1){
				count = 2;
			}

			if(count > resultat.getBd().size())
				count = resultat.getBd().size();

			List<Bds.Bd> bds = resultat.getBd().subList((page-1)*recordsPerPage, count);

			int noOfRecords = resultat.getBd().size();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

			// injection des bean
			request.setAttribute("noOfRecords", noOfRecords);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("bds", bds);
			request.setAttribute("currentPage", page);

			//recuperation du dispatcher
			RequestDispatcher dispatcher = 
					getServletContext().getRequestDispatcher("/WEB-INF/resultSearch.jsp");

			//envoie a la jsp
			dispatcher.include(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			xquery = new Xquery();
			bds = DataBinding.deserialise(xquery.getResource("BD.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* application de getter en fonction du nom de l'input.
		 * par exemple si l'utilisateur remplit le champ titre, on va donc appliquer getTitre() sur les
		 * bd.
		 */
		resultat = new Bds();
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
		
		// pagination
		int page = 1;
		int recordsPerPage = 2;
		if(request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		int count = ((page-1)*recordsPerPage)+2;
		if(page == 1){
			count = 2;
		}

		if(count > resultat.getBd().size())
			count = resultat.getBd().size();
		
		List<Bds.Bd> list = resultat.getBd().subList((page-1)*recordsPerPage, count);

		int noOfRecords = resultat.getBd().size();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		// injection des bean
		request.setAttribute("noOfRecords", noOfRecords);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("bds", list);
		request.setAttribute("currentPage", page);
		
		//recuperation du dispatcher
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/resultSearch.jsp");

		//envoie a la jsp
		dispatcher.include(request, response);
	}

}
