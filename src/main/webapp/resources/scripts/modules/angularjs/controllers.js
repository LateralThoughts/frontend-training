
/* Controllers */
define(['modules/angularjs/app'], function (app) {

    "use strict";
    app.controller('ZuprTrackrMain', ['$scope', function($scope) {
        $scope.elements =  [{
            rate: 550,
            day: "2013-12-09",
            employee : {
                firstName: 'Florent',
                lastName: 'Biville',
                employer : {
                    name : 'Lateral Thoughts',
                    address : 'PARIS'
                }
            },
            client : {
                name: 'ACME',
                address : 'PARIS'
            }
        },{
            rate: 500,
            day: "2011-12-08",
            employee : {
                firstName: 'Olivier',
                lastName: 'Girardot',
                employer : {
                    name : 'Pythonistas',
                    address : 'LUXEMBOURG'
                }
            },
            client : {
                name: 'ACME Subsidiary',
                address : 'LYON'
            }
        },{
            rate: 450,
            day: "2012-12-08",
            employee : {
                firstName: 'Rey',
                lastName: 'Nicolas',
                employer : {
                    name : 'Lateral Thoughts',
                    address : 'PARIS'
                }
            },
            client : {
                name: 'HOPWORK',
                address : 'PARIS'
            }
        }];
    }]);
    return app;
});