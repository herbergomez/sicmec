/**
 * 
 */
$( document ).ready(function() {	
	$("#pais").change(function(){
        var id=$("#pais").val();
          $.ajax({
         type:"GET",
         url:"/sicmec/admin/reportes/getDepartamentos/"+id,
         success:function(result)
         {
        $("#departamento option").remove();
        $("#municipio option").remove();
           				
       for (var int = 0; int < result.length; int++) {
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
$("#departamento").change(function()
       {
   var id = $("#departamento").val();
                   		
   $.ajax
         ({
           type: "GET",
           url:"/sicmec/admin/reportes/getMunicipios/"+id,
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

});

function createDepaList(dep)
{
	var select = document.getElementById("departamento");
	var option = document.createElement("option");
	var text =  document.createTextNode(dep.nombreDepartamento);
	option.setAttribute("value",dep.idSicDepartamento);	
	option.appendChild(text);
	select.appendChild(option);	
};	
function createMunList(mun)
{
	var select = document.getElementById("municipio");
	var option = document.createElement("option");
	var text =  document.createTextNode(mun.nombreMunicipio);
	option.setAttribute("value",mun.idSicMunicipio);
	
	option.appendChild(text);
	select.appendChild(option);
	
};