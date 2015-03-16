var BankingAppModule = angular.module('BankingAppModule', ['ngRessource']);

BankingAppModule.controller('BankingAppController', function ($scope, $http) {
    BankingAppModule

    app.config(function ($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/api/account/',
                templateUrl: URLS.partialsList,
                controller: 'BankingAppController'
            });

        app.controller("BankingAppController", function ($scope, Account, $state) {
            function init() {
                $scope.getAccounts();
            }


            $scope.getAccounts = function () {
                $scope.account = Account.query();
            };
        });
    })
});


/*

 (function(){
    angular.module("BankingApp.controllers", []);
    angular.module("BankingApp.services", []);
    angular.module("BankingApp.", ["ngResource", "BankingApp.controllers", "BankingApp.services"]);
}(angular));

 */
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