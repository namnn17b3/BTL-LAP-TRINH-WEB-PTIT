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
			session.setAttribute("soLuongSanPhamTrongGioHang", sanPhamTrongGioHangDao.getSoLuongSanPhamTrongGioHangByIdUser(currentUser.getId()));
		}
		if (req.getServletPath().indexOf("/gio-hang") < 0
				&& req.getServletPath().indexOf("/login") < 0
				&& req.getServletPath().indexOf("/img") < 0
				&& req.getServletPath().indexOf("/img_user") < 0
				&& req.getServletPath().indexOf("/img_sp") < 0
				&& req.getServletPath().indexOf("/css") < 0
				&& req.getServletPath().indexOf("/js") < 0
				&& session.getAttribute("clickGioHang") != null) {
			session.removeAttribute("clickGioHang");
		}
		chain.doFilter(req, resp);
	}
}
