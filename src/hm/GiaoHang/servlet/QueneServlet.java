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

import hm.GiaoHang.entity.Receipt;
import hm.GiaoHang.utils.MyUtils;
import hm.GiaoHang.utils.ReceiptDBUtils;


@WebServlet("/quene")
public class QueneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueneServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCustomer = MyUtils.getLoginedUser(request.getSession()).getIdCustomer();
		List<Receipt> list = null;
		List<String> title = null;
		try {
			title = ReceiptDBUtils.title();
			list = ReceiptDBUtils.query(idCustomer, 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("title", title);
		request.setAttribute("list", list);
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/WEB-INF/view/QueueView.jsp");
	    
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
