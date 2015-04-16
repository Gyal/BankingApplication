angular.module('bankingApp.services')
    .factory('accountService', function ($resource) {
        return $resource('/api/account/list', {
            update: {
                method: 'PUT'
            },
            remove: {
                method: 'DELETE'
            }
        });
    })

    .factory('userService', function ($resource) {
        return $resource('/api/account/:id', {
            id: '@id'
        });
    })
    .factory('loginService', function ($http, $q) {
        var self = this;
        self.currentUser = {};

        var login = function(username, password, callback) {
            var req = {
                params : {
                    connexionLogin: username,
                    password: password
                }
            };
            $http.post('/api/connexion/', req)
                .success(function(data, status) {
                    self.currentUser = data;
                    callback(data);
                });
        };

        return {
            login: login
        };
    });