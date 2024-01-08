package fruitshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import fruitshop.dao.UserDao;
import fruitshop.dao.impl.UserDaoImpl;
import fruitshop.model.User;
import fruitshop.utils.Sha1;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final UserDao userDao = new UserDaoImpl();
	
	private int statusValidation(String ten, String email, String emailNotChange, String matKhau, String nhapLaiMatKhau) {
		if (ten.matches("^.{1,50}$") == false) {
			return 1;
		}
		if (email.equals(emailNotChange) == false) {
			return 2;
		}
		if (matKhau.matches("^.{8,}$") == false) {
			return 3;
		}
		if (nhapLaiMatKhau.equals(matKhau) == false) {
			return 4;
		}
		return 0;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		req.getRequestDispatcher("./profile_user.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		String pathGoc = req.getServletContext().getRealPath("./img_user");
		
		String ten = "";
		String email = "";
		String matKhau = "";
		String nhapLaiMatKhau = "";
		String coYeuCauXoaAnh = "";
		try {
			List<FileItem> fileItems = (List<FileItem>) req.getAttribute("listFileItem");
			for (FileItem item : fileItems) {
				if (item.isFormField()) {
					if (item.getFieldName().equals("ten")) {
						ten = new String(item.getString().getBytes("ISO-8859-1"), "UTF-8");
					}
					else if (item.getFieldName().equals("email")) {
						email = item.getString();
					}
					else if (item.getFieldName().equals("mat-khau")) {
						matKhau = item.getString();
					}
					else if (item.getFieldName().equals("nhap-lai-mat-khau")) {
						nhapLaiMatKhau = item.getString();
					}
					else if (item.getFieldName().equals("co-yeu-cau-xoa-anh")) {
						// System.out.println("profile controller line 57");
						coYeuCauXoaAnh = item.getString();
					}
				}
				else {
					// có file update
					if (item.getSize() > 0) {
						// String fileName = item.getName();
						// String extendFile = fileName.substring(fileName.lastIndexOf("."));
						String anh = Sha1.encryptThisString("user" + currentUser.getEmail()) + ".jpg";
						File file = new File(pathGoc + File.separator + anh);
						currentUser.setAnh("./img_user/" + anh);
						if (file.exists() == true) {
							file.delete();
						}
						item.write(file);
					}
					// Không tải upload ảnh nhưng mà tên ảnh != ./img_uer/fb-no-img.png
					// -> đã tồn tại ảnh
					// trong csdl và có yêu cầu xóa ảnh -> xóa
					else if (currentUser.getAnh().equals("./img_user/fb-no-img.png") == false && coYeuCauXoaAnh.equals("") == false) {
						// System.out.println("profile controller line 75 " + currentUser.getAnh());
						File file = new File(super.getServletContext().getRealPath(currentUser.getAnh()));
						file.delete();
						currentUser.setAnh("./img_user/fb-no-img.png");
					}
				}
				// System.out.println("profile controller line 82 " + coYeuCauXoaAnh);
			}
			
			// Validate data từ client gửi lên
			int sttValidation = statusValidation(ten, email, currentUser.getEmail(), matKhau, nhapLaiMatKhau);
			if (sttValidation != 0) {
				req.setAttribute("sttValidation", sttValidation);
				req.getRequestDispatcher("./profile_user.jsp").forward(req, resp);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("profile controller line 88 " + currentUser.getAnh());
		currentUser.setTen(ten);
		currentUser.setMatKhau(matKhau);
		userDao.upDateUserByEmail(currentUser);
		session.setAttribute("currentUser", currentUser);
		req.setAttribute("thongBaoCapNhatThanhCong", 1);
		req.getRequestDispatcher("./profile_user.jsp").forward(req, resp);
	}
}
