
define(['angular',
    'modules/angularjs/app',
    'modules/angularjs/services',
    'modules/angularjs/controllers',
    'modules/angularjs/filters',
    'modules/angularjs/directives'], function(angular) {

    "use strict";
    return {
        bootstrap: function(moduleNames) {
            angular.element(document).ready(function () {
                angular.bootstrap(document, moduleNames);
            });
        }
    };
});
