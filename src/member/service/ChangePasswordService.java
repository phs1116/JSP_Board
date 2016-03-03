package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class ChangePasswordService {
	private MemberDao memberDao = new MemberDao();

	public void changePassword(String id, String curPwd, String newPwd) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Member member = memberDao.selectById(id, conn);

			if (member == null)
				throw new MemberNotFoundException();
			if (!member.matchPassword(curPwd))
				throw new InvalidPasswordException();

			member.changePassword(newPwd);
			memberDao.update(member, conn);
			conn.commit();

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}

	}
}
