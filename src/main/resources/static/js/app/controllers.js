// pointeur sur le module en cours
//$scope = code de ma div
//a:1 = Json ou mode JavaScript
application.controller('AccountCtrl', function ($scope, accountService) {
    $scope.hello = "Hello world";
    $scope.accounts = [
        {
            id: "5",
            libelle: "test5",
            balance: "100"
        },
        {
            id: "1",
            libelle: "test",
            balance: "3000"
        }
    ];

    $scope.updateAccounts = function () {
        $scope.accounts = accountService.getAccounts();
        //alert($scope.accounts);
    };
});
