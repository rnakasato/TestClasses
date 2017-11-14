package com.rnakasato.report;

import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportGenerator {
	public static JasperDesign jasperDesign;
	public static JasperPrint jasperPrint;
	public static JasperReport jasperReport;
	public static String reportTemplateUrl = "person-template.jrxml";

	// Necessário JAVA 7
	public static void main(String[] args) {
		try {
			InputStream resourceAsStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(reportTemplateUrl);
			// get report file and then load into jasperDesign
			jasperDesign = JRXmlLoader.load(resourceAsStream);
			// compile the jasperDesign
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			// fill the ready report with data and parameter
			jasperPrint = JasperFillManager.fillReport(jasperReport, null,
					new JRBeanCollectionDataSource(findReportData()));
			// view the report using JasperViewer
			JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	public static Collection findReportData() {
		// declare a list of object
		List<Person> data = new LinkedList<Person>();
		Person p1 = new Person();
		p1.setFirstName("John");
		p1.setSurname("Smith");
		p1.setAge(Integer.valueOf(5));

		Person p2 = new Person();
		p2.setFirstName("Rafa");
		p2.setSurname("Naka");
		p2.setAge(Integer.valueOf(22));

		data.add(p1);
		data.add(p2);
		return data;
	}
}
