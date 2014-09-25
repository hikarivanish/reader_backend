'use strict';

/* Controllers */

var readerControllers = angular.module('readerControllers', []);

readerControllers.controller('UserCtrl', [ '$scope', '$routeParams', 'UserFac',
		function($scope, $routeParams, UserFac) {
			$scope.user = UserFac.query();
			$scope.channelId = $routeParams.channelId;
		} ]);

readerControllers.controller('ItemListCtrl', [ '$scope', '$routeParams',
		'ItemFac', function($scope, $routeParams, ItemFac) {
			$scope.items = ItemFac.query({
				channelId : $routeParams.channelId,
				itemStart : $routeParams.itemStart,
				itemLimit : 4
			});
		} ]);

readerControllers.controller('DefaultCtrl', function($scope) {

});
