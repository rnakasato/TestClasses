package com.rnakasato.writefile.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPUtils {
	public static FTPClient connectFTP(FTPData ftpData) throws IOException {
		FTPClient ftpClient = new FTPClient();

		ftpClient.connect(ftpData.getServer(), ftpData.getPort());
		ftpClient.login(ftpData.getUsername(), ftpData.getPassword());
		ftpClient.enterLocalPassiveMode();

		return ftpClient;
	}

	public static void disconnectFTP(FTPClient ftpClient) throws IOException {
		ftpClient.disconnect();
	}

	public static void writeFile(File localFile, String remoteFileName, FTPClient ftpClient) throws IOException {
		createDirectoryTree(remoteFileName, ftpClient);
		InputStream inputStream = new FileInputStream(localFile);

		// System.out.println("Start uploading first file");
		boolean done = ftpClient.storeFile(remoteFileName, inputStream);

		inputStream.close();
		if (done) {
			// System.out.println("The first file is uploaded successfully.");
		}

	}

	public static void createDirectoryTree(String directory, FTPClient ftpClient) throws IOException {
		String[] directoryTree = directory.split("/");
		StringBuilder parentPath = new StringBuilder();

		for (String dir : directoryTree) {
			if (dir.contains(".")) {
				System.out.println("The specified path is not a directory: " + dir);
			} else {
				parentPath.append("/");
				parentPath.append(dir);

				FTPFile ftpFile = ftpClient.mlistFile(parentPath.toString());
				if (ftpFile == null) {
					ftpClient.makeDirectory(parentPath.toString());
					System.out.println("Directory was successfully created: " + parentPath.toString());
				} else {
					System.out.println(
							"The directory already exists my friend no rework here please: " + parentPath.toString());
				}
			}

		}

	}

	public static void deleteDirecotyRecursive(FTPClient ftpClient, String parentDir, String currentDir)
			throws IOException {
		String dirToList = parentDir;
		if (StringUtils.isNotEmpty(currentDir)) {
			dirToList += "/" + currentDir;
		}

		FTPFile[] subFiles = ftpClient.listFiles(dirToList);

		if (subFiles != null && subFiles.length > 0) {
			for (FTPFile aFile : subFiles) {
				String currentFileName = aFile.getName();
				if (currentFileName.equals(".") || currentFileName.equals("..")) {
					continue;
				}
				StringBuilder pathBuilder = new StringBuilder();
				pathBuilder.append(parentDir);
				pathBuilder.append("/");

				if (StringUtils.isNotEmpty(currentDir)) {
					pathBuilder.append(currentDir);
					pathBuilder.append("/");
				}
				pathBuilder.append(currentFileName);
				String filePath = pathBuilder.toString();

				if (aFile.isDirectory()) {
					// remove the sub directory
					deleteDirecotyRecursive(ftpClient, dirToList, currentFileName);
				} else {
					// delete the file
					boolean deleted = ftpClient.deleteFile(filePath);
					if (deleted) {
						System.out.println("DELETED the file: " + filePath);
					} else {
						System.out.println("CANNOT delete the file: " + filePath);
					}
				}
			}
		}

		// finally, remove the directory itself
		boolean removed = ftpClient.removeDirectory(dirToList);
		if (removed) {
			System.out.println("REMOVED the directory: " + dirToList);
		} else {
			System.out.println("CANNOT remove the directory: " + dirToList);
		}

	}

}
