package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;

import model.Xquery;
import util.Bds;

/**
 * Servlet implementation class ModifyBd
 */
public class ModifyBd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyBd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
			IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
			IOException {
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
		int identifiant = Integer.parseInt(request.getParameter("identifiantBd"));
		
		Xquery xquery = null;
		
		// suppression de la bd et on rajoute une nouvelle bd apres, un peu plus bas
		try {
			xquery = new Xquery();
			xquery.delete(identifiant);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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

		// creation du bean bd
		Bds.Bd bd = new Bds.Bd();

		// modifications infos bd
		Bds.Bd.Informations informations = new Bds.Bd.Informations();
		informations.setTitre(titre);
		informations.setSerie(serie);
		informations.setIdentifiant(identifiant);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        // injection des bean
        request.setAttribute("bd", bd);
        
        //recuperation du dispatcher
     	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
     			"/WEB-INF/bd.jsp");
     		
     	//envoie a la jsp
     	dispatcher.include(request, response);
        
	}

}
