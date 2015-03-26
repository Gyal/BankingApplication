// pointeur sur le module en cours
//$scope = code de ma div
//a:1 = Json ou mode JavaScript
application.controller('AccountCtrl', function ($scope, accountService) {
    $scope.hello = "Hello world";
    $scope.users = [
        {
            id: "5",
            libelle: "test5"
        },
        {
            id: "",
            libelle: "test"
        }
    ];
    $scope.clickHello = function () {
        //accountService.sayHello($scope.hello);
        $scope.users = accountService.getUsers();
        //$scope.users.push({name:"titi"});
        };
});
