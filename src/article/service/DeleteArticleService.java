package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class DeleteArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();

	public void delete(DeleteRequest delReq) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Article article = articleDao.selectById(delReq.getArticleNumber(), conn);
			if (article == null)
				throw new ArticleNotFoundException();
			if (!article.getWriter().getId().equals(delReq.getId()))
				throw new PermissionDeniedException();
			contentDao.delete(delReq.getArticleNumber(), conn);
			articleDao.delete(delReq.getArticleNumber(), conn);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} catch(ArticleNotFoundException|PermissionDeniedException e){
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}

}
