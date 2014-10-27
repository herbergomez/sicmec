/**
 * Autor: Pablo Portillo
 */

$( document ).ready(function() {
	
$('#agregarUsuarioForm').validate({
		errorElement: "span",
		rules: {
		   nombre: 
		   {
		     	required: true,
		     	maxlength: 20,
		     	minlength: 3
		   },
		   apellido: 
		   {
		        required: true,
		        maxlength: 20,
		     	minlength: 3
		   },
		   usuario: 
		   {
			    required: true,
			    maxlength: 20,
		     	minlength: 3,
		     	remote: 
		     	{
		    		type: "POST",
		    		url: "/sicmec/Utils/validarUsername",
	                	data : 
	                	{
	                        usuario: function() 
	                        { return $("#usuario").val(); }
	                	}
		     	}
		   },
		   pass: 
		   {
			    required: true,
			    maxlength: 10,
		     	minlength: 6
		   },
		   passConfirm: 
		   {
			    required: true,
			    equalTo: pass
		   },
		   mail: 
		   {
			    required: true,
			    email: true
		   },
		   
		   rol:
		   {
			   required: true
		   }
		  },
		messages : 
		{
			usuario:
			{
				remote : "Este usuario ya existe."
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
	
$('#modificarUsuarioForm').validate({
	errorElement: "span",
	rules: {
		nombreUp: 
	   {
	     	required: true,
	     	maxlength: 20,
	     	minlength: 3
	   },
	   apellidoUp: 
	   {
	        required: true,
	        maxlength: 20,
	     	minlength: 3
	   },
	   mailUp: 
	   {
		    required: true,
		    email: true
	   },
	   rolUp:
	   {
		   required: true
	   },
	   fxAct:
	   {
		   required: true
	   },
	   fxDes:
	   {
		   required: true
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

	$(".datatables").DataTable({
		"scrollY": "350px",
        "scrollCollapse": false,
        "aoColumns": 
        	[
        	 { "sWidth": "10%", "sClass": "center", "bSortable": false },
        	 { "sWidth": "10%" },
        	 { "sWidth": "10%" },
        	 { "sWidth": "10%" },
        	 { "sWidth": "10%" },
        	 { "sWidth": "10%" },
        	 { "sWidth": "10%" },
             { "sWidth": "10%" },
            ],
            language: {
                "sProcessing":     "Procesando...",
                "sLengthMenu":     "Mostrar _MENU_ registros",
                "sZeroRecords":    "No se encontraron resultados",
                "sEmptyTable":     "Ningún dato disponible en esta tabla",
                "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
                "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
                "sInfoPostFix":    "",
                "sSearch":         "Buscar:",
                "sUrl":            "",
                "sInfoThousands":  ",",
                "sLoadingRecords": "Cargando...",
                "oPaginate": {
                    "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"
                },
                "oAria": {
                    "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                }
            },
    		"fnDrawCallback" : function() 
    		{
    		
    			$(".onUpdate").click(function(){
    				var id = $(this).data("id");
    				//alert(id);
    				
    				$.ajax
    				({
    					type: "GET",
    					url:"/sicmec/admin/usuarios/getUser/"+id,
    					success:function(result)
    					{
    						$("#id").val(result.idSicUsuario);
    						$("#nombreUp").val(result.sicPersona.nombre);
    						$("#apellidoUp").val(result.sicPersona.apellido);
    						$("#mailUp").val(result.sicPersona.email);
    						$("#rolUp").val(result.sicRol.idSicRol);
    						$("#fxAct").val(result.fxActivacion);
    						$("#fxDes").val(result.fxDesactivacion);
    						
    						$("#modalUpdateUsuario").modal("show");
    					},
    					error: function (xhr, ajaxOptions, thrownError) 
    					{
    						alert("unable to find server..")
    				    }
    				});
    			});
    			

    			$(".onDelete").click(function(){
    				var id = $(this).data("id");
    				
    				$("#AreYouSureConfirm").attr("href","/sicmec/admin/usuarios/delUser/"+id);
    				$("#modalEliminarUsuario").modal('show');
    				
    			});
    			
    		}   
	});
		
});

function onDeleteValidate()
{
	if($("#okdelete").is(':checked'))
	{
		$("#AreYouSureConfirm").removeAttr("onclick");
		$("#AreYouSureConfirm").addClass("btn-primary");
		
	}
	else
	{
		$("#AreYouSureConfirm").attr("href","#");
		$("#AreYouSureConfirm").attr("onclick","return false;");
		$("#AreYouSureConfirm").removeClass("btn-primary");
	}
};