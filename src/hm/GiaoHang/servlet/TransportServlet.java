package hm.GiaoHang.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String from = request.getAttribute("from");
		String to = request.getAttribute("to");
		String mass = request.getAttribute("mass");
	}

}
