angular.module('appServices', ['ngResource'])
		.factory('projetosResource', function($resource) {
			
			return $resource('rest/projetos/:username/:gua_pro_id',
				
					{username: '@username', gua_pro_id: '@gua_pro_id'},
					
							{
							update : {
								method: 'PUT'
							},
							get : {
					 			method: 'GET',
								isArray: false
							}
						
						
					});   
	
}).factory('projetoRegistro', function(projetosResource, $q, $rootScope) {
	var service = {};
	
	var evento = 'projetoRegistrado' 
	  
	service.register = function(projeto) {
		return $q(function(resolve, reject) {
			if(projeto.gua_pro_id) {
				
				
				projetosResource.update({gua_pro_id : projeto.gua_pro_id}, projeto, function() {
					$rootScope.$broadcast(evento);
					resolve({
						message: 'Projeto ' + projeto.gua_pro_nome + ' foi alterado com sucesso!',
						include:  false
					});
				}, function(error) {
					console.log(error);
					reject({
						message: 'Ocorreu um erro ao alterar o projeto ' + projeto.gua_pro_nome + ' !'
					});
				});
			} else {
				projetosResource.save(projeto, function() {
					$rootScope.$broadcast(projeto);
					resolve({
						message: 'Projeto ' + projeto.gua_pro_nome  + ' foi criado com sucesso!',
						include: true
					});
				}, function(error) {
					console.log(error);
					reject({
						message: 'Ocorreu um erro ao criar o projeto ' + projeto.gua_pro_nome + '!'
					});
				});
			}
		});
	};
	return service;
	
});