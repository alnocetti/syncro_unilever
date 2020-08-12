package com.next.fmg.syncro.fileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import com.next.fmg.syncro.main.Application;

public class FileManager {

	public FileManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("resource")
	public void localCopy(String file) throws IOException {
		
		File source = new File(Application.DIR_LECTURA_DBF + file);
		
		File dest = new File(Application.DIR_LOCAL_DBF + file);
		
		FileChannel sourceChannel = null;
		FileChannel destChannel = null;
		
		try {
			sourceChannel = new FileInputStream(source).getChannel();
			destChannel = new FileOutputStream(dest).getChannel();
			destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		} finally {
			sourceChannel.close();
			destChannel.close();
		}
		
		System.out.println(file + " copied");
		
	}
	
	
	@SuppressWarnings("resource")
	public void remoteCopy(String file) throws IOException {
		
		File source = new File(Application.DIR_LOCAL_DBF + file);
		
		File dest = new File(Application.DIR_LECTURA_DBF + file);
		
		FileChannel sourceChannel = null;
		FileChannel destChannel = null;
		
		try {
			sourceChannel = new FileInputStream(source).getChannel();
			destChannel = new FileOutputStream(dest).getChannel();
			destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		} finally {
			sourceChannel.close();
			destChannel.close();
		}
		
		System.out.println(file + " copied");
		
	}


}
