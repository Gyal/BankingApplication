'use strict';

angular.module('accountService', ['ngResource']).
    factory('Account', function ($resource) {
        return $resource('rest/account/:id', {}, {
            'save': {method:'PUT'}
        });
    });