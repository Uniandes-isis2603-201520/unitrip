(function (ng) {

    var mod = ng.module("mainApp", [
        "ui.router",
        
    ]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        
            $urlRouterProvider.otherwise("/inicio");
            
            $stateProvider
                    .state ('inicio', {
                    url: '/inicio',
                    templateUrl: "src/modules/inicio/inicio.tpl.html"
                    })
            $stateProvider
                    .state('config', {
                    url: '/config',
                    templateUrl: "src/modules/cuenta/config.tpl.html"
                    })
            $stateProvider
                    .state('evento', {
                    url: '/evento',
                    templateUrl: "src/modules/evento/evento.tpl.html"
                    })
            $stateProvider
                    .state('experiencias', {
                    url: '/experiencias',
                    templateUrl: "src/modules/experiencias/exp.tpl.html"
                    })
            $stateProvider
                    .state('parada', {
                    url: '/parada',
                    templateUrl: "src/modules/parada/parada.tpl.html"
                    })
            $stateProvider
                    .state('viajes', {
                    url: '/viajes',
                    templateUrl: "src/modules/viajes/viajes.tpl.html"
                    })
            $stateProvider
                    .state('itinerario', {
                    url: '/itinerario',
                    templateUrl: "src/modules/itinerario/itinerario.tpl.html"
                    })
            $stateProvider
                    .state('itinerario.calendario', {
                    url: '/itinerario',
                    templateUrl: "src/modules/itinerario/calendario.html"
                    })
                    .state('itinerario.timeline', {
                    url: '/itinerario',
                    templateUrl: "src/modules/itinerario/timeline.html"
                    })
            $stateProvider
                    .state('itinerario.lista', {
                    url: '/itinerario',
                    templateUrl: "src/modules/itinerario/lista.html"
                    });



        }]);
})(window.angular);