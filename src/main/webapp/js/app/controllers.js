angular.module("bankingApp.controllers", ['ngCookies'])

    .controller("AccountCtrl", function ($scope, accountService, operationService) {
        accountService.query(function (response) {
            $scope.accounts = response || [];
        });
        $scope.melina = function () {
            alert("Hello");
        };

        $scope.getOperations = function (id) {
            operationService.asyncGetAccountOperation(id).then(function (response) {
                $scope.operations = response.data;
            })
        }
    }

)

    // Transfer

    .controller("TransferCtrl", function ($scope, transferService, userService) {
        //var vm = this;
        userService.query(function (response) {
            $scope.user = response || [];
           // Ajout de données fictives $scope.user.accounts.unshift({id:2, libelle:"test"});
            /* $scope.from=$scope.user.accounts[0];
            $scope.toTest=$scope.user.accounts[0];
            $scope.accountCredited=$scope.user.accounts[0];
            $scope.accountDebited=$scope.user.accounts[0];*/

        });

        $scope.transfer = function () {
            transferService.transfer($scope.amount, $scope.from, $scope.toTest, function (response) {
            });
        }
        $scope.deposit = function () {
          transferService.deposit($scope.amount, $scope.accountCredited, function (response) {
            });
        }
        $scope.withdraw = function () {
            transferService.withdraw($scope.amount, $scope.accountDebited, function (response) {
            });
        }
    }
)

    .controller('LoginCtrl', function ($scope, loginService) {
        $scope.username = "test";
        $scope.password = "test";

        $scope.login = function () {
            loginService.login($scope.username, $scope.password, function (response) {

                $scope.user = response || [];

            });
        }
        $scope.logOut = function () {
            alert("test");
            loginService.logOut();
        }

    })

    .controller('UserCtrl', function ($scope, userService, $location) {
        $scope.SeeUserAccount = function (id) {
            $location.path("/account/list/:" + id);
        }

        userService.query(function (response) {
            $scope.user = response || [];


        });
    })
