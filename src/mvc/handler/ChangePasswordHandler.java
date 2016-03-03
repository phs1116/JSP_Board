package mvc.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.ChangePasswordService;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;

public class ChangePasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/changePwdForm.jsp";
	private static final String SUBMIT_VIEW = "/WEB-INF/view/changePwdSuccess.jsp";
	private ChangePasswordService chPwdService = new ChangePasswordService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equalsIgnoreCase("GET"))
			return processForm(request, response);
		else if (request.getMethod().equalsIgnoreCase("POST"))
			return processSubmit(request, response);
		else {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = (User) request.getSession().getAttribute("authUser");
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);

		String curPwd = request.getParameter("curPwd");
		String newPwd = request.getParameter("newPwd");

		if (curPwd == null || curPwd.isEmpty())
			errors.put("curPwd", Boolean.TRUE);
		if (newPwd == null || newPwd.isEmpty())
			errors.put("newPwd", Boolean.TRUE);

		if (!errors.isEmpty())
			return FORM_VIEW;

		try {
			chPwdService.changePassword(user.getId(), curPwd, newPwd);
			return SUBMIT_VIEW;
		} catch (InvalidPasswordException ex) {
			errors.put("invalidPwd", Boolean.TRUE);
			return FORM_VIEW;
		} catch (MemberNotFoundException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

	}
}
