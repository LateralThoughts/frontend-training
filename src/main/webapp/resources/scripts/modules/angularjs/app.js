
define(['angular'], function (angular) {

    "use strict";
    var app = angular.module('zuprTrackr', ['ngResource']);
    app.config(['$routeProvider', function($routeProvider) {
        $routeProvider
            .when('/activities', {
                templateUrl: '/resources/scripts/templates/angular-partials/activities.html',
                controller: 'ZuprTrackrActivities'
            })
            .when('/companies', {
                templateUrl: '/resources/scripts/templates/angular-partials/companies.html',
                controller: 'ZuprTrackrCompanies'
            })
            .when('/employees', {
                templateUrl: '/resources/scripts/templates/angular-partials/employees.html',
                controller: 'ZuprTrackrEmployees'
            })
            .otherwise({
                redirectTo: '/activities'
            });
    }]);
    return app;
});
