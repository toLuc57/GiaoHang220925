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
	private static final String idFee = "idFee";
	private static final String date = "date";
	private static final String origin = "origin";
	private static final String destination = "destination";
	private static final String duration = "duration";
	private static final String status = "status";
	private static final String price = "price";
	
	public static List<String> title(){
		List<String> list = new ArrayList<String>();
		list.add("Id");
		list.add("Customer Id");
		list.add("Ship Id");
		list.add("Fee Id");
		list.add("Date");
		list.add("Origin");
		list.add("Destination");
		list.add("Duration");
		list.add("Status");
		list.add("Price");
		return list;
	}
	
	public static List<Receipt> query() 
			throws SQLException {
		Connection conn = null;
		List<Receipt> list = new ArrayList<Receipt>();
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + id + "`, `" + idCustomer 
					+ "`, `" + idShip + "`, `" + idFee + "`, `" + date 
					+ "`, `" + origin + "`, `" + destination  + "`, `" + duration
					+ "`, `" + status + "`, `" + price
					+ "` from `" + table +"`";
			System.out.println(sql);
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String findId = rs.getString(id);
				String findIdCustomer = rs.getString(idCustomer);
				String findIdShip = rs.getString(idShip);
				String findIdFee = rs.getString(idFee);
				String findDate = rs.getString(date);
				String findOrigin = rs.getString(origin);
				String findDestination = rs.getString(destination);
				int findDuration = rs.getInt(duration);
				int findStatus = rs.getInt(status);
				int findPrice = rs.getInt(price);
				Receipt f = new Receipt(findId,findIdCustomer,findIdShip,findIdFee,
						findDate,findOrigin,findDestination,findDuration,findStatus,findPrice);
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
					+ "`, `" + idFee + "`, `" + date + "`, `" + origin 
					+ "`, `" + destination  + "`, `" + duration
					+ "`, `" + status + "`, `" + price
					+ "` from `" + table 
					+ "` where `" + idCustomer + "` =?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findIdCustomer);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String findId = rs.getString(id);
				String findIdShip = rs.getString(idShip);
				String findIdFee = rs.getString(idFee);
				String findDate = rs.getString(date);
				String findOrigin = rs.getString(origin);
				String findDestination = rs.getString(destination);
				int findDuration = rs.getInt(duration);
				int findStatus = rs.getInt(status);
				int findPrice = rs.getInt(price);
				Receipt f = new Receipt(findId,findIdCustomer,findIdShip,findIdFee,
						findDate,findOrigin,findDestination,findDuration,findStatus,findPrice);
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
	public static List<Receipt> query(String findIdCustomer, int findStatus) 
			throws SQLException {
		if(findIdCustomer == null || findIdCustomer.length() == 0) {
			return query();
		}
		Connection conn = null;
		List<Receipt> list = new ArrayList<Receipt>();
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + id + "`, `" + idShip 
					+ "`, `" + idFee + "`, `" + date + "`, `" + origin 
					+ "`, `" + destination  + "`, `" + duration
					+ "`, `" + status + "`, `" + price
					+ "` from `" + table 
					+ "` where `" + idCustomer + "` =?"
					+ " and `" + status + "` =?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findIdCustomer);
			pstm.setInt(2, findStatus);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String findId = rs.getString(id);
				String findIdShip = rs.getString(idShip);
				String findIdFee = rs.getString(idFee);
				String findDate = rs.getString(date);
				String findOrigin = rs.getString(origin);
				String findDestination = rs.getString(destination);
				int findDuration = rs.getInt(duration);
				int findPrice = rs.getInt(price);
				Receipt f = new Receipt(findId,findIdCustomer,findIdShip,findIdFee,
						findDate,findOrigin,findDestination,findDuration,findStatus,findPrice);
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
					+ "`, `" + idFee + "`, `" + date + "`, `" + origin 
					+ "`, `" + destination  + "`, `" + duration
					+ "`, `" + status + "`, `" + price
					+ "` from `" + table
					+ "` where `" + id + "` = ?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				String findIdCustomer = rs.getString(idCustomer);
				String findIdShip = rs.getString(idShip);
				String findIdFee = rs.getString(idFee);
				String findDate = rs.getString(date);
				String findOrigin = rs.getString(origin);
				String findDestination = rs.getString(destination);
				int findDuration = rs.getInt(duration);
				int findStatus = rs.getInt(status);
				int findPrice = rs.getInt(price);
				Receipt f = new Receipt(findId,findIdCustomer,findIdShip,findIdFee,
						findDate,findOrigin,findDestination,findDuration,findStatus,findPrice);
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
	public static String findIdRecord(Receipt findRecords) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + id 
					+ "` from `" + table
					+ "` where `" + idCustomer + "` = ? and `" 
					+ idShip + "` = ? and `" + idFee + "` = ? and `"
					+ date + "` = ? and`" + origin + "` = ? and `"
					+ destination + "` = ? and `" + duration + "` = ? and `"
					+ status + "` = ? and `" + price + "` = ?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findRecords.getIdCustomer());
			pstm.setString(2, findRecords.getIdShip());
			pstm.setString(3, findRecords.getIdFee());
			pstm.setString(4, findRecords.getDate());
			pstm.setString(5, findRecords.getOrigin());
			pstm.setString(6, findRecords.getDestination());
			pstm.setInt(7, findRecords.getDuration());
			pstm.setInt(8, findRecords.getStatus());
			pstm.setInt(9, findRecords.getPrice());
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				return rs.getString(id);
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
					+ idShip + "` =?, `" + idFee + "` =?, `"
					+ date + "` =?, `" + origin + "`=?, `" 
					+ destination + "`=?, `" + duration + "`=?, `"
					+ status + "`=?, `" + price +"`=? "
					+ " where `" + id +"` =?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, update.getIdCustomer());
			pstm.setString(2, update.getIdShip());
			pstm.setString(3, update.getIdFee());
			pstm.setString(4, update.getDate());
			pstm.setString(5, update.getOrigin());
			pstm.setString(6, update.getDestination());
			pstm.setInt(7, update.getDuration());
			pstm.setInt(8, update.getStatus());
			pstm.setInt(9, update.getPrice());
			pstm.setString(10, update.getId());
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
					+ " (`" + idCustomer + "`, `" + idShip + "`, `" + idFee
					+ "`, `" + date + "`, `" + origin + "`, `" + destination 
					+ "`, `" + duration + "`, `" + status +  "`, `" + price  +"`) "
					+ " value (?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, insert.getIdCustomer());
			pstm.setString(2, insert.getIdShip());
			pstm.setString(3, insert.getIdFee());
			pstm.setString(4, insert.getDate());
			pstm.setString(5, insert.getOrigin());
			pstm.setString(6, insert.getDestination());
			pstm.setInt(7, insert.getDuration());
			pstm.setInt(8, insert.getStatus());
			pstm.setInt(9, insert.getPrice());
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
