$(document).ready(function() {
	$("#action").click(function() {
			$("#deroulant").slideToggle();
	});
	$("#close").click(function() {
		if ($("#deroulant").css("display") == "block") {
			$("#deroulant").slideUp();
		}
	});
});