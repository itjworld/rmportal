package com.rmportal.repositories;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.rmportal.model.PortalInfo;

@Repository
public interface InfoRepository extends JpaRepository<PortalInfo, Long> {

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<PortalInfo> findByType(String type);

	public static void main2(String[] args) {
		Process p = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			p = runtime.exec(
					"mysqldump -uroot -proot -B rmportal");
			// change the dbpass and dbname with your dbpass and dbname

				System.out.println("Backup created successfully!");
				InputStream in = p.getInputStream(); 
				
				OutputStream ou = p.getOutputStream(); 
				    BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF8"));
				    StringBuffer temp = new StringBuffer();
				    int count;
				    int BUFFER=1024;
				    char[] cbuf = new char[BUFFER];

				    while ((count = br.read(cbuf, 0, BUFFER)) != -1)
				        temp.append(cbuf, 0, count);

				    br.close();
				    in.close();

				    System.out.println(temp.toString());


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
