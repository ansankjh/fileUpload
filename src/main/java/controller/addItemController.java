package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import service.ItemService;
import vo.Item;
import vo.ItemImg;

@WebServlet("/item/addItem")
public class addItemController extends HttpServlet {
	private ItemService itemService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/item/fileUpload.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// MultipartRequest를 이용한 파일업로드
		// MultipartRequest mreq를 사용하기 위한 매개값 4가지 업로드폴더dir(위치값가짐), 최대파일사이즈(maxFileSize), 인코딩(utf-8), 중복이름정책(fp)-> 업로드폴더내에 동일한 이름이 있으면 뒤에 숫자 추가
		
		// 업로드폴더
		String dir = request.getServletContext().getRealPath("/upload");
		// 최대파일사이즈
		int maxFileSize = 1024 * 1024 * 100; // 100Mbtye? 1024byte -> 1Kbtye * 1024 -> 1Mbyte *100 -> 100Mbyte
		// 중복이름정책
		DefaultFileRenamePolicy fp = new DefaultFileRenamePolicy();
		// 
		MultipartRequest mreq = new MultipartRequest(request, dir, maxFileSize, "utf-8", fp);
		
		// 이미지 파일 검사(이미지가 아니면 안올라가게)
		String contentType = mreq.getContentType("fileName"); // input type = file
		if(contentType.equals("image/jpeg") || contentType.equals("image/png")) {
			// MultipartRequest로 원본 request를 랩핑후에는 스트림을 받을 필요없이 
			// MultipartRequest의 api를 사용하면된다
			
			// form에 요청해서 받아온값들
			String itemName = mreq.getParameter("itemName"); // MultipartRequest.getParameter("itemName");
			String img = mreq.getFilesystemName("fileName");
			// System.out.println(itemName + "<-- itemName");
			// System.out.println(img + "<-- imgName");
			
			// 메서드 불러올 매개값
			Item item = new Item();
			item.setItemName(itemName);
			ItemImg itemImg = new ItemImg();
			itemImg.setFilename(img);
			
			// 메서드 호출
			this.itemService = new ItemService();
			int row = itemService.addItem(item, itemImg, dir);
		} else {
			System.out.println("*.jpeg, *.png파일만 업로드 가능");
			File f = new File(dir + "\\" + mreq.getFilesystemName("fileName"));
			if(f.exists()) {
				f.delete(); // 이미지 파일이 아닌 파일이 업로드 되었기 때문에 삭제
			}
		}
		
		// 메서드 실행후 form으로 다시 돌아가게 하기
		response.sendRedirect(request.getContextPath()+"/item/addItem");
		
		/*
		// MultipartRequest로 원본 request를 랩핑후에는 스트림을 받을 필요없이 
		// MultipartRequest의 api를 사용하면된다
		
		// form에 요청해서 받아온값들
		String itemName = mreq.getParameter("itemName"); // MultipartRequest.getParameter("itemName");
		String img = mreq.getFilesystemName("fileName");
		// System.out.println(itemName + "<-- itemName");
		// System.out.println(img + "<-- imgName");
		
		// 메서드 불러올 매개값
		Item item = new Item();
		item.setItemName(itemName);
		ItemImg itemImg = new ItemImg();
		itemImg.setFilename(img);
		
		// 메서드 호출
		this.itemService = new ItemService();
		int row = itemService.addItem(item, itemImg, dir);
		*/
		
	
	}

}
