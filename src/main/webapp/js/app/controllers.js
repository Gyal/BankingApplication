angular.module("bankingApp.controllers", ['ngCookies'])

    .controller("AccountCtrl", function ($scope, accountService) {
        accountService.query(function (response) {
            $scope.accounts = response || [];
        });
        $scope.melina = function () {
         alert("Hello");
            accountService.transfer = function(response){
                    $scope.test = response || [];
                }       };
    }
)


    // Transfer

    .controller("TransferCtrl", function ($scope, transferService) {
        alert($scope.from);
        //$scope.amount=5;
        //$scope.from ="222";
        $scope.transfer = function (){

            transferService.transfer($scope.amount, $scope.from, $scope.toTest,function (response) {

               // $scope.test = response || [];
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

    })

    .controller('UserCtrl', function ($scope, userService, $location) {
        $scope.SeeUserAccount = function (id) {
            $location.path("/account/list/:" + id);
            //alert("test");
            //userService.SeeUserAccount();
        }

        userService.query(function (response) {

            $scope.user = response || [];
            //ajout d'un cookie
            // $window.sessionStorage.token = response.token;


        });
    })
