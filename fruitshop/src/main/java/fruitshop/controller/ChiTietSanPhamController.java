package fruitshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fruitshop.model.DonHang;
import fruitshop.model.SanPham;
import fruitshop.service.DonHangService;
import fruitshop.service.SanPhamService;

@WebServlet("/chi-tiet-san-pham")
public class ChiTietSanPhamController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static SanPhamService sanPhamService =  new SanPhamService();
	private static DonHangService donHangService = new DonHangService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("id") == null) {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
		}
		else {
			int id = Integer.parseInt(req.getParameter("id"));
			SanPham sanPham = sanPhamService.getSanPhamById(id);
			req.setAttribute("sanPham", sanPham);
			
			int soLuongTatCa = donHangService.getAllSoLuongDanhGiaChoSanPhamByIdAndStar(id, -1);
			int soLuongNamSao = donHangService.getAllSoLuongDanhGiaChoSanPhamByIdAndStar(id, 5);
			int soLuongBonSao = donHangService.getAllSoLuongDanhGiaChoSanPhamByIdAndStar(id, 4);
			int soLuongBaSao = donHangService.getAllSoLuongDanhGiaChoSanPhamByIdAndStar(id, 3);
			int soLuongHaiSao = donHangService.getAllSoLuongDanhGiaChoSanPhamByIdAndStar(id, 2);
			int soLuongMotSao = donHangService.getAllSoLuongDanhGiaChoSanPhamByIdAndStar(id, 1);
			int soLuongKhongSao = donHangService.getAllSoLuongDanhGiaChoSanPhamByIdAndStar(id, 0);
			int choose = -1;
			int page = 1;
			if (req.getParameter("choose") != null) {
				choose = Integer.parseInt(req.getParameter("choose"));
			}
			if (req.getParameter("page") != null) {
				page = Integer.parseInt(req.getParameter("page"));
			}
			if (req.getParameter("choose") != null && req.getParameter("page") != null) {
				req.setAttribute("forcusDanhGia", 1);
			}
			else {
				req.setAttribute("forcusDanhGia", 0);
			}
			List<DonHang> currentListDanhGia = donHangService.getDanhGiaChoSanPhamByIdAndPageChoose(id, choose, page);
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
			if (currentListDanhGia.size() == 0) {
				req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			}
			else {
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
	}
}
