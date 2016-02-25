

 $(document).ready(function(){
 $("#demo").on("hide.bs.collapse", function(){
      $(".btn").html('<span class="glyphicon glyphicon-collapse-down"></span> Open');
     });
 $("#demo").on("show.bs.collapse", function(){
  $(".btn").html('<span class="glyphicon glyphicon-collapse-up"></span> Close');
     });
 });

$(document).ready(function(){
 $("#listarViaje").on("hide.bs.collapse", function(){
      $(".btn").html('<span class="glyphicon glyphicon-collapse-down"></span> Open');
     });
 $("#listarViaje").on("show.bs.collapse", function(){
  $(".btn").html('<span class="glyphicon glyphicon-collapse-up"></span> Close');
     });
 });

