function toggleUsed(elem) {
   var className = elem.className;
   if(className.indexOf("used") == -1) {
      elem.className = className + " used";
   } else {
      elem.className = className.substring(0, className.indexOf("used")); 
   }
   
   var oldText = elem.getAttribute("data-oldText");
   var currentText = elem.innerText;
   elem.innerText = oldText;
   elem.setAttribute("data-oldText", currentText)
}

$(document).ready(function() {
   
   $(".button#tierTwo").click(function(){
	   	if(this.innerText.indexOf("Lock") > -1) {
	   		$("body").attr("id", "tierOne");
	   		$(".tier2").addClass("disabled");
	   	} else {
	   		$(".tier2").removeClass("disabled");
	   		$("body").attr("id", "tierTwo");	   		
	   	}
   })
   
   $(".button#tierThree").click(function(){
	   	if(this.innerText.indexOf("Lock") > -1) {
	   		$("body").attr("id", "tierTwo");
	   		$(".tier3").addClass("disabled");
	   	} else {
	   		$(".tier3").removeClass("disabled");
	   		$("body").attr("id", "tierThree");	   		
	   	}
   })
   
   $(".button").click(function(){
		   if (this.className.indexOf("disabled") == -1) {
			   toggleUsed(this);
		   }
	   })
})
