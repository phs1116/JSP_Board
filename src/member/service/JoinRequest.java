package member.service;

import java.util.Date;
import java.util.Map;

import member.model.Member;

public class JoinRequest {
	private String id;
	private String name;
	private String password;
	private String confirmPassword;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isPasswordEqualToConfirm() {

		return password != null && password.equals(confirmPassword);
	}

	public void validate(Map<String, Boolean> errors) {

		// 각 컬럼들이 비어있는지를 검사
		checkEmpty(errors, id, "id");
		checkEmpty(errors, name, "name");
		checkEmpty(errors, password, "password");
		checkEmpty(errors, confirmPassword, "confirmPassword");

		// 마지막으로 password와 confirmPassword가 ㅇ리치하는지 확인한다.
		if (!errors.containsKey(confirmPassword)) {
			// 일치하지 않으면 에러체크.
			if (!isPasswordEqualToConfirm()) {
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}

	// 비어있는지 체크하는 메서드. 비어있을경우에는 에러 체크 맵인 errors 맵에 에러라는 뜻으로 true를 전달한다.
	public void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if (value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
	
	public Member convertToMember(){
		return new Member(id, name, password, new Date());
	}

}
