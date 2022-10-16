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
		List<String> listOrigin = null;
		List<String> listDestination = null;
		try {
			list = PlacesDBUtils.query();
			listOrigin = PlacesDBUtils.findOrigin("");
			listDestination = PlacesDBUtils.findDestination("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("title", title);
		request.setAttribute("list", list);
		request.setAttribute("listOrigin", listOrigin);
		request.setAttribute("listDestination", listDestination);
		
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/WEB-INF/view/TransportView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int mass = Integer.parseInt(request.getParameter("mass"));
		String origin = request.getParameter("origin");
		String destination = request.getParameter("destination");
		System.out.println(origin);
//		Load table 
		List<Fee> feeList = null;
		List<String> listOrigin = null;
		List<String> listDestination = null;
		try {
			Places p = PlacesDBUtils.calculate(origin, destination);
			listOrigin = PlacesDBUtils.findOrigin("");
			listDestination = PlacesDBUtils.findDestination("");
			if(p != null) {
				request.setAttribute("placeid", p.getIdplaces());
			}
			feeList = FeeDBUtils.findRecords(p.getDistance(), mass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<String> title = FeeDBUtils.title();
		
		request.setAttribute("title", title);
		request.setAttribute("feeList", feeList);
		
		request.setAttribute("mass", mass);
		request.setAttribute("origin", origin);
		request.setAttribute("destination", destination);
		
		request.setAttribute("listOrigin", listOrigin);
		request.setAttribute("listDestination", listDestination);
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/WEB-INF/view/TransportView.jsp");
	    
		dispatcher.forward(request, response);
	}

}
