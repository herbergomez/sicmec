/**
 * @Autor Pablo Portillo
 * @Fecha 10/11/2014
 */

var valido = false;
var citaMedica = "";

$( document ).ready(function() 
{
	$(".close").click(function(){
		
		$(".alert").hide();
		
	});
	
	$("#formularioRealizarEntrega").validate({
		errorElement: "span",
		rules: {
		   user: 
		   {
		     	required: true,
		     	maxlength: 20,
		     	minlength: 3
		   },
		   pass: 
		   {
		        required: true,
		        maxlength: 20,
		     	minlength: 3
		   },
		   motivo: 
		   {
			    required: true,
			    maxlength: 100,
		     	minlength: 5
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
		submitHandler: function(form)
		{
			var user = $("#user").val();
			var pass= $("#pass").val();
			var motivo = $("#motivo").val();
			realizarEntregaAutenticada(user,pass,motivo);	
		}
		 
	});
	
	$("#buscar").click(function()
	{
		var exp = $("#exp").val().trim(); 
		if(exp!="")
		{
			cargando();
			setTimeout(
			function()
			{ 
				$("#load").remove();
				$("#content").removeAttr("style");
				doHistory(exp);
				getTreatment(exp);
			},
			3000);
			
		}
		
	});
	
	$("#limpiar").click(function()
	{
		$("#exp").val("");
		limpiarFormulario();
		vaciarTablaMed();
	});
	
	$("#realizarEntrega").click(function()
	{
		if(citaMedica != "")
		{
			if(valido)
			{
				/* Si este paciente aun no ha recibido sus
				 * medicamentos en el periodo estipulado. 
				 */
				realizarEntrega();
			}
			else
			{
				/* Si este paciente ya ha recibido sus
				 * medicamentos en el periodo estipulado 
				 * el sistema pedira confirmación 
				 * (usuario y contraseña) para poder
				 * registrar la entrega. 
				 */
				$("#modalRealizarEntrega").modal("show");
				
				//alert("Este proceso requiere validación");
			}	
		}	
	});
});

var realizarEntregaAutenticada = function(user,pass,comentario)
{
	
	$.ajax
	({
		type: "POST",
		url:"/sicmec/farm/entregaMed/entregarTratAutenticada",
		data : ({tratamiento:citaMedica,cmt:comentario,usuario:user,pass:pass}),
		success:function(result)
		{
			$("#modalRealizarEntrega").modal("hide");
			$(".alert").hide();
			
			if(result == "ok")
			{
				doHistory($("#exp").val());
				$("#entrega").show();
			}
			else
			{
				$("#errorEntrega").show();
				$("#mensajeErrorEntrega").html(result);
			}	
		},
		error: function (error) 
		{
			$("#modalRealizarEntrega").modal("hide");
			$("#mensajeErrorEntrega").html(error);
	    }
	});
};

var realizarEntrega = function()
{
	$.ajax
	({
		type: "POST",
		url:"/sicmec/farm/entregaMed/entregarTrat",
		data : ({tratamiento:citaMedica}),
		success:function(result)
		{
			$(".alert").hide();
			
			if(result == "ok")
			{
				doHistory($("#exp").val());
				$("#entrega").show();
				valido = false;
			}
			else
			{
				$("#errorEntrega").show();
				$("#mensajeErrorEntrega").html(result);
			}	
		},
		error: function (xhr, ajaxOptions, thrownError) 
		{
			$("#mensajeErrorEntrega").html(error);
	    }
	});
};



var doHistory = function (id)
{
	var his = "";
	$.ajax
	({
		type: "GET",
		url:"/sicmec/farm/entregaMed/historial/"+id,
		success:function(result)
		{
			if(result.length != 0)
			{
				for (var int = 0; int < result.length; int++) 
				{
				   his += "<li class='list-group-item'>"+result[int].fxEntregaTratamiento+", Tipo: "+result[int].tipo+
				   "<a title='Ver detalle' style='padding: 2px 6px !important;' target='_blank' href='./detalleEntregaMed/"+result[int].idSicEntregaTratamiento+"' class='btn btn-sm btn-default pull-right onDetail'><i class='fa fa-search-plus'></i></a></li>";
				}
				
			}	
			else
			{
				his += "<li class='list-group-item'>No se encontrar&oacute;n resultados</li>";
			}
			
			$("#historial").html(his);
		
	    		$('.onDetail').qtip({
	    		    style: 
	    		    {
	    		        classes: 'qtip-bootstrap qtip-shadow'
	    		    }
	    		});
		},
		error: function (xhr, ajaxOptions, thrownError) 
		{
			
	    }
	});
	
};

var getTreatment = function (id)
{
	$.ajax
	({
		type: "GET",
		url:"/sicmec/farm/entregaMed/tratamiento/"+id,
		success:function(result)
		{
			if(result.length != 0)
			{
				if(result[0].entregaValida)
				{
					valido = true;
					//citaMedica = result[0].fkSicCitaMedica.idSicCitaMedica;
					citaMedica = result[0].idSicTratamiento;
//					alert(valido + citaMedica);
					new jBox('Notice', 
							{
									    content: 'El paciente aun no ha recibido su medicamento en este periodo',
									    color: 'green'
							});
				}
				else
				{
					valido = false;
					citaMedica = result[0].fkSicCitaMedica.idSicCitaMedica;
//					alert(valido + citaMedica);
					new jBox('Notice', 
					{
							    content: 'El paciente ya recibio su medicamento en este periodo',
							    color: 'red'
					});
				}	
				
				llenarTablaDeMedicamentos(result[0].listMeds);
				$("#per").val(result[0].periodisidad); 
				$("#dosis").text(result[0].dosis);
				$("#fecha").val(result[0].fxTratamiento);
			}
			else
			{
				valido = false;
				citaMedica = "";
				limpiarFormulario();
				vaciarTablaMed();
				new jBox('Notice', 
						{
								    content: 'No se encontro paciente con este expediente',
								    color: 'blue'
						});
			}
		},
		error: function (xhr, ajaxOptions, thrownError) 
		{
			
	    }
	});	
};

var llenarTablaDeMedicamentos = function (data)
{
	var tr="";
	
	for (var int = 0; int < data.length; int++) 
	{
		tr += "<tr><td>"+data[int].drugName+"</td></tr>";
	}
	
	$("#meds").html(tr);
	
};
var vaciarTablaMed = function()
{
	$("#meds tr").remove();
};
var limpiarFormulario = function()
{
	$("#historial li").remove();
	$("#per").val(""); 
	$("#dosis").text("");
	$("#fecha").val("");
};

var cargando = function ()
{	
	$("#content").attr("style","opacity: 0.35;")
	$("#content").append("<div id='load' style='width: 100% height: 100%;"+
			  "opacity: 0.35;'>" +
		"<img src='../resources/images/ajax-loader.gif' style='position: absolute; right: 50%; top: 50%;'/></div>");
};

