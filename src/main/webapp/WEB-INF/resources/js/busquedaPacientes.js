/**
 * 
 */

$( document ).ready(function() {
	
	$(".datatables").DataTable({
		"pagingType": "simple_numbers",
        'bProcessing': true,
        'bServerSide': true,
        "iDisplayLength": 10,
        "iDisplayStart": 0,
        "bSortClasses": false,
        "sAjaxSource": '/sicmec/control/busquedaPacientes',
        'fnServerParams': function ( aoData ) 
        {
        	tipoBusqueda = $("#tipoBusqueda").val();
        	
            aoData.push( { "name": "tipoBusqueda", "value": tipoBusqueda } );
              
        },
        "bStateSave": true,
        'bJQueryUI': false,
        "aoColumns": 
        	[
        	 { "sWidth": "1%",data: null,"bSortable": false, render: function ( data, type, row )
        		 {
        		 	
            	 	return '<form action="/sicmec/control/expediente" method="post">'+
            	 	'<input name="exp" value="'+data.numExpediente+'" style="display:none;"></input>'+
            	 	'<button type="submit" class="btn btn-default btn-sm" title="Realizar cita">'+
        	 		'<i class="fa fa-stethoscope fa-lg"></i>'+
        	 		'</button></form>';
            	 	
            	 } 
        	 },
        	 { "sWidth": "10%",'mData': 'numExpediente' },
        	 { "sWidth": "10%",'mData': 'fkSicPersona.nombre' },
        	 { "sWidth": "10%",'mData': 'fkSicPersona.apellido' },
        	 { "sWidth": "10%",'mData': 'documentoIdentidad' },
        	 { "sWidth": "6%",'mData': 'edad' },
        	 { "sWidth": "10%",'mData': 'fkSicMunicipio.fkSicDepartamento.fkSicPais.nombrePais' },
        	 //{ "sWidth": "10%",'mData': 'fkSicMunicipio.fkSicDepartamento.nombreDepartamento' },
        	 //{ "sWidth": "10%",'mData': 'fkSicMunicipio.nombreMunicipio' },
        	 { "sWidth": "8%",'mData': 'fkSicEstadoPaciente.descripcion' },
        	// { "sWidth": "5%" },
            ],
            language: {
            	"sProcessing":     "<i class='fa fa-cog fa-spin'></i>",
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
                "sLoadingRecords": "<i class='fa fa-cog fa-spin fa-4x'></i>",
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
                	$('.btn').qtip({
//                	    content: {
//                	        text: 'Inactivar usuario'
//                	    },
                	    style: 
                	    {
                	        classes: 'qtip-bootstrap qtip-shadow'
                	    }
                	});
                }
	});

});
