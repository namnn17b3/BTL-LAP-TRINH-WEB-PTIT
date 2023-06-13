package fruitshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/xu-ly-gio-hang", "/gio-hang"})
public class GioHangFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		for (Cookie c : cookies) {
			if (c.getName().equals("clickThemVaoGioHang")) {
				try {					
					String[] data = c.getValue().split("_");
					System.out.println(c.getValue());
					double x = Double.parseDouble(data[0].split("==")[1]);
					double y = Double.parseDouble(data[1].split("==")[1]);
					int soLuong = Integer.parseInt(data[2].split("==")[1]);
					String url = data[3].split("==")[1];
					int id = Integer.parseInt(data[4].split("==")[1]);
					session.setAttribute("x", x);
					session.setAttribute("y", y);
					session.setAttribute("url", url);
					session.setAttribute("id", id);
					session.setAttribute("soLuong", soLuong);
					session.setAttribute("clickThemVaoGioHang", 1);
					c.setPath("/fruitshop/xu-ly-gio-hang");
					c.setMaxAge(0);
					resp.addCookie(c);
				} catch (Exception e) {
					req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
					return;
				}
			}
			else if (c.getName().equals("clickGioHang")) {
				session.setAttribute("clickGioHang", 1);
				c.setPath("/fruitshop/gio-hang");
				c.setMaxAge(0);
				resp.addCookie(c);
			}
		}
		
		// đã đăng nhập
		if (session.getAttribute("clickThemVaoGioHang") != null || session.getAttribute("clickGioHang") != null) {
			if (session.getAttribute("currentUser") == null) {
				// System.out.println("profile filter line 26 null currentUser");
				// session.invalidate();
				resp.sendRedirect("./login");
				return;
			}
			else {
				System.out.println("line 60 giohang filter");
				session.removeAttribute("clickThemVaoGioHang");
				session.setAttribute("forcusDanhGia", 1);
				// session.removeAttribute("clickGioHang");
				chain.doFilter(req, resp);
				return;
			}
		}
		
		// chặn truy cập trực tiếp qua url
		if (session.getAttribute("clickThemVaoGioHang") == null || session.getAttribute("clickGioHang") == null) {
			System.out.println("line 71");
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
	}
}
