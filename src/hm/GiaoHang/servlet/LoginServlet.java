package hm.GiaoHang.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hm.GiaoHang.entity.UserAccount;
import hm.GiaoHang.utils.MyUtils;
import hm.GiaoHang.utils.UserAccountDBUtils;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/WEB-INF/view/LoginView.jsp");
	    
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("yourpassword");
		String rememberMeStr = request.getParameter("rememberMe");
		boolean remember = "Y".equals(rememberMeStr);

		boolean hasError = false;
		String errorString = null;

		if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Required username and password!";
		} 
		else {
			try {
				String notice = UserAccountDBUtils.Find(userName, password);
				if(!UserAccountDBUtils.IsSuccess(notice)) {
					hasError = true;
					errorString = notice;
				}
			} catch (SQLException e) {
				hasError = true;
				errorString = e.getMessage();
			}
		}

		// if it has error, forward /WEB-INF/view/LoginView.jsp
		if (hasError) {
			UserAccount user = new UserAccount();
			user.setUsername(userName);
			user.setPassword(password);
			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);

			// Forward /WEB-INF/view/LoginView.jsp
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/WEB-INF/view/LoginView.jsp");
			dispatcher.forward(request, response);
		}
		// If it has not error
		// Save info into Session.
		// And forward home page.
		else {
			HttpSession session = request.getSession();
			
			MyUtils.storeLoginedUser(session, MyUtils.getTemporarySave());
			MyUtils.setTemporarySave(null);
			// If user choose "Remember me".
			if (remember) {
				MyUtils.storeUserCookie(response, MyUtils.getLoginedUser(session));
			}
			// Otherwise delete Cookie
			else {
				MyUtils.deleteUserCookie(response);
			}
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}
}
