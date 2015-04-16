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
            .when('/transfer', {templateUrl: '/template/transfer.html'})
            .when('/newAccount', {templateUrl: '/template/createAccounts.html'})
            .when('/newCustomer', {templateUrl: '/template/createCustomer.html'})
            .when('/error404', {templateUrl: '404.html'})
            .when('/:id', {templateUrl: '/template/test.html'})
            .otherwise({redirectTo: '/'});
    });
    angular.module("bankingApp.controllers", []);
    angular.module("bankingApp.services", []);

}(angular));