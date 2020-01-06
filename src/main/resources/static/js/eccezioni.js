$(document).ready(function(){
	$('form').submit(function () {
        var anno = $('#anno').val();
        if(anno!=null && (!$.isNumeric(anno) || anno<0 || anno % 1 != 0)){
        	alert("Devi inserire input anno corretto.");
        	return false;
        }
	});
	
	$('form').submit(function () {
        var isbn = $('#isbn').val();
        if(isbn!=null && (!$.isNumeric(isbn) || isbn<0 || isbn % 1 != 0)){
        	alert("Devi inserire un isbn corretto.");
        	return false;
        }
	});

});