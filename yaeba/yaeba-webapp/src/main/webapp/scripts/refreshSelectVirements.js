$(document).ready(
		function() {
			$("#compteEmetteur").change(
					function() {
						var selectedValue = $("option:selected", this).val();
						var htmlToInsert = "";
						$("#compteEmetteur option").each(
								function() {
									if ($(this).val() != 0
											&& $(this).val() != selectedValue)
										htmlToInsert += "<option value='"
												+ $(this).val() + "'>"
												+ $(this).text() + "</option>";
								});
						$("#compteRecepteur").html(htmlToInsert);
					});
		});