/**
 * @Autor Pablo Portillo
 * @Fecha 10/11/2014
 */

$( document ).ready(function() 
{
	var $selected = $(this).find(':selected');
	$("#paqMedDes").html($selected.data('description'));
	
	$("#tratamiento").change(function()
	{
		var $selected = $(this).find(':selected');
		$("#paqMedDes").html($selected.data('description'));
	});
	
	$(".sintomasList").mCustomScrollbar({
		    theme:"dark"
		});
	
	$(".close").click(function(){
		$(".alert").hide();
	});
	
	$("#limpiarFormulario").click(function(){
		limpiarModalCitas();
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
 				var paciente = $("#id").val();
 				var diagnostico = $("#diagnostico").val();
 				
 				var signosSintomas = "";
 				
 				$("input[name='signosSintomas']:checked").each(function( index ) 
 		 				{
 							signosSintomas += $(this).attr('value');
 							signosSintomas += ", ";
 		 				});
 		 				
 				signosSintomas += $("#otrosSignosSintomas").val();
 		 				
 				var peso = $("#peso").val();
 				var estatura = $("#estatura").val();
 				var comentario = $("#comentario").val();
 				var tratamiento = $("#tratamiento").val();
 				var dosis = $("#dosis").val();
 				var periodisidad = $("#periodisidad").val();
 				
 				
 				$.ajax({
 	 		 	    type: "POST",
 	 		 		url: "/sicmec/control/guardarCita",	
 	 		 		data : ({diag:diagnostico,signosSintomas:signosSintomas,peso:peso,estatura:estatura,cmt:comentario,paqMed:tratamiento,dosis:dosis,per:periodisidad,pac:paciente}),
 	 		 	    success: function(data)
 	 		 	    	{
 	 		 	    		if(data == "ok")
 	 		 	    			{
 	 		 	    				$("#citaSuccess").show();
 	 		 	    				limpiarModalCitas();
 	 		 	    				historialPaciente(paciente);
 	 		 	    			}
 	 		 	    		else
 	 		 	    			{
 	 		 	    				limpiarModalCitas();
 	 		 	    				$("#citaError").show();
 	 		 	    			}
 	 					},
 	 						
 	 				error: function(e)
 	 					{
 	 						limpiarModalCitas();
 	 						$("#citaError").show();
 	 					}
 	 		 	    });	
 		}
 		 
 	});
	
	$("#printGraph").click(function()
			{
				 var expPac = $("#exp").val();
				 var namePac = $("#nombrePaciente").text();
				 //alert($("#exp").val());
				 //alert($("#nombrePaciente").text());
				 var headElements = "<p><span>Numero de expediente: "+expPac+"</span></p><p><span>Nombre del paciente: "+namePac+"</span></p>";
				 
				 var options = { mode : 'popup', popClose : true ,extraHead : headElements,popTitle : '' };
				 
				$("#printHistorico").printArea(options);
			
			});
	
	$("#doChart").click(function(){
		
		var tipo = $("#tipoExamenGrafico").val();
		var paciente = $("#id").val();
		
		generarGrafico(paciente,tipo);
			
	});

	historialPaciente($("#id").val());
	
	$("#modalExamen").on('show.bs.modal', function (e) 
			{
				$("#resMessage").html("<span>Resultado &#91;mg&#47;dl&#93;</span>");
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
 		 		 		url: "/sicmec/control/guardarExam",	
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
 		 		 		url: "/sicmec/control/guardarExam",	
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
	
});


var historialPaciente = function (id)
{
	
	$("#historial div").remove();
	
	var historial = "";
	
	cargandoHistorico();
	
	setTimeout(
			function()
			{
				$.ajax({
			 	    type: "POST",
			 		url: "/sicmec/control/historialPac",	
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
								+	"<button title='Exportar a pdf' class='btn btn-default btn-sm' style='padding: 2px 6px; margin-left: 0.25%;' onclick='pdfCita("+int+")' ><i class='fa fa-file-pdf-o fa-lg'></i></button>"
								+	"<h4 class='panel-title' style='display: inline-block; padding-left: 1%;'>" 
								+    "<a data-toggle='collapse' data-parent='#historial' class='collapsed' href='#body"+int+"' aria-expanded='false' aria-controls='body"+int+"'>" 
								+	 "Fecha: "+data[int].fkSicCitaMedica.fxCitaMedica
								//+	 "Doctor: "
								//+    data[int].fkSicCitaMedica.fkSicUsuario.sicPersona.nombre+", "+data[int].fkSicCitaMedica.fkSicUsuario.sicPersona.apellido 
								+    "</a></h4></div>" 
								+	"<div id='body"+int+"' class='panel-collapse collapse' role='tabpanel' aria-labelledby='body"+int+"'>"
								+    "<span class='doctorName'>" +
										"Doctor: "+data[int].fkSicCitaMedica.fkSicUsuario.sicPersona.nombre+", "+data[int].fkSicCitaMedica.fkSicUsuario.sicPersona.apellido
								+    "</span>"
								+	 "<div class='panel-body' style='padding-left: 12px;' >"					
								+    	"<p>" 
								+			"<strong>Fecha: </strong>"
								+			data[int].fkSicCitaMedica.fxCitaMedica
								+		"</p>"
								+    	"<p>"
								+    		"<strong>Diagnostico: </strong>"
								+			data[int].fkSicCitaMedica.diagnostico
								+		"</p>"
								+		"<p>"
								+    		"<strong>Signos y sintomas: </strong>"
								+			data[int].fkSicCitaMedica.signosSintomas
								+		"</p>"
								+    	"<p>"
								+    		"<strong>Comentario: </strong>"
								+			data[int].fkSicCitaMedica.comentario
								+		"</p>"
								 
								
								+    	"<p>"
								+    		"<strong>Tratamiento: </strong>"
								+			 data[int].fkSicCatMedicamentos.name
								+		"</p>"
								+    	"<p>"
								+    		"<strong>Dosis: </strong>"
								+				data[int].dosis
								+		"</p>"
								+    	"<p>"
								+    		"<strong>Periodisidad: </strong>"
								+			data[int].periodisidad
								+		"</p>"	
								 
								+	"</div></div></div>";
							}
			 	    		
			 	    		
			 	    		$("#historial").html(historial);
			 	    		
			 	    		if(data.length > 0)
			 	    		{
			 	    			$("#tituloHistorial").html("Historial de &uacute;ltimas citas &#40;"+data.length+"&#41;");
			 	    		}	
			 	    		else
			 	    		{
			 	    			$("#tituloHistorial").html("Historial de citas &#40;"+data.length+"&#41;");
			 	    		}
			 	    		
			 	    		
			 	    		$('.btn').qtip({
//			 	    		    content: {
//			 	    		        text: 'Inactivar usuario'
//			 	    		    },
			 	    		    style: 
			 	    		    {
			 	    		        classes: 'qtip-bootstrap qtip-shadow'
			 	    		    }
			 	    		});
			 	    		
			 	    		$("#historial").mCustomScrollbar({
			 	    		    theme:"dark"
			 	    		});
			 	    		
			 	    		$("#load").remove();
    						$("#historial").removeAttr("style");
						},
							
					error: function(e)
						{
							$(".alert").hide();
							$("#alertaError").show();
						}
			 	    });
			}, 3000);
	
};


