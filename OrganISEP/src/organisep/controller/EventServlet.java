package organisep.controller;

import java.io.IOException;
import java.text.DateFormat;
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
		String nom = request.getParameter("Nom");
		String d = request.getParameter("Date");
		String t = request.getParameter("Time");
		String lien = request.getParameter("Lien");		
		String desc = request.getParameter("Desc");
		
		int idCreat = 0;
		if (!request.getParameter("IdCreat").equals("")) {
			idCreat = Integer.parseInt(request.getParameter("IdCreat"));
		}
		
		int nbr_participant = 0;
		if (!request.getParameter("Nbr").equals("")) {
			nbr_participant = Integer.parseInt(request.getParameter("Nbr"));
		}
		
		int budget = 0;
		if (!request.getParameter("Budget").equals("")) {
			budget = Integer.parseInt(request.getParameter("Budget"));
		}
		
		String[] salles = request.getParameterValues("Salles[]");
		ArrayList<Integer> listSalles = new ArrayList<Integer>();
		if (salles != null) {
			for (int i = 0; i < salles.length; i++) {
				String[] idSalle = salles[i].split("_");
				listSalles.add(Integer.parseInt(idSalle[1]));
			}
		}		
		String[] ressources = request.getParameterValues("Ress[]");
		ArrayList<String> listRessources = new ArrayList<String>();
		if (ressources != null) {
			listRessources = new ArrayList<String>(Arrays.asList(ressources));
		}
		
		String imageEvent = "img/imgEvent/noImage.png";
		
		SimpleDateFormat old_sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date da;
		try {
			da = old_sdf.parse(d);
			d = sdf.format(da);
			EventDao eventDao = new EventDao();
			String creat = eventDao.getCreateur(idCreat, "nom");
			EventBean eventBean = new EventBean(nom, d, t, imageEvent, creat, nbr_participant, budget, lien, listRessources, desc);
			int idEvent = eventDao.createEvent(eventBean, idCreat);
			
			for (int j = 0; j < listSalles.size(); j++) {
				eventDao.reservSalle(listSalles.get(j), idEvent);
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
