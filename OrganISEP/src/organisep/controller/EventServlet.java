package organisep.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import organisep.bean.CommentBean;
import organisep.bean.EventBean;
import organisep.model.CommentDao;
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
		
		else if (action.equals("updateStatut")) {			
			int id = Integer.parseInt(request.getParameter("id"));
			int val = Integer.parseInt(request.getParameter("Val"));
			String comment = request.getParameter("Comment");
			
			EventDao eventDao = new EventDao();
			eventDao.updateValidationEvent(id, val);
			
			if (!comment.equals("")) {
				int idCreat = Integer.parseInt(request.getParameter("IdCreat"));
				
				Date date = new Date();
				Boolean read = false;
						
				CommentDao commentDao = new CommentDao();
				String creat = eventDao.getCreateur(idCreat, "nom");
				CommentBean commentBean = new CommentBean(comment, date, creat, read, id);
				try {
					commentDao.createComment(commentBean, idCreat);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		String action = request.getParameter("action");
		
		if(action.equals("creat")) {
			String nom = request.getParameter("Nom");
			String d = request.getParameter("Date");
			String t = request.getParameter("Time");
			String lien = request.getParameter("Lien");		
			String desc = request.getParameter("Desc");
			String image = request.getParameter("Image");
			
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
			if (!image.equals("")) {
				imageEvent = "img/imgEvent/" + image ;
			}
			
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
				for (int k = 0; k < listRessources.size(); k++) {
					eventDao.reservRess(listRessources.get(k), idEvent);
				}
				eventDao.sendHTMLEmail(eventBean, "creat");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(action.equals("update")) {
			int idEvent = Integer.parseInt(request.getParameter("IdEvent"));
			String nom = request.getParameter("Nom");
			String t = request.getParameter("Time");	
			String desc = request.getParameter("Desc");
			int nbr_participant = Integer.parseInt(request.getParameter("Nbr"));
			String image = request.getParameter("Image");
			
			int budget = 0;
			if (!request.getParameter("Budget").equals("")) {
				budget = Integer.parseInt(request.getParameter("Budget"));
			}
			
			String[] salles = request.getParameterValues("Salles[]");
			ArrayList<Integer> listSallesId = new ArrayList<Integer>();
			ArrayList<String> listSalles = new ArrayList<String>();
			if (salles != null) {
				for (int i = 0; i < salles.length; i++) {
					String[] splitSalles = salles[i].split("_");
					listSallesId.add(Integer.parseInt(splitSalles[1]));
					listSalles.add(splitSalles[0]);
				}
			}		
			
			String[] ressources = request.getParameterValues("Ress[]");
			ArrayList<String> listRessources = new ArrayList<String>();
			if (ressources != null) {
				listRessources = new ArrayList<String>(Arrays.asList(ressources));
			}
			
			String date = "";
			if (request.getParameter("Date").equals("")) {
				date = request.getParameter("ActualDate");
			} else {
				date = request.getParameter("Date");
				SimpleDateFormat old_sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
				Date da;
				try {
					da = old_sdf.parse(date);
					date = sdf.format(da);
				}
				catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
			String imageEvent = "";
			if (!image.equals("")) {
				imageEvent = "img/imgEvent/" + image ;
			}
			
			
			try {
				
				EventDao eventDao = new EventDao();
				EventBean eventBean = eventDao.getEventInEventsById(idEvent);
				eventBean.updateEvent(nom, t, desc, nbr_participant, budget, listSalles, listRessources, date, imageEvent);
				eventDao.updateEvent(eventBean);
				
				eventDao.deleteSalle(idEvent);
				for (int j = 0; j < listSallesId.size(); j++) {
					eventDao.reservSalle(listSallesId.get(j), idEvent);
				}
				
				eventDao.deleteRess(idEvent);
				for (int k = 0; k < listRessources.size(); k++) {
					eventDao.reservRess(listRessources.get(k), idEvent);
				}
				
				
				String event;
				event = this.gson.toJson(eventBean);
				response.setContentType("application/json");
				response.getWriter().write(event);
				eventDao.sendHTMLEmail(eventBean, "update");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
