/**
 * 
 */
$( document ).ready(function() {	
	$('#parameterPacienteForm').validate({
		errorElement: "span",
		rules: {
		   fdesde:
		   {
			   required:true
		   },
		   fhasta:
		   {
			   required:true
		   }
		},
		highlight: function(element) {
			$(element).closest('.form-group')
			.removeClass('has-success').addClass('has-error');
		},
		success: function(element) {
			element.addClass('help-inline')
			.closest('.form-group')
			.removeClass('has-error').addClass('has-success');
		}
});
	$('#fdesde').datetimepicker({
	    dateFormat: "dd-mm-yy"
	   });
	$('#fhasta').datetimepicker({
	    dateFormat: "dd-mm-yy"
	   });
   $('#fdesde').val(function(){
		  var d = new Date();
		  if((d.getMonth()+1)<10)
		  {
			month = "0"+(d.getMonth()+1);  
		  }
		  else
		  {
			  month = d.getMonth()+1;
		  }	  
		  if(d.getDate() < 10)
		  {
			  day = "0"+d.getDate();
		  }
		  else
		  {
			  day = d.getDate();
		  }
		  return day + "-" +  (month) + "-" +d.getFullYear();
		});
   $('#fhasta').val(function(){
		  var d = new Date();
		  if((d.getMonth()+1)<10)
		  {
			month = "0"+(d.getMonth()+1);  
		  }
		  else
		  {
			  month = d.getMonth()+1;
		  }	  
		  if(d.getDate() < 10)
		  {
			  day = "0"+d.getDate();
		  }
		  else
		  {
			  day = d.getDate();
		  }
		  return day + "-" +  (month) + "-" +d.getFullYear();
		});
});