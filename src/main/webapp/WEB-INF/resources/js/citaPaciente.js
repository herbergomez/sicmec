/**
 * @Autor Pablo Portillo
 * @Fecha 10/11/2014
 */

$( document ).ready(function() 
{
	$('#nuevaCitaForm').validate({
 		errorElement: "span",
 		rules: {
 			diagnostico:
 		    {
 				required: true
 		    },
 		    comentario:
 			{
 			   	required: true
 			},
 			tratamiento:
 			{
 				required: true
 			},
 			dosis:
 			{
 				required: true
 			},
 			periodisidad:
 			{
 				required: true
 			}	
 		    },
 		messages : {
 			diagnostico:
 		    {
 				required: "Este campo es obligatorio"
 		    },
 		    comentario:
 			{
 			   	required: "Este campo es obligatorio"
 			},
 			tratamiento:
 			{
 				required: "Este campo es obligatorio"
 			},
 			dosis:
 			{
 				required: "Este campo es obligatorio"
 			},
 			periodisidad:
 			{
 				required: "Este campo es obligatorio"
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
 		},
 		submitHandler: function()
 		{
 			if($("#pacTable tbody tr.selected").length == 1)
 			{
 				var paciente = $("#pacTable tbody tr.selected").data("id");
 				var diagnostico = $("#diagnostico").val();
 				var comentario = $("#comentario").val();
 				var tratamiento = $("#tratamiento").val();
 				var dosis = $("#dosis").val();
 				var periodisidad = $("#periodisidad").val();
 				
 				$.ajax({
 	 		 	    type: "POST",
 	 		 		url: "/sicmec/control/cita/guardarCita",	
 	 		 		data : ({diag:diagnostico,cmt:comentario,paqMed:tratamiento,dosis:dosis,per:periodisidad,pac:paciente}),
 	 		 	    success: function(data)
 	 		 	    	{
 	 		 	    		if(data == "ok")
 	 		 	    			{
 	 		 	    				$("#modalNuevaCita").modal('hide');
 	 		 	    				historialPaciente(paciente);
 	 		 	    			}
 	 					},
 	 						
 	 				error: function(e)
 	 					{
 	 					
 	 					}
 	 		 	    });
 			}		
 		}
 		 
 	});
	 
		$('#pacTable tbody').on( 'click', 'tr', function () {
		     if(!$(this).find("td").hasClass("dataTables_empty"))
		     {
		        if ($(this).hasClass('selected')) 
		        {
		            $(this).removeClass('selected');
		            $("#citaTab").hide();
		            $("#controlTab").hide();
		        }
		        else 
		        {
		            $('#pacTable tbody tr.selected').removeClass('selected');
		            
		            var id=$(this).data("id");
		            //alert(id);
		            historialPaciente(id);
		            $(this).addClass('selected');
		            //alert($("#pacTable tbody tr.selected").data("id"));
		            
		            $("#citaTab").show();
		            $("#controlTab").show();
		            
		         }
		     }
		    });
	
	$(".close").click(function(){
		$(".alert").hide();
	});
	
	$("#buscar").click(function()
	{
		$("#citaTab").hide();
        $("#controlTab").hide();
		var exp = $("#exp").val();
			if(exp != '' && exp != "")
			{
				cargandoTabla();
				
				setTimeout(
						function()
						{
							ajaxCall(exp);
						},
						1000);
				
				
			}
	});		
	
});

function ajaxCall(exp)
{
	$.ajax({
 	    type: "POST",
 		url: "/sicmec/control/cita/buscar",	
 		data : ({exp:exp}),
 	    success: function(data)
 	    	{
 	    			$("#tablaPacientes tr").remove();
 	    			
 	    			for (var int = 0; int < data.length; int++) 
 	 	    		{
 	    				agregarPaciente(data[int]);
 					}
 	    			
 	    			$('#pacTable').dataTable().fnDestroy();
 	    			
 	    		    var table1 = $('#pacTable').dataTable({
 	    				"aLengthMenu": [[5],[5]],
 	    				"sPaginationType": "full_numbers",
 	    				"bLengthChange": false,
 	    				bInfo: false,
 	    				bDestroy: true,
 	    			    "bFilter": false,
 	    				"autoWidth": true,
 	    		    	"oSearch": {"sSearch": " "},
 	    		    	"oLanguage": {
 	    		    		"sProcessing":     "Procesando...",
 	    		    	    "sLengthMenu":     "Mostrar _MENU_ registros",
 	    		    	    "sZeroRecords":    "No se encontraron resultados",
 	    		    	    "sEmptyTable":     "No hay datos disponibles",
 	    		    	    "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
 	    		    	    "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
 	    		    	    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
 	    		    	    "sInfoPostFix":    "",
 	    		    	    "sSearch":         " Buscar:",
 	    		    	    "sUrl":            "",
 	    		    	    "sInfoThousands":  ",",
 	    		    	    "sLoadingRecords": "Cargando...",
 	    		    	    "oPaginate": {
 	    		    	        "sFirst":    "Primero",
 	    		    	        "sLast":     "\u00DAltimo",
 	    		    	        "sNext":     "Siguiente",
 	    		    	        "sPrevious": "Anterior"
 	    		    	    },
 	    		    	    "oAria": {
 	    		    	        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
 	    		    	        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
 	    		    	    }
 	    				}
 	    		    });
 	    		
 	    		
			},
				
		error: function(e)
			{
				$(".alert").hide();
				$("#alertaError").show();
			}
 	    });
}

function cargandoTabla()
{
	$("#tablaPacientes tr").remove();
	
	var tbody = document.getElementById("tablaPacientes");
	var tr = document.createElement("tr");
	tr.className="odd";
	var td = document.createElement("td");
	td.className="text-center";
	var i = document.createElement("i");
	i.className="fa fa-cog fa-spin fa-4x";
	td.setAttribute("colspan","6");
	td.setAttribute("valign","top");
	td.appendChild(i);
	tr.appendChild(td);
	tbody.appendChild(tr);
};

function ceroResultados()
{
	$("#tablaPacientes tr").remove();
	
	var tbody = document.getElementById("tablaPacientes");
	var tr = document.createElement("tr");
	tr.className="odd";
	var td = document.createElement("td");
	td.className="text-center";
	td.appendChild(document.createTextNode("No se encontrar\u00F3n pacientes"));
	td.setAttribute("colspan","6");
	td.setAttribute("valign","top");
	
	tr.appendChild(td);
	tbody.appendChild(tr);
};

function agregarPaciente(paciente)
{
	var tbody = document.getElementById("tablaPacientes");
	var tr = document.createElement("tr");
	tr.setAttribute("data-id",paciente.idSicPaciente);
	var tdbtn = document.createElement("td");
	var a = document.createElement("a");
		a.className="btn btn-sm btn-default detail";
		a.setAttribute("title","Ver detalle");
		a.setAttribute("href","../admin/detallePaciente/"+paciente.numExpediente);
		a.setAttribute("target","_blank");
	var i = document.createElement("i");
		i.className="fa fa-search-plus";
		a.appendChild(i);
		tdbtn.appendChild(a);
		
	var tdexp = document.createElement("td");
		tdexp.appendChild(document.createTextNode(paciente.numExpediente));
	var tdnmbr = document.createElement("td");
		tdnmbr.appendChild(document.createTextNode(paciente.fkSicPersona.nombre));
	var tdape = document.createElement("td");
		tdape.appendChild(document.createTextNode(paciente.fkSicPersona.apellido));
	var tddui = document.createElement("td");
		tddui.appendChild(document.createTextNode(paciente.documentoIdentidad));
	var tdedad = document.createElement("td");
		tdedad.appendChild(document.createTextNode(paciente.edad));
		
	tr.appendChild(tdbtn);
	tr.appendChild(tdexp);
	tr.appendChild(tdnmbr);
	tr.appendChild(tdape);
	tr.appendChild(tddui);
	tr.appendChild(tdedad);
	tbody.appendChild(tr);
	
	$('.detail').qtip({
//	    content: {
//	        text: 'Inactivar usuario'
//	    },
	    style: 
	    {
	        classes: 'qtip-bootstrap qtip-shadow'
	    }
	});
	
};


function historialPaciente(id)
{
	$("#historial div").remove();
	
	var historial = "";
	
	$.ajax({
 	    type: "POST",
 		url: "/sicmec/control/cita/historialPac",	
 		data : ({pac:id}),
 	    success: function(data)
 	    	{
 	    		for (var int = 0; int < data.length; int++) 
 	    		{
					historial = historial 
					+ "<div class='panel panel-primary'>" 
					+  "<div class='panel-heading' role='tab' id=title_'"+int+"'>" 
					+	"<h4 class='panel-title'>" 
					+    "<a data-toggle='collapse' data-parent='#historial' class='collapsed' href='#body"+int+"' aria-expanded='false' aria-controls='body"+int+"'>" 
					+	 "Fecha: "+data[int].fkSicCitaMedica.fxCitaMedica+", Doctor: "
					+    data[int].fkSicCitaMedica.fkSicUsuario.sicPersona.nombre+", "+data[int].fkSicCitaMedica.fkSicUsuario.sicPersona.apellido 
					+    "</a></h4></div>" 
					+	"<div id='body"+int+"' class='panel-collapse collapse' role='tabpanel' aria-labelledby='body"+int+"'>" 
					+	 "<div class='panel-body'>" 
					+    "<div class='col-md-6'>"
					+    	"<div class='form-group'>" 
					+			"<label>Fecha de cita: </label>" 
					+			"<input class='form-control' disabled='disabled' value='"+data[int].fkSicCitaMedica.fxCitaMedica+"'/>"
					+		"</div>"
					+    	"<div class='form-group'>"
					+    		"<label>Diagnostico: </label>"
					+     		"<input class='form-control' disabled='disabled' value='"+data[int].fkSicCitaMedica.diagnostico+"'/>"
					+		"</div>"
					+    	"<div class='form-group'>"
					+    		"<label>Comentario: </label>"
					+			"<input class='form-control' disabled='disabled' value='"+data[int].fkSicCitaMedica.comentario+"'/>"
					+		"</div>"
					+	 "</div>" 
					+    "<div class='col-md-6'>"
					+    	"<div class='form-group'>"
					+    		"<label>Tratamiento: </label>"
					+			"<input class='form-control' disabled='disabled' value='"+data[int].fkSicCatMedicamentos.name+"'/>"
					+		"</div>"
					+    	"<div class='form-group'>"
					+    		"<label>Dosis: </label>"
					+			"<input class='form-control' disabled='disabled' value='"+data[int].dosis+"'/>"
					+		"</div>"
					+    	"<div class='form-group'>"
					+    		"<label>Periodisidad: </label>"
					+			"<input class='form-control' disabled='disabled' value='"+data[int].periodisidad+"'/>"
					+		"</div>"	
					+	 "</div>" 
					+	"</div></div></div>";
				}
 	    		
 	    		$("#historial").html(historial);
 	    		
			},
				
		error: function(e)
			{
				$(".alert").hide();
				$("#alertaError").show();
			}
 	    });
};