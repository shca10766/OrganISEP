package organisep.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			loginDao.sessionUser(loginBean);
			HttpSession session = request.getSession();
			session.setAttribute("user", loginBean);
			request.setAttribute("action", "Tableau de bord");
			request.getRequestDispatcher("/template.jsp").forward(request, response);
		} 
		else {
			request.setAttribute("errMessage", userValidate);
			this.getServletContext().getRequestDispatcher("/index.jsp").forward( request, response );
		}
	}

}