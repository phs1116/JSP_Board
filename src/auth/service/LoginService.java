package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class LoginService {
	private MemberDao memberDao = new MemberDao();

	public User login(String id, String password) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.selectById(id, conn);

			// 회원 확인
			if (member == null) {
				throw new LoginFailException();
			}

			// 비밀번호 확인
			if (!member.matchPassword(password)) {
				throw new LoginFailException();
			}

			return new User(member.getId(), member.getName());

		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
}
