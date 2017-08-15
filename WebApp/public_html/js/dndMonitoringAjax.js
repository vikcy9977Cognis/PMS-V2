var billing_type;
/* 
 * creates a new XMLHttpRequest object which is the backbone of AJAX, 
 * or returns false if the browser doesn't support it 
 */
 
function getXMLHttpRequest() { 
      var xmlHttpReq = false; 
      // to create XMLHttpRequest object in non-Microsoft browsers 
      if (window.XMLHttpRequest) { 
        xmlHttpReq = new XMLHttpRequest(); 
      } else if (window.ActiveXObject) { 
        try { 
          // to create XMLHttpRequest object in later versions 
          // of Internet Explorer 
          xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP"); 
        } catch (exp1) { 
          try { 
            // to create XMLHttpRequest object in older versions 
            // of Internet Explorer 
            xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP"); 
          } catch (exp2) { 
            xmlHttpReq = false; 
          } 
        } 
      } 
      return xmlHttpReq; 
} 

function validate_input () {

    try {        
        
        var xmlHttpRequest = getXMLHttpRequest(); 
        xmlHttpRequest.onreadystatechange = getReadyStateHandler(xmlHttpRequest, "dndMonitoring_validate_input"); 
        
        xmlHttpRequest.open("POST", "/MSTWebApp/RrcStandardSrv?service=ui.dnd.DndMonitoringReportSvc", true); 
        
        xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");   
        
        var url = "part=validate&txtPol=" + document.frm.txtPOL.value + 
                                "&txtShipper=" + document.frm.txtShipper.value+ 
                                "&txtConsignee=" + document.frm.txtConsignee.value;
       
        xmlHttpRequest.send(url);                 
                    
    } catch (err) {    
        //alert(err);
    }    

}


function getDndMonitoringLevel1() { 

    try {
    
        var xmlHttpRequest = getXMLHttpRequest(); 
        xmlHttpRequest.onreadystatechange = getReadyStateHandler(xmlHttpRequest, "dndMonitoring_lvl1"); 
        
        xmlHttpRequest.open("POST", "/MSTWebApp/RrcStandardSrv?service=ui.dnd.DndMonitoringReportSvc", true); 
        
        xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");   
        
        var url = "part=findLevel1&txtStartDate=" + document.frm.txtStartDate.value + 
                                "&txtEndDate=" + document.frm.txtEndDate.value +  
                                "&cmbCountry=" + document.frm.cmbCountry.value + 
                                "&cmbPod=" + document.frm.cmbPOD.value + 
                                "&txtPol=" + document.frm.txtPOL.value + 
                                "&txtShipper=" + document.frm.txtShipper.value+ 
                                "&txtConsignee=" + document.frm.txtConsignee.value;
       
        xmlHttpRequest.send(url);                 
                    
    } catch (err) {    
        //alert(err);
    }    
} 

function getDndMonitoringLevel2(parameters_lvl2) { 

    try {       
        
        var xmlHttpRequest = getXMLHttpRequest(); 
        xmlHttpRequest.onreadystatechange = getReadyStateHandler(xmlHttpRequest, "dndMonitoring_lvl2"); 
        
        xmlHttpRequest.open("POST", "/MSTWebApp/RrcStandardSrv?service=ui.dnd.DndMonitoringReportSvc", true); 
        
        xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");   
        
        var lvl2 = parameters_lvl2.split("|");       
       
        var url = "part=findLevel2&pol=" + lvl2[0] + 
                                "&pod=" + lvl2[1] +  
                                "&billing_type=" + lvl2[2] + 
                                "&actual_days=" + lvl2[3] + 
                                "&qtn_free_days=" + lvl2[4] + 
                                "&units=" + lvl2[5] + 
                                "&combine=" + lvl2[6];
                                
                                
        billing_type = lvl2[2];
       
        xmlHttpRequest.send(url); 
                    
    } catch (err) {    
        alert(err);
    }    
}

