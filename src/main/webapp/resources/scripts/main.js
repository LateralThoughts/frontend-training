// loader configuration
require.config({
    baseUrl: '/resources/scripts',
    map: {
        '*' : {
            'text' : 'vendor/text',
            'tpl' : 'templates'
        }
    },
    shim: {
        'vendor/underscore': {
            exports: '_'
        }
    }
});

require(['jquery'], function($) {
    $.ajaxSetup({
        accepts: {
            json:'application/json'
        }
    });
});