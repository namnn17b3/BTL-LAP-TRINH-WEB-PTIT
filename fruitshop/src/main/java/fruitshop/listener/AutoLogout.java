package fruitshop.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import fruitshop.dao.UserDao;
import fruitshop.dao.impl.UserDaoImpl;
import fruitshop.model.User;

@WebListener
public class AutoLogout implements HttpSessionListener {
	
	private static final UserDao userDao = new UserDaoImpl();
	
	@Override
    public void sessionCreated(HttpSessionEvent se) {
		return;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    	System.out.println("line 24 auto logout");
        HttpSession session = se.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        try {
	    	currentUser.setTrangThai(0);
	    	userDao.upDateUserByEmail(currentUser);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
