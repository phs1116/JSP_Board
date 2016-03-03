package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class ModifyArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();

	public void modify(ModifyRequest modReq) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Article article = articleDao.selectById(modReq.getArticleNumber(), conn);
			if (article == null)
				throw new ArticleNotFoundException();
			if (!article.getWriter().getId().equals(modReq.getId()))
				throw new PermissionDeniedException();
			articleDao.update(modReq.getArticleNumber(), modReq.getTitle(), conn);
			contentDao.update(modReq.getArticleNumber(), modReq.getContent(), conn);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} catch (PermissionDeniedException | ArticleNotFoundException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}

	}

}