function getPODFromCountry(cmbCountry) {

    try {       
        
        var selectedCountry = cmbCountry.options[cmbCountry.selectedIndex].value;
        
        if (cmbCountry.selectedIndex > 0) {

            var xmlHttpRequest = getXMLHttpRequest(); 
            xmlHttpRequest.onreadystatechange = getReadyStateHandler(xmlHttpRequest, "getPODFromCountry"); 
            
            xmlHttpRequest.open("POST", "/MSTWebApp/RrcStandardSrv?service=ui.dnd.DndMonitoringReportSvc", true); 
            
            xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");  
           
            var url = "part=getPODFromCountry&country=" + selectedCountry;
           
            xmlHttpRequest.send(url); 
        } else {
            
            document.frm.cmbPOD.options.length = 0;
        }
                    
    } catch (err) {    
        alert(err);
    }    
}

function getCountry() {

    try {  
       
        var xmlHttpRequest = getXMLHttpRequest(); 
        xmlHttpRequest.onreadystatechange = getReadyStateHandler(xmlHttpRequest, "getCountry"); 
        
        xmlHttpRequest.open("POST", "/MSTWebApp/RrcStandardSrv?service=ui.dnd.DndMonitoringReportSvc", true); 
        
        xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");  
       
        var url = "part=getCountry";
       
        xmlHttpRequest.send(url); 
                    
    } catch (err) {    
        alert(err);
    }    
}

function printExcel(strReportFormat, reportName) {

     try {       
        
        var xmlHttpRequest = getXMLHttpRequest(); 
        xmlHttpRequest.onreadystatechange = getReadyStateHandler(xmlHttpRequest, "printExcel"); 
        
        xmlHttpRequest.open("POST", "/MSTWebApp/RrcStandardSrv?service=ui.dnd.DndMonitoringReportSvc", true); 
        
        xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");   
       
        var url = "part=printExcel&reportFormat=" + strReportFormat + 
                                "&reportName=" + reportName;
       
        xmlHttpRequest.send(url); 
                    
    } catch (err) {    
        alert(err);
    }  
}
  
/* 
 * Returns a function that waits for the state change in XMLHttpRequest 
 */
