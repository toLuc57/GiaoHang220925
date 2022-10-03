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
	private static final String name = "placesname";
	private static final String locateX = "locate_x";
	private static final String locateY = "locate_y";
	
	public static List<String> title(){
		List<String> list = new ArrayList<String>();
		list.add("Id");
		list.add("Place name");
		list.add("Locate X");
		list.add("Locate Y");
		return list;
	}
	
	public static List<Places> query() 
			throws SQLException {
		Connection conn = null;
		List<Places> list = new ArrayList<Places>();
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + id + "`, `" + name 
					+ "`, `" + locateX + "`, `" + locateY 
					+ "` from `" + table + "`";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String findId = rs.getString(id);
				String findName = rs.getString(name);
				double findX = rs.getDouble(locateX);
				double findY = rs.getDouble(locateY);
				Places f = new Places(findId,findName, findX, findY);
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
			String sql = "select `" + id + "`, `" + name 
					+ "`, `" + locateX + "`, `" + locateY 
					+ "` from `" + table 
					+ "` where `" + id +"` =?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				String findName = rs.getString(name);
				double findX = rs.getDouble(locateX);
				double findY = rs.getDouble(locateY);
				Places f = new Places(findId,findName, findX, findY);
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
	public static Places findName(String findName) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + id + "`, `" + name 
					+ "`, `" + locateX + "`, `" + locateY 
					+ "` from `" + table 
					+ "` where `" + name +"` LIKE ?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%"+ findName + "%");
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				String findId = rs.getString(id);
				double findX = rs.getDouble(locateX);
				double findY = rs.getDouble(locateY);
				Places f = new Places(findId,findName, findX, findY);
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
	public static void update(Places update) throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "update `" + table
					+ "` set `" + name + "` =? ,`"
					+ locateX + "`=?, `" + locateY + "`=? "
					+ " where `" + id +"` =?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, update.getName());
			pstm.setDouble(2, update.getX());
			pstm.setDouble(3, update.getY());
			pstm.setString(4, update.getId());
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
					+ " (`" + name + "`, `"+ locateX 
					+ "`, `"+ locateY + "`) "
					+ " value (?,?,?)";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, insert.getName());
			pstm.setDouble(2, insert.getX());
			pstm.setDouble(3, insert.getY());
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
