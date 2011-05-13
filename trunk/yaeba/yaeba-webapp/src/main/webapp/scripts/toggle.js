$(document).ready(function() {
	$("#action2").click(function() {
		$("#action").click();
	});
	$("#action").click(function() {
		if ($("#deroulant").css("display") == "block") {
			$("#arrow").rotate(0);
			$("#deroulant").slideUp();
		}
		else {
			$("#arrow").rotate(90);
			$("#deroulant").slideDown();
		}
	});
	$("#close").click(function() {
		if ($("#deroulant").css("display") == "block") {
			$("#deroulant").slideUp();
			$("#arrow").rotate(0);
		}
	});
});