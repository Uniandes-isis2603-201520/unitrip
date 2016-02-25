// src/modules/viajes/viajes.mock.js
// "Mock" del servicio. Simula el funcionamiento del servicio 
// durante la ejecución de las pruebas

(function (ng) {

    var mod = ng.module('viajesMock', ['ngMockE2E']);


    mod.run(['$httpBackend', function ($httpBackend) {
            var ignore_regexp = new RegExp('^((?!api).)*$');
            /*
             * @type RegExp
             * recordUrl acepta cualquier url con el formato 
             * api/(cualquierpalabra)/(numero)
             * ej: api/persons/1
             */
            var recordUrl = new RegExp('api/viajes/([0-9]+)');

            /*
             * @type Array
             * records: Array con un libro por defecto
             */
            var records = [{
                    id: 48454,
                    name: 'viaje1',
                    initialDate: '10-20-2016',
                    finalDate: '10-20-2016'
                }];

            function getQueryParams(url) {
                var vars = {}, hash;
                var hashes = url.slice(url.indexOf('?') + 1).split('&');
                for (var i = 0; i < hashes.length; i++) {
                    hash = hashes[i].split('=');
                    vars[hash[0]] = hash[1];
                }
                return vars;
            }

            /*
             * Ignora las peticiones GET, no contempladas en la Exp regular ignore_regexp
             */
            $httpBackend.whenGET(ignore_regexp).passThrough();

            /*
             * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/persons"
             * Obtiene los parámetros de consulta "queryParams" para establecer 
             * la pagina y la maxima cantidad de records. Con los anteriores parametros 
             * se realiza la simulacion de la paginacion.
             * Response: 200 -> Status ok, array de libros y los headers.
             */
            $httpBackend.whenGET('api/viajes').respond(function (method, url) {
                var queryParams = getQueryParams(url);
                var responseObj = [];
                var page = queryParams.page;
                var maxRecords = queryParams.maxRecords;
                var headers = {};
                if (page && maxRecords) {
                    var start_index = (page - 1) * maxRecords;
                    var end_index = start_index + maxRecords;
                    responseObj = records.slice(start_index, end_index);
                    headers = {"X-Total-Count": records.length};
                } else {
                    responseObj = records;
                }
                return [200, responseObj, headers];
            });
            /*
             * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/persons/[numero]"
             * Obtiene el id de la url y el registro asociado dentro del array records.
             * Response: 200 -> Status ok, record -> libro y ningún header.
             */
            $httpBackend.whenGET(recordUrl).respond(function (method, url) {
                var id = parseInt(url.split('/').pop());
                var record;
                ng.forEach(records, function (value) {
                    if (value.id === id) {
                        record = ng.copy(value);
                    }
                });
                return [200, record, {}];
            });
            /*
             * Esta funcion se ejecuta al invocar una solicitud POST a la url "api/persons"
             * Obtiene el record de libro desde el cuerpo de la peticion
             * Genera un id aleatorio y lo asocia al record de libro y lo guarda en el 
             * array de records.
             * Response: 201 -> Status created, record -> libro y ningún header.
             */
            $httpBackend.whenPOST('api/viajes').respond(function (method, url, data) {
                var record = ng.fromJson(data);
                record.id = Math.floor(Math.random() * 10000);
                records.push(record);
                return [201, record, {}];
            });

            /*
             * Esta funcion se ejecuta al invocar una solicitud DELETE a la url "api/persons/[numero]"
             * Obtiene el id del la url y el registro asociado dentro del array records.
             * Luego realiza un splice "eliminar registro del array".
             * Response: 204, no retorna ningun dato ni headers.
             */

            $httpBackend.whenDELETE(recordUrl).respond(function (method, url) {
                var id = parseInt(url.split('/').pop());
                ng.forEach(records, function (value, key) {
                    if (value.id === id) {
                        records.splice(key, 1);
                    }
                });
                return [204, null, {}];
            });

            /*
             * Esta funcion se ejecuta al invocar una solicitud PUT a la url "api/persons/[numero]"
             * Obtiene el id del la url y el record de libro desde el cuerpo de la peticion
             * Busca y reemplaza el anterior registro por el enviado en el cuerpo de la solicitud
             * Response: 204, no retorna ningun dato ni headers. 
             * 
             */
            $httpBackend.whenPUT(recordUrl).respond(function (method, url, data) {
                var id = parseInt(url.split('/').pop());
                var record = ng.fromJson(data);
                ng.forEach(records, function (value, key) {
                    if (value.id === id) {
                        records.splice(key, 1, record);
                    }
                });
                return [204, null, {}];
            });
        }]);
})(window.angular);