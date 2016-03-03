package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.handler.CommandHandler;
import mvc.handler.NullHandler;

public class Controller extends HttpServlet {

	private Map<String, CommandHandler> handlerMap = new HashMap<>();

	@Override
	public void init() throws ServletException {
		String configFile = getInitParameter("configFile");
		String configFilePath = getServletContext().getRealPath(configFile);
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(configFilePath));
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServletException();
		}
		// properties에 설정정보를 불러옴.

		Iterator<Object> keySet = properties.keySet().iterator();
		while (keySet.hasNext()) {
			String command = (String) keySet.next();
			String handlerName = properties.getProperty(command);
			try {
				// 클래스 객체 생성
				Class<?> hClass = Class.forName(handlerName);
				CommandHandler handler = (CommandHandler) hClass.newInstance();
				// 맵에 핸들러 삽입
				handlerMap.put(command, handler);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				throw new ServletException();
			}
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getRequestURI();
		if (command.indexOf(request.getContextPath()) == 0)
			command = command.substring(request.getContextPath().length());

		CommandHandler handler = handlerMap.get(command);

		// 커맨드와 일치하는 핸들러가 없으면 NullHandler 생성
		if (handler == null)
			handler = new NullHandler();
		String viewPage = null;
		try {
			viewPage = handler.process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}

		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}

}
