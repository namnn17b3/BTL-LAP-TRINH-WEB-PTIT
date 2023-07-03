package fruitshop.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fruitshop.dao.DanhGiaDao;
import fruitshop.dao.DonHangDao;
import fruitshop.dao.impl.DanhGiaDaoImpl;
import fruitshop.dao.impl.DonHangDaoImpl;
import fruitshop.model.DanhGia;
import fruitshop.model.DanhSachDonHang;
import fruitshop.model.DonHang;
import fruitshop.model.User;

@WebServlet("/danh-gia")
public class DanhGiaController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DanhGiaDao danhGiaDao = new DanhGiaDaoImpl();
	private static final DonHangDao donHangDao = new DonHangDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		DanhSachDonHang danhSachDonHang = (DanhSachDonHang) session.getAttribute("danhSachDonHang");
		int idSanPham = Integer.parseInt(req.getParameter("id"));
		DonHang donHang = donHangDao.getDonHangByIdSanPhamIdDanhDonHang(idSanPham, danhSachDonHang.getId());
		donHang.setDanhGia(donHangDao.tonTaiDanhGiaDonHang(currentUser.getId(), idSanPham, danhSachDonHang.getId()));

		if (danhSachDonHang.getHuy() == 1 || danhSachDonHang.getNgayNhan() == null) {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		if (donHang.getDanhGia() == 0 && danhSachDonHang.getNgayNhan() != null && new Date().getTime() - danhSachDonHang.getNgayNhan().getTime() > 7 * 3600000 * 24) {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		
		DanhGia danhGia = danhGiaDao.getDanhGiaByIdUserIdSanPhamIdDanhSachDonHang(currentUser.getId(), idSanPham, danhSachDonHang.getId());
		if (danhGia == null) {
			danhGia = new DanhGia();
			danhGia.setSoSaoVote(-1);
			danhGia.setNgayBinhLuan(null);
			danhGia.setNoiDungBinhLuan("");
		}
		req.setAttribute("danhGia", danhGia);
		req.setAttribute("donHang", donHang);
		req.getRequestDispatcher("./danh_gia.jsp").forward(req, resp);
		return;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		double x = 0;
		double y = 0;
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("toaDo")) {
				String[] data = c.getValue().split("_");
				x = Double.parseDouble(String.valueOf(data[0].split("==")[1]));
				y = Double.parseDouble(String.valueOf(data[1].split("==")[1]));
				c.setMaxAge(0);
				c.setPath("/fruitshop/danh-gia");
				resp.addCookie(c);
			}
		}
		session.setAttribute("x", x);
		session.setAttribute("y", y);
		DanhSachDonHang danhSachDonHang = (DanhSachDonHang) session.getAttribute("danhSachDonHang");
		if (danhSachDonHang.getHuy() == 1 || danhSachDonHang.getNgayNhan() == null) {
			session.setAttribute("danhGiaStatus", 1);
			resp.sendRedirect("./chi-tiet-don-hang?id="+danhSachDonHang.getId());
			return;
		}
		if (danhSachDonHang.getNgayNhan() != null && new Date().getTime() - danhSachDonHang.getNgayNhan().getTime() > 7 * 3600000 * 24) {
			session.setAttribute("danhGiaStatus", 1);
			resp.sendRedirect("./chi-tiet-don-hang?id="+danhSachDonHang.getId());
			return;
		}
		String noiDungBinhLuan = (String) req.getParameter("noi-dung-danh-gia");
		
		// Convert ISO-8859-1 to UTF-8 
		noiDungBinhLuan = new String(noiDungBinhLuan.getBytes("ISO-8859-1"), "UTF-8");
		
		int soSaoVote = Integer.parseInt(req.getParameter("so-sao-vote"));
		int idSanPham = Integer.parseInt(req.getParameter("id"));
		String thaoTac = req.getParameter("thao-tac");
		int coDanhGia = donHangDao.tonTaiDanhGiaDonHang(currentUser.getId(), idSanPham, danhSachDonHang.getId());
		DonHang donHang = donHangDao.getDonHangByIdSanPhamIdDanhDonHang(idSanPham, danhSachDonHang.getId());
		
		// them danh gia
		if (thaoTac.equals("ok") && coDanhGia == 0) {
			DanhGia danhGia = new DanhGia();
			danhGia.setIdSanPham(idSanPham);
			danhGia.setIdUser(currentUser.getId());
			danhGia.setSoSaoVote(soSaoVote);
			danhGia.setNoiDungBinhLuan(noiDungBinhLuan);
			danhGia.setNgayBinhLuan(new Date());
			danhGiaDao.themDanhGia(danhGia);
			session.setAttribute("tenSanPhamDanhGia", 2);
			session.setAttribute("danhGiaStatus", 2);
			session.setAttribute("tenSanPhamDanhGia", donHang.getTenSanPham());
			resp.sendRedirect("./chi-tiet-don-hang?id="+danhSachDonHang.getId());
			return;
		}
		
		// sua danh gia
		if (thaoTac.equals("ok") && coDanhGia == 1) {
			DanhGia danhGia = danhGiaDao.getDanhGiaByIdUserIdSanPhamIdDanhSachDonHang(currentUser.getId(), idSanPham, danhSachDonHang.getId());
			danhGia.setSoSaoVote(soSaoVote);
			danhGia.setNoiDungBinhLuan(noiDungBinhLuan);
			danhGia.setNgayBinhLuan(new Date());
			danhGiaDao.capNhatDanhGia(danhGia);
			session.setAttribute("danhGiaStatus", 3);
			session.setAttribute("tenSanPhamDanhGia", donHang.getTenSanPham());
			resp.sendRedirect("./chi-tiet-don-hang?id="+danhSachDonHang.getId());
			return;
		}
		
		// xoa danh gia
		if (thaoTac.equals("xoa")) {
			DanhGia danhGia = danhGiaDao.getDanhGiaByIdUserIdSanPhamIdDanhSachDonHang(currentUser.getId(), idSanPham, danhSachDonHang.getId());
			danhGiaDao.xoaDanhGia(danhGia);
			session.setAttribute("danhGiaStatus", 4);
			session.setAttribute("tenSanPhamDanhGia", donHang.getTenSanPham());
			resp.sendRedirect("./chi-tiet-don-hang?id="+danhSachDonHang.getId());
			return;
		}
	}
}
