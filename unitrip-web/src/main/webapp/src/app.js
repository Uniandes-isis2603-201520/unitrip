(function (ng) {

    var mod = ng.module("mainApp", [
        "ui.router",
        "viajeModule",
        "itinerarioModule",
        "eventoModule",
        "paradaModule",
        "experienciaModule",
        "ngMessages"
    ]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/viaje");
            $stateProvider

                    .state('viaje', {
                        url: '/viaje',
                        controller: "viajeCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/viaje/viaje.tpl.html"
                    })
                    .state('itinerario', {
                        url: '/itinerario',
                        controller: "itinerarioCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/itinerario/itinerario.tpl.html"
                    })
                    .state('evento', {
                        url: '/evento',
                        controller: "eventoCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/evento/evento.tpl.html"
                    })
                    .state('parada', {
                        url: '/parada',
                        controller: "paradaCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/parada/parada.tpl.html"
                    })
                    .state('experiencia', {
                        url: '/experiencia',
                        controller: "experienciaCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/experiencia/experiencia.tpl.html"
                    });
        }]);
})(window.angular);