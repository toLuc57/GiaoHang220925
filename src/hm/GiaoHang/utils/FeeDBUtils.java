package hm.GiaoHang.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hm.GiaoHang.entity.Fee;
import hm.GiaoHang.jdbc.MySQLConnUtils;

public class FeeDBUtils {
	private static final String table = "fee";
	private static final String id = "id";
	private static final String name = "name";
	private static final String distance = "distance";
	private static final String mass = "mass";
	private static final String price = "price";
	
	public static List<String> title(){
		List<String> list = new ArrayList<String>();
		list.add("Id");
		list.add("Fee name");
		list.add("Distance");
		list.add("Mass");
		list.add("Fee price");
		return list;
	}
	
	public static List<Fee> query() 
			throws SQLException {
		Connection conn = null;
		List<Fee> list = new ArrayList<Fee>();
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + id + "`, `" + name + "`, `" + distance
					+ "`, `" + mass + "`, `" + price
					+ "` from `" + table + "`" ;
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String findId = rs.getString(id);
				String findName = rs.getString(name);
				double findDistance = rs.getDouble(distance);
				int findMass = rs.getInt(mass);
				double findPrice = rs.getDouble(price);
				Fee f = new Fee(findId,findName,findDistance,
						findMass,findPrice);
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
	
	public static Fee find(String findId) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + id + "`, `" + name + "`, `" + distance
					+ "`, `" + mass + "`, `" + price
					+ "` from `" + table 
					+ "` where `" + id +"` =?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				String findName = rs.getString(name);
				double findDistance = rs.getDouble(distance);
				int findMass = rs.getInt(mass);
				double findPrice = rs.getDouble(price);
				Fee f = new Fee(findId,findName,findDistance,
						findMass,findPrice);
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
	public static void update(Fee update) throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "update `" + table
					+ " set `" + name + "` =?, `"
					+ distance + "` =?, `"
					+ mass + "`=?, `" + price +"`=? "
					+ " where `" + id +"` =?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, update.getFeename());
			pstm.setDouble(2, update.getDistance());
			pstm.setInt(3, update.getMass());
			pstm.setDouble(4, update.getFeeprice());
			pstm.setString(5, update.getFeeid());
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

	public static void insert(Fee insert) throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table
					+ " (`" + name + "` , `" + distance
					+ "`, `"	+ mass + "`, `" + price +"`) "
					+ " value (?,?,?,?)";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, insert.getFeename());
			pstm.setDouble(2, insert.getDistance());
			pstm.setInt(3, insert.getMass());
			pstm.setDouble(4, insert.getFeeprice());
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
	
	public static List<Fee> findRecords(double distance1, double mass1)
			throws SQLException{
		Connection conn = null;
		List<Fee> list = new ArrayList<Fee>();
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `"+ id +"`, `"	+ name 
					+ "`, min(`" + distance + "`)" 	+ " as '" + distance 
					+"', min(`"+ mass + "`)" + " as '" + mass 
					+ "', `" + price + "` FROM `" + table 
					+ "` where `" + distance + "` >= ?"
					+ " and `"+ mass + "` >= ?" 
					+ "GROUP BY `" + id+ "`,`" + name + "`,`" + price+ "`";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setDouble(1, distance1);
			pstm.setDouble(2, mass1);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String findId = rs.getString(id);
				String findName = rs.getString(name);
				double findDistance = rs.getDouble(distance);
				int findMass = rs.getInt(mass);
				double findPrice = rs.getDouble(price);
				Fee f = new Fee(findId,findName,findDistance,
						findMass,findPrice);
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
}
