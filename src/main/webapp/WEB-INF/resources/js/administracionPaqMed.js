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
		
		/*---------------------------Funciones-----------------------------*/
		
		/**
		 * Funcion para enviar la peticion ajax para guardar un paquete de medicamentos
		 */
		function savePaqMed( iPaqId, aMeds )
		{
			//Validamos que no este vacio el arreglo ni el id del paquete
			if ( iPaqId == null || iPaqId == 0 || aMeds.lenght <= 0 )
			{
				//Mostrar msj
				return false;
			}
			//Peticion ajax
			$.ajax
			({
				type: "POST",
				url:"/sicmec/admin/paq/addMedPaq",
				data: {idPaq : iPaqId, idMeds : $.serialize(aMeds)},
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
		 
		
});