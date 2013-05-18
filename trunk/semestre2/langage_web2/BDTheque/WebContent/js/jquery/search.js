$(document).ready(function(){
	$("#recherche").keyup(function(){
		var recherche =$(this).val();
		var data ='recherche=' + recherche;
		if(recherche.length>0){
			$.ajax({
				type : "GET",
				url : "/BDTheque/SearchLive",
				data : data,
				success : function(server_response){
						$("#resultat").html(server_response).show();
				}
			});
		}else{
			$("#resultat").hide();
		}
	});
});