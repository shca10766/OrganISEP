package organisep.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import organisep.model.ReservationDao;



@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
	private Gson gson = new Gson();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String date = request.getParameter("date");
		String type = request.getParameter("type");
		ReservationDao rd = new ReservationDao();
		
		if (type.equals("salle")) {
			ArrayList<String> l_salles = rd.getSalles(date);
			String salles = this.gson.toJson(l_salles);
			
			response.setContentType("application/json");
			response.getWriter().write(salles);
		}
		else {
			response.setContentType("text/plain");
			response.getWriter().write("1");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
