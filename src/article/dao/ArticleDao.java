package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import java.sql.Statement;

import article.model.Article;
import article.model.Writer;
import jdbc.JdbcUtil;

public class ArticleDao {
	public Article insert(Article article, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into article"
					+ "(writer_id,writer_name,title,regdate,moddate,read_cnt)" + "values(?,?,?,?,?,0)");
			pstmt.setString(1, article.getWriter().getId());
			pstmt.setString(2, article.getWriter().getName());
			pstmt.setString(3, article.getTitle());
			pstmt.setTimestamp(4, new Timestamp(article.getRegDate().getTime()));
			pstmt.setTimestamp(5, new Timestamp(article.getModifiedDate().getTime()));
			int insertedCount = pstmt.executeUpdate();

			// 만약 insert가 됬으면.
			if (insertedCount > 0) {
				stmt = conn.createStatement();
				// last_insert_id를 통해 가장 최근에 성공적으로 수행된 insert구문의 PK를 구한다.
				rs = stmt.executeQuery("select last_insert_id() from article");
				if (rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Article(newNum, article.getWriter(), article.getTitle(), article.getRegDate(),
							article.getModifiedDate(), 0);
				}
			}

			// insert 실패했을 경우 null 반환
			return null;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}

	}

	// 게시판의 전체 게시글수를 구하는 쿼리
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from article");
			if (rs.next())
				return rs.getInt(1);

			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<Article> select(int startRow, int size, Connection conn) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from article order by " + "article_no desc limit ?,?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Article> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertArticle(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Article convertArticle(ResultSet rs) throws SQLException {
		return new Article(rs.getInt("article_no"), new Writer(rs.getString("writer_id"), rs.getString("writer_name")),
				rs.getString("title"), rs.getTimestamp("regdate"), rs.getTimestamp("moddate"), rs.getInt("read_cnt"));
	}

	public Article selectById(int no, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("select * from article where article_no = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Article article = null;
			if (rs.next()) {
				article = convertArticle(rs);
			}

			return article;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void increaseReadCount(int no, Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update article set read_cnt = read_cnt+1 " + "where article_no=?")) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}

	public int update(int no, String title, Connection conn) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("update article set title=?,moddate = now() where article_no=?")) {
			pstmt.setString(1, title);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	
	public int delete(int no, Connection conn) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement("DELETE FROM article where article_no=?")){
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}
}
