'use strict';
function BankingListController($scope, $location, Todo) {
    $scope.accounts = Todo.query();
    $scope.gotoAccountNewPage = function () {
        $location.path("/api/account/new")
    };
    $scope.deleteAccount = function (todo) {
        todo.$delete({'id':account.id}, function () {
            $location.path('/');
        });
    };
}
function BankingDetailController($scope, $routeParams, $location, Account) {
    $scope.banking = Banking.get({id:$routeParams.id}, function (account) {
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