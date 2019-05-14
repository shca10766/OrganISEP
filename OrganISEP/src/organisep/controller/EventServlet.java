package organisep.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import organisep.bean.EventBean;
import organisep.model.EventDao;

/**
 * Servlet implementation class EventServlet
 */
@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
	private Gson gson = new Gson();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if (action.equals("etiquettes")) {
			EventDao eventDao = new EventDao();
			ArrayList<EventBean> listEvents = new ArrayList<EventBean>();
			
			listEvents = eventDao.getEvents(listEvents);
			String events = this.gson.toJson(listEvents);
			
			response.setContentType("application/json");
			response.getWriter().write(events);
		}
		
		else if (action.equals("salle")) {
			String date = request.getParameter("date");
			String type = request.getParameter("type");
			EventDao e = new EventDao();
			
			if (type.equals("salle")) {
				ArrayList<String> l_salles = e.getListSalles(date);
				String salles = this.gson.toJson(l_salles);
				
				response.setContentType("application/json");
				response.getWriter().write(salles);
			}
			else {
				response.setContentType("text/plain");
				response.getWriter().write("1");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*String nom = request.getParameter("Nom");
		int nbr_participant = Integer.parseInt(request.getParameter("Nbr"));
		String d = request.getParameter("Date");
		String t = request.getParameter("Time");
		int budget = Integer.parseInt(request.getParameter("Budget"));
		String lien = request.getParameter("Lien");
		
		String[] salles = request.getParameterValues("Salles");
		ArrayList<String> listSalles = new ArrayList<String>(Arrays.asList(salles));
		String[] ressources = request.getParameterValues("Ress");
		ArrayList<String> listRessources = new ArrayList<String>(Arrays.asList(ressources));
		
		String desc = request.getParameter("Desc");
		
		String imagelogo = "img/imgUser/logoJunior.png";
		String imageEvent = "img/noImage.png";
		String creat = "Junior isep";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date da = sdf.parse(d);
			EventBean e = new EventBean(nom, da, imagelogo, listSalles, creat, imageEvent, nbr_participant, budget, lien, listRessources, desc);
			EventDao ed = new EventDao();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
