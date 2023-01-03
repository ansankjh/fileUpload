package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ItemService;

/**
 * Servlet implementation class UpdateFileController
 */
@WebServlet("/item/updateFile")
public class UpdateFileController extends HttpServlet {
	private ItemService itemService;
	
	// updateForm
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메서드에 넣을 매개변수 요청하여 받기
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		System.out.println(itemNo);
		
		// itemNo로 수정할 값들 받아서 action에 넘겨주기
		this.itemService = new ItemService();
		ArrayList<HashMap<String, Object>> list = itemService.getItemListByUpdate(itemNo);
		
		request.setAttribute("m", list);
		
		request.getRequestDispatcher("/WEB-INF/view/item/updateFile.jsp").forward(request, response);
	}

	// updateAction
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
