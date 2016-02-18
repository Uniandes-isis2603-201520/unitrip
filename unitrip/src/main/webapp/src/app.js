(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);

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

                    .state('config', {
                        url: '/config',
                        templateUrl: "src/modules/cuenta/config.tpl.html"
                    })
                    .state('evento', {
                    url: '/evento',
                    templateUrl: "src/modules/evento/evento.tpl.html"
                    })
                    .state('experiencias', {
                    url: '/experiencias',
                    templateUrl: "src/modules/experiencias/exp.tpl.html"
                    })
                    .state('viajes', {
                    url: '/viajes',
                    templateUrl: "src/modules/viajes/viajes.tpl.html"
                    })
                    .state('itinerario', {
                    url: '/itinerario',
                    templateUrl: "src/modules/itinerario/itinerario.tpl.html"
                    });



        }]);
})(window.angular);