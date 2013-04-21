
/* Controllers */
define(['modules/angularjs/app'], function (app) {

    "use strict";
    app.controller('ZuprTrackrActivities',
            ['$scope', 'Activity', function($scope, Activity) {
                $scope.elements = Activity.query();
            }]
        ).controller('ZuprTrackrEmployees',
            ['$scope', 'Employee', function($scope, Employee) {
                $scope.elements = Employee.query();
            }]
        ).controller('ZuprTrackrCompanies',
            ['$scope', 'Company', function($scope, Company) {
                $scope.elements = Company.query();
            }]
        );

    return app;
});