function callExams(cita)
{
	Cita = cita;
	
	var tr = "";
	$.ajax({
 	    type: "GET",
 		url: "/sicmec/control/exams",	
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

var clearModalExam = function()
{
	$('#formExamen').trigger("reset");
		$("#result").closest('.form-group')
		.removeClass('has-error').removeClass('has-success');
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

var printCita = function(id)
{
	 var expPac = $("#exp").val();
	 var namePac = $("#nombrePaciente").html();
	
	 var headElements = "<p><span>Numero de expediente: "+expPac+"</span></p><p><span>Nombre del paciente: "+namePac+"</span></p><div class='img'><img height='75px' width='100px' src='/sicmec/resources/images/logo_minsal.png'/></div>";
	 
	 var options = { mode : 'popup', popClose : true ,extraHead : headElements,popTitle : '' };
	 
	$("#body"+id).printArea(options);
};

var pdfCita = function (id)
{
	var doc = new jsPDF();
	
	 var expPac = $("#exp").val();
	 var namePac = $("#nombrePaciente").html();
	 
	 var specialElementHandlers = {
			    '#bypassme': function(element, renderer){
			        return true;
			    }
			};
	 
	doc.fromHTML(
			"<p><span>Numero de expediente: "+expPac+"</span></p><p><span>Nombre del paciente: "+namePac+"</span></p>"+
			$("#body"+id).html(), 15, 15, 
	{
	        'width': 500, 
	        'elementHandlers': specialElementHandlers
    });
	
	doc.save('cita.pdf');
	
};

var generarGrafico = function (paciente,tipo)
{
	limpiarGrafico();
	
	var tr = "";
	$.ajax({
 	    type: "GET",
 		url: "/sicmec/control/grafico",	
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

var cargandoHistorico = function ()
{	
	$("#historial").attr("style","opacity: 0.95;")
	$("#historial").append("<div id='load' style='width: 100% height: 100%;"+
			  "opacity: 0.35;'>" +
		"<img src='../resources/images/ajax-loader.gif' style='position: absolute; right: 50%; top: 50%;'/></div>");
};

var limpiarModalCitas = function ()
{
	$('#nuevaCitaForm').find('div,input,label,select').removeClass('has-success');
	$('#nuevaCitaForm').find('div,input,label,select').removeClass('has-error');
	$("#nuevaCitaForm").each(function(e){
		this.reset();
	});
	$('#nuevaCitaForm').find('span').text('');
};