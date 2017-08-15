appModule.controller("summaryReportController", function ($rootScope, $scope, $q, $http, $location, $routeParams,$timeout, $modal)  {
    
   var HEADERS = {
        'Content-Type' : 'application/x-www-form-urlencoded'
    };
    var URL_INIT_REPORT = URLRRCSTANDARDSRV + '?service=ui.pms.PmsReportSvc&part=initReport';
    var URL_GET_STATUS_REPORT = URLRRCSTANDARDSRV + '?service=ui.pms.PmsReportSvc&part=getStatusReport';

    $scope.formData = {
    };
    $scope.tableData = {
    };
    var loadData = {
    };

    $scope.formData.dataDate = null;
    $scope.formData.pmYear = null;
    $scope.formData.pmYear = null;
    $scope.formData.pmPeriod = null;
    $scope.formData.comSeqNo = null;
    $scope.formData.companyList = [];
    $scope.formData.yearList = [];
    $scope.formData.periodList = [];
    /**
     * Initialize date picker
     */
      $scope.initPmListDatePicker = function () {
        $('.input-group.date').datepicker( {
            format : "dd/mm/yyyy", autoclose : true,
            todayHighlight: true,
            todayBtn: true
        });
    };
    
    /**
     * Initialize Report Data Page.
     */
    $scope.initReport = function () {
        var filterData = {
        };

        return $http.get(URL_INIT_REPORT, 
        {
            params : filterData, headers : HEADERS
        }).success(function (response) {
            console.dir(response);
            $scope.formData = response;
            loadData.formData = angular.copy(response);

            $scope.formData.dataDate = response.defaultDataDate;
            $scope.formData.pmYear = response.defaultPMYear;
            $scope.formData.pmYear = response.defaultPMYear;
            $scope.formData.pmPeriod = response.defaultPMPeriod;
            
             $('.input-group.date').datepicker('update', $scope.formData.dataDate);
             
           // $scope.formData.comSeqNo = loadData.formData;

        }).error(function (response) {
            console.error(response);
            $scope.formData = {
            };
        });
    };
    $scope.getStatusReport = function () {
        var filterData = {
            date : $scope.formData.dataDate, pmYear : $scope.formData.pmYear, 
            pmPeriod : $scope.formData.pmPeriod, "comSeqNo" : $scope.formData.comSeqNo
        };

        return $http.get(URL_GET_STATUS_REPORT,
        {
              params : filterData, headers : HEADERS
        }).success(function (response) {
            console.dir(response.data);
            $scope.tableData = response.data;
        }).error(function (response) {
            console.error(response);
            $scope.tableData = {
            };
        });
    };
    /**
     * Refresh Report
     */
     $scope.refreshStatusList = function() {
         displayOverlay();
      var promisesInitData = [];
      promisesInitData.push($scope.getStatusReport());
       $q.all(promisesInitData).then(function(resData){
     
      }).finally(function(){
         removeOverlay();
      });
     };
    /**
     *  Initialize Report Page
     */
    var initLoad = function() {
      displayOverlay();
      var promisesInitPage = [];
      promisesInitPage.push($scope.initReport());
     
      
      $q.all(promisesInitPage).then(function(resData){
       var promisesInitData = [];
       promisesInitData.push($scope.getStatusReport());
       $q.all(promisesInitData).then(function(resData){
      
                  }).
            finally (function (){
          removeOverlay();
      });
      
      }).finally(function(){
        //  removeOverlay();
      });
    };
    
     /**
     *  Search Report button click
     */
    $scope.searchReport = function() {
      displayOverlay();
      var promisesInitData = [];
      promisesInitData.push($scope.getStatusReport());
       $q.all(promisesInitData).then(function(resData){
     
      }).finally(function(){
         removeOverlay();
      });
    };
    
      $scope.initPmListTable = function () {
        $('.pm-report-list-table').footable();
        $("#service_group0").footable().bind( {
            "footable_sorting" : function (e) {
                console.log(e);

                // return confirm("Do you want to sort by column: " + e.column.name + ", direction: " + e.direction);
            }

        });

    };
   /**
    * Calculate Sum of particular column
    */
    $scope.sumTotalColumn = function (columnName) {
    var totalCount = 0;
    for(var item in $scope.tableData) {
        totalCount += parseInt($scope.tableData[item][columnName]);
    }
    return totalCount;
   };

    // Initialize Date Picker
    $scope.initPmListDatePicker();
    // Initilize data table
     $scope.initPmListTable();
    // Init 
    initLoad();
    

});

// Export PDF sample
function demoFromHTML() {
    var pdf = new jsPDF('p', 'pt', 'a4');
    // source can be HTML-formatted string, or a reference
    // to an actual DOM element from which the text will be scraped.
    source = $('#reportTable_Wrapper')[0];

    // we support special element handlers. Register them with jQuery-style 
    // ID selector for either ID or node name. ("#iAmID", "div", "span" etc.)
    // There is no support for any other type of selectors 
    // (class, of compound) at this time.
    specialElementHandlers = {
        // element with id of "bypass" - jQuery style selector
        /*
        '#bypassme': function (element, renderer) {
            // true = "handled elsewhere, bypass text extraction"
            return true
        } */
    };
    var margins = {
        top : 50, bottom : 50, left : 20, width : 500
    };

    // all coords and widths are in jsPDF instance's declared units
    // 'inches' in this case
    pdf.fromHTML(source, // HTML string or DOM elem ref.
    margins.left, // x coord
    margins.top, 
    {
        // y coord
'width' : margins.width, // max width of content on PDF,
'table_2' : true, 'table_2_scaleBasis' : 'font', // 'font' or 'width'
'table_2_fontSize' : 9, 'elementHandlers' : specialElementHandlers
    },
    function (dispose) {
        // dispose: object with X, Y of the last line add to the PDF 
        //          this allow the insertion of new lines after html
        pdf.save('Test.pdf');
    },
    margins);
}