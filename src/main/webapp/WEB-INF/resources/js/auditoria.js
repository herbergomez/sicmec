/**
 * 
 */

$( document ).ready(function() 
{
	$(".datatables").DataTable({
		"scrollY": "350px",
		"bFilter": false,
		"bLengthChange": false,
        "scrollCollapse": false,
        "aoColumns": 
        	[
        	 { "sWidth": "10%", "sClass": "center", "bSortable": false },
        	 { "sWidth": "20%", "sClass": "center"},
        	 { "sWidth": "50%", "bSortable": false },
        	 { "sWidth": "20%"}
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
            }  
	});
	
	
	$('#desde').val(function(){
		  var d = new Date();
		  if((d.getMonth()+1)<10)
		  {
			month = "0"+(d.getMonth()+1);  
		  }
		  else
		  {
			  month = d.getMonth()+1;
		  }	  
		  if(d.getDate() < 10)
		  {
			  day = "0"+d.getDate();
		  }
		  else
		  {
			  day = d.getDate();
		  }
		  return d.getFullYear() + "-" + (month) + "-" + day + " 00:00:00";
		});
	
	$('#hasta').val(function(){
		  var d = new Date();
		  if((d.getMonth()+1)<10)
		  {
			month = "0"+(d.getMonth()+1);  
		  }
		  else
		  {
			  month = d.getMonth()+1;
		  }	
		  
		  if(d.getDate() < 10)
		  {
			  day = "0"+d.getDate();
		  }
		  else
		  {
			  day = d.getDate();
		  }
		  return d.getFullYear() + "-" + (month) + "-" + day + " 23:59:59";
		});
});	