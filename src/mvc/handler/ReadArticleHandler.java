package mvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleContentNotFoundException;
import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.ReadArticleService;

public class ReadArticleHandler implements CommandHandler {
	private static final String READ_VIEW = "/WEB-INF/view/readArticle.jsp";
	private ReadArticleService readService = new ReadArticleService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String noVal = request.getParameter("no");
		int articleNum = Integer.parseInt(noVal);
		try{
		ArticleData articleData = readService.getArticle(articleNum, true);
		request.setAttribute("articleData", articleData);
		return READ_VIEW;
		}catch(ArticleContentNotFoundException | ArticleNotFoundException e){
			request.getServletContext().log("no article", e);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
