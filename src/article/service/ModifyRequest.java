package article.service;

import java.util.Map;

public class ModifyRequest {
	private String id;
	private int articleNumber;
	private String title;
	private String content;

	public ModifyRequest(String id, int articleNumber, String title, String content) {
		super();
		this.id = id;
		this.articleNumber = articleNumber;
		this.title = title;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty())
			errors.put("title", Boolean.TRUE);
	}
}
