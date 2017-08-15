/*-----------------------------------------------------------------------------------------------------------  
RutMessage.js
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Manop Wanngam 10/10/07 
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

// function for floating a message  at the bottom of a window
function floatMsgBottom() {
    var intStartX = 0;
    var intStartY = 30;
    var objNs = (navigator.appName.indexOf("Netscape") != -1);
    var docMessage = document;
    
    function floatElement(id) {
        var elMessage = docMessage.getElementById?docMessage.getElementById(id):docMessage.all?docMessage.all[id]:docMessage.layers[id];
        if (docMessage.all) {
            elMessage.style.visibility = 'visible';
        }
        if (docMessage.layers) {
            elMessage.style = elMessage;
        }
        elMessage.sP = function(x, y) {
            this.style.left = x;
            this.style.top=y;
        }
        elMessage.x = intStartX;
        if (verticalpos == "fromtop") {
            elMessage.y = intStartY;
        } else {
            elMessage.y = objNs ? pageYOffset + innerHeight : document.body.scrollTop + document.body.clientHeight;
            elMessage.y -= intStartY;
        }
        return elMessage;
    }
    window.stayTopLeft = function() {
        if (verticalpos == "fromtop") {
            var pY = objNs ? pageYOffset : document.body.scrollTop;
            objFloat.y += (pY + intStartY - objFloat.y)/8;
        } else {
            var pY = objNs ? pageYOffset + innerHeight : document.body.scrollTop + document.body.clientHeight;
            objFloat.y += (pY - intStartY - objFloat.y)/8;
        }
        objFloat.sP(objFloat.x, objFloat.y);
        setTimeout("stayTopLeft()", 10);
    }
    objFloat = floatElement("divStayTopLeft");
    stayTopLeft();
}

function showBarMessage(strTdName, strMessage) {
    objTd = document.all(strTdName);
    if (objTd != null) {
        objTd.innerHTML = strMessage;
    }
}

function showBarMessage(strTdName, strMessage, intErrCode) {
    objTd = document.all(strTdName);
    if (objTd != null) {
        if (intErrCode == 1) {
            objTd.innerHTML = "<Font color=red>" + strMessage + "</font>";
        } else {
            objTd.innerHTML = strMessage;
        }
    }
}
