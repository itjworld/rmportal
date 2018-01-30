package com.rmportal.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.rmportal.model.GuestDetail;

public class ExcelView extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"my-xls-file.xls\"");
		@SuppressWarnings("unchecked")
		List<GuestDetail> roomBookDetails = (List<GuestDetail>) model.get("roomDetails");
		Sheet sheet = workbook.createSheet("Room Book Details");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        style.setFont(font);


        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Id");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Name");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Mobile");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Email");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Rent");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("Security");
        header.getCell(5).setCellStyle(style);
        
        int rowCount = 1;

        for(GuestDetail detail : roomBookDetails){
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(detail.getId());
            userRow.createCell(1).setCellValue(detail.getfName());
            userRow.createCell(2).setCellValue(detail.getMobile());
            userRow.createCell(3).setCellValue(detail.getEmail());
            userRow.createCell(4).setCellValue(detail.getRent());
            userRow.createCell(5).setCellValue(detail.getSecurity());
         }

    }
	
}
