(function (ng) {
    var mod = ng.module("eventoModule");

    mod.service("eventoService", ["$http", "eventoContext", function ($http, context) {

        /**
         * Obtener la lista de eventos.
         * Hace una petición GET con $http a /eventos para obtener la lista
         * de objetos de la entidad evento
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un array de objetos de eventos.
         */
        this.fetchRecords = function () {
            return $http.get(context);
        };

        /**
         * Obtener un registro de eventos.
         * Hace una petición GET a /eventos/:id para obtener
         * el objeto de un registro específico de eventos
         * @param {number} id del registro a obtener
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto instancia de eventos.
         */
        this.fetchRecord = function (id) {
            return $http.get(context + "/" + id);
        };

        /**
         * Guardar un registro de eventos.
         * Si currentRecord tiene la propiedad id, hace un PUT a /eventos/:id con los
         * nuevos datos de la instancia de eventos.
         * Si currentRecord no tiene la propiedad id, se hace un POST a /eventos
         * para crear el nuevo registro de eventos
         * @param {object} currentRecord instancia de eventos a guardar/actualizar
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto de eventos con su nuevo id
         */
        this.saveRecord = function (currentRecord) {
            if (currentRecord.id) {
                return $http.put(context + "/" + currentRecord.id, currentRecord);
            } else {
                return $http.post(context, currentRecord);
            }
        };

        /**
         * Hace una petición DELETE a /eventos/:id para eliminar un evento
         * @param {number} id identificador de la instancia de evento a eliminar
         * @returns {promise} promise para leer la respuesta del servidor.
         * No se recibe cuerpo en la respuesta.
         */
        this.deleteRecord = function (id) {
            return $http.delete(context + "/" + id);
        };      
    }]);
})(window.angular);
