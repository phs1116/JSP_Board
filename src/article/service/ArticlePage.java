package article.service;

import java.util.List;

import article.model.Article;

public class ArticlePage {
	private int total;
	private int currentPage;
	private List<Article> content;
	private int totalPage;
	private int startPage;
	private int endPage;

	public ArticlePage(int total, int currentPage, int size, List<Article> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;

		if (total == 0) {
			totalPage = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPage = total / size;
			if (total % size > 0) {
				totalPage += 1;
			}
			// 1~5페이지, 6~10페이지 순으로 5페이지씩 출력한다.
			startPage = (currentPage / 5) * 5 + 1;
			// 위 로직대로라면 만약 5의 배수인 5페이지일 경우에도 시작페이지가 6이므로 이에 대한 처리.
			if (currentPage % 5 == 0)
				startPage -= 5;
			endPage = startPage + 4;

			// endPage가 전체 페이지를 넘어설 ㄴ경우
			if (endPage > totalPage)
				endPage = totalPage;

		}
	}

	public int getTotal() {
		return total;
	}
	
	public boolean hasNoArticles(){
		return total == 0;
	}
	
	public boolean hasArticles(){
		return total>0;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Article> getContent() {
		return content;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

}
