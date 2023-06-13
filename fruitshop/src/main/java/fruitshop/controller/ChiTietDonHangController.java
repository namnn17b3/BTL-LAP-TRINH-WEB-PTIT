package fruitshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fruitshop.dao.DanhSachDonHangDao;
import fruitshop.dao.DonHangDao;
import fruitshop.dao.impl.DanhSachDonHangDaoImpl;
import fruitshop.dao.impl.DonHangDaoImpl;
import fruitshop.model.DanhSachDonHang;
import fruitshop.model.DonHang;
import fruitshop.model.User;

@WebServlet("/chi-tiet-don-hang")
public class ChiTietDonHangController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DonHangDao donHangDao = new DonHangDaoImpl();
	private static final DanhSachDonHangDao danhSachDonHangDao = new DanhSachDonHangDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser == null) {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		
		int idDanhSachDonHang = 0;
		try {
			idDanhSachDonHang = Integer.parseInt(req.getParameter("id"));
		}
		catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		int page = 0;
		int soLuongDonHang = donHangDao.getSoLuongDonHangByIdDanhSachDonHang(idDanhSachDonHang);
		if (req.getParameter("page") == null) {
			page = 1;
		}
		else {
			try {
				page = Integer.parseInt(req.getParameter("page"));
				int maxPage = (soLuongDonHang % 5 == 0) ? (soLuongDonHang / 5) : (soLuongDonHang / 5 + 1);
				if (page > maxPage) {
					req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
				return;
			}
		}
		DanhSachDonHang danhSachDonHang = danhSachDonHangDao.getDanhSachDonHangById(idDanhSachDonHang);
		if (danhSachDonHang == null) {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		List<DonHang> listDonHang = donHangDao.getListDonHangByIdDanhSachDonHang(idDanhSachDonHang, page);
		for (DonHang donHang : listDonHang) {
			donHang.setDanhGia(donHangDao.tonTaiDanhGiaDonHang(currentUser.getId(), donHang.getIdSanPham(), idDanhSachDonHang));
		}
		req.setAttribute("listDonHang", listDonHang);
		req.setAttribute("soLuongDonHang", soLuongDonHang);
		req.setAttribute("page", page);
		session.setAttribute("danhSachDonHang", danhSachDonHang);
		req.getRequestDispatcher("./chi_tiet_don_hang.jsp").forward(req, resp);
	}

}
