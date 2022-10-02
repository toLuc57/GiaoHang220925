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

import hm.GiaoHang.entity.Fee;
import hm.GiaoHang.utils.FeeDBUtils;

@WebServlet("/transport")
public class TransportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TransportServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/WEB-INF/view/ReceiptView.jsp");
	    
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String massSTR = request.getParameter("mass");
		
		double distance = 0;
		double mass = 0;
//		Load table 
		List<Fee> feeList = null;
		try {
			feeList = FeeDBUtils.findRecords(distance, mass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("feeList", feeList);
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/WEB-INF/view/ReceiptView.jsp");
	    
		dispatcher.forward(request, response);
	}

}
