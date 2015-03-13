(function(angular) {
    var AccountFactory = function ($resource) {
        return $resource('/api/accountType/{id}',{
            id: '@id'
        }, {
            update: {
                method: 'PUT'
            },
            remove: {
                method: 'DELETE'
            }
        });
    };
    AccountFactory.$inject = ['$resource'];
    angular.module('BankingApp.services').factory('accountService', AccountFactory);
}(angular));



/*use strict';

var app = angular.module('app',['ngResource','ngRoute']);


app.factory('Account', [function ($resource) {
    return $resource('api/account/:accountId', {}, {
        update: {method:'PUT'}
    });
}]);


*/

/*angular.module('accountService', ['ngResource']).
    factory('Account', function ($resource) {
        return $resource('rest/account/:id', {}, {
            'save': {method:'PUT'}
        });
    });*/