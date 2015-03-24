/**
 * Created by Mélina on 16/03/2015.
 */

// Super Tuto pour angular : http://www.angular-js.fr/decouvrez-angularjs/


// Déclaration de l'application
var app = angular.module('bankingApp', [
    'accountList'
]);

// Déclaration du module todule accountList
var accountList = angular.module('accountList', []);

// Contrôleur de l'application "AccountList"
accountList.controller('AccountCtrl', ['$scope',
    function ($scope) {

        $scope.accounts = [],
            $scope.profil = "L'id de l'user en Angular"
    }]);


// Ajout d'un compte

/*
    $scope.profil = "L'id de l'user en Angular"
    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'templates/index.html'
            })
    }]);
 }*/