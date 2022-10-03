package hm.GiaoHang.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hm.GiaoHang.entity.Receipt;
import hm.GiaoHang.jdbc.MySQLConnUtils;

public class ReceiptDBUtils {
	private static final String table = "receipt";
	private static final String id = "id";
	private static final String idCustomer = "idCustomer";
	private static final String idShip = "idShip";
	private static final String date = "date";
	private static final String origin = "origin";
	private static final String destination = "destination";
	private static final String status = "status";
	
	public static List<String> title(){
		List<String> list = new ArrayList<String>();
		list.add("Id");
		list.add("Customer Id");
		list.add("Ship Id");
		list.add("Date");
		list.add("Origin");
		list.add("Destination");
		list.add("Status");
		return list;
	}
	
	public static List<Receipt> query() 
			throws SQLException {
		Connection conn = null;
		List<Receipt> list = new ArrayList<Receipt>();
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + id + "`, `" + idCustomer 
					+ "`, `" + idShip + "`, `" + date + "`, `" + origin 
					+ "`, `" + destination + "`, `" + status
					+ "` from `" + table +"`";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String findId = rs.getString(id);
				String findIdCustomer = rs.getString(idCustomer);
				String findIdShip = rs.getString(idShip);
				String findDate = rs.getString(date);
				String findOrigin = rs.getString(origin);
				String findDestination = rs.getString(destination);
				int findStatus = rs.getInt(status);
				Receipt f = new Receipt(findId,findIdCustomer,findIdShip,
						findDate,findOrigin,findDestination,findStatus);
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
	
	public static List<Receipt> query(String findIdCustomer) 
			throws SQLException {
		if(findIdCustomer == null || findIdCustomer.length() == 0) {
			return query();
		}
		Connection conn = null;
		List<Receipt> list = new ArrayList<Receipt>();
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + id + "`, `" + idShip 
					+ "`, `" + date + "`, `" + origin 
					+ "`, `" + destination + "`, `" + status
					+ "` from `" + table 
					+ "` where `" + idCustomer + "` =?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findIdCustomer);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String findId = rs.getString(id);
				String findIdShip = rs.getString(idShip);
				String findDate = rs.getString(date);
				String findOrigin = rs.getString(origin);
				String findDestination = rs.getString(destination);
				int findStatus = rs.getInt(status);
				Receipt f = new Receipt(findId,findIdCustomer,findIdShip,
						findDate,findOrigin,findDestination,findStatus);
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
	
	public static Receipt find(String findId) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + idCustomer + "`, `" + idShip
					+ "`, `" + date + "`, `" + origin 
					+ "`, `" + destination + "`, `" + status
					+ "` from `" + table
					+ "` where `" + id + "` = ?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				String findIdCustomer = rs.getString(idCustomer);
				String findIdShip = rs.getString(idShip);
				String findDate = rs.getString(date);
				String findOrigin = rs.getString(origin);
				String findDestination = rs.getString(destination);
				int findStatus = rs.getInt(status);
				Receipt f = new Receipt(findId,findIdCustomer,findIdShip,
						findDate,findOrigin,findDestination,findStatus);
				return f;
			} 
		}
		catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		return null;
	}
	public static void update(Receipt update) throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "update `" + table
					+ "` set `" + idCustomer + "` =?, `"
					+ idShip + "` =?, `" + date + "` =?, `"
					+ origin + "`=?, `" + destination +"`=?, `"
					+ status +"`=? "
					+ " where `" + id +"` =?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, update.getIdCustomer());
			pstm.setString(2, update.getIdShip());
			pstm.setString(3, update.getDate());
			pstm.setString(4, update.getOrigin());
			pstm.setString(5, update.getDestination());
			pstm.setInt(6, update.getStatus());
			pstm.setString(7, update.getId());
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

	public static void insert(Receipt insert) throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table
					+ " (`" + idCustomer + "`, `" + idShip 
					+ "`, `" + date + "`, `" + origin 
					+ "`,` "+ destination +  "`,` " + status  +"`) "
					+ " value (?,?,?,?,?)";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, insert.getIdCustomer());
			pstm.setString(2, insert.getIdShip());
			pstm.setString(3, insert.getDate());
			pstm.setString(4, insert.getOrigin());
			pstm.setString(5, insert.getDestination());
			pstm.setInt(6, insert.getStatus());
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

	public static void delete(String deleteId) throws SQLException {
		Connection conn = null;
		try {
			String sql = "Delete from `" + table + "` where `"+ id + "`= ?";
			conn = MySQLConnUtils.getMySQLConUtils();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, deleteId);
			pstm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}	
	}
}
