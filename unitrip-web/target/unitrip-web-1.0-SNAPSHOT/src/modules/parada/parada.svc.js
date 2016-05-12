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
            this.fetchRecords = function (viajeroId,viajeId,itiId) {
                console.log("GET TODOS:"+context + "/" + viajeroId + "/viajes/"+viajeId+"/itinerarios/"+itiId+"/paradas");
                return $http.get("viajeros/"+context + "/" + viajeroId + "/viajes/"+viajeId+"/itinerarios/"+itiId+"/paradas")
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
            this.saveRecord = function (viajeroId, viajeId, itiId, currentRecord) {
                if(currentRecord.id){
                    console.log("Viajero ID SAVE : "+ viajeroId);
                    console.log("Viaje ID SAVE : "+viajeId);
                     console.log("Iti ID SAVE : "+itiId);
                    return this.updateItinerario(viajeroId, viajeId, itiId ,currentRecord.id ,currentRecord);
                }else{
                    return this.createItinerario(viajeroId, viajeId, itiId,currentRecord);
                }
            };

            this.createParada = function (viajeroId, viajeId, itiId ,currentRecord) {
                console.log("ENTRAMOS CREATE: "+context + "/" + viajeroId + "/viajes/" + viajeId +"/itinerarios");
                return $http.post(context + "/" + viajeroId + "/viajes/" + viajeId +"/itinerarios/"+itiId, currentRecord);
            };

            this.updateParada = function (viajeroId, viajeId, itinerarioId, paradaId ,currentRecord) {
                console.log("ENTRAMOS UPDATE: "+context + "/" + viajeroId + "/viajes/" + viajeId+"/itinerarios/"+itinerarioId);
                return $http.put(context + "/" + viajeroId + "/viajes/" + viajeId+"/itinerarios/"+itinerarioId+"/paradas/"+paradaId, currentRecord);
            };




        }]);
})(window.angular);
