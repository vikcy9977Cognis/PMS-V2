 function loadResetSubMystaff(event) {
  }
  
 function onPrevMgrNameClick(idx) {        }
 
appModule.controller("EmpPerformanceResultController", function($scope, $http,  State, $q ,$location, $timeout) {
var URLLOADMYPM=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadmypm';
var URLLOADINIT=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadprofile';

var URLLOADACTI=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadactivities';
var URLLOADMYSTAFF=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadmystaff';
var URLLOADCOMPANY=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadcompany';
// var URLLOADdivision=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loaddivision';
var URLLOADDEPARTMENT=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loaddepartant';
 var URLLOADSECTION=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadsection';
var URLLOADCOMPETENCY=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadcompetency';
var URLLOADKPIS=URLRRCSTANDARDSRV+'?service=ui.pms.PmsPMFormSvc&part=pmform_loadkpis';
console.log(URLLOADSECTION);
if($scope.data ==undefined){
   $scope.data = {};
}
if($scope.tablePM ==undefined){
   $scope.tablePM = {};
}
$scope.showTable=true;
$scope.findSearchListEmp = function() {
  $scope.showTable=true;
}

});
appModule.directive("pmStatusItem", function() {
});
appModule.directive("pmPagingToolbar", function() {
});
appModule.directive("pmTableView", function() {
});




