/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {

    var mod = ng.module("experienciaModule");

    mod.service("experienciaService", ["$http", "experienciaContext", function ($http, context) {
            /**
             * Obtener la lista de experiencias.
             * Hace una petición GET con $http a /experiencias para obtener la lista
             * de experiencias
             * @returns {promise} promise para leer la respuesta del servidor}
             * Devuelve una lista de objetos de experiencias con sus atributos y experiencias
             */
            this.fetchRecords = function (viajeroId) {
                return $http.get(context+"/"+viajeroId+"/experiencias");
            };


            /**
             * Obtener un registro de experiencias.
             * Hace una petición GET a /experiencias/:id para obtener
             * los datos de un registro específico de experiencias
             * @param {number} id del registro a obtener
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de experiencias con sus atributos y experiencias
             */
            this.fetchRecord = function (viajeroId, viajeId) {
                return $http.get(context + "/" + viajeroId + "/experiencias/" + viajeId);
            };


            /**
             * Guardar un registro de experiencias.
             * Si currentRecord tiene la propiedad id, hace un PUT a /experiencias/:id con los
             * nuevos datos de la instancia de experiencias.
             * Si currentRecord no tiene la propiedad id, se hace un POST a /experiencias
             * para crear el nuevo registro de experiencias
             * @param {object} currentRecord instancia de book a guardar/actualizar
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de experiencias con sus datos incluyendo el id
             */
            this.saveRecord = function(idViajero,currentRecord){
                if(currentRecord.id){
                    return this.updateExperiencia(idViajero, currentRecord.id ,currentRecord);
                }else{
                    return this.createExperiencia(idViajero,currentRecord);

            }};

            /**
             * Hace una petición DELETE a /experiencias/:id para eliminar un book
             * @param {number} id identificador de la instancia de book a eliminar
             * @idViajero name
             * @returns {promise} promise para leer la respuesta del servidor
             * No devuelve datos.
             */
            this.deleteRecord = function (idViajero, id) {
                console.log("DELETE IDVIAJERO:"+idViajero + " -- ID_EXP:"+id);
                return $http.delete(context + "/" + idViajero + "/experiencias/" + id);
            };

            this.createExperiencia = function (idViajero, currentRecord) {
                return $http.post(context + "/" + idViajero + "/experiencias" , currentRecord);
            };

            this.updateExperiencia = function (idViajero , expId ,currentRecord) {
                console.log("ENTRAMOS A UPDATE CON ID:"+idViajero+ "ID EXP:"+expId);
                return $http.put(context + "/" + idViajero + "/experiencias/" + expId, currentRecord);
            };

        }]);
})(window.angular);