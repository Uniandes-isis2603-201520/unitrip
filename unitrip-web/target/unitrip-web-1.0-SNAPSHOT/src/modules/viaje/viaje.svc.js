/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {

    var mod = ng.module("viajeModule");

    mod.service("viajeService", ["$http", "viajeContext", function ($http, context) {
            /**
             * Obtener la lista de viajes.
             * Hace una petición GET con $http a /viajes para obtener la lista
             * de viajes
             * @returns {promise} promise para leer la respuesta del servidor}
             * Devuelve una lista de objetos de viajes con sus atributos y reviews
             */
            this.fetchRecords = function () {
                return $http.get(context);
            };

            /**
             * Obtener un registro de viajes.
             * Hace una petición GET a /viajes/:id para obtener
             * los datos de un registro específico de viajes
             * @param {number} id del registro a obtener
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de viajes con sus atributos y reviews
             */
            this.fetchRecord = function (id) {
                return $http.get(context + "/" + id);
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
             */
            this.saveRecord = function (currentRecord) {
                if (currentRecord.id) {
                    return $http.put(context + "/" + currentRecord.id, currentRecord);
                } else {
                    return $http.post(context, currentRecord);
                }
            };

            /**
             * Hace una petición DELETE a /viajes/:id para eliminar un book
             * @param {number} id identificador de la instancia de book a eliminar
             * @returns {promise} promise para leer la respuesta del servidor
             * No devuelve datos.
             */
            this.deleteRecord = function (id) {
                return $http.delete(context + "/" + id);
            };
        }]);
})(window.angular);