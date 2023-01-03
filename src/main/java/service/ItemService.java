package service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;

import dao.ItemDao;
import dao.ItemImgDao;
import vo.Item;
import vo.ItemImg;

public class ItemService {
	private ItemDao itemDao;
	private ItemImgDao itemImgDao;
	
	public ArrayList<HashMap<String, Object>> getItemList() {
		// dao 초기화&공간확보
		itemDao = new ItemDao();
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = null;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/fileupload", "root", "java1234");
			// 오토커밋 끄기
			conn.setAutoCommit(false);
			// dao 호출
			list = itemDao.selectItemList(conn);
			conn.commit();
		} catch(Exception e) {
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
		return list;
	}
	
	public int addItem(Item item, ItemImg itemImg, String dir) {
		// dao 초기화&공간확보
		itemDao = new ItemDao();
		itemImgDao = new ItemImgDao();
		// 객체 초기화
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/fileupload", "root", "java1234");
			// 오토커밋 끄기
			conn.setAutoCommit(false);
			// dao 호출
			HashMap<String, Integer> map = itemDao.insertItem(conn, item);
			
			itemImg.setItemNo(map.get("autoKey"));
			itemImgDao.insertItem(conn, itemImg); // itemImg.getItemNo() --> 0
			// 커밋
			conn.commit();
		} catch(Exception e) {
			//실패시 롤백&파일삭제
			try {
				conn.rollback();
				// db 작업에 실패 시 이미 업로드 되어있는 파일 삭제
				File f = new File(dir+"\\"+itemImg.getFilename());
				if(f.exists()) {
					f.delete();
				}
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
		return row;
	}
} 
