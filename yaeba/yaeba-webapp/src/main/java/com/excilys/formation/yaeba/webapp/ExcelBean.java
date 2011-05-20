package com.excilys.formation.yaeba.webapp;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.Utilisateur;

public class ExcelBean extends AbstractExcelView {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.setContentType("application/msexcel");
		response.setHeader("Content-Disposition", "attachment; filename=\"yourFile.xls\"");

		String numeroCompte = (String) model.get("numero");
		String libelle = (String) model.get("libelle");
		DateBean dateBean = (DateBean) model.get("dateBean");

		List<Operation> listeOperations = (List<Operation>) model.get("listeOperations");

		float sommeCB = (Float) model.get("sommeCB");

		Utilisateur u = (Utilisateur) model.get("utilisateur");

		this.setContentType("application/msexcel");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + libelle + "_" + numeroCompte + "_" + dateBean.getAnnee() + "-" + dateBean.getMois() + ".xls\"");

		HSSFSheet sheet = workbook.createSheet("Revenue Report");

		// Déclarations des styles
		HSSFFont fontBold = workbook.createFont();
		fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFFont fontRed = workbook.createFont();
		fontRed.setColor(HSSFColor.RED.index);

		HSSFFont fontBoldWhite = workbook.createFont();
		fontBoldWhite.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontBoldWhite.setColor(HSSFColor.WHITE.index);

		HSSFCellStyle styleBoldRight;
		styleBoldRight = workbook.createCellStyle();
		styleBoldRight.setFont(fontBold);
		styleBoldRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

		HSSFCellStyle styleBoldCenter;
		styleBoldCenter = workbook.createCellStyle();
		styleBoldCenter.setFont(fontBold);
		styleBoldCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle styleBoldCenterOrange;
		styleBoldCenterOrange = workbook.createCellStyle();
		styleBoldCenterOrange.setFont(fontBoldWhite);
		styleBoldCenterOrange.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		styleBoldCenterOrange.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleBoldCenterOrange.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle styleCenterGray;
		styleCenterGray = workbook.createCellStyle();
		styleCenterGray.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleCenterGray.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		styleCenterGray.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFCellStyle styleRightGray;
		styleRightGray = workbook.createCellStyle();
		styleRightGray.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		styleRightGray.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		styleRightGray.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFCellStyle styleLeftGrayColorRed;
		styleLeftGrayColorRed = workbook.createCellStyle();
		styleLeftGrayColorRed.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleLeftGrayColorRed.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		styleLeftGrayColorRed.setFont(fontRed);
		styleLeftGrayColorRed.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFCellStyle styleCenter;
		styleCenter = workbook.createCellStyle();
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle styleRight;
		styleRight = workbook.createCellStyle();
		styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

		HSSFCellStyle styleLeftColorRed;
		styleLeftColorRed = workbook.createCellStyle();
		styleLeftColorRed.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleLeftColorRed.setFont(fontRed);

		// 2eme ligne
		HSSFRow header = sheet.createRow(1);
		HSSFCell c = header.createCell((short) 0);
		c.setCellValue(new HSSFRichTextString("Login"));
		c.setCellStyle(styleBoldRight);

		c = header.createCell((short) 1);
		c.setCellValue(new HSSFRichTextString(u.getLogin()));
		c.setCellStyle(styleCenter);

		// 3eme ligne
		header = sheet.createRow(2);
		c = header.createCell((short) 0);
		c.setCellValue(new HSSFRichTextString("Nom"));
		c.setCellStyle(styleBoldRight);

		c = header.createCell((short) 1);
		c.setCellValue(new HSSFRichTextString(u.getNom()));
		c.setCellStyle(styleCenter);

		// 4eme ligne
		header = sheet.createRow(3);
		c = header.createCell((short) 0);
		c.setCellValue(new HSSFRichTextString("Prenom"));
		c.setCellStyle(styleBoldRight);

		c = header.createCell((short) 1);
		c.setCellValue(new HSSFRichTextString(u.getPrenom()));
		c.setCellStyle(styleCenter);

		// 5eme ligne
		header = sheet.createRow(4);
		c = header.createCell((short) 0);
		c.setCellValue(new HSSFRichTextString("Adresse"));
		c.setCellStyle(styleBoldRight);

		c = header.createCell((short) 1);
		c.setCellValue(new HSSFRichTextString(u.getAdresse()));
		c.setCellStyle(styleCenter);

		// 7eme ligne
		header = sheet.createRow(6);
		c = header.createCell((short) 2);
		c.setCellValue(new HSSFRichTextString(dateBean.getMois() + "/" + dateBean.getAnnee()));
		c.setCellStyle(styleBoldCenter);

		// 9eme ligne
		header = sheet.createRow(8);
		c = header.createCell((short) 2);
		c.setCellValue(new HSSFRichTextString("Libelle"));
		c.setCellStyle(styleBoldCenterOrange);

		c = header.createCell((short) 3);
		c.setCellValue(new HSSFRichTextString("Date"));
		c.setCellStyle(styleBoldCenterOrange);

		c = header.createCell((short) 4);
		c.setCellValue(new HSSFRichTextString("Montant"));
		c.setCellStyle(styleBoldCenterOrange);

		HSSFRow row;
		int rowNum = 9;
		for (Operation op : listeOperations) {
			row = sheet.createRow(rowNum++);

			c = row.createCell((short) 2);
			c.setCellValue(new HSSFRichTextString(op.getLibelle()));
			if (rowNum % 2 == 0) c.setCellStyle(styleCenterGray);
			else
				c.setCellStyle(styleCenter);

			c = row.createCell((short) 3);
			c.setCellValue(new HSSFRichTextString(op.getDateCreation().toString("dd-MM-yyyy")));
			if (rowNum % 2 == 0) c.setCellStyle(styleCenterGray);
			else
				c.setCellStyle(styleCenter);

			c = row.createCell((short) 4);
			c.setCellValue(new HSSFRichTextString(op.getMontant() + ""));
			if (rowNum % 2 == 0) {
				if (op.getMontant() < 0) c.setCellStyle(styleLeftGrayColorRed);
				else
					c.setCellStyle(styleRightGray);
			} else {
				if (op.getMontant() < 0) c.setCellStyle(styleLeftColorRed);
				else
					c.setCellStyle(styleRight);
			}
		}

		header = sheet.createRow(++rowNum);
		c = header.createCell((short) 2);
		c.setCellValue(new HSSFRichTextString("Total des dépenses CB"));
		c.setCellStyle(styleBoldRight);

		c = header.createCell((short) 4);
		c.setCellValue(new HSSFRichTextString(sommeCB + ""));
		c.setCellStyle(styleCenter);

		sheet.autoSizeColumn((short) 2);
		sheet.autoSizeColumn((short) 3);
		sheet.autoSizeColumn((short) 4);
	}
}