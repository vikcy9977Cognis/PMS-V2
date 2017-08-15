appModule.controller("pmFormMaintenanceController", function($rootScope, $scope, $compile, $q, $http, $location, $routeParams, $timeout, $modal, $window, $ngBootbox) {

    // console.dir($routeParams);
    $scope.empSeqNo = $routeParams.emp_seqno;
    $scope.pmHdrSeqNo = $routeParams.pmhdr_seqno;

    //fix seqNo fot test edit
    // $scope.empSeqNo = 80;
    // $scope.pmHdrSeqNo = 51;

    $scope.optionsFileUpload = {
        autoUpload: true,
        maxFileSize: 5000000,
        acceptFileTypes: /(\.|\/)(xlx?s|doc?s|pdf)$/i
    };

    //init variable form 
    $scope.formData = {};
    $scope.formData.overallPerformance = {};
    var loadData = {};
    $scope.authorize = null;

    //constant for request
    var HEADERS = { 'Content-Type': 'application/x-www-form-urlencoded' };
    var URL_BASE_MAINTAIN = URLRRCSTANDARDSRV + '?service=ui.pms.PmsPMFormMaintenanceSvc';
    var URL_GET_AUTHORIZE = URL_BASE_MAINTAIN + '&part=maintenance_get_user_authorize';
    var URL_LOAD_PM_HEADER = URL_BASE_MAINTAIN + '&part=maintenance_load_pm_header';
    var URL_LOAD_JOB_BRAND_WEIGHTAGE = URL_BASE_MAINTAIN + '&part=maintenance_load_job_brand_weightage';
    var URL_LOAD_COM_AND_DEPART_CORE = URL_BASE_MAINTAIN + '&part=maintenance_load_company_and_department_core_value';
    var URL_LOAD_PESIDENT_DIRECTIVE = URL_BASE_MAINTAIN + '&part=maintenance_load_president_directive';
    var URL_LOAD_INDIVIDUAL_KPIs = URL_BASE_MAINTAIN + '&part=maintenance_load_individual_kpi';
    var URL_LOAD_OVERALL = URL_BASE_MAINTAIN + '&part=maintenance_load_overall';
    var URL_LOAD_INDICATOR_DETAIL = URL_BASE_MAINTAIN + '&part=maintenance_load_indicator_detail';
    var URL_SAVE = URL_BASE_MAINTAIN + '&part=maintenance_edit_save';
    var URL_SUBMIT = URL_BASE_MAINTAIN + '&part=maintenance_edit_submit';
    var URL_APPROVE_REJECT = URL_BASE_MAINTAIN + '&part=maintenance_edit_approve_reject';
    var URL_GET_ATTACH_FILE = URL_BASE_MAINTAIN + '&part=maintenance_get_attach_file';
    var URL_ADD_ATTACH_FILE = URL_BASE_MAINTAIN + '&part=maintenance_add_attach_file';
    var URL_REMOVE_ATTACH_FILE = URL_BASE_MAINTAIN + '&part=maintenance_remove_attach_file';
    var URL_UPLOAD_FILE = URL_BASE_MAINTAIN + '&part=maintenance_insert_attach_file';
    var DOWNLOAD_FILE = URL_BASE_MAINTAIN + '&part=maintenance_download_attach_file';

    $scope.percent = 0;
    $scope.width = 0;
    $scope.left = 0;
    $scope.isShowAgreement = false;

    $scope.ratingScaleList = [
        { rating: 5, definition: "Exceptional" },
        { rating: 4, definition: "Exceeded Expectations" },
        { rating: 3, definition: "Meets Expectations" },
        { rating: 2, definition: "Improvement Needed" },
        { rating: 1, definition: "Unacceptable" },
        { rating: 0, definition: "Under Performed" }
    ];

    $scope.ratingValues = [0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0];


    $scope.getAuthorize = function() {
        var filterData = { empSeqNo: $scope.empSeqNo, pmHDRSeqNo: $scope.pmHdrSeqNo };

        return $http.get(URL_GET_AUTHORIZE, { params: filterData, headers: HEADERS }).success(function(response) {
            // console.dir(response.data);
            $scope.authorize = response.data;
            // return response;
        }).error(function(response) {
            // console.error(response);
            $scope.authorize = null;
        });
    };


    $scope.loadPMHeader = function() {
        var filterData = { empSeqNo: $scope.empSeqNo, pmHDRSeqNo: $scope.pmHdrSeqNo };

        return $http.get(URL_LOAD_PM_HEADER, { params: filterData, headers: HEADERS }).success(function(response) {
            // console.dir(response.data);
            $scope.formData.appraiseeDetail = response.data;
            loadData.appraiseeDetail = angular.copy(response.data);
            // $scope.formData.appraiseeDetail.performanceAppraisal = $scope.formData.appraiseeDetail.pmPeriod +'H'+ ' ' + 'Y' + $scope.formData.appraiseeDetail.pmYear;
            // return response;

        }).error(function(response) {
            // console.error(response);
            $scope.formData.appraiseeDetail = {};
        });
    };

    $scope.loadJobBrandWeightage = function() {
        return $http.get(URL_LOAD_JOB_BRAND_WEIGHTAGE, { headers: HEADERS }).success(function(response) {
            // console.dir(response.data);
            $scope.formData.jobBrandWeightageList = response.data;
            loadData.jobBrandWeightageList = angular.copy(response.data);
            // return response;
        }).error(function(response) {
            // console.error(response);
            $scope.formData.jobBrandWeightageList = [];
        });
    };

    $scope.loadCompanyAndDepartCore = function() {
        var filterData = { empSeqNo: $scope.empSeqNo, pmHDRSeqNo: $scope.pmHdrSeqNo };

        return $http.get(URL_LOAD_COM_AND_DEPART_CORE, { params: filterData, headers: HEADERS }).success(function(response) {
            // console.dir(response.data);
            $scope.formData.departmentCoreValues = response.data.departmentIndcators;
            $scope.formData.companyCoreList = response.data.companyCoreValue;
            $scope.formData.departmentCoreList = response.data.departmentCoreValue;
            loadData.companyCoreList = angular.copy(response.data.companyCoreValue);
            loadData.departmentCoreList = angular.copy(response.data.departmentCoreValue);
            // return response;
        }).error(function(response) {
            // console.error(response);
            $scope.formData.departmentCoreValues = [];
            $scope.formData.companyCoreList = [];
            $scope.formData.departmentCoreList = [];
            loadData.companyCoreList = [];
            loadData.departmentCoreList = [];
        });
    };

    $scope.loadPesidentDirective = function() {
        var filterData = { empSeqNo: $scope.empSeqNo, pmHDRSeqNo: $scope.pmHdrSeqNo };

        return $http.get(URL_LOAD_PESIDENT_DIRECTIVE, { params: filterData, headers: HEADERS }).success(function(response) {
            $scope.formData.presidentDirective = response.data;
            loadData.presidentDirective = angular.copy(response.data);
            // return response;
        }).error(function(response) {
            // console.error(response);
            $scope.formData.presidentDirective = [];

        });

    };

    $scope.loadIndividualKPIs = function() {
        var filterData = { empSeqNo: $scope.empSeqNo, pmHDRSeqNo: $scope.pmHdrSeqNo };

        return $http.get(URL_LOAD_INDIVIDUAL_KPIs, { params: filterData, headers: HEADERS }).success(function(response) {
            // console.dir(response.data);
            $scope.formData.individualKPIs = response.data;
            $scope.bscPerspective = response.bscIndicators;
            loadData.individualKPIs = angular.copy(response.data);
            // return response;
        }).error(function(response) {
            // console.error(response);
            $scope.formData.individualKPIs = [];
            $scope.bscPerspective = [];
        });

    };

    $scope.loadOverall = function() {
        var jobBrand = $scope.formData.appraiseeDetail.jobBrand;
        var filterData = { empSeqNo: $scope.empSeqNo, jobBrand: jobBrand };

        return $http.get(URL_LOAD_OVERALL, { params: filterData, headers: HEADERS }).success(function(response) {
            // console.dir(response.data);
            $scope.formData.overallPerformance = response.data;
            loadData.overallPerformance = angular.copy(response.data);
            if (response.data !== null && angular.isArray(response.data.overall)) {
                //var period1 = {};
                //var period2 = {};
                $scope.formData.overallPerformance.overall.period1 = {};
                $scope.formData.overallPerformance.overall.period2 = {};

                for (var i = 0; i < response.data.overall.length; i++) {
                    var overallItem = response.data.overall[i];
                    if (parseInt(overallItem.pmPeriod) == 1) {
                        period1 = overallItem;
                        $scope.formData.overallPerformance.overall.period1 = period1;
                    } else if (parseInt(overallItem.pmPeriod) == 2) {
                        period2 = overallItem;
                        $scope.formData.overallPerformance.overall.period2 = period2;
                    }
                }
                var ratePeriod1 = $scope.formData.overallPerformance.overall.period1.comRatingPercent;
                var ratePeriod2 = $scope.formData.overallPerformance.overall.period2.comRatingPercent;
                if (ratePeriod1 != null || ratePeriod2 != null) {
                    var ratingTotal = $scope.getOverallRatingPercent(ratePeriod1, ratePeriod2);
                    $scope.formData.overallPerformance.overall.comRatingPercentY = ratingTotal;
                }
                var ratePdPeriod1 = $scope.formData.overallPerformance.overall.period1.presidentRatingPercent;
                var ratePdPeriod2 = $scope.formData.overallPerformance.overall.period2.presidentRatingPercent;
                if (ratePdPeriod1 != null || ratePeriod2 != null) {
                    var ratingPdTotal = $scope.getOverallRatingPercent(ratePdPeriod1, ratePeriod2);
                    $scope.formData.overallPerformance.overall.presidentRatingPercentY = ratingPdTotal;
                }
                var rateIndPeriod1 = $scope.formData.overallPerformance.overall.period1.individualRatingPercent;
                var rateIndPeriod2 = $scope.formData.overallPerformance.overall.period2.individualRatingPercent;
                if (rateIndPeriod1 != null || rateIndPeriod2 != null) {
                    var ratingIndTotal = $scope.getOverallRatingPercent(rateIndPeriod1, rateIndPeriod2);
                    $scope.formData.overallPerformance.overall.individualRatingPercentY = ratingIndTotal;
                }
                var rateOverallPeriod1 = $scope.formData.overallPerformance.overall.period1.overallRatingPercent;
                var rateOverallPeriod2 = $scope.formData.overallPerformance.overall.period2.overallRatingPercent;
                if (rateOverallPeriod1 != null || rateOverallPeriod2 != null) {
                    var ratingOverallTotal = $scope.getOverallRatingPercent(rateOverallPeriod1, rateOverallPeriod2);
                    $scope.formData.overallPerformance.overall.overallRatingPercentY = ratingOverallTotal;
                }
            }
            // return response;
        }).error(function(response) {
            // console.error(response);
            $scope.formData.overallPerformance = [];

        });

    };

    $scope.attachFileList = [];

    $scope.getAttachFile = function() {
        // var filterData = { empSeqNo: $scope.empSeqNo, pmHDRSeqNo: $scope.pmHdrSeqNo };
        var filterData = { pmHDRSeqNo: $scope.pmHdrSeqNo };

        return $http.get(URL_GET_ATTACH_FILE, { params: filterData, headers: HEADERS }).success(function(response) {
            $scope.formData.attachFile = response.data;
            loadData.attachFile = angular.copy(response.data);

        }).error(function(response) {
            // console.error(response);
            $scope.formData.attachFile = [];
            loadData.attachFile = [];
        });
    };

    function displayOverlay(appendStr) {
        $("<table id='overlay'><tbody><tr><td>" + "<i class='fa fa-spinner fa-spin '></i>" + "</td></tr></tbody></table>").css({
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
        }).appendTo(appendStr);
    }

    function removeOverlay() {
        $("#overlay").remove();
    }

    var initLoad = function() {
        var promisesInitPage = [];
        promisesInitPage.push($scope.getAuthorize());
        promisesInitPage.push($scope.loadPMHeader());

        displayOverlay("body");
        $q.all(promisesInitPage).then(function(res) {
            if ($scope.authorize.viewFlag == 'N') {
                $scope.formData = {};
                removeOverlay();
                $rootScope.showErrorAlert('N', "You do not have authorize to view this PM form.");
                return;
            }

            var promisesData = [];

            promisesData.push($scope.loadJobBrandWeightage());
            promisesData.push($scope.loadCompanyAndDepartCore());
            promisesData.push($scope.loadPesidentDirective());
            promisesData.push($scope.loadIndividualKPIs());
            promisesData.push($scope.loadIndividualKPIs());
            promisesData.push($scope.loadOverall());
            promisesData.push($scope.getAttachFile());

            $q.all(promisesData).then(function(resData) {

            }).finally(function() {
                removeOverlay();
            });

        }).finally(function() {

        });
    };

    initLoad();





    $scope.refresh = function() {
        $scope.formData = {};
        $scope.formData.overallPerformance = {};
        var loadData = {};
        $scope.authorize = null;

        var promisesInitPage = [];
        promisesInitPage.push($scope.getAuthorize());
        promisesInitPage.push($scope.loadPMHeader());

        // displayOverlay("body");
        return $q.all(promisesInitPage).then(function(res) {

            var promisesData = [];

            promisesData.push($scope.loadJobBrandWeightage());
            promisesData.push($scope.loadCompanyAndDepartCore());
            promisesData.push($scope.loadPesidentDirective());
            promisesData.push($scope.loadIndividualKPIs());
            promisesData.push($scope.loadIndividualKPIs());
            promisesData.push($scope.loadOverall());
            promisesData.push($scope.getAttachFile());
            return $q.all(promisesData).then(function(resData) {
                return resData;
            });

        });
    };

    $scope.showCoreValueInfo = function(title, indSeqNo) {
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

        dialog.init(function() {
            var HEADERS = { 'Content-Type': 'application/x-www-form-urlencoded' };
            var URL_BASE_MAINTAIN = URLRRCSTANDARDSRV + '?service=ui.pms.PmsPMFormMaintenanceSvc';
            var URL_LOAD_INDICATOR_DETAIL = URL_BASE_MAINTAIN + '&part=maintenance_load_indicator_detail';

            // var jobBrand = $scope.formData.appraiseeDetail.jobBrand;
            // var dltInd = {};
            var params = { "indSeqNo": indSeqNo, "jobBrand": jobBrand };
            $http.get(URL_LOAD_INDICATOR_DETAIL, { "params": params, headers: HEADERS }).success(function(response) {
                $scope.dtlInd = response.data;

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
            }).error(function(err) {
                dialog.find('.bootbox-body').html(err);
            });
            // setTimeout(function(){
            //     dialog.find('.bootbox-body').html('I was loaded after the dialog was shown!');
            // }, 3000);
        });


        // $scope.agreementModalInstance = $modal.open({
        //   templateUrl: 'views/modal-core-value-info.html',
        //   backdrop: 'static',
        //   backdropClass: 'pms-backdrop-ext',
        //   keyboard: true,
        //   controller: 'modalCoreValueInfoCtrl',
        //   resolve: {
        //     titleInfo: function () {
        //       return title;
        //     },
        //     indSeqNo: function () {
        //       return indSeqNo;
        //     },
        //     jobBrand: function () {
        //       return jobBrand;
        //     }
        //   }
        // });
    };

    $scope.getIndNameBySeqno = function(indSeqNo) {
        if (!angular.isArray($scope.formData.departmentCoreValues)) {
            return '';
        }
        // var ind = $scope.formData.departmentCoreValues.find(function(item){
        //       return item.seqNo == indSeqNo ;
        // }); fix IE BUG
        var ind = null;
        for (var i = 0; i < $scope.formData.departmentCoreValues.length; i++) {
            var item = $scope.formData.departmentCoreValues[i];
            if (item.seqNo == indSeqNo) {
                ind = item;
                break;
            }
        }
        return ind.name;
    };


    $scope.getOverallRatingPercent = function(rating1, rating2) {
        // if($scope.formData.overallPerformance.overall){
        var period1 = parseFloat(rating1);
        var period2 = parseFloat(rating2);

        // var total = period1 != undefined && period1 != null && period1 != "" ?period1:null;
        var total = !isNaN(period1) ? period1 : null;
        // total = period2  ? total? (total+period2)/2  :period2  : total ;
        // if(period2 != undefined && period2 != null && period2 != "" ){
        if (!isNaN(period2)) {
            if (total != null) {
                total = (total + period2) / 2;
            } else {
                total = period2;
            }
        }
        return total;
        // }
    };




    $scope.getEditContent = function(pageNo) {
        return "views/pm-form-edit" + pageNo + ".html";
    };

    $scope.isWizardMode = function() {
        return $scope.wizardMode;
    };

    $scope.checkDisplayAgreement = function() {
        if (!$scope.isShowAgreement) {
            $scope.displayAgreementModal();
        }
    };


    $scope.totalDepartmentWeightage = function() {
        var total = null;
        if ($scope.formData.departmentCoreList != null) {
            for (var i = 0; i < $scope.formData.departmentCoreList.length; i++) {
                var item = $scope.formData.departmentCoreList[i];
                if (!isNaN(parseFloat(item.indWeightPercentage))) {
                    if (total == null) {
                        total = 0;
                    }
                    total += parseFloat(item.indWeightPercentage);
                }
            }
        }
        return total;
    };

    $scope.totalWeightIndividual = function() {
        var total = null;
        if (angular.isArray($scope.formData.individualKPIs)) {
            angular.forEach($scope.formData.individualKPIs, function(item, idx) {
                if (!isNaN(parseFloat(item.indWeightPercentage))) {
                    if (!isNaN(parseFloat(item.indWeightPercentage))) {
                        if (total == null) {
                            total = 0;
                        }
                        total += parseFloat(item.indWeightPercentage);
                    }
                }
            });
        }
        return total;
    };

    $scope.totalWeightPD = function() {
        var total = null;
        if (angular.isArray($scope.formData.presidentDirective)) {
            angular.forEach($scope.formData.presidentDirective, function(item, idx) {
                if (!isNaN(parseFloat(item.indWeightPercentage))) {
                    if (!isNaN(parseFloat(item.indWeightPercentage))) {
                        if (total == null) {
                            total = 0;
                        }
                        total += parseFloat(item.indWeightPercentage);
                    }
                }
            });
        }
        return total;

    };


    $scope.overallCoreValueRate = function() {
        var totRate;
        if (angular.isArray($scope.formData.companyCoreList)) {
            for (var i = 0; i < $scope.formData.companyCoreList.length; i++) {
                var item = $scope.formData.companyCoreList[i];
                var percent = parseFloat(item.indWeightPercentage);
                if (isNaN(percent)) {
                    continue;
                }
                var rating = parseFloat(item.indRating);
                if (isNaN(rating) && $scope.authorize.editFlag == 'Y') {
                    var selfRating = parseFloat(item.indSelfRating);
                    if (isNaN(selfRating)) {
                        continue;
                    }
                    rating = selfRating;
                    item.indRating = selfRating;
                }
                totRate = totRate || 0.0;
                totRate += (percent * rating) / 100;

            }
        }

        if (angular.isArray($scope.formData.departmentCoreList)) {
            for (var i = 0; i < $scope.formData.departmentCoreList.length; i++) {
                var item = $scope.formData.departmentCoreList[i];
                var percent = parseFloat(item.indWeightPercentage);
                if (isNaN(percent)) {
                    continue;
                }
                var rating = parseFloat(item.indRating);
                if (($scope.authorize.approveLv1Flag == 'N' || $scope.authorize.approveLv2Flag == 'N') && !item.dtlSeqNo && $scope.authorize.editFlag != 'N') {
                    // if((isNaN(rating) || (rating <= 0 && !item.dtlSeqNo)) && $scope.authorize.editFlag != 'N'){
                    var selfRating = parseFloat(item.indSelfRating);
                    if (isNaN(selfRating)) {
                        continue;
                    }
                    rating = selfRating;
                    if ($scope.authorize.approveLv1Flag == 'N' && !item.dtlSeqNo) {
                        item.rating = rating;
                    }
                    item.indRating = selfRating;
                }
                totRate = totRate || 0.0;
                totRate += (percent * rating) / 100;

            }
        }
        var comRating = totRate ? (($scope.overallCoreValueWeightage() / 100) * totRate).toFixed(2) : totRate;
        $scope.formData.appraiseeDetail.comRating = comRating;

        return $scope.formData.appraiseeDetail.comRating;
    };

    $scope.overallRatePercent = function() {
        var totRate;
        if ($scope.formData.appraiseeDetail.comRating) {
            totRate = ($scope.formData.appraiseeDetail.comRating * 100) / 5;
        }
        // var totRate ;  
        $scope.formData.appraiseeDetail.comRatingPercent = totRate;
        return $scope.formData.appraiseeDetail.comRatingPercent;
    };

    $scope.coreValueRatingChg = function() {
        $scope.overallCoreValueRate();
        $scope.overallRatePercent();
    };

    $scope.overallCoreValueWeightage = function() {
        var totalDpmWeightage = $scope.totalDepartmentWeightage();

        if (isNaN(parseFloat($scope.formData.totalCompanyWeightage)) && isNaN(parseFloat(totalDpmWeightage))) {
            return "-";
        }
        var total = 0.0;
        if (!isNaN(parseFloat($scope.formData.totalCompanyWeightage))) {
            total += parseFloat($scope.formData.totalCompanyWeightage);
        }
        if (!isNaN(parseFloat(totalDpmWeightage))) {
            total += parseFloat(totalDpmWeightage);
        }
        return total;
    };


    var _timeout;

    $scope.chgDepartCoreValue = function(idx) {
        if (_timeout) { // if there is already a timeout in process cancel it
            $timeout.cancel(_timeout);
        }
        _timeout = $timeout(function() {
            var total = $scope.overallCoreValueWeightage();
            if (!isNaN(parseFloat(total)) && total > 100) {
                var max = 100 - (total - parseFloat($scope.formData.departmentCoreList[idx].indWeightPercentage));
                $scope.formData.departmentCoreList[idx].indWeightPercentage = max;
            }
            $scope.coreValueRatingChg();
            _timeout = null;
        }, 1200);
    };

    $scope.addDepartMentCoreValue = function() {
        if (!$scope.formData.departmentCoreList) {
            $scope.formData.departmentCoreList = [];
        }
        // var obj = { indSeqNo: '', indSelfRating: 0 };
        var obj = { indSeqNo: '', indSelfRating: 0 };
        var total = $scope.overallCoreValueWeightage();
        total = !isNaN(parseFloat(total)) ? total : 0;
        if ((100 - total) <= 10 || $scope.formData.departmentCoreList.length == 2) {
            obj.indWeightPercentage = 100 - total;
        }
        $scope.formData.departmentCoreList.push(obj);
        $scope.coreValueRatingChg();
    };

    $scope.removeDepartMentCoreValue = function(index) {
        var delObj = $scope.formData.departmentCoreList[index];
        if (delObj.dtlSeqNo && parseInt(delObj.dtlSeqNo) > 0 && angular.isArray(loadData.departmentCoreList)) {
            for (var i = 0; i < loadData.departmentCoreList.length; i++) {
                var tmp = loadData.departmentCoreList[i];
                if (delObj.dtlSeqNo == tmp.dtlSeqNo) {
                    loadData.departmentCoreList[i].del = true;
                }
            }
        }
        $scope.formData.departmentCoreList.splice(index, 1);
        $scope.coreValueRatingChg();
    };

    $scope.findIndicatorIndexByDtlSeqNo = function(list, dtlSeqNo) {
        if (angular.isArray(list)) {
            for (var i = 0; i < list.length; i++) {
                if (list[i].dtlSeqNo == dtlSeqNo) {
                    return i;
                }
            }
        }
        return -1;
    };

    $scope.findIndicatorIndexByIndSeqNo = function(list, indSeqNo) {
        if (angular.isArray(list)) {
            for (var i = 0; i < list.length; i++) {
                if (list[i].indSeqNo == indSeqNo) {
                    return i;
                }
            }
        }
        return -1;
    };

    $scope.findIndicatorListIndexByKey = function(list, key, seqNo) {
        if (angular.isArray(list)) {
            for (var i = 0; i < list.length; i++) {
                var item = list[i];
                if (item[key] == seqNo) {
                    return i;
                }
            }
        }
        return -1;
    };

    $scope.totalInvRate = function() {
        var totRate;
        if (angular.isArray($scope.formData.individualKPIs)) {
            for (var i = 0; i < $scope.formData.individualKPIs.length; i++) {
                var item = $scope.formData.individualKPIs[i];
                var percent = parseFloat(item.indWeightPercentage);
                if (isNaN(percent)) {
                    continue;
                }
                var rating = parseFloat(item.indRating);
                if (isNaN(rating)) {
                    continue;
                }
                totRate = totRate || 0.0;
                totRate += (percent * rating) / 100;
            }
        }
        var idvRating = totRate ? (($scope.totalWeightPD() / 100) * totRate).toFixed(2) : totRate;
        $scope.formData.appraiseeDetail.individualRating = idvRating;
    };

    $scope.totalInvRatePercent = function() {
        var totRate;
        if ($scope.formData.appraiseeDetail.individualRating) {
            totRate = ($scope.formData.appraiseeDetail.individualRating * 100) / 5;
        }
        $scope.formData.appraiseeDetail.individualRatingPercent = totRate;
        return $scope.formData.appraiseeDetail.individualRatingPercent;

    };

    $scope.idvRateAndPercentChg = function() {
        $scope.totalInvRate();
        $scope.totalInvRatePercent();
    };


    $scope.addIndividualKPIs = function() {
        if (!$scope.formData.individualKPIs) {
            $scope.formData.individualKPIs = [];
        }
        $scope.formData.individualKPIs.push({ indRating: 0 });
    };
    $scope.removeIndividualKPIs = function(index) {
        var delObj = $scope.formData.individualKPIs[index];
        if (delObj.dtlSeqNo && parseInt(delObj.dtlSeqNo) > 0 && angular.isArray(loadData.individualKPIs)) {
            for (var i = 0; i < loadData.individualKPIs.length; i++) {
                var tmp = loadData.individualKPIs[i];
                if (delObj.dtlSeqNo == tmp.dtlSeqNo) {
                    loadData.individualKPIs[i].del = true;
                }
            }
        }
        $scope.formData.individualKPIs.splice(index, 1);
    };

    $scope.performanceScaleDef = [
        { title: "Outstanding (85% & above)", definition: "Exceeds all expectations / makes positive contributions / adds value beyond scope" }, { title: "Very Good (70% - 84%)", definition: "Meets all expectations / exceeds in most areas / adds value beyond scope" }, { title: "Good (60% - 69%)", definition: "Meets all expectations / exceeds in some areas" }, { title: "Fair (50% - 59%)", definition: "Just able to meet expectations & required standards" }, { title: "Poor (49% & below)", definition: "Unable to meet expectations & required standards" }
    ];

    $scope.checkStatus = function(percent) {
        var res = "-";
        if (percent <= 49) {
            res = "Poor";
        } else if (percent >= 50 && percent <= 59) {
            res = "Fair";
        } else if (percent >= 60 && percent <= 69) {
            res = "Good";
        } else if (percent >= 70 && percent <= 84) {
            res = "Very Good";
        } else if (percent >= 85) {
            res = "Outstanding";
        }
        return res;
    };


    $scope.showAllRate = false;
    $scope.togglerShowAllRate = function() {
        $scope.showAllRate = !$scope.showAllRate;
    };
    
    $scope.displayAgreementModal = function() {

        $scope.agreementModalInstance = $modal.open({
            template: '<div class="modal-header"><h3 class="modal-title">Terms Of Use</h3></div>' +
                '<div class="modal-body"> <ng-include src="\'views/agreement.html\'"></ng-include></div>' +
                '<div class="modal-footer" style="text-align:  center  !important">' +
                '    <button class="btn btn-primary" ng-click="$dismiss(cancel)">Understand</button>' +
                '</div>',
            backdrop: 'static',
            backdropClass: 'pms-backdrop-ext',
            size: 'lg',
            keyboard: false
        });

        // var htmlTxt = $("#agreementModal").html();
        // bootbox.dialog({
        //   title: "Terms Of Use",
        //   message: htmlTxt,
        //   buttons: {
        //     success: {
        //       label: "Understand",
        //       className: "btn-purple",
        //       callback: function () {
        //         $scope.isShowAgreement = true;
        //       }
        //     }
        //   }
        // });

    };


    $scope.backClick = function() {
        $location.url("/maintenance");
    };

    $scope.checkValidateForm = function() {
        if ($scope.authorize.editFlag != 'N' && $scope.overallCoreValueWeightage() != 100) {
            $rootScope.showErrorAlert('N', "PM Form not allow to update ,please check Competency Profile Overall Weightage.");
            return false;
        }

        if ($scope.authorize.editFlag != 'N' && $scope.totalWeightIndividual() != 100) {
            $rootScope.showErrorAlert('N', "PM Form not allow to update ,please check IndividualKPIs Overall Weightage.");
            return false;
        }
        if ($scope.formData.departmentCoreList != null && angular.isArray($scope.formData.departmentCoreList)) {
            var indSelectedSeqNo = [];
            for (var i = 0; i < $scope.formData.departmentCoreList.length; i++) {
                var item = $scope.formData.departmentCoreList[i];
                if (item.indSeqNo == undefined || item.indSeqNo == null || item.indSeqNo == '') {
                    $rootScope.showErrorAlert('N', "PM form not allow to update,please select Department Core Value.");
                    return false;
                }

                if (indSelectedSeqNo.length > 0) {
                    for (var j = 0; j < indSelectedSeqNo.length; j++) {
                        var selIndSeqNo = indSelectedSeqNo[j];
                        if (item.indSeqNo == selIndSeqNo) {
                            var idx = $scope.findIndicatorListIndexByKey($scope.formData.departmentCoreValues, 'seqNo', item.indSeqNo);
                            $rootScope.showErrorAlert('N', "PM form not allow to update, Duplicate Department Core Value name:" + $scope.formData.departmentCoreValues[idx].name);
                            return false;
                        }
                    }
                }
                indSelectedSeqNo.push(item.indSeqNo);
            }
        }

        if ($scope.formData.individualKPIs != null && angular.isArray($scope.formData.individualKPIs)) {
            for (var i = 0; i < $scope.formData.individualKPIs.length; i++) {
                var item = $scope.formData.individualKPIs[i];
                if (item.bscSeqNo == undefined || item.bscSeqNo == null || item.bscSeqNo == '') {
                    $rootScope.showErrorAlert('N', "PM form not allow to update,please select BSC Perspective.");
                    return false;
                }
            }
        }

        return true;

    };

    $scope.saveClick = function() {
        var valid = $scope.checkValidateForm();
        if (!valid) {
            return;
        }
        var data = getChangeValue();
        if (data == null) {
            data = {};
        }

        displayOverlay("body");
        $scope.saveData(data).then(function(response) {
            var ret = response.data;
            if (ret.valid == 'N') {
                $rootScope.showErrorAlert('N', ret.msg);
                removeOverlay();
            } else {
                $rootScope.showErrorAlert('Y', 'Save successful.');
                $scope.refresh().then(function() {
                    removeOverlay();
                });
            }

        }).finally(function() {


        });
    };

    $scope.submitData = function() {
        var valid = $scope.checkValidateForm();
        if (!valid) {
            return;
        }
        var data = getChangeValue();
        if (data == null) {
            data = {};
        }

        displayOverlay("body");
        $scope.saveData(data).then(function(response) {
            var ret = response.data;
            if (ret.valid == 'N') {
                $rootScope.showErrorAlert('N', ret.msg);
                removeOverlay();
            } else {
                $http.post(URL_SUBMIT + '&empSeqNo=' + $scope.empSeqNo + '&pmHDRSeqNo=' + $scope.pmHdrSeqNo, { headers: HEADERS }).success(function(response) {
                    var retSubmit = response;
                    if (retSubmit.valid == 'N') {
                        $rootScope.showErrorAlert('N', retSubmit.msg);
                        removeOverlay();
                        // $('html, body').animate({ scrollTop: $('#page-alert').offset().top }, 'slow');
                    } else {
                        $rootScope.showErrorAlert('Y', 'Submit successful.');
                        // $('html, body').animate({ scrollTop: $('#page-alert').offset().top }, 'slow');
                        $scope.refresh().then(function() {
                            removeOverlay();
                        });
                    }

                }).error(function(response) {
                    $rootScope.showErrorAlert('N', response);
                    removeOverlay();
                    console.error(response);
                }).finally(function() {

                });
            }

        });


    };

    $scope.submitClick = function() {
        bootbox.confirm({
            message: "Are you sure you want to submit PM?",
            buttons: {
                confirm: {
                    label: 'Yes',
                    className: 'btn-success'
                },
                cancel: {
                    label: 'No',
                    className: 'btn-danger'
                }
            },
            callback: function(result) {
                if (result) {
                    $scope.submitData();
                } else {
                    bootbox.hideAll();
                }
                // console.log('This was logged in the callback: ' + result);
            }
        });


    };

    $scope.rejectData = function(level) {
        var data = { approveReject: "R" };
        if (level == 1) {
            data.mgrLv1Comment = $scope.formData.appraiseeDetail.part4Comment1;
        } else if (level == 2) {
            data.mgrLv2Comment = $scope.formData.appraiseeDetail.part5Comment1;
        }
        displayOverlay("body");
        $http.post(URL_APPROVE_REJECT + '&pmHDRSeqNo=' + $scope.pmHdrSeqNo, JSON.stringify(data), { headers: { 'Content-Type': 'application/json' } }).success(function(response) {
            var ret = response;
            if (ret.valid == 'N') {
                $rootScope.showErrorAlert('N', ret.msg);
                removeOverlay();
            } else {
                $rootScope.showErrorAlert('Y', 'Reject successful.');
                $scope.refresh().then(function() {
                    removeOverlay();
                });
            }
        }).error(function(response) {
            // console.error(response);
            $rootScope.showErrorAlert('N', response);
            removeOverlay();
        }).finally(function() {

        });
    };

    $scope.approveData = function(level) {
        var valid = $scope.checkValidateForm();
        if (!valid) {
            return;
        }
        var data = getChangeValue();
        if (data == null) {
            data = {};
        }
        displayOverlay("body");
        $scope.saveData(data).then(function(response) {
            var ret = response.data;
            if (ret.valid == 'N') {
                $rootScope.showErrorAlert('N', ret.msg);
                removeOverlay();
            } else {
                var dataApprove = { approveReject: "A" };
                if (level == 1) {
                    dataApprove.mgrLv1Comment = $scope.formData.appraiseeDetail.part4Comment1;
                } else if (level == 2) {
                    dataApprove.mgrLv2Comment = $scope.formData.appraiseeDetail.part5Comment1;
                }
                $http.post(URL_APPROVE_REJECT + '&pmHDRSeqNo=' + $scope.pmHdrSeqNo, JSON.stringify(dataApprove), { headers: { 'Content-Type': 'application/json' } }).success(function(response) {
                    var ret = response;
                    if (ret.valid == 'N') {
                        $rootScope.showErrorAlert('N', ret.msg);
                        removeOverlay();
                    } else {
                        $rootScope.showErrorAlert('Y', 'Approve successful.');
                        $scope.refresh().then(function() {
                            removeOverlay();
                        });
                    }
                }).error(function(response) {
                    // console.error(response);
                    $rootScope.showErrorAlert('N', response);
                    removeOverlay();
                });
            }

        });
    };

    $scope.approveRejectClick = function(level, flag) {
        var flagStr = "Approve";
        if (flag != 'A') {
            flagStr = "Reject";
        }
        var msg = "Are you sure you want to " + flagStr + " this PM Form?";
        bootbox.confirm({
            message: msg,
            buttons: {
                confirm: {
                    label: 'Yes',
                    className: 'btn-success'
                },
                cancel: {
                    label: 'No',
                    className: 'btn-danger'
                }
            },
            callback: function(result) {
                if (!result) {
                    bootbox.hideAll();
                    return;
                }
                if (flag != 'A') {
                    $scope.rejectData(level);
                } else {
                    $scope.approveData(level);
                }
            }
        });
    };

    function getChangeValue() {
        var data = {};
        //STEP 1: Update PM header
        // formData.appraiseeDetail.part3Comment1
        if ($scope.formData.appraiseeDetail.part3Comment1 != loadData.appraiseeDetail.part3Comment1 ||
            $scope.formData.appraiseeDetail.part3Comment2 != loadData.appraiseeDetail.part3Comment2 ||
            $scope.formData.appraiseeDetail.part3Comment3 != loadData.appraiseeDetail.part3Comment3 ||
            $scope.formData.appraiseeDetail.part3Comment4 != loadData.appraiseeDetail.part3Comment4) {
            data = data || {};
            data.pmHeader = {
                part3Comment1: $scope.formData.appraiseeDetail.part3Comment1,
                part3Comment2: $scope.formData.appraiseeDetail.part3Comment2,
                part3Comment3: $scope.formData.appraiseeDetail.part3Comment3,
                part3Comment4: $scope.formData.appraiseeDetail.part3Comment4
            };
        }
        //$scope.formData.companyCoreList // loadData.coreValues.companyCoreList
        // STEP 2: Update Company Core Value
        if ($scope.formData.companyCoreList != null && angular.isArray($scope.formData.companyCoreList)) {
            var companyCoreValues = [];
            for (var i = 0; i < $scope.formData.companyCoreList.length; i++) {
                var cpnCore = $scope.formData.companyCoreList[i];
                var oldCpn = loadData.companyCoreList[i];
                if (!angular.equals(cpnCore, oldCpn)) {
                    var obj = { dtlSeqNo: cpnCore.dtlSeqNo, indSeqNo: cpnCore.indSeqNo };
                    if ($scope.authorize.approveLv1Flag != 'N') {
                        if (cpnCore.remark != oldCpn.remark) {
                            // obj.upd = true ;
                            // obj.remark = cpnCore.remark ;
                            companyCoreValues.push(cpnCore);
                            continue;
                        }
                        if (cpnCore.indRating != oldCpn.indRating) {
                            // obj.upd = true ;
                            // obj.indRating = cpnCore.indRating ;
                            companyCoreValues.push(cpnCore);
                            continue;
                        }
                    }

                }
                if (cpnCore.indSelfRating != oldCpn.indSelfRating) {
                    // obj.upd = true ;
                    // obj.indSelfRating = cpnCore.indSelfRating ;
                    // data.companyCoreValues = data.companyCoreValues || [];
                    companyCoreValues.push(cpnCore);
                    continue;
                }
            }
            if (companyCoreValues.length > 0) {
                if (data == null) {
                    data = {};
                }
                data.companyCoreValues = companyCoreValues;
            }
        }
        // STEP 3: Insert/Update/Delete Department Core Value
        if ($scope.formData.departmentCoreList != null && angular.isArray($scope.formData.departmentCoreList)) {
            var departmentCoreValues = [];
            var departmentCoreValuesAdd = [];
            var departmentCoreValuesUpd = [];
            var departmentCoreValuesDel = [];
            // var departmentCoreValuesDel = [];
            for (var i = 0; i < loadData.departmentCoreList.length; i++) { //find delete object
                var old = loadData.departmentCoreList[i];
                if (old.del == true) {
                    old.action = 'del';
                    departmentCoreValuesDel.push(old);
                    // loadData.departmentCoreList.splice(i, 1);
                    continue;
                }
            }

            for (var i = 0; i < $scope.formData.departmentCoreList.length; i++) {
                var dpm = $scope.formData.departmentCoreList[i];
                if (dpm.dtlSeqNo == undefined || dpm.dtlSeqNo == null || parseInt(dpm.dtlSeqNo) < 0) {
                    dpm.action = 'add';
                    departmentCoreValuesAdd.push(dpm);
                    continue;
                }

                var idx = $scope.findIndicatorIndexByDtlSeqNo(loadData.departmentCoreList, dpm.dtlSeqNo);
                if (idx > -1) {
                    var oldDpm = loadData.departmentCoreList[idx];
                    if (oldDpm.del == true) {
                        dpm.action = 'add';
                        departmentCoreValuesAdd.push(dpm);
                        continue;
                    }
                    if (parseInt(dpm.indSeqNo) != parseInt(oldDpm.indSeqNo)) {
                        var idxDtlObj = $scope.findIndicatorIndexByIndSeqNo(loadData.departmentCoreList, dpm.indSeqNo);
                        if (idxDtlObj > -1 && loadData.departmentCoreList[idxDtlObj].del != true && idx != idxDtlObj) {
                            if ($scope.findIndicatorIndexByDtlSeqNo(departmentCoreValuesUpd, loadData.departmentCoreList[idxDtlObj].dtlSeqNo) <= -1) {
                                loadData.departmentCoreList[idxDtlObj].del = true;
                                var old = loadData.departmentCoreList[idxDtlObj];
                                old.action = 'del';
                                departmentCoreValuesDel.push(old);
                                loadData.departmentCoreList.splice(idxDtlObj, 1);
                            }
                        }
                    }

                    if (parseInt(dpm.indSeqNo) != parseInt(oldDpm.indSeqNo) || dpm.remark != oldDpm.remark || dpm.indSelfRating != oldDpm.indSelfRating ||
                        dpm.indRating != oldDpm.indRating || dpm.indWeightPercentage != oldDpm.indWeightPercentage) {
                        // if ($scope.findIndicatorIndexByDtlSeqNo(loadData.departmentCoreList, dpm.dtlSeqNo)) {

                        // }
                        dpm.action = 'upd';
                        departmentCoreValuesUpd.push(dpm);
                    }
                } else {
                    dpm.action = 'add';
                    departmentCoreValuesAdd.push(dpm);
                }

                // var idx = $scope.findIndicatorIndexByIndSeqNo(loadData.departmentCoreList, dpm.indSeqNo);
                // // var oldDpm = idx >= 0 ? loadData.departmentCoreList[idx] : null;
                // if (idx > -1) {
                //     var oldDpm = loadData.departmentCoreList[idx];
                //     if (oldDpm.del != true) {
                //         dpm.dtlSeqNo = oldDpm.dtlSeqNo;
                //         if (dpm.indSeqNo != oldDpm.indSeqNo || dpm.remark != oldDpm.remark || dpm.indSelfRating != oldDpm.indSelfRating ||
                //             dpm.indRating != oldDpm.indRating || dpm.indWeightPercentage != oldDpm.indWeightPercentage) {
                //             dpm.action = 'upd';
                //             departmentCoreValuesUpd.push(dpm);
                //         }
                //     }
                // } else {
                //     idx = $scope.findIndicatorIndexByDtlSeqNo(loadData.departmentCoreList, dpm.dtlSeqNo);
                //     if (idx > -1) {
                //         var oldDpm = loadData.departmentCoreList[idx];
                //         if (dpm.indSeqNo != oldDpm.indSeqNo || dpm.remark != oldDpm.remark || dpm.indSelfRating != oldDpm.indSelfRating ||
                //             dpm.indRating != oldDpm.indRating || dpm.indWeightPercentage != oldDpm.indWeightPercentage) {
                //             dpm.action = 'upd';
                //             departmentCoreValuesUpd.push(dpm);
                //         }
                //     }
                // }
                // dpm.action = 'add';
                // departmentCoreValuesAdd.push(dpm);
                // if (oldDpm == null || oldDpm.del == true || dpm.dtlSeqNo == undefined || dpm.dtlSeqNo == null || parseInt(dpm.dtlSeqNo) < 0) {
                //     dpm.action = 'add';
                //     departmentCoreValuesAdd.push(dpm);
                //     continue;
                // }

                // if (dpm.dtlSeqNo != oldDpm.dtlSeqNo || dpm.indSeqNo != oldDpm.indSeqNo || dpm.remark != oldDpm.remark || dpm.indSelfRating != oldDpm.indSelfRating ||
                //     dpm.indRating != oldDpm.indRating || dpm.indWeightPercentage != oldDpm.indWeightPercentage) {
                //     dpm.action = 'upd';
                //     departmentCoreValuesUpd.push(dpm);
                // }


                // var old = i < loadData.departmentCoreList.length ? loadData.departmentCoreList[i] : null;
                // if (dpm.dtlSeqNo == undefined || dpm.dtlSeqNo == null || parseInt(dpm.dtlSeqNo) < 0) {
                //     dpm.action = 'add';
                //     departmentCoreValuesAdd.push(dpm);
                // } else {
                //     var idx = $scope.findIndicatorIndexByDtlSeqNo(loadData.departmentCoreList, dpm.dtlSeqNo);
                //     if (idx > -1) {
                //         var oldDpm = loadData.departmentCoreList[idx];
                //         if (dpm.indSeqNo != oldDpm.indSeqNo || dpm.remark != oldDpm.remark || dpm.indSelfRating != oldDpm.indSelfRating ||
                //             dpm.indRating != oldDpm.indRating || dpm.indWeightPercentage != oldDpm.indWeightPercentage) {
                //             dpm.action = 'upd';
                //             departmentCoreValuesUpd.push(dpm);
                //         }
                //     }
                // }
            }
            departmentCoreValues = departmentCoreValuesDel.concat(departmentCoreValuesUpd.concat(departmentCoreValuesAdd));
            if (departmentCoreValues.length > 0) {
                if (data == null) {
                    data = {};
                }
                data.departmentCoreValues = departmentCoreValues;
            }
        }

        // STEP 4: Insert/Update/Delete Individual KPI
        if ($scope.formData.individualKPIs != null && angular.isArray($scope.formData.individualKPIs)) {
            var individualKPIs = [];
            var individualKPIsDel = [];
            var individualKPIsUpd = [];
            var individualKPIsAdd = [];

            for (var i = 0; i < loadData.individualKPIs.length; i++) {
                var old = loadData.individualKPIs[i];
                if (old.del == true) {
                    old.action = 'del';
                    individualKPIsDel.push(old);
                }
            }
            for (var i = 0; i < $scope.formData.individualKPIs.length; i++) {
                var dpm = $scope.formData.individualKPIs[i];
                if (dpm.dtlSeqNo == undefined || dpm.dtlSeqNo == null || parseInt(dpm.dtlSeqNo) < 0) {
                    dpm.action = 'add';
                    individualKPIsAdd.push(dpm);
                } else {
                    var idx = $scope.findIndicatorIndexByDtlSeqNo(loadData.individualKPIs, dpm.dtlSeqNo);
                    if (idx > -1) {
                        var oldDpm = loadData.individualKPIs[idx];
                        if (dpm.indSeqNo != oldDpm.indSeqNo || dpm.indDescription != oldDpm.indDescription ||
                            dpm.indRating != oldDpm.indRating || dpm.indWeightPercentage != oldDpm.indWeightPercentage ||
                            dpm.indResult != oldDpm.indResult || dpm.indSlab0 != oldDpm.indSlab0 ||
                            dpm.indSlab1 != oldDpm.indSlab1 || dpm.indSlab2 != oldDpm.indSlab2 ||
                            dpm.indSlab3 != oldDpm.indSlab3 || dpm.indSlab4 != oldDpm.indSlab4 ||
                            dpm.indSlab5 != oldDpm.indSlab5) {
                            dpm.action = 'upd';
                            individualKPIsUpd.push(dpm);
                        }
                    }
                }
            }
            individualKPIs = individualKPIsDel.concat(individualKPIsUpd.concat(individualKPIsAdd));
            if (individualKPIs.length > 0) {
                if (data == null) {
                    data = {};
                }
                data.individualKPIs = individualKPIs;
            }
        }

        // STEP Attach File
        if ($scope.formData.attachFile != null && angular.isArray($scope.formData.attachFile)) {
            var attachFileList = [];
            var attachFileListDel = [];
            var attachFileListAdd = [];

            for (var i = 0; i < loadData.attachFile.length; i++) {
                var old = loadData.attachFile[i];
                if (old.del == true) {
                    old.action = 'del';
                    attachFileListDel.push(old);
                }
            }

            for (var i = 0; i < $scope.formData.attachFile.length; i++) {
                var f = $scope.formData.attachFile[i];
                if (f.uploadDTLSeqNo == undefined || f.uploadDTLSeqNo == null || f.fileIndex != null) {
                    f.action = 'add';
                    attachFileListAdd.push(f);
                }
            }
            attachFileList = attachFileListDel.concat(attachFileListAdd);
            if (attachFileList.length > 0) {
                if (data == null) {
                    data = {};
                }
                data.attachFileList = attachFileList;
            }
        }

        return data;
    }

    $scope.saveData = function(data) {
        var deferred = $q.defer();
        // resolve('Hello, ' + name + '!');
        // } else {
        //   reject('Greeting ' + name + ' is not allowed.');

        // console.dir(data);
        // console.log(JSON.stringify(data));
        // deferred.resolve(data);
        return $http.post(URL_SAVE + '&empSeqNo=' + $scope.empSeqNo + '&pmHDRSeqNo=' + $scope.pmHdrSeqNo, JSON.stringify(data), { headers: { 'Content-Type': 'application/json' } }).success(function(response) {
            return response;
            // deferred.resolve(response);
            //       // if (response !== null && parseInt(response.resultCode) === 200) {
            //       //     $rootScope.showResultModal('Edit Language successful.').then(function () {
            //       //         $state.go('language.list');
            //       //     });
            //       // } else {
            //       //     $rootScope.showAlertModal('Edit Language fail.');
            //       //     console.dir(response);
            //       // }

        }).error(function(response) {
            // console.error(response);
            // $rootScope.showAlertModal('Edit Language fail.');
        }).finally(function() {
            // $rootScope.hideLoadingOverlay('page-wrapper');
        });


        // return deferred.promise;

    };

    $scope.showErrorAlert = function(msg) {
        var htmlTxt = ' <div class="text-danger">' + msg + '</div>';
        bootbox.alert(htmlTxt);
    };

    $scope.showAlert = function(msg) {
        // var htmlTxt = ' <div class="text-danger">' +msg+'</div>';  
        bootbox.alert(msg);
    };

    $scope.filterDepartmentListFn = function(item) {
        if ($scope.formData != null && angular.isArray($scope.formData.departmentCoreList)) {
            for (var i = 0; i < $scope.formData.departmentCoreList.length; i++) {
                if ($scope.formData.departmentCoreList[i].indSeqNo == item.seqNo) {
                    return false;
                }
            }
        }
        return true; // otherwise it won't be within the results
    };

    $scope.percentStr = function(percent) {
        if (percent == undefined || percent == null || isNaN(parseFloat(percent)) || parseFloat(percent) <= 0) {
            return "-";
        }
        return percent + "%";
    };
    // $scope.uploader = new FileUploader();
    $scope.fileInvalid = false;
    $scope.uploadFile = {};
    // $scope.formData.attachFile
    // $scope.formData.attachFile = [];

    // for(var i = 0 ; i < 3 ; i++){
    //     var f = {};
    //     f.name = 'file_'+(i+1)+'.xlx';
    //     f.category = 'Packing List';
    //     f.size = '2MB';
    //     f.lastMod = '11 Mar 2017';
    //     $scope.testData.push(f);
    // }

    //  $scope.uploadChg = function(){
    //     console.dir($scope.uploadData);
    //     if($scope.uploadData.file != undefined && $scope.uploadData.file != null){
    //         $scope.filesDataUpload.push($scope.uploadData.file);
    //         // $scope.fileUpload = null;
    //     }
    //  }

    $scope.uploading = false;

    //     item.put("uploadHDRSeqNo", mod.getUploadHDRSeqNo());
    // item.put("uploadDTLSeqNo", mod.getUploadDTLSeqNo());
    // item.put("pmHDRSeqNo", mod.getPmHDRSeqNo());
    // item.put("fileName", mod.getFileName());
    // item.put("fileFullPath", mod.getFileFullPath());
    // item.put("fileSizeKB", mod.getFileSizeKB());
    // item.put("recordChangeDate", mod.getRecordChangeDate());

    var setUploading = function(bol) {
        $scope.uploading = bol;
    }

    var addAttackFileList = function(file) {
        if (!$scope.formData.attachFile) {
            $scope.formData.attachFile = [];
        }
        $scope.formData.attachFile.push(file);
    };

    var bytesToKB = function bytesToSize(bytes) {
        //var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
        if (bytes == 0) return 0;
        var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
        // return Math.round(bytes / Math.pow(1024, 1), 2);
        return parseFloat(bytes / Math.pow(1024, 1)).toFixed(2);
    };

    $scope.addAttachFileClick = function() {
        var url = URL_ADD_ATTACH_FILE + '&useMultipart=Y&pmHDRSeqNo=' + $scope.pmHdrSeqNo;
        $scope.uploadFile = {};
        $('#fileupload').fileupload({
            autoUpload :true,
            dataType : 'text',
            url: url,
            add :function(e, data){
                var file = data.files[0];
                // var fileName = file.name;
                for (var i = 0; i < $scope.formData.attachFile.length; i++) {
                    var checkName = $scope.formData.attachFile[i].fileName;
                    if (file.name == checkName) {
                         $scope.$apply(function() {
                            $scope.uploadFile.valid = false;
                            $scope.uploadFile.err = 'duplicate file';
                            setUploading(false);
                        });
                        return;
                    }
                }
                $scope.$apply(function() {
                    setUploading(true);
                });
                data.process().done(function () {
                    data.submit();
                });
                // data.submit();
            },
            done: function(e, data) {
                // var result = $( 'pre', data.result ).text();
                // console.dir(data.result);
                var result = angular.fromJson(data.result);
                if(result.valid != 'V'){
                    $scope.$apply(function() {
                        $scope.uploadFile.valid = false;
                        $scope.uploadFile.err = result.msg;
                        setUploading(false);
                    });
                    return;
                }
                $scope.$apply(function() {
                    addAttackFileList(result.data);
                    setUploading(false);
                });
            }
        });
        // setUploading(true);
        // $('#fileupload').fileupload({
        //     url: uploadAttachFileUrl,
        //     dataType: 'json',
        //     // add: function(e, data) {

        //     //     // data.context = $('<p/>').text('Uploading...').appendTo(document.body);
        //     //     // data.submit();
        //     //      $.each(data.files, function (index, file) {
        //     //             console.log('Added file: ' + file.name);
        //     //         });
        //     //         data.url = uploadAttachFileUrl;
        //     //         var jqXHR = data.submit()
        //     //             .success(function (result, textStatus, jqXHR) {/* ... */
        //     //                 $scope.$apply(function(){
        //     //                     addAttackFileList(data.result.data);
        //     //                     setUploading(false);
        //     //                 });
        //     //             })
        //     //             .error(function (jqXHR, textStatus, errorThrown) {/* ... */
        //     //             setUploading(false);})
        //     //             .complete(function (result, textStatus, jqXHR) {/* ... */
        //     //             setUploading(false);});

        //     // },
        //     done: function(e, data) {
        //         // data.context.text('Upload finished.');
        //         $scope.$apply(function(){
        //             addAttackFileList(data.result.data);
        //             setUploading(false);
        //         });

        //     }
        // });
    };
    // $scope.addAttachFileClick();

    // $scope.$on('fileuploadadd', function(event, files) {
    //     var uploadAttachFileUrl = URL_ADD_ATTACH_FILE + '&useMultipart=Y&pmHDRSeqNo=' + $scope.pmHdrSeqNo;
    //     setUploading(true);
    //     // var form  = $('#fileupload')[0];

    //     $.ajax({
    //         // Uncomment the following to send cross-domain cookies:
    //         //xhrFields: {withCredentials: true},
    //         url: uploadAttachFileUrl,
    //         // dataType: 'json',
    //         context: $('#fileupload')[0]
    //     }).always(function() {
    //         setUploading(false);
    //     }).done(function(result) {
    //         addAttackFileList(result.data);
    //     });

    // $('#fileupload').fileupload({
    //     dataType : 'json',
    //     url : uploadAttachFileUrl ,
    //     done : function (e , data){
    //         $scope.$apply(function(){
    //             addAttackFileList(data.result.data);
    //             setUploading(false);
    //         });
    //     }
    // });
    //  $.each(files, function (index, file) {
    // console.dir(file)
    //   setUploading(true);
    //   var fd = new FormData();
    //     // var file = $scope.uploadFile.file;
    //     var file = files.files[0];
    //     // console.dir(file);
    //     for (var i = 0; i < $scope.formData.attachFile.length; i++) {
    //         var checkName = $scope.formData.attachFile[i].fileName;
    //         if (file.name == checkName) {
    //             $scope.uploadFile.valid = false;
    //             $scope.uploadFile.err = 'duplicate file';
    //             setUploading(false);
    //             return;
    //         }
    //     }


    //     var fd = new FormData();
    //     fd.append('file', file);

    //     $http.post(URL_ADD_ATTACH_FILE + '&useMultipart=Y&pmHDRSeqNo=' + $scope.pmHdrSeqNo, fd, {
    //         transformRequest: angular.identity,
    //         headers: {
    //             'Content-Type': undefined,
    //             enctype: 'multipart/form-data'
    //         }
    //         // params: {
    //         //     fd
    //         // }
    //     }).success(function(response) {
    //         // var tmpFileData = {
    //         //     pmHDRSeqNo: $scope.pmHdrSeqNo,
    //         //     fileName: file.name,
    //         //     fileSizeKB: bytesToKB(file.size),
    //         //     recordChangeDate : moment().format('DD/MM/YYYY'),
    //         //     file: file
    //         // };
    //         addAttackFileList(response.data);
    //         // $scope.formData.attachFile.push(response.data);
    //         // $scope.filesDataUpload.push(response.data);
    //     }).error(function() {

    //     }).finally(function() {
    //         setUploading(false);
    //     });


    // $.each(files, function (index, file) {
    //     // console.dir(file)
    //       setUploading(true);
    //         // var file = $scope.uploadFile.file;
    //          var file = file;
    //         // console.dir(file);
    //         for (var i = 0; i < $scope.formData.attachFile.length; i++) {
    //             var checkName = $scope.formData.attachFile[i].fileName;
    //             if (file.name == checkName) {
    //                 $scope.uploadFile.valid = false;
    //                 $scope.uploadFile.err = 'duplicate file';
    //                 setUploading(false);
    //                 return;
    //             }
    //         }


    //         var fd = new FormData();
    //         fd.append('file', file);

    //         $http.post(URL_ADD_ATTACH_FILE + '&useMultipart=Y&pmHDRSeqNo=' + $scope.pmHdrSeqNo, fd, {
    //             transformRequest: angular.identity,
    //             headers: {
    //                 'Content-Type': undefined,
    //                 enctype: 'multipart/form-data'
    //             }
    //             // params: {
    //             //     fd
    //             // }
    //         }).success(function(response) {
    //             // var tmpFileData = {
    //             //     pmHDRSeqNo: $scope.pmHdrSeqNo,
    //             //     fileName: file.name,
    //             //     fileSizeKB: bytesToKB(file.size),
    //             //     recordChangeDate : moment().format('DD/MM/YYYY'),
    //             //     file: file
    //             // };
    //             addAttackFileList(response.data);
    //             // $scope.formData.attachFile.push(response.data);
    //             // $scope.filesDataUpload.push(response.data);
    //         }).error(function() {

    //         }).finally(function() {
    //             setUploading(false);
    //         });


    // });
    // });

    // $scope.$watch('uploadFile', function() {
    //     // alert('hey, myVar has changed!');
    //     // console.dir($scope.uploadFile);
    //     if ($scope.uploadFile != undefined && $scope.uploadFile != null) {
    //         if ($scope.uploadFile.valid) {

    //             setUploading(true);
    //             var file = $scope.uploadFile.file;
    //             // if

    //             // console.dir(file);
    //             for (var i = 0; i < $scope.formData.attachFile.length; i++) {
    //                 var checkName = $scope.formData.attachFile[i].fileName;
    //                 if (file.name == checkName) {
    //                     $scope.uploadFile.valid = false;
    //                     $scope.uploadFile.err = 'duplicate file';
    //                     setUploading(false);
    //                     return;
    //                 }
    //             }


    //             var fd = new FormData();
    //             fd.append('file', file);

    //             $http.post(URL_ADD_ATTACH_FILE + '&useMultipart=Y&pmHDRSeqNo=' + $scope.pmHdrSeqNo, fd, {
    //                 transformRequest: angular.identity,
    //                 headers: {
    //                     'Content-Type': undefined,
    //                     enctype: 'multipart/form-data'
    //                 }
    //                 // params: {
    //                 //     fd
    //                 // }
    //             }).success(function(response) {
    //                 // var tmpFileData = {
    //                 //     pmHDRSeqNo: $scope.pmHdrSeqNo,
    //                 //     fileName: file.name,
    //                 //     fileSizeKB: bytesToKB(file.size),
    //                 //     recordChangeDate : moment().format('DD/MM/YYYY'),
    //                 //     file: file
    //                 // };
    //                 addAttackFileList(response.data);
    //                 // $scope.formData.attachFile.push(response.data);
    //                 // $scope.filesDataUpload.push(response.data);
    //             }).error(function() {

    //             }).finally(function() {
    //                 setUploading(false);
    //             });


    //             // setUploading(false);
    //             // var file = $scope.uploadFile.file;

    //             // $scope.uploading = true; ;

    //             // $timeout(function(){


    //             // }, 2000);
    //             // setTimeout(function(){
    //             //     setUploading(false);
    //             // }, 2000);
    //         }
    //     }
    // });

    $scope.attachClick = function() {
        $scope.uploadFile = {};
        // $scope.addAttachFileClick();
    }

    $scope.attachDialogOptions = {
        templateUrl: 'views/custom-dialog.tpl.html',
        scope: $scope,
        size: 'large',
        show: true,
        backdrop: true,
        closeButton: true,
        animate: true
    };

    $scope.uploadFileClick = function() {
        // console.log('testtttttttt');
        // console.dir();
    };

    $scope.downloadFileClick = function(idx) {
        // console.log('downloadFileClick at :' + $scope.filesDataUpload[idx].name);

        var file = $scope.formData.attachFile[idx];
        var url = DOWNLOAD_FILE + '&pmHDRSeqNo=' + $scope.pmHdrSeqNo + '&fileName=' + encodeURIComponent(file.fileName) + '&fileFullPath=' + encodeURIComponent(file.fileFullPath);

        $window.open(url, "_blank");
        // var fd = new FormData();
        // fd.append('file', file);

        // $http.post(URL_UPLOAD_FILE + '&useMultipart=Y&pmHDRSeqNo=' + $scope.pmHdrSeqNo, fd, {
        //     transformRequest: angular.identity,
        //     headers: {
        //         'Content-Type': undefined,
        //         enctype: 'multipart/form-data'
        //     },
        //     params: {
        //         fd
        //     }
        // })

        // .success(function() {})

        // .error(function() {});
    };

    $scope.deleteFileClick = function(idx) {
        var f = $scope.formData.attachFile[idx];
        if (f.uploadDTLSeqNo) {
            for (var i = 0; i < loadData.attachFile.length; i++) {
                if (f.uploadDTLSeqNo == loadData.attachFile[i].uploadDTLSeqNo) {
                    loadData.attachFile[i].del = true;
                    $scope.formData.attachFile.splice(idx, 1);
                    return;
                }
            }

        } else {
            $scope.formData.attachFile.splice(idx, 1);
            // var params = {pmHDRSeqNo}
            // $http.delete(URL_REMOVE_ATTACH_FILE+'&pmHDRSeqNo=' + $scope.pmHdrSeqNo+'&keyName='+f.fileIndex , { headers: HEADERS }).success(function(response) {

            // }).error(function() {

            // });
        }
    };


    // $scope.attachClick = function() {
    //     var tplCrop = '<button type="button" class="btn btn-purple btn-labeled fa fa-upload"  ng-click="uploadFileClick();">Upload File</button>';
    //     var template = angular.element(tplCrop);
    //     var linkFn = $compile(template);
    //     var titleHtml = linkFn($scope);
    //     var dialog = bootbox.dialog({
    //         title: titleHtml,
    //         message: '<div id="attachFiles" class="text-center"><i class="fa fa-spin fa-spinner"></i> Loading...</div>',
    //         backdrop: true
    //             // buttons:{
    //             //   "OK" : function() {
    //             //             dialog.modal('hide');
    //             //         }
    //             // }
    //     });

    //     dialog.init(function() {
    //         // var HEADERS = { 'Content-Type': 'application/x-www-form-urlencoded' };
    //         // var URL_BASE_MAINTAIN = URLRRCSTANDARDSRV + '?service=ui.pms.PmsPMFormMaintenanceSvc';
    //         // var URL_LOAD_INDICATOR_DETAIL = URL_BASE_MAINTAIN + '&part=maintenance_load_indicator_detail';

    //         // // var jobBrand = $scope.formData.appraiseeDetail.jobBrand;
    //         // // var dltInd = {};
    //         // var params = { "indSeqNo": indSeqNo, "jobBrand": jobBrand };
    //         // $http.get(URL_LOAD_INDICATOR_DETAIL, { "params": params, headers: HEADERS }).success(function(response) {
    //         //     $scope.dtlInd = response.data;

    //         //     var html = '<h4>' + title + '</h4><div><p><strong>Definition</strong></p><p>' + ($scope.dtlInd.definition ? $scope.dtlInd.definition : '') + '</p></div>' +
    //         //         '<div><p><strong>Description</strong></p>';
    //         //     if ($scope.dtlInd.description1 && $scope.dtlInd.description1 != null) {
    //         //         html += '<p>' + $scope.dtlInd.description1 + '</p>';
    //         //     }
    //         //     if ($scope.dtlInd.description2 && $scope.dtlInd.description2 != null) {
    //         //         html += '<p>' + $scope.dtlInd.description2 + '</p>';
    //         //     }
    //         //     if ($scope.dtlInd.description3 && $scope.dtlInd.description3 != null) {
    //         //         html += '<p>' + $scope.dtlInd.description3 + '</p>';
    //         //     }
    //         //     html += '</div></div>';
    //         //     dialog.find('.bootbox-body').html(html);
    //         // }).error(function(err) {
    //         //     dialog.find('.bootbox-body').html(err);
    //         // });
    //         setTimeout(function() {
    //             dialog.find('.bootbox-body').html('<table class="table table-striped"><thead><tr> <th>#</th><th>First Name</th> <th>Last Name</th> <th>Username</th> </tr> </thead> <tbody> <tr> <th scope="row">1</th> <td>Mark</td> <td>Otto</td> <td>@mdo</td> </tr> <tr> <th scope="row">2</th> <td>Jacob</td> <td>Thornton</td> <td>@fat</td> </tr> <tr> <th scope="row">3</th> <td>Larry</td> <td>the Bird</td> <td>@twitter</td> </tr> </tbody> </table>');
    //         }, 3000);
    //     });

    // };


    // // Save new PM form
    // $scope.savePMForm = function () {
    //   $location.path("/showSummarizePM");
    // }
    // // Save new PM form
    // $scope.submitPMForm = function () {
    //   $location.path("/showSummarizePM");
    // }
    // // Confirm Save new PM form
    // $scope.confirmSubmitPMForm = function () {
    //   $location.path("/savePMSuccess");
    // }
    // dialog.init(function(){
    //     setTimeout(function(){
    //         dialog.find('.bootbox-body').html('I was loaded after the dialog was shown!');
    //     }, 3000);
    // });

    $rootScope.$on("$locationChangeStart", function(event, next, current) {
        // handle route changes 
        $rootScope.alertShow = false;
    });
    
    $scope.exportPdf = function() {
        $scope.showAllRate = true;
        $("#chkAll").prop("checked", true);
        var cntIndv = $scope.formData.individualKPIs.length;
//        if($scope.countRate%2!=0){
//            $(".export-pdf").css("letter-spacing", "2px");
//            $(".export-pdf").css("word-spacing", "2px");
//            $(".export-p1").css("margin-top", "1000px");
    //        $("#export").css("margin-left", "-300px");
            var padding = $("#content-container").css("padding-left");
            
            var userAgent = window.navigator.userAgent;
            var left =-20;
            var dynamicLeft = left;
            var width = 225;
            var lastLeft = -15;
            var lastWidth = 215;
            var top = 5;
            if(padding=="50px"){
                left = 0;
                dynamicLeft = left;
                width = 205;
                lastLeft = 5;
                lastWidth = 200;
            }else{
                if(userAgent.indexOf("Chrome") != -1 || userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("MSIE") != -1 || (!!document.documentMode==true)){
                    left = 0;
                    dynamicLeft = left;
                    width = 205;
                    lastLeft = 5;
                    lastWidth = 200;
                }
            }
            var page1 = document.getElementById('page1');
            var page2 = document.getElementById('page2');
            var page3 = document.getElementById('page3');
            var page4 = document.getElementById('page4');
            if(cntIndv>3&&cntIndv<=5){
                $(".margintop td").css("padding-bottom", "325px");
            }/*else if(cntIndv>5&&((cntIndv%2)==0)){
                $(".margintop td").css("padding-bottom", "300px");
            }*/else{
                $(".margintop td").css("padding-bottom", "280px");
            }
            if((cntIndv)%2==0){
                $(".margintop").last().find("td").css("padding-bottom", "0px");
            }
            if(cntIndv<=3){
                $(".margintop td").css("padding-bottom", "10px");
            }
            $("#tbIndividual .odd").css("background-color", "transparent");
            displayOverlay("body");
            html2canvas(page1, {
                background :'#FFFFFF',
                onrendered: function(canvas1) {
                    var imgData1 = canvas1.toDataURL(
                        'image/jpeg', 1.0);
                        
                    html2canvas(page2, {
                        background :'#FFFFFF',
                        onrendered: function(canvas2) {
                            var imgData2 = canvas2.toDataURL(
                                'image/jpeg', 1.0);
                        
                            html2canvas(page3, {
                                background :'#FFFFFF',
                                onrendered: function(canvas3) {
                                    var imgData3 = canvas3.toDataURL(
                                        'image/jpeg', 1.0);
                                        
                                    var imgWidth = 150;
                                    var pageHeight = 280;
                                    var imgHeight = canvas3.height * width / canvas3.width;
                                    var heightLeft = imgHeight;
                                    var position = 5;
                                    
                                    var img3Height = canvas3.height;
                                        
                                    html2canvas(page4, {
                                        background :'#FFFFFF',
                                        onrendered: function(canvas4) {
                                            var imgData4 = canvas4.toDataURL(
                                                'image/jpeg', 1.0);
                                            var pdf = new jsPDF();
                            //                pdf.addImage(imgData, 'PNG', 30, 5, 150, 290);
                                            pdf.addImage(imgData1, 'JPEG', left, top, width, 280);
                                            
                                            pdf.addPage();
                                            pdf.addImage(imgData2, 'JPEG', left, top, width, 280);
                                            
//                                            heightLeft -= pageHeight;
//                                            while (heightLeft >= 0) {
//                                                pdf.addPage();
//                                                pdf.addImage(imgData3, 'JPEG', left, position, width, imgHeight);
//                                                position = position - 220;
//                                                heightLeft -= pageHeight;
//                                            }

                                            var dynamicHeight = 0;
                                            var posTop = 5;
                                            var dynamicTop = -285;
                                            if(cntIndv>3 && cntIndv<=5){
                                                dynamicTop = -275;
                                            }
//                                            if(cntIndv>6){
//                                                dynamicHeight = 1000;
//                                            }
                                            for(var i=0;i<cntIndv;i++){
                                                dynamicHeight += 150;
//                                                if(cntIndv>9 && (i+1)%2==0){
//                                                    dynamicLeft += 5;
//                                                }
                                            }
                                            if(cntIndv>3){
                                                dynamicHeight -= 50;
                                            }

                                            var cnt = 0;
                                            if(cntIndv<=3){
                                                pdf.addPage();
                                                pdf.addImage(imgData3, 'JPEG', left, top, width, 275);
                                            }else if(cntIndv>3){
                                                for(var i=0;i<cntIndv;i++){
                                                    /*if(i==0){
                                                        pdf.addPage();
                                                        pdf.addImage(imgData3, 'JPEG', left, top, width, 550);
                                                    }else */if(i%2==0){
                                                        pdf.addPage();
                                                        pdf.addImage(imgData3, 'JPEG', dynamicLeft, posTop, width, dynamicHeight);
                                                        posTop += dynamicTop;
//                                                        if(cnt>2 && cnt<6){
//                                                            posTop += 15;
//                                                        }
//                                                        if(i>7 && (i%2)==0){
//                                                            posTop += -15;
//                                                        }
                                                        if(cntIndv>7 && (cntIndv%2==0)){
                                                            posTop += -10;
                                                        }else if(cntIndv>6 && cntIndv<10 && (cntIndv%2!=0)){
                                                            posTop += -5;
                                                        }else if(cntIndv>10 && (cntIndv%2!=0)){
                                                            posTop += -3;
                                                        }
                                                    }else if((i+1)==cntIndv && i%2!=0){
                                                    }else if(i%2!=0){
                                                    }else if((i+1)==cntIndv){
                                                        pdf.addPage();
                                                        pdf.addImage(imgData3, 'JPEG', dynamicLeft, posTop, width, dynamicHeight);
                                                    }
                                                }
                                            }
//                                            pdf.removePage();
                                            
//                                            pdf.addPage();
//                                            pdf.addImage(imgData3, 'JPEG', left, top, width, 280);
                                
                                            pdf.addPage();
                                            pdf.addImage(imgData4, 'JPEG', lastLeft, top, lastWidth, 280);
                                            
                                            var fileName = 'pm_' + $scope.formData.appraiseeDetail.empId + '_' +
                                                $scope.formData.appraiseeDetail.pmYear + 'H' + $scope.formData.appraiseeDetail.pmPeriod;
                                            pdf.save(fileName + '.pdf');
    //                                        $(".export-pdf").css("letter-spacing", "0px");
    //                                        $(".export-pdf").css("word-spacing", "2px");
                                            removeOverlay();
                                            $(".margintop td").css("padding-bottom", "0px");
                                            $("#tbIndividual .odd").css("background-color", "#f0f3f7");
                                        }
                                    });
                                }
                            });
                        }
                    });
    //                $("#export").css("margin-left", "0px");
    //                $("#export").css("width", "100%");
//                    $(".export-style").css("margin-top", "0px");
//                    $(".export-p1").css("margin-top", "0px");
                    removeOverlay();
                }
            }).finally(function() {
                removeOverlay();
            });
//        }else{
//            $(".export-style").css("margin-top", "800px");
//            $(".export-p1").css("margin-top", "1100px");
//    //        $("#export").css("margin-left", "-300px");
//    //        $("#export").css("width", "800px");
//            var node = document.getElementById('export');
//            displayOverlay("body");
//            html2canvas(node, {
//                onrendered: function(canvas) {
//                    var imgData = canvas.toDataURL(
//                        'image/png', 1.0);
//                    // var doc = new jsPDF('p', 'mm');
//                    // doc.addImage(imgData, 'PNG', 10, 10);
//                    // doc.save('sample-file.pdf');
//                    //                 var pdf = new jsPDF("l", "mm", "a4");
//                    // var imgData = canvas.toDataURL('image/jpeg', 1.0);
//    
//                    // // due to lack of documentation; try setting w/h based on unit
//                    // pdf.addImage(imgData, 'JPEG', 10, 10, 180, 150);  // 180x150 mm @ (10,10)mm
//                    var pdf = new jsPDF();
//    //                pdf.addImage(imgData, 'PNG', 30, 5, 150, 290);
//                    pdf.addImage(imgData, 'JPEG', 0, 5, 200, 1100);
//                    
//                    pdf.addPage();
//                    pdf.addImage(imgData, 'JPEG', 0, -340, 200, 1150);
//        
//                    pdf.addPage();
//                    pdf.addImage(imgData, 'JPEG', 0, -625, 200, 1150);
//        
//                    pdf.addPage();
//                    pdf.addImage(imgData, 'JPEG', 0, -790, 200, 1000);
//                    
//                    var fileName = 'pm_' + $scope.formData.appraiseeDetail.empId + '_' +
//                        $scope.formData.appraiseeDetail.pmYear + 'H' + $scope.formData.appraiseeDetail.pmPeriod;
//                    pdf.save(fileName + '.pdf');
//    //                $("#export").css("margin-left", "0px");
//    //                $("#export").css("width", "100%");
//                    $(".export-style").css("margin-top", "0px");
//                    $(".export-p1").css("margin-top", "0px");
//                    removeOverlay();
//                }
//            }).finally(function() {
//                removeOverlay();
//            });
//        }

        // domtoimage.toPng(node).then(function (dataUrl) {
        //     var img = new Image();
        //     img.src = dataUrl;

        //     var pdf = new jsPDF();
        //     pdf.addImage(img, 'JPEG', 30, 5, 150, 290);
        //     var download = document.getElementById('download');
        //     // pm_10200_2017H1
        //     var fileName = 'pm_'+$scope.formData.appraiseeDetail.empId+'_'
        //         +$scope.formData.appraiseeDetail.pmYear+'H'+$scope.formData.appraiseeDetail.pmPeriod;
        //     pdf.save(fileName+'.pdf');

        // }).catch(function (error) {
        //     console.log('oops, something went wrong!', error);
        // });
    };
});

appModule.controller("modalCoreValueInfoCtrl", function($scope, $modalInstance, $http, $timeout, titleInfo, indSeqNo, jobBrand) {

    $scope.titleInfo = titleInfo;
    var HEADERS = { 'Content-Type': 'application/x-www-form-urlencoded' };
    var URL_BASE_MAINTAIN = URLRRCSTANDARDSRV + '?service=ui.pms.PmsPMFormMaintenanceSvc';
    var URL_LOAD_INDICATOR_DETAIL = URL_BASE_MAINTAIN + '&part=maintenance_load_indicator_detail';
    $scope.loading = true;
    // var jobBrand = $scope.formData.appraiseeDetail.jobBrand;
    var params = { "indSeqNo": indSeqNo, "jobBrand": jobBrand };
    $http.get(URL_LOAD_INDICATOR_DETAIL, { "params": params, headers: HEADERS }).success(function(response) {
        $scope.dtlInd = response.data;
    }).finally(function() {
        $scope.loading = false;
    });

});