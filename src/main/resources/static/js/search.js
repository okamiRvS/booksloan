$(document).ready(function() {
		$("#myInput").on("keyup",function() {
			var value = $(this).val().toLowerCase();
			// $(".search").length significa se esiste il selettore
			if($(".search").length) {
				if(value.length>0) {
					$("#myTable").show();
				} else {
					$("#myTable").hide();
				}
			}
			// filtra senza considerare l'ultima colonna
			$("#myTable tr").filter(function() {
				var ncol = $("#myTable").find("tr:first td").length;
				var bool = false;
				for(i = 1; i < ncol; i++) {
					if($(this).find("td:nth-child(" + i + ")").text().toLowerCase().indexOf(value) > -1) {
						bool = true;
					}
				}
				if(bool) {
					$(this).toggle(true);
				} else {
					$(this).toggle(false);
				}
			});
		});
	});