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
 * Servlet implementation class ItemListController
 */
@WebServlet("/item/itemList")
public class ItemListController extends HttpServlet {
	private ItemService itemService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.itemService = new ItemService();
		ArrayList<HashMap<String, Object>> list = itemService.getItemList();
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/item/itemList.jsp").forward(request, response);
	}
}
