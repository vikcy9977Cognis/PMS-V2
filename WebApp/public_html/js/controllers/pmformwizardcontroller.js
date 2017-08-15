
appModule.controller("pmWizardController", function($rootScope, $scope, $http, $location, $modal) {
  $scope.tabCount = 6;
  $scope.currentTab = 1;
  $scope.percent = 0;
  $scope.width = 0;
  $scope.left = 0;
 
    $scope.item = {};
    $scope.formData = {};
    $scope.formData.action = "";
    // Appraisee detail
    $scope.formData.appraiseeDetail = {};
    // Weightage by Job Band data
    $scope.formData.weightageByJobBand = [];
    // Rating Scale data
    $scope.formData.ratingScale = [
      {rating: 5, definition: "Exceptional"}
      ,{rating: 4, definition: "Exceeded Expectations"}
      ,{rating: 3, definition: "Meets Expectations"}
      ,{rating: 2, definition: "Improvement Needed"}
      ,{rating: 1, definition: "Unacceptable"}
      ,{rating: 0, definition: "Under Performed"}
    ];    
    
    // Rating Values data
    $scope.formData.ratingValues = [0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0];
    
    // Completency Profile data
    $scope.formData.completecyProfile = {
        total_company_weightage: 0,
        total_department_weightage: 0,
        total_weightage: 0,
        total_rate: 0,
        overall_rate: 0
    };
    $scope.formData.completecyProfile.companyCoreValue = [];
    $scope.formData.completecyProfile.departmentCoreValue= [];
    // Department core value list data
    $scope.formData.departmentCoreValues = [];
    
    $scope.totalWeightage = 0;
    $scope.totalRate = 0.0;
    
    ///president_directive
    $scope.formData.presidentDirective = {
        total_president_weightage: 0,
        total_president_rate: 0,
        total_individual_weightage: 0,
        total_individual_rate: 0
    };
    // BSC Perspective
    $scope.formData.presidentDirective.bscPerspective = [];     
    // President Directive data
    $scope.formData.presidentDirective.presidentDirective = [];
    // Individual KPIs data
    $scope.formData.presidentDirective.individualKPIs = [];    
    
    $scope.formData.performanceProgress = {
        reason: "",
        barriers: "",
        indicate_learning: "",
        appraisee_comment: ""
    };
    
    $scope.formData.overallPerformance = {
        comment: "",
        fullYear: 0,
        month: 0,
        year: 0,
        flag: false
    };
    $scope.formData.overallPerformance.jobWeightage = {
        comWeightage: 0,
        indKPIWeightage: 0,
        pdWeightage: 0,
        overallWeightage: 0
    };
    $scope.formData.overallPerformance.competency = {
        period1: "-",
        period2: "-",
        overall: 0
    };
    $scope.formData.overallPerformance.president = {
        period1: "-",
        period2: "-",
        overall: 0
    };;
    $scope.formData.overallPerformance.overall = {
        period1: "-",
        period2: "-",
        overall: 0
    };;
    $scope.formData.overallPerformance.individual = {
        period1: "-",
        period2: "-",
        overall: 0
    };;

    $scope.formData.performanceScaleDef = [
        {title: "Outstanding (85% & above)", definition: "Exceeds all expectations / makes positive contributions / adds value beyond scope"}
        ,{title: "Very Good (70% - 84%)", definition: "Meets all expectations / exceeds in most areas / adds value beyond scope"}
        ,{title: "Good (60% - 69%)", definition: "Meets all expectations / exceeds in some areas"}
        ,{title: "Fair (50% - 59%)", definition: "Just able to meet expectations & required standards"}
        ,{title: "Poor (49% & below)", definition: "Unable to meet expectations & required standards"}
    ];
    
    $scope.formData.counterSigning = {
        comment: ""
    };
    
    $scope.isShowAgreement = false;
 
  // Move to next tab
  $scope.gotoNextTab = function() {
    $rootScope.hideErrorAlert();
    if($scope.currentTab==2){        
        var item = $scope.formData.completecyProfile.departmentCoreValue;
        if(item!=null&&item.length>0){
            for(var i in item){
                var id = item[i].core_value;
                var name = item[i].core_name;
                for(var j in item){
                    var _id = item[j].core_value;
                    if(id==_id && j!=i){
                        $rootScope.showErrorAlert('N', "PM form not allow to insert, Duplicate Department Core Value name: " + name);
                        return;
                    }
                }
            }
        }
        
        var totalWeightage = parseFloat($scope.formData.completecyProfile.total_weightage);
        if(totalWeightage!=100){
            $rootScope.showErrorAlert('N', "Total Weightage(%) of Company and Department core value must be equal to 100%");
            return;
        }
    }
    if($scope.currentTab==3){
        var individualWeightage = parseFloat($scope.formData.presidentDirective.total_individual_weightage);
        if(individualWeightage!=100){
            $rootScope.showErrorAlert('N', "Total Weightage(%) of Individual KPI must be equal to 100%");
            return;
        }
    }
    if ($scope.currentTab < $scope.tabCount ) {
      $scope.currentTab +=1;
    }
    $scope.calulatePercent();
  }
  // Move to previous tab
  $scope.gotoPreviousTab = function() {
        $rootScope.hideErrorAlert();
    if ($scope.currentTab > 1 ) {
      $scope.currentTab -= 1;
    }
     $scope.calulatePercent();
  }
  // Return current tab content
  $scope.getCurrentTabContent = function () {
    if(!angular.equals({}, $scope.formData)){
        if($scope.formData.action==="cancel"){
            $scope.currentTab = $scope.tabCount;
        }
    }
    return "views/pm-form-tab" + $scope.currentTab + ".html";
  }
   // Return  tab content at specify page number
  $scope.getTabContent = function (pageNo) {
    return "views/pm-form-tab" + pageNo + ".html";
  }
  // Save new PM form
  $scope.savePMForm = function () {//Editing
        $rootScope.hideErrorAlert();
    displayOverlay("body");
    var wizard_set_data_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_set_data';
    $scope.formData.action = "save";
    $http({
        url: wizard_set_data_url,
        method: "POST",
        data: JSON.stringify($scope.formData),
        headers: {'Content-Type': 'application/json'}
    }).success(function (data, status, headers, config) {
        if(data.valid==="V"){
            //$location.path("/showSummarizePM");
            var wizard_confirm_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_confirm';
            $http({
                method: 'POST',
                url: wizard_confirm_url,
                headers: {'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
                if(data.valid==="V"){
                    $location.path("/savePMSuccess");
                }else{
                    $rootScope.showErrorAlert('N', data.msg);
                }
                removeOverlay();
            }).error(function (data, status, headers, config) {
                removeOverlay();
            });
        }else{
            removeOverlay();
        }
    }).error(function (data, status, headers, config) {
        removeOverlay();
    });
  }
  
  // Save new PM form
  $scope.submitPMForm = function () {
        $rootScope.hideErrorAlert();
        var template = '<div class="modal-body">Are you sure you want to submit this PM form?</div>';
        template += '<div class="modal-footer">';
        template += '<button ng-click="okClick()" class="finish btn btn-success">OK</button>';
        template += '<button ng-click="cancelClick()" class="finish btn btn-default">Cancel</button>';
        template += '</div>';
        var modal = $modal.open({
            template: template,
            backdrop: 'static',
            backdropClass: 'pms-backdrop-ext',
            keyboard: false,
            //controller: 'submitCtrl',
            //scope: $scope,
            size: "sm",
            controller: function($rootScope, $scope){
                $scope.okClick = function () {
                    modal.close();
                    $rootScope.submitOkClick();
                };
                $scope.cancelClick = function () {
                    modal.close();
                };
            }
        });
  };
  
  $rootScope.submitOkClick = function(){
      displayOverlay("body");
        var wizard_set_data_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_set_data';
        $scope.formData.action = "submit";
        $http({
            method: 'POST',
            url: wizard_set_data_url,
            data: JSON.stringify($scope.formData),
            headers: {'Content-Type': 'application/json'}
        }).success(function (data, status, headers, config) {
            if(data.valid==="V"){
                //$location.path("/showSummarizePM");
                var wizard_confirm_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_confirm';
                $http({
                    method: 'POST',
                    url: wizard_confirm_url,
                    headers: {'Content-Type': 'application/json'}
                }).success(function (data, status, headers, config) {
                    if(data.valid==="V"){
                        $location.path("/savePMSuccess");
                    }else{
                        $rootScope.showErrorAlert('N', data.msg);
                    }
                    removeOverlay();
                }).error(function (data, status, headers, config) {
                    removeOverlay();
                });
            }else{
                removeOverlay();
            }
        }).error(function (data, status, headers, config) {
            removeOverlay();
        });
  };
  
  // Confirm Save new PM form
  $scope.confirmSubmitPMForm = function () {
        $rootScope.hideErrorAlert();
    displayOverlay("body");
    var wizard_confirm_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_confirm';
    $http({
        method: 'POST',
        url: wizard_confirm_url,
        headers: {'Content-Type': 'application/json'}
    }).success(function (data, status, headers, config) {
        if(data.valid==="V"){
            $location.path("/savePMSuccess");
        }else{
            $rootScope.showErrorAlert('N', data.msg);
        }
        removeOverlay();
    }).error(function (data, status, headers, config) {
        removeOverlay();
    });
  };
  
  $scope.cancelClick = function(){
        $rootScope.hideErrorAlert();
    displayOverlay("body");
    var wizard_set_data_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_set_data';
    var wizard_get_data_url = URLRRCSTANDARDSRV+'?service=ui.pms.PmsWizardSvc&part=wizard_get_data';
    
    $http({
        method: 'POST',
        url: wizard_get_data_url,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data, status, headers, config) {
        if(data.valid==="V"){
            $scope.formData = data.data!=null?angular.fromJson(data.data):[];
            $scope.formData.action = "cancel";
            $http({
                url: wizard_set_data_url,
                method: "POST",
                data: JSON.stringify($scope.formData),
                headers: {'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
                if(data.valid==="V"){
                    $location.path("/noticeNewPM");
                }else{
                    $rootScope.showErrorAlert('N', data.msg);
                }
                removeOverlay();
            }).error(function (data, status, headers, config) {
                removeOverlay();
            });
        }else{
            removeOverlay();
            $rootScope.showErrorAlert('N', data.msg);
        }
    }).error(function (data, status, headers, config) {
        removeOverlay();
    });
  };
  
  $scope.successOkClick = function(){
     //   $location.url("/maintenance");      
    var newForm = jQuery('<form>', {
        'action': URLRRCSTANDARDSRV,
        'method': 'POST',
        'target': '_top'
    }).append(jQuery('<input>', {
        'name': 'service',
        'value': 'ui.pms.PmsHomeSvc',
        'type': 'hidden'
    }))
    .append(jQuery('<input>', {
        'name': 'pageAction',
        'value': 'maintenance',
        'type': 'hidden'
    }))
    .appendTo('body');
    newForm.submit();


  };
  
  $scope.calulatePercent = function() {
      $scope.percent = ($scope.currentTab/$scope.tabCount) * 100;
      $scope.width = (100/$scope.tabCount);
      $scope.left = $scope.width*($scope.currentTab -1);
  }
  // Initialize percent complete
  $scope.calulatePercent();
  $scope.isWizardMode = function () {
      return $scope.wizardMode;
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
});
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