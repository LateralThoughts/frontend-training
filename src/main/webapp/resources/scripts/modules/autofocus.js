define(["jquery", "vendor/underscore"], function($, _) {

    var filterVisibleInputs = function(ancestor) {
        return $(ancestor   + " :input").filter(":visible");
    };

    return {
        /**
         * Finds the first visible input/select/textarea/button child of the given selector
         * expression and adds, if found, focus on it.
         * @param startExpression optional start element (default: body)
         */
        focus : function(startExpression) {
            var $startElement = (startExpression === undefined) ? "body" : startExpression,
                matchingInput = _.head(filterVisibleInputs($startElement));

            if (matchingInput !== undefined) {
                $(matchingInput).focus();
            }
        }
    };
});