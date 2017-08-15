<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ page isELIgnored="false" %>
<html lang="en" ng-app="pms">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>RCL-Performance Management System</title>
	<!--STYLESHEET-->
	<!--=================================================-->
	<!--Bootstrap Stylesheet [ REQUIRED ]-->
	    <link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">
	<!--Nifty Stylesheet [ REQUIRED ]-->
	<link href="<c:url value='/css/nifty.min.css'/>" rel="stylesheet">
        <link href="<c:url value='/css/theme-purple.min.css'/>" rel="stylesheet">
	<!--Font Awesome [ OPTIONAL ]-->
	<link href="<c:url value='/css/font-awesome.min.css'/>" rel="stylesheet">
        <link href="<c:url value='/css/footable.core.min.css'/>" rel="stylesheet">
        <link href="<c:url value='/css/morris.min.css'/>" rel="stylesheet">
        <!--Bootstrap Datepicker [ OPTIONAL ]-->
        <link href="<c:url value='/css/bootstrap-datepicker.min.css'/>" rel="stylesheet">
	 
        <!-- Customize Style -->
        <link href="<c:url value='/css/style.css'/>" rel="stylesheet">

 <!--[if lte IE 8]>
      <script>
        document.createElement('ng-include');
        document.createElement('ng-pluralize');
        document.createElement('ng-view');

        // Optionally these for CSS
        document.createElement('ng:include');
        document.createElement('ng:pluralize');
        document.createElement('ng:view');
      </script>
    <![endif]-->
    
	<!--SCRIPT-->
	<!--=================================================-->
	<!--JAVASCRIPT-->
	<!--=================================================-->
  <script src="<c:url value='/js/jquery-1.11.3.js'/>" type="text/javascript"> </script>
  <!--<script src="<c:url value='/js/jquery-2.1.4.min.js'/>" type="text/javascript"> </script>-->
  <script src="<c:url value='/js/modernizr-2.6.2.js'/>" type="text/javascript"> </script>
  <script src="<c:url value='/js/bootstrap.min.js'/>" type="text/javascript"> </script>
  <script src="<c:url value='/js/bootstrap-datepicker.min.js'/>" type="text/javascript"> </script>
  <script src="<c:url value='/js/moment.min.js'/>" type="text/javascript"> </script>
  <script src="<c:url value='/js/bootbox.min.js'/>" type="text/javascript"> </script>
  <script src="<c:url value='/js/footable.all.min.js'/>" type="text/javascript"> </script>
  <script src="<c:url value='/js/raphael.min.js'/>" type="text/javascript"> </script>
  <script src="<c:url value='/js/morris.min.js'/>" type="text/javascript"> </script>
  <script src="<c:url value='/js/es6-promise.auto.min.js'/>" type="text/javascript"> </script>
  <script src="<c:url value='/js/html2canvas.js'/>" type="text/javascript"> </script>
  <script src="<c:url value='/js/dom-to-image.min.js'/>" type="text/javascript"> </script>
  <script src="<c:url value='/js/jspdf.min.js'/>" type="text/javascript"> </script>
  <script type="text/javascript"> 
    var URLRRCSTANDARDSRV ="<c:url value='/RrcStandardSrv' />"; 
  </script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="<c:url value='/js/html5shiv.min.js'/>" type="text/javascript"> </script>
      <script src="<c:url value='/js/respond.min.js'/>" type="text/javascript"> </script>
    <![endif]-->

</head>
 
<!--TIPS-->
<!--You may remove all ID or Class names which contain "demo-", they are only used for demonstration. -->

