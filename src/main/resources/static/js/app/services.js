'use strict';
angular.module('AccountService', ['ngResource']).
    factory('Account', function ($resource) {
        return $resource('rest/account/:id', {}, {
            'save': {method:'PUT'}
        });
    });