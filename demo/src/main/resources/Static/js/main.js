jQuery(function(){
    $("#btn").on("click", function(){
        if($("#ct-publicacion").is(" :visible")){
            $("#ct-publicacion").hide("slow");
            $("#ct-publicaciones").hide("slow");
            $("#ct-trabajo").show("slow");
            $("#ct-trabajos").show("slow");
            $("#btn").text("Publicaciones");
        }else{
            $("#ct-trabajo").hide("slow");
            $("#ct-trabajos").hide("slow");
            $("#ct-publicacion").show("slow");
            $("#ct-publicaciones").show("slow");
            $("#btn").text("Trabajos");
        }
    })
   
        
    
})