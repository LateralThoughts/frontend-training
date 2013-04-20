
/* Filters */
define(['modules/angularjs/app', 'vendor/underscore'], function (app, _) {

    "use strict";
    app.filter("filterFirstName", function() {
        return function(items, value) {

            if (_.isUndefined(value)) {
                return items;
            }

            return _.filter(items, function(item) {
                return  !_.isUndefined(item.employee) &&
                    !_.isUndefined(item.employee.firstName) &&
                    item.employee.firstName.toLocaleLowerCase().indexOf(value.toLocaleLowerCase()) >= 0;
            });
        };
    });

    return app;
});