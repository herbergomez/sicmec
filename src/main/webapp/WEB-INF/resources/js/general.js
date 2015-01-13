/**
 * 
 */
$(document).ready(function() 
{
	$("#formCambioClave").validate({
	errorElement: "span",
	rules: 
	{
	   oldPass:
	   {
	       required:true,
	       maxlength: 20,
	       minlength: 3
	   },
	   newPass:
	   {
		   required:true,
	       maxlength: 20,
	       minlength: 3 
	   },
	   confirmNewPass:
	   {
		   required:true,
		   equalTo: "#newPass"
	   }
	  },
	highlight: function(element) {
		$(element).closest('.control-group')
		.removeClass('has-success').addClass('has-error');
	},
	success: function(element) {
		element.addClass('help-inline')
		.closest('.control-group')
		.removeClass('has-error').addClass('has-success');
	},
	submitHandler : function(form)
	{
		var oldPass = $("#oldPass").val();
		var newPass = $("#newPass").val();
		
		$.ajax
		({
			type: "POST",
			url:"/sicmec/Utils/cambiarClave",
			data : ({oldPass:oldPass,newPass:newPass}),
			success:function(result)
			{
				limpiarModal();
				
				if(result == "true")
				{
					new jBox('Notice', 
							{
								content: 'Contrase&ntilde;a modificada con &eacutexito',
								color: 'green'
							});
					
				}
				else
				{
					new jBox('Notice', 
							{
								content: result,
								color: 'red'
							});
				}	
			},
			error: function (xhr, ajaxOptions, thrownError) 
			{
				alert("unable to find server..")
		    }
		});
	}
	 
});
	
	
});

function limpiarModal()
{
	$("#modalCambioClave").modal('hide');
	$("#oldPass").val("");
	$("#newPass").val("");
}