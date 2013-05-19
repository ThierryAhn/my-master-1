package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.DataBinding;
import model.Xquery;
import util.Bds;
import util.Bds.Bd;

/**
 * Servlet implementation class Upload
 */
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Bds bds = null;
	private Xquery xquery;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Upload() {
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
		//recuperation du dispatcher
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/upload.jsp");
		//envoie a la jsp
		dispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperation des fichiers d'upload
		InputStream file = null;
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item : items) {
				if (!item.isFormField()) { 
					file = item.getInputStream();
					break;
				}
			}
			// insertion des bd
			Bds news = DataBinding.deserialise(file);
			for(Bd bd :news.getBd()){
				xquery.insert(bd);
			}
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		}

		//recuperation du dispatcher
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/upload.jsp");
		//envoie a la jsp
		dispatcher.include(request, response);
	}
}
