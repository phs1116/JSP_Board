package mvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticlePage;
import article.service.ListArticleService;

public class ListArticleHandler implements CommandHandler {
	private static final String PAGE_VIEW ="/WEB-INF/view/listArticle.jsp";
	ListArticleService listService = new ListArticleService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNoVal = request.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null)
			pageNo = Integer.parseInt(pageNoVal);
		
		ArticlePage articlePage = listService.getArticlePage(pageNo);
		request.setAttribute("articlePage", articlePage);
		return PAGE_VIEW;
	}
}
