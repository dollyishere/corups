package controller.study;

/**
 * 페이징 네비게이터 함수
 * @since 2024.04.12
 * @author 황인환
 */
public class PageNavigator {
	
	/**
	 * 페이징 네비게이터를 만들어주는 함수
	 * @param totalCount	- 총수
	 * @param listCount		- 노출될 목록 게시물 수
	 * @param pagePerBlock	- 노출될 블록 수
	 * @param pageNum		- 페이지 번호
	 * @param searchType	- 검색 항목
	 * @param searchText	- 검색어
	 * @return
	 */
	public String getPageNavigator(int totalCount, int listCount, int pagePerBlock, int pageNum, 
									String searchType, String searchText) {
		
		StringBuffer sb = new StringBuffer();
		if(totalCount > 0) {
			int totalNumOfPage = (totalCount % listCount == 0) ? 
												totalCount / listCount :
												totalCount / listCount + 1;
			
			int totalNumOfBlock = (totalNumOfPage % pagePerBlock == 0) ?
												totalNumOfPage / pagePerBlock :
												totalNumOfPage / pagePerBlock + 1;
			
			int currentBlock = (pageNum % pagePerBlock == 0) ? 
										pageNum / pagePerBlock :
										pageNum / pagePerBlock + 1;
			
			int startPage = (currentBlock - 1) * pagePerBlock + 1;
			int endPage = startPage + pagePerBlock - 1;
			
			if(endPage > totalNumOfPage){
				endPage = totalNumOfPage;
			}
			
			boolean isNext = false;
			boolean isPrev = false;
			if(currentBlock < totalNumOfBlock){
				isNext = true;
			}
			
			if(currentBlock > 1){
				isPrev = true;
			}
			
			if(totalNumOfBlock == 1){
				isNext = false;
				isPrev = false;
			}
			
			if(pageNum > 1){
				sb.append("<a href=\"").append("boardListServlet?pageNum=1&amp;searchType="+searchType+"&amp;searchText="+searchText);
				sb.append("\" title=\"◀◀\">◀◀</a>&nbsp;");
			}
			
			if (isPrev) {
				int goPrevPage = startPage - pagePerBlock;			
				sb.append("&nbsp;<a href=\"").append("boardListServlet?pageNum="+goPrevPage+"&amp;searchType="+searchType+"&amp;searchText="+searchText);
				sb.append("\" title=\"◁\">◁</a>&nbsp;");
			} else {
				
			}
			
			for (int i = startPage; i <= endPage; i++) {
				if (i == pageNum) {
					sb.append("<a href=\"#\"><strong>").append(i).append("</strong></a>&nbsp;&nbsp;");
				} else {
					sb.append("<a href=\"").append("boardListServlet?pageNum="+i+"&amp;searchType="+searchType+"&amp;searchText="+searchText);
					sb.append("\" title=\""+i+"\">").append(i).append("</a>&nbsp;&nbsp;");
				}
			}
			
			if (isNext) {
				int goNextPage = startPage + pagePerBlock;
	
				sb.append("<a href=\"").append("boardListServlet?pageNum="+goNextPage+"&amp;searchType="+searchType+"&amp;searchText="+searchText);
				sb.append("\" title=\"▷\">▷</a>");
			} else {
				
			}
			
			if(totalNumOfPage > pageNum){
				sb.append("&nbsp;&nbsp;<a href=\"").append("boardListServlet?pageNum="+totalNumOfPage+"&amp;searchType="+searchType+"&amp;searchText="+searchText);
				sb.append("\" title=\"▶▶\">▶▶</a>");
			}
			
		}
		return sb.toString();
	}
}
