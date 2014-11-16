/**
 * @Autor Pablo Portillo
 * @Fecha 10/11/2014
 */

$( document ).ready(function() 
{

	
	$(".close").click(function(){
		$(".alert").hide();
	});
	
	$("#buscar").click(function(){
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
 	    		if(data.length != 0)
 	    		{
 	    			$("#tablaPacientes tr").remove();
 	    			
 	    			for (var int = 0; int < data.length; int++) 
 	 	    		{
 	    				agregarPaciente(data[int]);
 					}
 	    		}
 	    		else
 	    		{
 	    			ceroResultados();
 	    		}
 	    		
 	    		
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