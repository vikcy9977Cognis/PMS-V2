 
 
 function loadResetSubMystaff(event) {
  
   
  $('#mngId').val($('#mngIdOld').val() );
      // $scope.changeDisplayLengthPm();
      
      
    
     $("#staffsubmit").trigger("click");
 
    // event.preventDefault();
   //event.stopPropagation();
  }
  
 function onPrevMgrNameClick(idx) {
      //      console.log('test');
            var scope = angular.element(document.getElementById('empidClick')).scope();
            if (scope != undefined) {            
            var selectedManager = scope.supervisorLevelStack[idx];
            if (selectedManager != undefined) {
                scope.loadSubMystaff(selectedManager.mngId,
                   selectedManager.empName,null,selectedManager.company,
                    selectedManager.division ,selectedManager.department,
                    selectedManager.section);
            }
            }
            
          
        }
 

appModule.controller("PMListController", function($scope, $http,  State, $q ,$location, $timeout) {
var URLLOADMYPM=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadmypm';
var URLLOADINIT=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadprofile';

var URLLOADACTI=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadactivities';
var URLLOADMYSTAFF=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadmystaff';
var URLLOADCOMPANY=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadcompany';
var URLLOADDIVISION=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loaddivision';
var URLLOADDEPARTMENT=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loaddepartant';
var URLLOADSECTION=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadsection';
if($scope.data ==undefined){
   $scope.data = {};
}
if($scope.tablePM ==undefined){
   $scope.tablePM = {};
}
//$scope.$on('$locationChangeStart', function( event ) {
//    var answer = confirm("Are you sure you want to leave this page?")
//    if (!answer) {
//        event.preventDefault();
//    }
//});
$scope.$on('$destroy', function() {
    if( State.storeData.datas == undefined){
        var tabActive= $('#barMystaff').attr('class') ;   
        var tabdata=1;
        if(tabActive != undefined  && tabActive != ""){
           tabdata=2;
        }
        var startDataLen = parseInt($scope.start, 10) + parseInt($scope.displayLength, 10);
        //var pageNoData = parseInt($scope.data.pageNo, 10)   ;
        
        var formdatas = $('#setDataParamGrid').val();
        var datas=JSON.parse('{"' + decodeURI( (formdatas)).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g,'":"') + '"}');
        var  empidClick =  datas.empidClick.replace(/%26%2332%3B/g  , " ")  ;
        datas['start'] = $scope.start;
        //datas['pageNo'] = pageNoData;
        //datas['displayLength'] = $scope.displayLength;
        datas['tabdata'] =tabdata;
        datas['setDataCompany'] =$('#setDataCompany').val( );
        datas['setDataDivision'] =$('#setDataDivision').val( );
        datas['setDataDepartment'] =$('#setDataDepartment').val( );
        datas['setDataSection'] =$('#setDataSection').val( );
        datas['viewMode'] = $scope.viewMode;
     
        //$scope.initTableMyStaff(datas);
        State.storeData=  { 
         datas : datas,
         empidClick:empidClick
        };
    }
});
 $scope.initTableMyStaff = function(data) {
    displayOverlay();
    if( State.storeData.datas != undefined ){
  
      data=State.storeData.datas;
      
       $scope.changeView (data.viewMode)  ;
       if(State.storeData.datas.tabdata ==2){
         $('.nav-tabs a[data-target="#demo-lft-tab-2"]').tab('show');
       }
        $scope.displayLength=data.displayLength;
        $scope.data.displayLength=data.displayLength;
         $scope.displayLength=data.displayLength;
      
//    
          $scope.$watch('displayLength', function() {
                  //console.log("change");
              });
      //$('#displayLength') .find('option') .remove() .end() ;
//      $("#displayLength option").each(function() {
//         console.log(  $(this)["0"].label);
//          //$(this).remove();
//      });
//      
 $('.displayLengths').each(function(){ // iterate all id's on the page
    var elements_with_specified_id = this.id;
      $(this).find('option') .remove() .end() ;
      //<option value="0" label="10">10</option><option value="1" label="20">20</option><option value="2" label="50" selected="selected">50</option><option value="3" label="100">100</option>
      $(this).append("<option value=\"10\" label=\"10\">10</option>");
      $(this).append("<option value=\"20\" label=\"20\">20</option>");
      $(this).append("<option value=\"50\" label=\"50\">50</option>");
     $(this).append("<option value=\"100\" label=\"100\">100</option>");
     $(this).find('option:selected').removeAttr("selected");
     $(this).find('option').filter(function(index) {
          return $(this).text() == data.displayLength;
      }).attr('selected', 'selected');
      $(this).find('option').filter(function(index) {
          return $(this).text() == data.displayLength;
      }).trigger("click");
        $(this).val(data.displayLength);
  });
//      $("#displayLength").find('option') .remove() .end() ;
//      //<option value="0" label="10">10</option><option value="1" label="20">20</option><option value="2" label="50" selected="selected">50</option><option value="3" label="100">100</option>
//      $("#displayLength").append("<option value=\"10\" label=\"10\">10</option>");
//      $("#displayLength").append("<option value=\"20\" label=\"20\">20</option>");
//      $("#displayLength").append("<option value=\"50\" label=\"50\">50</option>");
//      $("#displayLength").append("<option value=\"100\" label=\"100\">100</option>");
//      $("#displayLength").find('option:selected').removeAttr("selected");
//      $("#displayLength option").filter(function(index) {
//          return $(this).text() == data.displayLength;
//      }).attr('selected', 'selected');
//      $("#displayLength option").filter(function(index) {
//          return $(this).text() == data.displayLength;
//      }).trigger("click");
//        $('#displayLength').val(data.displayLength);
      $('#empidClick').val(State.storeData.empidClick);
       $('#reloadempidClick').val(State.storeData.empidClick);
      
      $('#staffName').val(data.findName) ;
      $('#jobGradeStaff').val(data.jobGrade) ;
      $("#jobGradeStaff").find('option:selected').removeAttr("selected");
      $("#jobGradeStaff option").filter(function(index) {
          return $(this).text() == data.jobGrade;
      }).attr('selected', 'selected');
      $('#jobBrandStaff').val(data.jobBrand) ;
       $("#jobGradeStaff").find('option:selected').removeAttr("selected");
      $("#jobGradeStaff option").filter(function(index) {
          return $(this).text() == data.jobGrade;
      }).attr('selected', 'selected');
      
      
//        datas['setDataCompany'] =$('#setDataCompany').val( );
//        datas['setDataDivision'] =$('#setDataDivision').val( );
//        datas['setDataDepartment'] =$('#setDataDepartment').val( );
//        datas['setDataSection'] =$('#setDataSection').val( );
      $('#setDataCompany').val(data.setDataCompany);
      $('#setDataDivision').val(data.setDataDivision);
      $('#setDataDepartment').val(data.setDataDepartment);
      $('#setDataSection').val(data.setDataSection);
      
     
      $('#designationMystaff').val(data.findDesignation);
       $('#selectedStatusMystaff').val(data.selectedStatus);
      
      
      
       
       if(State.storeData.datas.pmperiod != undefined){
         if(State.storeData.datas.pmperiod == 1){
         setTimeout(function() {
              $('#periodIdMyStaff1H').trigger("click");
        }, 500);
           
         }
         if(State.storeData.datas.pmperiod == 2){
           setTimeout(function() {
             $('#periodIdMyStaff2H').trigger("click");
            }, 500);
             
         }
       }
      
       State.storeData={};
    }
    if (data != undefined) {
        if (data.mngId == undefined || data.mngId == "")
            if ($("#mngIdOld").val() != "") {
                data.mngId = $("#mngIdOld").val();
            }
        if (data.pageNo == undefined || data.pageNo == "") {


            data.pageNo = 1;
        }
        if (data.displayLength == undefined || data.displayLength == "") {
            data.displayLength = $scope.displayLength;
        }

    }
    data.pmperiod = $('#pmperiodMyStaff').val();
    if ($('#startLoadMystaff').val() == "load") {
        data.company = "";
        data.department = "";
        data.division = "";
        data.section = "";

    }
    $('#startLoadMystaff').val("");

    if ($scope.start == undefined) {
        setTimeout(function() {
            $scope.start = 0;
        }, 500);
        setTimeout(function() {
            $scope.$watch('start', function() {
                //console.log("change");
            });
        }, 500);
    }
    if ($scope.pageLength == undefined) {
        setTimeout(function() {
            $scope.pageLength = 0;
        }, 500);
        setTimeout(function() {
            $scope.$watch('pageLength', function() {
                //console.log("change");
            });
        }, 500);
    }
    if ($scope.total == undefined) {
        setTimeout(function() {
            $scope.total = 0;
        }, 500);
        setTimeout(function() {
            $scope.$watch('total', function() {
                //console.log("change");
            });
        }, 500);
    }

    $http({
        method: 'GET',
        url: URLLOADMYSTAFF,
        params: data,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).success(function(datas, status, headers, config) {
        
        var dataForce = $('#forceReloadData').val();
        if (dataForce != "") {
            //
            $('#forceReloadData').val('');
            $q.all([
                $q.when($scope.loadCompanyAsyn()),
                $q.when($scope.loadDivisionAsyn()),
                $q.when($scope.loadDepartmentAsyn()),
                $q.when($scope.loadSectionAsyn())
            ]).then(function(dataLoadQ) {
                    //Array of result [resultOfgetServiceDetails1, resultOfgetServiceDetails2]
                    // $scope.variable = data;
                    $scope.data.pageNo=data.pageNo;
                    $scope.data.size = datas.data.rowTotal;

                    $scope.data.start = datas.data.rowStart;
                    $scope.start = datas.data.rowStart;
                    $scope.total = datas.data.rowTotal;
                    $scope.pageLength = parseInt($scope.data.displayLength, 10) * parseInt($scope.data.pageNo, 10);
                    //$scope.pageLength=    $scope.displayLength ; 
                    if (parseInt($scope.pageLength, 10) > $scope.data.size) {
                        $scope.pageLength = $scope.data.size;
                    }
                    
                    $scope.data.pmstafflist = datas.data.dataTable;
                    setTimeout(function() {
                            // parseInt($scope.pageLength , 10)
                            console.log('data =');
                            console.log($scope.data.pmstafflist);
                            $("#divlistHeaderMystaff").html(' <li><a  href="#/maintenance" onclick="loadResetSubMystaff(this);" ng-click="loadResetSubMystaff($event)">All</a></li>');
                            try {
                                if ($("#setDataCompany").val() == "" || $("#setDataCompany").val() == "-") {
                                    $("#companyMystaff").find('option:selected').removeAttr("selected");
                                    $("#companyMystaff option").filter(function(index) {
                                   
                                        return $(this).val() == "";
                                    }).attr('selected', 'selected');
                                } else {
                                    $("#companyMystaff").find('option:selected').removeAttr("selected");
                                    $("#companyMystaff option").filter(function(index) {
                                          if($(this).text() == $("#setDataCompany").val()){
                                      $("#companyMystaff").val($(this).val());  
                                     }
                                        return $(this).text() == $("#setDataCompany").val();
                                    }).attr('selected', 'selected');
                                }
                              
                                //Division
                                if ($("#setDataDivision").val() == "" || $("#setDataDivision").val() == "-") {
                                    $("#divisionMystaff").find('option:selected').removeAttr("selected");
                                    $("#divisionMystaff option").filter(function(index) {
                                    
                                    
                                        return $(this).val() == "";
                                    }).attr('selected', 'selected');
                                } else {
                                    $("#divisionMystaff").find('option:selected').removeAttr("selected");
                                    $("#divisionMystaff option").filter(function(index) {
                                     if($(this).text() == $("#setDataDivision").val()){
                                      $("#divisionMystaff").val($(this).val());  
                                     }
                                        return $(this).text() == $("#setDataDivision").val();
                                    }).attr('selected', 'selected');
                                }
                                //Department 
                                if ($("#setDataDepartment").val() == "" || $("#setDataDepartment").val() == "-") {
                                    $("#departmentMystaff").find('option:selected').removeAttr("selected");
                                    $("#departmentMystaff option").filter(function(index) {
                                        return $(this).val() == "";
                                    }).attr('selected', 'selected');
                                } else {
                                    $("#departmentMystaff").find('option:selected').removeAttr("selected");
                                    $("#departmentMystaff option").filter(function(index) {
                                      if($(this).text() == $("#setDataDepartment").val()){
                                      $("#departmentMystaff").val($(this).val());  
                                     }
                                        return $(this).text() == $("#setDataDepartment").val();
                                    }).attr('selected', 'selected');
                                }
                                //Section 
                                if ($("#setDataSection").val() == "" || $("#setDataSection").val() == "-") {
                                    $("#sectionMystaff").find('option:selected').removeAttr("selected");
                                    $("#sectionMystaff option").filter(function(index) {
                                        return $(this).val() == "";
                                    }).attr('selected', 'selected');
                                } else {
                                    $("#sectionMystaff").find('option:selected').removeAttr("selected");
                                    $("#sectionMystaff option").filter(function(index) {
                                    if($(this).text() == $("#setDataSection").val()){
                                      $("#sectionMystaff").val($(this).val());  
                                     }
                                        return $(this).text() == $("#setDataSection").val();
                                    }).attr('selected', 'selected');
                                }
                                
                                  $("#divlistHeaderMystaff").append("<li>" + $("#setDataCompany").val() + "</li>");
                                  $("#divlistHeaderMystaff").append("<li>" + $("#setDataDivision").val() + "</li>");
                                  $("#divlistHeaderMystaff").append("<li>" + $("#setDataDepartment").val() + "</li>");
                                  $("#divlistHeaderMystaff").append("<li>" + $("#setDataSection").val() + "</li>");

                                if ($("#empidClick").val() == "") {
                                     if($('#reloadempidClick').val()!=""){
                                       $("#empidClick").val($("#reloadempidClick").val());
                                     }
                                    else{
                                      $("#empidClick").val($("#loadEmpName").val());
                                    }
                                }
                                $("#divlistHeaderMystaff").append("<li class='active'><a href='javascript:void(0)' ng-click='loadSubMystaff("+ $scope.selectedManager.mngId 
                +"," +  $scope.selectedManager.empName + ",$event," +$scope.selectedManager.company + 
                "," + $scope.selectedManager.division + "," + $scope.selectedManager.department
                +"," +$scope.selectedManager.section +")>" + $("#empidClick").val() + "</a></li>");
                                $("#empidClick").val("");
                            } catch (err) {
                                console.log("error");
                            }
                            $scope.listHeaderMystaff = [];
                            removeOverlay();
                            //console.log(data,status, headers, config);
                        }

                    );
                },
                9000);
        } else {
            $scope.data.pageNo=data.pageNo;
            $scope.data.size = datas.data.rowTotal;
            $scope.data.start = datas.data.rowStart;
            $scope.start = datas.data.rowStart;
            $scope.total = datas.data.rowTotal;
            if($scope.data.displayLength== null){
              $scope.data.displayLength=$('#displayLength').val();
            }
            $scope.pageLength = parseInt($scope.data.displayLength, 10) * parseInt($scope.data.pageNo, 10);
            //$scope.pageLength=    $scope.displayLength ; 
            if (parseInt($scope.pageLength, 10) > $scope.data.size) {
                $scope.pageLength = $scope.data.size;
            }
           
            $scope.data.pmstafflist = datas.data.dataTable;
            // parseInt($scope.pageLength , 10)
            console.log('data =');
            console.log($scope.data.pmstafflist);
            $("#divlistHeaderMystaff").html(' <li><a  href="#/maintenance" onclick="loadResetSubMystaff(this);" ng-click="loadResetSubMystaff($event)">All</a></li>');
            if ($("#setDataCompany").val() == "" || $("#setDataCompany").val() == "-") {
                $("#companyMystaff").find('option:selected').removeAttr("selected");
                $("#companyMystaff option").filter(function(index) {
                
                    return $(this).val() == "";
                }).attr('selected', 'selected');
            } else {
                $("#companyMystaff").find('option:selected').removeAttr("selected");
                $("#companyMystaff option").filter(function(index) {
                if($(this).text() == $("#setDataCompany").val()){
                                $("#companyMystaff").val($(this).val());  
                }
                return $(this).text() == $("#setDataCompany").val();
                }).attr('selected', 'selected');
            }
            //Division
            if ($("#setDataDivision").val() == "" || $("#setDataDivision").val() == "-") {
                $("#divisionMystaff").find('option:selected').removeAttr("selected");
                $("#divisionMystaff option").filter(function(index) {
                
                    return $(this).val() == "";
                }).attr('selected', 'selected');
            } else {
                $("#divisionMystaff").find('option:selected').removeAttr("selected");
                $("#divisionMystaff option").filter(function(index) {
                 if($(this).text() == $("#setDataDivision").val()){
                                $("#divisionMystaff").val($(this).val());  
                }
                    return $(this).text() == $("#setDataDivision").val();
                }).attr('selected', 'selected');
            }
           
            //Department 
            if ($("#setDataDepartment").val() == "" || $("#setDataDepartment").val() == "-") {
                $("#departmentMystaff").find('option:selected').removeAttr("selected");
                $("#departmentMystaff option").filter(function(index) {
               
                    return $(this).val() == "";
                }).attr('selected', 'selected');
            } else {
                $("#departmentMystaff").find('option:selected').removeAttr("selected");
                $("#departmentMystaff option").filter(function(index) {
                 if($(this).text() == $("#setDataDepartment").val()){
                                $("#departmentMystaff").val($(this).val());  
                }
                    return $(this).text() == $("#setDataDepartment").val();
                }).attr('selected', 'selected');
            }
            //Section 
            if ($("#setDataSection").val() == "" || $("#setDataSection").val() == "-") {
                $("#sectionMystaff").find('option:selected').removeAttr("selected");
                $("#sectionMystaff option").filter(function(index) {
                
                    return $(this).val() == "";
                }).attr('selected', 'selected');
            } else {
                $("#sectionMystaff").find('option:selected').removeAttr("selected");
                $("#sectionMystaff option").filter(function(index) {
                 if($(this).text() == $("#setDataSection").val()){
                                $("#sectionMystaff").val($(this).val());  
                }
                    return $(this).text() == $("#setDataSection").val();
                }).attr('selected', 'selected');
            }

            $("#divlistHeaderMystaff").append("<li>" + $("#setDataCompany").val() + "</li>");
            $("#divlistHeaderMystaff").append("<li>" + $("#setDataDivision").val() + "</li>");
            $("#divlistHeaderMystaff").append("<li>" + $("#setDataDepartment").val() + "</li>");
            $("#divlistHeaderMystaff").append("<li>" + $("#setDataSection").val() + "</li>");

            if ($("#empidClick").val() == "") {
                $("#empidClick").val($("#loadEmpName").val());
            }
            if ($scope.supervisorLevelStack.length == 0) {
                  $("#divlistHeaderMystaff").append("<li class='active'>" + $("#empidClick").val() + "</li>");
            } else {
               var startIdx = 0;
               if( ( $('#userLevelId').val() == 'ADMIN'|| $('#userLevelId').val() == 'HR' ) ){
                startIdx = 1;
               }
                for (var i=startIdx;i<$scope.supervisorLevelStack.length;i++) {
                    var mgrItem = $scope.supervisorLevelStack[i];
                    if ($("#empidClick").val() == mgrItem.empName){
                          $("#divlistHeaderMystaff").append("<li class='active'><span>" + $("#empidClick").val() + "</span></li>");
                        break;
                    } else {
                        $("#divlistHeaderMystaff").append("<li class='active'><span class='btn-link' onclick='onPrevMgrNameClick("+ i+")'>" + mgrItem.empName + "</span></li>");
                    }
                }    
            }
            /*
            if ($scope.selectedManager.previousMngId != undefined && $scope.selectedManager.previousMngId != ""
            &&  $scope.selectedManager.previousMngId != $scope.selectedManager.mngId ) {
                 $("#divlistHeaderMystaff").append("<li class='active'><span class='btn-link' onclick='onPrevMgrNameClick()'>" + $scope.selectedManager.previousMngName + "</span></li>");
            }
            if ( $scope.selectedManager.mngId  == undefined) {
                 $("#divlistHeaderMystaff").append("<li class='active'>" + $("#empidClick").val() + "</li>");
            } else {
            
            $("#divlistHeaderMystaff").append("<li class='active' ng-click='loadSubMystaff(selectedManager.mngId"+
                ",selectedManager.empName,$event,selectedManager.company" +
                ",selectedManager.division ,selectedManager.department" +
                ",selectedManager.section)'><a href='#/maintenance'>" + $("#empidClick").val() + "</a></li>");
            }
            
             $("#divlistHeaderMystaff").append("<li class='active'><span>" + $("#empidClick").val() + "</span></li>");
            }
            */
            removeOverlay();
        }
    
        $('#loadWithoutSearch').val("");
        $('#mngId').val($('#mngIdOld').val());
       
        data.empidClick = $("#empidClick").val();
         data.empidClick = data.empidClick.replace(/\s+/g, '&#32;');
        $('#setDataParamGrid').val($.param( data ));
         $('#empidClick').val("");
    }).error(function(data, status, headers, config) {
        // handle error things
        removeOverlay();
        console.log(data, status, headers, config);
    });
}
 
 
  $scope.loadSectionAsyn = function () {
     $('#sectionMystaff') .find('option') .remove() .end() ;
   var comSeqno=  $("#companyMystaff").val();
   var divSeqno=  $("#divisionMystaff").val();
   var depSeqno=  $("#departmentMystaff").val();
   
         var dataInput = {
           "comSeqno":comSeqno,
           "divSeqno":divSeqno,
           "depSeqno":depSeqno
         };
 
    return $http.get(URLLOADSECTION, { params: dataInput ,  headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).success(function (response) {
          // $('#companyMystaff').val('');
          console.dir(response);
            $scope.data.listSection =response;
      //$scope.formData.jobBrandWeightageList = response.data;
       return response;
         
    }).error(function (response) {
      console.error(response);
      //$scope.formData.jobBrandWeightageList = [];
    }).finally(function () {
      // $scope.onSearch = false;
      // $rootScope.hideLoadingOverlay('tableLoading');
    });
  };
$scope.loadSection = function() {
  $('#sectionMystaff') .find('option') .remove() .end() ;
   var comSeqno=  $("#companyMystaff").val();
   var divSeqno=  $("#divisionMystaff").val();
   var depSeqno=  $("#departmentMystaff").val();
   
         var dataInput = {
           "comSeqno":comSeqno,
           "divSeqno":divSeqno,
           "depSeqno":depSeqno
         };
      
        $http({
            method: 'GET',
            url: URLLOADSECTION,
            params: dataInput ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (datas, status, headers, config) {
        
            console.log(datas,status, headers, config);
             
            $scope.data.listSection =datas;
 
        }).error(function (datas, status, headers, config) {
            // handle error things
            console.log(datas,status, headers, config);
        });
    };
    
     $scope.loadDepartmentAsyn = function () {
     $('#departmentMystaff') .find('option') .remove()   ;
   var comSeqno=  $("#companyMystaff").val();
   var divSeqno=  $("#divisionMystaff").val();
   
         var dataInput = {
           "comSeqno":comSeqno,
           "divSeqno":divSeqno
         };
 
    return $http.get(URLLOADDEPARTMENT, { params: dataInput ,  headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).success(function (response) {
           //$('#companyMystaff').val('');
          console.dir(response);
            $scope.data.listDepartment =response;
      //$scope.formData.jobBrandWeightageList = response.data;
       return response;
         
    }).error(function (response) {
      console.error(response);
      //$scope.formData.jobBrandWeightageList = [];
    }).finally(function () {
      // $scope.onSearch = false;
      // $rootScope.hideLoadingOverlay('tableLoading');
    });
  };
    
    
    
  $scope.loadDepartment = function() {
 
  $('#departmentMystaff') .find('option') .remove()   ;
   var comSeqno=  $("#companyMystaff").val();
   var divSeqno=  $("#divisionMystaff").val();
   
         var dataInput = {
           "comSeqno":comSeqno,
           "divSeqno":divSeqno
         };
      
        $http({
            method: 'GET',
            url: URLLOADDEPARTMENT,
            params: dataInput ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (datas, status, headers, config) {
        
            console.log(datas,status, headers, config);
             
            $scope.data.listDepartment =datas;
 
        }).error(function (datas, status, headers, config) {
            // handle error things
            console.log(datas,status, headers, config);
        });
    };
     $scope.loadDivisionAsyn = function () {
     $('#divisionMystaff') .find('option') .remove() ;
     var comSeqno=  $("#companyMystaff").val();
   
         var dataInput = {
           "comSeqno":comSeqno
         };
 
    return $http.get(URLLOADDIVISION, { params: dataInput ,  headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).success(function (response) {
           //$('#companyMystaff').val('');
          console.dir(response);
            $scope.data.listDivision =response;
      //$scope.formData.jobBrandWeightageList = response.data;
       return response;
         
    }).error(function (response) {
      console.error(response);
      //$scope.formData.jobBrandWeightageList = [];
    }).finally(function () {
      // $scope.onSearch = false;
      // $rootScope.hideLoadingOverlay('tableLoading');
    });
  };
   $scope.loadDivision = function() {
//   var valueData= $("#companyMystaff").val();
//   for(var item  in $scope.data.listCompany.data){
//     if(item.des2  == valueData ){
//      
//     }
//   }

 $('#divisionMystaff') .find('option') .remove() ;

  var comSeqno=  $("#companyMystaff").val();
   
         var dataInput = {
           "comSeqno":comSeqno
         };
      
        $http({
            method: 'GET',
            url: URLLOADDIVISION,
            params: dataInput ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (datas, status, headers, config) {
        
            console.log(datas,status, headers, config);
            console.log("up");
            $scope.data.listDivision =datas;
            // handle success things
            
//              $scope.data.pmlist =  datas.data.dataTable;
//               
//              $scope.data.sizePm = datas.data.rowTotal;
//
//                $scope.data.startPm=   datas.data.rowStart  ;      
//               $scope.tablePM.start=   datas.data.rowStart   ; 
//               $scope.tablePM.total=   datas.data.rowTotal   ; 
//               $scope.tablePM.pageLength=    $scope.displayLengthPm;    
//               
//             
//                $scope.tablePM.pageLength  = parseInt( $scope.data.displayLengthPm , 10) * parseInt($scope.data.pageNoPm , 10);
//               //$scope.pageLength=    $scope.displayLength ; 
//               if( parseInt( $scope.pageLengthPm , 10)  > $scope.data.sizePm){
//                  $scope.pageLengthPm =$scope.data.sizePm;
//               }
//                
//               
//            console.log(data,status, headers, config);
//            console.log("up");
//            $scope.initLastActivityList();
        }).error(function (datas, status, headers, config) {
            // handle error things
            console.log(datas,status, headers, config);
        });
    };
   
    
     

   
//   $scope.company= {selected: "All"};
//   $scope.department={selected: "All"};
//    $scope.division={selected: "All"};
//    $scope.section={selected: "All"};
              
//              $scope.data.listDivision={};
//     $scope.$watch('data.listDivision', function() {
//                  //console.log("change");
//              });
 $scope.loadCompanyAsyn = function () {
  $('#companyMystaff') .find('option') .remove() ;
    return $http.get(URLLOADCOMPANY, {  headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).success(function (response) {
           //$('#companyMystaff').val('');
          console.dir(response.data);
          $scope.data.listCompany =response;
      //$scope.formData.jobBrandWeightageList = response.data;
       return response;
         
    }).error(function (response) {
      console.error(response);
      //$scope.formData.jobBrandWeightageList = [];
    }).finally(function () {
      // $scope.onSearch = false;
      // $rootScope.hideLoadingOverlay('tableLoading');
    });
  };
    $scope.loadCompany = function() {
     
     
       
        $http({
            method: 'GET',
            url: URLLOADCOMPANY,
            // params: data ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (datas, status, headers, config) {
        
            console.log(datas,status, headers, config);
            console.log("up");
            
             $scope.data.listCompany =datas;
              //companyMystaff
              $('#companyMystaff').val('');
            // companyMystaff
            // handle success things
            
//              $scope.data.pmlist =  datas.data.dataTable;
//               
//              $scope.data.sizePm = datas.data.rowTotal;
//
//                $scope.data.startPm=   datas.data.rowStart  ;      
//               $scope.tablePM.start=   datas.data.rowStart   ; 
//               $scope.tablePM.total=   datas.data.rowTotal   ; 
//               $scope.tablePM.pageLength=    $scope.displayLengthPm;    
//               
//             
//                $scope.tablePM.pageLength  = parseInt( $scope.data.displayLengthPm , 10) * parseInt($scope.data.pageNoPm , 10);
//               //$scope.pageLength=    $scope.displayLength ; 
//               if( parseInt( $scope.pageLengthPm , 10)  > $scope.data.sizePm){
//                  $scope.pageLengthPm =$scope.data.sizePm;
//               }
//                
//               
//            console.log(data,status, headers, config);
//            console.log("up");
//            $scope.initLastActivityList();
        }).error(function (datas, status, headers, config) {
            // handle error things
            console.log(datas,status, headers, config);
        });
    };
    
   $scope.reloadMyPm = function() {
    var dataYear=$('#selectedYearMyPm').val();
      var formData = {
         "pmperiod":$('#pmperiodData').val(),
         selectedYear:dataYear
       };
       $scope.initMyPm(formData);
    };
 //
//  $scope.initLoad = function( ) {
////                  String sortBy =
////                    RutString.nullToStr((String)request.getParameter("cmbSortBy"));
////                String sortDirection =
////                    RutString.nullToStr((String)request.getParameter("cmbSortDirection"));
////                 
//       if($scope.formData == undefined ){
//          $scope.formData = {
//           
//         };
//       }
//        $scope.list=[]; 
//        $scope.dataYear = {selected: "All"};
//        $http({
//            method: 'GET',
//            url: URLLOADMYPM,
//            data: $scope.formData ,
//            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
//        }).success(function (data, status, headers, config) {
//            // handle success things
//            console.log(" load pm data");
//          
//            console.log(data,status, headers, config);
//        }).error(function (data, status, headers, config) {
//            // handle error things
//            console.log(data,status, headers, config);
//        });
//   }

  $scope.reloadOption = function(  ) {
                    $scope.loadCompany();
                    $scope.loadDivision ();
                    $scope.loadDepartment();
                    $scope.loadSection();
         } ;           
   //$scope.reloadOption();
    $scope.data.pmlist = [];

    $scope.$watch('data.pmlist', function() {
        //console.log("change");
    });
    $scope.data.lastActivityList = [];
    $scope.$watch('data.lastActivityList', function() {
        //console.log("change");
    });
     if($scope.tablePM.start == undefined ){
             $scope.tablePM.start=0;
             $scope.$watch('tablePM.start', function() {
                  //console.log("change");
              });
           }
            if($scope.tablePM.pageLength == undefined ){
             $scope.tablePM.pageLength=0;
             $scope.$watch('tablePM.pageLength', function() {
                  //console.log("change");
              });
           }
           if($scope.tablePM.pageLength == undefined ){
             $scope.tablePM.pageLength=0;
             $scope.$watch('tablePM.pageLength', function() {
                  //console.log("change");
              });
           }
  
      $scope.initLastActivityList = function(  ) {
        $http({
            method: 'GET',
            url: URLLOADACTI,
             //params: data ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (datas, status, headers, config) {
            // handle success things
              $scope.data.lastActivityList =  datas.data;
            console.log(datas,status, headers, config);
        }).error(function (data, status, headers, config) {
            // handle error things
            console.log(data,status, headers, config);
        });
   }
  
   
    $scope.initTableMyPm = function( data) {
//                  String sortBy =
//                    RutString.nullToStr((String)request.getParameter("cmbSortBy"));
//                String sortDirection =
//                    RutString.nullToStr((String)request.getParameter("cmbSortDirection"));
//                 
               
           //init listYear
           if($scope.tablePM.start == undefined ){
            
             setTimeout(function(){
                 $scope.tablePM.start=0;
              }, 500);
             
              setTimeout(function(){
                $scope.$watch('tablePM.start', function() {
                    //console.log("change");
                });

              }, 500); 
           }
           if($scope.tablePM.pageLength == undefined ){
            
            setTimeout(function(){
                   $scope.tablePM.pageLength=0;
              }, 500); 
              setTimeout(function(){
                 $scope.$watch('tablePM.pageLength', function() {
                  //console.log("change");
              }); 
              }, 500);  
            
           }
              if($scope.tablePM.total == undefined ){
            
            setTimeout(function(){
                  $scope.tablePM.total=0;
              }, 500); 
              setTimeout(function(){
                $scope.$watch('tablePM.total', function() {
                  //console.log("change");
              });
              }, 500);  
            
           }
           
         
 
  
 
    
        $http({
            method: 'GET',
            url: URLLOADMYPM,
             params: data ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (datas, status, headers, config) {
            // handle success things
              $scope.data.pmlist =  datas.data.dataTable;
               
              $scope.data.sizePm = datas.data.rowTotal;
                
                $scope.data.startPm=   datas.data.rowStart  ;      
               $scope.tablePM.start=   datas.data.rowStart   ; 
               $scope.tablePM.total=   datas.data.rowTotal   ; 
               $scope.tablePM.pageLength=    $scope.displayLengthPm;    
               
             
                $scope.tablePM.pageLength  = parseInt( $scope.data.displayLengthPm , 10) * parseInt($scope.data.pageNoPm , 10);
               //$scope.pageLength=    $scope.displayLength ; 
               if( parseInt( $scope.tablePM.pageLength , 10)  > $scope.data.sizePm){
                  $scope.tablePM.pageLength =$scope.data.sizePm;
               }
            //     $scope.loadSubMystaff
               //$('#mngId').val(dataInput);
              // $scope.changeDisplayLengthPm();
              var tmpData=$scope.data.pmlist ["0"].empname;
               $('#empidClick').val(tmpData);
      //$('#forceReloadData').val("true");
      if($('#pualStart').val()!=""){
        $('#pualStart').val('');
      }
        if( ( $('#userLevelId').val() == 'ADMIN'|| $('#userLevelId').val() == 'HR' ) ){
           $('#mngId').val('');
            $('#loadDivision').val( "" );
            $('#loadCompany').val( "" );
            $('#loadDepartment').val( "" );
            $('#loadSection').val( "" );
         

            $scope.loadSubMystaff("",tmpData,"All","All",  "All" ,"All","All");
      
        }
      else{
      //function (mngId,empName,event,company,division,department,section) {
    
            $('#loadDivision').val( $scope.data.pmlist ["0"].divname );
            $('#loadCompany').val( $scope.data.pmlist ["0"].comname );
            $('#loadDepartment').val( $scope.data.pmlist ["0"].depname );
            $('#loadSection').val( $scope.data.pmlist ["0"].secname );
      
         $scope.loadSubMystaff($scope.data.pmlist ["0"].fkempseqno,tmpData,"",$scope.data.pmlist ["0"].comname,  $scope.data.pmlist ["0"].divname ,$scope.data.pmlist ["0"].depname,$scope.data.pmlist ["0"].secname);
      }
      $('#loadEmpName').val( $scope.data.pmlist ["0"].empname);
      
    
              
             //  $("#staffsubmit").trigger("click");
             console.log(data,status, headers, config);
            console.log("up");
            $scope.initLastActivityList();
        }).error(function (datas, status, headers, config) {
            // handle error things
            console.log(datas,status, headers, config);
        });
   }

 
//  // View mode
$scope.viewMode = "status";
//  // Year Filter
 $scope.selectedYear = "2017";
 
//  // Period Filter
// $scope.selectedPeriod = "";
//
//// Search criteria
// $scope.searchInputText = "";
//
//$scope.individualName = "Khomsun";
//
 $scope.displayLengthPm = 20;
//    
$scope.$watch('displayLengthPm', function() {
                  //console.log("change");
              });
 $scope.displayLenghtListPm= [10, 20, 50, 100];
//    
//  // Status list
  $scope.data.sortByPm= "";
  $scope.data.sortDirectionPm="";
  $scope.data.displayLengthPm="20";
  $scope.data.pageNoPm="1";
  $scope.data.startPm="1";                           
  $scope.data.sizePm="";                           
              
  
  $scope.loadResetSubMystaff = function (event) {
  
   
 // $('#mngId').val($('#mngIdOld').val() );
      // $scope.changeDisplayLengthPm();
      
      $timeout(function () {
      $scope.listHeaderMystaff=[];
      $scope.supervisorLevelStack = [];
     $("#staffsubmit").trigger("click");
}, 300);
     event.preventDefault();
    event.stopPropagation();
  }
  $scope.loadChangeTabSubMystaff = function (mngId,empName,event,company,division,department,section,cc) {
  $('.nav-tabs a[data-target="#demo-lft-tab-2"]').tab('show');
   $scope.supervisorLevelStack = [];
  setTimeout(function() {
             $("#staffReset").trigger("click");
    $("#staffsubmit").trigger("click");
            }, 100);
    
  //$scope.loadSubMystaff(mngId,empName,event,company,division,department,section);
  }
 $scope.supervisorLevelStack = [];
 
$scope.loadSubMystaff = function (mngId,empName,event,company,division,department,section) {
           
       if (  $scope.supervisorLevelStack.length == 0) {
       var topLevelManager = {};
          topLevelManager.mngId = $('#mngId').val();      
          topLevelManager.empName = $("#loadEmpName").val()      
          topLevelManager.company = $('#loadCompany').val();      
          topLevelManager.division = $('#loadDivision').val();      
          topLevelManager.department = $('#loadDepartment').val();      
          topLevelManager.section = $('#loadSection').val();
          $scope.supervisorLevelStack.push(topLevelManager);
      }
      
      if ( $scope.supervisorLevelStack.length > 0){
        // Check pop the item above
        var foundIdx = -1;
        for (var i=0;i<$scope.supervisorLevelStack.length;i++) {
          var mgrItem = $scope.supervisorLevelStack[i];
          if (mgrItem.mngId == mngId){
              foundIdx = i;
            break;
          }
        }
        if (foundIdx > -1 && $scope.supervisorLevelStack.length > 1) {
          // Case Existing
          var arrLen = $scope.supervisorLevelStack.length;
            for (var i=foundIdx+1;i<arrLen;i++) {
                console.log("Remove :" + i);
                $scope.supervisorLevelStack.splice((foundIdx+1), 1);
            }
        } else {        
          // Case Not Existing
              var selectedManager = {};
              selectedManager.mngId = mngId;
               selectedManager.empName = empName;
               selectedManager.company = company;
              selectedManager.division = division;
               selectedManager.department = department;
               selectedManager.section = section;
               $scope.supervisorLevelStack.push(selectedManager);
        }        
      }
      console.log(" ** Supervisor Level List");
      for (var i=$scope.supervisorLevelStack.length-1;i>=0;i--) {
        var selectedManager = $scope.supervisorLevelStack[i]; 
        console.log("Level:"  +i);   
        console.log("mngId: " +selectedManager.mngId);
        console.log("empName: " +selectedManager.empName );
        console.log("company: " +selectedManager.company );
        console.log("division: " +selectedManager.division );
        console.log("department: " +selectedManager.department );
        console.log("section: " +selectedManager.section);
    }
    
//     var formdatas =  $("#formStaff").serializeArray();
//        var datas = {};Name
//        $(formdatas ).each(function(indexs, objs){
//            datas[objs.name] = objs.value;
//        });
//       
//      var json1 = datas;
//       json1["mngId"]=dataInput;
//       //json1["selectedYear"]="1";
//       json1["pageNo"]=$scope.data.pageNo;
//       json1["displayLength"]=$scope.displayLength;
//      $scope.initTableMyStaff(json1);

//$('#setDataEmpName').val(dataInput);
//      <input type="hidden" id="setDataEmpName" value='-1' />
//  <input type="hidden" id="setDataDivision" value='-1' />
//  <input type="hidden" id="setDataCompany"  value='-1' />
//  <input type="hidden" id="setDataDepartment"  value='-1' />
//  <input type="hidden" id="setDataSection"  value='-1' />
//  <input type="hidden" id="setDataStaffYeart"  value='-1' />
//  <input type="hidden" id="setDataStaffPeriod"  value='-1' />
 
     $('#setDataCompany').val(company);
     $('#setDataDivision').val(division);
     $('#setDataDepartment').val(department);
      $('#setDataSection').val(section);
    
   
     $('#mngId').val(mngId);
     // $scope.changeDisplayLengthPm();
      $('#empidClick').val(empName);
       $('#loadWithoutSearch').val("1");
     
     $timeout(function () {
    $("#staffsubmit").trigger("click");
}, 300);
     //document.getElementById('staffsubmit').click();
      // angular.element('#staffsubmit').triggerHandler('click');
   //event.preventDefault();
   //event.stopPropagation();

};
   
$scope.changeDisplayLengthPm= function () {
  $scope.data.displayLengthPm=$scope.displayLengthPm;
  $scope.data.pageNoPm=1;
  var dataYear=$('#selectedYearMyPm').val();
  $scope.formData = {
     "pmperiod":$('#pmperiodData').val(),
     "selectedYear":dataYear,
      pageNo:$scope.data.pageNoPm,
     "displayLength":$scope.displayLengthPm
   };
    $scope.initTableMyPm($scope.formData);
    //alert($scope.displayLengthPM );
 };
 
 //backPageMyStaff
 $scope.nextPageMyStaff = function() {
    var startDataLen = parseInt($scope.start, 10) + parseInt($scope.displayLength, 10);
    if (startDataLen <= parseInt($scope.total, 10)) {
        var pageNoData = parseInt($scope.data.pageNo, 10) + 1;
        $scope.data.pageNo = parseInt($scope.data.pageNo, 10) + 1;
        //$('#setDataParamGrid').val();
        var formdatas = $('#setDataParamGrid').val();
        var datas=JSON.parse('{"' + decodeURI( (formdatas)).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g,'":"') + '"}');
        
 
        $('#empidClick').val( datas.empidClick.replace(/%26%2332%3B/g  , " ") );
        datas['pageNo'] = pageNoData;
        datas['displayLength'] = $scope.displayLength;
        $scope.initTableMyStaff(datas);
    }
};

$scope.backPageMyStaff = function() {
     var startDataLen = parseInt($scope.start, 10);
     if (startDataLen != 1) {
         var pageNoData = parseInt($scope.data.pageNo, 10) - 1;
         $scope.data.pageNo = parseInt($scope.data.pageNo, 10) - 1;

         var formdatas = $('#setDataParamGrid').val();
         var datas=JSON.parse('{"' + decodeURI( (formdatas) ).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g,'":"') + '"}');
          $('#empidClick').val( datas.empidClick.replace(/%26%2332%3B/g , " ") );
         datas['pageNo'] = pageNoData;
         datas['displayLength'] = $scope.displayLength;

         $scope.initTableMyStaff(datas);
     }
 };
 
 $scope.nextPagePm = function() {
 
//   $scope.data.startPm=   datas.data.rowStart  ;      
//               $scope.tablePM.start
               //{{tablePM.start}}-{{tablePM.pageLength}}
               var startDataLen=parseInt($scope.tablePM.start, 10) +  parseInt($scope.tablePM.pageLength, 10) ;
              if(startDataLen < parseInt($scope.tablePM.total, 10)){
               var pageNoData=  parseInt( $scope.data.pageNoPm, 10)+1;
                 $scope.data.pageNoPm = parseInt( $scope.data.pageNoPm, 10)+1;
             
                            $scope.formData = {
                             "pmperiod":"1",
                             "selectedYear":"",
                              pageNo:pageNoData,
                             "displayLength":$scope.displayLengthPm
                           };
                            $scope.initTableMyPm($scope.formData);
              }
               
    
   };
    $scope.backPagePm = function() {
 
//   $scope.data.startPm=   datas.data.rowStart  ;      
//               $scope.tablePM.start
               //{{tablePM.start}}-{{tablePM.pageLength}}
               var startDataLen=parseInt(tablePM.start, 10) ;
              if(startDataLen != 1){
               var pageNoData=  parseInt( $scope.data.pageNoPm, 10)-1;
             $scope.data.pageNoPm = parseInt( $scope.data.pageNoPm, 10)-1;
                            $scope.formData = {
                             "pmperiod":"1",
                             "selectedYear":"",
                              pageNo:pageNoData,
                             "displayLength":$scope.displayLengthPm
                           };
                            $scope.initTableMyPm($scope.formData);
              }
               
    
   };
 
  $scope.data.pmStatusList = [
    {"status": "N", "description": "New"},
    {"status": "I", "description": "In Progress"},
    {"status": "W", "description": "Waitlisted 1"},
    {"status": "V", "description": "Waitlisted 2"},
    {"status": "C", "description": "Completed"}
  ];
//  // Simulate lastActivityList
  
$scope.data.lastActivityList = [ ];

 $scope.displayLength = 20;
//    
$scope.$watch('displayLength', function() {
                  //console.log("change");
              });
 $scope.displayLenghtList= [10, 20, 50, 100];
//    
//  // Status list
  $scope.data.sortBy= "";
  $scope.data.sortDirection="";
  $scope.data.displayLength="20";
  $scope.data.pageNo="1";
  $scope.data.start="1";                           
  $scope.data.size="";                           
              
    if($scope.start == undefined ){
            
             setTimeout(function(){
                 $scope.start=0;
              }, 500);
             
              setTimeout(function(){
                $scope.$watch('start', function() {
                    //console.log("change");
                });

              }, 500); 
           }
           if($scope.pageLength == undefined ){
            
            setTimeout(function(){
                   $scope.pageLength=0;
              }, 500); 
              setTimeout(function(){
                 $scope.$watch('pageLength', function() {
                  //console.log("change");
              }); 
              }, 500);  
            
           }
              if($scope.total == undefined ){
            
            setTimeout(function(){
                  $scope.total=0;
              }, 500); 
              setTimeout(function(){
                $scope.$watch('total', function() {
                  //console.log("change");
              });
              }, 500);  
            
           }
 
   $scope.changeDisplayLengthT= function (val) {
      var me=$('.displayLengths')[1] ;
      var datas= $(me).find('option:selected').text();
      var notme=$('.displayLengths')[0] ;
      $(notme).find('option:selected').removeAttr("selected");
//     $(me).find('option:selected').removeAttr("selected");
      $(notme).find('option').filter(function(index) {
          return $(this).text() ==  datas;
      }).attr('selected', 'selected');
       var pageNoData = 1;
        $scope.data.pageNo = 1;
        if($scope.displayLength==undefined){
          $scope.displayLength=datas; 
        }
        $scope.data.displayLength=$scope.displayLength;
        var formdatas = $('#setDataParamGrid').val();
        var datas=JSON.parse('{"' + decodeURI( (formdatas)).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g,'":"') + '"}');
         $('#empidClick').val( datas.empidClick.replace(/%26%2332%3B/g , " ") );
        datas['pageNo'] = pageNoData;
        datas['displayLength'] = $scope.displayLength;

     
        $scope.initTableMyStaff(datas);
     //$scope.changeDisplayLength();
   };
$scope.changeDisplayLength= function (val) {

//alert();
      var me=$('.displayLengths')[0] ;
      var datas= $(me).find('option:selected').text();
      var notme=$('.displayLengths')[1] ;
      $(notme).find('option:selected').removeAttr("selected");
//     $(me).find('option:selected').removeAttr("selected");
      $(notme).find('option').filter(function(index) {
          return $(this).text() ==  datas;
      }).attr('selected', 'selected');
     //$('.displayLengths')[1];
    
//   $scope.displayLength=val;
//   $scope.changeDisplayLength();
   
        var pageNoData = 1;
        $scope.data.pageNo = 1;
        if($scope.displayLength==undefined){
          $scope.displayLength=$('#displayLength').val(); 
        }
        $scope.data.displayLength=$scope.displayLength;
        var formdatas = $('#setDataParamGrid').val();
        var datas=JSON.parse('{"' + decodeURI( (formdatas)).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g,'":"') + '"}');
         $('#empidClick').val( datas.empidClick.replace(/%26%2332%3B/g , " ") );
        datas['pageNo'] = pageNoData;
        datas['displayLength'] = $scope.displayLength;

     
        $scope.initTableMyStaff(datas);
    //alert($scope.displayLengthPM );
 };

// $scope.changeDisplayLength = function() {
//   //    console.log("displayLength:" + $scope.displayLengthPM);
//    
//     };
//    // Filter display by status
// $scope.statusFilterFn = function (status) {
//  return function(item) {
//      return item.status == status;
//    }
// }
// // FIlter display by year
// $scope.yearFilterFn = function (pm) {
//   return $scope.selectedYear == ""
//   || pm.year == $scope.selectedYear;
// }
//  // FIlter display by name
// $scope.nameFilterFn = function (pm) {
//   return $scope.individualName == ""
//   || pm.employee_name.toLowerCase().search($scope.individualName.toLowerCase()) != -1;
// }
//   // FIlter display by position
// $scope.positionFilterFn = function (pm) {
//   return $scope.searchInputText == ""
//   || pm.position.toLowerCase().search($scope.searchInputText.toLowerCase()) != -1;
// };
 
 // Refresh Status list


 // Initialize refresh overlay
 $scope.initOverlay = function() {
  $('.pms-overlay-btn').niftyOverlay({
      iconClass: "fa fa-spinner fa-spin fa-2x"
  }); 
 };
 // Change view mode
 $scope.changeView = function (view) {
   $scope.viewMode = view;
 };
 $scope.initPmListTable = function () {
   $('.pm-list-table').footable();
     $('#pmstaff').footable();
   
 };
$scope.getStausClass = function (item) {
  var retStatusCls = "label-default";
  var status = item.pmstatus;
  if (status == $scope.data.pmStatusList[0].status) {
    retStatusCls = "label-primary";
  } else if (status == $scope.data.pmStatusList[1].status) {
    retStatusCls = "label-info";
  } else if (status == $scope.data.pmStatusList[2].status) {
    retStatusCls = "label-warning";
  }else if (status == $scope.data.pmStatusList[3].status) {
    retStatusCls = "label-success";
  }
  return retStatusCls;
};
$scope.getStausShow = function (item) {
 
 
  var retStatusCls = "";
  var status = item;
  if (status == $scope.data.pmStatusList[0].status) {
    retStatusCls = $scope.data.pmStatusList[0].description;
  } else if (status == $scope.data.pmStatusList[1].status) {
    retStatusCls = $scope.data.pmStatusList[1].description;
  } else if (status == $scope.data.pmStatusList[2].status) {
     retStatusCls = $scope.data.pmStatusList[2].description;
  }else if (status == $scope.data.pmStatusList[3].status) {
    retStatusCls = $scope.data.pmStatusList[3].description;
  }else if (status == $scope.data.pmStatusList[4].status) {
    retStatusCls = $scope.data.pmStatusList[4].description;
  }
  return retStatusCls;
};
$scope.getTableView = function () {
    return "views/pmlist-table.html";
};
$scope.getStatusView = function () {
    return "views/pmlist-status.html";
};





//$scope.initLoad();
 //$scope.initACTI();
 $scope.initOverlay();
 $scope.initPmListTable();
 

});
appModule.directive("pmStatusItem", function() {
     return {
      restruct: "E",
      scope: {
        item: '='
      },
      controller:"PMListController",
      templateUrl: "views/pmstatus_item_template.html"
    };
});
appModule.directive("pmPagingToolbar", function() {
     return {
      restruct: "E",
      controller:"PMListController",
      templateUrl: "views/paging-toolbar-template.html"
    };
});
appModule.directive("pmTableView", function() {
     return {
      restruct: "E",
      scope: {
        items: '=',
        individual: '@'
      },
      controller:"PMListController",
      templateUrl: "views/pmlist-table.html"
    };
});

appModule.directive('footable', function(){
  return function(scope, element)
  {

    if(scope.$last && !$('.footable').hasClass('footable-loaded')) {
            $('.footable').footable();
    }

    var footableObject = $('.footable').data('footable');
    if (footableObject  !== undefined) {
            footableObject.appendRow($(element));
    }

  };})
