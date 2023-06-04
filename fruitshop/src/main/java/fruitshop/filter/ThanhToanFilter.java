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

@WebFilter("/thanh-toan")
public class ThanhToanFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		if (session.getAttribute("thanhToanThanhCong") != null) {
			req.getRequestDispatcher("./thanh_toan_thanh_cong.jsp").forward(req, resp);
			return;
		}
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("clickMuaNgay")) {
				System.out.println(c.getValue() + " line 28 thanh toan filter");
				String[] data = c.getValue().split("_");
				int id = Integer.parseInt(data[0].split("==")[1]);
				int soLuong = Integer.parseInt(data[1].split("==")[1]);
				int tongTien = Integer.parseInt(data[2].split("==")[1]);
				session.setAttribute("clickMuaNgay", 1);
				session.setAttribute("id", id);
				session.setAttribute("soLuong", soLuong);
				session.setAttribute("tongTien", tongTien);
				c.setMaxAge(0);
				c.setPath("/fruitshop/thanh-toan");
				resp.addCookie(c);
				break;
			}
			else if (c.getName().equals("clickTienHanhThanhToan")) {
				System.out.println(session.getAttribute("tongTien"));
				session.setAttribute("clickTienHanhThanhToan", 1);
				c.setMaxAge(0);
				c.setPath("/fruitshop/thanh-toan");
				resp.addCookie(c);
				break;
			}
		}
		
		if (session.getAttribute("clickMuaNgay") != null || session.getAttribute("clickTienHanhThanhToan") != null) {
			if (session.getAttribute("currentUser") == null) {
				// System.out.println("profile filter line 26 null currentUser");
				// session.invalidate();
				resp.sendRedirect("./login");
				return;
			}
			else {
				chain.doFilter(req, resp);
				return;
			}
		}
		
		if (session.getAttribute("clickMuaNgay") == null || session.getAttribute("clickTienHanhThanhToan") == null) {
			System.out.println("line 70 thanh toan filter");
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
	}
}
