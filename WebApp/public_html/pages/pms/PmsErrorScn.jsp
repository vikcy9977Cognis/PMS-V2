<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ page isELIgnored="false" %>
<html lang="en">
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
       
        <!-- Customize Style -->
        <link href="<c:url value='/css/style.css'/>" rel="stylesheet">

 
    
	<!--SCRIPT-->
	<!--=================================================-->
	<!--JAVASCRIPT-->
	<!--=================================================-->
  <script src="<c:url value='/js/jquery-1.11.3.js'/>" type="text/javascript"> </script>
  
  

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="<c:url value='/js/html5shiv.min.js'/>" type="text/javascript"> </script>
      <script src="<c:url value='/js/respond.min.js'/>" type="text/javascript"> </script>
    <![endif]-->

</head>
 
<!--TIPS-->
<!--You may remove all ID or Class names which contain "demo-", they are only used for demonstration. -->

<body>
	

		
		<div id="container" class="cls-container">
		
		<!-- HEADER -->
		<!--===================================================-->
		<div class="cls-header">
			<div class="cls-brand">
				<a class="box-inline" href="javascript:void(0)">
					<span class="brand-title">RCL - Performance Management System <span class="text-thin">v.${pmsVersion}</span></span>
				</a>
			</div>
		</div>
		
		<!-- CONTENT -->
		<!--===================================================-->
		<div class="cls-content">
			<h3 class="error-code text-danger">ERROR</h3>
			<p class="h4  pad-btm mar-btm">
				
				${statusMessage}
			</p>
			
			
			
		</div>
		
		
	</div>



	
	<!--===================================================-->
	<!-- END OF CONTAINER -->



	<!--Nifty Admin [ RECOMMENDED ]-->

	<script src="<c:url value='/js/vendor/nifty.js'/>" type="text/javascript"> </script>
      
</body>
</html>
