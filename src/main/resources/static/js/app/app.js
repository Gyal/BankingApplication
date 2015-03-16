/**
 * Created by Mélina on 16/03/2015.
 */

// Super Tuto pour angular : http://www.angular-js.fr/decouvrez-angularjs/

var app = angular.module('myApp', []);

var AccountCtrl = function ($scope) {
    $scope.profil = "L'id de l'user en Angular"
    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'templates/index.html'
            })
    }]);
}