define(['jquery'], function($) {
	
	function rotateRule(deg) {
		return 'translate(10px) rotate(' +  deg + 'deg)';
	}
	
	return { // export de l'API
		rotate: function(selector, degrees) {
			var $element = $(selector);
			$element.css('-moz-transform', rotateRule(degrees));
			$element.css('-moz-transform-origin', '0% 10%');

                        $element.css('-webkit-transform', rotateRule(degrees));
                        $element.css('-webkit-transform-origin', '0% 10%');
		}
	};
});