function getReadyStateHandler(xmlHttpRequest, part) { 
  
      // an anonymous function returned 
      // it listens to the XMLHttpRequest instance       
      return function() {
      
        var loadingMsg;
        loadingMsg = "";
        loadingMsg = loadingMsg + "<table border=0 cellSpacing=1 cellPadding=0 width=100%>";
        loadingMsg = loadingMsg + "<tr><td class=TableLeftText colSpan=5>Loading ...</td></tr>";
        loadingMsg = loadingMsg + "</table>";
        
        if (part == "dndMonitoring_lvl1") {
        
            document.getElementById("dndMonitoringLevel1").innerHTML = loadingMsg;        
            document.getElementById("dndMonitoringLevel2").innerHTML = "";
        
            if (xmlHttpRequest.readyState == 4) { 
              if (xmlHttpRequest.status == 200) {   
                
                var doc = eval("(" + xmlHttpRequest.responseText + ")");              
                document.getElementById("dndMonitoringLevel1").innerHTML = generateHTML_dndMonitoringLevel1(doc);                         
                
              } else { 
                alert("HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText); 
              } 
            }
        } else if (part == "dndMonitoring_lvl2"){
        
            document.getElementById("dndMonitoringLevel2").innerHTML = loadingMsg; 
            
            if (xmlHttpRequest.readyState == 4) { 
              if (xmlHttpRequest.status == 200) {   
                
                var doc = eval("(" + xmlHttpRequest.responseText + ")");              
                document.getElementById("dndMonitoringLevel2").innerHTML = generateHTML_dndMonitoringLevel2(doc);                         
                
              } else { 
                alert("HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText); 
              } 
            }
        } else if (part == "getPODFromCountry"){
        
            document.getElementById("ajaxCmbPod").innerHTML = "<select size=1 name=cmbPOD class=FormDropDown style=\"width:80px\"><option value=\"\"></option>"; 
            
            if (xmlHttpRequest.readyState == 4) { 
              if (xmlHttpRequest.status == 200) {   
                
                var doc = eval("(" + xmlHttpRequest.responseText + ")");              
                document.getElementById("ajaxCmbPod").innerHTML = generateHTML_dndMonitoringPortCountry(doc);                         
                
              } else { 
                alert("HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText); 
              } 
            }
        } else if (part == "getCountry"){
        
            document.getElementById("ajaxCmbCountry").innerHTML = "<select size=1 name=cmbCountry class=FormDropDown style=\"width:120px\"><option value=\"\"></option>;"; 
            
            if (xmlHttpRequest.readyState == 4) { 
              if (xmlHttpRequest.status == 200) {   
                
                var doc = eval("(" + xmlHttpRequest.responseText + ")");              
                document.getElementById("ajaxCmbCountry").innerHTML = generateHTML_dndMonitoringCountry(doc);                         
                
              } else { 
                alert("HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText); 
              } 
            }
        } else if (part == "dndMonitoring_validate_input"){
        
            document.getElementById("dndMonitoringLevel1").innerHTML = ""; 
            document.getElementById("dndMonitoringLevel2").innerHTML = ""; 
            
            if (xmlHttpRequest.readyState == 4) { 
              if (xmlHttpRequest.status == 200) {   
              
                var validate;
                var result = xmlHttpRequest.responseText.replace(/^\s+|\s+$/g,''); 
                
                if(result.substring(result.length - 1) == ",")
                    result = result.substring(0, result.length - 1);
                
                showBarMessage('messagetd',result,1);
                
                if (result == "")
                    getDndMonitoringLevel1();
                
              } else { 
                alert("HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText); 
              } 
            }
        } else if (part == "printExcel"){
        
            document.getElementById("dndExportExcel").innerHTML = ""; 
            
            if (xmlHttpRequest.readyState == 4) { 
              if (xmlHttpRequest.status == 200) {   
 
                //document.getElementById("dndExportExcel").innerHTML = xmlHttpRequest.responseText;                         
                window.location.href = xmlHttpRequest.responseText;

                
              } else { 
                alert("HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText); 
              } 
            }
         }
      }; 
     
}

function highlightDNDLevel1(formId) { 
     
    var lvl1 = formId.optSelect;
    var selectedVal = "";
    var selectedRow = "";
    
    for (i=0; i<lvl1.length; i++) {                 
        if (lvl1[i].checked == true) {          
           selectedVal = lvl1[i].value;
           selectedRow = i;
           document.getElementById("dndLevel_" + selectedRow).style.backgroundColor= "#ADC3E7";  
           getDndMonitoringLevel2(selectedVal);           
        } else {
            document.getElementById("dndLevel_" + i).style.backgroundColor= "#FFFFFF";
        }    
    }    
}

function generateHTML_dndMonitoringLevel1(doc) {

    try {
        var result = "";
        var i;
        var checked = "";
        var chkValue = "";
        
        if (doc.dndMonitoringLevel1.length >= 1) {
        
            result = result + "<table border=0 cellSpacing=1 cellPadding=0 width=100%>";
            result = result + "<tr><td class=TableLeftText colSpan=5>Level 1</td></tr>";
            result = result + "</table>";
            
            result = result + "<table width=100%>";     
            result = result + "<tr>";
            result = result + "<td class=TableLeftSub width=5%>Select</td>"; 
            result = result + "<td class=TableLeftSub width=14%>POL</td>"; 
            result = result + "<td class=TableLeftSub width=14%>POD</td>";    
            result = result + "<td class=TableLeftSub width=25%>BILLING TYPE</td>";    
            result = result + "<td class=TableLeftSub width=10%>ACTUAL DAYS</td>";   
            result = result + "<td class=TableLeftSub width=10%>QTN DAYS</td>";  
            result = result + "<td class=TableLeftSub width=10%>UNITS</td>";  
            result = result + "<td class=TableLeftSub width=26%>COMBINE</td>";  
            result = result + "<td class=TableLeftSub width=2%>&nbsp;&nbsp;&nbsp;</td>";  
            result = result + "</tr>";
            result = result + "</table>";
            
            result = result + "<tr>"; 
            result = result + "<td colspan=8>";  
            result = result + "<div style=height:195px;overflow-y:scroll;overflow-x:auto>"; 
            result = result + "<table width=100%>";        
           
            for (i=0;i<=doc.dndMonitoringLevel1.length-1;i++) {  
                
                lvlFound = true;
                
                chkValue = doc.dndMonitoringLevel1[i].Pol + "|" +
                    doc.dndMonitoringLevel1[i].Pod + "|" +
                    doc.dndMonitoringLevel1[i].Billing_type_code + "|" +
                    doc.dndMonitoringLevel1[i].Actual_days + "|" +
                    doc.dndMonitoringLevel1[i].Qtn_days + "|" +
                    doc.dndMonitoringLevel1[i].Units + "|" +
                    doc.dndMonitoringLevel1[i].Combine;
                    
                result = result + "<tr style=background-color:#FFFFFF; class=TableLeftWhite id=dndLevel_" + i + ">";
                result = result + "<td width=5%><input type=radio name=optSelect value=" + chkValue + " onclick=\"highlightDNDLevel1(this.form);\"/></td>";  
                result = result + "<td width=14%>" + doc.dndMonitoringLevel1[i].Pol + "</td>";
                result = result + "<td width=14%>" + doc.dndMonitoringLevel1[i].Pod + "</td>";
                result = result + "<td width=25%>" + doc.dndMonitoringLevel1[i].Billing_type_name + "</td>";
                result = result + "<td width=10%>" + doc.dndMonitoringLevel1[i].Actual_days + "</td>";
                result = result + "<td width=10%>" + doc.dndMonitoringLevel1[i].Qtn_days + "</td>";
                result = result + "<td width=10%>" + doc.dndMonitoringLevel1[i].Units + "</td>";
                result = result + "<td width=26%>" + doc.dndMonitoringLevel1[i].Combine +"</td>";
                result = result + "</tr>";
            }
            
            result = result + "<td colspan=5><span style=VISIBILITY:hidden;display:none><input type=radio name=optSelect display=none/></span></td>";
            
            result = result + "</table></div>";
            result = result + "</td></tr>";
            result = result + "</table>";

            result = result + "<table border=0 cellSpacing=0 cellPadding=0 width=100%>";
            result = result + "<tr align=right>";
            result = result + "<td class=TableLeftSubBtn width=85% align=left>Total Records: " + i + "</td>";
            result = result + "<td class=TableLeftSubBtn>";
            result = result + "<input class=FormBtnClr name=btnPrintExcel1 value= 'Print Excel' type=button onClick=printExcel('SPREADSHEET','DND109_EXCEL_LVL1');></input>&nbsp;";
            result = result + "<td>";
            result = result + "</tr></table>";
            
        } else {
        
            result = result + "<table border=0 cellSpacing=1 cellPadding=0 width=100%>";
            result = result + "<tr><td class=TableLeftText colSpan=5>No data found</td></tr>";
            result = result + "</table>";
        }
            
        return result;
            
    } catch (err) {
        alert(err);
        
        return "";
    }
}    
    
function generateHTML_dndMonitoringLevel2(doc) {

    try {
        var result = "";
        var i;     
        var strHeaderETA = "";
        var strHeaderETD = "";
        
        if (billing_type.substring(0,1) == "E") {
            strHeaderETA = "POL ETA";
            strHeaderETD = "POL ETD";
        } else if (billing_type.substring(0,1) == "I") {
            strHeaderETA = "POD ETA";
            strHeaderETD = "POD ETD";
        }
        
        if (doc.dndMonitoringLevel2.length >= 1) {
        
            result = result + "<table border=0 cellSpacing=1 cellPadding=0 width=100%>";
            result = result + "<tr><td class=TableLeftText colSpan=13>Level 2</td></tr>";
            result = result + "</table>";
            
            result = result + "<table width=100%>";     
            result = result + "<tr>";
            result = result + "<td class=TableLeftSub width=10%>BL NO</td>"; 
            result = result + "<td class=TableLeftSub width=6%>SERVICE</td>";
            result = result + "<td class=TableLeftSub width=6%>VESSEL</td>";
            result = result + "<td class=TableLeftSub width=6%>VOYAGE</td>";
            result = result + "<td class=TableLeftSub width=5%>" + strHeaderETA + "</td>"; // Header for ETA
            result = result + "<td class=TableLeftSub width=5%>" + strHeaderETD + "</td>"; // Header for ETD    
            result = result + "<td class=TableLeftSub width=9%>SHIPPER CODE</td>";    
            result = result + "<td class=TableLeftSub width=15%>SHIPPER NAME</td>";   
            result = result + "<td class=TableLeftSub width=9%>CONSIGNEE CODE</td>";  
            result = result + "<td class=TableLeftSub width=9%>CONSIGNEE NAME</td>";  
            result = result + "<td class=TableLeftSub width=9%>QUOTATION NO</td>";              
            result = result + "<td class=TableLeftSub width=6%>UNITS IN BL</td>";
            result = result + "<td class=TableLeftSub width=6%>UNITS</td>";
            result = result + "<td class=TableLeftSub width=2%>&nbsp;&nbsp;&nbsp;</td>"; 
            
            result = result + "</tr>";
            result = result + "</table>";
            
            result = result + "<tr>"; 
            result = result + "<td colspan=13>";  
            result = result + "<div style=height:195px;overflow-y:scroll;overflow-x:auto>"; 
            result = result + "<table width=100%>";        
            
            for (i=0;i<=doc.dndMonitoringLevel2.length-1;i++) {  
                
                lvlFound = true;
                    
                result = result + "<tr style=background-color:#FFFFFF; class=TableLeftWhite>";                
                result = result + "<td width=10% valign=top>" + doc.dndMonitoringLevel2[i].bl_no + "</td>";  
                result = result + "<td width=6% valign=top>" + doc.dndMonitoringLevel2[i].service + "</td>";
                result = result + "<td width=6% valign=top>" + doc.dndMonitoringLevel2[i].vessel + "</td>";
                result = result + "<td width=6% valign=top>" + doc.dndMonitoringLevel2[i].voyage + "</td>";       
                result = result + "<td width=5% valign=top>" + doc.dndMonitoringLevel2[i].eta + "</td>";
                result = result + "<td width=5% valign=top>" + doc.dndMonitoringLevel2[i].etd + "</td>";
                result = result + "<td width=9% valign=top>" + doc.dndMonitoringLevel2[i].shipper_code + "</td>";
                result = result + "<td width=15% valign=top>" + doc.dndMonitoringLevel2[i].shipper_name + "</td>";
                result = result + "<td width=9% valign=top>" + doc.dndMonitoringLevel2[i].consignee_code + "</td>";
                result = result + "<td width=9% valign=top>" + doc.dndMonitoringLevel2[i].consignee_name + "</td>";
                result = result + "<td width=9% valign=top>" + doc.dndMonitoringLevel2[i].quotation_no + "</td>";
                result = result + "<td width=6% valign=top>" + doc.dndMonitoringLevel2[i].units_in_bl + "</td>";
                result = result + "<td width=6% valign=top>" + doc.dndMonitoringLevel2[i].units + "</td>";  
                result = result + "</tr>";
            }
            
            result = result + "</table></div>";
            result = result + "</td></tr>";
            result = result + "</table>";

            result = result + "<table border=0 cellSpacing=0 cellPadding=0 width=100%>";
            result = result + "<tr align=right>";
            result = result + "<td class=TableLeftSubBtn width=85% align=left>Total Records: " + i + "</td>";
            result = result + "<td class=TableLeftSubBtn>";
            result = result + "<input class=FormBtnClr name=btnPrintExcel1 value= 'Print Excel' type=button onClick=printExcel('SPREADSHEET','DND109_EXCEL_LVL2');></input>&nbsp;";
            result = result + "<td>";
            result = result + "</tr></table>";
            
        } else {
        
            result = result + "<table border=0 cellSpacing=1 cellPadding=0 width=100%>";
            result = result + "<tr><td class=TableLeftText colSpan=5>No data found</td></tr>";
            result = result + "</table>";
        }
            
        return result;
            
    } catch (err) {
        alert(err);
        
        return "";
    
    }
}

/**** Perform KeyStroke when ComboBox is changed ****/
// init global values 
var nKeyDown = 0  
var nKeyPress = 0 
var nAscii = 0  // stores the result we are looking for 
var lCtrl = false 
function KeyStroke(e) { // keyboard event fired 

    if (!e) var e = window.event ; 
    if (e.keyCode) code = e.keyCode; 
    else if (e.which) code = e.charCode; 
  
      if (e.type=='keydown') { 
        nKeyDown  = code 
        nKeyPress = 0 
        nAscii = code 
        lCtrl = true ;         
    } 
    if (e.type=='keypress') {  
        nKeyPress = code 
        if (nKeyDown!=57 && ( code>=33 && code<=40 || code==8 || code==9 || code==13)) { 
            nAscii = code ;  
            lCtrl = true ; 
        } else { 
            lCtrl = false ; 
            nAscii = nKeyPress ;            
        } 
    }   
    if (e.type=='keyup') { 

        if (lCtrl) { 
        nAscii = nAscii * -1 ; 
        } 
    } 

    return true; 
 } 
/**** Perform KeyStroke when ComboBox is changed ****/ 

function generateHTML_dndMonitoringPortCountry(doc) {

    try {
        var result = "";
        var i;    
        
        if (doc.dndMonitoringPortCountry.length >= 1) {    
            
            result = result + "<select size=1 name=cmbPOD class=FormDropDown style=\"width:80px\"><option value=\"\"></option>";
            
            for (i=0;i<=doc.dndMonitoringPortCountry.length-1;i++) {             
                result = result + "<option value=" + doc.dndMonitoringPortCountry[i].port_code + ">" + doc.dndMonitoringPortCountry[i].port_code + "</option>";
            }
            
            result = result + "</select>";
        }        
        
        return result;
            
    } catch (err) {
        alert(err);
        
        return "";
    
    }
}

function generateHTML_dndMonitoringCountry(doc) {

    try {
        var result = "";
        var i;  
        
        if (doc.dndMonitoringPortCountry.length >= 1) {    
            
            //result = result + "<select size=1 name=cmbCountry class=FormDropDown style=\"width:120px\" onchange=getPODFromCountry(this);><option value=\"\"></option>";
            result = result + "<select size=1 name=cmbCountry class=FormDropDown style=\"width:120px\" onchange=getPODFromCountry(this); " + 
                        "onkeydown=KeyStroke(event); onkeypress=KeyStroke(event); onkeyup=KeyStroke(event);>" +
                        "<option value=\"\"></option>";
            
            for (i=0;i<=doc.dndMonitoringPortCountry.length-1;i++) {             
                result = result + "<option value=" + doc.dndMonitoringPortCountry[i].country_code + ">" + doc.dndMonitoringPortCountry[i].country_name + "</option>";
            }
            
            result = result + "</select>";
        }        
        
        return result;
            
    } catch (err) {
        alert(err);
        
        return "";
    
    }
}
