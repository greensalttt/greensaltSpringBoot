package com.example.greenspringboot.board.paging;

public class PageHandler12 {


    private SearchCondition12 sc;
    private int totalCnt; //전체 게시물의 개수
    private int naviSize = 12; //navi의 크기
    private int totalPage; //전체 페이지의 개수
    private int beginPage; //navi의 시작페이지
    private int endPage; //navi의 끝페이지
    private boolean showPrev;
    private boolean showNext;


    public PageHandler12(int totalCnt, SearchCondition12 sc) {
        this.totalCnt = totalCnt;
        this.sc = sc;

        doPaging(totalCnt, sc);
    }


    //    게시글 갯수에 따라 페이지수 늘어나는 메서드
    public void doPaging(int totalCnt, SearchCondition12 sc){
        this.totalCnt = totalCnt;

        totalPage = (int)Math.ceil(totalCnt / (double)sc.getPageSize());
        beginPage = (sc.getPage()-1) / naviSize * naviSize + 1;
        endPage = Math.min(beginPage + naviSize - 1, totalPage);
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }

    public SearchCondition12 getSc() {
        return sc;
    }

    public void setSc(SearchCondition12 sc) {
        this.sc = sc;
    }
    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }


}