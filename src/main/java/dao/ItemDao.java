package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Item;
import vo.ItemImg;

public class ItemDao {
	// 상품 상세보기 : 조인으로 해결
	public ArrayList<HashMap<String, Object>> selectItemList(Connection conn) throws Exception {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = null;
		// 쿼리문 작성
		String sql = "SELECT it.item_no itemNo, it.item_name itemName, img.filename fileName"
				+ "		FROM item it INNER JOIN item_img img "
				+ " 	ON it.item_no = img.item_no";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		list = new ArrayList<HashMap<String, Object>>();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("itemNo", rs.getInt("itemNo"));
			m.put("itemName", rs.getString("itemName"));
			m.put("fileName", rs.getString("fileName"));
			list.add(m);
		}
		// 자원반납, 리턴값
		stmt.close();
		rs.close();
		return list;
	}
	
	// updateFileForm
	public ArrayList<HashMap<String, Object>> selectItemListByUpdate(Connection conn, int itemNo) throws Exception {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = null;
		// 쿼리문 작성
		String sql = "SELECT it.item_no itemNo, it.item_name itemName, img.filename fileName"
				+ "		FROM item it INNER JOIN item_img img "
				+ " 	ON it.item_no = img.item_no"
				+ "		WHERE it.item_no=?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, itemNo);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		list = new ArrayList<HashMap<String, Object>>();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("itemNo", rs.getInt("itemNo"));
			m.put("itemName", rs.getString("itemName"));
			m.put("fileName", rs.getString("fileName"));
			list.add(m);
		}
		// 자원반납, 리턴값
		stmt.close();
		rs.close();
		return list;
	}
	
	// updateFileAction
	public int updateFile(Connection conn, Item item, ItemImg itemImg) throws Exception  {
		// 객체 초기화
		int itRow = 0;
		int imgRow = 0;
		// 쿼리문 작성
		String itSql = "UPDATE item SET item_name=? WHERE item_no=?";
		// 쿼리 객체 생성
		PreparedStatement itStmt = conn.prepareStatement(itSql);
		// 쿼리문 ?값 지정
		itStmt.setString(1, item.getItemName());
		itStmt.setInt(2, item.getItemNo());
		// 쿼리 실행
		itRow = itStmt.executeUpdate();
		
		// 쿼리문 작성
		String imgSql = "UPDATE item SET filename=? WHERE item_no=?";
		// 쿼리 객체 생성
		PreparedStatement imgStmt = conn.prepareStatement(imgSql);
		// 쿼리문 ?값 지정
		imgStmt.setString(1, itemImg.getFilename());
		imgStmt.setInt(2, itemImg.getItemNo());
		// 쿼리 실행
		imgRow = imgStmt.executeUpdate();
		
		
		imgStmt.close();
		itStmt.close();
		return itRow+imgRow;
	}
	
	// updateFileForm
	public HashMap<String, Object> selectListByUpdate(Connection conn, int itemNo) throws Exception {
		// 객체 초기화
		HashMap<String, Object> map = null;
		// 쿼리문 작성
		String sql = "SELECT it.item_no itemNo, it.item_name itemName, img.filename fileName"
				+ "		FROM item it INNER JOIN item_img img"
				+ "		ON it.item_no = img.item_no"
				+ "		WHERE it.item_no=?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, itemNo);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			map = new HashMap<String, Object>();
			map.put("itemNo", rs.getInt("itemNo"));
			map.put("itemName", rs.getString("itemName"));
			map.put("fileNmae", rs.getString("fileName"));
		}
		
		stmt.close();
		rs.close();
		return map;
	}
	
	public HashMap<String, Integer> insertItem(Connection conn, Item item) throws Exception {
		// 객체초기화
		HashMap<String, Integer> map = null;
		// 쿼리문 작성
		String sql = "INSERT INTO item(item_name) values(?)";
		// 쿼리문으로 생성된 auto_increment item_no를 바로 받고 싶으면 PreparedStatement.RETURN_GENERATED_KEYS 쿼리 실행 후 생성된 auto_increment값을 ResultSet에 반환
		PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setString(1, item.getItemName());
		
		int row = stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		int autoKey = 0;
		if(rs.next()) {
			autoKey = rs.getInt(1); // stmt.executeUpdate();에서 생성된 auto_increment key값을 바로 받아온다.
		}
		
		map = new HashMap<String, Integer>();
		map.put("row", row);
		map.put("autoKey", autoKey);
		
		stmt.close();
		return map;
	}
}
