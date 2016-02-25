(function (ng) {

    var mod = ng.module("eventoModule");

    mod.controller("eventoCtrl", ["$scope", "eventoService", function ($scope, svc) {

            $scope.alerts = [];
            $scope.currentRecord = {};
            $scope.records = [];
            var self = this;

            this.deleteRecord = function (record) {
                return svc.deleteRecord(record.id).then(function () {
                    self.fetchRecords();
                }, responseError);
            };
            function responseError(response) {
                self.showError(response.data);
            }
            this.editRecord = function (record) {
                $scope.$broadcast("pre-edit", $scope.currentRecord);
                return svc.fetchRecord(record.id).then(function (response) {
                    $scope.currentRecord = response.data;
                    self.editMode = true;
                    $scope.$broadcast("post-edit", $scope.currentRecord);
                    return response;
                }, responseError);
            };
             this.saveRecord = function () {
                return svc.saveRecord($scope.currentRecord).then(function () {
                    self.fetchRecords();
                }, responseError);
            };


   }]);

})(window.angular);