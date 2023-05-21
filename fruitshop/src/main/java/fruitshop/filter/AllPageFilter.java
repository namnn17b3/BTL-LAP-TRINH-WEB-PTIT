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

import fruitshop.dao.DonHangDao;
import fruitshop.dao.impl.DonHangDaoImpl;
import fruitshop.model.User;

@WebFilter("/*")
public class AllPageFilter implements Filter {
	
	private static final DonHangDao donHangDao = new DonHangDaoImpl();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		session.setAttribute("soLuongSanPhamTrongGioHang", 0);		
		if (session.getAttribute("currentUser") != null) {
			User currentUser = (User) session.getAttribute("currentUser");
			session.setAttribute("soLuongSanPhamTrongGioHang", donHangDao.getSoLuongSanPhamTrongGioHangByIdUser(currentUser.getId()));
		}
		chain.doFilter(req, resp);
	}
}
