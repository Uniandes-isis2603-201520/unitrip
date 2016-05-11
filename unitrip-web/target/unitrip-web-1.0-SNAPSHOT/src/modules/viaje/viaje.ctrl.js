(function (ng) {

    var mod = ng.module("viajeModule");

    mod.controller("viajeCtrl", ["$scope", "viajeService","viajeroService", function ($scope, svc , $location) {



            $scope.alerts = [];
            $scope.currentRecord = {
                id: '' /*Tipo Long*/,
                idViajero: '',
                name: '' /*Tipo String*/,
                itinerarios: [] /*Colección de registros de Itinerarios*/,
                experiencias: [] /*Colección de registros de Experiencias*/
            };
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

            $scope.goItinerarios = function () {
                $location.path(src / modules / itinerario / itinerario.tpl.html);
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


            $scope.$on("post-edit", onEdit);

            function onEdit(event, args) {
                $scope.refId = args.id;
                if (args.id) {
                    $scope.records = [];
                    svc.getViajes(args.id).then(function (response) {
                        $scope.records = response.data;
                    }, responseError);
                }
            }

            /*
             * Funcion createRecord emite un evento a los $scope hijos del controlador por medio de la
             * sentencia &broadcast ("nombre del evento", record), esto con el fin cargar la información de modulos hijos
             * al actual modulo.
             * Habilita el modo de edicion. El template de la lista cambia por el formulario.
             *
             */
            this.createRecord = function () {
                this.editMode = true;
                $scope.currentRecord = {};
            };

            /*
             * Funcion editRecord emite el evento ("pre-edit") a los $Scope hijos del controlador por medio de la
             * sentencia &broadcast ("nombre del evento", record), esto con el fin cargar la información de modulos hijos
             * al actual modulo.
             * Habilita el modo de edicion.  Carga el template de formulario con los datos del record a editar.
             *
             */

            this.editRecord = function (record) {
                return svc.fecthRecord($scope.refId, record.id).then(function (response) {
                    $scope.currentRecord = response.data;
                    self.editMode = true;
                    return response;
                }, responseError);
            };

            /*
             * Funcion fetchRecords consulta el servicio svc.fetchRecords con el fin de consultar
             * todos los registros del modulo viajero.
             * Guarda los registros en la variable $scope.records
             * Muestra el template de la lista de records.
             */
            this.fetchRecords = function () {
                return svc.fetchRecords($scope.refId).then(function (response) {
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
                console.log("ID VIAJERO: "+$scope.refId);
                return svc.saveRecord($scope.refId, $scope.currentRecord).then(function () {
                    self.fetchRecords();
                }, responseError);
            };

            /*
             * Funcion deleteRecord hace un llamado al servicio svc.deleteRecord con el fin
             * de eliminar el registro asociado.
             * Muestra el template de la lista de records al finalizar el borrado del registro.
             */
            this.deleteRecord = function (record) {
                return svc.deleteRecord($scope.refId, record.id).then(function () {
                    self.fetchRecords();
                }, responseError);
            };

        }]);

})(window.angular);