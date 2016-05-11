/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {

    var mod = ng.module("viajeModule");

    mod.service("viajeService", ["$http", "viajeContext", function ($http, context, $rootScope, $log) {
            /**
             * Obtener la lista de viajes.
             * Hace una petición GET con $http a /viajes para obtener la lista
             * de viajes
             * @returns {promise} promise para leer la respuesta del servidor}
             * Devuelve una lista de objetos de viajes con sus atributos y reviews
             *
            this.fetchRecords = function () {
                $log.debug();
                return $http.get();
            };

            /**
             * Obtener un registro de viajes.
             * Hace una petición GET a /viajes/:id para obtener
             * los datos de un registro específico de viajes
             * @param {number} id del registro a obtener
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de viajes con sus atributos y reviews
             *
            this.fetchRecord = function (id) {
                $log.debug("GET" + context +"/"+$rootScope.viajeroActual + "/viajes/" + id);
                return $http.get(context +"/"+$rootScope.viajeroActual + "/viajes/" + id);
            };

            /**
             * Guardar un registro de viajes.
             * Si currentRecord tiene la propiedad id, hace un PUT a /viajes/:id con los
             * nuevos datos de la instancia de viajes.
             * Si currentRecord no tiene la propiedad id, se hace un POST a /viajes
             * para crear el nuevo registro de viajes
             * @param {object} currentRecord instancia de book a guardar/actualizar
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de viajes con sus datos incluyendo el id
             *
            this.saveRecord = function (currentRecord) {
                if (currentRecord.id) {
                    $log.debug("PUT"+context + "/" + $rootScope.viajeroActual+ "/viajes/" + currentRecord.id, currentRecord);
                    return $http.put(context + "/" + $rootScope.viajeroActual+ "/viajes/" + currentRecord.id, currentRecord);
                } else {
                    return $http.post(context + "/" + $rootScope.viajeroActual+ "/viajes/" + currentRecord.id, currentRecord);
                }
            };

            /**
             * Hace una petición DELETE a /viajes/:id para eliminar un book
             * @param {number} id identificador de la instancia de book a eliminar
             * @returns {promise} promise para leer la respuesta del servidor
             * No devuelve datos.
             *
            this.deleteRecord = function (id) {
                $log.debug(context +"/"+$rootScope.viajeroActual+ "/viajes/" + id);
                return $http.delete(context +"/"+$rootScope.viajeroActual+ "/viajes/" + id);
            };
            */
            this.getViajes = function (viajeroId) {
                return $http.get(context + "/" + viajeroId + "/viajes");
            };

            this.getViaje = function (viajeroId, currentRecord) {
                return $http.get(context + "/" + viajeroId + "/viajes/" + currentRecord.id);
            };

            this.createViaje = function (viajeroId, currentRecord) {
                return $http.post(context + "/" + viajeroId + "/viajes", currentRecord);
            };

            this.updateViaje = function (viajeroId, currentRecord, prize) {
                return $http.put(context + "/" + viajeroId + "/viajes/" + currentRecord.id, currentRecord);
            };

            this.deleteViaje = function (viajeroId, currentRecord) {
                return $http.delete(context + "/" + viajeroId + "/viajes/" + currentRecord.id);
            };

            this.saveViaje = function(viajeroId, currentRecord){
                if(currentRecord.id){
                    return this.updateViaje(viajeroId, currentRecord.id, currentRecord);
                }else{
                    return this.createViaje(viajeroId, currentRecord);
                }
            };

        }]);
})(window.angular);