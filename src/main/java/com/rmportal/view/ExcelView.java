package com.rmportal.view;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;


public class ExcelView extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String headerName [] =(String[]) model.get("headerName");
		String fields []  =(String[]) model.get("fields");
		String fileName  =(String) model.get("fileName");
		List<?> list = (List<?>) model.get("data");
		String sheetName  =(String) model.get("sheetName");
		Class<?> clss[] = {};
		Object [] obj= {};
		if(null!=fileName) {
			fileName+="attachment; filename="+fileName+".xls";
		}else {
			fileName="attachment; filename=download.xls";
		}
		response.setHeader("Content-Disposition", fileName);
		final Sheet sheet = workbook.createSheet(sheetName);
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        style.setFont(font);

        int rowCount = 0;
        final Row header = sheet.createRow(rowCount);
        for (int i = 0; i < headerName.length; i++) {
        	header.createCell(i).setCellValue(headerName[i]);
            header.getCell(i).setCellStyle(style);
		}
        
       
        
        

        for(Object detail : list){
        	final Row row =  sheet.createRow(++rowCount);
            for (int i = 0; i < fields.length; i++) {
            	String subProperty []=fields[i].split("\\.");
            	final Field  field=detail.getClass().getDeclaredField(subProperty[0]);
            	final Transient t = field.getAnnotation(Transient.class);
				if(null!=t) {
					final String methodName="get"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1);
					final Method m = detail.getClass().getMethod(methodName, clss);
					final Object v=m.invoke(detail,obj);
					setCellValue(i, v, row);	
					continue;
				}
				if(null!=field) {
					field.setAccessible(true);
					final Object value=field.get(detail);
					if(null!=value) {
						if(subProperty.length>1) {
							final Field subField=value.getClass().getDeclaredField(subProperty[1]);
							subField.setAccessible(true);
							final Object subValue=subField.get(value);
							setCellValue(i, subValue, row);		
						}else {
							setCellValue(i, value, row);	
						}
						
					}else {
						setCellValue(i, value, row);	
					}
				}
			}
         }

    }
	
	private void setCellValue(int i,Object value,final Row row) {
		if(null!=value) {
			if(value instanceof Number) {
				final Number _n=(Number)value;
				row.createCell(i).setCellValue(_n.doubleValue());
			}else {
				row.createCell(i).setCellValue((String)value);
			}
		}else {
			row.createCell(i).setCellValue("");
		}
		
	}
	
}
