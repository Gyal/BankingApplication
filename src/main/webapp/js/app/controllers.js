angular.module("bankingApp.controllers")

    .controller("AccountCtrl", function ($scope, accountService) {

        accountService.query(function (response) {
            $scope.accounts = response || [];
        });

    })

    .controller('LoginCtrl', function ($scope, loginService) {
        $scope.username = "test";
        $scope.password = "test";

        $scope.login = function () {
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


/*
 loginService.save(function (response) {

 $scope.user = response || [];
 });
 $scope.user = "test";
 document.location.href = "index2.html";
 */
/*$scope.go = function() {
 document.location.href="index2.html";
 };*/



/*
 AccountCtrl.$inject = ['$scope', 'accountService'];
 angular.module("bankingApp.controllers").controller("AccountCtrl", AccountCtrl);
 UserCtrl.$inject = ['$scope', 'userService'];
 angular.module("bankingApp.controllers").controller("UserCtrl", UserCtrl);
 LoginCtrl.$inject = ['$scope', 'loginService'];
 angular.module("bankingApp.controllers").controller("LoginCtrl", LoginCtrl);

 }(angular));*/