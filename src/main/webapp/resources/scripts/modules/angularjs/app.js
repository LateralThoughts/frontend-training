
define(['angular'], function (angular) {

    "use strict";
    var app = angular.module('zuprTrackr', ['ngResource']);
    app.config(['$routeProvider', function($routeProvider) {
        $routeProvider
            .when('/activities', {
                templateUrl: '/resources/scripts/templates/angular-partials/activities.html',
                controller: 'ZuprTrackrActivities'
            })
            /*
             * TODO - add routes for :
             * /companies -> controller: ZuprTrackrCompanies ('companies.html', same path as above)
             * /employees -> controller: ZuprTrackrEmployees ('employees.html', same path as above)
             */
            .otherwise({
                redirectTo: '/activities'
            });
    }]);
    return app;
});
