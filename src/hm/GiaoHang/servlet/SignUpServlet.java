package hm.GiaoHang.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hm.GiaoHang.entity.Customer;
import hm.GiaoHang.entity.UserAccount;
import hm.GiaoHang.utils.CustomerDBUtils;
import hm.GiaoHang.utils.MyUtils;
import hm.GiaoHang.utils.UserAccountDBUtils;


@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignUpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> list = null;
		try {
			list = UserAccountDBUtils.query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/WEB-INF/view/SignUpView.jsp");
	    
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		String address = request.getParameter("address");
		String psw = request.getParameter("psw");
		String pswRepeat = request.getParameter("pswRepeat");
		
		try {
			if(!psw.equals(pswRepeat) || UserAccountDBUtils.IsExists(username)) {
				request.setAttribute("errorMessage", "Signup error");
				request.setAttribute("username", username);
				request.setAttribute("name", name);
				request.setAttribute("telephone", telephone);
				request.setAttribute("address", address);
				
				RequestDispatcher dispatcher = this.getServletContext().
						getRequestDispatcher("/WEB-INF/view/SignUpView.jsp");
			    
				dispatcher.forward(request, response);
				return;
			}
			Customer insert = new Customer(name, telephone, address, 1);
			CustomerDBUtils.insert(insert);
			String idNewRecord = CustomerDBUtils.findIdCustomer(insert);
			insert.setIdcustomer(idNewRecord);
			UserAccount u = new UserAccount(username, psw, idNewRecord, null, "1");
			UserAccountDBUtils.insert(u);
			MyUtils.storeLoginedUser(request.getSession(), u);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/home");
	}
}
