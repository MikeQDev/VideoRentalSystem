package com.vrs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Misc {
	/**
	 * 
	 * @return HTML code for a a carosel containing videos
	 */
	public static String buildBrowseRow() {
		Random r = new Random();
		List<Integer> curList = new ArrayList<Integer>();
		StringBuilder sB = new StringBuilder();
		while (curList.size() != 12) {
			int c = r.nextInt(28) + 1;
			if (c != 6 && !curList.contains(c))
				curList.add(c);
		}
		int x = 0;
		for (Integer i : curList) {
			if (++x == 7)
				sB.append(" </div> </div> <div class=\"item\"> <div class=\"row\"> ");
			sB.append("<div class=\"col-xs-2\"><a href=\"watch?videoId=" + i
					+ "\" class=\"thumbnail\"><img src=\"content/" + i
					+ "/cover.jpg\"></a></div>\n");
		}
		return sB.toString();
	}

	private Misc() {

	}
}
