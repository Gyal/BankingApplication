'use strict';
var bankingApp = angular.module('bankingApp', [
    'accountListControllers',
    'ngRoute'
]);

bankingApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/api/accountTypes', {
                templateUrl: 'templates/api-list.html',
                controller: 'AccountListCtrl'
            }).
            when('/api/account/new', {
                templateUrl: 'templates/api-new.html',
                controller: 'AccountNewController'
            }).
            when('/api/account:id', {
                templateUrl: 'templates/api-detail.html',
                controller: 'AccountDetailController'
            }).
                otherwise({
                redirectTo: '/api'
            });
    }]);