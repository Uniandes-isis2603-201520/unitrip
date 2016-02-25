
(function(ng){
  
  // define el módulo "paradaModule" con dependencia a ui.bootstrap
  var mod = ng.module("paradaModule", ["ui.bootstrap"]);
    
  // define una constante usada por el servicio y el mock del servicio
  mod.constant("paradaContext", "api/parada");
    
})(window.angular);