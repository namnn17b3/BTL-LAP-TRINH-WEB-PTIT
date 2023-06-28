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

import fruitshop.model.User;

@WebFilter("/api/admin/*")
public class AdminAPIFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser == null) {
			// System.out.println("profile filter line 26 null currentUser");
			// session.invalidate();
			resp.sendError(403, "error authentication");
			return;
		}
		if (currentUser != null && currentUser.getVaiTro().equals("u")) {
			// System.out.println("profile filter line 26 null currentUser");
			// session.invalidate();
			resp.sendError(403, "error authentication");
			return;
		}
		chain.doFilter(request, response);
	}
}