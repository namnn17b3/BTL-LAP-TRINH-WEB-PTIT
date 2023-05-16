package fruitshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fruitshop.dao.SanPhamDao;
import fruitshop.dao.impl.SanPhamDaoImpl;
import fruitshop.model.SanPham;

@WebServlet("/danh-sach-san-pham")
public class DanhSachSanPhamTheoLoaiController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SanPhamDao sanPhamDao = new SanPhamDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String loaiSanPham = req.getParameter("loai");
		String loaiSanPhamRequest = loaiSanPham;
		int page = 1;
		if (req.getParameter("page") != null) {
			try {
				page = Integer.parseInt(req.getParameter("page"));				
			} catch (Exception e) {
				req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
				return;
			}
		}
		if (loaiSanPham.equals("tat-ca")) {
			loaiSanPham = "Tất cả";
		}
		else if (loaiSanPham.equals("gio-qua-tang")) {
			loaiSanPham = "Giỏ quà tặng trái cây";
		}
		else if (loaiSanPham.equals("mua-nguyen-thung")) {
			loaiSanPham = "Mua nguyên thùng";
		}
		else if (loaiSanPham.equals("mua-le")) {
			loaiSanPham = "Mua lẻ";
		}
		else if (loaiSanPham.equals("combo")) {
			loaiSanPham = "Combo";
		}
		else if (loaiSanPham.equals("do-kho")) {
			loaiSanPham = "Đồ khô";
		}
		else if (loaiSanPham.equals("trai-cay-nam-phi")) {
			loaiSanPham = "Nam Phi";
		}
		else if (loaiSanPham.equals("trai-cay-han-quoc")) {
			loaiSanPham = "Hàn Quốc";
		}
		else if (loaiSanPham.equals("trai-cay-my")) {
			loaiSanPham = "Mỹ";
		}
		else if (loaiSanPham.equals("trai-cay-uc")) {
			loaiSanPham = "Úc";
		}
		else if (loaiSanPham.equals("trai-cay-newzealand")) {
			loaiSanPham = "New Zealand";
		}
		else if (loaiSanPham.equals("trai-cay-chile")) {
			loaiSanPham = "Chile";
		}
		else if (loaiSanPham.equals("trai-cay-nuoc-khac")) {
			loaiSanPham = "Nhiều nước";
		}
		else {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		List<SanPham> listSanPham = sanPhamDao.getListSanPhamByLoai(loaiSanPham, page);
		if (listSanPham.size() == 0) {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		int soLuongSanPhamAll = sanPhamDao.getSoLuongSanPhamByLoai(loaiSanPham);
		req.setAttribute("listSanPham", listSanPham);
		req.setAttribute("soLuongSanPhamAll", soLuongSanPhamAll);
		req.setAttribute("page", page);
		req.setAttribute("loaiSanPham", loaiSanPhamRequest);
		
		req.getRequestDispatcher("./danh_sach_san_pham.jsp").forward(req, resp);
	}
}
