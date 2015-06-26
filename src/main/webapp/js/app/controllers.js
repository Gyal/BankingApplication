angular.module("bankingApp.controllers", ['ngCookies'])

    .controller("AccountCtrl", function ($resource, $scope, transferService, accountService, operationService, userService, $location) {
        $scope.createAccount = function () {
            accountService.createAccount($scope.accountName, $scope.accountType, function (response) {
            });
        }
        userService.query(function (response) {
            $scope.user = response || [];
        })


        $scope.getAccountByCustomer = function (id) {
            $location.path("/account/list/:" + id);
        }

        $scope.getOperations = function (id) {

            if (typeof operations == 'undefined') {
                operationService.asyncGetAccountOperation(id).then(function (response) {
                    $scope.operations = response.data;

                })
            }
        }
    })


    .controller("accountList", function ($resource, accountList, $scope) {
        accountList.query(function (response) {
            $scope.accounts = response || [];
        });
    }
)

    // Transfer

    .controller("TransferCtrl", function ($scope, transferService, userService, accountList) {
        accountList.query(function (response) {
            $scope.accounts = response || [];
        });

        userService.query(function (response) {
            $scope.user = response || [];
        });
        $scope.deposit = function () {
            transferService.deposit($scope.amount, $scope.accountCredited, function (response) {
            });
        }

        $scope.transfer = function () {
            transferService.transfer($scope.amount, $scope.from, $scope.toTest, function (response) {
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
            loginService.logOut();
        }

    })

    .controller('UserCtrl', function ($scope, userService) {

    })

