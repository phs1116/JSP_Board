package mvc.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Writer;
import article.service.WriteArticleService;
import article.service.WriteRequest;
import auth.service.User;

public class WriteArticleHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/writeArticleForm.jsp";
	private static final String SUBMIT_VIEW = "/WEB-INF/view/writeArticleSuccess.jsp";
	private WriteArticleService writeService = new WriteArticleService();

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

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		User user = (User) request.getSession().getAttribute("authUser");
		WriteRequest writeReq = createWirteRequest(user, request);
		writeReq.validate(errors);
		if (!errors.isEmpty())
			return FORM_VIEW;

		int newArticleNo = writeService.write(writeReq);
		request.setAttribute("newArticleNo", newArticleNo);
		return SUBMIT_VIEW;

	}

	private WriteRequest createWirteRequest(User user, HttpServletRequest request) {
		return new WriteRequest(new Writer(user.getId(), user.getName()), request.getParameter("title"),
				request.getParameter("content"));
	}

}
