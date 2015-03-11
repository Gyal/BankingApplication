'use strict';

var app = angular.module('app',['ngResource','ngRoute']);


app.factory('Account', [function ($resource) {
    return $resource('api/account/:accountId', {}, {
        update: {method:'PUT'}
    });
}]);




/*angular.module('accountService', ['ngResource']).
    factory('Account', function ($resource) {
        return $resource('rest/account/:id', {}, {
            'save': {method:'PUT'}
        });
    });*/