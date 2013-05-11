package controller;

import java.io.File;
import java.io.IOException;

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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.xmldb.api.base.XMLDBException;

import util.Bds;

import java.sql.Date;
import java.util.GregorianCalendar;

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
        GregorianCalendar gcal = new GregorianCalendar();
        XMLGregorianCalendar xgcal = null;
		try {
			xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
		Xquery xquery = null;
		Bds bds = null;
		try {
			xquery = new Xquery();
			bds = DataBinding.deserialise(xquery.getXMLResource());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // creation du bean bd
        Bds.Bd bd = new Bds.Bd();
        
        // infos bd
        Bds.Bd.Informations informations = new Bds.Bd.Informations();
        informations.setTitre(titre);
        informations.setSerie(serie);
        informations.setIdentifiant(bds.getBd().size() + 1);
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
			bds = DataBinding.deserialise(xquery.getXMLResource());
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
