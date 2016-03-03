package mvc.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.handler.CommandHandler;

public class NullHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
		rsp.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
	

}
