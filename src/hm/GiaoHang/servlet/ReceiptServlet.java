package hm.GiaoHang.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hm.GiaoHang.entity.InvoiceDetails;
import hm.GiaoHang.entity.Receipt;
import hm.GiaoHang.utils.InvoiceDetailsDBUtils;
import hm.GiaoHang.utils.MyUtils;
import hm.GiaoHang.utils.ReceiptDBUtils;

@WebServlet("/receipt")
public class ReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReceiptServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> title = ReceiptDBUtils.title() ;
		List<Receipt> list = null;
		Map<String, List<InvoiceDetails>> map = new HashMap<>();
		String id = MyUtils.getLoginedUser(request.getSession()).getIdCustomer();
		
		try {
			list = ReceiptDBUtils.query(id);
			for(var i : list) {
				String receiptId = i.getId();
				map.put(receiptId, InvoiceDetailsDBUtils.find(receiptId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("title", title);
		request.setAttribute("receiptList", list);
		request.setAttribute("map", map);
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/WEB-INF/view/ReceiptView.jsp");
	    
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
