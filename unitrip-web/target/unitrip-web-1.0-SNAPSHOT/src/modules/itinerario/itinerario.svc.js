/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){
    
    var mod = ng.module("itinerarioModule");
    
     mod.service("itinerarioService", ["$http", "itinerarioContext", function ($http, context) {
        /**
         * Obtener la lista de itinerarios.
         * Hace una petición GET con $http a /itinerarios para obtener la lista
         * de itinerarios
         * @returns {promise} promise para leer la respuesta del servidor}
         * Devuelve una lista de objetos de itinerarios con sus atributos y reviews
         */
        this.fetchRecords = function () {
            return $http.get(context);
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
            return $http.get(context + "/" + id);
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
                return $http.put(context + "/" + currentRecord.id, currentRecord);
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
            return $http.delete(context + "/" + id);
        };
    }]);
    
    
})(window.angular);

