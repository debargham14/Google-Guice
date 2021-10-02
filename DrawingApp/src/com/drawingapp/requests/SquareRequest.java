package com.drawingapp.requests;

import com.drawingapp.services.DrawShape;

public class SquareRequest {
	DrawShape d;
	
	public SquareRequest (DrawShape d) {
		this.d = d;
	}
	
	public void makeRquest () {
		d.draw();
	}
}
