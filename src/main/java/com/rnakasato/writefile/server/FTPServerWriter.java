package com.rnakasato.writefile.server;

import java.io.File;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import com.rnakasato.dosomething.ZhuLi;

public class FTPServerWriter {

	private static String ROOT_FOLDER = "Root Folder";

	public static void main(String[] args) {
		FTPData ftpData = new FTPData();
		ftpData.setServer("localhost");
		ftpData.setPort(21);
		ftpData.setUsername("admin");
		ftpData.setPassword("admin");

		try {
			FTPClient ftpClient = FTPUtils.connectFTP(ftpData);

			System.out.println("Starting transfer of files  to FTP Server");
			for (int i = 0; i < 10000; i++) {

				// create a random file to upload to FTP server
				File file = ZhuLi.doTheThing();

				FTPUtils.createDirectoryTree(ROOT_FOLDER, ftpClient);
				FTPUtils.writeFile(file, ROOT_FOLDER + "/" + i + " - " + file.getName(), ftpClient);
				file.delete();

				if (i % 100 == 0) {
					System.out.println("Number of done uploads: " + i);
				}

			}
			System.out.println("I guess the files were uploaded");
			// deleteDirecotyRecursive(ftpClient, ROOT_FOLDER, null);

			FTPUtils.disconnectFTP(ftpClient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
