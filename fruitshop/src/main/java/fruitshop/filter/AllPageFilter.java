package fruitshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fruitshop.dao.SanPhamTrongGioHangDao;
import fruitshop.dao.impl.SanPhamTrongGioHangDaoImpl;
import fruitshop.model.User;

@WebFilter("/*")
public class AllPageFilter implements Filter {
	
	private static final SanPhamTrongGioHangDao sanPhamTrongGioHangDao = new SanPhamTrongGioHangDaoImpl();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		session.setAttribute("soLuongSanPhamTrongGioHang", 0);
		if (session.getAttribute("currentUser") != null) {
			User currentUser = (User) session.getAttribute("currentUser");
//			System.out.println("line 34 all page filter");
			session.setAttribute("soLuongSanPhamTrongGioHang", sanPhamTrongGioHangDao.getSoLuongSanPhamTrongGioHangByIdUser(currentUser.getId()));
		}
//		System.out.println("line 36 " + session.getAttribute("clickMuaNgay"));
//		System.out.println("line 37 " + session.getAttribute("lanDauTruyCap"));
		if (req.getServletPath().indexOf("/gio-hang") < 0
				&& req.getServletPath().indexOf("/thanh-toan") < 0
				&& req.getServletPath().indexOf("/login") < 0
				&& req.getServletPath().indexOf("/img") < 0
				&& req.getServletPath().indexOf("/img_user") < 0
				&& req.getServletPath().indexOf("/img_sp") < 0
				&& req.getServletPath().indexOf("/css") < 0
				&& req.getServletPath().indexOf("/js") < 0
				&& session.getAttribute("clickGioHang") != null) {
			
			session.removeAttribute("clickGioHang");
		}
		
		if (req.getServletPath().indexOf("/thanh-toan") < 0
				&& req.getServletPath().indexOf("/login") < 0
				&& req.getServletPath().indexOf("/img") < 0
				&& req.getServletPath().indexOf("/img_user") < 0
				&& req.getServletPath().indexOf("/img_sp") < 0
				&& req.getServletPath().indexOf("/css") < 0
				&& req.getServletPath().indexOf("/js") < 0
				&& session.getAttribute("clickMuaNgay") != null) {
			
			session.removeAttribute("clickMuaNgay");
		}
		
		if (req.getServletPath().indexOf("/thanh-toan") < 0
				&& req.getServletPath().indexOf("/login") < 0
				&& req.getServletPath().indexOf("/img") < 0
				&& req.getServletPath().indexOf("/img_user") < 0
				&& req.getServletPath().indexOf("/img_sp") < 0
				&& req.getServletPath().indexOf("/css") < 0
				&& req.getServletPath().indexOf("/js") < 0
				&& session.getAttribute("clickTienHanhThanhToan") != null) {
			
			session.removeAttribute("clickTienHanhThanhToan");
		}
		
		if (req.getServletPath().indexOf("/thanh-toan") < 0
				&& req.getServletPath().indexOf("/login") < 0
				&& req.getServletPath().indexOf("/img") < 0
				&& req.getServletPath().indexOf("/img_user") < 0
				&& req.getServletPath().indexOf("/img_sp") < 0
				&& req.getServletPath().indexOf("/css") < 0
				&& req.getServletPath().indexOf("/js") < 0
				&& session.getAttribute("clickTienHanhThanhToan") != null) {
			
			session.removeAttribute("clickTienHanhThanhToan");
		}
		
		if (req.getServletPath().indexOf("/thanh-toan") < 0
				&& req.getServletPath().indexOf("/login") < 0
				&& req.getServletPath().indexOf("/img") < 0
				&& req.getServletPath().indexOf("/img_user") < 0
				&& req.getServletPath().indexOf("/img_sp") < 0
				&& req.getServletPath().indexOf("/css") < 0
				&& req.getServletPath().indexOf("/js") < 0
				&& session.getAttribute("lanDauTruyCap") != null) {
			
			session.removeAttribute("lanDauTruyCap");
		}
		
		if (req.getServletPath().indexOf("/thanh-toan") < 0
				&& req.getServletPath().indexOf("/login") < 0
				&& req.getServletPath().indexOf("/img") < 0
				&& req.getServletPath().indexOf("/img_user") < 0
				&& req.getServletPath().indexOf("/img_sp") < 0
				&& req.getServletPath().indexOf("/css") < 0
				&& req.getServletPath().indexOf("/js") < 0
				&& session.getAttribute("thanhToanThanhCong") != null) {
			
			session.removeAttribute("thanhToanThanhCong");
		}
		
		if (req.getServletPath().indexOf("/chi-tiet-don-hang") < 0
				&& req.getServletPath().indexOf("/danh-gia") < 0
				&& req.getServletPath().indexOf("/login") < 0
				&& req.getServletPath().indexOf("/img") < 0
				&& req.getServletPath().indexOf("/img_user") < 0
				&& req.getServletPath().indexOf("/img_sp") < 0
				&& req.getServletPath().indexOf("/css") < 0
				&& req.getServletPath().indexOf("/js") < 0
				&& session.getAttribute("thanhToanThanhCong") != null) {
			
			session.removeAttribute("danhSachDonHang");
		}
		
		resp.addHeader("Content-Security-Policy", "frame-ancestors 'self'");
		resp.addHeader("X-Frame-Options", "SAMEORIGIN");
		
		chain.doFilter(req, resp);
	}
}
