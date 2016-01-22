angular.module('guaraniApp').controller('treeGridControllerCompareGeralDestino', function ($scope, $http, $timeout, ParamService, $routeParams) {

	     $scope.tree_data = [];
	     $scope.layouts = [];
	    
	      $http.get('rest/layouts')
	 	 .success(function(layouts){
	 		$scope.layouts = layouts;
	 	 }).error(function(){
	 		$scope.mensagem = 'Erro ao obter Versões!';
	     });
		 
	    
	    $scope.updateTables = function(){
 		ParamService.versaodestino = $scope.layout;
 		console.log('Destino: ' +ParamService.versaodestino);
 		console.log('Origem: ' + ParamService.versaoorigem);

 		console.log($routeParams.gua_pro_id); 
	   }; 	 
 	
	     
 	$scope.setVersaoCompare = function() {
 		$http.get('rest/tabelas/tabela/geral/'+ ParamService.versaoorigem +'/' + ParamService.versaodestino +'/' + $routeParams.gua_pro_id)
 	    .success(function(tree_data){
	        	   $scope.wait = true;
	        	    $timeout(function() {
		               $scope.tree_data  = tree_data ;
			    	   console.log(tree_data);
			    	   console.log("Sucesso!");
			    	   $scope.wait = false;
			    	   
	        	    }, 1000);

	            })
	            .error(function(error){
	               console.log(erro);
	               
	           });	      	   
	 };
	 


	    $scope.my_tree = tree = {};
	    $scope.expanding_property = {
	      field: "nome",
	      displayName: "Nome",
	      sortable : true,
	      filterable: true
	    };
	
	    
	    $scope.col_defs = [
	     {
	          field: "comentarios",
	          displayName: "Comentários",
	          sortable : true,                                        
	          filterable: true
	      },
	
	      {
	          field: "cprimaria",
	          displayName: "Primaria",
	          sortable : true,                                        
	          filterable: true
	      },
	      {
	          field: "tam",
	          displayName: "Tamanho",
	          sortable : true,                                        
	          sortingType : "number"
	      },
	       
	       {
	          field: "deci",
	          displayName: "Decimal",
	          sortable : true,                                        
	          filterable: true
	      },
	       {
	          field: "nulo",
	          displayName: "Nulo",
	          sortable : true,                                        
	          filterable: true
	      },
	       {
	          field: "ativo",
	          displayName: "Ativo",
	          sortable : true,                                        
	          filterable: true
	      }
	  ];
	    
	  $scope.my_tree_handler = function (branch) {
	      console.log('Selecionou: ', branch)
	  }

	 /* $scope.grid = function (branch) {
	      console.log('Clicou: ', branch)
	      $location.path('/ver/tabela/'+branch);
	  }*/


			    

    
			    
});
 