package hm.GiaoHang.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hm.GiaoHang.entity.InvoiceDetails;
import hm.GiaoHang.entity.Receipt;
import hm.GiaoHang.jdbc.MySQLConnUtils;

public class InvoiceDetailsDBUtils {
	private static final String table = "invoice_details";
	private static final String receiptId = "receipt_id";
	private static final String goodsId = "goods_id";
	private static final String goodsName = "goods_name";
	private static final String amount = "amount";
	private static final String price = "price";
	
	public static List<String> title(){
		List<String> list = new ArrayList<String>();
		list.add("Id");
		list.add("Receipt Id");
		list.add("Goods Id");
		list.add("Goods Name");
		list.add("Amount");
		list.add("Price");
		return list;
	}
	
	public static List<InvoiceDetails> query() 
			throws SQLException {
		Connection conn = null;
		List<InvoiceDetails> list = new ArrayList<InvoiceDetails>();
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + receiptId + "`, `" + goodsId 
					+ "`, `" + goodsName	+ "`, `" + amount + "`, `" + price 
					+ "` from `" + table + "`";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String findReceiptId = rs.getString(receiptId);
				String findGoodsId = rs.getString(goodsId);
				String findgoodsName = rs.getString(goodsName);
				int findAmount = rs.getInt(amount);
				double findPrice = rs.getDouble(price);
				InvoiceDetails f = new InvoiceDetails(findReceiptId,findGoodsId,
						findgoodsName,findAmount,findPrice);
				list.add(f);
			} 
		}
		catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		return list;
	}
	
	public static List<InvoiceDetails> find(String findId) 
			throws SQLException {
		Connection conn = null;
		List<InvoiceDetails> list = new ArrayList<InvoiceDetails>();
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `"  + goodsId + "`, `" + goodsName	
					+ "`, `" + amount + "`, `" + price 
					+ "` from `" + table
					+ "` where `" + receiptId + "` = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findId);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String findGoodId = rs.getString(goodsId);
				String findGoodsName = rs.getString(goodsName);
				int findAmount = rs.getInt(amount);
				double findPrice = rs.getDouble(price);
				InvoiceDetails f = new InvoiceDetails(findId,findGoodId,
						findGoodsName,findAmount,findPrice);
				list.add(f);
			} 
		}
		catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		return list;
	}
	
	public static Map<String, List<InvoiceDetails>> query(List<Receipt> receiptList) 
			throws SQLException {
		Map<String, List<InvoiceDetails>> m = new HashMap<String, List<InvoiceDetails>>();
		for(var i : receiptList) {
			List<InvoiceDetails> list = InvoiceDetailsDBUtils.find(i.getId());
			m.put(i.getId(), list);
		}
		return m;
	}
	public static void update(InvoiceDetails update) throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "update `" + table
					+ "` set `" + goodsName + "` =?, `"
					+ amount + "` =?, `" + price + "` =? "
					+ " where `" + receiptId +"` =?"
					+ " and `" + goodsId + "` =?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, update.getGoodsName());
			pstm.setInt(2, update.getAmount());
			pstm.setDouble(3, update.getPrice());
			pstm.setString(4, update.getId());
			pstm.setString(5, update.getGoodsId());
			pstm.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}		
	}

	public static void insert(InvoiceDetails insert) throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table
					+ " (`" + receiptId + "` , `" + goodsId 
					+ "`, `"	+ goodsName + "`, `" + amount 
					+ "`, `"	+ price  +"`) "
					+ " value (?,?,?,?,?)";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, insert.getId());
			pstm.setString(2, insert.getGoodsId());
			pstm.setString(3, insert.getGoodsName());
			pstm.setDouble(4, insert.getAmount());
			pstm.setDouble(5, insert.getPrice());
			pstm.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}	
	}

	public static void delete(String deleteReceiptId, String deleteGoodId) 
			throws SQLException {
		Connection conn = null;
		try {
			String sql = "Delete from `"+ table 
					+ "` where `" + receiptId + "`= ?"
					+ " and `" + goodsId + "`=?";
			conn = MySQLConnUtils.getMySQLConUtils();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, deleteReceiptId);
			pstm.setString(2, deleteGoodId);
			pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}	
	}
}
