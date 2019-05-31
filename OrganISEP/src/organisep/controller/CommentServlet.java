package organisep.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import organisep.bean.CommentBean;
import organisep.bean.EventBean;
import organisep.model.CommentDao;
import organisep.model.EventDao;

import com.google.gson.Gson;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private Gson gson = new Gson();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String event = request.getParameter("Event");
		int idCreat = Integer.parseInt(request.getParameter("IdCreat"));
		
		try {
			CommentDao commentDao = new CommentDao();
			commentDao.readComment(event, idCreat);
		}
		catch (ParseException e1) {
			e1.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if (action.equals("send")) {
			String comment = request.getParameter("Comment");
			String event = request.getParameter("Event");
			int idCreat = Integer.parseInt(request.getParameter("IdCreat"));
			
			Date date = new Date();
			Boolean read = false;
			
					
			try {
				CommentDao commentDao = new CommentDao();
				EventDao eventDao = new EventDao();
				String creat = eventDao.getCreateur(idCreat, "nom");
				int eventId = commentDao.getEvent(event);
				CommentBean commentBean = new CommentBean(comment, date, creat, read, eventId);
				commentDao.createComment(commentBean, idCreat);
				
				EventBean wholeEvent = eventDao.getEvent(event);
				event = this.gson.toJson(wholeEvent);
				response.getWriter().write(event);
				
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		else {
			String event = request.getParameter("Event");
			int idCreat = Integer.parseInt(request.getParameter("IdCreat"));
				
			try {
				CommentDao commentDao = new CommentDao();
				EventDao eventDao = new EventDao();
				commentDao.readComment(event, idCreat);
				
				EventBean wholeEvent = eventDao.getEvent(event);
				event = this.gson.toJson(wholeEvent);
				response.getWriter().write(event);
				
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}

}