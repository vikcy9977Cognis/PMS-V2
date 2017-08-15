/*-----------------------------------------------------------------------------------------------------------  
RutDisable.js
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 07/01/08
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

<!--
var strMessage="Sorry, clicking right mouse is not allowed!";

function clickIE(){
    if (event.button==2){
        alert(strMessage);
        return false;
    }
}

function clickNS(e){
    if (document.layers||document.getElementById&&!document.all){
        if (e.which==2||e.which==3){
            alert(strMessage);
            return false;
        }
    }
}

if (document.layers){
    document.captureEvents(Event.MOUSEDOWN);
    document.onmousedown=clickNS;
}else if (document.all&&!document.getElementById){
    document.onmousedown=clickIE;
}

document.oncontextmenu=new Function("alert(strMessage);return false")

function disable(){
    var strType = window.event.type;
    var intCode = window.event.keyCode;
    //alert(strType);
    //alert(intCode);
    if (event.button == 2){
        alert("Sorry, clicking right mouse is not allowed!")
    }
    if ( (78 == window.event.keyCode) && (event.ctrlKey) ) {
        alert("Sorry, clicking Ctrl+N is not allowed!");//Ctrl+N
    }
    if ( (79 == window.event.keyCode) && (event.ctrlKey) ) {
        alert("Sorry, clicking Ctrl+O is not allowed!");//Ctrl+O
    }
    if ( (37 == window.event.keyCode) && (event.altKey) ) {
        alert("Sorry, clicking Alt+Left arrow is not allowed!");//Alt+Left arrow
    }
    if ( (39 == window.event.keyCode) && (event.altKey) ) {
        alert("Sorry, clicking Alt+Right arrow is not allowed!");//Alt+Right arrow
    }
}
// --> 
