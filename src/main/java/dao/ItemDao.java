package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Item;

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
