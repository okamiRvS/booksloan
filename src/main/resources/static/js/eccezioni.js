$(document).ready(function(){
	$('form').submit(function () {
        var anno = $('#anno').val();
        if(!$.isNumeric(anno) || anno<0 || anno % 1 != 0){
        	alert("Devi inserire input anno corretto.");
        	return false;
        }
	});

});