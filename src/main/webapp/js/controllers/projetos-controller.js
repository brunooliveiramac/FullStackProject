angular.module('guaraniApp').controller('ProjetosController', function($scope, $http, $location, projetosResource, projetoRegistro, ParamService) {
	
	$scope.projetos = [];
	$scope.message = '';
	$scope.filter = '';
	$scope.wait = true;


	//$scope.projetos = projetosResource.get({username: $scope.user.name});
	
	if(angular.isDefined($scope.user)){

		$scope.projetos = projetosResource.query({username: $scope.user.name});
		console.log(ParamService.usuario);
		$scope.wait = false;

	}else{
		
		console.log('Não autorizado!');
		$location.path('/login');
	}
	
	
	$scope.selected = 'None';

	$scope.menuOptions = [ 
	                      ['Ver Tabelas', function ($itemScope) {
	                          $location.path('/ver/'+$itemScope.projeto.gua_pro_id);
	                      }],
	                      null, 
	                      ['Comparer Geral', function ($itemScope) {
	                          $location.path('/compare/geral/'+$itemScope.projeto.gua_pro_id);
	                      }]
	                  ];
	
/*	projetosResource.get(function(data) {
		$scope.projetos = data.projetos;
	}, function(error) {
		console.log(error);
	});
	*/
	
	
	
	/*$http.get('rest/projetos/'+$scope.user.name)
	.success(function(projetos){
		$scope.projetos = projetos;
	}).error(function(){
		$scope.mensagem = 'Erro ao obter Projetos!';
	});
	*/
	   

	$scope.remover = function(projeto){
		projetosResource.delete({gua_pro_id : projeto.gua_pro_id}, function(){
			var indiceProjeto = $scope.projetos.indexOf(projeto);
			$scope.projetos.splice(indiceProjeto, 1); //Remove Projeto da lista sem precisar fazer outra requ para atualizar a pag e trazer as fotos
			$scope.mensagem = 'Projeto '+ projeto.gua_pro_nome + ' foi removido com sucesso!';
		}, function(erro){
			$scope.mensagem = 'Erro ao remover a Projeto!';

		});
	};
	
});