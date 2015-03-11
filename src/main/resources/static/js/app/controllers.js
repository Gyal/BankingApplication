'use strict';

function AccountListController($scope, $location, Account) {
    $scope.accounts = Account.query();
    $scope.gotoAccountNewPage = function () {
        $location.path("/api/account/new")
    };
    $scope.deleteAccount = function (account) {
        account.$delete({'id':account.id}, function () {
            $location.path('/');
        });
    };
}
function AccountDetailController($scope, $routeParams, $location, Account) {
    $scope.account = Account.get({id:$routeParams.id}, function (account) {
    });
    $scope.gotoAccountListPage = function () {
        $location.path("/")
    };
}
function AccountNewController($scope, $location, Account) {
    $scope.submit = function () {
        Account.save($scope.account, function (account) {
            $location.path('/');
        });
    };
    $scope.gotoAccountListPage = function () {
        $location.path("/")
    };
}