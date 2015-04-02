// déclaration d'un service,  singleton en paramètre que les modules et angular


application.service("accountService", function ($http) {
    return {

        // définition de la fonction sayHello du service accountService
        sayHello: function (msg) {
            alert(msg);

        },


        getAccounts: function () {
            $http.get("api/account/list").success(function (data) {
                accounts: data;

            });
            return data;

        }

    };

});
