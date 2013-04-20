// loader configuration
require.config({
    baseUrl: '/resources/scripts',
    paths: {
        'angular': 'vendor/angular/angular',
        'angular-resource': 'vendor/angular/angular-resource'
    },
    map: {
        '*' : {
            'text' : 'vendor/text',
            'tpl' : 'templates'
        }
    },
    shim: {
        'vendor/underscore': {
            exports: '_'
        },
        'angular': {
            exports: 'angular'
        },
        'angular-resource': {
            deps: ['angular']
        }
    },
    priority: 'angular'
});

require(['jquery'], function($) {
    $.ajaxSetup({
        accepts: {
            json:'application/json'
        }
    });
});