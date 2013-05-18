package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import model.DataBinding;
import model.Xquery;

import util.Bds;
import util.ConvertToHtml;
import util.ConvertToPdf;

/**
 * Servlet implementation class BD
 */
public class BD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Xquery xquery;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BD() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
			IOException {
		int identifiant = Integer.parseInt(request.getParameter("Identifiant"));
		
		xquery = null;
		Bds bds = null;
		try {
			xquery = new Xquery();
			bds = DataBinding.deserialise(xquery.getResource("BD.xml"));
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
		
		
		int identifiant = Integer.parseInt(request.getParameter("identifiantBd"));
		String actionButton = request.getParameter("actionButton");
		
		Bds bdss = null;
		
		// si clic sur bouton supprimer
		if(actionButton.equals("Supprimer")){
			try {
				xquery = new Xquery();
				xquery.delete(identifiant);
				bdss = DataBinding.deserialise(xquery.getResource("BD.xml"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			int count = 2;
	        
	        if(count > bdss.getBd().size())
	        	count = bdss.getBd().size();
			
			// injection des bean
			List<Bds.Bd> bds = bdss.getBd().subList(0, count);
	        
	        int page = 1;
	        int recordsPerPage = 2;
	        
	        int noOfRecords = bdss.getBd().size();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			
			// injection des bean
			request.setAttribute("noOfRecords", noOfRecords);
			request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("bdss", bdss);
	        request.setAttribute("bds", bds);
	        request.setAttribute("currentPage", page);	
			
			//recuperation du dispatcher
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
			
			//envoie a la jsp
			dispatcher.include(request, response);
		}else{ // export pdf et html
			
			RequestDispatcher dispatcher = null;
			
			xquery = null;
			Bds bds = null;
			try {
				xquery = new Xquery();
				bds = DataBinding.deserialise(xquery.getResource("BD.xml"));
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
			
			String realPath = request.getSession().getServletContext().getRealPath("/");
			
			// fichier xml qui contient la bd courante
			String xml = DataBinding.serialisetoString(bd);
			FileWriter fstream = new FileWriter(realPath+ "/WEB-INF/classes/data/BDTEmp.xml");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(xml);
			out.close();
			
			String xmlFileLocation = request.getSession().getServletContext().getRealPath(
					"\\WEB-INF\\classes\\data\\BDTEmp.xml");
			
			// si clic sur bouton export pdf
			if(actionButton.equals("Export PDF")){
				String xslfoFileLocation = request.getSession().getServletContext().getRealPath(
						"\\WEB-INF\\classes\\model\\BD-FO.xsl");
				String pdfFileLocation = request.getSession().getServletContext().getRealPath("\\BD.pdf");
				
				// conversion en pdf
				try {
					new ConvertToPdf(xslfoFileLocation, xmlFileLocation, pdfFileLocation);
				} catch (FOPException e) {
					e.printStackTrace();
				} catch (TransformerException e) {
					e.printStackTrace();
				}
				
				//recuperation du dispatcher
				dispatcher = getServletContext().getRequestDispatcher(
		     			"/BD.pdf");
				
				// destruction fichier temporaire créé
				File file = new File(xmlFileLocation);
				file.delete();
			}else{
				// si clic sur bouton export html
				if(actionButton.equals("Export HTML")){
					String xslFileLocation = request.getSession().getServletContext().getRealPath(
							"\\WEB-INF\\classes\\data\\BD.xsl");
					String htmlFileLocation = request.getSession().getServletContext().getRealPath("" +
							"\\BD.html");
					// conversion en html
					try {
						new ConvertToHtml(xslFileLocation, xmlFileLocation, htmlFileLocation);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				//recuperation du dispatcher
				dispatcher = getServletContext().getRequestDispatcher(
		     			"/BD.html");
			}
			
			request.setAttribute("bd", bd);
			
			
			
	     	//envoie a la jsp
	     	dispatcher.include(request, response);
		}
		
	}

}
