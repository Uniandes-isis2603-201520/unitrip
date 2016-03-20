(function (ng) {
    var mod = ng.module("paradaModule");

    mod.service("paradaService", ["$http", "paradaContext", function ($http, context) {

        /**
         * Obtener la lista de paradas.
         * Hace una petición GET con $http a /paradas para obtener la lista
         * de objetos de la entidad parada
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un array de objetos de paradas.
         */
        this.fetchRecords = function () {
            return $http.get(context);
        };

        /**
         * Obtener un registro de paradas.
         * Hace una petición GET a /paradas/:id para obtener
         * el objeto de un registro específico de paradas
         * @param {number} id del registro a obtener
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto instancia de paradas.
         */
        this.fetchRecord = function (id) {
            return $http.get(context + "/" + id);
        };

        /**
         * Guardar un registro de paradas.
         * Si currentRecord tiene la propiedad id, hace un PUT a /paradas/:id con los
         * nuevos datos de la instancia de paradas.
         * Si currentRecord no tiene la propiedad id, se hace un POST a /paradas
         * para crear el nuevo registro de paradas
         * @param {object} currentRecord instancia de paradas a guardar/actualizar
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto de paradas con su nuevo id
         */
        this.saveRecord = function (currentRecord) {
            if (currentRecord.id) {
                return $http.put(context + "/" + currentRecord.id, currentRecord);
            } else {
                return $http.post(context, currentRecord);
            }
        };

        /**
         * Hace una petición DELETE a /paradas/:id para eliminar un parada
         * @param {number} id identificador de la instancia de parada a eliminar
         * @returns {promise} promise para leer la respuesta del servidor.
         * No se recibe cuerpo en la respuesta.
         */
        this.deleteRecord = function (id) {
            return $http.delete(context + "/" + id);
        };      
    }]);
})(window.angular);
