package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class JoinService {
	private MemberDao memberDao = new MemberDao();

	public void join(JoinRequest joinReq) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			// 멤버의 아이디가 있는지를 조사.
			Member member = memberDao.selectById(joinReq.getId(), conn);

			if (member != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}

			// 일치하는 아이디가 없으면.
			member = joinReq.convertToMember();
			memberDao.insert(member, conn);

			conn.commit();

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}

	}

}
