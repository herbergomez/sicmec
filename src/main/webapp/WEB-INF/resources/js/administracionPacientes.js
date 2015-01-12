/**
 * 
 */

$( document ).ready(function() {	
	$('#agregarPacienteForm').validate({
		errorElement: "span",
		rules: {
		   expediente:
		   {
			   required:true,
			   maxlength: 10,
			   remote:
			   {
		    		type: "POST",
		    		url: "/sicmec/Utils/validarExpediente",
	                	data : 
	                	{
	                        paciente: function() 
	                        { return $("#expediente").val(); }
	                	}
		     	}
		   },
		   estado:
		   {
			   required:true
		   },
		   fcreacion:
		   {
			   required:true 
		   },
		   patologia:{
			   required:true
		   },		   		   
		   nombres: 
		   {
		     	required: true,
		     	maxlength: 20,
		     	minlength: 3
		   },
		   apellidos: 
		   {
		        required: true,
		        maxlength: 20,
		     	minlength: 3
		   },
		   mail: 
		   {
			    email: true
		   },
		   fnacimiento:
		   {
			   required: true
		   },
		   pais:
		   {
			   required:true
		   },
		   departamento:
		   {
			   required:true
		   },
		   municipio:
		   {
			   required:true
		   },		   
		   telefono:
		   {
			   required: true
		   },
		   direccion:
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
	$('#modificarPacienteForm').validate({
		errorElement: "span",
		rules: {
		   estadoUp:
		   {
		       required:true
		   },
		   fcreacionUp:
		   {
			   required:true 
		   },
		   patologiaUp:{
		       required:true
		   },
		   nombresUp: 
		   {
		     	required: true,
		     	maxlength: 20,
		     	minlength: 3
		   },
		   apellidosUp: 
		   {
		        required: true,
		        maxlength: 20,
		     	minlength: 3
		   },
		   mailUp: 
		   {
			    email: true
		   },
		   fnacimientoUp:
		   {
			   required: true
		   },
		   telefonoUp:
		   {
			   required: true
		   },
		   direccionUp:
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
		"scrollY":        "350px",
        "scrollCollapse": false,
        "aoColumns": 
        	[
        	 { "sWidth": "10%", "sClass": "center", "bSortable": false },
        	 { "sWidth": "10%" },
        	 { "sWidth": "10%" },
        	 { "sWidth": "10%" },
        	 { "sWidth": "10%" },
        	 { "sWidth": "10%" },
        	 { "sWidth": "5%" },
        	 { "sWidth": "10%" },
        	 { "sWidth": "10%" },
        	 { "sWidth": "10%" },
        	// { "sWidth": "5%" },
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
                "fnDrawCallback": function() {
                	//$("#tblPaciente tbody" ).on('click', 'tr.clickRow',  function() {
                //		if ( $(this).hasClass('selected') ) {
                        //    $(this).removeClass('selected');
                       // }
                       // else {
                      //  	$('#tblPaciente').DataTable().$('tr.selected').removeClass('selected');
                    //        $(this).addClass('selected');
                  //      }		 
                //	});             	         
                	$(".onUpdate").click(function(){
                		var id = $(this).data("id");
                		
                		$.ajax
                		({
                			type: "GET",
                			url:"/sicmec/admin/pacientes/getPaciente/"+id,
                			success:function(result)
                			{
                				$("#idUp").val(result.idSicPaciente);
                				$("#expedienteUp").val(result.numExpediente);
                				$("#patologiaUp").val(result.fkSicTipoPatologia.idSicTipoPatologia);
                				
                				$("#nombresUp").val(result.fkSicPersona.nombre);
                				$("#apellidosUp").val(result.fkSicPersona.apellido);
                				$("#mailUp").val(result.fkSicPersona.email);
                				$("#telefonoUp").val(result.telefonoPaciente);
                				$("#duiUp").val(result.documentoIdentidad);
                				var sexo=result.sexoPaciente;
                				var val="";
                				if (sexo=="M"){
                					val="Masculino";
                				} else {
                					if (sexo=="F"){
                						val="Femenino";
                					}
                				}
                				$("#sexoUp").val(val);
                				$("#estadoUp").val(result.fkSicEstadoPaciente.idSicEstadoPaciente);
                				$("#direccionUp").val(result.direccionPaciente);
                				$("#fnacimientoUp").val(result.fxNacimiento);
                				$("#fcreacionUp").val(result.fxCreacion);
                				var idMuni= result.fkSicMunicipio.idSicMunicipio;
                				var idDepa= result.fkSicMunicipio.fkSicDepartamento.idSicDepartamento;
                				var idPais= result.fkSicMunicipio.fkSicDepartamento.fkSicPais.idSicPais;
                							
                				getDepaByPais(idPais,idDepa);
                				getMuniByDep(idDepa,idMuni);
                				$("#paisUp").val(result.fkSicMunicipio.fkSicDepartamento.fkSicPais.idSicPais);		
                				//DATOS DE REPONSABLE DEL PACIENTE.
                				if(result.fkSicContactoPaciente!=null){
                					$("#nomContactUp").val(result.fkSicContactoPaciente.nombreContacto);
                					$("#apContactUp").val(result.fkSicContactoPaciente.apellidoContacto);
                					$("#duiContactUp").val(result.fkSicContactoPaciente.dui);
                					$("#telContactUp").val(result.fkSicContactoPaciente.telefono);
                				} else {
                					$("#nomContactUp").val("");
                					$("#apContactUp").val("");
                					$("#duiContactUp").val("");
                					$("#telContactUp").val("");
                				}
                			},
                			error: function (xhr, ajaxOptions, thrownError) 
                			{
                				alert("unable to find server..")
                		    }
                		});
                	});
                	$(".onDelete").click(function(){
                		var id = $(this).data("id");
                		
                		$("#AreYouSureConfirm").attr("href","/sicmec/admin/pacientes/delPaciente/"+id);
                		//$("#modalEliminarPaciente").modal('show');
                		
                	});
                	$("#pais").change(function(){
                		var id=$("#pais").val();
                		$.ajax({
                			type:"GET",
                			url:"/sicmec/admin/pacientes/getDepartamentos/"+id,
                			success:function(result)
                			{
                				$("#departamento option").remove();
                				$("#municipio option").remove();
                				
                				for (var int = 0; int < result.length; int++) 
                				{
                					createDepaList(result[int]);
                				}
                			},
                			error: function (xhr, ajaxOptions, thrownError) 
                			{
                				alert("unable to find server..");
                				$("#departamento option").remove();
                		    }
                		});
                	});
                	$("#paisUp").change(function(){
                		var id=$("#paisUp").val();
                		$.ajax({
                			type:"GET",
                			url:"/sicmec/admin/pacientes/getDepartamentos/"+id,
                			success:function(result)
                			{
                				$("#departamentoUp option").remove();
                				$("#municipioUp option").remove();
                				
                				for (var int = 0; int < result.length; int++) 
                				{				
                					createDepaListUp(result[int]);
                				}
                			},
                			error: function (xhr, ajaxOptions, thrownError) 
                			{
                				alert("unable to find server..");
                				$("#departamentoUp option").remove();
                		    }
                		});
                	});
                	$("#departamento").change(function()
                	{
                		var id = $("#departamento").val();
                		
                		$.ajax
                		({
                			type: "GET",
                			url:"/sicmec/admin/pacientes/getMunicipios/"+id,
                			success:function(result)
                			{	
                				$("#municipio option").remove();
                				
                				for (var int = 0; int < result.length; int++) 
                				{
                					createMunList(result[int]);
                				}
                			},
                			error: function (xhr, ajaxOptions, thrownError) 
                			{
                				alert("unable to find server..");
                				$("#municipio option").remove();
                		    }
                		});
                	});	
                	$("#departamentoUp").change(function()
                			{
                				var id = $("#departamentoUp").val();
                				$.ajax
                				({
                					type: "GET",
                					url:"/sicmec/admin/pacientes/getMunicipios/"+id,
                					success:function(result)
                					{
                						$("#municipioUp option").remove();
                						
                						for (var int = 0; int < result.length; int++) 
                						{
                							createMunListUp(result[int]);
                						}
                					},
                					error: function (xhr, ajaxOptions, thrownError) 
                					{
                						alert("unable to find server..");
                						$("#municipio option").remove();
                				    }
                				});
                				
                		});
            }   
	});
});


function createMunList(mun)
{
	var select = document.getElementById("municipio");
	var option = document.createElement("option");
	var text =  document.createTextNode(mun.nombreMunicipio);
	option.setAttribute("value",mun.idSicMunicipio);
	
	option.appendChild(text);
	select.appendChild(option);
	
};
function createMunListUp(mun)
{
	var select = document.getElementById("municipioUp");
	var option = document.createElement("option");
	var text =  document.createTextNode(mun.nombreMunicipio);
	option.setAttribute("value",mun.idSicMunicipio);	
	option.appendChild(text);
	select.appendChild(option);
    
};


function createDepaList(dep)
{
	var select = document.getElementById("departamento");
	var option = document.createElement("option");
	var text =  document.createTextNode(dep.nombreDepartamento);
	option.setAttribute("value",dep.idSicDepartamento);	
	option.appendChild(text);
	select.appendChild(option);	
};
function createDepaListUp(dep)
{
	var select = document.getElementById("departamentoUp");
	var option = document.createElement("option");
	var text =  document.createTextNode(dep.nombreDepartamento);
	option.setAttribute("value",dep.idSicDepartamento);	
	option.appendChild(text);
	select.appendChild(option);	
};
function getDepaByPais(idPais,idDepa){
	$.ajax
	({
		type: "GET",
		url:"/sicmec/admin/pacientes/getDepartamentos/"+idPais,
		success:function(result)
		{
			$("#departamentoUp option").remove();
			$("#municipioUp option").remove();
			
			for (var int = 0; int < result.length; int++) 
			{
				createDepaListUp(result[int]);
			}
			$("#departamentoUp").val(idDepa);
		},
		error: function (xhr, ajaxOptions, thrownError) 
		{
			alert("unable to find server..");
			$("#departamentoUp option").remove();
	    }
	});
 };

function getMuniByDep(idDepa,idMuni){
	$.ajax
	({
		type: "GET",
		url:"/sicmec/admin/pacientes/getMunicipios/"+idDepa,
		success:function(result)
		{
			$("#municipioUp option").remove();
			
			for (var int = 0; int < result.length; int++) 
			{
				createMunListUp(result[int]);
			}
			$("#municipioUp").val(idMuni);
		},
		error: function (xhr, ajaxOptions, thrownError) 
		{
			alert("unable to find server..");
			$("#municipioUp option").remove();
	    }
	});
 };

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