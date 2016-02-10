(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/viajes");
            $stateProvider
                    .state('config', {
                        url: '/config',
                        templateUrl: "src/modules/cuenta/config.tpl.html"
                    })
                    .state('viajes', {
                    url: '/viajes',
                    templateUrl: "src/modules/misViajes/misViajes.tpl.html"
                    });

        }]);
})(window.angular);