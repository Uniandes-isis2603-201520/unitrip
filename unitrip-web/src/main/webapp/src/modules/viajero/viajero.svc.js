/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {

    var mod = ng.module("viajeroModule");

    mod.service("viajeroService", ["$http", "viajeroContext", function ($http, context) {
            /**
             * Obtener la lista de viajeros.
             * Hace una petición GET con $http a /viajeros para obtener la lista
             * de viajeros
             * @returns {promise} promise para leer la respuesta del servidor}
             * Devuelve una lista de objetos de viajeros con sus atributos y reviews
             */
            this.fetchRecords = function () {
                return $http.get(context);
            };

            /**
             * Obtener un registro de viajeros.
             * Hace una petición GET a /viajeros/:id para obtener
             * los datos de un registro específico de viajes
             * @param {number} id del registro a obtener
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de viajeros con sus atributos y reviews
             */
            this.fetchRecord = function (id) {
                return $http.get(context + "/" + id);
            };

            /**
             * Guardar un registro de viajeros.
             * Si currentRecord tiene la propiedad id, hace un PUT a /viajeros/:id con los
             * nuevos datos de la instancia de viajeros.
             * Si currentRecord no tiene la propiedad id, se hace un POST a /viajes
             * para crear el nuevo registro de viajes
             * @param {object} currentRecord instancia de viajero a guardar/actualizar
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de viajes con sus datos incluyendo el id
             */
            this.saveRecord = function (currentRecord) {
                if (currentRecord.id) {
                    return $http.put(context + "/" + currentRecord.id, currentRecord);
                } else {
                    return $http.post(context, currentRecord);
                }
            };

            /**
             * Hace una petición DELETE a /viajeros/:id para eliminar un viajero
             * @param {number} id identificador de la instancia de viajero a eliminar
             * @returns {promise} promise para leer la respuesta del servidor
             * No devuelve datos.
             */
            this.deleteRecord = function (id) {
                return $http.delete(context + "/" + id);
            };

            this.getViajes = function (id) {
                return $http.get(context + "/" + id + "/viajes");
            };

            this.replaceViaje = function (viajeroId, viajes) {
                return $http.put(context + "/" + viajeroId + "/viajes", viajes);
            };

            this.removeViaje = function (viajeroId, viajeId) {
                return $http.delete(context + "/" + viajeroId + "/viajes/" + viajeId);
            };

        }]);
})(window.angular);