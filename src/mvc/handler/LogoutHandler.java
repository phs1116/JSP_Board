package mvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		//세션이 존재할경우 반환, 존재하지 않으면 null 반환.
		if (session != null)
			session.invalidate();

		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return null;
	}

}
