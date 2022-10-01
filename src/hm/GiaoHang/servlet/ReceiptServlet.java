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
import hm.GiaoHang.utils.ReceiptDBUtils;

@WebServlet("/receipt")
public class ReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReceiptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> title = ReceiptDBUtils.title() ;
		List<Receipt> list = null;
		try {
			list = ReceiptDBUtils.query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("title", title);
		request.setAttribute("receiptList", list);
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/WEB-INF/view/ReceiptView.jsp");
	    
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
