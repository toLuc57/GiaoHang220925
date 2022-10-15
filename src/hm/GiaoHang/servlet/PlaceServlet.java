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

import hm.GiaoHang.entity.Places;
import hm.GiaoHang.utils.PlacesDBUtils;

@WebServlet("/place")
public class PlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PlaceServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("value");
		String place = request.getParameter("place");
		System.out.println("PlaceServlet: " + value);
		List<Places> list = null;
		try {
			System.out.println("---------------------------------");
			if(value.equals("origin")) {
				list = PlacesDBUtils.findOrigin(place);
				System.out.println("PlaceServlet: " + list.size());
				request.setAttribute("originList", list);
				for(var i : list) {
					System.out.println(i.getOrigin());
				}
			}
			else if(value.equals("destination")){
				list = PlacesDBUtils.findDestination(place);
				for(var i : list) {
					System.out.println(i.getDestination());
				}
				request.setAttribute("destinationList", list);
			}
			else {
				System.out.println("dshkfkaslhfd");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/WEB-INF/view/TransportView.jsp");
	    
		dispatcher.forward(request, response);
	}

}
