
/* Controllers */
define(['modules/angularjs/app'], function (app) {

    "use strict";
    app.controller('ZuprTrackrActivities',
        ['$scope', 'Activity', function($scope, Activity) {
            //TODO: fetch from Activity and assign to $scope
        }]
    ).controller('ZuprTrackrEmployees',
        ['$scope', 'Employee', function($scope, Employee) {
            //TODO: fetch from Activity and assign to $scope
        }]
    ).controller('ZuprTrackrCompanies',
        ['$scope', 'Company', function($scope, Company) {
            //TODO: fetch from Activity and assign to $scope
        }]
    );

    return app;
});