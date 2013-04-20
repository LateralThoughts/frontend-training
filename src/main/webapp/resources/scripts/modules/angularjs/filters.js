
/* Filters */
define(['modules/angularjs/app', 'vendor/underscore'], function (app, _) {

    "use strict";
    app.filter("filterFirstName", function() {
        return function(items, value) {

            if (_.isUndefined(value)) {
                return items;
            }

            return _.filter(items, function(item) {
                /*
                 * TODO: return predicate based on employee first name
                 * equality to the passed value
                 *  - items is the collection being filtered
                 *  - item is the current iteration element
                 *  - value is the actual argument (e.g. "florent")
                 */
                return true;
            });
        };
    });

    return app;
});