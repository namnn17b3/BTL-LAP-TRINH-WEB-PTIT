package fruitshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fruitshop.dao.DanhGiaDao;
import fruitshop.dao.SanPhamDao;
import fruitshop.dao.impl.DanhGiaDaoImpl;
import fruitshop.dao.impl.SanPhamDaoImpl;
import fruitshop.model.DanhGia;
import fruitshop.model.SanPham;

@WebServlet("/chi-tiet-san-pham")
public class ChiTietSanPhamController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final SanPhamDao sanPhamDao =  new SanPhamDaoImpl();
	private static final DanhGiaDao danhGiaDao = new DanhGiaDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int choose = -1;
		int id = 0;
		SanPham sanPham = null;
		try {
			id = Integer.parseInt(req.getParameter("id"));
			// System.out.println(id);
			sanPham = sanPhamDao.getSanPhamById(id);
			if (sanPham == null) {
				req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
				return;
			}
			if (req.getParameter("choose") != null) {
				choose = Integer.parseInt(req.getParameter("choose"));
				if (choose < -1 || choose > 5) {
					req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
					return;
				}
			}
		}
		catch (Exception e) {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		int page = 1;
		int soLuongTatCa = danhGiaDao.getAllSoLuongDanhGiaChoSanPhamByIdAndStar(id, -1);
		int soLuongNamSao = danhGiaDao.getAllSoLuongDanhGiaChoSanPhamByIdAndStar(id, 5);
		int soLuongBonSao = danhGiaDao.getAllSoLuongDanhGiaChoSanPhamByIdAndStar(id, 4);
		int soLuongBaSao = danhGiaDao.getAllSoLuongDanhGiaChoSanPhamByIdAndStar(id, 3);
		int soLuongHaiSao = danhGiaDao.getAllSoLuongDanhGiaChoSanPhamByIdAndStar(id, 2);
		int soLuongMotSao = danhGiaDao.getAllSoLuongDanhGiaChoSanPhamByIdAndStar(id, 1);
		int soLuongKhongSao = danhGiaDao.getAllSoLuongDanhGiaChoSanPhamByIdAndStar(id, 0);
		int soLuongDanhGiaTheoPhanLoai = soLuongTatCa;
		if (choose == 5) {
			soLuongDanhGiaTheoPhanLoai = soLuongNamSao;
		}
		else if (choose == 4) {
			soLuongDanhGiaTheoPhanLoai = soLuongBonSao;
		}
		else if (choose == 3) {
			soLuongDanhGiaTheoPhanLoai = soLuongBaSao;
		}
		else if (choose == 2) {
			soLuongDanhGiaTheoPhanLoai = soLuongHaiSao;
		}
		else if (choose == 1) {
			soLuongDanhGiaTheoPhanLoai = soLuongMotSao;
		}
		else if (choose == 0) {
			soLuongDanhGiaTheoPhanLoai = soLuongKhongSao;
		}
		try {
			if (req.getParameter("page") != null) {
				page = Integer.parseInt(req.getParameter("page"));
				int totalPage = (soLuongDanhGiaTheoPhanLoai % 5 == 0) ? soLuongDanhGiaTheoPhanLoai / 5 : soLuongDanhGiaTheoPhanLoai / 5 + 1;
				if ((page < 1 || page > totalPage) && page != 1 && totalPage != 0) {
					req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
					return;
				}
			}
			if (req.getParameter("choose") != null && req.getParameter("page") != null) {
				req.setAttribute("forcusDanhGia", 1);
			}
			else {
				req.setAttribute("forcusDanhGia", 0);
			}
		}
		catch (Exception e) {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		
		req.setAttribute("sanPham", sanPham);		
		List<DanhGia> currentListDanhGia = danhGiaDao.getDanhGiaChoSanPhamByIdAndPageChoose(id, choose, page);
		req.setAttribute("soLuongTatCa", soLuongTatCa);
		req.setAttribute("soLuongNamSao", soLuongNamSao);
		req.setAttribute("soLuongBonSao", soLuongBonSao);
		req.setAttribute("soLuongBaSao", soLuongBaSao);
		req.setAttribute("soLuongHaiSao", soLuongHaiSao);
		req.setAttribute("soLuongMotSao", soLuongMotSao);
		req.setAttribute("soLuongKhongSao", soLuongKhongSao);
		req.setAttribute("page", page);
		req.setAttribute("choose", choose);
		req.setAttribute("soLuongDanhGiaTheoPhanLoai", soLuongDanhGiaTheoPhanLoai);
		req.setAttribute("currentListDanhGia", currentListDanhGia);
		
		req.getRequestDispatcher("./chi_tiet_san_pham.jsp").forward(req, resp);
	}
}
