package fruitshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fruitshop.model.SanPham;
import fruitshop.service.SanPhamService;

@WebServlet("/search")
public class SearchController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SanPhamService sanPhamService = new SanPhamService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String tenSanPham = req.getParameter("ten-san-pham");
		// System.out.println(tenSanPham);
		int page = 1;
		if (req.getParameter("page") != null) {
			try {
				page = Integer.parseInt(req.getParameter("page"));				
			} catch (Exception e) {
				req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
				return;
			}
		}
		List<SanPham> listSanPham = sanPhamService.searchSanPhamByName(tenSanPham, page);
		if (listSanPham.size() == 0) {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		int soLuongSanPhamAll = sanPhamService.getSoLuongSanPhamByName(tenSanPham);
		req.setAttribute("listSanPham", listSanPham);
		req.setAttribute("page", page);
		req.setAttribute("soLuongSanPhamAll", soLuongSanPhamAll);
		req.setAttribute("tenSanPham", tenSanPham);
		req.getSession().setAttribute("tenSanPham", tenSanPham);
		req.getRequestDispatcher("./ket_qua_tim_kiem.jsp").forward(req, resp);
	}
}
