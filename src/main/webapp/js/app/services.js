angular.module('bankingApp.services', ['ngCookies'])

//*************Controleur permettant l'affichage de la liste de compte ******************************************************/
  // Séparation de accountList et de accountService car impossible de retourner ne fonction + une ressource
    .factory('accountList', function ($resource) {
        return $resource('/api/account/list');
    })
    //
    .factory('accountService', function ($cookieStore, $http ) {
        var idCustomerCookie = $cookieStore.get('JSESSIONID');

        var createAccount = function (accountName, accountType) {
            $http({

                url: '/api/account/' + idCustomerCookie,
                method: 'POST',
                headers: {
                    'Content-Type': 'charset=UTF-8'
                },
                params: {
                    "accountName": accountName,
                    "accountType": accountType
                }

            })
                .then(function () {
                    alert("L'opération est un succès."); //Success
                },
                function () {
                    alert("Une erreur s'est produite."); //Failed
                });
          }

        return {

            createAccount: createAccount
        };




    })


//*************Controleur gestion des operation sur les comptes ******************************************************/
    .factory('operationService', function ($http) {
        var asyncGetAccountOperation = function (idAccount) {
            return $http.get('/api/account/transaction/' + idAccount);
        };
        return {
            // si pas return de login : la requete ne se sera exécuté en GET
            asyncGetAccountOperation: asyncGetAccountOperation
        };
    })
//*************Controleur gestion transaction : deposit, withDraw, transfer ******************************************************/

    .factory('transferService', function ($cookieStore, $http, $location) {
        var idCustomerCookie = $cookieStore.get('JSESSIONID');

        var transfer = function (amount, from, to) {
            $http({

                method: 'PUT',
                url: '/api/account/balance/' + idCustomerCookie + '/transfer',
                headers: {
                    'Content-Type': 'charset=UTF-8'
                },
                params: {
                    "amount": amount,
                    "from": from,
                    "toTest": to
                }

            })
                .then(function(){
                    //Success
                    // Transfert de l'id de l'utilisateur par cookie
                    alert("Votre transfert d'un montant de " + amount + " euros , a bien été réalisé du compte " + from + " au compte " + to);
                    $location.path('/account/' + idCustomerCookie);
                },
                function(){
                    alert("Une erreur s'est produite lors de la transaction."); //Failed
                });
        };

        var deposit = function (amount, accountCredited) {
            $http({

                method: 'PUT',
                url: '/api/account/balance/' + idCustomerCookie + '/deposit',
                headers: {
                    'Content-Type': 'charset=UTF-8'
                },
                params: {
                    "amount": amount,
                    "accountCredited": accountCredited
                }

            })
                .then(function(){
                    //Success
                    // Transfert de l'id de l'utilisateur par cookie
                    alert("Votre dépot sur le compte " + accountCredited + " d'un montant de " + amount + " euros a bien été effectué");
                    $location.path('/account/' + idCustomerCookie);
                },
                function(){
                    alert("Une erreur s'est produite lors de la transaction."); //Failed
                });

        };


        var withdraw = function (amount, accountDebited) {

            $http({

                method: 'PUT',
                url: '/api/account/balance/' + idCustomerCookie + '/withdraw',
                headers: {
                    'Content-Type': 'charset=UTF-8'
                },
                params: {
                    "amount": amount,
                    "accountDebited": accountDebited
                }

            })
                .then(function(){
                    //Success
                    // Transfert de l'id de l'utilisateur par cookie
                    alert("Votre retrait sur le compte " + accountDebited + " d'un montant de " + amount + " euros a bien été effectué");
                    $location.path('/account/' + idCustomerCookie);
                },
                function(){
                    alert("Une erreur s'est produite lors de la transaction."); //Failed
                });

        };
        return {
            // si pas return de login : la requete ne se sera exécuté en GET
            transfer: transfer,
            deposit: deposit,
            withdraw: withdraw
        };
    })


//*************Controleur permettant l'affichage de la page index: avec l'id l'utilisateur récupéré du coookie **************/
    .factory('userService', function ($resource, $cookieStore) {
        var idCustomerCookie = $cookieStore.get('JSESSIONID');

        return {
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
            logOut: logOut
        };

    })