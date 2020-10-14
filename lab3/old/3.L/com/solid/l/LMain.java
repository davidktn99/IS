package com.solid.l;

import com.solid.l.bad.BadGraphicsService;
import com.solid.l.bad.BadRectangle;
import com.solid.l.bad.BadSquare;

public class LMain {
	public static void main(String[] args) {
		testBadL();
	}

	private static void testBadL() {
		BadRectangle actuallySquare = new BadSquare(20, 30);
		BadGraphicsService badGraphicsService = new BadGraphicsService();

		badGraphicsService.checkForArea(actuallySquare);
	}

}
