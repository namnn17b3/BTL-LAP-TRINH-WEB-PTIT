package fruitshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fruitshop.dao.DanhSachChuyenKhoanDao;
import fruitshop.dao.DanhSachDonHangDao;
import fruitshop.dao.impl.DanhSachChuyenKhoanDaoImpl;
import fruitshop.dao.impl.DanhSachDonHangDaoImpl;
import fruitshop.model.DanhSachChuyenKhoan;
import fruitshop.model.DanhSachDonHang;
import fruitshop.model.User;
import fruitshop.utils.Pair;

@WebServlet("/don-hang")
public class DonHangController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DanhSachChuyenKhoanDao danhSachChuyenKhoanDao = new DanhSachChuyenKhoanDaoImpl();
	private static final DanhSachDonHangDao danhSachDonHangDao = new DanhSachDonHangDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser == null) {
			resp.sendRedirect("./home");
			return;
		}
		
		int page = 0;
		int choose = 0;
		if (req.getParameter("choose") == null) {
			choose = 0;
		}
		else {
			try {				
				choose = Integer.parseInt(req.getParameter("choose"));
				if (choose > 4) {
					req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
					return;
				}
			} catch (Exception e) {
				req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
				return;
			}
		}
		
		int soLuongDanhSachDonHang = danhSachDonHangDao.getSoLuongDanhSachDonHangByIdUser(currentUser.getId(), choose);
		
		if (req.getParameter("page") == null) {
			page = 1;
		}
		else {
			try {				
				page = Integer.parseInt(req.getParameter("page"));
				int maxPage = (soLuongDanhSachDonHang % 5 == 0) ? soLuongDanhSachDonHang / 5 : soLuongDanhSachDonHang / 5 + 1;
				if (page > maxPage) {
					req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
					return;
				}
			} catch (Exception e) {
				req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
				return;
			}
		}
		
		List<DanhSachDonHang> listDanhSachDonHang = danhSachDonHangDao.getListDanhSachDonHangByIdUser(currentUser.getId(), choose, page);
		List<DanhSachChuyenKhoan> listDanhSachChuyenKhoan = danhSachChuyenKhoanDao.getListDanhSachChuyenKhoanByListDanhSachDonHang(listDanhSachDonHang);
		List<Pair<DanhSachDonHang, DanhSachChuyenKhoan>> listPair = new ArrayList<>();
		for (int i = 0; i < listDanhSachDonHang.size(); i++) {
			listPair.add(new Pair<>(listDanhSachDonHang.get(i), listDanhSachChuyenKhoan.get(i)));
		}
		req.setAttribute("listPair", listPair);
		req.setAttribute("soLuongDanhSachDonHang", soLuongDanhSachDonHang);
		req.setAttribute("page", page);
		req.setAttribute("choose", choose);
		req.getRequestDispatcher("./don_hang.jsp").forward(req, resp);
	}
}
