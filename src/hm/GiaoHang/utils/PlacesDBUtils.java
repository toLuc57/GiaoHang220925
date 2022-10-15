package hm.GiaoHang.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hm.GiaoHang.entity.Places;
import hm.GiaoHang.jdbc.MySQLConnUtils;

public class PlacesDBUtils {
	private static final String table = "places";
	private static final String id = "idplaces";
	private static final String origin = "origin";
	private static final String destination = "destination";
	private static final String distance = "distance";
	private static final String duration = "duration";
	
	public static List<String> title(){
		List<String> list = new ArrayList<String>();
		list.add("Id");
		list.add("Origin");
		list.add("Destination");
		list.add("Distance");
		list.add("Duration");
		return list;
	}
	
	public static List<Places> query() 
			throws SQLException {
		Connection conn = null;
		List<Places> list = new ArrayList<Places>();
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + id + "`, `" + origin 
					+ "`, `" + destination + "`, `" + distance + "`, `" + duration 
					+ "` from `" + table + "`";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String findId = rs.getString(id);
				String findOrigin = rs.getString(origin);
				String findDestination = rs.getString(destination);
				double findDistance = rs.getDouble(distance);
				int findDuration = rs.getInt(duration);
				Places f = new Places(findId,findOrigin, findDestination, findDistance, findDuration);
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
	
	public static Places find(String findId) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + origin + "`, `" + destination 
					+ "`, `" + distance + "`, `" + duration 
					+ "` from `" + table 
					+ "` where `" + id +"` =?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				String findOrigin = rs.getString(origin);
				String findDestination = rs.getString(destination);
				double findDistance = rs.getDouble(distance);
				int findDuration = rs.getInt(duration);
				Places f = new Places(findId,findOrigin, findDestination, findDistance, findDuration);
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
	public static List<Places> findOrigin(String findName) 
			throws SQLException {
		Connection conn = null;
		List<Places> list = new ArrayList<Places>();
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql ="select `" + id + "`, `" + origin 
					+ "`, `" + destination + "`, `" + distance + "`, `" + duration 
					+ "` from `" + table 
					+ "` where `" + origin +"` LIKE ?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%"+ findName + "%");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String findId = rs.getString(id);
				String findOrigin = rs.getString(origin);
				String findDestination = rs.getString(destination);
				double findDistance = rs.getDouble(distance);
				int findDuration = rs.getInt(duration);
				Places f = new Places(findId,findOrigin, findDestination, findDistance, findDuration);
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
	public static List<Places> findDestination(String findName) 
			throws SQLException {
		Connection conn = null;
		List<Places> list = new ArrayList<Places>();
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql ="select `" + id + "`, `" + origin 
					+ "`, `" + destination + "`, `" + distance + "`, `" + duration 
					+ "` from `" + table 
					+ "` where `" + destination +"` LIKE ?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%"+ findName + "%");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String findId = rs.getString(id);
				String findOrigin = rs.getString(origin);
				String findDestination = rs.getString(destination);
				double findDistance = rs.getDouble(distance);
				int findDuration = rs.getInt(duration);
				Places f = new Places(findId,findOrigin, findDestination, findDistance, findDuration);
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
	public static void update(Places update) throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "update `" + table
					+ "` set `" + origin + "` =? ,`" + destination + "`=?, `" 
					+ distance + "`=?, `" + duration + "`=? "
					+ " where `" + id +"` =?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, update.getOrigin());
			pstm.setString(2, update.getDestination());
			pstm.setDouble(3, update.getDistance());
			pstm.setInt(4, update.getDuration());
			pstm.setString(5, update.getIdplaces());
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

	public static void insert(Places insert) throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table
					+ " (`" + origin + "`, `" + destination 
					 + "`, `" + distance + "`, `"+ duration + "`) "
					+ " value (?,?,?,?)";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, insert.getOrigin());
			pstm.setString(2, insert.getDestination());
			pstm.setDouble(3, insert.getDistance());
			pstm.setInt(4, insert.getDuration());
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
			String sql = "Delete from `"+ table + "` where `"+ id + "`= ?";
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
