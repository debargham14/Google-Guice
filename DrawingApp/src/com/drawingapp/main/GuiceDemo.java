package com.drawingapp.main;

import com.drawingapp.requests.SquareRequest;
import com.drawingapp.services.DrawShape;
import com.drawingapp.services.DrawSquare;

public class GuiceDemo {
	
	private static final String SQUARE_REQ = "SQUARE";
	
	private static void sendRequest (String squareReq) {
		if (squareReq.equals(SQUARE_REQ)) {
			DrawShape d = new DrawSquare();
			SquareRequest request = new SquareRequest (d);
			request.makeRquest();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sendRequest (SQUARE_REQ);
	}

}
