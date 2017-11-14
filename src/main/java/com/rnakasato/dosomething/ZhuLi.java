package com.rnakasato.dosomething;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 
 * @author Rafael 
 * No important things are done here, just to create a random zip file with some contents
 */
public class ZhuLi {

	// Avatar reference
	public static File doTheThing() throws IOException {
		File zipFile = new File("textFiles.zip");
		zipFile.createNewFile();

		FileOutputStream fos = new FileOutputStream(zipFile);
		ZipOutputStream zout = new ZipOutputStream(fos);

		List<String> fileList = new ArrayList<>();
		fileList.add("folderA/text.txt");
		fileList.add("folderA/text.txt");
		fileList.add("folderB/text.txt");

		addDirectory(zout, fileList);

		return zipFile;
	}
	
	

	private static void addDirectory(ZipOutputStream zout, List<String> fileList) throws IOException {

		for (String filePath : fileList) {
			zout.putNextEntry(new ZipEntry(filePath));
			zout.write("Gogogogogo lazy programmer".getBytes());
			zout.closeEntry();
		}
		zout.close();
	}

}
