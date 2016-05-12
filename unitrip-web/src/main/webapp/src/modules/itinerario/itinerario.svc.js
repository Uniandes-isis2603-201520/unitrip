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
             * @param viajeroId
             * @param viajeId
             * @returns {promise} promise para leer la respuesta del servidor}
             * Devuelve una lista de objetos de itinerarios con sus atributos y reviews
             */
            this.fetchRecords = function (viajeroId,viajeId) {
                $log.debug("GET TODOS:"+context + "/" + viajeroId + "/viajes/"+viajeId+"/itinerarios");
                return $http.get("viajeros/"+context + "/" + viajeroId + "/viajes/"+viajeId+"/itinerarios");
            };

            /**
             * Obtener un registro de itinerarios.
             * Hace una petición GET a /itinerarios/:id para obtener
             * los datos de un registro específico de itinerarios
             * @param {number} id del registro a obtener
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de itinerarios con sus atributos y reviews
             */
            this.fetchRecord = function (viajeroId, viajeId,itiId) {
                console.log("GET : "+context + "/" + viajeroId + "/viajes/" + viajeId + "/itinerarios/" + itiId);
                return $http.get(context + "/" + viajeroId + "/viajes/" + viajeId + "/itinerarios/" + itiId);
            };

            /**
             * Guardar un registro de itinerarios.
             * Si currentRecord tiene la propiedad id, hace un PUT a /itinerarios/:id con los
             * nuevos datos de la instancia de itinerarios.
             * Si currentRecord no tiene la propiedad id, se hace un POST a /itinerarios
             * para crear el nuevo registro de itinerarios
             * @param {object} currentRecord instancia de book a guardar/actualizar
             * @param viajeroId
             * @param viajeId
             * @param currentRecord
             * @returns {promise} promise para leer la respuesta del servidor
             * Devuelve un objeto de itinerarios con sus datos incluyendo el id
             */
            this.saveRecord = function (viajeroId, viajeId, currentRecord) {
                if(currentRecord.id){
                    console.log("Viajero ID SAVE : "+ viajeroId);
                    console.log("Viaje ID SAVE : "+viajeId);
                    return this.updateItinerario(viajeroId, viajeId, currentRecord.id ,currentRecord);
                }else{
                    return this.createItinerario(viajeroId, viajeId, currentRecord);
                }
            };

            this.createItinerario = function (viajeroId, viajeId ,currentRecord) {
                console.log("ENTRAMOS CREATE: "+context + "/" + viajeroId + "/viajes/" + viajeId +"/itinerarios");
                return $http.post(context + "/" + viajeroId + "/viajes/" + viajeId +"/itinerarios", currentRecord);
            };

            this.updateItinerario = function (viajeroId, viajeId, itinerarioId ,currentRecord) {
                console.log("ENTRAMOS UPDATE: "+context + "/" + viajeroId + "/viajes/" + viajeId+"/itinerarios/"+itinerarioId);
                return $http.put(context + "/" + viajeroId + "/viajes/" + viajeId+"/itinerarios/"+itinerarioId, currentRecord);
            };

            /**
             * Hace una petición DELETE a /itinerarios/:id para eliminar un book
             * @param viajeroId
             * @param viajeId
             * @param itiId
             * @returns {promise} promise para leer la respuesta del servidor
             * No devuelve datos.
             */
            this.deleteRecord = function (viajeroId,viajeId,itiId) {
                $log.debug("DELETE:"+context + "/" + viajeroId + "/viajes/" + viajeId + "/itinerarios/" + itiId);
                return $http.delete(context + "/" + viajeroId + "/viajes/" + viajeId + "/itinerarios/" + itiId);
            };
        }]);


})(window.angular);

