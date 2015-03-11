'use strict';


var app = angular.module('BankingApp', [ 'ngRoute' ]);
angular.module('BankingApp', ['ngRessource'])
    .config(["$routeProvider", function ($routeProvider) {
    $routeProvider.
        when('/', {
            templateUrl:'templates/api-list.html',
            controller:'AccountListController'
        }).
        when('/api/account/new', {
            templateUrl:'templates/api-new.html',
            controller:'AccountNewController'
        }).
        when('/api/account:id', {
            templateUrl:'templates/api-detail.html',
            controller:'AccountDetailController'
        }).
        otherwise({
            redirectTo:'/'
        });
}]);