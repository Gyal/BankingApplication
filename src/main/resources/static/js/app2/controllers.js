(function() {
    var AccountController = function($scope, accountService){

        accountService.query(function(response) {
            $scope.accountTypes = response || [];
        });

        $scope.addAccount = function (accountDTO) {
            accountService.save({
                description: accountDTO.description
            }, function (account) {
                $scope.accountTypes.push(account);
            });
            $scope.newAccount = "";
        };
        $scope.deleteAccount = function (accountDTO) {
           accountService.remove({
                id: todoDTO.id
            }, function () {
                $scope.accountTypes = _.remove($scope.accountTypes, function (element) {
                    return element.id !== accountDto.id;
                });
            });
        };
        $scope.updateAccount = function (accountDTO) {
            accountService.update({
                id: accountDTO.id
            },{
                account: accountDTO
            });
        };
    };
    AccountController.$inject = ['$scope', 'accountService'];
    angular.module("BankingApp.controllers").controller("AccountController", AccountController);
}(angular));



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