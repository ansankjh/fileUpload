package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import dao.ItemDao;
import dao.ItemImgDao;
import vo.Item;
import vo.ItemImg;

public class ItemService {
	private ItemDao itemDao;
	private ItemImgDao itemImgDao;
	public void addItem(Item item, ItemImg itemImg) {
		// dao 초기화&공간확보
		itemDao = new ItemDao();
		itemImgDao = new ItemImgDao();
		// 객체 초기화
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/fileuplod", "rood", "java1234");
			// 오토커밋 끄기
			conn.setAutoCommit(false);
			// dao 호출
			HashMap<String, Integer> map = itemDao.insertItem(conn, item);
			
			itemImg.setItemNo(map.get("autoKey"));
			itemImgDao.insertItem(conn, itemImg); // itemImg.getItemNo() --> 0
			// 커밋
			conn.commit();
		} catch(Exception e) {
			//실패
			try {
				conn.rollback();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
