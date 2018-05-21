package com.rmportal.view;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String headerName [] =(String[]) model.get("headerName");
		String fields []  =(String[]) model.get("fields");
		String fileName  =(String) model.get("fileName");
		List<?> list = (List<?>) model.get("data");
		if(null!=fileName) {
			fileName+="attachment; filename="+fileName+".pdf";
		}else {
			fileName="attachment; filename=download.pdf";
		}
		response.setHeader("Content-Disposition", fileName);

		getPDFDocument(document, list,headerName,fields);

	}

	public void getPDFDocument(Document document, List<?> list,String headerName[],String fields []) throws DocumentException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		//document.add(new Paragraph("Room Book Details " + LocalDate.now()));
		Class<?> clss[] = {};
		Object [] obj= {};
		PdfPTable table = new PdfPTable(fields.length);
		table.setWidthPercentage(100.0f);
		table.setSpacingBefore(10);

		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.TIMES);
		font.setColor(BaseColor.WHITE);
		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.DARK_GRAY);
		cell.setPadding(5);

		// write table header
		for (String header : headerName) {
			cell.setPhrase(new Phrase(header, font));
			table.addCell(cell);
		}
		for (Object detail : list) {
			for (String property : fields) {
				String subProperty []=property.split("\\.");
				final Field  field=detail.getClass().getDeclaredField(subProperty[0]);
				final Transient t = field.getAnnotation(Transient.class);
				if(null!=t) {
					final String methodName="get"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1);
					final Method m = detail.getClass().getMethod(methodName, clss);
					final Object v=m.invoke(detail,obj);
					setTableValue(v, table);
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
							setTableValue(subValue, table);	
						}else {
							setTableValue(value, table);
						}
						
					}else {
						setTableValue(value, table);
					}
				}
			}
			
		}

		document.add(table);
	}
	
	private void setTableValue(final Object value,final PdfPTable table) {
		if(null!=value) {
			if(value instanceof Number) {
				table.addCell(String.valueOf(value));
			}else {
				table.addCell((String)value);
			}	
		}else {
			table.addCell("");
		}
		
	}

	/*
	 * Writes the content of a PDF file (using iText API) to the {@link
	 * OutputStream}.
	 * 
	 * @param outputStream {@link OutputStream}.
	 * 
	 * @throws Exception
	 */
	public void writePdf(Document document) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, outputStream);
		//byte[] bytes = outputStream.toByteArray();
        //construct the pdf body part
//        DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
	}

}
