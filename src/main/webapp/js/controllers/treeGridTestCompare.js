angular.module('guaraniApp').controller('treeGridControllerCompare', function ($scope, $http, $timeout, ParamService, $routeParams) {
	
	    $scope.tree_data = [];
	    
		if($routeParams.id) {
		    $http.get('rest/tabelas/tabela/'+ $routeParams.versao +'/' + $routeParams.id)
		    .success(function(tree_data){
		    	console.log('Compare!');
		    	console.log('tree: '+tree_data);
		    	$scope.versao = $routeParams.versao;
		        $scope.tree_data  = tree_data ;
		       
		    })
		    .error(function(error){
		       console.log(erro);
		    });
		}
	     
	     
	    
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
	 