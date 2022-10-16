package hm.GiaoHang.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hm.GiaoHang.entity.Fee;
import hm.GiaoHang.entity.Places;
import hm.GiaoHang.entity.Receipt;
import hm.GiaoHang.utils.FeeDBUtils;
import hm.GiaoHang.utils.MyUtils;
import hm.GiaoHang.utils.PlacesDBUtils;
import hm.GiaoHang.utils.ReceiptDBUtils;


@WebServlet("/insertReceipt")
public class InsertReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertReceiptServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCustomer = MyUtils.getLoginedUser(request.getSession()).getIdCustomer();
		// Default idShip is '1'
		String idShip = "1";
		// Default date is today
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String date = dtf.format(now);
		String feeid = request.getParameter("feeid");
		String placeid = request.getParameter("placeid");
		// Recept not ready
		int status = 0;
		
		Receipt newRecords = null;
		try {
			Places p = PlacesDBUtils.find(placeid);
			Fee f = FeeDBUtils.find(feeid);
			int duration = p.getDuration();
			if(f.getFeename().equals("Express")) {
				duration += 5;
			}
			else {
				// Normal
				duration += 30;
			}
			newRecords = new Receipt(idCustomer, idShip, feeid,
					date, p.getOrigin(), p.getDestination(), duration,
					status, f.getFeeprice());
			ReceiptDBUtils.insert(newRecords);
			String newIdRecords = ReceiptDBUtils.findIdRecord(newRecords);
			newRecords.setId(newIdRecords);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/quene");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			Receipt newRecords = ReceiptDBUtils.find(id);
//			// Recept ready
//			newRecords.setStatus(1);
//			ReceiptDBUtils.update(newRecords);
//			response.sendRedirect(request.getContextPath() + "/receipt");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		RequestDispatcher dispatcher = this.getServletContext().
//				getRequestDispatcher("/WEB-INF/view/TransportView.jsp");
//	    
//		dispatcher.forward(request, response);
	}

}
