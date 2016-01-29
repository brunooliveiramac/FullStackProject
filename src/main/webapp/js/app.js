angular.module('guaraniApp', ['ngRoute', 'ngCookies', 'guaraniApp.services', 'appServices','treeGrid','ui.bootstrap.contextMenu', 'ngFileUpload']) 
	.config(
		[ '$routeProvider', '$locationProvider', '$httpProvider', '$compileProvider', function($routeProvider, $locationProvider, $httpProvider, $compileProvider) {
			
	        $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|tel|file|blob):/);

			
			$routeProvider.when('/create', {
				templateUrl: 'partials/create.html',
				controller: 'ProjetoController'
			});
			
			$routeProvider.when('/edit/:gua_pro_id', {
				templateUrl: 'partials/create.html',
				controller: 'ProjetoController' 
			});

			$routeProvider.when('/login', {
				templateUrl: 'partials/login.html',
				controller: LoginController
			}); 
			 
			$routeProvider.when('/validador', {
				templateUrl: 'partials/validador.html',
				controller: 'ValidadorController'
			});
			
			$routeProvider.when('/files', {
				templateUrl: 'partials/files.html',
				controller: 'DownloadController'
			});
			  
			 
			$routeProvider.when('/ver/:gua_pro_id', {
				templateUrl: 'partials/treeGrid.html',
				controller: 'treeGridController'
			});
			
			$routeProvider.when('/ver/tabela/:versao/:id', {
				templateUrl: 'partials/treeGridCompare.html',
				controller: 'treeGridControllerCompare' 
 
			});
			
			$routeProvider.when('/compare/geral/:gua_pro_id', {
				templateUrl: 'partials/treeGridCompareGeral.html',
				controller: 'treeGridControllerCompare'
			});
			
			 
			$routeProvider.otherwise({
				templateUrl: 'partials/index.html',
				controller: 'ProjetosController' 
			});
			
			
			
			$locationProvider.hashPrefix('!');
			
			/* Register error provider that shows message on failed requests or redirects to login page on
			 * unauthenticated requests */
		    $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
			        return {
			        	'responseError': function(rejection) {
			        		var status = rejection.status;
			        		var config = rejection.config;
			        		var method = config.method;
			        		var url = config.url; 
			      
			        		if (status == 401) {
			        			$location.path( "/login" );
			        			$rootScope.error = "Dados inválidos!";
			        		} else {
			        			$rootScope.error = method + " Erro! " + url + " Status " + status + "! Contate um administrador do Sistema!";
			        		}
			              
			        		return $q.reject(rejection);
			        	}
			        };
			    }
		    );
		    
		    /* Registers auth token interceptor, auth token is either passed by header or by query parameter
		     * as soon as there is an authenticated user */
		    $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
		        return {
		        	'request': function(config) {
		        		var isRestCall = config.url.indexOf('rest') == 0;
		        		if (isRestCall && angular.isDefined($rootScope.authToken)) {
		        			var authToken = $rootScope.authToken;
		        			if (guaraniAppConfig.useAuthTokenHeader) {
		        				config.headers['X-Auth-Token'] = authToken;
		        			} else {
		        				config.url = config.url + "?token=" + authToken;
		        			}
		        		}
		        		return config || $q.when(config);
		        	}
		        };
		    }
	    );
		   
		} ]
		
	).run(function($rootScope, $location, $cookieStore, UserService) {
		
		/* Reset error when a new view is loaded */
		$rootScope.$on('$viewContentLoaded', function() {
			delete $rootScope.error;
		});
		
		$rootScope.hasRole = function(role) {
			
			if ($rootScope.user === undefined) {
				return false;
			}
			
			if ($rootScope.user.roles[role] === undefined) {
				return false;
			}
			
			return $rootScope.user.roles[role];
		};
		
		$rootScope.logout = function() {
			delete $rootScope.user;
			delete $rootScope.authToken;
			$cookieStore.remove('authToken');
			$location.path("/login");
		};
		
		 /* Try getting valid user from cookie or go to login page */
		var originalPath = $location.path();
		$location.path("/login");
		var authToken = $cookieStore.get('authToken');
		if (authToken !== undefined) {
			$rootScope.authToken = authToken;
			UserService.get(function(user) {
				$rootScope.user = user;
				$location.path(originalPath);
			});
		}
		
		$rootScope.initialized = true;
	});


function IndexController($scope, ProjetosService) {
	
	$scope.projetos = ProjetosService.query();
	
	$scope.deletetarProjeto = function(projeto) {
		projeto.$remove(function() {
			$scope.projetos = ProjetosService.query();
		});
	};
};


function EditController($scope, $routeParams, $location, ProjetosService) {

	$scope.projeto = ProjetosService.get({id: $routeParams.id});
	
	$scope.save = function() {
		$scope.projeto.$save(function() {
			$location.path('/');
		});
	};
};


function CreateController($scope, $location, ProjetosService) {
	
	$scope.projeto = new ProjetosService();
	
	$scope.save = function() {
		$scope.projeto.$save(function() {
			$location.path('/');
		});
	};
};


 
function LoginController($scope, $rootScope, $location, $cookieStore, UserService) {
	
	$scope.rememberMe = false;
    $scope.mensagem = '';
    
	$scope.login = function() {
			UserService.authenticate($.param({username: $scope.username, password: $scope.password}),
			function(authenticationResult) {
			var authToken = authenticationResult.token;
			$rootScope.authToken = authToken;
			
					if ($scope.rememberMe) {
						$cookieStore.put('authToken', authToken);
					}
					
			UserService.get(function(user) {
	 			$rootScope.user = user;
				$location.path("/"); 
			}, function(erro){
    			$scope.mensagem = "Dados inválidos!";
			});
		});
	};
};


var services = angular.module('guaraniApp.services', ['ngResource']);

services.factory('UserService', function($resource) {
	
	return $resource('rest/user/:action', {},
			{
				authenticate: {
					method: 'POST',
					params: {'action' : 'authenticate'},
					headers : {'Content-Type': 'application/x-www-form-urlencoded'}
				},
			} 
		);
});


services.factory('ProjetosService', function($resource) {
	
	return $resource('rest/projetos/:id', {id: '@id'});
});

angular.module('guaraniApp')
    .factory('FileService', function ($http) {
        return {
            getFile: function (params) {
            	 return 'http://192.168.0.56' + ':' + '8080' + '/' + 'guaranirta' + '/rest/files/word/'+params
        }
    };
});

services.getFile = function () {
    return url + ':' + port + '/' + context + '/rest/files/word'
};
 

services.factory('ParamService', function() {
	  return { 
	      versao : '',
	      id : '',
	      versaocompare : '',
	      versaoorigem : '',
	      versaodestino : '',
	      usuario : ''
	  };
});