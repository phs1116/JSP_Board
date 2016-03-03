package mvc.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import member.service.JoinRequest;
import member.service.JoinService;

public class JoinHandler implements CommandHandler {
	private static final String FORM_VIEW = "WEB-INF/view/joinForm.jsp";
	private static final String SUCC_VIEW = "WEB-INF/view/joinSuccess.jsp";
	private JoinService joinService = new JoinService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")){
			return processForm(request, response);
		}else if(request.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(request, response);
		}else{
			//POST나 GET방식의 요청이 아닐경우 에러 발생
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response){
		return FORM_VIEW;
	}
	

	private String processSubmit(HttpServletRequest request, HttpServletResponse response){
		JoinRequest joinReq = new JoinRequest();
		joinReq.setId(request.getParameter("id"));
		joinReq.setName(request.getParameter("name"));
		joinReq.setPassword(request.getParameter("password"));
		joinReq.setConfirmPassword(request.getParameter("confirmPassword"));
		
		Map<String, Boolean> errors = new HashMap<>();
		
		//폼페이지에서 에러 출력을 위해 request 속성에 추가.
		request.setAttribute("errors", errors);
		
		joinReq.validate(errors);
		
		//에러가 존재할경우 실행중지후 반환.
		if(!errors.isEmpty()){
			return FORM_VIEW;
		}
		
		try{
		joinService.join(joinReq);
		
		return SUCC_VIEW;		
		}catch(DuplicateIdException ex){
			errors.put("duplicatedId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

	
}
