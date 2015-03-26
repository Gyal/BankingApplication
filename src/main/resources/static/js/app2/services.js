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


