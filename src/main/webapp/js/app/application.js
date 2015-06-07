/**
 * Created by Mélina on 16/03/2015.
 */

// Déclaration de l'application
/**
 * Created by Mélina on 16/03/2015.
 */

// Déclaration de l'application

(function () {
    var app = angular.module('bankingApp', ["ngResource", "bankingApp.controllers", "bankingApp.services", "ngRoute"]);

    app.config(function ($routeProvider) {
        $routeProvider
            .when('/list', {templateUrl: '/template/listAccount.html'})
            .when('/connexion', {templateUrl: '/template/connexion.html'})
            .when('/account/transfer', {templateUrl: '/template/transfer.html'})
            .when('/account/deposit', {templateUrl: '/template/deposit.html'})
            .when('/account/withdraw', {templateUrl: '/template/withDraw.html'})
            .when('/newAccount', {templateUrl: '/template/createAccounts.html'})
            .when('/newCustomer', {templateUrl: '/template/createCustomer.html'})
            .when('/error404', {templateUrl: '404.html'})
            .when('/:id', {templateUrl: '/template/transfer.html'})
            .when('/account/:id', {templateUrl: '/index2.html'})
            .when('/account/list/:id', {templateUrl: '/indexAccount.html'})
            .otherwise({redirectTo: '/'});
    });
    angular.module("bankingApp.controllers", []);
    angular.module("bankingApp.services", []);

}(angular));