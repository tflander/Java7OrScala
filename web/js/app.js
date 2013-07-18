function toggleUsed(elem) {
   var className = elem.className;
   if(className.indexOf("used") == -1) {
      elem.className = className + " used";
   } else {
      elem.className = className.substring(0, className.indexOf("used")); 
   }
}

$(document).ready(function() {
   $(".button").click(function(){
   	toggleUsed(this);
   })
})
