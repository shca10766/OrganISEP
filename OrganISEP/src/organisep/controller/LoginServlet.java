package organisep.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import organisep.bean.LoginBean;
import organisep.model.LoginDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("login_email");
		String mdp = request.getParameter("login_mdp");

		LoginBean loginBean = new LoginBean(email, mdp);

		LoginDao loginDao = new LoginDao();

		String userValidate = loginDao.authenticateUser(loginBean);
		
		if (userValidate.equals("SUCCESS")) {
			request.setAttribute("email", email); 
			//request.getRequestDispatcher("/Home.jsp").forward(request, response);
		} 
		else {
			request.setAttribute("errMessage", userValidate);
			this.getServletContext().getRequestDispatcher("/connexion.jsp").forward( request, response );
		}
	}

}