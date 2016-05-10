(function (ng) {

    var mod = ng.module("viajeroModule");

    mod.controller("viajeroCtrl", ["$scope", "viajeroService", function ($scope, svc, $rootScope) {



            $scope.alerts = [];
            $scope.currentRecord = {
                id: '' /*Tipo Long*/,
                name: '' /*Tipo String*/,
                experiencias: [] /*Colección de registros de Experiencias*/,
                usuario: ''/*Tipo String*/,
                edad: '' /*Tipo /*Tipo Long*/,
                password: ''/*Tipo String*/,
                mail: ''/*Tipo String*/,
                viajes: [] /*Colección de registros de Viajes*/


            };

            $rootScope.viajeroActual='';
            $scope.records = [];

            $scope.today = function () {
                $scope.value = new Date();
            };

            $scope.clear = function () {
                $scope.value = null;
            };

            $scope.open = function ($event) {
                $event.preventDefault();
                $event.stopPropagation();

                $scope.opened = true;
            };

            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };

            this.showSuccess = function (msg) {
                showMessage(msg, "success");
            };

            var self = this;
            function responseError(response) {
                self.showError(response.data);
            }
            //Variables para el controlador
            this.readOnly = false;
            this.editMode = false;


            this.changeTab = function (tab) {
                $scope.tab = tab;
            };

            //Ejemplo alerta
            showMessage("Bienvenido!", "success");


            /*
             * Funcion createRecord emite un evento a los $scope hijos del controlador por medio de la
             * sentencia &broadcast ("nombre del evento", record), esto con el fin cargar la información de modulos hijos
             * al actual modulo.
             * Habilita el modo de edicion. El template de la lista cambia por el formulario.
             *
             */

            this.createRecord = function () {
                $scope.$broadcast("pre-create", $scope.currentRecord);
                this.editMode = true;
                $scope.currentRecord = {};
                $scope.$broadcast("post-create", $scope.currentRecord);
            };

            /*
             * Funcion editRecord emite el evento ("pre-edit") a los $Scope hijos del controlador por medio de la
             * sentencia &broadcast ("nombre del evento", record), esto con el fin cargar la información de modulos hijos
             * al actual modulo.
             * Habilita el modo de edicion.  Carga el template de formulario con los datos del record a editar.
             *
             */

            this.editRecord = function (record) {
                $scope.$broadcast("pre-edit", $scope.currentRecord);
                return svc.fetchRecord(record.id).then(function (response) {
                    $scope.currentRecord = response.data;
                    self.editMode = true;
                    $scope.$broadcast("post-edit", $scope.currentRecord);
                    return response;
                }, responseError);
            };

            /*
             * Funcion fetchRecords consulta el servicio svc.fetchRecords con el fin de consultar
             * todos los registros del modulo viaje.
             * Guarda los registros en la variable $scope.records
             * Muestra el template de la lista de records.
             */

            this.fetchRecords = function () {
                return svc.fetchRecords().then(function (response) {
                    $scope.records = response.data;
                    $scope.currentRecord = {};
                    self.editMode = false;
                    return response;
                }, responseError);
            };

            /*
             * Funcion saveRecord hace un llamado al servicio svc.saveRecord con el fin de
             * persistirlo en base de datos.
             * Muestra el template de la lista de records al finalizar la operación saveRecord
             */
            this.saveRecord = function () {
                return svc.saveRecord($scope.currentRecord).then(function () {
                    self.fetchRecords();
                }, responseError);
            };

            /*
             * Funcion deleteRecord hace un llamado al servicio svc.deleteRecord con el fin
             * de eliminar el registro asociado.
             * Muestra el template de la lista de records al finalizar el borrado del registro.
             */
            this.deleteRecord = function (record) {
                return svc.deleteRecord(record.id).then(function () {
                    self.fetchRecords();
                }, responseError);
            };



            /*
             * Funcion fetchRecords consulta todos los registros del módulo viaje en base de datos
             * para desplegarlo en el template de la lista.
             */
            this.fetchRecords();






        }]);

})(window.angular);