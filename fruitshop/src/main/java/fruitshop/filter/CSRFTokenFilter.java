package fruitshop.filter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebFilter(urlPatterns = {"/profile", "/thanh-toan", "/danh-gia"})
public class CSRFTokenFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		if (req.getMethod().equalsIgnoreCase("post")) {
			String csrfToken = (String) session.getAttribute("csrfToken");
			String csrftTokenFromClient = "";
			if (req.getContentType().startsWith("multipart/form-data") == false) {
				csrftTokenFromClient = req.getParameter("csrf-token");				
			}
			else {
				DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
				String pathGoc = req.getServletContext().getRealPath("./img_user");
				diskFileItemFactory.setRepository(new File(pathGoc));
				ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
				
				try {
					List<FileItem> listFileItem = servletFileUpload.parseRequest(req);
					req.setAttribute("listFileItem", listFileItem);
					for (FileItem item : listFileItem) {
						if (item.isFormField() && item.getFieldName().equals("csrf-token")) {
							csrftTokenFromClient = item.getString();
							break;
						}
					}
				} catch (FileUploadException e) {					
					e.printStackTrace();
				}
			}
			
			if (csrftTokenFromClient.equals(csrfToken)) {
				chain.doFilter(req, resp);
				return;
			}
			else {
				resp.sendError(401, "error authentication!");
				return;
			}
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
