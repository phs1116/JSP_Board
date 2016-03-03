package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.dao.ArticleDao;
import article.model.Article;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class ListArticleService {
	ArticleDao articleDao = new ArticleDao();
	private int size = 10;

	public ArticlePage getArticlePage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			int total = articleDao.selectCount(conn);
			//현재 페이지넘버의 첫 게시글부터 size만큼 출력한다.
			//현제 페이지의 첫 게시글 = (페이지-1) * 페이지에 출력하는 게시글 수
			List<Article> content = articleDao.select((pageNum -1)*size,size , conn);
			return new ArticlePage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}
}
