 /*-----------------------------------------------------------------------------------------------------------  
 RutPage.java
 ------------------------------------------------------------------------------------------------------------- 
 Copyright RCL Public Co., Ltd. 2007 
 -------------------------------------------------------------------------------------------------------------
 Author Piyapong Ieumwananonthachai 01/11/07
 - Change Log ------------------------------------------------------------------------------------------------  
 ## DD/MM/YY -User-     -TaskRef-      -Short Description  
 01 13/07/09 WUT                       Added customization for items per page
 -----------------------------------------------------------------------------------------------------------*/

 package com.rclgroup.dolphin.web.util;

 import java.util.ArrayList;
 import java.util.List;


 public class RutPage {
     public static final String FLAG_YES = "Y";
     public static final String FLAG_NO = "N";
     public static final int VERSION_01 = 1;//ORIGINAL RUTPAGE version 1
     public static final int VERSION_02 = 2;//ORIGINAL RUTPAGE version 2
     
     public static final int MAX_ITEMS_PER_PAGE_DEFAULT = 10;
     public static final int MAX_PAGES_DEFAULT = 10;
     public static final int PAGE_NO_DEFAULT = 1;
     
     // Begin: ##01
     public static final int MAX_ITEMS_PER_PAGE_50 = 50;
     
     // End: ##01
     
     protected int pageNo;
     protected int maxItemsPerPage;
     protected int numOfPages;
     protected int numOnLastPage;
     protected int versionFlag = this.VERSION_01;
     protected int numOfAllData;    
     protected List items;
     
     protected int maxPagesPerPageSet;
     protected int numOfPageSets;
     protected int numOnLastPageSet;
     protected List pageNoItems;
     
     public RutPage(int maxItemsPerPage, int maxPagesPerPageSet, List items,int itemSize, int verstionFlag) {
       init (maxItemsPerPage, maxPagesPerPageSet, items, itemSize, versionFlag);
     }
     
     public RutPage(int maxItemsPerPage, int maxPagesPerPageSet, List items) {
        init(maxItemsPerPage,maxPagesPerPageSet,items,0,this.VERSION_01);
     }
     
     public RutPage() {
         init(this.MAX_ITEMS_PER_PAGE_DEFAULT,this.MAX_PAGES_DEFAULT,new ArrayList(),0,this.VERSION_01);
     }
     
    public RutPage(int maxItemPerPage,int maxPage) {
        init(maxItemPerPage,maxPage,new ArrayList(),0,this.VERSION_01);
    }

    private void init(int maxItemsPerPage, int maxPagesPerPageSet, List items,int itemSize, int verstionFlag) {
        pageNo = PAGE_NO_DEFAULT;
        this.maxItemsPerPage = maxItemsPerPage;
        this.maxPagesPerPageSet = maxPagesPerPageSet;
        this.items = items;
        this.versionFlag = verstionFlag;         
        if(this.versionFlag == this.VERSION_01){
            this.numOfAllData = items.size();            
        }else{
           this.numOfAllData = itemSize;
        }
        calculatePageAndPageSet(numOfAllData,maxItemsPerPage,maxPagesPerPageSet);
    }
     protected void calculatePageAndPageSet(int numOfItems,int maxItemsPerPage,int maxPagesPerPageSet) {
         calculatePage(numOfItems,maxItemsPerPage);
         calculatePageSet(numOfPages,maxPagesPerPageSet);
         
         pageNoItems = new ArrayList();
         for(int i=1;i<= numOfPages;i++){
             pageNoItems.add(new Integer(i));
         }
     }
     
     protected void calculatePage(int numOfItems,int maxItemsPerPage) {
         numOfPages = round(numOfItems,maxItemsPerPage);
         if(numOfItems%maxItemsPerPage == 0){
             numOnLastPage = maxItemsPerPage;
         }else{
             numOnLastPage = numOfItems%maxItemsPerPage;        
         }
     }

     protected void calculatePageSet(int numOfPages,int maxPagesPerPageSet) {
         numOfPageSets = round(numOfPages,maxPagesPerPageSet);
         if(numOfPages%maxPagesPerPageSet == 0){
             numOnLastPageSet = maxPagesPerPageSet;
         }else{
             numOnLastPageSet = numOfPages%maxPagesPerPageSet;        
         }
     }
     
     protected static int round(int a,int b){
         int c = 0;
         if(a%b == 0){
             c = a/b;
         }else{
             c = a/b + 1;
         }
         return c;
     }

     public RutPage(List items) {
         this(MAX_ITEMS_PER_PAGE_DEFAULT,MAX_PAGES_DEFAULT,items);
     }
     
    public RutPage(List items,int itemSize,int verstionFlag) {
        this(MAX_ITEMS_PER_PAGE_DEFAULT,MAX_PAGES_DEFAULT,items,itemSize,verstionFlag);
    }
     
     public void setPageNo(int pageNo) {
         this.pageNo = pageNo;
     }

     public int getPageNo() {
         return pageNo;
     }
     
     public int getMaxItemsPerPage() {
         return maxItemsPerPage;
     }
     
     public int getNumOfPages() {
         return numOfPages;
     }

     protected int getNumOnLastPage() {
         return numOnLastPage;
     }
     
     public void setItems(List items) {
         this.items = items;
     }

     public List getItems() {
         return items;
     }
     
    public List getItemList(int pageNo,List list) {
        try {
            int num = 0;
            if (isLastPage(pageNo)) {
                num = getNumOnLastPage();
            } else {
                num = getMaxItemsPerPage();
            }
            
            if (this.versionFlag == this.VERSION_01) {
                for (int i = 0; i < num; i++) {
                    list.add(items.get((pageNo - 1) * getMaxItemsPerPage() + i));
                }    
            } else {
                int tmpPageNo = pageNo%maxPagesPerPageSet;
                if (tmpPageNo==0) {
                    tmpPageNo = 10;
                }             
                for (int i = 0; i < num; i++) {
                    list.add(items.get((tmpPageNo - 1) * getMaxItemsPerPage() + i));
                }  
            }    
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
     
     protected boolean isLastPage(int pageNo) {
         return (numOfPages == pageNo);
     }
     
     public int getPageSetNo() {
         int pageSetNo = 0;
         while(pageNo > pageSetNo*maxPagesPerPageSet){++pageSetNo;}
         return pageSetNo;
     }
     
     public int getMaxPagesPerPageSet() {
         return maxPagesPerPageSet;
     }
     
     public int getNumOfPageSets() {
         return numOfPageSets;
     }
     
     protected int getNumOnLastPageSet() {
         return numOnLastPageSet;
     }
     
     public List getPageNoList(int pageSetNo,List list) {
         int num = 0;
         
         if (isLastPageSet(pageSetNo))
             num = getNumOnLastPageSet();
         else
             num = getMaxPagesPerPageSet();
         for (int i = 0; i < num; i++) {
             list.add(pageNoItems.get((pageSetNo - 1) * getMaxPagesPerPageSet() + i));
         }
         return list;
     }
     
     public boolean isLastPageSet(int pageSetNo) {
         return (numOfPageSets == 0 || numOfPageSets == pageSetNo);
     }
     
     public Object getItem(int pageNo,int index){
         List listResult = getItemList(pageNo,new ArrayList()); 
         return listResult.get(index); 
     }
     
     public void reCalculatePageAndPageSet() {
         if (items != null) {
            calculatePageAndPageSet(items.size(), getMaxItemsPerPage(), getMaxPagesPerPageSet());
         } else {
            System.err.println("Error reCalculatePageAndPageSet : Cannot re-calculate page and page set.");
         }
     }

    public int getVersionFlag() {
        return versionFlag;
    }

    public void setNumOfAllData(int numOfAllData) {
        this.numOfAllData = numOfAllData;
    }
    
    public int calculateRowAt(){
        int rowAt = 0;        
        rowAt = (getPageSetNo()-1) * maxPagesPerPageSet * maxItemsPerPage + 1;        
        return rowAt;
    }
    
    public int calculateRowTo(){
        int rowTo = 0;
        rowTo = getPageSetNo() * maxPagesPerPageSet * maxItemsPerPage;
        return rowTo;
    }
}