<body ng-controller="pmsController">
<c:import url="/pages/pms/keepAlive.jsp"></c:import>   
	<div id="container" class="effect mainnav-lg">

		<!--NAVBAR-->
		<!--===================================================-->
		<header id="navbar">
			<div id="navbar-container" class="boxed">

				<!--Brand logo & name-->
				<!--================================-->
				<div class="navbar-header">
					<a href="index.html" class="navbar-brand">
						<img src="img/logo.png" alt="RCL Logo" class="brand-icon">
						<div class="brand-title">
							<span class="brand-text">RCL-PMS</span>
						</div>
					</a>
				</div>
				<!--================================-->
				<!--End brand logo & name-->


				<!--Navbar Dropdown-->
				<!--================================-->
				<div class="navbar-content clearfix">
					<ul class="nav navbar-top-links pull-left">

						<!--Navigation toogle button-->
						<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
						<li class="tgl-menu-btn">
							<a class="mainnav-toggle" href="#">
								<i class="fa fa-navicon fa-lg"></i>
							</a>
						</li>
						<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
						<!--End Navigation toogle button-->


						<!-- TODO: Navigation Dropdown here! -->
                                                
						<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
						<!--End notifications dropdown-->




					</ul>
					<ul class="nav navbar-top-links pull-right">


						<!--User dropdown-->
						<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
						<li id="dropdown-user" class="dropdown">
							<a href="" data-toggle="dropdown" class="dropdown-toggle text-right">
								<span class="pull-right">
									<img class="img-circle img-user media-object" src="imges/1" alt="Profile Picture">
								</span>
								<div class="username hidden-xs">${employeeProfile.empName}</div>
							</a>


							<div class="dropdown-menu dropdown-menu-md dropdown-menu-right with-arrow panel-default">

								<!-- Dropdown heading  -->
								<div class="pad-all bord-btm">
									<p class="text-lg text-muted text-thin mar-btm">750Gb of 1,000Gb Used</p>
									<div class="progress progress-sm">
										<div class="progress-bar" style="width: 70%;">
											<span class="sr-only">70%</span>
										</div>
									</div>
								</div>


								<!-- User dropdown menu -->
								<ul class="head-list">
									<li>
										<a href="#">
											<i class="fa fa-user fa-fw fa-lg"></i> Profile
										</a>
									</li>
									<li>
										<a href="#">
											<span class="badge badge-danger pull-right">9</span>
											<i class="fa fa-envelope fa-fw fa-lg"></i> Messages
										</a> 
									</li>
									<li>
										<a href="#">
											<span class="label label-success pull-right">New</span>
											<i class="fa fa-gear fa-fw fa-lg"></i> Settings
										</a>
									</li>
								</ul>

								<!-- Dropdown footer -->
								<div class="pad-all text-right">
									<a href="javascript:window.close()" class="btn btn-primary">
										<i class="fa fa-sign-out fa-fw"></i> Logout
									</a>
								</div>
							</div>
						</li>
						<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
						<!--End user dropdown-->

					</ul>
				</div>
				<!--================================-->
				<!--End Navbar Dropdown-->

			</div>
		</header>
		<!--===================================================-->
		<!--END NAVBAR-->

		<div class="boxed">

			<!--CONTENT CONTAINER-->
			<!--===================================================-->
			<div id="content-container">
				<div id="page-alert">
					<div class="alert-wrap" ng-class="{'in':alertShow}">
						<div class="alert" ng-class="{'alert-success': statusCode != 'N' , 'alert-danger': statusCode == 'N'}" role="alert"><button class="close" type="button" ng-click="hideErrorAlert();"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
							<div class="media" ng-show="statusCode == 'N'"><strong>Error&nbsp;Message:</strong> {{statusMessage}}</div>
							<div class="media" ng-show="statusCode != 'N'"><strong>Done! </strong> {{statusMessage}}</div>
						</div>
					</div>
				</div>
				<!--Page content-->
				<!--===================================================-->
				<div id="page-content">
					

					<!-- QUICK TIPS -->
					<!-- ==================================================================== -->
				<!--	<h3>Your content here...</h3> -->
                                       <div ng-view></div>
				</div>
				<!--===================================================-->
				<!--End page content-->


			</div>
			<!--===================================================-->
			<!--END CONTENT CONTAINER-->



			<!--MAIN NAVIGATION-->
			<!--===================================================-->
			<nav id="mainnav-container">
				<div id="mainnav">
					<!--Shortcut buttons-->
					<!--================================-->
					<div id="mainnav-shortcut"> 
						<ul class="list-unstyled">
							<li class="col-xs-4" data-content="Dashboard">
								<a class="shortcut-grid" href="#/dashboard">
									<i class="fa fa-dashboard"></i>
								</a>
							</li>
							<li class="col-xs-4" data-content="Report">
                                <c:choose>
									<c:when test="${employeeProfile.userLevel eq 'HR'}">
											<a class="shortcut-grid" href="#/summaryReport">
												<i class="fa fa-bar-chart-o"></i>
											</a>	
									</c:when>
									<c:when test="${employeeProfile.userLevel eq 'ADMIN'}">
											<a class="shortcut-grid" href="#/summaryReport">
												<i class="fa fa-bar-chart-o"></i>
											</a>	
									</c:when>
									<c:otherwise>
										<a class="shortcut-grid">
												<i class="fa fa-bar-chart-o"></i>
										</a>
									</c:otherwise>
                                </c:choose>                                     
								
							</li>
							<li class="col-xs-4" data-content="Notice New PM Form">
								
                                                                				
                                <c:choose>
									<c:when test="${employeeProfile.flagCreate eq 'Y'}">
											<a class="shortcut-grid" href="#/noticeNewPM">
									<i class="fa fa-plus-square"></i>
								</a>	
									</c:when>
									
									<c:otherwise>
										<a class="shortcut-grid">
												<i class="fa fa-plus-square"></i>
										</a>
									</c:otherwise>
                                </c:choose>                                     
							</li>
						</ul>
					</div>
					<!--================================-->
					<!--End shortcut buttons-->


					<!--Menu-->
					<!--================================-->
						<div id="mainnav-menu-wrap">
						<div class="nano">
							<div class="nano-content">
								<ul id="mainnav-menu" class="list-group">


									<li class="list-divider"></li>

									<!--Category name-->
									<li class="list-header">Performance Managment System</li>
                                                                       
									<!--Menu list item-->
									<!-- <li> -->
                                                                        <!--
										<a href="#">
											<i class="fa fa-th"></i>
											<span class="menu-title">Setup</span>
											<i class="arrow"></i>
										</a>
                                                                                -->

										<!--Submenu-->
                                                                                <!--
										<ul class="collapse in">
											<li class="active-link"><a href="#/employeeProfile">Employee Profile</a></li>
											<li><a href="#/organizationSetup">Organization Setup</a></li>
											<li><a href="#/indicatorSetup">Indicator Setup</a></li>
										</ul>
                                                                                -->
									<!-- </li> -->

									<!--Menu list item-->
									<li class="active-sub">
										<a href="#">
											<i class="fa fa-cog"></i>
											<span class="menu-title">
												<strong>Maintenance</strong>
											</span>
											<i class="arrow"></i>
										</a>

										<!--Submenu-->
										<ul class="collapse in">
											<li><a href="#/maintenance">PM Form <!-- <span class="pull-right badge badge-success">3</span>--></a></li>
										</ul>
									</li>


									<!--Menu list item-->
									
									<c:if test="${ (employeeProfile.userLevel eq 'HR') ||
													(employeeProfile.userLevel eq 'ADMIN') }">														
									<li>
										<a href="#">
											<i class="fa fa-bar-chart-o"></i>
											<span class="menu-title">
												<strong>Report</strong>
											</span>
											<i class="arrow"></i>
										</a>

										<!--Submenu-->
										<ul class="collapse">
											<li><a href="#/summaryReport">PM Summary Report</a></li>
                      
										</ul>					
									
									</li>
									<li>
										<a href="#">
											<i class="fa fa-bar-chart-o"></i>
											<span class="menu-title">
												<strong>HR Maintenace</strong>
											</span>
											<i class="arrow"></i>
										</a>
										<!--Submenu-->
										<ul class="collapse">
											<li><a href="#/empPrRes">Employee Performance Result Report</a></li>
                      					</ul>	
										<ul class="collapse">
											<li><a href="#/">Employee Maintenance</a></li>
                      					</ul>				
										<ul class="collapse">
											<li><a href="#/">Job Band Maintenance</a></li>
                      					</ul>
										<ul class="collapse">
											<li><a href="#/">Competency Maintenance</a>
												<ul class="collapse">
													<li><a href="#/companyCore">Company Core value Setup</a></li>
												</ul>
												<ul class="collapse">
													<li><a href="#/departmentCore">Department Core value Setup</a></li>
												</ul>
												<ul class="collapse">
													<li><a href="#/bscPerspective">BSC Perspective Setup</a></li>
												</ul>
												<ul class="collapse">
													<li><a href="#/">President Directive Setup</a></li>
												</ul>
												<ul class="collapse">
													<li><a href="#/pmsPeriod">PMS Period Setup</a></li>
												</ul>
											</li>
                      					</ul>
									</li>
                                     </c:if>                               
									<li class="list-divider"></li>

									<!--Category name-->                                                                    
                                                                        <li class="list-header"><span>&copy; RCL 2017 v.${pmsVersion}</span></li>

								</ul>


							</div>
						</div>
					</div>
					<!--================================-->
					<!--End menu-->

				</div>
			</nav>
			<!--===================================================-->
			<!--END MAIN NAVIGATION-->

		</div>
