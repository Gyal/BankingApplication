/**
 * Created by Mélina on 16/03/2015.
 */

// Déclaration de l'application
(function () {
    angular.module("bankingApp.controllers", []);
    angular.module("bankingApp.services", []);
    angular.module("bankingApp", ["ngResource", "bankingApp.controllers", "bankingApp.services"]);
}(angular));