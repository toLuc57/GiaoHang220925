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
import hm.GiaoHang.entity.Places;
import hm.GiaoHang.utils.FeeDBUtils;
import hm.GiaoHang.utils.PlacesDBUtils;

@WebServlet("/transport")
public class TransportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TransportServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> title = FeeDBUtils.title();
		List<Places> list = null;
		try {
			list = PlacesDBUtils.query();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("title", title);
		request.setAttribute("list", list);
		
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/WEB-INF/view/TransportView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String massSTR = request.getParameter("mass");
		
		Places a = null;
		Places b= null;
		
		double distance = 0;
		try {
			a = PlacesDBUtils.findName(from);
			b = PlacesDBUtils.findName(to);
		} catch (SQLException e1) {
			e1.printStackTrace();
			distance = 0;
		}
		double mass = 0;
		try {
			mass = Double.parseDouble(massSTR);
			distance = Places.calculateDistance(a, b);
		} catch (NullPointerException |NumberFormatException e) {
			e.printStackTrace();
			mass = 0;
		}
//		Load table 
		List<Fee> feeList = null;
		try {
			feeList = FeeDBUtils.findRecords(distance, mass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<String> title = FeeDBUtils.title();
		request.setAttribute("mass", mass);
		request.setAttribute("title", title);
		request.setAttribute("feeList", feeList);
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/WEB-INF/view/TransportView.jsp");
	    
		dispatcher.forward(request, response);
	}

}
