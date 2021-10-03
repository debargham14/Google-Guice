package com.drawingapp.main;

import com.drawingapp.module.AppModule;
import com.drawingapp.requests.SquareRequest;
import com.drawingapp.services.DrawBigSquare;
import com.drawingapp.services.DrawShape;
import com.google.inject.Guice;
import com.google.inject.Injector;

//class to implement and check dependency injection using Google Guice 
public class GuiceDemo {
	
	private static final String SQUARE_REQ = "SQUARE";
	private static final String BIGSQUARE_REQ = "BIGSQUARE";
	
	private static void sendRequest (String squareReq) {
		if (squareReq.equals(SQUARE_REQ)) {
			//for reducing dependency and induce loose coupling 
			Injector injector = Guice.createInjector(new AppModule());
			SquareRequest request = injector.getInstance (SquareRequest.class);
			request.makeRequest();
		}
		else if (squareReq.equals (BIGSQUARE_REQ)) {
			DrawShape d = new DrawBigSquare ();
			SquareRequest request = new SquareRequest (d);
			request.makeRequest();
		}
	}
	
	public static void main(String[] args) {
		sendRequest (SQUARE_REQ);
		sendRequest (BIGSQUARE_REQ);
	}

}
