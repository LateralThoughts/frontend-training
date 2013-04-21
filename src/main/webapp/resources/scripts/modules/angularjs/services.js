
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
    /* TODO: add 2 other services: Employee and Activity */;
    return app;
});
