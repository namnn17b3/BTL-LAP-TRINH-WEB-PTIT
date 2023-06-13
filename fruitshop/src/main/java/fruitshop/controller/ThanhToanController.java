package fruitshop.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fruitshop.dao.DanhSachChuyenKhoanDao;
import fruitshop.dao.DanhSachDonHangDao;
import fruitshop.dao.DonHangDao;
import fruitshop.dao.SanPhamDao;
import fruitshop.dao.SanPhamTrongGioHangDao;
import fruitshop.dao.impl.DanhSachChuyenKhoanDaoImpl;
import fruitshop.dao.impl.DanhSachDonHangDaoImpl;
import fruitshop.dao.impl.DonHangDaoImpl;
import fruitshop.dao.impl.SanPhamDaoImpl;
import fruitshop.dao.impl.SanPhamTrongGioHangDaoImpl;
import fruitshop.model.DanhSachChuyenKhoan;
import fruitshop.model.DanhSachDonHang;
import fruitshop.model.DonHang;
import fruitshop.model.SanPham;
import fruitshop.model.SanPhamTrongGioHang;
import fruitshop.model.User;
import fruitshop.utils.Email;

@WebServlet("/thanh-toan")
public class ThanhToanController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SanPhamDao sanPhamDao = new SanPhamDaoImpl();
	private static final DonHangDao donHangDao = new DonHangDaoImpl();
	private static final SanPhamTrongGioHangDao sanPhamTrongGioHangDao = new SanPhamTrongGioHangDaoImpl();
	private static final DanhSachDonHangDao danhSachDonHangDao = new DanhSachDonHangDaoImpl();
	private static final DanhSachChuyenKhoanDao danhSachChuyenKhoanDao = new DanhSachChuyenKhoanDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("lanDauTruyCap") == null) {
			resp.setHeader("Refresh", "300;URL=./home");
			req.getRequestDispatcher("./thanh_toan.jsp").forward(req, resp);
			return;
		}
		String time = "300";
		for (Cookie c : req.getCookies()) {
			if (c.getName().equals("time")) {
				time = c.getValue();
				break;
			}
		}
		resp.setHeader("Refresh", time+";URL=./home");
		req.getRequestDispatcher("./thanh_toan.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		User currentUser = (User) session.getAttribute("currentUser");
		String ten = (String) req.getParameter("ten");
		String email = (String) req.getParameter("email");
		String diaChiNguoiNhan = (String) req.getParameter("dia-chi-nguoi-nhan");
		String soDienThoaiNguoiNhan = (String) req.getParameter("so-dien-thoai-nguoi-nhan");
		String hinhThucThanhToan = (String) req.getParameter("hinh-thuc-thanh-toan");
		String soTaiKhoanNguoiChuyen = null;
		String nganHangNguoiChuyen = null;
		String ngayChuyenKhoan = null;
		DanhSachDonHang danhSachDonHang = new DanhSachDonHang();
		danhSachDonHang.setIdUser(currentUser.getId());
		danhSachDonHang.setTenNguoiNhan(ten);
		danhSachDonHang.setDiaChiNguoiNhan(diaChiNguoiNhan);
		danhSachDonHang.setSoDienThoaiNguoiNhan(soDienThoaiNguoiNhan);
		danhSachDonHang.setSoLuongSanPham(session.getAttribute("clickMuaNgay") != null ? 1 : sanPhamTrongGioHangDao.getSoLuongSanPhamTrongGioHangByIdUser(currentUser.getId()));
		danhSachDonHang.setNgayXuat(new Date());
		danhSachDonHang.setTongTien(Integer.parseInt(String.valueOf(session.getAttribute("tongTien"))));
		danhSachDonHang.setThanhToan(hinhThucThanhToan.equals("1") ? "Chuyển khoản" : "Tiền mặt");
		danhSachDonHangDao.themDanhSachDonHang(danhSachDonHang);
		int idDanhSachDonHang = danhSachDonHangDao.getIdLastDanhSachDonHang();
		
		if (hinhThucThanhToan.equals("1")) {
			soTaiKhoanNguoiChuyen = (String) req.getParameter("so-tai-khoan-nguoi-chuyen");
			nganHangNguoiChuyen = (String) req.getParameter("ngan-hang-nguoi-chuyen");
			ngayChuyenKhoan = (String) req.getParameter("ngay-chuyen-khoan");

			DanhSachChuyenKhoan danhSachChuyenKhoan = new DanhSachChuyenKhoan();
			danhSachChuyenKhoan.setIdDanhSachDonHang(idDanhSachDonHang);
			try {
				danhSachChuyenKhoan.setNgayChuyenKhoan(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(ngayChuyenKhoan));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			danhSachChuyenKhoan.setSoTienChuyenKhoan(Integer.parseInt(String.valueOf(session.getAttribute("tongTien"))));
			danhSachChuyenKhoan.setSoTaiKhoanNguoiChuyen(soTaiKhoanNguoiChuyen);
			danhSachChuyenKhoan.setTenNganHangNguoiChuyen(nganHangNguoiChuyen);
			danhSachChuyenKhoanDao.themDanhSachChuyenKhoan(danhSachChuyenKhoan);
		}
		
		if (session.getAttribute("clickMuaNgay") != null) {
			int idSanPham  = (int) session.getAttribute("id");
			int soLuong = (int) session.getAttribute("soLuong");
			SanPham sanPham = sanPhamDao.getSanPhamById(idSanPham);
			if (sanPham.getSoLuongNhap() < sanPham.getSoLuongBan() + soLuong) {
				req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
				return;
			}
			DonHang donHang = new DonHang();
			donHang.setIdDanhSachDonHang(idDanhSachDonHang);
			donHang.setIdSanPham(idSanPham);
			donHang.setSoLuong(soLuong);
			donHangDao.themDonHang(donHang);
		}
		else {	
			List<SanPhamTrongGioHang> listSanPhamTrongGioHang = sanPhamTrongGioHangDao.getAllSanPhamTrongGioHang(currentUser.getId());
			for (SanPhamTrongGioHang sanPhamTrongGioHang : listSanPhamTrongGioHang) {
				DonHang donHang = new DonHang();
				donHang.setIdDanhSachDonHang(idDanhSachDonHang);
				donHang.setIdSanPham(sanPhamTrongGioHang.getIdSanPham());
				donHang.setSoLuong(sanPhamTrongGioHang.getSoLuong());
				donHangDao.themDonHang(donHang);
			}
			sanPhamTrongGioHangDao.xoaSanPhamTrongGioHangByIdUser(currentUser.getId());
		}
		
		// gui email thanh cong
		// String noiDung = 
		// "<h3>Cảm ơn bạn đã tin tưởng mua sản phẩm của chúng tôi</h3>"
		// +"<div>Mã đơn hàng của bạn: "+"FSDH"+idDanhSachDonHang+"</div>"
		// +"<div>Ngày đặt đơn: "+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(danhSachDonHang.getNgayXuat())+"</div>"
		// +"<div>Tổng tiền: "+danhSachDonHang.getTongTien()+"</div>"
		// + "<div>Hãy theo dõi đơn hàng của bạn nhé!</div>"
		// +"<div style='color: red'><i>Chú ý: Đây là mail tự động! Vui lòng không reply!</i></div>";
		// Email.sendMail(email, noiDung);
		session.removeAttribute("clickMuaNgay");
		session.removeAttribute("clickTienHanhThanhToan");
		session.setAttribute("thanhToanThanhCong", 1);
		session.setAttribute("soLuongSanPhamTrongGioHang", 0);
		req.getRequestDispatcher("./thanh_toan_thanh_cong.jsp").forward(req, resp);
	}
}
