/**
 * @author Kevin Garay
 */

$( document ).ready(function() {
  
	/*DataTable definition*/
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
            "fnDrawCallback": function() 
            {
            	/*Showing the modal view to edit a drug*/
            	$(".onUpdate").click(function(){
            		var id = $(this).data("id");
            		//alert(id);		
            		$.ajax
            		({
            			type: "GET",
            			url:"/sicmec/admin/drugs/getDrug/"+id,
            			success:function(result)
            			{
            				$("#idUpdate").val(result.idDrug);
            				$("#nombreUpdate").val(result.drugName);
            				$("#descripcionUpdate").val(result.drugDescription);
            				var iActivo  = result.estado;
            				var iTipo  = result.tipo;
            				
            				//Validamos si el medicamento es insitutcional(1) o no(0)
            				if ( iTipo == 1 ) {
            					$("#insitucional").prop("checked",true);
            					$("#noInstitucional").prop("checked",false);
            				} else {
            					$("#insitucional").prop("checked",false);
            					$("#noInstitucional").prop("checked",true);
            				}
            				//Validamos si el medicamento esta activo o no
            				if ( iActivo == 1 ) {
            					$("#activo").prop("checked",true);
            					$("#desactivo").prop("checked",false);
            				} else {
            					$("#activo").prop("checked",false);
            					$("#desactivo").prop("checked",true);
            				}
            				
            				//$("#modalUpdateDrug").modal("show");
            			},
            			error: function (xhr, ajaxOptions, thrownError) 
            			{
            				alert("unable to find server..")
            		    }
            		});
            	});
            	
            	
            }
	});
	
	/*Validation of new drug*/
	$('#agregarMedicamentoForm').validate({
		errorElement: "span",
		rules: {
		   nombre: 
		   {
		     	required: true,
		     	maxlength: 20,
		     	minlength: 3,
		     	remote: 
		     	{
		    		type: "POST",
		    		url: "/sicmec/Utils/validarNombreDroga",
	                	data : 
	                	{
	                		nombre: function() 
	                        { 
	                			return $("#nombre").val(); 
                			}
	                	}
		     	}
		   },
		   descripcion: 
		   {
		        required: false,
		        maxlength: 20,
		     	minlength: 3
		   },
		   tipo:
		   {
			   required: true
		   }
		  },
		messages : 
		{
			nombre:
			{
				remote : "El medicamento que desea agregar ya existe."
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
	
	/*Validation of update drug*/
	$('#updateMedicamentoForm').validate({
		errorElement: "span",
		rules: {
			nombreUpdate: 
		   {
		     	required: true,
		     	maxlength: 20,
		     	minlength: 3
		   },
		   descripcionUpdate: 
		   {
		        required: false,
		        maxlength: 20,
		     	minlength: 3
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