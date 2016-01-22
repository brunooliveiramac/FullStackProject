angular.module('guaraniApp').controller('treeGridController', function ($scope, $http, $timeout, ParamService, $location, $rootScope, $routeParams) {

	
			
			      $scope.tree_data = [];
			      $scope.layouts = [];
	      
			      var versao = '';
	      
	  

		    	  $http.get('rest/tabelas/'+$routeParams.gua_pro_id)
		          .success(function(tree_data ){
		             $scope.tree_data  = tree_data ;
		          })
		          .error(function(error){
		             console.log(erro);
		          });
		    	   
	      
	     	
		     	 $http.get('rest/layouts')
		     	 .success(function(layouts){
		     		$scope.layouts = layouts;
		     	 }).error(function(){
		     		$scope.mensagem = 'Erro ao obter Versões!';
		         });
     	
     	
		    	$scope.updateTables = function(){
		    		ParamService.versao = $scope.layout;
		    		console.log(ParamService.versao);
		    	}; 	
		     	
 
		    	$scope.setVersao = function() {
			      	  $http.get('rest/tabelas/'+ParamService.versao+'/'+ $routeParams.gua_pro_id)
			          .success(function(tree_data ){
			               $scope.tree_data  = tree_data ;
				    	   console.log(tree_data);
			            })
			            .error(function(error){
			               console.log(erro);
			           });	      	   
		     	 };
		     	 
		     	 /*
		     
		     	function tabelasPorVersao(id) {
		     		  $http.get('rest/tabelas/'+id)
		     	         .success(function(tree_data ){
		     	            $scope.tree_data  = tree_data ;
		     	    		console.log(tree_data);
		     	         })
		     	         .error(function(error){
		     	            console.log(erro);
		     	      });
		     		  
		     	};*/

	   
           
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
		            }, 
		            {
		                field: "layout_gua_lay_num_ver",
		                displayName: "Versão",
		                sortable : true,                                        
		                filterable: true
		            }
		        ];
		          
		        $scope.my_tree_handler = function (branch) {
		            console.log('Selecionou: ', branch)
		        }
		        
		        $scope.grid = function (versao, id) { 
		        	if(angular.isDefined(versao)){
		 		
		        			   $location.path('/ver/tabela/'+ParamService.versao+'/'+id);
		                       console.log('Versão e ID: ', ParamService.versao + ' '+ id)
		
		        	}else{
				    			$rootScope.error = "Selecione uma tabela com uma versão definida!";
				        		console.log('Erro! Somente Tabela!');
		        	}
		        }
 
});
 