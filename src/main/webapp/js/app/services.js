angular.module('bankingApp.services', ['ngCookies'])

//*************Controleur permettant l'affichage de la liste de compte ******************************************************/
    .factory('accountService', function ($resource) {
        return $resource('/api/account/list');
    })
    /****************************************************************************************************************************/
    .factory('transferService', function ($cookieStore, $http) {
        var transfer = function (amount, from, to) {
           /* alert(from);
            alert(to);
            alert(amount);*/
          //  alert("tgggest");
            var idCustomerCookie = $cookieStore.get('user');
            //alert(idCustomerCookie);
            var req = {
                method: 'POST',
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
            $http(req).success(function (data) {

                // Transfert de l'id de l'utilisateur par cookie
                alert("heo");

            });
        };

        return {
            // si pas return de login : la requete ne se sera exécuté en GET
            transfer: transfer
        };



      /*  return $resource('/api/account/transfer',
            {
                query: {method: 'POST'}
            } );*/
    })



//*************Controleur permettant l'affichage de la page index: avec l'id l'utilisateur récupéré du coookie **************/
    .factory('userService', function ($resource, $cookieStore) {
        var idCustomerCookie = $cookieStore.get('user');
        // alert(idCustomerCookie);
        var SeeUserAccount = function ($location){
          //  alert("test");
          //  $location.path("/api/account/:id");
        }

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
    *l'id de l'user est rnvoyé par la méthode api/connexion*********************************************************************/
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
                $cookieStore.put('user', data);
                $location.path('/account/' + data);

            });
        };

        return {
            // si pas return de login : la requete ne se sera exécuté en GET
            login: login
        };

    })
