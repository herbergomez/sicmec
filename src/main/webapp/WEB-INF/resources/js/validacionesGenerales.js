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
});