/**
 * 
 */
$( document ).ready(function() {
	/**
	 * TODO LO CONCERNIENTE A ADMINISTARCION DE PATOLOGIAS
	 */
	$('#agregarPatologiaForm').validate({
		errorElement: "span",
		rules: {
			 nombrePatologia: 
			   {
			     	required: true,
			     	minlength: 5,
			     	remote:
					{
				        type: "POST",
				        url: "/sicmec/Utils/validarPatologia",
			            data : 
			                {
			            	nombrePatologia: function() 
			                  { return $("#nombrePatologia").val(); }
			             }
				     }
			   },
			   descripcionPatologia: 
			   {
			        required: true,
			     	minlength: 10		  
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
	$('#modificarPatologiaForm').validate({
		errorElement: "span",
		rules: {
			 nombrePatologiaUp: 
			   {
			     	required: true,
			     	minlength: 5
			   },
			   descripcionPatologiaUp: 
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
	$("#tablePatologias").DataTable({
		"scrollY":        "350px",
        "scrollCollapse": false,
        "aoColumns": 
        	[
        	 { "sWidth": "5%", "sClass": "center", "bSortable": false },
        	 { "sWidth": "10%" },
        	 { "sWidth": "15%" },
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
                } ,
                "fnDrawCallback": function() {
                	$(".onUpdatePatologia").click(function(){
                		var id = $(this).data("id");
                		
                		$.ajax
                		({
                			type: "GET",
                			url:"/sicmec/admin/patologias/getPatologia/"+id,
                			success:function(result)
                			{
                				$("#idPatologiaUp").val(result.idSicPatologia);
                				$("#nombrePatologiaUp").val(result.nombrePatologia);
                				$("#descripcionPatologiaUp").val(result.descripcionPatologia);	
                				//$("#modalUpdatePatologia").modal("show");
                			},
                			error: function (xhr, ajaxOptions, thrownError) 
                			{
                				alert("unable to find server..")
                		    }
                		});
                	});                             	
                },
            }   
	});
	
	
/**
 * TODO LO CONCERNIENTE A ADMINISTARCION DE TIPOS DE PATOLOGIAS
 */
	$('#agregarTipoPatologiaForm').validate({
		errorElement: "span",
		rules: {
			 nombreTipoPatologia: 
			   {
			     	required: true,
			     	minlength: 5,
			     	remote:
					{
				        type: "POST",
				        url: "/sicmec/Utils/validarTipoPatologia",
			            data : 
			                {
			            	nombreTipoPatologia: function() 
			                  { return $("#nombreTipoPatologia").val(); }
			             }
				     }
			   },
			   descripcionTipoPatologia: 
			   {
			        required: true,
			     	minlength: 10		  
			  },
			  patologia: 
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
	$('#modificarTipoPatologiaForm').validate({
		errorElement: "span",
		rules: {
			 nombreTipoPatologiaUp: 
			   {
			     	required: true,
			     	minlength: 5
			   },
			   descripcionTipoPatologiaUp: 
			   {
			        required: true,
			     	minlength: 10		  
			  },
			  patologiaUp: 
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
	
	$("#tableTipoPatologias").DataTable({
		"scrollY":        "350px",
        "scrollCollapse": false,
        "aoColumns": 
        	[
        	 { "sWidth": "5%", "sClass": "center", "bSortable": false },
        	 { "sWidth": "10%" },
        	 { "sWidth": "15%" },
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
                } ,
                "fnDrawCallback": function() {
                	$(".onUpdateTipoPatologia").click(function(){
                		var id = $(this).data("id");
                		
                		$.ajax
                		({
                			type: "GET",
                			url:"/sicmec/admin/patologias/getTipoPatologia/"+id,
                			success:function(result)
                			{
                				$("#idTipoPatologiaUp").val(result.idSicTipoPatologia);
                				$("#nombreTipoPatologiaUp").val(result.nombreTipoPatologia);
                				$("#descripcionTipoPatologiaUp").val(result.descripcionTipoPatologia);	
                				$("#patologiaUp").val(result.fkSicPatologia.idSicPatologia);
                				//$("#modalUpdateTipoPatologia").modal("show");
                			},
                			error: function (xhr, ajaxOptions, thrownError) 
                			{
                				alert("unable to find server..")
                		    }
                		});
                	});               	                           	
                },
            }   
	});
});