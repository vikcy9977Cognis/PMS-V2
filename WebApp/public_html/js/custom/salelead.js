function checkEnableControlsForInitiator() {
   

   
    if (strSLStatus == '' || strSLStatus == 'O') {
    
          // Enable all text input controls.
        $("input.form-control").prop("readonly", false);
        $("input[type=radio]").prop("disabled", false);
    //    $("select.form-control").attr("disabled", "false");
        $("select.form-control").prop("disabled", false);
        $("#cmbStatus").prop("disabled", true);
        $("#cmbReason").prop("disabled", true);
       
        $("textarea.form-control").prop("readonly", false); 
       // $(".control-label").addClass("required");
         $("#txtRefNumber").prop("readonly", true);
        $("#txtSLCreateDate").prop("readonly", true);
        $("#txtSLCreateDate").prop("readonly", true);
         $("#txtInitiatorOffice").prop("readonly", true);
        $("#txtInitiatorSales").prop("readonly", true);
        $("#txtRecipientSale").prop("readonly", true);
        $("#txtTargetVolWeekEQ20").prop("readonly", true);
        $("#txtTargetVolWeekEQ40").prop("readonly", true);
      //  $("#cmbCommodityCodeDesc").prop("readonly", true);
        
        $("#btnSave").prop("disabled", false);
        if (strRecordAddUser.length > 0) {
            if (strLoginID == strRecordAddUser) {
                
                $("#txaDialog").prop('readonly', true);
                $("#txtFile").prop('readonly', false);
                $("#btnUpdateDialog").prop("disabled", true);
                $("#btnUploadFile").prop("disabled", false);
                $("#btnChoosefile").prop("disabled", false);
             //   $("#txtFileName").prop('readonly', false);
                $("#btnDeleteAttach").prop("disabled", false);
                $("#btnSend").prop("disabled", false);
            } else {                
                $("#btnSend").prop("disabled", true);
                $("#txaDialog").prop('readonly', true);
                $("#txtFile").prop('readonly', true);
                $("#btnUpdateDialog").prop("disabled", true);
                $("#btnUploadFile").prop("disabled", true);
                $("#btnChoosefile").prop("disabled", true);
          //       $("#txtFileName").prop('readonly', true);
                $("#btnDeleteAttach").prop("disabled", true);
            }
        } else {
                $("#txaDialog").prop('readonly', true);
                $("#txtFile").prop('readonly', false);
                $("#btnUpdateDialog").prop("disabled", true);
                $("#btnUploadFile").prop("disabled", false);
                $("#btnChoosefile").prop("disabled", false);
         //       $("#txtFileName").prop('readonly', false);
                $("#btnDeleteAttach").prop("disabled", false);
        }
        $("#cmbRecipientOffice").prop("disabled", false);
        $("select.chosen").trigger("chosen:updated");
    } else if (strSLStatus == 'N' || strSLStatus == 'I' ||strSLStatus == 'W') {
      
        $("#btnSend").prop("disabled", true);
        if (strLoginID == strRecordAddUser || strLoginID == strSendUser) {
              
                $("#txaDialog").prop('readonly', false);
                $("#txtFile").prop('readonly', false);
                $("#btnUpdateDialog").prop("disabled", false);
                $("#btnUploadFile").prop("disabled", false);
                $("#btnChoosefile").prop("disabled", false);
         //       $("#txtFileName").prop('readonly', false);
                $("#btnDeleteAttach").prop("disabled", false);
        } else {
               $("#btnSend").prop("disabled", true);
        }
     $("#btnSave").prop("disabled", true);        
    } else if (strSLStatus == 'F' || strSLStatus == 'C') {
         $("#btnSave").prop("disabled", true);
          $("#btnSend").prop("disabled", true);
          
          if (strLoginID == strRecordAddUser || strLoginID == strSendUser) {
           
                $("#txaDialog").prop('readonly', false);
                $("#txtFile").prop('readonly', false);
                $("#btnUpdateDialog").prop("disabled", false);
                $("#btnUploadFile").prop("disabled", false);
                $("#btnChoosefile").prop("disabled", false);
         //       $("#txtFileName").prop('readonly', false);
                $("#btnDeleteAttach").prop("disabled", false);
        }
    } 
}
function checkEnableControlsForRecipient() {
 $("#btnSave").hide();
      if ( strSLStatus == 'N') {
        $("#btnSave").prop("disabled", true);      
        $("#btnAction").prop("disabled", false);
        $("#btnSubmitToHQ").prop("disabled", true);
        
      }  else if (strSLStatus == 'I') {
        $("#btnAction").prop("disabled", true);
        if (strLoginID == strActionUser) {
             $("#btnSave").prop("disabled", false);
              $("#btnSubmitToHQ").prop("disabled", false);
               $("#cmbReason").prop("disabled", false);
               $("#cmbReason").addClass("required");
               $("label[for=cmbReason]").addClass("required"); 
                $("#txaDialog").prop('readonly', false);
                $("#txtFile").prop('readonly', false);
                $("#btnUpdateDialog").prop("disabled", false);
                $("#btnUploadFile").prop("disabled", false);
                $("#btnChoosefile").prop("disabled", false);
      //          $("#txtFileName").prop('readonly', false);
                $("#btnDeleteAttach").prop("disabled", false);
        } else {
             $("#btnSave").prop("disabled", true);
              $("#btnSubmitToHQ").prop("disabled", true);
              $("#cmbReason").prop("disabled", true);
              
              $("#btnSend").prop("disabled", true);
                $("#txaDialog").prop('readonly', true);
                $("#txtFile").prop('readonly', true);
                $("#btnUpdateDialog").prop("disabled", true);
                $("#btnUploadFile").prop("disabled", true);
                $("#btnChoosefile").prop("disabled", true);
       //          $("#txtFileName").prop('readonly', true);
                $("#btnDeleteAttach").prop("disabled", true);
        }
         $("select.chosen").trigger("chosen:updated");
      } else if (strSLStatus == 'W') {
        $("#btnAction").prop("disabled", true);
        $("#btnSubmitToHQ").prop("disabled", true);
        if (strLoginID == strActionUser) {
             $("#btnSave").prop("disabled", false);
             
                $("#txaDialog").prop('readonly', false);
                $("#txtFile").prop('readonly', false);
                $("#btnUpdateDialog").prop("disabled", false);
                $("#btnUploadFile").prop("disabled", false);
                $("#btnChoosefile").prop("disabled", false);
                $("#txtFileName").prop('readonly', false);
                $("#btnDeleteAttach").prop("disabled", false);
        } else {
             $("#btnSave").prop("disabled", true);
              $("#btnSend").prop("disabled", true);
                $("#txaDialog").prop('readonly', true);
                $("#txtFile").prop('readonly', true);
                $("#btnUpdateDialog").prop("disabled", true);
                $("#btnUploadFile").prop("disabled", true);
                $("#btnChoosefile").prop("disabled", true);
        //         $("#txtFileName").prop('readonly', true);
                $("#btnDeleteAttach").prop("disabled", true);
        }
      } else if (strSLStatus == 'F' || strSLStatus == 'C') {
        $("#btnAction").prop("disabled", true);
        $("#btnSubmitToHQ").prop("disabled", true);
         $("#btnSave").prop("disabled", true);
         
          if (strLoginID == strActionUser) {
            
                $("#txaDialog").prop('readonly', false);
                $("#txtFile").prop('readonly', false);
                $("#btnUpdateDialog").prop("disabled", false);
                $("#btnUploadFile").prop("disabled", false);
                $("#btnChoosefile").prop("disabled", false);
         //       $("#txtFileName").prop('readonly', false);
                $("#btnDeleteAttach").prop("disabled", false);
        } else {
             
                $("#txaDialog").prop('readonly', true);
                $("#txtFile").prop('readonly', true);
                $("#btnUpdateDialog").prop("disabled", true);
                $("#btnUploadFile").prop("disabled", true);
                $("#btnChoosefile").prop("disabled", true);
       //          $("#txtFileName").prop('readonly', true);
                $("#btnDeleteAttach").prop("disabled", true);
        }
      }
}
function checkEnableControlsForApprover() {
 $("#btnSave").hide();
     if ( strSLStatus == 'N' || strSLStatus == 'I' ) {
        $("#btnSave").prop("disabled", true);      
        $("#btnApprove").prop("disabled", true);
        $("#btnReject").prop("disabled", true);       
      } else if (strSLStatus == 'W') {
        $("#btnSave").prop("disabled", false);      
        $("#btnApprove").prop("disabled", false);
        $("#btnReject").prop("disabled", false);
         $("#cmbStatus").prop("disabled", false);
          $("#cmbStatus").addClass("required"); 
          $("label[for=cmbStatus]").addClass("required"); 
          
           $("#txaDialog").prop('readonly', false);
              $("#txaDialog").prop('readonly', false);
                $("#txtFile").prop('readonly', false);
                $("#btnUpdateDialog").prop("disabled", false);
                $("#btnUploadFile").prop("disabled", false);
                $("#btnChoosefile").prop("disabled", false);
       //         $("#txtFileName").prop('readonly', false);
                $("#btnDeleteAttach").prop("disabled", false);
      } else if (strSLStatus == 'F' || strSLStatus == 'C') {
            $("#btnSave").prop("disabled", true);      
            $("#btnApprove").prop("disabled", true);
            $("#btnReject").prop("disabled", true);     
           $("#txaDialog").prop('readonly', false);
            $("#txtFile").prop('readonly', false);
            $("#btnUpdateDialog").prop("disabled", false);
            $("#btnUploadFile").prop("disabled", false);
            $("#btnChoosefile").prop("disabled", false);
       //     $("#txtFileName").prop('readonly', false);
            $("#btnDeleteAttach").prop("disabled", false)
      }
}
function checkEnableControls(role) {

    
        // Disable all text input controls.
        $("input.form-control").prop("readonly", true);
        $("input[type=radio]").prop("disabled", true);
        $("select.form-control").attr("disabled", "disabled");
        $("select.chosen").trigger("chosen:updated");
        $("textarea.form-control").prop("readonly", true); 
        $("input[type=button]").prop("disabled", true);
           $("#btnBack").prop("disabled", false);
   //     $(".control-label").removeClass("required");
    /*           
    if (role != 'initiator') {
        // Enable controls only allow to edit by recipient.
        $("#cmbReason").removeAttr("disabled");
        $("label[for=cmbReason]").addClass("required");
        
     //   $("#txaDialog").prop('readonly', false);
     //   $("#txtFile").prop('readonly', false);
    */
     
    if (role == 'initiator') {    
        checkEnableControlsForInitiator();
    } else if (role == 'recipient') {
        checkEnableControlsForRecipient();
    } else if (role == 'hq') {
        checkEnableControlsForApprover();
    }
}
function displaySaleLeadDialog(strUrl, jsonGetDialogData) {
$.ajax({
        type: "POST",
        url: strUrl,
        data: jsonGetDialogData,
        datatype: "application/json"

    })
     .done(function (jsonData) {
         
        // showStatusMessage(jsonData);
        var ul = $("#previousDialog").find("ul");
        var strHtmlTxt = "";
        for( var idx in jsonData) {
            strHtmlTxt = strHtmlTxt + "<li>" + jsonData[idx].dialogMsg + "</li>";
        }
        ul.html(strHtmlTxt);
        $("#txaDialog").val("");
     }
     );
}
function addSaleLeadDialog(strUrl,jsonAddDialogData) {
    $.ajax({
        type: "POST",
        url: strUrl,
        data: jsonAddDialogData,
        datatype: "application/json"

    })
     .done(function (jsonData) {
         
       // Refresh previous dialog
       displaySaleLeadDialog(strUrl, jsonGetDialogData);
     });
}
$(function() {
    // Validate on chonsen select change.
    $(".chosen").on('change', function() {
        var strName = $(this).attr('name');
        $("label.error[for="+ strName + "]").hide();
    });
    // Hook approve button click
    $("#btnApprove").on('click', function(event) {
        var strSelectedSLStatus = $("#cmbStatus").val();
        if (strSelectedSLStatus == 'F' || strSelectedSLStatus == 'C') {
            return true;
        } else {
            $("#lblStatus").show();
           return false;
        } 
        return true;
    });
    $("#cmbStatus").on('change', function() {
        if ($("#lblStatus").length > 0) {
         $("#lblStatus").hide();
        }
    });
   // Hook form submit event() 
   $("#frmSaleLead").on("submit", function(event) {
    //   event.preventDefault();
       clearStatusMessage();
       var frm = $(this);
       frm.validate();
       if (!frm.valid()) {
            var jsonMsg = {statusCode: 1,
                            statusMessage: "Please enter data for the required fields in form"};
            showStatusMessage(jsonMsg);
           return false;
       }
       return true;
   });
    // Display Commodity Description when Commodity code is selected.
  /*  $("#cmbCommodityCode").on('change', function() {
        var strDesc = $("#cmbCommodityCode option:selected").text();
        $("#cmbCommodityCodeDesc").val(strDesc);
    });
   */ 
  
   
    // Refresh select value to show selected item
    $("select").each(function() {
     var strVal = $(this).attr("value");
     $(this).val(strVal).trigger('change'); 
     if ($(this).hasClass("chosen")) {
        $(this).trigger('chosen:updated');
     }
    });
       
              
        // Populate Previous Dialog
        displaySaleLeadDialog(strUrl, jsonGetDialogData);
        // Clear dialog input box
         $("#txaDialog").text("");
        // Hook add Sale lead dialog
        $('#btnUpdateDialog').on('click', function() {
            var strDialogMsg = $("#txaDialog").val().trim(); 
            if (strDialogMsg.length == 0) {
                $("#lblDialog").show();
                $("#txaDialog").focus();
                return;
            }
            $("#lblDialog").hide();
            jsonAddDialogData.dialogMsg = strDialogMsg;
            addSaleLeadDialog(strUrl,jsonAddDialogData);
        }); 
        // Attach file list table.
          var dtAttachFile = $('#dtAttachFile').DataTable({
          "serverSide": true,
        "responsive": true,
        "searching": false,
        "orderable": false,
        "ordering": false,
        "paging": false,
        "info": false,
        "bAutoWidth": false,
        pageLength: nPageLength,
        ajax: {
            url: strUrl,
            data: jsonGetAttachFileData,
            type: 'POST'
        },
        columns: [
            
            {
                data: "filename",
                width: "80%",
                "createdCell": function (td, cellData, rowData, row, col) {
                    var htmlTxt = "<a class=\"btn-link\" href=\"" + strUrl + "?service=" + strService +"&role="+ strRole + "&pageAction=downloadAttach&fileSeqNo=" + rowData["hdrSeqNo"] + "\">" + cellData + "</a>";

                    $(td).html(htmlTxt);
                }
            },
            {
                data: "uploaddate"                
            }
        ] 
    });
    
      var selectedAttachDeleteRows = [];
    var QNT_ATTACHFILE_KEY = "hdrSeqNo";
    $('#dtAttachFile tbody').on('click', 'tr', function () {
        $(this).toggleClass('selected');

        var rowData = dtAttachFile.row(this).data();
        //  console.log(rowData);
        if (rowData != null) {
        var id = rowData[QNT_ATTACHFILE_KEY];
        var index = $.inArray(id, selectedAttachDeleteRows);
        if (index == -1) {
            // Add to selected delete list if not found in list
            selectedAttachDeleteRows.push(id);
        } else {
            // Remove from selected delete list if already existed
            selectedAttachDeleteRows.splice(index, 1);
        }
        }
    });
    $('#btnDeleteAttach').on('click', function (event) {
        //  dtVendorList.row('.selected').remove().draw(false);
        event.preventDefault();

        if (selectedAttachDeleteRows.length > 0) {
            var htmlTxt = "";
            for (var idx in selectedAttachDeleteRows) {
                // htmlTxt = htmlTxt + selectedAttachDeleteRows[idx] + "<br/>";
                var htmlTxt = "Are you sure want to permanently delete these " + selectedAttachDeleteRows.length + "items?";
            }
            bootbox.dialog({
                message: htmlTxt,
                title: "Confirm Delete Attach",
                buttons: {
                    confirm: {
                        label: "Confirm",
                        className: "btn-success",
                        callback: function () {
                            clearStatusMessage();


                        //    var paramData = $.param({ 'SelectedRows[]': selectedAttachDeleteRows });
                            jsonDeleteAttachFileData.delAttachFiles = selectedAttachDeleteRows
                            $.ajax({
                                type: "POST",
                                url: strUrl,
                                data: jsonDeleteAttachFileData,
                                datatype: "application/json"

                            })
                             .done(function (jsonData) {
                                 // Reload table data
                                 dtAttachFile.draw();
                                 // Clear selected delete row
                                 selectedAttachDeleteRows = [];
                                 // Show status message
                                  showStatusMessage(jsonData);
                             }
                             );
                        }
                    },
                    cancel: {
                        label: "Cancel",
                        className: "btn-default"

                    }
                }
            });

        } else {
            bootbox.alert("There is no selected row to be deleted");
        }

    });// End handle  Delete Attach button 

    $('#frmAttachFile').ajaxForm({
        beforeSubmit: function (a, f, o) {
            //   o.dataType = $('#uploadResponseType')[0].value;
            //   $('#uploadOutput').html('Submitting...');


            //     if (!$("#addAttachForm").valid())
            //         return false;
            console.log("uploading ..");
             clearStatusMessage();
        },
        success: function (jsonData) {
            if (jsonData.statusCode == 0) {
                // Reload table data
                dtAttachFile.draw();
                // Clear selected delete row
                selectedAttachDeleteRows = [];

                // Reset form data
                document.getElementById("frmAttachFile").reset();
               $("#file").val("");
               $("#txtFileName").val("");
              
            } else {
                // Show error message status in modal dialog
                showStatusMessage(jsonData);
            }
             $("#btnUploadFile").show();
            $("#uploadSpiner").hide();
        }
    });

    // Handle Save Attach
    $("#btnUploadFile").on('click', function (event) {
        event.preventDefault();

        var strFilename = $.trim( $("#txtFileName").val());
        if (strFilename == "") {
            $("#lblFilename").show();
        } else {
            $("#lblFilename").hide();
        }
        var frmAttach = $('#frmAttachFile');    
        frmAttach.append(document.getElementById("file"));
     //   frmAttach.find("input:file").val( $("#file").val());
         $("#btnUploadFile").hide();
         $("#uploadSpiner").show();
        frmAttach.submit();

    });// End Save Attach button
    $("input[name=txtFileName]").attr("readonly", true);
    $("textarea[maxlength]").bind('input propertychange', function() {  
        var maxLength = $(this).attr('maxlength');  
        if ($(this).val().length > maxLength) {  
            $(this).val($(this).val().substring(0, maxLength));  
        }  
    })  
    $("input[name=file]").on('change', function () {
        //  textBox.val(fileUpload.val());


        var txtFileName = $("input[name=txtFileName]");
        txtFileName.val("");

        if (document.getElementById("file").files.length > 0) {
            var filename = document.getElementById("file").files[0].name;
            //  console.log(filepath);             
            if (filename && filename.length > 0) {
                txtFileName.val(filename);
            }
        }

    });

});