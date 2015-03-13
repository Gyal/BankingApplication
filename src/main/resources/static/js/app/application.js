(function(){
    angular.module("BankingApp.controllers", []);
    angular.module("BankingApp.services", []);
    angular.module("BankingApp.", ["ngResource", "BankingApp.controllers", "BankingApp.services"]);
}(angular));


/*'use strict';
var bankingApp = angular.module('bankingApp', [
    'accountListControllers',
    'ngRoute'
]);

bankingApp.config(['$resourceProvider',
    function ($resourceProvider) {
        $resourceProvider.defaults.stripTrailingSlashes = false;
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


   */