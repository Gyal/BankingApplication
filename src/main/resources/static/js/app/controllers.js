'use strict';

var accountListControllers = angular.module('accountListControllers', []);
accountListControllers.controller('AccountListCtrl' , ['$scope', '$http',
    function($scope, $http) {
        $http.get('/api/accountTypes.json').success(function (data) {
            $scope.phones = 'title';
        });
        $scope.orderProp = 'celling';
    }]);
accountListControllers.controller('AccountListCtrl', ['$scope', '$routeParams',
    function($scope, $routeParams) {
        $scope.title = $routeParams.title;
    }
]);



/*
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
}*/