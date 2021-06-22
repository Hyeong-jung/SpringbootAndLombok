package com.springboot.lombok.util;

public class PagingUtil {

	Integer currentPageNum;         // 현재 페이지 번호

	Integer objectCountTotal;       // 전체 글 수
	Integer objectCountPerPage;     // 한 화면에 출력할 오브젝트 수
	Integer objectStartNum;         // 한 화면에 표시되는 오브젝트의 시작
	Integer objectEndNum;           // 한 화면에 표시되는 오브젝트의 마지막

	Integer pageNumCountTotal;      // 전체 페이지 수
	Integer pageNumCountPerPage;    // 한 화면에 출력할 페이지 번호 수
	Integer pageNumStart;           // 한 화면에 출력되는 페이지 번호의 시작
	Integer pageNumEnd;             // 한 화면에 출력되는 페이지 번호의 마지막

	Boolean isPrev;                 // 이전 페이지 표시 여부
	Boolean isNext;                 // 다음 페이지 표시여부

	
	/**
	 * 생성자 1; 
	 * 1) 현재 페이지 번호 : 1, 한 화면에 출력할 오브젝트 수: 10, 한 화면에 출력할 페이지 번호 수 : 10으로 기본설정한다.
	 * 2) setObjectStartAndEnd()를 호출하여 한 화면에 표시되는 오브젝트의 시작과 마지막을 설정한다.
	 * 
	 */
	public PagingUtil() {
		this.currentPageNum = 1;
		this.objectCountPerPage = 10;
		this.pageNumCountPerPage = 10;

		setObjectStartAndEnd();
	}
	
	
	/**
	 * 생성자 2;
	 * 1) '현재 페이지 번호'를 변수로 받는다. 한 화면에 출력할 오브젝트 수: 10, 한 화면에 출력할 페이지 번호 수 : 10으로 기본설정한다.
	 * 2) setObjectStartAndEnd()를 호출하여 한 화면에 표시되는 오브젝트의 시작과 마지막을 설정한다.
	 * 
	 * @param currentPageNum
	 */
	public PagingUtil(int currentPageNum) {
		this.currentPageNum = (0 < currentPageNum) ? currentPageNum : 1 ;
		this.objectCountPerPage = 10;
		this.pageNumCountPerPage = 10;

		setObjectStartAndEnd();
	}
	
	/**
	 * 생성자 3;
	 * 1) '현재 페이지 번호','한 화면에 출력할 오브젝트 수','한 화면에 출력할 페이지 번호 수'를 변수로 받는다.
	 * 2) setObjectStartAndEnd()를 호출하여 한 화면에 표시되는 오브젝트의 시작과 마지막을 설정한다.
	 * 
	 * @param currentPageNum
	 * @param objectCountPerPage
	 * @param pageNumCountPerPage
	 */
	public PagingUtil(int currentPageNum, int objectCountPerPage, int pageNumCountPerPage) {
		this.currentPageNum = (0 < currentPageNum) ? currentPageNum : 1 ;
		this.objectCountPerPage = (0 < objectCountPerPage) ? objectCountPerPage : 10 ;
		this.pageNumCountPerPage = (0 < pageNumCountPerPage) ? pageNumCountPerPage : 10 ;

		setObjectStartAndEnd();
	}

	/**
	 * 한 화면에 표시되는 오브젝트의 시작과 마지막을 설정한다.
	 * 
	 */
	public void setObjectStartAndEnd() {
		this.objectEndNum = currentPageNum * objectCountPerPage;
		this.objectStartNum = (objectEndNum - 1) - (objectCountPerPage - 1);

	}

	public boolean setCalcForPaging(Integer objectCountTotal) {
		if (objectCountTotal == null) {
			return false;
		}
		
		try {
			
			this.objectCountTotal = objectCountTotal;
			this.pageNumCountTotal = (int) Math.ceil((double)objectCountTotal / objectCountPerPage);
			
			int tmpPageNumStart = ((int) Math.ceil(currentPageNum / pageNumCountPerPage) 
					* pageNumCountPerPage);
			int tmpPageNumEnd = 0;
					
			if (tmpPageNumStart == 0) {
				this.pageNumStart = 1;
				tmpPageNumEnd = tmpPageNumStart + pageNumCountPerPage;		
			} else if (tmpPageNumStart == currentPageNum) {
				this.pageNumStart = tmpPageNumStart - (pageNumCountPerPage - 1);
				tmpPageNumEnd = currentPageNum;
			} else {
				this.pageNumStart = tmpPageNumStart + 1;
				tmpPageNumEnd = pageNumStart + pageNumCountPerPage;
			}
			
			
			this.pageNumEnd = (pageNumCountTotal < tmpPageNumEnd) ? pageNumCountTotal : tmpPageNumEnd;
			
			this.isPrev = (currentPageNum > pageNumCountPerPage) ? true : false;
			this.isNext = (pageNumEnd < pageNumCountTotal || (pageNumStart < pageNumEnd && currentPageNum < pageNumCountTotal)  ) ? true : false;
			
			this.objectEndNum = (objectCountTotal < objectEndNum) ? objectCountTotal : objectEndNum;
		    return true;
		    
		} catch (Exception e) {e.printStackTrace(); return false;}
		
	}
	
	
	public boolean setCalcForPaging() {
		return setCalcForPaging(this.objectCountTotal);
	}

	
	// -- Getter & Setter --
	
	public void setObjectCountTotal(Integer objectCountTotal) {
		this.objectCountTotal = objectCountTotal;
		
	}
	
	public void setCurrentPageNum(Integer currentPageNum) {
		this.currentPageNum = (0 < currentPageNum) ? currentPageNum : 1 ;
		setObjectStartAndEnd();
	}

	public Integer getCurrentPageNum() {
		return currentPageNum;
	}

	public Integer getObjectCountTotal() {
		return objectCountTotal;
	}

	public Integer getObjectCountPerPage() {
		return objectCountPerPage;
	}

	public Integer getObjectStartNum() {
		return objectStartNum;
	}

	public Integer getObjectEndNum() {
		return objectEndNum;
	}

	public Integer getPageNumCountTotal() {
		return pageNumCountTotal;
	}

	public Integer getPageNumCountPerPage() {
		return pageNumCountPerPage;
	}

	public Integer getPageNumStart() {
		return pageNumStart;
	}

	public Integer getPageNumEnd() {
		return pageNumEnd;
	}

	public boolean isPrev() {
		return isPrev;
	}

	public boolean isNext() {
		return isNext;
	}
	
	
}
