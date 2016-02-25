(function (ng) {

    var mod = ng.module("eventoModule");

    mod.service("eventoService", ["$http", "editorialContext", function ($http, context) {

             this.saveRecord = function (currentRecord) {
                if (currentRecord.id) {
                    return $http.put(context + "/" + currentRecord.id, currentRecord);
                } else {
                    return $http.post(context, currentRecord);
                }
            };
            this.deleteRecord = function (id) {
                return $http.delete(context + "/" + id);
            };
            this.fetchRecord = function (id) {
                return $http.get(context + "/" + id);
            };
            this.fetchRecords = function () {
                return $http.get(context);
            };


     }]);
})(window.angular);