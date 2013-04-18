/**
 * I'm a dummy module, but JSLint does not like me
 */
require(['jquery'], function($) {
    return {
        doSomething: function() {
            var a = 2;
            var b = 3;
            console.log(a + b);
            if (a == b) alert("OMG!");
            superNeededGlobal = "I need to be exported to the root scope";
        }
    }
});