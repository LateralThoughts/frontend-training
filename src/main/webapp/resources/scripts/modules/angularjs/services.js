
/* Services */
define(['modules/angularjs/app'], function (app) {

    "use strict";

    app.factory('Company', ['$resource', function($resource) {
            return $resource('/companies/:id', {}, {
                query: {
                    method: 'GET',
                    params: {id: ''},
                    isArray: true
                }
            });
        }])
        .factory('Employee', ['$resource', function($resource) {
            return $resource('/employees/:id', {}, {
                query: {
                    method: 'GET',
                    params: {id: ''},
                    isArray: true
                }
            });
        }])
        .factory('Activity', ['$resource', function($resource) {
            return $resource('/activities/:id', {}, {
                query: {
                    method: 'GET',
                    params: {id: ''},
                    isArray: true
                }
            });
        }]);
    return app;
});
