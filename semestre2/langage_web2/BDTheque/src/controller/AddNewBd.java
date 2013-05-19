package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import model.DataBinding;
import model.Xquery;

import org.joda.time.DateTime;

import util.Bds;

/**
 * Servlet implementation class AddNewBd
 */
public class AddNewBd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewBd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperation des donnees entrees
		String titre = request.getParameter("titreBd");
		String serie = request.getParameter("serieBd");
		String scenario = request.getParameter("scenarioBd");
		String dessin = request.getParameter("dessinBd");
		String couleurs = request.getParameter("couleursBd");
		String editeur = request.getParameter("editeurBd");
		String format = request.getParameter("formatBd");
		String isbn = request.getParameter("isbnBd");
		String image = request.getParameter("imageBd");
		String description = request.getParameter("descriptionBd");
		
		// date courante
		DateTime dateTime = new DateTime();
		
		XMLGregorianCalendar xgcal = null;
		try {
			xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			xgcal.setYear(dateTime.getYear());
			xgcal.setMonth(dateTime.getMonthOfYear());
			xgcal.setDay(dateTime.getDayOfMonth());
		} catch (DatatypeConfigurationException e1) {
			e1.printStackTrace();
		}
		
		Xquery xquery = null;
		Bds bdss = null;
		try {
			xquery = new Xquery();
			bdss = DataBinding.deserialise(xquery.getResource("BD.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        // creation du bean bd
        Bds.Bd bd = new Bds.Bd();
        
        // infos bd
        Bds.Bd.Informations informations = new Bds.Bd.Informations();
        informations.setTitre(titre);
        informations.setSerie(serie);
        int lastId = bdss.getBd().get(bdss.getBd().size()-1).getInformations().getIdentifiant();
        informations.setIdentifiant(lastId + 1);
        informations.setScenario(scenario);
        informations.setDessin(dessin);
        informations.setCouleurs(couleurs);
        informations.setEditeur(editeur);
        informations.setFormat(format);
        informations.setISBN(isbn);
        informations.setDate(xgcal);
        bd.setInformations(informations);
        
        // image bd
        Bds.Bd.Image imageBd = new Bds.Bd.Image();
        imageBd.setValue(image);
        bd.setImage(imageBd);
        
        // description bd
        Bds.Bd.Description descriptionBd = new Bds.Bd.Description();
        descriptionBd.setValue(description);
        bd.setDescription(descriptionBd);
        
        
        // execution de l'insert
        try {
			xquery.insert(bd);
			xquery = new Xquery();
			bdss = DataBinding.deserialise(xquery.getResource("BD.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        int count = 2;
        
        if(count > bdss.getBd().size())
        	count = bdss.getBd().size();
        
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
	}

}
