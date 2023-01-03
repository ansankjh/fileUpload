package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import vo.Item;

public class ItemDao {
	public HashMap<String, Integer> insertItem(Connection conn, Item item) throws Exception {
		// 객체초기화
		
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
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("row", row);
		map.put("autoKey", autoKey);
		
		stmt.close();
		return map;
	}
}
