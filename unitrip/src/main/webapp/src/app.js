(function (ng) {

    var mod = ng.module("mainApp", [
        "ui.router",
        "itinerarioModule",
        "viajeModule",
        "eventoModule",
        "experienciaModule",
        "itinerarioMock",
        "viajeMock",
        "eventoMock",
        "experienciaMock",
        "ngMessages"
    ]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/itinerario");
            $stateProvider
                    .state('itinerario', {
                        url: '/itinerario',
                        controller: "itinerarioCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/itinerario/itinerario.tpl.html"
                    })
                    .state('viaje', {
                        url: '/viaje',
                        controller: "viajeCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/viaje/viaje.tpl.html"
                    })
                    .state('evento', {
                        url: '/evento',
                        controller: "eventoCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/evento/evento.tpl.html"
                    })
                    .state('experiencia', {
                        url: '/experiencia',
                        controller: "experienciaCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "src/modules/experiencia/experiencia.tpl.html"
                    });
        }]);
})(window.angular);