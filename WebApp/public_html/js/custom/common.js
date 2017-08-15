/**
 * Contains General Purpose functions.
 */
 
 
 function showStatusMessage(resultStatus) {
    var statusBox = $('#statusMessageBox');
    if (resultStatus.statusCode === 0) {
        // Success
        statusBox.removeClass("alert-danger");
        statusBox.addClass("alert-success");
        statusBox.find('#statusMessageBody').html('<strong>Done! </strong>' + resultStatus.statusMessage);
    } else {
        // Error
        statusBox.removeClass("alert-success");
        statusBox.addClass("alert-danger");
        statusBox.find('#statusMessageBody').html('<strong>Failed! </strong>' + resultStatus.statusMessage);
    }
    statusBox.show();
}
/**
* Reset status message.
*/
function clearStatusMessage() {
    $('#statusMessageBox').hide();
}

 $(function() {
    // Accept only integer number
$(".integer").on('keypress', function (e) {
    // Validate allow input only number.
    var evt = (e) ? e : window.event;
    var charCode = (evt.keyCode) ? evt.keyCode : evt.which;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;

});

// Accept only decimal number
$(".decimal").on('keypress', function (e) {

    var evt = (e) ? e : window.event;
    var charCode = (evt.keyCode) ? evt.keyCode : evt.which;
    if (
            (charCode != 45 || $(this).val().indexOf('-') != -1) &&      // â€œ-â€? CHECK MINUS, AND ONLY ONE.
            (charCode != 46 || $(this).val().indexOf('.') != -1) &&      // â€œ.â€? CHECK DOT, AND ONLY ONE.
            (charCode < 48 || charCode > 57))
        return false;

    return true;

});
   // Initialize Chosen controls.
    $(".chosen").chosen( {width: '100%'}); 
       // File upload
    $("input:file").each(function () {
        var fileUpload = $(this);
        var button = $('<input type="button" id="btnChoosefile" value="Choose file .." />')
            .prop('disabled', fileUpload.prop('disabled'));


        button.click(function () {
            fileUpload.click();
        });

        fileUpload.after(button); /*.after(textBox); */
        fileUpload.hide();
    });
       $("input[name=FileName]").attr("readonly", true);
/*
    $("input[name=file]").on('change', function (event) {
        //  textBox.val(fileUpload.val());
          
       
        var txtFileName = $("input[name=FileName]");
        txtFileName.val("");
		  
        if (document.getElementById("file").files.length > 0) {
            var filename = document.getElementById("file").files[0].name;
            //  console.log(filepath);             
            if (filename && filename.length > 0) {
                txtFileName.val(filename);
            }
        }

    });
*/
 });
 