</div>




		<!-- SCROLL TOP BUTTON -->
		<!--===================================================-->
		<button id="scroll-top" class="btn"><i class="fa fa-chevron-up"></i></button>
		<!--===================================================-->



	
	<!--===================================================-->
	<!-- END OF CONTAINER -->



	<!--Nifty Admin [ RECOMMENDED ]-->
	
	
	<script src="<c:url value='/js/vendor/nifty.js'/>" type="text/javascript"> </script>
        <script src="<c:url value='/js/angular.min.js'/>" charset="utf-8"></script>
        <script src="<c:url value='/js/ngModules/angular-resource.min.js'/>" charset="utf-8"></script>
        <script src="<c:url value='/js/ngModules/angular-route.min.js'/>" charset="utf-8"></script>
		<script src="<c:url value='/js/ui-bootstrap-tpls-0.12.0.min.js'/>" charset="utf-8"></script>
		<script src="<c:url value='/js/ngBootbox.min.js'/>" type="text/javascript"> </script>
		<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
		<script src="<c:url value='/js/vendor/jquery.ui.widget.js'/>" type="text/javascript"> </script>
		<script src="<c:url value='/js/jquery.iframe-transport.js'/>" type="text/javascript"></script> 
		<script src="<c:url value='/js/jquery.fileupload.js'/>" type="text/javascript"> </script>

        <script type="text/javascript">
         
        
        var appModule = angular.module("pms",["ngRoute","ui.bootstrap","ngBootbox"]); //"blueimp.fileupload"
        appModule.factory('State', function(){
         
          return {
            storeData:{}
          };
        });

        appModule.config(function($routeProvider) {
          $routeProvider.when("/dashboard", {
            templateUrl: "views/dashboard.html"
          })
          .when("/employeeProfile", {
            templateUrl: "views/employeeProfile.html"
          })
          .when("/organizationSetup", {
            templateUrl: "views/organizationSetup.html"
          })
          .when("/indicatorSetup", {
            templateUrl: "views/indicatorSetup.html"
          })
          .when("/maintenance", {
            templateUrl: "views/maintenance.html"
          })
          .when("/summaryReport", {
            templateUrl: "views/summaryReport.html"
          })
          .when("/empPrRes", {
            templateUrl: "views/empPrRes.html"
          })
          .when("/companyCore", {
            templateUrl: "views/companyCoreValueSetup.html"
          })
           .when("/departmentCore", {
            templateUrl: "views/departmentCoreValueSetup.html"
          })
           .when("/bscPerspective", {
            templateUrl: "views/bscPerspectiveSetup.html"
          })
           .when("/pmsPeriod", {
            templateUrl: "views/pmsPeriodSetup.html"
          })
          .when("/agingReport", {
            templateUrl: "views/agingReport.html"
          })
          .when("/noticeNewPM", {
            templateUrl: "views/noticeNewPMWizard.html"
          })
           .when("/editPMForm", {
            templateUrl: "views/noticeNewPM.html"
          })
          .when("/showSummarizePM", {
            templateUrl: "views/pm-summarize.html"
          })
          .when("/savePMSuccess", {
            templateUrl: "views/pm-form-success.html"
          })
          .otherwise({
            templateUrl: "views/dashboard.html"
          });
        });

		appModule.config(['$httpProvider', function($httpProvider) {
			//initialize get if not there
			if (!$httpProvider.defaults.headers.get) {
				$httpProvider.defaults.headers.get = {};    
			}    

			// Answer edited to include suggestions from comments
			// because previous version of code introduced browser-related errors

			//disable IE ajax request caching
			$httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
			// extra
			$httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
			$httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
		}]);
				
		appModule.run(function ($rootScope, $location ,$filter) {
					$rootScope.commonData = {} ;
            $rootScope.commonData.pmStatusList = [
                {"status": "N", "description": "New" , cssClss : "label-primary" },
                {"status": "I", "description": "In Progress" ,cssClss : "label-info"},
                {"status": "W", "description": "Waitlisted 1" , cssClss : "label-warning"},
                {"status": "V", "description": "Waitlisted 2" , cssClss : "label-warning"},
                {"status": "C", "description": "Completed" , cssClss : "label-success"}
              ];
             
            $rootScope.getPmStausDesc = function (status) {
                status = status || '' ;
                var result = $filter('filter')( $rootScope.commonData.pmStatusList ,
                    { 'status' : status.toUpperCase()} , true);
                
                return result != null && result.length > 0 ? result[0].description : status;
            };
            
            $rootScope.getPmStausClass = function (status) {
                var retStatusCls = "label-default";
                status = status || '' ;
                var result = $filter('filter')( $rootScope.commonData.pmStatusList ,
                    { 'status' : status.toUpperCase()} , true);
                
                return result != null && result.length > 0 ? result[0].cssClss : retStatusCls;
            };

			$rootScope.showErrorAlert = function(statusCode , statusMessage){
				$rootScope.statusCode = statusCode;
				$rootScope.statusMessage = statusMessage;
				$rootScope.alertShow = true ;
				$('html, body').animate({ scrollTop: $('#page-alert').offset().top }, 'slow');
			}

			$rootScope.hideErrorAlert = function(){
				// $rootScope.statusCode = '';
				$rootScope.alertShow = false ;
			}
        });
            
        appModule.directive('numuriconly', function(){
           return {
             require: 'ngModel',
             link: function(scope, element, attrs, modelCtrl) {
               modelCtrl.$parsers.push(function (inputValue) {
                   if (inputValue == undefined) return '' 
                   var transformedInput = inputValue.replace(/[^0-9]/g, ''); 
                   if (transformedInput!=inputValue) {
                                        modelCtrl.$setViewValue(transformedInput);
                                        modelCtrl.$render();	
                   }        
        
                   return transformedInput;
               });
             }
           };
        });

		appModule.directive('fileModel', ['$parse', function($parse) {
            return {
                restrict: 'A',
                link: function(scope, element, attrs) {
                    var model = $parse(attrs.fileModel);
                    var modelSetter = model.assign;
					var maxSize = 5 * (1024*1024) ; //%MB
					// var maxSizeInByte = -1 ; //2000 B
					// if(attrs.maxSize){
					// 	if(attrs.maxSize.endsWith("MB") ){
					// 		maxSizeInByte = parseInt(attrs.maxSize.slice(0, attrs.maxSize.length -2 )) * (1024*1024) ;
					// 	}
					// }
					var allowType = ["application/pdf","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","application/vnd.openxmlformats-officedocument.wordprocessingml.document" ,
					"application/vnd.ms-excel","application/msword"];

					element.bind('change', function(event) {
						// console.dir(event.target.value);
                        scope.$apply(function() {
// 							var f = var formData = {
//       'name'              : $('input[name=name]').val(),
//       'superheroAlias'    : $('input[name=superheroAlias]').val()
//   };;
							// if(!element[0].files){
							// 	f = event.target.value;
								
							// }else{
							// 	f = element[0].files[0] ;
							// }
							var f = element[0].files[0] ;
							
							var ret = {};
							ret.file = f ;
							ret.valid = true ;
							if ( allowType.indexOf(f.type) < 0  ){
								ret.valid = false;
								ret.err = "Invalid upload file extension. Allows upload only .pdf, .xls, .xlsx, .doc, .docx file"
								modelSetter(scope, ret);
								return;
							}
							if ( f.size > maxSize  ) {
								ret.valid = false ; 
								ret.err = "Cannot upload file size more than 5 MBytes"
								modelSetter(scope, ret);
								return;
							}

                            modelSetter(scope, ret);
                        });
                    });
                }
            };
        }]);

        </script>
         <script src="<c:url value='/js/controllers/pmformcontroller.js'/>" charset="utf-8"></script>
         <script src="<c:url value='/js/filters/pm-status-filter.js'/>" charset="utf-8"></script>
         <script src="<c:url value='/js/controllers/pmscontroller.js'/>" charset="utf-8"></script>
         <script src="<c:url value='/js/controllers/pmlistcontroller.js'/>" charset="utf-8"></script>
         
         <script src="<c:url value='/js/controllers/pmformwizardcontroller.js'/>" charset="utf-8"></script>
         <script src="<c:url value='/js/controllers/pmformmaintenancecontroller.js'/>" charset="utf-8"></script>
         <script src="<c:url value='/js/controllers/reportcontroller.js'/>" charset="utf-8"></script>
         <script src="<c:url value='/js/controllers/competencycontroller.js'/>" charset="utf-8"></script>
         
	<!--
	REQUIRED
	You must include this in your project.

	RECOMMENDED
	This category must be included but you may modify which plugins or components which should be included in your project.

	OPTIONAL
	Optional plugins. You may choose whether to include it in your project or not.

	Detailed information and more samples can be found in the document.
	-->
</body>
</html>
