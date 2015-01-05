$(document).ready(function(){
		/*--------------------------Validaciones---------------------------*/
	
		/*---------------------------Triggers-----------------------------*/
	
		/**
		 * Trigger para eliminar los medicamentos del paquete
		 */
		$("#moveRight").click(function(e) {
			var selectedOpts = $('#actualMeds option:selected');
			selectedOpts.removeClass('agregados');
			$('#availableMeds').append( $(selectedOpts).clone() );
			$(selectedOpts).remove();
		});
		
		/**
		 * Trigger para agregar los medicamentos al paquete
		 */
		$("#moveLeft").click(function(e) {
			var selectedOpts = $('#availableMeds option:selected');
			selectedOpts.addClass('agregados');
			$('#actualMeds').append( $(selectedOpts).clone() );
			$(selectedOpts).remove();			
		});
		
		/**
		 * Trigger para guardar la informacion de los medicamentos asociados al paquete
		 */
		$("#guardar").click(function(){
			//Variables con los id de los medicamentos y el id del paquete
			var aMedicamentosPaquete = new Array();
			var iPaqId = $('#medPaqs').val();
			//Recorremos los medicamentos agregados
			$('.agregados').each(function(){
				aMedicamentosPaquete.push( $(this).val() );
			});
			//Enviamos la peticion ajax en la function
			bResponse = savePaqMed(iPaqId, aMedicamentosPaquete);
		});
		
		/**
		 * Evento para el cambio de paquete de medicamento
		 */
		$("#medPaqs").change(function(){
			//Obtenemos el valor seleccionado
			var iPaqId = $(this).val();
			//Validamos que no este vacio
			if ( iPaqId == 0 || iPaqId == null )
			{
				alert("Invalido");
				return false;
			}
			//funcion ajax
			bResponse = loadMedicaments( iPaqId );
		});
		
		/*---------------------------Funciones-----------------------------*/
		
		/**
		 * Funcion para enviar la peticion ajax para guardar un paquete de medicamentos
		 */
		function savePaqMed( iPaqId, aMeds )
		{
			console.log("id "+iPaqId+"<>Meds "+aMeds);
			//Validamos que no este vacio el id del paquete
			if ( iPaqId == null || iPaqId == 0 )
			{
				alert("Error!!!!");
				return false;
			}
			//Validamos si el paquete esta vacio
			if ( aMeds == null || aMeds == "" )
			{
				aMeds[0] = "false";
			}
			//Peticion ajax
			$.ajax
			({
				type: "POST",
				url:"/sicmec/admin/paq/addMedPaq",
				data: {"idPaq" : iPaqId, "idMeds[]" : aMeds},
				success:function(result)
				{
					alert('Guardado');
				},
				error: function (xhr, ajaxOptions, thrownError) 
				{
					alert("unable to find server..")
			    }
			});
			
		}
		
		/**
		 * Funcion que envia la peticion ajax para la carga de medicamentos por paquete
		 */ 
		function loadMedicaments ( iPaqMed )
		{
			//Peticion ajax para obtener todos los medicamentos fuera del paquete
			$.ajax
			({
				type: "GET",
				url:"/sicmec/admin/paq/medsOut/"+iPaqMed,
				success:function(result)
				{
					$("#availableMeds option").remove();
					for (var int = 0; int < result.length; int++) 
    				{
						console.log("OUT:"+result[int]);
						createMedAvailableList(result[int]);
    				}
					//Peticion ajax para obtener todos los medicamentos del paquete
					$.ajax
					({
						type: "GET",
						url:"/sicmec/admin/paq/medsIn/"+iPaqMed,
						success:function(result)
						{
							for (var int = 0; int < result.length; int++) 
		    				{
								console.log("IN:"+result[int]);
		    					createMedList(result[int]);
		    				}
						},
						error: function (xhr, ajaxOptions, thrownError) 
						{
							alert("unable to find server..")
					    }
					});
				},
				error: function (xhr, ajaxOptions, thrownError) 
				{
					alert("unable to find server..")
			    }
			});			
		}
		
		/**
		 * Funcion utilizada para crear las opciones de los medicamentos del paquete
		 * @param oMed
		 */
		function createMedList( oMed )
		{
			//Obtenemos los objetos
			var oSelect = document.getElementById("actualMeds");
			var oOption = document.createElement("option");
			//Establecemos el texto del option y sus atributos
			var sText =  document.createTextNode(oMed.drugName);
			oOption.setAttribute("value",oMed.idDrug);
			oOption.setAttribute("class","agregados");
			//Agregamos el texto al option
			oOption.appendChild(sText);
			//Agregamos el option al select
			oSelect.appendChild(oOption);			
		}
		
		/**
		 * Funcion utilizada para crear las opciones de los medicamentos disponibles
		 * @param oMed
		 */
		function createMedAvailableList( oMed )
		{
			//Obtenemos los objetos
			var oSelect = document.getElementById("availableMeds");
			var oOption = document.createElement("option");
			//Establecemos el texto del option y sus atributos
			var sText =  document.createTextNode(oMed.drugName);
			oOption.setAttribute("value",oMed.idDrug);
			//Agregamos el texto al option
			oOption.appendChild(sText);
			//Agregamos el option al select
			oSelect.appendChild(oOption);			
		}
});
