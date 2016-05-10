/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("itinerarioModule");

    mod.service("itinerarioService", ["$http", "itinerarioContext", function ($http, context, $rootScope, $log) {
            /**
             * Obtener la lista de itinerarios.
             * Hace una petición GET con $http a /itinerarios para obtener la lista
             * de itinerarios
             * @returns {promise} promise para leer la respuesta del servidor}
             * Devuelve una lista de objetos de itinerarios con sus atributos y reviews
             */
            this.fetchRecords = function () {
                $log.debug("GET TODOS:"+"viajeros/"+$rootScope.viajeroActual+"/viajes/"+$rootScope.viajeActual+"/"+context);
                return $http.get("viajeros/"+$rootScope.viajeroActual+"/viajes/"+$rootScope.viajeActual+"/"+context);
            };

            /**
             * Obtener un registro de itinerarios.
             * Hace una petición GET a /itinerarios/:id para obtener
             * los datos de un registro específico de itinerarios
             * @param {number} id del registro a obtener
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de itinerarios con sus atributos y reviews
             */
            this.fetchRecord = function (id) {
                $log.debug("GET:"+"viajeros/"+$rootScope.viajeroActual+"/viajes/"+$rootScope.viajeActual+"/"+context+"/"+ id);
                return $http.get("viajeros/"+$rootScope.viajeroActual+"/viajes/"+$rootScope.viajeActual+"/"+context+"/"+ id);
            };

            /**
             * Guardar un registro de itinerarios.
             * Si currentRecord tiene la propiedad id, hace un PUT a /itinerarios/:id con los
             * nuevos datos de la instancia de itinerarios.
             * Si currentRecord no tiene la propiedad id, se hace un POST a /itinerarios
             * para crear el nuevo registro de itinerarios
             * @param {object} currentRecord instancia de book a guardar/actualizar
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de itinerarios con sus datos incluyendo el id
             */
            this.saveRecord = function (currentRecord) {
                if (currentRecord.id) {
                    $log.debug("PUT:"+"viajeros/"+$rootScope.viajeroActual+"/viajes/"+$rootScope.viajeActual+"/"+context+ "/" + currentRecord.id);
                    return $http.put("viajeros/"+$rootScope.viajeroActual+"/viajes/"+$rootScope.viajeActual+"/"+context+ "/" + currentRecord.id, currentRecord);
                } else {
                    return $http.post(context, currentRecord);
                }
            };

            /**
             * Hace una petición DELETE a /itinerarios/:id para eliminar un book
             * @param {number} id identificador de la instancia de book a eliminar
             * @returns {promise} promise para leer la respuesta del servidor
             * No devuelve datos.
             */
            this.deleteRecord = function (id) {
                $log.debug("DELETE:"+"viajeros/"+$rootScope.viajeroActual+"/viajes/"+$rootScope.viajeActual+"/"+context+ "/" + id);
                return $http.delete("viajeros/"+$rootScope.viajeroActual+"/viajes/"+$rootScope.viajeActual+"/"+context+ "/" + id);
            };
        }]);


})(window.angular);

