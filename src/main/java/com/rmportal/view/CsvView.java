package com.rmportal.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;


public class CsvView extends AbstractCsvView{

	@Override
	protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String header [] =(String[]) model.get("headerName");
		String fields []  =(String[]) model.get("fields");
		String fileName  =(String) model.get("fileName");
		List<?> list = (List<?>) model.get("data");
		if(null!=fileName) {
			fileName+="attachment; filename="+fileName+".csv";
		}else {
			fileName="attachment; filename=download.scv";
		}
		response.setHeader("Content-Disposition", fileName);
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);

        csvWriter.writeHeader(header);

        for(Object detail : list){
            csvWriter.write(detail, fields);
        }
        csvWriter.close();
		
	}

}
