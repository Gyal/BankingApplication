'use strict';
    
angular.module('BankingApp', ['BankingService']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/api/account/list', {templateUrl:'templates/api-list.html', controller:BankingListController}).
            when('/api/account/new', {templateUrl:'templates/api-new.html', controller:BankingNewController}).
            when('/api/account:id', {templateUrl:'templates/api-detail.html', controller:BankingDetailController}).
            otherwise({redirectTo:'/api/account/list'});
    }]);