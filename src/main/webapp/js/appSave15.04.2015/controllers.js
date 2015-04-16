angular.module("bankingApp.controllers")
    .controller("AccountCtrl", function ($scope, accountService) {

        accountService.query(function (response) {
            $scope.accounts = response || [];
        });

    })
    .controller('LoginCtrl', function ($scope, loginService) {
        $scope.username = "test";
        $scope.password = "test";

        $scope.login = function() {

            loginService.login($scope.username, $scope.password, function (response) {
                $scope.user = response || [];
            });
        }

    })
    .controller('UserCtrl', function ($scope, userService) {

        userService.query(function (response) {
            $scope.user = response || [];
        });
    })



