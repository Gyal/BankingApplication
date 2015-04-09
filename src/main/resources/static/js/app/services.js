(function (angular) {
    var AccountFactory = function ($resource) {
        return $resource('/api/account/list', {
            //  id: 'account-id'
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
    angular.module('bankingApp.services').factory('accountService', AccountFactory);
}(angular));