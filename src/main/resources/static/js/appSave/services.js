// déclaration d'un service,  singleton en paramètre que les modules et angular


application.service("accountService", function ($http) {
    return {

        // définition de la fonction sayHello du service accountService
        sayHello: function (msg) {
            alert(msg);

        },


        getAccount: function () {
            return $http.get("api/account/list").then(function (response) {
                return {
                    accounts: response.data
                }
            });
        }

    };

});
