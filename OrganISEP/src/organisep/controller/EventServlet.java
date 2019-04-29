package organisep.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import organisep.bean.EventsBean;
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
		EventsBean eventsBean = new EventsBean();
		EventDao eventDao = new EventDao();
		
		eventsBean = eventDao.getEvents(eventsBean);
		String events = this.gson.toJson(eventsBean);
		
		response.setContentType("application/json");
		response.getWriter().write(events);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
