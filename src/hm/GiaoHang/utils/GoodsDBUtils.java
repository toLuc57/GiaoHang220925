package hm.GiaoHang.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hm.GiaoHang.entity.Goods;
import hm.GiaoHang.jdbc.MySQLConnUtils;

public class GoodsDBUtils {
	private static final String table = "goods";
	private static final String id = "id";
	private static final String name = "name";
	
	public static List<String> title(){
		List<String> list = new ArrayList<String>();
		list.add("Id");
		list.add("Good name");
		return list;
	}
	
	public static List<Goods> query() 
			throws SQLException {
		Connection conn = null;
		List<Goods> list = new ArrayList<Goods>();
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + id + "`, `" + name 
					+ "` from `" + table + "`";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String findId = rs.getString(id);
				String findName = rs.getString(name);
				Goods f = new Goods(findId,findName);
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
	
	public static Goods find(String findId) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + id + "`, `" + name 
					+ "` from `" + table 
					+ "` where `" + id +"` =?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				String findName = rs.getString(name);
				Goods f = new Goods(findId,findName);
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
	public static void update(Goods update) throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "update `" + table
					+ "` set `" + name + "` =? "
					+ " where `" + id +"` =?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, update.getName());
			pstm.setString(2, update.getId());
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

	public static void insert(Goods insert) throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table
					+ " (`" + name + "`) "
					+ " value (?)";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, insert.getName());
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
