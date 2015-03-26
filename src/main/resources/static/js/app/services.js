// déclaration d'un service,  singleton en paramètre que les modules et angular
application.service("accountService", function ($http) {
    return {

        // définition de la fonction sayHello du service accountService
        sayHello: function (msg) {
            alert(msg);

        },


        getUsers: function () {
            var promise = $http.get("api/account/").then(function (response) {
                alert(response.data[0]);
                return response.data[0];
            });
            return promise;
        }

    };

});

//sayHello("melina");