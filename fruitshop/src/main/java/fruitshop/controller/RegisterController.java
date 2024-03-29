package fruitshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import fruitshop.dao.UserDao;
import fruitshop.dao.impl.UserDaoImpl;
import fruitshop.model.User;
import fruitshop.utils.Email;
import fruitshop.utils.RanDomCode;
import fruitshop.utils.Sha1;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final UserDao userDao = new UserDaoImpl();
	
	private int statusValidation(User user, String nhapLaiMatKhau) {
		if (user.getTen().matches("^.{1,50}$") == false) {
			return 1;
		}
		if (user.getEmail().matches("^([a-zA-Z0-9\\.]+)@([a-zA-H0-9\\.].+)$") == false) {
			return 2;
		}
		if (user.getMatKhau().matches("^.{8,}$") == false) {
			return 3;
		}
		if (nhapLaiMatKhau.equals(user.getMatKhau()) == false) {
			return 4;
		}
		return 0;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("register get");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		if (session.getAttribute("thongBaoXacNhan") != null && session.getAttribute("thongBaoXacNhan").equals("1")) {
			// System.out.println("ok else get");
			Date currentTime = new Date();
			Date thoiDiemTaoCode = (Date) session.getAttribute("thoiDiemTaoCode");
			long timeOut = (currentTime.getTime() - thoiDiemTaoCode.getTime()) / 1000;
			if (timeOut > 60) {
				session.setAttribute("timeOut", -1);
			}
			else {
				session.setAttribute("timeOut", 59 - timeOut);
			}
		}
		req.setAttribute("sttValidation", 0);
		req.getRequestDispatcher("./register.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("register post");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String daHuyBo = req.getParameter("da-huy-bo");
		String daDongYDangKiLai = req.getParameter("dong-y-dang-nhap-lai");
		String coGuiCode = req.getParameter("sent");
		int thongBaoDangKiLai = session.getAttribute("thongBaoDangKiLai") != null ? 1 : 0;
		if ((daHuyBo != null && daHuyBo.equals("1")) || (daDongYDangKiLai != null && daDongYDangKiLai.equals("1"))) {
			// System.out.println("da huy bo");
			session.invalidate();
			req.getRequestDispatcher("./register.jsp").forward(req, resp);
			return;
		}
		if (thongBaoDangKiLai == 1) {
			req.getRequestDispatcher("./register.jsp").forward(req, resp);
			return;
		}
		if (coGuiCode != null && coGuiCode.equals("1")) {
			// System.out.println("co gui");
			String clientCode = req.getParameter("code");
			String code = (String) session.getAttribute("code");
			Date currentTime = new Date();
			Date thoiDiemTaoCode = (Date) session.getAttribute("thoiDiemTaoCode");
			// System.out.println(thoiDiemTaoCode + " line 70");
			int soLanXacNhan = (int) session.getAttribute("soLanXacNhan");
			soLanXacNhan++;
			session.setAttribute("soLanXacNhan", soLanXacNhan);
			long timeOut = (currentTime.getTime() - thoiDiemTaoCode.getTime()) / 1000;
			if (timeOut > 60) {
				session.setAttribute("timeOut", -1);
			}
			else {
				session.setAttribute("timeOut", 59 - timeOut);
			}
			
			// hết thời gian
			if (timeOut > 60) {
				session.invalidate();
				session = req.getSession();
				session.setAttribute("thongBaoDangKiLai", 1);
				session.setAttribute("coThongBao", 1);
				req.getRequestDispatcher("./register.jsp").forward(req, resp);
				return;
			}
			
			// nhập sai code nhưng số lần <= 3
			if (code.equals(clientCode) == false && soLanXacNhan <= 3) {
				// System.out.println("sai code " + soLanXacNhan);
				req.getRequestDispatcher("./register.jsp").forward(req, resp);
				return;
			}
			
			// nhập sai code nhưng số lần > 3
			if (code.equals(clientCode) == false && soLanXacNhan > 3) {
				// System.out.println(session.getId());
				session.invalidate();
				session = req.getSession();
				// System.out.println(session.getId());
				session.setAttribute("thongBaoDangKiLai", 1);
				session.setAttribute("coThongBao", 1);
				req.getRequestDispatcher("./register.jsp").forward(req, resp);
				return;
			}
			
			// nếu nhập đúng
			if (code.equals(clientCode) == true) {
				// System.out.println("code dung");
				User user = (User) session.getAttribute("user");
				FileItem fileItem = (FileItem) session.getAttribute("fileItem");
				userDao.addUser(user);
				user.setId(userDao.getUserByEmail(user.getEmail()).getId());
				// có tồn tại ảnh upload
				if (fileItem != null) {
					try {
						fileItem.write(new File(super.getServletContext().getRealPath(user.getAnh())));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				session.invalidate();
				session = req.getSession();
				session.setAttribute("currentUser", user);
				session.setAttribute("thongBaoDangKiThanhCong", 1);
				resp.sendRedirect("./home");
				return;
			}
		}
		session.setMaxInactiveInterval(3600 * 24);
		User user = new User();
		user.setAnh("./img_user/fb-no-img.png");
		user.setVaiTro("u");
		String nhapLaiMatKhau = "";
		try {
			// tạo đối tượng để lưu tạm thời file upload lên
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			String pathGoc = super.getServletContext().getRealPath("./img_user");
			
			// Đổi thành đường dẫn tới thư mục, lưu tạm thời file upload len
			diskFileItemFactory.setRepository(new File(pathGoc));
			
			// đọc request từ client gửi lên trong form upload
			ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
			
			// Lấy danh sách các trương input(field, fileItem) từ form client
			List<FileItem> fieldList = servletFileUpload.parseRequest(req);
			// Lấy ra từng input của form client
			for (FileItem item : fieldList) {
				// Kiểm tra xem là text hay là file
				if (item.isFormField()) {
					if (item.getFieldName().equals("ten")) {
						user.setTen(new String(item.getString().getBytes("ISO-8859-1"), "UTF-8"));
					}
					else if (item.getFieldName().equals("email")) {
						user.setEmail(item.getString());
						if (userDao.tonTaiUser(user.getEmail())) {
							req.setAttribute("emailDaTonTai", 1);
							req.getRequestDispatcher("./register.jsp").forward(req, resp);
							return;
						}
					}
					else if (item.getFieldName().equals("mat-khau")) {
						user.setMatKhau(item.getString());
					}
					else if (item.getFieldName().equals("nhap-lai-mat-khau")) {
						nhapLaiMatKhau = item.getString();
					}
				}
				else {
					// tồn tại file upload
					if (item.getSize() > 0) {
						// item.getName() -> lấy ra tên của file upload
						// String fileName = item.getName();
						// String extendFile = fileName.substring(fileName.lastIndexOf("."));
						String anh = "user" + Sha1.encryptThisString(user.getEmail()) + ".jpg";
						user.setAnh("./img_user/" + anh);
						// ghi file vào đường dẫn vật lý của server
						// item.write(new File(pathGoc + File.separator + anh));
						session.setAttribute("fileItem", item);
					}
				}
			}
			
			// validate dữ liệu từ client gửi lên
			int sttValidation = statusValidation(user, nhapLaiMatKhau);
			if (sttValidation != 0) {
				req.setAttribute("sttValidation", sttValidation);
				req.getRequestDispatcher("./register.jsp").forward(req, resp);
				return;
			}
			
			// Lần đầu hiện thông báo xác nhận (lần đầu tiên luôn là phương thức post)
			if (session.getAttribute("thoiDiemTaoCode") == null) {
				// System.out.println("ok if post");
				session.setAttribute("thoiDiemTaoCode", new Date());
				session.setAttribute("timeOut", 59);
				String code = RanDomCode.randomCode();
				// session.setAttribute("userRegister", user);
				session.setAttribute("thongBaoXacNhan", "1");
				session.setAttribute("soLanXacNhan", 0);
				session.setAttribute("code", code);
				session.setAttribute("soLanXacThuc", 0);
			}
			// Từ lần thứ hai trở đi nếu refresh với phương thức post
			else {
				// System.out.println("ok else post");
				Date currentTime = new Date();
				Date thoiDiemTaoCode = (Date) session.getAttribute("thoiDiemTaoCode");
				long timeOut = (currentTime.getTime() - thoiDiemTaoCode.getTime()) / 1000;
				if (timeOut > 60) {
					session.setAttribute("timeOut", -1);
				}
				else {
					session.setAttribute("timeOut", 59 - timeOut);
				}
			}
			session.setAttribute("coThongBao", 1);
			session.setAttribute("user", user);
			// Nội dung
			String noiDung = 
			"<h3>Cảm ơn bạn đã đăng kí tài khoản tại Fruit Shop</h3>"
			+"<div>Mã code xác nhận đăng kí tài khoản: "+session.getAttribute("code")+"</div>"
			+"<div style='color: red'><i>Chú ý: Đây là mail tự động! Vui lòng không reply!</i></div>";
			Email.sendMail(user.getEmail(), noiDung);
			System.out.println("add session success " + session.getAttribute("code") + " " + user.getEmail());
			req.getRequestDispatcher("./register.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
