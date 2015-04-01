// pointeur sur le module en cours
//$scope = code de ma div
//a:1 = Json ou mode JavaScript
application.controller('AccountCtrl', function ($scope, accountService) {
    $scope.hello = "Hello world";
    $scope.account = [
        {
            id: "5",
            libelle: "test5"
        },
        {
            id: "1",
            libelle: "test"
        }
    ];

    $scope.clickHello = function () {
        $scope.account = accountService.getAccount();
        };
});
