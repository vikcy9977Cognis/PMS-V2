function displayOverlay() {
    $("<table id='overlay'><tbody><tr><td>" +  "<i class='fa fa-spinner fa-spin '></i>" + "</td></tr></tbody></table>").css({
        "position": "fixed",
        "top": "0px",
        "left": "0px",
        "width": "100%",
        "height": "100%",
        "background-color": "rgba(0,0,0,.5)",
        "z-index": "10000",
        "vertical-align": "middle",
        "text-align": "center",
        "color": "#fff",
        "font-size": "40px",
        "font-weight": "bold",
        "cursor": "wait"
    }).appendTo("body");
}
 
function removeOverlay() {
    $("#overlay").remove();
}
 
appModule.controller('validationCtrlMyPm', ['$scope', '$http', function ($scope, $http) {
     
    var URLLOADINIT=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadprofile';
    var URLLOADYEAR=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadyear';
    var URLLOADMYPM=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadmypm';
    var URLLOADACTI=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadactivities';
    var URLLOADMYSTAFF=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadmystaff';
   
    
   if($scope.data ==undefined){
     $scope.data = {};
  }
   if($scope.tablePM ==undefined){
    $scope.tablePM = {};
    }
     $scope.refreshMyPmList = function() {
     $scope.initMyPm('');
      $scope.$parent.supervisorLevelStack = [];
    };
   //start
   $scope.formData = {
     "pmperiod":"1",
     "selectedYear":"",
      pageNo:1,
     "displayLength":10
   };
 
     $scope.periodMyPm1H = function() {
   if($scope.formData.pmperiod !="1" ){
       $('#periodIdMyPm1H').removeClass();
       $('#periodIdMyPm1H').addClass("btn btn-purple");
        $('#periodIdMyPm2H').removeClass();
         $('#periodIdMyPm2H').addClass( "btn btn-default");
          $('#pmperiodData').val( "1");
          $scope.formData.pmperiod ="1";
         
    var formData = {
     pmForceperiod: $scope.formData.pmperiod,
     pmperiod: $scope.formData.pmperiod,
     selectedYear:$('#selectedYearMyPm').val()
   };
       $scope.initMyPm(formData);
       
       
   }
      
        
        $scope.formData.pmperiod="1";
    };
    
    $scope.periodMyPm2H = function() {
   if($scope.formData.pmperiod !="2" ){
        $('#periodIdMyPm1H').removeClass();
       $('#periodIdMyPm1H').addClass("btn btn-default");
        $('#periodIdMyPm2H').removeClass();
         $('#periodIdMyPm2H').addClass( "btn btn-purple");
         $('#pmperiodData').val( "2");
          $scope.formData.pmperiod =2;
         var formData = {
          pmForceperiod: $scope.formData.pmperiod,
     pmperiod: $scope.formData.pmperiod,
     selectedYear:$('#selectedYearMyPm').val()
   };
       $scope.initMyPm(formData);
       
   }
      
        
        $scope.formData.pmperiod="2";
    };
    
   $scope.initMyPm = function(dataInput) {
         
        //show loading
        //panel-overlay
  
        displayOverlay() ;
        $scope.displayLength=20;
 
 
 
        $http({
            method: 'GET',
            url: URLLOADINIT,
            params: dataInput ,
            async:   false,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (datas, status, headers, config) {
            
             $('#pualStart').val("true");
            // handle success things
           var data= datas.data[0];
            //.data["0"].myStaffCount
            if(data.myStaffCount > 0 || ( data.userLevel == 'ADMIN'|| data.userLevel == 'HR' )  ){
              angular.element('#barMystaff').css('display', '');
            }else{
              angular.element('#barMystaff').css('display', 'none');
            }
            $('#userLevelId').val(data.userLevel);
            if(  ( data.userLevel != 'ADMIN' && data.userLevel != 'HR' )  ){
             //$('#userLevelId').val(data.userLevel);
                $('#mngId').val(data.mngId);
                 $('#mngIdOld').val(data.mngId);
                
            }else{
               $('#mngId').val("");
                 $('#mngIdOld').val("");
            }
        
          
              
             
              if(data.addNewPm  != 'Y' )   {
                angular.element('#addNewPm').css('display', 'none');
              }
              if(dataInput.forceStart != undefined ){
                 $scope.formData.selectedYear = data.yearActive;
                 $scope.selectedYear = data.yearActive+"";
                  if(data.periodActive=='1'){
               //ng-click="periodMyPm1H()"
                 $scope.periodMyPm1H();
                $('#loadStaffYeart').val(data.yearActive);
                 $('#loadStaffPeriod').val(data.periodActive);;
               
                
                
               }
               else{
                $scope.periodMyPm2H();
                
               }
              }else{
              
              }
              
               
               $scope.initTableMyPm($scope.formData);
              // $scope.initTableMyStaff();
//              $scope.initLastActivityList();
//          
 
              setTimeout(function(){
              $('#forceReloadData').val("true");
             // $('#myStaffReloadAjax').trigger('click');
 
 
 
               
              // $('#staffsubmit').trigger('click');
//              $('#startLoadMystaff').val("load");
// 
////              $("companyMystaff").val("");
////              $("departmentMystaff").val("");
////               $("sectionMystaff").val("");
//               $('#staffsubmit').trigger('click');
//                //angular.element('#staffsubmit').triggerHandler('click');
//                  
                //$scope.reloadOption();
//$scope.loadCompany();
                  //removeOverlay(); 
              },  100);
              
                setTimeout(function(){
                removeOverlay(); 
              },  5000);
              
             //myStaffReloadAjax
              // $scope.finishLoadMypm();
           
           
 
 
            console.log(datas,status, headers, config);
        }).error(function (datas, status, headers, config) {
            // handle error things
            removeOverlay();
            console.log(datas,status, headers, config);
        });
    }
    
    $scope.initDropDown = function() {
         
        //init listYear
        $scope.listYear=[]; 
        $scope.dataYear = {selected: "All"};
        
        
        
        $http({
            method: 'GET',
            url: URLLOADYEAR,
            async:   false,
            //data: $(element).serialize() ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data, status, headers, config) {
            // handle success things
           $scope.listYear =data;
            
           
           console.log(data,status, headers, config);
        }).error(function (data, status, headers, config) {
            // handle error things
              
            
            console.log(data,status, headers, config);
        });
    }
    
  $scope.setFormScope= function(scope){
     this.form = scope;
  }
   $scope.refreshMyPm = function() {
  
    var formData = {
     pmperiod: $scope.formData.pmperiod,
     selectedYear:$('#selectedYearMyPm').val()
   };
       $scope.initMyPm(formData);
       
       
   }
    $scope.refreshStaff = function() {
    $("#staffsubmit").trigger("click");
 };
 $('#staffreload').click(function(){
    //Some code
    $("#staffsubmit").trigger("click");
});

   $scope.func1H = function() {
        $scope.formData.pmperiod="1";
    };
    $scope.func2H = function() {
        $scope.formData.pmperiod="2";
    };
    
    
//    $scope.nextPagePm = function() {
//          var formData = {
//     pmperiod: $scope.formData.pmperiod,
//     selectedYear:$('#selectedYearMyPm').val()
//   };
//        $scope.initTableMyPm($scope.formData);
//    };
    
    
    
    $scope.submit = function(event){
       var element = event.currentTarget;
       console.log($(element).serialize());
       console.log($scope.formData);
        console.log( $scope.formSearch ); 
        
        $http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
 
       
    };
    var tmpDa
    var tmpData = {
          forceStart: true
     
     };
     $scope.initMyPm(tmpData);
    $scope.initDropDown();
}]);
 


////////////////////////////////////////////////////////////////


appModule.factory('Excel',function($window){
		var uri='data:application/vnd.ms-excel;base64,',
			template='<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
			base64=function(s){return $window.btoa(unescape(encodeURIComponent(s)));},
			format=function(s,c){return s.replace(/{(\w+)}/g,function(m,p){return c[p];})};
		return {
			tableToExcel:function(tableId,worksheetName){
				var table=$(tableId),
					ctx={worksheet:worksheetName,table:table.html()},
					href=uri+base64(format(template,ctx));
				return href;
			}
		};
	}).controller('validationCtrlEmpPerfRes', ["$rootScope", "$scope", "$http" ,"Excel","$timeout", function ($rootScope, $scope, $http,Excel,$timeout) {
            var URLLOADINIT=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadprofile';
            var URLLOADCOMPANY=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadcompany';
            var URLLOADDEPARTMENT=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loaddepartant';
            var URLLOADJOBBRAND=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadjobbrand';
            var URLLOADYEARS=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadyear';
            var URLLOADEMPPERFRES=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadempperfres';   
           
            $scope.exportToExcel=function(tableId){ // ex: '#my-table'
			var exportHref=Excel.tableToExcel(tableId,'EMPPERREPORT');
			$timeout(function(){location.href=exportHref;},100); // trigger download
		}
   
    $scope.initEmpPer = function(dataInput) {
         
        displayOverlay() ;
        $scope.displayLength=20;
        $http({
            method: 'GET',
            url: URLLOADINIT,
            params: dataInput ,
            async:   false,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (datas, status, headers, config) {
            
             $('#pualStart').val("true");
            // handle success things
           var data= datas.data[0];
           
            $('#userLevelId').val(data.userLevel);
            if(  ( data.userLevel != 'ADMIN' && data.userLevel != 'HR' )  ){
             //$('#userLevelId').val(data.userLevel);
                $('#mngId').val(data.mngId);
                 $('#mngIdOld').val(data.mngId);
                
            }else{
               $('#mngId').val("");
                 $('#mngIdOld').val("");
            }
                setTimeout(function(){
                removeOverlay(); 
              },  5000);
              
 
            console.log(datas,status, headers, config);
        }).error(function (datas, status, headers, config) {
            // handle error things
            removeOverlay();
            console.log(datas,status, headers, config);
        });
    }
   
  // $scope.initEmpPer('');
//   
       if($scope.data ==undefined){
         $scope.data = {};
      }
 
 
 
   $scope.formData = {
     pmperiod:"1",
     selectedYear:$('#selectedYearEmpPerfRes').val()
   };
     
    $scope.loadrefreshEmpPerfResData = function(event) {
      $scope.submit(event)
    };
   
   $scope.loadResetAllEmpPerfRes = function(event) {
     //$scope.submit(event)
    $scope.$parent.supervisorLevelStack = [];
    
     $('#validationCtrlEmpPerfReset') .trigger("click");
     
      if( $('#loadCompany').val() == ""){
         $("#companyEmpPerfRes").val('');
         $("#companyEmpPerfRes option").filter(function(index) { return $(this).val() == "" }).attr('selected', 'selected');
      }else{
        $("#companyEmpPerfRes option").filter(function(index) { return $(this).text() == $('#loadCompany').val() }).attr('selected', 'selected');
      }
        if( $('#loadDepartment').val() == ""){
         $("#departmentEmpPerfRes").val('');
         $("#departmentEmpPerfRes option").filter(function(index) { return $(this).val() == "" }).attr('selected', 'selected');
      }else{
        $("#departmentEmpPerfRes option").filter(function(index) { return $(this).text() == $('#loadDepartment').val() }).attr('selected', 'selected');
      }
    
      if($('#loadStaffPeriod').val()!=""){
        if($('#loadStaffPeriod').val()==1){
          //$("#periodIdMyStaff1H").trigger("click");
         $scope.periodEmpPerfRes1H();
        }else if($('#loadStaffPeriod').val()==2) {
            $scope.periodEmpPerfRes2H();
        }
      }
      
     // $("#departmentMystaff option").filter(function(index) { return $(this).text() === datas.data.dataTable[0].depname; }).attr('selected', 'selected');
 };
// $scope.doExportExcel = function (event) {
// displayOverlay();
//  var URLEXPORTEXCEL=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=exportExcel';
//      $http({
//            method: 'GET',
//            async:   false,
//            url: URLEXPORTEXCEL,
//            params: {} ,
//            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
//        }).success(function (datas, status, headers, config) {
//        
//            console.log(datas,status, headers, config);
//             
//       //     $scope.data.listSection =datas;
//       
//        if (datas.statusCode == 0) {
//            window.open(datas.statusMessage);
//        } else {
//            // showStatusMessage(jsonResult);
//        }
//        removeOverlay();
//        
//        }).error(function (datas, status, headers, config) {
//            // handle error things
//           
//            console.log(datas,status, headers, config);
//             removeOverlay();
//        });
// };
  $("#loadrefreshEmpPerfRes").click(function(){
    $("#EmpPerfResSubmit").trigger("click");
});
      
    $scope.loadCompany = function() {
         
        $http({
            method: 'GET',
            async:   false,
            url: URLLOADCOMPANY,
            // params: data ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (datas, status, headers, config) {
          // $('#companyEmpPerfRes').find('option').remove() ;
             if(datas.data==''){
                $scope.reloadOption();
             }else
             $scope.listCompany =datas;
             
              console.log(datas,status, headers, config);
        }).error(function (datas, status, headers, config) {
            // handle error things
            console.log(datas,status, headers, config);
        });
    };
   
   $scope.loadDepartment = function() {
 
  //$('#departmentEmpPerfRes').find('option').remove()   ;
   var comSeqno=  "";
   var divSeqno=  "";
   
         var dataInput = {
           "comSeqno":comSeqno,
           "divSeqno":divSeqno
         };
      
        $http({
            method: 'GET',
            async:   false,
            url: URLLOADDEPARTMENT,
            async:   false,
            params: dataInput ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (datas, status, headers, config) {
        
            console.log(datas,status, headers, config);
             
            $scope.listDepartment =datas;
  
        }).error(function (datas, status, headers, config) {
            // handle error things
            console.log(datas,status, headers, config);
        });
    };
   $scope.reloadOption = function(  ) {
  
                     $scope.loadCompany();
                    $scope.loadDepartment();
         } ;     
         
        $scope.reloadAjax  = function(  ) {
         $scope.reloadOption();
        } ;     
   //$scope.reloadOption ();
      
      
     $scope.clearOption = function() {
      $('#departmentEmpPerfRes') .find('option').remove().end() .append('<option value="">All</option>').val('') ;
      $('#companyEmpPerfRes') .find('option').remove().end() .append('<option value="">All</option>').val('') ;
       //$('#divisionMystaff') .find('option') .remove() .end() .append('<option value="">All</option>') .val('') ;
        //$('#sectionMystaff') .find('option') .remove() .end() .append('<option value="">All</option>') .val('') ;
      
     }
    
   
    $scope.initPmperiod = function() {
     $scope.pmperiod="1";
     $scope.func1H = function() {
          $scope.pmperiod="1";
          console.log(this);
      };
      $scope.func2H = function() {
          $scope.pmperiod="2";
      };
    }
    
    
    
    
    $scope.selectedItemCompany= function() {
      //$scope.loadDivision();
    }
    
    
    $scope.initDropDown = function() {
    //init listYear
        $scope.listYears=[]; 
        $http({
            method: 'GET',
            async:   false,
            url: URLLOADYEARS,
            //data: $(element).serialize() ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data, status, headers, config) {
            // handle success things
           $scope.listYears =data;
            
            console.log(data,status, headers, config);
        }).error(function (data, status, headers, config) {
            // handle error things
             
            console.log(data,status, headers, config);
        });
                
        //init Job Brand
        $scope.listJobBrand=[]; 
        $scope.dataJobBrand = {selected: ""};
        $http({
            method: 'GET',
            async:   false,
            url: URLLOADJOBBRAND,
            //data: $(element).serialize() ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data, status, headers, config) {
            // handle success things
           $scope.listJobBrand =data; 
             
            console.log(data,status, headers, config);
        }).error(function (data, status, headers, config) {
            // handle error things
             
            console.log(data,status, headers, config);
        });
    }
    
  $scope.setFormScope= function(scope){
     this.form = scope;
  }
   
     $scope.periodEmpPerfRes1H = function() {
       if($('#pmperiodEmpPerfRes').val() !="1" && $('#pmperiodEmpPerfRes').val()== "2" ){
           $('#periodIdEmpPerfRes1H').removeClass();
           $('#periodIdEmpPerfRes1H').addClass("btn btn-purple");
           
           $('#pmperiodEmpPerfRes').val( "0");
          $scope.formData.pmperiodEmpPerfRes="0";
       }else if($('#pmperiodEmpPerfRes').val() !="1" && $('#pmperiodEmpPerfRes').val()== "0" ){
           $('#periodIdEmpPerfRes1H').removeClass();
           $('#periodIdEmpPerfRes1H').addClass("btn btn-default");
           
           $('#pmperiodEmpPerfRes').val( "2");
          $scope.formData.pmperiodEmpPerfRes="2";
       }else if($('#pmperiodEmpPerfRes').val() =="1"){
            
       }
    };
    
    $scope.periodEmpPerfRes2H = function() {
    
           if($('#pmperiodEmpPerfRes').val() !="2" && $('#pmperiodEmpPerfRes').val()== "1" ){
           $('#periodIdEmpPerfRes2H').removeClass();
           $('#periodIdEmpPerfRes2H').addClass("btn btn-purple");
           
           $('#pmperiodEmpPerfRes').val( "0");
          $scope.formData.pmperiodEmpPerfRes="0";
       }else if($('#pmperiodEmpPerfRes').val() !="2" && $('#pmperiodEmpPerfRes').val()== "0" ){
           $('#periodIdEmpPerfRes2H').removeClass();
           $('#periodIdEmpPerfRes2H').addClass("btn btn-default");
           
           $('#pmperiodEmpPerfRes').val( "1");
          $scope.formData.pmperiodEmpPerfRes="1";
       }else if($('#pmperiodEmpPerfRes').val() =="2"){
            
       }
   }
    
    $scope.submit = function(form){
       
       console.log("Search Form Submit() called for Emplyee Performance REsult Report");
       
       var selYear = $("#selectedYearEmpPerfRes").val();
       
       document.getElementById("perfComp1H").innerHTML = "1H " + selYear;
       document.getElementById("resComp1H").innerHTML = "1H " + selYear;
       document.getElementById("perfInd1H").innerHTML = "1H " + selYear;
       document.getElementById("resInd1H").innerHTML = "1H " + selYear;
       document.getElementById("perfComp2H").innerHTML = "2H " + selYear;
       document.getElementById("resComp2H").innerHTML = "2H " + selYear;
       document.getElementById("perfInd2H").innerHTML = "2H " + selYear;
       document.getElementById("resInd2H").innerHTML = "2H " + selYear;
       
       document.getElementById("tblEmpPerfRes").style.display="table";
       
       console.log($('formSearchEmpPerf').serialize());
//       console.log($scope.formData);
//        console.log( $scope.formSearch ); 
        $http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
        
        var dataInput = {
            "selectedYear" : $("#selectedYearEmpPerfRes").val(),
             "company" :  $("#companyEmpPerfRes").val()=="" ? "-1" : $("#companyEmpPerfRes").val(),
              "department" :  $("#departmentEmpPerfRes").val()=="" ? "-1" : $("#departmentEmpPerfRes").val(),
              "jobBrand" :  $("#jobBrandEmpPerfRes").val()=="" ? "All" : $("#departmentEmpPerfRes").val(),
              "resByCompetency" : $("#resultByCompetency").val(),
              "resByIndKPIs" : $("#resultByIndKPIs").val()
         };
          displayOverlay() ;
        
        $http({
            method: 'GET',
            url: URLLOADEMPPERFRES,
            params:dataInput,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data, status, headers, config) {
            // handle success things
           $rootScope.pmsempperflist =data;
           var forDatas=data;
           if($('#periodIdEmpPerfRes1H').hasClass('btn btn-purple') && $('#periodIdEmpPerfRes2H').hasClass('btn btn-purple')){
                    $rootScope.pmsempperflist =data;
           }else{
            $(forDatas ).each(function(index, obj){
           if($('#periodIdEmpPerfRes1H').hasClass('btn btn-purple') && !$('#periodIdEmpPerfRes2H').hasClass('btn btn-purple')){
                   var newData={};
                   data.data[index].competencyPercent2H="-"; 
                   data.data[index].resComp2H="-";
                   data.data[index].averageCompetencyPercent=data.data[index].competencyPercent1H;
                   data.data[index].resCompAvg=data.data[index].resComp1H;                   
                   
                   data.data[index].indKPIPercent2H="-";
                   data.data[index].resIndKPI2H="-";
                   data.data[index].averageIndKPIPercent=data.data[index].indKPIPercent1H;
                   data.data[index].resIndKPIAvg=data.data[index].resIndKPI1H; 
           }else if(!$('#periodIdEmpPerfRes1H').hasClass('btn btn-purple') && $('#periodIdEmpPerfRes2H').hasClass('btn btn-purple')){
                   data.data[index].competencyPercent1H="-"; 
                   data.data[index].resComp1H="-";
                    data.data[index].averageCompetencyPercent=data.data[index].competencyPercent2H;
                   data.data[index].resCompAvg=data.data[index].resComp2H;  
                   
                   data.data[index].indKPIPercent1H="-";
                   data.data[index].resIndKPI1H="-";
                   data.data[index].averageIndKPIPercent=data.data[index].indKPIPercent2H;
                   data.data[index].resIndKPIAvg=data.data[index].resIndKPI2H; 
            }
            });
             $rootScope.pmsempperflist =data;
        } removeOverlay();
            console.log(data,status, headers, config);
            //console.log("/////////////// "+$scope.data.pmsempperflist.data);
        }).error(function (data, status, headers, config) {
            // handle error things 
            removeOverlay();
            console.log(data,status, headers, config);
        });
//        var formdatas =  $(element).serializeArray();
//        var datas = {};
//        $(formdatas ).each(function(index, obj){
//            datas[obj.name] = obj.value;
//        });
//        console.log("loadWithoutSearch =" + $('#loadWithoutSearch').val());
//        if($('#loadWithoutSearch').val()== 1){         
//        } 
//        else{
//             $('#setDataCompany').val($("#companyEmpPerfRes option:selected").text());
//         // $('#setDataDivision').val($("#divisionMystaff option:selected").text());
//          $('#setDataDepartment').val($("#departmentEmpPerfRes option:selected").text());
//          // $('#setDataSection').val($("#sectionMystaff option:selected").text());
//           
//             // Clear bedcrumb link
//           $scope.$parent.supervisorLevelStack = [];
//        }
//        //datas.mngId=$('#mngId').val();
//        
//        //if($('#empidClick').val()==""){
//          //$('#empidClick').val($('#loadEmpName').val());
//        //}
//         datas['pageNo'] = 1;
//        //$scope.initTableMyStaff(datas);
    };
      var tmpData = {
          forceStart: true
     
     };
     $scope.initEmpPer(tmpData);
       
   // $scope.clearOption();
    $scope.initDropDown();  
    $scope.reloadOption();
}]);



///////////////////////////////////////////////////////////////
 
 
appModule.controller('validationCtrl', ['$scope', '$http', function ($scope, $http) {
    var URLLOADCOMPANY=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadcompany';
    var URLLOADDIVISION=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loaddivision';
    var URLLOADDEPARTMENT=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loaddepartant';
    var URLLOADSECTION=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadsection';
 
   var URLLOADMYSTAFF=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadmypm';
 
  
   var URLLOADJOBGRADE=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadjobgrade';
   var URLLOADJOBBRAND=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadjobbrand';
   var URLLOADYEARS=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadyear';
   if($scope.data ==undefined){
     $scope.data = {};
  }
  function finishLoadMypm(){};
 
  $scope.listHeaderMystaff=[];
   $scope.$watch('listHeaderMystaff', function() {
        //console.log("change");
    });
   
   $scope.formData = {
     pmperiod:"1",
     selectedYear:""
   };
     $scope.loadrefreshMystaffData = function(event) {
      $scope.submit(event)
 };
   $scope.loadResetAllMyStaff = function(event) {
     //$scope.submit(event)
    $scope.$parent.supervisorLevelStack = [];
    
     $('#validationCtrlReset') .trigger("click");
     
      
      if( $('#loadDivision').val() == ""){
         $("#divisionMystaff").val('');
         $("#divisionMystaff option").filter(function(index) { return $(this).val() == "" }).attr('selected', 'selected');
      }else{
      
        $("#divisionMystaff option").filter(function(index) { return $(this).text() == $('#loadDivision').val() }).attr('selected', 'selected');
      }
       if( $('#loadCompany').val() == ""){
         $("#companyMystaff").val('');
         $("#companyMystaff option").filter(function(index) { return $(this).val() == "" }).attr('selected', 'selected');
      }else{
        $("#companyMystaff option").filter(function(index) { return $(this).text() == $('#loadCompany').val() }).attr('selected', 'selected');
      }
        if( $('#loadDepartment').val() == ""){
         $("#departmentMystaff").val('');
         $("#departmentMystaff option").filter(function(index) { return $(this).val() == "" }).attr('selected', 'selected');
      }else{
        $("#departmentMystaff option").filter(function(index) { return $(this).text() == $('#loadDepartment').val() }).attr('selected', 'selected');
      }
       if( $('#loadSection').val() == ""){
         $("#sectionMystaff").val('');
         $("#sectionMystaff option").filter(function(index) { return $(this).val() == "" }).attr('selected', 'selected');
      }else{
        $("#sectionMystaff option").filter(function(index) { return $(this).text() == $('#loadSection').val() }).attr('selected', 'selected');
      }
      if($('#loadStaffPeriod').val()!=""){
        if($('#loadStaffPeriod').val()==1){
          //$("#periodIdMyStaff1H").trigger("click");
         $scope.periodMyStaff1H();
        }else if($('#loadStaffPeriod').val()==2) {
            $scope.periodMyStaff2H();
        }
      }
      
     // $("#departmentMystaff option").filter(function(index) { return $(this).text() === datas.data.dataTable[0].depname; }).attr('selected', 'selected');
 };
 $scope.doExportExcel = function (event) {
 displayOverlay();
  var URLEXPORTEXCEL=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=exportExcel';
      $http({
            method: 'GET',
            async:   false,
            url: URLEXPORTEXCEL,
            params: {} ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (datas, status, headers, config) {
        
            console.log(datas,status, headers, config);
             
       //     $scope.data.listSection =datas;
       
        if (datas.statusCode == 0) {
            window.open(datas.statusMessage);
        } else {
            // showStatusMessage(jsonResult);
        }
        removeOverlay();
        
        }).error(function (datas, status, headers, config) {
            // handle error things
           
            console.log(datas,status, headers, config);
             removeOverlay();
        });
 };
  $("#loadrefreshMystaffId").click(function(){
    $("#staffsubmit").trigger("click");
});
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
            async:   false,
            url: URLLOADSECTION,
            async:   false,
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
            async:   false,
            url: URLLOADDEPARTMENT,
            async:   false,
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
   $scope.loadDivision = function() {
 
    $('#divisionMystaff') .find('option') .remove() ;
 
    var comSeqno=  $("#companyMystaff").val();
   
         var dataInput = {
           "comSeqno":comSeqno
         };
      
        $http({
            method: 'GET',
            async:   false,
            url: URLLOADDIVISION,
            async:   false,
            params: dataInput ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (datas, status, headers, config) {
        
            console.log(datas,status, headers, config);
            console.log("up");
            $scope.data.listDivision =datas;
  
        }).error(function (datas, status, headers, config) {
            // handle error things
            console.log(datas,status, headers, config);
        });
    };
   $scope.loadCompany = function() {
     
     
       
        $http({
            method: 'GET',
            async:   false,
            url: URLLOADCOMPANY,
            // params: data ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (datas, status, headers, config) {
        
           
           $('#companyMystaff') .find('option') .remove() ;
            
             $scope.data.listCompany =datas;
              
              console.log(datas,status, headers, config);
        }).error(function (datas, status, headers, config) {
            // handle error things
            console.log(datas,status, headers, config);
        });
    };
   $scope.reloadOption = function(  ) {
  
                    $scope.loadCompany();
                    $scope.loadDivision ();
                    $scope.loadDepartment();
                    $scope.loadSection();
         } ;     
         
        $scope.reloadAjax  = function(  ) {
         $scope.reloadOption();
        } ;     
   //$scope.reloadOption ();
      
      
   
//       //listDepartment
//    $scope.data.listDepartment={"data":[{"des":"","value":"All"}]};
//       
//  
//    $scope.$watch('data.listDepartment', function() {
//                
//              });
//  
//      $scope.department=  "";
//      $scope.$watch('department', function() {
//                
//              });
//       $('#departmentMystaff').val('');
//         setTimeout(function() {
//            $('#departmentMystaff').val('');
//      }, 1);
//      //listDepartment
//   
//    $scope.data.listCompany={"data":[{"des":"","value":"All","des2":""}]};
//       
//  
//    $scope.$watch('data.listCompany', function() {
//                
//      });
//  
//      $scope.company=  "";
//      $scope.$watch('company', function() {
//                
//      });
//       $('#companyMystaff').val('');
//         setTimeout(function() {
//            $('#companyMystaff').val('');
//      }, 1);
//
//    //
//    //division
//     $scope.data.listDivision={"data":[{"des":"","value":"All"}]};
//     
//    $scope.$watch('data.listDivision', function() {
//                
//              });
//      $scope.division=  "";
//      $scope.$watch('division', function() {
//                
//              });
//                
//       $('#divisionMystaff').val('');
//         setTimeout(function() {
//            $('#divisionMystaff').val('');
//      }, 1);
//  
     $scope.clearOption = function() {
      $('#departmentMystaff') .find('option') .remove() .end() .append('<option value="">All</option>') .val('') ;
      $('#companyMystaff') .find('option') .remove() .end() .append('<option value="">All</option>') .val('') ;
       $('#divisionMystaff') .find('option') .remove() .end() .append('<option value="">All</option>') .val('') ;
        $('#sectionMystaff') .find('option') .remove() .end() .append('<option value="">All</option>') .val('') ;
      
     }
    
   
    $scope.initPmperiod = function() {
     $scope.pmperiod="1";
     $scope.func1H = function() {
          $scope.pmperiod="1";
          console.log(this);
      };
      $scope.func2H = function() {
          $scope.pmperiod="2";
      };
    }
    
    
    
    
    $scope.selectedItemCompany= function() {
      //$scope.loadDivision();
    }
    
    
    $scope.initDropDown = function() {
    //init listYear
        $scope.listYears=[]; 
        
        
        
 
        $http({
            method: 'GET',
            url: URLLOADYEARS,
            async:   false,
            //data: $(element).serialize() ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data, status, headers, config) {
            // handle success things
 
          
            
            console.log(data,status, headers, config);
        }).error(function (data, status, headers, config) {
            // handle error things
           
            console.log(data,status, headers, config);
        });
        //init  Job Grade
        
        $scope.listJobGrade=[]; 
        $scope.dataJobGrade = {selected: ""};
        $http({
            method: 'GET',
            async:   false,
            url: URLLOADJOBGRADE,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data, status, headers, config) {
            // handle success things
           $scope.listJobGrade=data; 
            
           console.log(data,status, headers, config);
        }).error(function (data, status, headers, config) {
           
          console.log(data,status, headers, config);
        });
        
        //init Job Brand
        $scope.listJobBrand=[]; 
        $scope.dataJobBrand = {selected: ""};
        $http({
            method: 'GET',
            async:   false,
            url: URLLOADJOBBRAND,
            //data: $(element).serialize() ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data, status, headers, config) {
            // handle success things
           $scope.listJobBrand =data; 
             
            console.log(data,status, headers, config);
        }).error(function (data, status, headers, config) {
            // handle error things
             
            console.log(data,status, headers, config);
        });
        
        
        $http({
            method: 'GET',
            async:   false,
            url: URLLOADYEARS,
            //data: $(element).serialize() ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data, status, headers, config) {
            // handle success things
           $scope.listYears =data;
            
            console.log(data,status, headers, config);
        }).error(function (data, status, headers, config) {
            // handle error things
             
            console.log(data,status, headers, config);
        });
        //init  Job Grade
        
        $scope.listJobGrade=[]; 
        $scope.dataJobGrade = {selected: ""};
        $http({
            method: 'GET',
            async:   false,
            url: URLLOADJOBGRADE,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data, status, headers, config) {
            // handle success things
           $scope.listJobGrade=data; 
             
           console.log(data,status, headers, config);
        }).error(function (data, status, headers, config) {
         
          console.log(data,status, headers, config);
        });
          
        
    }
    
  $scope.setFormScope= function(scope){
     this.form = scope;
  }
   
     $scope.periodMyStaff1H = function() {
       if($('#pmperiodMyStaff').val() !="1" ){
           $('#periodIdMyStaff1H').removeClass();
           $('#periodIdMyStaff1H').addClass("btn btn-purple");
           $('#periodIdMyStaff2H').removeClass();
           $('#periodIdMyStaff2H').addClass( "btn btn-default");
       }
       
       $('#pmperiodMyStaff').val( "1");
       $scope.formData.pmperiodMyStaff="1";
    };
    
    $scope.periodMyStaff2H = function() {
       if( $('#pmperiodMyStaff').val() !="2" ){
            $('#periodIdMyStaff1H').removeClass();
            $('#periodIdMyStaff2H').removeClass();
            $('#periodIdMyStaff1H').addClass("btn btn-default");
            $('#periodIdMyStaff2H').addClass( "btn btn-purple ");
         }
          $('#pmperiodMyStaff').val( "2");
         
        $scope.formData.pmperiodMyStaff="2";
   }
    $scope.selectedItemCompany= function() {
      //$scope.loadDivision();
    }
    $scope.submit = function(event){
       var element = event.currentTarget;
       console.log("Search Form Submit() called");
       
       
//       console.log($(element).serialize());
//       console.log($scope.formData);
//        console.log( $scope.formSearch ); 
//        $http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
//        $http({
//            method: 'GET',
//            url: URLLOADMYSTAFF,
//            params: $(element).serialize(),
//            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
//        }).success(function (data, status, headers, config) {
//            // handle success things
//            
//           $scope.data.pmstafflist =data.data; 
//            console.log(data,status, headers, config);
//        }).error(function (data, status, headers, config) {
//            // handle error things
//            console.log(data,status, headers, config);
//        });
        var formdatas =  $(element).serializeArray();
        var datas = {};
        $(formdatas ).each(function(index, obj){
            datas[obj.name] = obj.value;
        });
        console.log("loadWithoutSearch =" + $('#loadWithoutSearch').val());
        if($('#loadWithoutSearch').val()== 1){         
        } 
        else{
             $('#setDataCompany').val($("#companyMystaff option:selected").text());
          $('#setDataDivision').val($("#divisionMystaff option:selected").text());
          $('#setDataDepartment').val($("#departmentMystaff option:selected").text());
           $('#setDataSection').val($("#sectionMystaff option:selected").text());
           
             // Clear bedcrumb link
           $scope.$parent.supervisorLevelStack = [];
        }
        datas.mngId=$('#mngId').val();
        
        if($('#empidClick').val()==""){
          $('#empidClick').val($('#loadEmpName').val());
        }
         datas['pageNo'] = 1;
        $scope.initTableMyStaff(datas);
    };
    
    $scope.initDropDown();
    
    $scope.clearOption();
}]);


appModule.controller("pmformTab1Controller", ["$rootScope", "$scope", "$http", "$location", "$filter", "$modal",
        function($rootScope, $scope, $http, $location, $filter, $modal) {
 //$scope.initTable("validationCtrl");
    var wizard_appraisee_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_appraisee';
    var wizard_get_data_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_get_data';
    
    if(angular.equals({}, $scope.formData.appraiseeDetail)){ 
        displayOverlay("body");  
        $http({
            method: 'POST',
            url: wizard_get_data_url,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data, status, headers, config) {
            var formData = [];
            if(data.valid==="V"){
                formData = data.data!=null?angular.fromJson(data.data):[];
            }else{
                $rootScope.showErrorAlert('N', data.msg);
            }
            if(formData==null||formData.length<1){
//                set appraisee
                $http({
                    method: 'GET',
                    url: wizard_appraisee_url,
                    //data: $(element).serialize() ,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }).success(function (data, status, headers, config) {
                    if(data.valid==="V"){
                        var crm_profile = data.crm_profile!=null&&data.crm_profile.length>0?data.crm_profile[0]:null;
                        if(crm_profile!=null){
//                            var joinDate = "";
//                            if(crm_profile.joinDate=="" || crm_profile.joinDate==null){
//                                joinDate = "";
//                            }else{
//                                var date = new Date(crm_profile.joinDate);
//                                joinDate = $filter('date')(date, "dd/MM/yyyy");
//                            }
                            $scope.formData.appraiseeDetail = {
                                performance_appraisal: data.pm_period+"H Y"+data.pm_year,
                                name: crm_profile.empName,
                                employee_id: crm_profile.empId,
                                position_title: "EXECUTIVE",
                                department: crm_profile.designation+"/"+crm_profile.comName+"/"+crm_profile.divName+"/"+crm_profile.depName+"/"+crm_profile.secName,
                                grade: crm_profile.jobGradeName,
                                completency_behavior_level: crm_profile.jobBrandName,
                                date_joined: crm_profile.joinDate,
                                supervisor: crm_profile.managerLv1Name,
                                counter_siging_supervisor: crm_profile.managerLv2Name, 
                                jobBrand: crm_profile.jobBrand,
                                expYear: crm_profile.expYear,
                                expMonth: crm_profile.expMonth
                            };
                        }
                        var job_weightage = data.job_weightage;
                        for(var i in job_weightage){
                            var pdWeightage = "-";
                            var comWeightage = "-";
                            var indKPIWeightage = "-";
                            if(job_weightage[i].pdWeightage!=0){
                                pdWeightage = job_weightage[i].pdWeightage+"%";
                            }
                            if(job_weightage[i].comWeightage!=0){
                                comWeightage = job_weightage[i].comWeightage+"%";
                            }
                            if(job_weightage[i].indKPIWeightage!=0){
                                indKPIWeightage = job_weightage[i].indKPIWeightage+"%";
                            }
                            $scope.formData.weightageByJobBand.push(
                                {job_band: job_weightage[i].description, 
                                completency: comWeightage, 
                                pd: pdWeightage, 
                                individual_kpis: indKPIWeightage}
                            );
                        }
                    }else{
                        $rootScope.showErrorAlert('N', data.msg);
                    }
                    $scope.checkDisplayAgreement();
                    removeOverlay();
            //        console.log(data,status, headers, config);
                }).error(function (data, status, headers, config) {
                    // handle error things
                    removeOverlay();
                    console.log(data,status, headers, config);
                });                
            }else{
                if(formData.action!="cancel"){
    //                set appraisee
                    $http({
                        method: 'GET',
                        url: wizard_appraisee_url,
                        //data: $(element).serialize() ,
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    }).success(function (data, status, headers, config) {
                        if(data.valid==="V"){
                            var crm_profile = data.crm_profile!=null&&data.crm_profile.length>0?data.crm_profile[0]:null;
                            if(crm_profile!=null){
//                                var joinDate = "";
//                                if(crm_profile.joinDate!=""){
//                                    var date = new Date(crm_profile.joinDate);
//                                    joinDate = $filter('date')(date, "dd-MMM-yy");
//                                }
                                $scope.formData.appraiseeDetail = {
                                    performance_appraisal: data.pm_period+"H Y"+data.pm_year,
                                    name: crm_profile.empName,
                                    employee_id: crm_profile.empId,
                                    position_title: "EXECUTIVE",
                                    department: crm_profile.designation+"/"+crm_profile.comName+"/"+crm_profile.divName+"/"+crm_profile.depName+"/"+crm_profile.secName,
                                    grade: crm_profile.jobGradeName,
                                    completency_behavior_level: crm_profile.jobBrandName,
                                    date_joined: crm_profile.joinDate,
                                    supervisor: crm_profile.managerLv1Name,
                                    counter_siging_supervisor: crm_profile.managerLv2Name, 
                                    jobBrand: crm_profile.jobBrand,
                                    expYear: crm_profile.expYear,
                                    expMonth: crm_profile.expMonth
                                };
                            }
                            var job_weightage = data.job_weightage;
                            for(var i in job_weightage){
                                var pdWeightage = "-";
                                var comWeightage = "-";
                                var indKPIWeightage = "-";
                                if(job_weightage[i].pdWeightage!=0){
                                    pdWeightage = job_weightage[i].pdWeightage+"%";
                                }
                                if(job_weightage[i].comWeightage!=0){
                                    comWeightage = job_weightage[i].comWeightage+"%";
                                }
                                if(job_weightage[i].indKPIWeightage!=0){
                                    indKPIWeightage = job_weightage[i].indKPIWeightage+"%";
                                }
                                $scope.formData.weightageByJobBand.push(
                                    {job_band: job_weightage[i].description, 
                                    completency: comWeightage, 
                                    pd: pdWeightage, 
                                    individual_kpis: indKPIWeightage}
                                );
                            }
                        }else{
                            $rootScope.showErrorAlert('N', data.msg);
                        }
                        $scope.checkDisplayAgreement();
                        removeOverlay();
                //        console.log(data,status, headers, config);
                    }).error(function (data, status, headers, config) {
                        // handle error things
                        removeOverlay();
                        console.log(data,status, headers, config);
                    });  
                }else{
                    $scope.currentTab = $scope.tabCount;
                    angular.copy(formData, $scope.formData);
                    removeOverlay();   
                }
            }
            
        }).error(function (data, status, headers, config) {
            removeOverlay();
        });
    }
    $scope.checkDisplayAgreement = function () {
        $scope.displayAgreementModal();
    }
    $scope.displayAgreementModal = function () {  
        $scope.agreementModalInstance = $modal.open({
          template: '<div class="modal-header"><h3 class="modal-title">Terms Of Use</h3></div>' +
          '<div class="modal-body"> <ng-include src="\'views/agreement.html\'"></ng-include></div>' +
          '<div class="modal-footer" style="text-align:  center  !important">' +
          '    <button class="btn btn-primary" ng-click="$dismiss(cancel)">Understand</button>' +
          '</div>',
          backdrop: 'static',
          backdropClass: 'pms-backdrop-ext',
          keyboard: false,
          size: "lg"
        });
    }
}]); // END controller

appModule.controller("pmformTab2Controller", ["$rootScope", "$scope", "$http", "$location", 
        function($rootScope, $scope, $http, $location) {
 //$scope.initTable("validationCtrl");
 
    var wizard_competency_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_competency';
    
    var defaultDepartmentCoreValues = 0;
    var defaultDepartmentCoreName = "";
    $scope.formData.action = "";
     
    if($scope.formData.completecyProfile.departmentCoreValue.length<1){
        displayOverlay("body");
        //set competency
        $http({
            method: 'GET',
            url: wizard_competency_url,
            //data: $(element).serialize() ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data, status, headers, config) {
            $scope.formData.completecyProfile.companyCoreValue = [];
            $scope.formData.departmentCoreValues = [];
    //        console.log(data);
            if(data.valid==="V"){
                var indicator_company = data.indicator_company;
                var total_company_weightage = 0;
                for(var i in indicator_company){
                    var weightage = indicator_company[i].weightage;
                    $scope.formData.completecyProfile.companyCoreValue.push(
                        {ind_seqno: indicator_company[i].seqNo,
                        core_value: indicator_company[i].name, 
                        supervisor_comment: "", 
                        weightage: weightage, 
                        self_rating: 0, 
                        rating: 0,
                        total_rating: 0}
                    );
                    total_company_weightage += weightage;
                }
                $scope.formData.completecyProfile.total_company_weightage = total_company_weightage;
                var indicator_department = data.indicator_department;
                var total_department_weightage = 0;
                defaultDepartmentCoreValues = indicator_department[0].seqNo;
                defaultDepartmentCoreName = indicator_department[0].name;
                for(var i in indicator_department){
                    var departmentWeightage = indicator_department[i].weightage;   
                    $scope.formData.departmentCoreValues.push(
                      {id: indicator_department[i].seqNo, value: indicator_department[i].name}
                    );
                    total_department_weightage += departmentWeightage;
                }
                $scope.formData.completecyProfile.departmentCoreValue= [
                    {core_value: defaultDepartmentCoreValues, 
                    core_name: defaultDepartmentCoreName,
                    supervisor_comment: "", 
                    weightage: 0, 
                    self_rating: 0, 
                    rating: 0,
                    total_rating: 0}
                ];
                $scope.formData.completecyProfile.total_department_weightage = total_department_weightage;
            }else{
                $rootScope.showErrorAlert('N', data.msg);
            }
            removeOverlay();
        }).error(function (data, status, headers, config) {
            // handle error things
            removeOverlay();
            console.log(data,status, headers, config);
        });
    }
    
    $scope.dtlInd = [];
    $scope.showCoreValueInfo = function (type, index) {
        var jobBrand = $scope.formData.appraiseeDetail.jobBrand;
        var dialog = bootbox.dialog({
          title: 'Information',
          message: '<div id="coreValueInfo" class="text-center"><i class="fa fa-spin fa-spinner"></i> Loading...</div>',
          backdrop: true
          // buttons:{
          //   "OK" : function() {
          //             dialog.modal('hide');
          //         }
          // }
        });
        var indSeqNo = "";
        var title = "";
        if(type==="c"){
            indSeqNo = $scope.formData.completecyProfile.companyCoreValue[index].ind_seqno;
            title = $scope.formData.completecyProfile.companyCoreValue[index].core_value;
        }else if(type==="d"){
            indSeqNo = $scope.formData.completecyProfile.departmentCoreValue[index].core_value;
            title = $scope.formData.completecyProfile.departmentCoreValue[index].core_name;
        }
        dialog.init(function () {
          var HEADERS = { 'Content-Type': 'application/x-www-form-urlencoded' };
          var URL_BASE_MAINTAIN = URLRRCSTANDARDSRV + '?service=ui.pms.PmsPMFormMaintenanceSvc';
          var URL_LOAD_INDICATOR_DETAIL = URL_BASE_MAINTAIN + '&part=maintenance_load_indicator_detail';
    
          // var jobBrand = $scope.formData.appraiseeDetail.jobBrand;
          $scope.dtlInd = {};
          var params = { "indSeqNo": indSeqNo, "jobBrand": jobBrand };
          $http.get(URL_LOAD_INDICATOR_DETAIL, { "params": params, headers: HEADERS }).success(function (response) {
            $scope.dtlInd = response.data;
//            console.log($scope.dtlInd);
            var html = '<h4>' + title + '</h4><div><p><strong>Definition</strong></p><p>' + ($scope.dtlInd.definition ? $scope.dtlInd.definition : '') + '</p></div>' +
              '<div><p><strong>Description</strong></p>';
            if ($scope.dtlInd.description1 && $scope.dtlInd.description1 != null) {
              html += '<p>' + $scope.dtlInd.description1 + '</p>';
            }
            if ($scope.dtlInd.description2 && $scope.dtlInd.description2 != null) {
              html += '<p>' + $scope.dtlInd.description2 + '</p>';
            }
            if ($scope.dtlInd.description3 && $scope.dtlInd.description3 != null) {
              html += '<p>' + $scope.dtlInd.description3 + '</p>';
            }
            html += '</div></div>';
            dialog.find('.bootbox-body').html(html);
          }).error(function(err){
              dialog.find('.bootbox-body').html(err);
          });
          // setTimeout(function(){
          //     dialog.find('.bootbox-body').html('I was loaded after the dialog was shown!');
          // }, 3000);
        });
    };
    
    $scope.departmentChange = function(){
        for(var i in $scope.formData.completecyProfile.departmentCoreValue){
            var id = parseInt($scope.formData.completecyProfile.departmentCoreValue[i].core_value);
            for(var j in $scope.formData.departmentCoreValues){
                var _id = parseInt($scope.formData.departmentCoreValues[j].id);
                if(id==_id){
                    $scope.formData.completecyProfile.departmentCoreValue[i].core_name = $scope.formData.departmentCoreValues[j].value;
                }
            }
        }
        
    };
    
    $scope.addDepartmentCore = function() {
        if($scope.formData.completecyProfile.departmentCoreValue.length>=3){
            return;
        }
        if(defaultDepartmentCoreValues==0){
            defaultDepartmentCoreValues = $scope.formData.departmentCoreValues[0].id;
        }
        $scope.formData.completecyProfile.departmentCoreValue.push(
            {core_value: defaultDepartmentCoreValues,
            core_name: defaultDepartmentCoreName,
            supervisor_comment: "", 
            weightage: 0, 
            self_rating: 0, 
            rating: 0,
            total_rating: 0}
        );
    };
    $scope.removeDepartmentCore = function (index) {
        $scope.formData.completecyProfile.departmentCoreValue.splice(index, 1);
    }
    
    $scope.changeCompanySelfRating = function(index){
        var weightage = parseFloat($scope.formData.completecyProfile.companyCoreValue[index].weightage);
        var self_rate = parseFloat($scope.formData.completecyProfile.companyCoreValue[index].self_rating);
        $scope.formData.completecyProfile.companyCoreValue[index].total_rating = self_rate;
        $scope.formData.completecyProfile.companyCoreValue[index].rating = (weightage*self_rate)/100;
    };
    
    $scope.changeDepartmentSelfRating = function(index){
        var weightage = parseFloat($scope.formData.completecyProfile.departmentCoreValue[index].weightage);
        var self_rate = parseFloat($scope.formData.completecyProfile.departmentCoreValue[index].self_rating);
        $scope.formData.completecyProfile.departmentCoreValue[index].total_rating = self_rate;
        $scope.formData.completecyProfile.departmentCoreValue[index].rating = (weightage*self_rate)/100;
    };
    $scope.overallCoreValueWeightage = function () {
      var totRate = 0.0;
    totRate = parseInt($scope.formData.completecyProfile.total_company_weightage);
    var departmentWeightage = 0;
    $scope.formData.completecyProfile.total_department_weightage = 0;
      angular.forEach($scope.formData.completecyProfile.departmentCoreValue, function (item) {
          if(item.weightage==''){
              item.weightage = 0;
          }
          departmentWeightage = parseInt(item.weightage);
          item.weightage = departmentWeightage;
          totRate += departmentWeightage;
          $scope.formData.completecyProfile.total_department_weightage += departmentWeightage;
          
        var self_rate = parseFloat(item.self_rating);
        item.rating = (departmentWeightage*self_rate)/100;
      });
      $scope.formData.completecyProfile.total_weightage = totRate;
      $scope.formData.completecyProfile.total_rate = (totRate/100)*$scope.formData.completecyProfile.total_rate;
      return totRate;
    };

    $scope.overallCoreValueRate = function () {
      var totRate = 0.0;
      angular.forEach($scope.formData.completecyProfile.companyCoreValue, function (item) {
        totRate += parseFloat(item.rating);
      });
      
      angular.forEach($scope.formData.completecyProfile.departmentCoreValue, function (item) {
          totRate += parseFloat(item.rating);
      });
      
      $scope.formData.completecyProfile.total_rate = ($scope.formData.completecyProfile.total_weightage/100)*totRate;
      return $scope.formData.completecyProfile.total_rate;
    };

    $scope.overallRate = function () {
        var totRate = 0.0;
        totRate = ($scope.formData.completecyProfile.total_rate*100)/5;
      return totRate;
    };
}]);

appModule.controller("pmformTab3Controller", ["$rootScope", "$scope", "$http", "$location", 
        function($rootScope, $scope, $http, $location) {
 
    var wizard_president_directive_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_president_directive';
    var wizard_performance_progress_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_performance_progress';
    var wizard_overall_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_overall';
    var wizard_counting_signing_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_counting_signing';
    
    $scope.formData.action = "";
//    $scope.formData = {};
    var defaultBSC = 0;
    if($scope.formData.presidentDirective.presidentDirective.length<1){
        displayOverlay("body");
        //set president_directive
        $http({
            method: 'GET',
            url: wizard_president_directive_url,
            //data: $(element).serialize() ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data, status, headers, config) {
            $scope.formData.presidentDirective.presidentDirective = [];
            $scope.formData.presidentDirective.bscPerspective = [];
            if(data.valid==="V"){
                var president= data.indicator_presient;
                var total_president_weightage = 0;
                var total_president_rate = 0;
                for(var i in president){
                    var weightage = parseFloat(president[i].weightage);
                    var rating = parseFloat(president[i].rating);
                    var sum_rate = (weightage*rating)/100;
                    $scope.formData.presidentDirective.presidentDirective.push(
                        {bsc_perspective: president[i].bscName, 
                        company_goal: president[i].name, 
                        rating_0: president[i].slab0, 
                        rating_1: president[i].slab1, 
                        rating_2: president[i].slab2, 
                        rating_3: president[i].slab3, 
                        rating_4: president[i].slab4, 
                        rating_5: president[i].slab5, 
                        weightage: weightage, 
                        result: president[i].result, 
                        rating:rating, 
                        sum_rate: sum_rate}
                    );
                    total_president_weightage += weightage;
                    total_president_rate += sum_rate;
                }
                $scope.formData.presidentDirective.total_president_weightage = total_president_weightage;
                $scope.formData.presidentDirective.total_president_rate = total_president_rate;
                
                var bsc = data.indicator_bsc;
                defaultBSC = bsc[0].seqNo;
                for(var i in bsc){
                    $scope.formData.presidentDirective.bscPerspective.push(
                     {id: bsc[i].seqNo, value: bsc[i].name}
                    );
                }
                $scope.formData.presidentDirective.individualKPIs = [
                    {bsc_perspective: defaultBSC, 
                    individual_goal: "", 
                    rating_0: 0, 
                    rating_1: 0, 
                    rating_2: 0, 
                    rating_3: 0, 
                    rating_4: 0, 
                    rating_5: 0, 
                    weightage: 0, 
                    result: 0, 
                    rating:0, 
                    sum_rate: 0}
                ];
            }else{
                $rootScope.showErrorAlert('N', data.msg);
            }
            removeOverlay();
        }).error(function (data, status, headers, config) {
            removeOverlay();
            // handle error things
            console.log(data,status, headers, config);
        });
    }else{
        var total_weightage = 0.0;
        var total_rate = 0.0;
        var president = $scope.formData.presidentDirective.presidentDirective;
        $scope.formData.presidentDirective.total_president_weightage = 0.0;
        $scope.formData.presidentDirective.total_president_rate = 0.0;
        for(var i in president){
            var weightage = parseFloat(president[i].weightage);
            var rating = parseFloat(president[i].rating);
            var sum_rate = (weightage*rating)/100;
            total_weightage += weightage;
            total_rate += sum_rate;
        }
        $scope.formData.presidentDirective.total_president_weightage = total_weightage;
        $scope.formData.presidentDirective.total_president_rate = total_rate;
    }
    
$scope.showCoreValueInfo = function () {
  bootbox.dialog({
			title: "Information",
			message: '<div class="media"><div class="media-body"><h4 class="text-thin">You can also use <strong>html</strong></h4>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.</div></div>',
			buttons: {
				confirm: {
					label: "OK"
				}
			}
		});
}

    $scope.addIndividualKPIs = function() {
//        if($scope.formData.presidentDirective.individualKPIs.length>=3){
//            return;
//        }
        if(defaultBSC==0){
            defaultBSC = $scope.formData.presidentDirective.bscPerspective[0].id;
        }
        $scope.formData.presidentDirective.individualKPIs.push(
            {bsc_perspective: defaultBSC, individual_goal: "", rating_0: 0, rating_1: 0, 
            rating_2: 0, rating_3: 0, rating_4: 0, rating_5: 0, weightage: 0, result: 0, rating:0, 
            sum_rate: 0}
        );
    };
    $scope.removeIndividualKPIs = function (index) {
        $scope.formData.presidentDirective.individualKPIs.splice(index, 1);
    }
    
    $scope.showAllRate = false;
    $scope.togglerShowAllRate = function () {
       $scope.showAllRate = !$scope.showAllRate; 
    };

    $scope.overallPresidentRate = function () {
        return ($scope.formData.presidentDirective.total_president_rate*100)/5;
    };

    $scope.overallIndividalRate = function () {
//        var totRate = 0.0;
//        var weightage = parseFloat($scope.formData.presidentDirective.total_individual_weightage);
        var rate = parseFloat($scope.formData.presidentDirective.total_individual_rate);
//        var overallRate = (weightage*rate)/100;
//        totRate = (overallRate*100)/5;
        return (rate*100)/5;
    };

    $scope.individualRate = function () {
        return $scope.formData.presidentDirective.total_individual_rate;
    };
    
    $scope.changeIndividalRating = function(){
        var sum_rate = 0.0;
        angular.forEach($scope.formData.presidentDirective.individualKPIs, function (item) {
            var rate = parseFloat(item.rating);
            var weightage = parseFloat(item.weightage);
            sum_rate += (weightage*rate)/100;
            item.sum_rate = sum_rate;
        });
        
        $scope.formData.presidentDirective.total_individual_rate = sum_rate;
    };
    
    $scope.overallIndividalWeightage = function () {
        var totRate = 0.0;
        var weightage = 0;
        $scope.formData.presidentDirective.total_individual_weightage = 0;
        angular.forEach($scope.formData.presidentDirective.individualKPIs, function (item) {
            if(item.weightage==''){
                item.weightage = 0;
            }
            weightage = parseInt(item.weightage);
            item.weightage = weightage;
            totRate += weightage;
            $scope.formData.presidentDirective.total_individual_weightage += weightage;
            
//            if(totRate>=100){
//                item.weightage = -((totRate-weightage)-100);
//                if(item.weightage<0){
//                    item.weightage = 0;
//                }
//            }
        });
        
        var sum_rate = 0.0;
        angular.forEach($scope.formData.presidentDirective.individualKPIs, function (item) {
            var rate = parseFloat(item.rating);
            var weightage = parseFloat(item.weightage);
            sum_rate += (weightage*rate)/100;
            item.sum_rate = sum_rate;
        });
        
        $scope.formData.presidentDirective.total_individual_rate = sum_rate;
        return totRate;
    };

// Get Department Core Value Description
$scope.getDepartmentCoreValueDesc = function (id) {
    var foundItem = $scope.formData.departmentCoreValues.filter(function(item) {
        return item.id == id;
    });
    if (foundItem.length > 0) {
        return foundItem[0].value;
    }
    return "";
};

// Get BSC BSC Value Description
$scope.getBSCValueDesc = function (id) {
    var foundItem = $scope.formData.bscPerspective.filter(function(item) {
        return item.id == id;
    });
    if (foundItem.length > 0) {
        return foundItem[0].value;
    }
    return "";
};
// Check whether Apprisee is submit PM Form?
$scope.isAppriseeSubmitPMForm = function (){
    return false;
};
// Check whether Level1 is approved?
$scope.isSupervisorLevel1Approve = function (){
    return false;
};
// Check whether Level2 is approved?
$scope.isSupervisorLevel2Approve = function (){
    return false;
};
}]);

appModule.controller("pmformTab4Controller", ["$scope", "$http", "$location", 
        function($scope, $http, $location) { 
    $scope.formData.action = "";
    var wizard_performance_progress_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_performance_progress';
    var wizard_counting_signing_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_counting_signing';
}]);

appModule.controller("pmformTab5Controller", ["$rootScope", "$scope", "$http", "$location", "$filter",
        function($rootScope, $scope, $http, $location, $filter) {
    var wizard_overall_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_overall';
    $scope.formData.action = "";
    
    if(!$scope.formData.overallPerformance.flag){
        displayOverlay("body");
        //set overall
        $http({
            method: 'GET',
            url: wizard_overall_url,
            //data: $(element).serialize() ,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data, status, headers, config) {
            $scope.dateJoin($scope.formData.appraiseeDetail.date_joined);
            
            if(data.valid==="V"){
                var pmOverall = data.pm_overall;
                if(pmOverall!=null && pmOverall.length>0){
                    var com_overall = pmOverall[0].comRatingPercent;
                    var pd_overall = pmOverall[0].presidentRatingPercent;
                    var ind_overall = pmOverall[0].individualRatingPercent;
                    var over_overall = pmOverall[0].overallRatingPercent;
                    $scope.formData.overallPerformance.fullYear = pmOverall[0].pmYear;
                    $scope.formData.overallPerformance.competency.period1 = com_overall;
                    $scope.formData.overallPerformance.president.period1 = pd_overall;
                    $scope.formData.overallPerformance.overall.period1 = ind_overall;
                    $scope.formData.overallPerformance.individual.period1 = over_overall;
                    if(pmOverall.length>1){
                        com_overall = (parseFloat(com_overall)+parseFloat(pmOverall[1].comRatingPercent))/2;
                        pd_overall = (parseFloat(pd_overall)+parseFloat(pmOverall[1].presidentRatingPercent))/2;
                        ind_overall = (parseFloat(ind_overall)+parseFloat(pmOverall[1].individualRatingPercent))/2;
                        over_overall = (parseFloat(over_overall)+parseFloat(pmOverall[1].overallRatingPercent))/2;
                        $scope.formData.overallPerformance.competency.period2 = pmOverall[1].comRatingPercent;
                        $scope.formData.overallPerformance.president.period2 = pmOverall[1].presidentRatingPercent;
                        $scope.formData.overallPerformance.overall.period2 = pmOverall[1].overallRatingPercent;
                        $scope.formData.overallPerformance.individual.period2 = pmOverall[1].individualRatingPercent;
                    }
                    $scope.formData.overallPerformance.competency.overall = com_overall;
                    $scope.formData.overallPerformance.president.overall = pd_overall;
                    $scope.formData.overallPerformance.overall.overall = over_overall;
                    $scope.formData.overallPerformance.individual.overall = ind_overall;
                }
                
                var job_weightage = data.pm_job_weightage;
                
                if(job_weightage!=null){
                    $scope.formData.overallPerformance.jobWeightage = {
                        comWeightage: job_weightage.comWeightage,
                        indKPIWeightage: job_weightage.indKPIWeightage,
                        pdWeightage: job_weightage.pdWeightage,
                        overallWeightage: job_weightage.indKPIWeightage+job_weightage.pdWeightage
                    };
                }
                $scope.formData.overallPerformance.flag = true;
            }else{
                $rootScope.showErrorAlert('N', data.msg);
            }
            removeOverlay();
        }).error(function (data, status, headers, config) {
            removeOverlay();
            // handle error things
            console.log(data,status, headers, config);
        });
    }
    
    $scope.dateJoin = function(d){
        if(d!=""){
//            var tmps = d.split("/");
//            var sysDate = new Date();
//            var date = new Date();
//            date.setYear(parseInt(tmps[2]));
//            date.setMonth(parseInt(tmps[1]));
//            date.setDate(parseInt(tmps[0]));
//            var timeDiff = Math.abs(sysDate.getTime() - date.getTime());
//            var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24))/365; 
//            var totalDate = $filter('number')(diffDays, 1);
//            var tmps = totalDate.split("\.");
            $scope.formData.overallPerformance.year = $scope.formData.appraiseeDetail.expYear;
            $scope.formData.overallPerformance.month = $scope.formData.appraiseeDetail.expMonth;
        }else{
            $scope.formData.overallPerformance.year = 0;
            $scope.formData.overallPerformance.month = 0;
        }
    };
    
    $scope.checkStatus = function(percent){
        var res = "-";
        if(percent==""||percent=="-"){
            return res;
        }
        if(percent<=49){
            res = "Poor";
        }else if(percent>=50&&percent<=59){
            res = "Fair";
        }else if(percent>=60&&percent<=69){
            res = "Good";
        }else if(percent>=70&&percent<=84){
            res = "Very Good";
        }else{
            res = "Outstanding";
        }
        return res;
    };
    
    $scope.showMsg = function(val){
        if(val=="-"||val==""){
            return "-";
        }else{
            return $filter('number')(parseInt(val), 2)+"%";
        }
    };
}]);

appModule.controller("pmformTab6Controller", ["$scope", "$http", "$location", 
        function($scope, $http, $location) {
    $scope.formData.action = "";
}]);

appModule.controller("pmformsummarizeController", ["$rootScope", "$scope", "$http", "$location", '$filter',
        function($rootScope, $scope, $http, $location, $filter) {
    displayOverlay("body");
    var wizard_get_data_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_get_data';
    $scope.formData = {};
    $http({
        method: 'POST',
        url: wizard_get_data_url,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data, status, headers, config) {
        if(data.valid==="V"){
            var formData = data.data!=null?angular.fromJson(data.data):[];
            angular.copy(formData, $scope.formData);
        }else{
            $rootScope.showErrorAlert('N', data.msg);
        }
        removeOverlay();
    }).error(function (data, status, headers, config) {
        removeOverlay();
    });
    
    $scope.getDepartmentCoreValueDesc = function(id){
        for(var i in $scope.formData.departmentCoreValues){
            var _id = $scope.formData.departmentCoreValues[i].id;
            id = parseInt(id);
            if(_id==id){
                return $scope.formData.departmentCoreValues[i].value;
            }
        }
    };
    
    $scope.getBSCValueDesc = function(id){
        for(var i in $scope.formData.presidentDirective.bscPerspective){
            var _id = $scope.formData.presidentDirective.bscPerspective[i].id;
            id = parseInt(id);
            if(_id==id){
                return $scope.formData.presidentDirective.bscPerspective[i].value;
            }
        }
    };
    
    $scope.checkStatus = function(percent){
        var res = "-";
        if(percent==""||percent=="-"){
            return res;
        }
        if(percent<=49){
            res = "Poor";
        }else if(percent>=50&&percent<=59){
            res = "Fair";
        }else if(percent>=60&&percent<=69){
            res = "Good";
        }else if(percent>=70&&percent<=84){
            res = "Very Good";
        }else{
            res = "Outstanding";
        }
        return res;
    };
    
    $scope.showMsg = function(val){
        if(val=="-"||val==""){
            return "-";
        }else{
            return $filter('number')(parseInt(val), 2)+"%";
        }
    };
}]);