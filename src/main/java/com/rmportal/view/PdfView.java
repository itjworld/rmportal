package com.rmportal.view;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.rmportal.model.RoomBookDetails;

@Component
public class PdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"my-pdf-file.pdf\"");

		@SuppressWarnings("unchecked")
		List<RoomBookDetails> roomBookDetails = (List<RoomBookDetails>) model.get("roomDetails");
		getPDFDocument(document, roomBookDetails);

	}

	public void getPDFDocument(Document document, List<RoomBookDetails> roomBookDetails) throws DocumentException {
		document.add(new Paragraph("Room Book Details " + LocalDate.now()));

		PdfPTable table = new PdfPTable(6);
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
		cell.setPhrase(new Phrase("Id", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Mobile", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Email", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Rent", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Security", font));
		table.addCell(cell);

		for (RoomBookDetails detail : roomBookDetails) {
			table.addCell(String.valueOf(detail.getId()));
			table.addCell(detail.getfName());
			table.addCell(detail.getMobile());
			table.addCell(detail.getEmail());
			table.addCell(String.valueOf(detail.getRent()));
			table.addCell(String.valueOf(detail.getSecurity()));
		}

		document.add(table);
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
		byte[] bytes = outputStream.toByteArray();
        //construct the pdf body part
//        DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
	}

}
