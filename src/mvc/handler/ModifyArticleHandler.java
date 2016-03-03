package mvc.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.ModifyArticleService;
import article.service.ModifyRequest;
import article.service.PermissionDeniedException;
import article.service.ReadArticleService;
import auth.service.User;

public class ModifyArticleHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/modifyForm.jsp";
	private static final String SUBMIT_VIEW = "/WEB-INF/view/modifySuccess.jsp";

	private ReadArticleService readService = new ReadArticleService();
	private ModifyArticleService modifyService = new ModifyArticleService();

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

	private String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
		String noVal = request.getParameter("no");
		int no = Integer.parseInt(noVal);
		ArticleData articleData = readService.getArticle(no, false);
		User user = (User) request.getSession().getAttribute("authUser");
		if (!canModify(user, articleData)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}

		ModifyRequest modReq = new ModifyRequest(user.getId(), no, articleData.getArticle().getTitle(),
				articleData.getContent());
		
		request.setAttribute("modReq", modReq);
		return FORM_VIEW;
		}catch(ArticleNotFoundException e){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	private boolean canModify(User authUser, ArticleData articleData) {
		String writeId = articleData.getArticle().getWriter().getId();
		return authUser.getId().equals(writeId);
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User authUser = (User)request.getSession().getAttribute("authUser");
		String noVal = request.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		ModifyRequest modReq = new ModifyRequest(authUser.getId(), no, request.getParameter("title"), 
				request.getParameter("content"));
		
		//에러가 날 경우 화면에 다시 표시해줄 데이터들 저장.
		request.setAttribute("modReq", modReq);
		
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		modReq.validate(errors);
		if(!errors.isEmpty())
			return FORM_VIEW;
		try{
		modifyService.modify(modReq);
		return SUBMIT_VIEW;
		}catch(PermissionDeniedException e){
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}catch(ArticleNotFoundException e){
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
