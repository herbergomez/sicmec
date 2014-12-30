/**
 * @Autor Pablo Portillo
 * @Fecha 10/11/2014
 */

$( document ).ready(function() 
{
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
});

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
				   his += "<li class='list-group-item'>"+int.fxEntregaTratamiento+
				   "<button style='padding: 2px 6px !important;' class='btn btn-sm btn-default pull-right'><i class='fa fa-search-plus'></i></button></li>";
				}
				
			}	
			else
			{
				his += "<li class='list-group-item'>No se encontrar&oacute;n resultados</li>";
			}
			
			$("#historial").html(his);
			
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
				llenarTablaDeMedicamentos(result[0].listMeds);
				$("#per").val(result[0].periodisidad); 
				$("#dosis").text(result[0].dosis);
			}
			else
			{
				$("#per").val(""); 
				$("#dosis").text("");
				vaciarTablaMed();
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

var cargando = function ()
{	
	$("#content").attr("style","opacity: 0.35;")
	$("#content").append("<div id='load' style='width: 100% height: 100%;"+
			  "opacity: 0.35;'>" +
		"<img src='../resources/images/ajax-loader.gif' style='position: absolute; right: 50%; top: 50%;'/></div>");
};

