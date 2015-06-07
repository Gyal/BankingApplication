angular.module('bankingApp.services', ['ngCookies'])

//*************Controleur permettant l'affichage de la liste de compte ******************************************************/
    .factory('accountService', function ($resource) {
        return $resource('/api/account/list');
    })
    /****************************************************************************************************************************/
        .factory('transferService', function ($cookieStore, $http, $location) {
        var idCustomerCookie = $cookieStore.get('JSESSIONID');

        var transfer = function (amount, from, to) {
            var req = {
                method: 'PUT',
                url: '/api/account/balance/'+idCustomerCookie+'/transfer',
                headers: {
                    'Content-Type': 'charset=UTF-8'
                },
                params: {
                    "amount": amount,
                    "from":from,
                    "toTest":to
                }
            };
            $http(req).success(function () {
                // Transfert de l'id de l'utilisateur par cookie
                alert("Votre transfert d'un montant de " + amount+ " euros , a bien été réalisé du compte "+ from+ " au compte " + to);
                $location.path('/account/' + idCustomerCookie);

            });
        };

        var deposit = function(amount,accountCredited) {


            var reqDeposit = {
                method: 'PUT',
                url: '/api/account/balance/'+idCustomerCookie+'/deposit',
                headers: {
                    'Content-Type': 'charset=UTF-8'
                },
                params: {
                    "amount": amount,
                    "accountCredited":accountCredited
                }
            };
            $http(reqDeposit).success(function () {

                // Transfert de l'id de l'utilisateur par cookie
                alert("Votre dépot sur le compte " + accountCredited +" d'un montant de " + amount + " euros a bien été effectué");
                $location.path('/account/' + idCustomerCookie);

            });
        };


        var withdraw = function(amount,accountDebited) {
            var reqWithDraw = {
                method: 'PUT',
                url: '/api/account/balance/'+idCustomerCookie+'/withdraw',
                headers: {
                    'Content-Type': 'charset=UTF-8'
                },
                params: {
                    "amount": amount,
                    "accountDebited":accountDebited
                }
            };
            $http(reqWithDraw).success(function () {
                // Transfert de l'id de l'utilisateur par cookie
                alert("Votre retrait sur le compte " + accountDebited +" d'un montant de " + amount + " euros a bien été effectué");
                $location.path('/account/' + idCustomerCookie);

            });
        };
        return {
            // si pas return de login : la requete ne se sera exécuté en GET
            transfer: transfer,
            deposit: deposit,
            withdraw : withdraw
        };
    })



//*************Controleur permettant l'affichage de la page index: avec l'id l'utilisateur récupéré du coookie **************/
    .factory('userService', function ($resource, $cookieStore) {
        var idCustomerCookie = $cookieStore.get('JSESSIONID');
        // alert(idCustomerCookie);
        var SeeUserAccount = function ($location){
            $location.path("/api/account/:id");
        }

        var See

        return {
            SeeUserAccount: SeeUserAccount
        },
            $resource('/api/account/:id',
            {id: "@id"},
            {
                query: {method: 'GET', params: {id: idCustomerCookie}}
            });
    })
    /***************************************************************************************************************************/

    /*************Controleur permettant la connexion d'un utilisateur et le stockage de son id dans un cookie :
    *l'id de l'user est renvoyé par la méthode api/connexion*********************************************************************/
    .factory('loginService', function ($http, $location, $cookieStore) {

        var login = function (username, password) {
            var req = {
                method: 'POST',
                url: '/api/connexion/',
                headers: {
                    'Content-Type': 'charset=UTF-8'
                },
                params: {
                    "connexionLogin": username,
                    "password": password
                }
            };
            $http(req).success(function (data) {

                // Transfert de l'id de l'utilisateur par cookie
                $cookieStore.put('JSESSIONID', data);
                $location.path('/account/' + data);

            });
        };

        var logOut = function () {
                $cookieStore.remove('JSESSIONID');
                $location.path('/connexion');
        };
        return {
            // si pas return de login : la requete ne se sera exécuté en GET
            login: login,
            logOut : logOut
        };

    })