package fruitshop.api.sanpham;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fruitshop.dao.SanPhamDao;
import fruitshop.dao.impl.SanPhamDaoImpl;
import fruitshop.model.SanPham;

@WebServlet("/api/san-pham/search-san-pham-by-name")
public class SearchSanPhamByNameAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SanPhamDao sanPhamDao = new SanPhamDaoImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String tenSanPham = req.getParameter("ten-san-pham");
		int page = Integer.parseInt(req.getParameter("page"));
		List<SanPham> listSanPham = sanPhamDao.searchSanPhamByName(tenSanPham, page);
		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/json");
		
		Gson gson = new Gson();
		String json = new String(gson.toJson(listSanPham).getBytes("ISO-8859-1"), "UTF-8");
		
		writer.println(json);
	}
}
