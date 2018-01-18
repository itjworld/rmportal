package com.rmportal.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.rmportal.model.RoomBookDetails;

public class CsvView extends AbstractCsvView{

	@Override
	protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"my-csv-file.csv\"");

		@SuppressWarnings("unchecked")
		List<RoomBookDetails> roomBookDetails = (List<RoomBookDetails>) model.get("roomDetails");
        String[] header = {"Id","fName","Mobile","Email","Rent","Security",};
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);

        csvWriter.writeHeader(header);

        for(RoomBookDetails detail : roomBookDetails){
            csvWriter.write(detail, header);
        }
        csvWriter.close();
		
	}

}
