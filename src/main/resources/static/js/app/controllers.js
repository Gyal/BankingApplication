(function () {
    var AccountCtrl = function ($scope, accountService) {

        accountService.query(function (response) {
            $scope.accounts = response || [];
        });

    };

    AccountCtrl.$inject = ['$scope', 'accountService'];
    angular.module("bankingApp.controllers").controller("AccountCtrl", AccountCtrl);
}(angular));