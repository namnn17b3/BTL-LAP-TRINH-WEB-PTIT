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

import fruitshop.model.User;
import fruitshop.service.UserService;
import fruitshop.utils.Email;
import fruitshop.utils.RanDomCode;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static UserService userService = new UserService();
	private static User user = new User();
	private static FileItem fileItem = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("register get");
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
		req.getRequestDispatcher("./register.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("register post");
		HttpSession session = req.getSession();
		String daHuyBo = req.getParameter("da-huy-bo");
		String daDongYDangKiLai = req.getParameter("dong-y-dang-nhap-lai");
		String coGuiCode = req.getParameter("sent");
		if ((daHuyBo != null && daHuyBo.equals("1")) || (daDongYDangKiLai != null && daDongYDangKiLai.equals("1"))) {
			// System.out.println("da huy bo");
			session.invalidate();
			req.getRequestDispatcher("./register.jsp").forward(req, resp);
			return;
		}
		if (coGuiCode != null && coGuiCode.equals("1")) {
			// System.out.println("co gui");
			String clientCode = req.getParameter("code");
			String code = (String) session.getAttribute("code");
			Date currentTime = new Date();
			Date thoiDiemTaoCode = (Date) session.getAttribute("thoiDiemTaoCode");
			// Bắt lỗi 500 error code
			if (thoiDiemTaoCode == null) {
				req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
				return;
			}
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
				req.setAttribute("thongBaoDangKiLai", 1);
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
				session.invalidate();
				req.setAttribute("thongBaoDangKiLai", 1);
				req.getRequestDispatcher("./register.jsp").forward(req, resp);
				return;
			}
			
			// nếu nhập đúng
			if (code.equals(clientCode) == true) {
				// System.out.println("code dung");
				userService.addUser(user);
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
				resp.sendRedirect("./home");
				return;
			}
		}
		session.setMaxInactiveInterval(3600 * 24);
		user.setAnh("./img_user/fb-no-img.png");
		user.setVaiTro("u");
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
						user.setTen(item.getString());
					}
					else if (item.getFieldName().equals("email")) {
						user.setEmail(item.getString());
						if (userService.tonTaiUser(user.getEmail())) {
							req.getRequestDispatcher("./register.jsp").forward(req, resp);
							return;
						}
					}
					else if (item.getFieldName().equals("mat-khau")) {
						user.setMatKhau(item.getString());
					}
				}
				else {
					// tồn tại file upload
					if (item.getSize() > 0) {
						// item.getName() -> lấy ra tên của file upload
						String fileName = item.getName();
						String extendFile = fileName.substring(fileName.lastIndexOf("."));
						String anh = "user" + userService.getNextUserId() + extendFile;
						user.setAnh("./img_user/" + anh);
						// ghi file vào đường dẫn vật lý của server
						// item.write(new File(pathGoc + File.separator + anh));
						fileItem = item;
					}
				}
			}
			// Lần đầu hiện thông báo xác nhận (lần đầu tiên luôn là phương thức post)
			if (session.getAttribute("thoiDiemTaoCode") == null) {
				// System.out.println("ok if post");
				session.setAttribute("thoiDiemTaoCode", new Date());
				session.setAttribute("timeOut", 59);
				String code = RanDomCode.randomCode();
				session.setAttribute("userRegister", user);
				session.setAttribute("thongBaoXacNhan", "1");
				session.setAttribute("soLanXacNhan", 0);
				session.setAttribute("code", code);
				session.setAttribute("fileItem", fileItem);
				session.setAttribute("soLanXacThuc", 0);
			}
			// Từ lần thứ hai trở đi nếu refresh với phương thức post
			else {
				System.out.println("ok else post");
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
			Email.sendMail(user.getEmail(), (String) session.getAttribute("code"));
			// System.out.println("add session success " + session.getAttribute("code"));
			req.getRequestDispatcher("./register.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
