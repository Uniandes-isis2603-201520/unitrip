(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/ perfil");
            $stateProvider
                    .state('config', {
                        url: '/config',
                        templateUrl: "src/modules/cuenta/config.tpl.html"
                    });

        }]);
})(window.angular);