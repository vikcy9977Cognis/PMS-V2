$(function() {
      
        
        // BOOTSTRAP DATEPICKER WITH RANGE SELECTION
	// =================================================================
	// Require Bootstrap Datepicker
	// http://eternicode.github.io/bootstrap-datepicker/
	// =================================================================
	
        $('#demo-dp-range .input-daterange').datepicker({
	
                format: "dd-mm-yyyy",
		todayBtn: "linked",
		autoclose: true,
		todayHighlight: true
	});
     
    // $(".date-picker").datepicker();
       
      // Append empty element to POD and POL
   $("#cmbPOL").prepend("<option value='' selected='selected'>&nbsp;</option>");
   $("#cmbPOD").prepend("<option value='' selected='selected'>&nbsp;</option>");
  
     // Manually trigger chose control its is changed.
      // Refresh select value to show selected item
    $("select").each(function() {
     var strVal = $(this).attr("value");
     $(this).val(strVal).trigger('change'); 
     if ($(this).hasClass("chosen")) {
        $(this).trigger('chosen:updated');
     }
    });
    /* 
    var dtContactList = $('#dtInitiatorList').DataTable({
        "processing": true,
        "serverSide": false,
        "responsive": true,
        "searching": false,
        "orderable": false,
        "ordering": false,
        "paging": true,
        "info": true}); */
        
});