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
            
                    .state('experiencias', {
                    url: '/experiencias',
                    templateUrl: "src/modules/experiencias/experiencias.tpl.html"
                    })
                    .state('viajes', {
                    url: '/viajes',
                    templateUrl: "src/modules/misViajes/misViajes.tpl.html"
                    });

        }]);
})(window.angular);