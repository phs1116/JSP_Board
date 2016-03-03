package mvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleNotFoundException;
import article.service.DeleteArticleService;
import article.service.DeleteRequest;
import article.service.PermissionDeniedException;
import auth.service.User;

public class DeleteArticleHandler implements CommandHandler {
	private static final String SUBMIT_VIEW = "/WEB-INF/view/deleteSuccess.jsp";
	private DeleteArticleService deleteService = new DeleteArticleService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String noVal = request.getParameter("no");
		int no = Integer.parseInt(noVal);
		User authUser = (User) request.getSession().getAttribute("authUser");
		DeleteRequest delReq = new DeleteRequest(authUser.getId(), no);
		try {
			deleteService.delete(delReq);
			return SUBMIT_VIEW;
		} catch (ArticleNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}

	}

}
