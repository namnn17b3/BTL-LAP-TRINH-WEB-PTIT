package fruitshop.api.admin.top5khachhang;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fruitshop.dao.UserDao;
import fruitshop.dao.impl.UserDaoImpl;
import fruitshop.model.User;

@WebServlet("/api/admin/top-5-khach-hang")
public class Top5KhachHangAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final UserDao userDao = new UserDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int choose = -1;
		try {
			choose = Integer.parseInt(req.getParameter("choose"));
			if (choose == 0) {
				List<User> list = userDao.getListTop5KhachHangTheoTongChiTieu();
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(list);
				
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json;charset=UTF-8");
				resp.getWriter().println(json);
			}
			else if (choose == 1) {
				List<User> list = userDao.getListTop5KhachHangTheoSoLuongMua();
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(list);
				
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json;charset=UTF-8");
				resp.getWriter().println(json);
			}
			else {
				resp.sendError(400, "invalid parameter!");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(400, "invalid parameter!");
			return;
		}
	}
}
