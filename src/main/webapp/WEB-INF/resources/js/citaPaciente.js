/**
 * @Autor Pablo Portillo
 * @Fecha 10/11/2014
 */

var Cita;

$( document ).ready(function() 
{
	$("#printGraph").click(function()
	{
		 var expPac = $("#pacTable tbody tr.selected td:nth-child(2)").html();
		 var namePac = $("#pacTable tbody tr.selected td:nth-child(3)").html() +", "+$("#pacTable tbody tr.selected td:nth-child(4)").html();
		
		 var headElements = "<p><span>Numero de expediente: "+expPac+"</span></p><p><span>Nombre del paciente: "+namePac+"</span></p>";
		 
		 var options = { mode : 'popup', popClose : true ,extraHead : headElements,popTitle : '' };
		 
		$("#printHistorico").printArea(options);
	
	});
	
	$("#doChart").click(function(){
		
		var tipo = $("#tipoExamenGrafico").val();
		var paciente = $("#pacTable tbody tr.selected").data("id");
		
		generarGrafico(paciente,tipo);
			
	});
	
	$("#modalExamen").on('show.bs.modal', function (e) 
	{
		$("#resMessage").html("<span>Resultado &#91;mg&#47;dl&#93;</span>");
	});
	
		
	$("#formExamen").validate({
 		errorElement: "span",
 		rules: 
 		{
 			result:
 		    	{
 					required: true
 		    	},
			comentarioExm:
				{
					maxlength: 25
				}
 		},
 		messages : 
 		{
 			result:
 		    {
 				required: 'Este campo es obligatorio.'
 		    }
        },
 		highlight: function(element) 
 		{
 			$(element).closest('.form-group')
 			.removeClass('has-success').addClass('has-error');
 		},
 		success: function(element) 
 		{
 			element.addClass('help-inline')
 			.closest('.form-group')
 			.removeClass('has-error').addClass('has-success');
 		},
 		submitHandler: function()
 		{
 			var tipo = $("#tipo").val();
 			var result = $("#result").val();
 			var cmt = $("#comentarioExm").val();
 			
 			if($("#tipo").val() == '7')
 			{
 				var regex =new RegExp("^\[0-9]{1,3}\/\[0-9]{1,3}$");
 				
 				if(regex.test($("#result").val()))
 				{
 					$("#result").closest('.form-group')
 		 			.removeClass('has-error').addClass('has-success');
 					
 					$.ajax({
 		 		 	    type: "POST",
 		 		 		url: "/sicmec/control/cita/guardarExam",	
 		 		 		data : ({cmt:cmt,result:result,cita:Cita,tipoExam:tipo}),
 		 		 	    success: function(data)
 		 		 	    	{
 		 		 	    		clearModalExam();
 		 		 	    		
 		 		 	    		if(data == "ok")
 		 		 	    			{
 		 		 	    				$(".alert").hide();
 		 		 	    				$("#alertaExamSuccess").show();
 		 		 	    				callExams(Cita);
 		 		 	    				//historialPaciente(paciente);
 		 		 	    			}
 		 		 	    		else
 		 		 	    			{
 		 		 	    				$(".alert").hide();
 		 		 	    				$("#alertaExamError").show();
 		 		 	    			}
 		 					},
 		 						
 		 				error: function(e)
 		 					{
 		 						clearModalExam();
 		 						
 		 						$(".alert").hide();
 		 	    				$("#alertaExamError").show();
 		 					}
 		 		 	    });
 				} 	
 				else
 				{
 					
 					$("#result").closest('.form-group')
 		 			.removeClass('has-success').addClass('has-error');
 				}
 			}
 			else
 			{
 	 			$.ajax({
 		 		 	    type: "POST",
 		 		 		url: "/sicmec/control/cita/guardarExam",	
 		 		 		data : ({cmt:cmt,result:result,cita:Cita,tipoExam:tipo}),
 		 		 	    success: function(data)
 		 		 	    	{
 		 		 	    		clearModalExam();
 		 		 	    		
 		 		 	    		if(data == "ok")
 		 		 	    			{
 		 		 	    				$(".alert").hide();
 		 		 	    				$("#alertaExamSuccess").show();
 		 		 	    				callExams(Cita);
 		 		 	    				//historialPaciente(paciente);
 		 		 	    			}
 		 		 	    		else
 		 		 	    			{
 		 		 	    				$(".alert").hide();
 		 		 	    				$("#alertaExamError").show();
 		 		 	    			}
 		 					},
 		 						
 		 				error: function(e)
 		 					{
 		 						clearModalExam();
 		 						
 		 						$(".alert").hide();
 		 	    				$("#alertaExamError").show();
 		 					}
 		 		 	    });
 			}	
 			
 		}
 		 
 	});
	
	$("#tipo").change(function(){
		
		if($("#tipo").val() == '7')
		{
			$("#resMessage").html("<span>Resultado &#91;Sist&oacute;lica&#47;Diast&oacute;lica&#93;</span>");
		}
		else
		{
			$("#resMessage").html("<span>Resultado &#91;mg&#47;dl&#93;</span>");
		}
	});
	
	
	
	$("#result").keyup(function()
	{	
		if($("#tipo").val() == '7')
		{
			var regex =new RegExp("^\[0-9]{1,3}\/\[0-9]{1,3}$");
			if(!regex.test($("#result").val()))
			{
				$("#result").closest('.form-group')
	 			.removeClass('has-success').addClass('has-error');
			}
			else	
			{
				$("#result").closest('.form-group')
	 			.removeClass('has-error').addClass('has-success');
			}
		}
		else
		{
			$("#result").closest('.form-group')
 			.removeClass('has-error').addClass('has-error');
		}
	});
	
	$('#nuevaCitaForm').validate({
 		errorElement: "span",
 		rules: {
 			diagnostico:
 		    {
 				required: true
 		    },
 		    peso:
 		    {
 		    	number: true,
				range: [1, 100000]
 		    },
 		    estatura:
		    {
		    	number: true,
				range: [1, 100000]
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
 		    peso:
		    {
		    	number: "Por favor ingrese un numero valido",
				range: "No estan permitidos los valores negativos"
		    },
		    estatura:
		    {
		    	number: "Por favor ingrese un numero valido",
				range: "No estan permitidos los valores negativos"
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
 				var signosSintomas = $("#signosSintomas").val();
 				var peso = $("#peso").val();
 				var estatura = $("#estatura").val();
 				var comentario = $("#comentario").val();
 				var tratamiento = $("#tratamiento").val();
 				var dosis = $("#dosis").val();
 				var periodisidad = $("#periodisidad").val();
 				
 				$.ajax({
 	 		 	    type: "POST",
 	 		 		url: "/sicmec/control/cita/guardarCita",	
 	 		 		data : ({diag:diagnostico,signosSintomas:signosSintomas,peso:peso,estatura:estatura,cmt:comentario,paqMed:tratamiento,dosis:dosis,per:periodisidad,pac:paciente}),
 	 		 	    success: function(data)
 	 		 	    	{
 	 		 	    		if(data == "ok")
 	 		 	    			{
 	 		 	    				$("#modalNuevaCita").modal('hide');
 	 		 	    				$("#citaSuccess").show();
 	 		 	    				historialPaciente(paciente);
 	 		 	    			}
 	 		 	    		else
 	 		 	    			{
 	 		 	    				$("#citaError").show();
 	 		 	    			}
 	 					},
 	 						
 	 				error: function(e)
 	 					{
 	 						$("#citaError").show();
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
		            limpiarGrafico();
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

function callExams(cita)
{
	Cita = cita;
	
	var tr = "";
	$.ajax({
 	    type: "GET",
 		url: "/sicmec/control/cita/exams",	
 		data : ({cita:cita}),
 	    success: function(data)
 	    	{
 	    		for (var int = 0; int < data.length; int++) 
 	    		{
 	    			tr = tr 
 	    			+ "<tr>"
 	    			+ "<td><div class='btn-group'>" 
 	    			+ "<button class='btn btn-sm btn-default onDelete' title='Eliminar' onclick='eliminarExam("+data[int].idSicExamen+");'><i class='fa fa-trash-o'></i></button>" 	    			
 	    			//+ "<button class='btn btn-sm btn-default'><i class='fa fa-pencil-square-o'></i></button>"
 	    			+ "</div></td>"
 	    			+ "<td>"+data[int].fkSicTipoExamen.descripcion+"</td>" 
 	    			+ "<td>"+data[int].resultado+"</td>" 
 	    			+ "<td>"+data[int].comentario+"</td>"
 	    			+ "</tr>"
				}
 	    		
 	    		$("#tbodyExams").html(tr);
 	    		
 	    		$('.onDelete').qtip({
// 	    		    content: {
// 	    		        text: 'Inactivar usuario'
// 	    		    },
 	    		    style: 
 	    		    {
 	    		        classes: 'qtip-bootstrap qtip-shadow'
 	    		    }
 	    		});
 	    		
			},
				
		error: function(e)
			{
				$(".alert").hide();
			}
 	    });
	
	$("#modalExamen").modal('show');
};

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
					+	"<button title='Agregar examenes' class='btn btn-default btn-sm exams' style='padding: 2px 4px;' onclick='callExams("+data[int].fkSicCitaMedica.idSicCitaMedica+")' ><i class='fa fa-medkit fa-lg'></i></button>"
					+	"<button title='Imprimir informe' class='btn btn-default btn-sm print' style='padding: 2px 6px; margin-left: 0.25%;' onclick='printCita("+int+")' ><i class='fa fa-print fa-lg'></i></button>"
					+	"<h4 class='panel-title' style='display: inline-block; padding-left: 1%;'>" 
					+    "<a data-toggle='collapse' data-parent='#historial' class='collapsed' href='#body"+int+"' aria-expanded='false' aria-controls='body"+int+"'>" 
					+	 "Fecha: "+data[int].fkSicCitaMedica.fxCitaMedica
					//+	 "Doctor: "
					//+    data[int].fkSicCitaMedica.fkSicUsuario.sicPersona.nombre+", "+data[int].fkSicCitaMedica.fkSicUsuario.sicPersona.apellido 
					+    "</a></h4></div>" 
					+	"<div id='body"+int+"' class='panel-collapse collapse' role='tabpanel' aria-labelledby='body"+int+"'>"
					+    "<span class='doctorName' style='padding-left:4%'>Doctor: "+data[int].fkSicCitaMedica.fkSicUsuario.sicPersona.nombre+", "+data[int].fkSicCitaMedica.fkSicUsuario.sicPersona.apellido
					+    "</span>"
					+	 "<div class='panel-body'>" 
					+    "<div class='col-md-6'>"
					+    	"<div class='form-group' style='margin-bottom: 10%;' >" 
					+			"<label class='col-sm-4' >Fecha de cita: </label>"
					+			"<div class='col-sm-8'>"
					+				"<input class='form-control ' disabled='disabled' value='"+data[int].fkSicCitaMedica.fxCitaMedica+"'/>"
					+			"</div>"
					+		"</div>"
					+    	"<div class='form-group' style='margin-bottom: 24%;' >"
					+    		"<label class='col-sm-4' >Diagnostico: </label>"
					+			"<div class='col-sm-8'>"
					+     			"<textarea class='form-control' disabled='disabled' rows='2' >"+data[int].fkSicCitaMedica.diagnostico+"</textarea>"				
					+			"</div>"
					+		"</div>"
					+		"<div class='form-group' style='margin-bottom: 38%;' >"
					+    		"<label class='col-sm-4' >Signos y sintomas: </label>"
					+			"<div class='col-sm-8'>"
					+     			"<textarea class='form-control' disabled='disabled' rows='2' >"+data[int].fkSicCitaMedica.signosSintomas+"</textarea>"				
					+			"</div>"
					+		"</div>"
					+    	"<div class='form-group' >"
					+    		"<label class='col-sm-4' >Comentario: </label>"
					+			"<div class='col-sm-8' >"
					+				"<textarea class='form-control' disabled='disabled' rows='2' >"+data[int].fkSicCitaMedica.comentario+"</textarea>"
					+			"</div>"	
					+		"</div>"
					+	 "</div>" 
					+    "<div class='col-md-6'>"
					+    	"<div class='form-group' style='margin-bottom: 10%;' >"
					+    		"<label class='col-sm-4' >Tratamiento: </label>"
					+			"<div class='col-sm-8' >"
					+				"<input class='form-control col-s' disabled='disabled' value='"+data[int].fkSicCatMedicamentos.name+"'/>"
					+			"</div>"
					+		"</div>"
					+    	"<div class='form-group' style='margin-bottom: 24%;' >"
					+    		"<label class='col-sm-4' >Dosis: </label>"
					+			"<div class='col-sm-8' >"
					+				"<textarea class='form-control' disabled='disabled'>"+data[int].dosis+"</textarea>"
					+			"</div>"
					+		"</div>"
					+    	"<div class='form-group' >"
					+    		"<label class='col-sm-4' >Periodisidad: </label>"
					+			"<div class='col-sm-8' >"
					+				"<input class='form-control' disabled='disabled' value='"+data[int].periodisidad+"'/>"
					+			"</div>"
					+		"</div>"	
					+	 "</div>" 
					+	"</div></div></div>";
				}
 	    		
 	    		$("#historial").html(historial);
 	    		
 	    		$('.exams , .print').qtip({
// 	    		    content: {
// 	    		        text: 'Inactivar usuario'
// 	    		    },
 	    		    style: 
 	    		    {
 	    		        classes: 'qtip-bootstrap qtip-shadow'
 	    		    }
 	    		});
 	    		
			},
				
		error: function(e)
			{
				$(".alert").hide();
				$("#alertaError").show();
			}
 	    });
};

var clearModalExam = function()
{
	$('#formExamen').trigger("reset");
		$("#result").closest('.form-group')
		.removeClass('has-error').removeClass('has-success');
};

var printCita = function(id)
{
	 var expPac = $("#pacTable tbody tr.selected td:nth-child(2)").html();
	 var namePac = $("#pacTable tbody tr.selected td:nth-child(3)").html() +", "+$("#pacTable tbody tr.selected td:nth-child(4)").html();
	
	 var headElements = "<p><span>Numero de expediente: "+expPac+"</span></p><p><span>Nombre del paciente: "+namePac+"</span></p><div class='img'><img height='75px' width='100px' src='/sicmec/resources/images/logo_minsal.png'/></div>";
	 
	 var options = { mode : 'popup', popClose : true ,extraHead : headElements,popTitle : '' };
	 
	$("#body"+id).printArea(options);
};

var eliminarExam = function (id)
{
	$.ajax({
	 	    type: "GET",
	 		url: "/sicmec/control/cita/eliminarExam",	
	 		data : ({exam:id}),
	 	    success: function(data)
	 	    	{
	 	    		if(data == true)
	 	    			{
	 	    				$(".alert").hide();
	 	    				$("#alertaExamDelete").show();
	 	    				callExams(Cita);
	 	    			}
	 	    		else
	 	    			{
	 	    				$(".alert").hide();
	 	    				$("#alertaExamDeleteError").show();
	 	    			}
				},
					
			error: function(e)
				{
					$(".alert").hide();
					$("#alertaExamDeleteError").show();
				}
	 	    });
};

var generarGrafico = function (paciente,tipo)
{
	limpiarGrafico();
	
	var tr = "";
	$.ajax({
 	    type: "GET",
 		url: "/sicmec/control/cita/grafico",	
 		data : ({pac:paciente,tipo:tipo}),
 	    success: function(data)
 	    	{
 	    		
 	    		
 	    		if(data[0].tipo == 'PRESION ARTERIAL')
 	    		{
 	    			$("#preasuretitle").text("Grafico progresivo de salud: "+data[0].tipo);
 	    			
 	    			for (var int = 0; int < data.length; int++) 
 	 	    		{
 	    				var res = data[int].resultado.split("/");
 	    				
 	 	    			tr = tr 
 	 	    				 + "<tr>"
 	 	    				 + "<td>"+data[int].fx+"</td>"
 	 	    				 + "<td>"+res[0]+"</td>"
 	 	    				 + "<td>"+res[1]+"</td>"
 	 	    				 + "</tr>";
 					}
 	    			
 	    			$("#historicPreasureChart").html(tr);
 	 	    		$("#historicPreasureTable").highchartTable();
 	    		}
 	    		else
 	    		{
 	    			$("#title").text("Grafico progresivo de salud: "+data[0].tipo);
 	    			
 	    			for (var int = 0; int < data.length; int++) 
 	 	    		{
 	 	    			tr = tr 
 	 	    				 + "<tr>"
 	 	    				 + "<td>"+data[int].fx+"</td>"
 	 	    				 + "<td>"+data[int].resultado+"</td>"
 	 	    				 //+ "<td>"+data[int].comentario+"</td>"
 	 	    				 + "</tr>";
 					}
 	 	    		
 	 	    		$("#historicChart").html(tr);
 	 	    		$("#historicTable").highchartTable();
 	    		}
 	    		
 	    		
			},
				
		error: function(e)
			{
				
			}
 	    });
};

var limpiarGrafico = function()
{
	$("#historicChart tr").remove();
	$("#historicPreasureChart tr").remove();
	$("div[data-highcharts-chart]").remove();
};




