package article.service;

public class DeleteRequest {
	private String id;
	private int articleNumber;
	
	public DeleteRequest(String id, int articleNumber) {
		super();
		this.id = id;
		this.articleNumber = articleNumber;
	}
	public String getId() {
		return id;
	}
	public int getArticleNumber() {
		return articleNumber;
	}
	
}
