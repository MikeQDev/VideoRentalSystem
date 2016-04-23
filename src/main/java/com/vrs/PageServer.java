package com.vrs;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vrs.database.WebSqlExecutor;
import com.vrs.security.CookieManager;
import com.vrs.security.Hasher;
import com.vrs.security.Miscellaneous;

@RestController
public class PageServer {
	private final DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");

	@RequestMapping("/home")
	public String generateCookie(HttpServletResponse response,
			HttpServletRequest req) {
		return browse(req);
	}

	@RequestMapping("/check")
	public String checkCookie(HttpServletRequest req) {
		if (CookieManager.hasValidLoginCookie(req))
			return browse(req);
		return CookieManager.reauth();
	}

	@RequestMapping("/search")
	public String search(HttpServletRequest req,
			@RequestParam(required = false) String q) {
		if (!CookieManager.hasValidLoginCookie(req))
			return CookieManager.reauth();
		if (q == null)
			return browse(req);
		Map<String, String> resultMap = null;
		try {
			q = Miscellaneous.sanitize(q.toUpperCase());
			ResultSet rS = WebSqlExecutor
					.selectSql("SELECT * FROM MOVIE WHERE UPPER(TITLE) LIKE '%"
							+ q + "%' OR UPPER(DESCRIPTION) LIKE '%" + q + "%'");
			resultMap = new HashMap<String, String>();
			while (rS.next()) {
				resultMap.put(rS.getString(1), rS.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Issue searching DB for movie!";
		}
		StringBuilder sB = new StringBuilder();
		String first = "<!DOCTYPE html> <html lang=\"en\"> <head> <meta charset=\"utf-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> <meta name=\"description\" content=\"\"> <meta name=\"author\" content=\"\"> <title>Thumbnail Gallery - Start Bootstrap Template</title> <link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" rel=\"stylesheet\"> <script src=\"js/search.js\"></script> <style type=\"text/css\"> body { background: #544f4f; color: white; } .thumbnail { position: relative; float: left; background-size: cover; } .thumbnail img{ position: relative; float: left; width: 250x; height: 300px; background-size: cover; }</style> </head><body> <nav class=\"navbar navbar-default\"> <div class=\"container-fluid\"> <a class=\"navbar-brand\" href=\"/browse\"> <img alt=\"Video Rental System\" src=\"index.png\"> </a> <div class=\"navbar-form navbar-right\"> <input id=\"searchBar\" type=\"text\" class=\"form-control\" placeholder=\"Search\"> <button class=\"btn btn-default\" onClick=\"hi()\">Search</button> </div> </div> </nav> <!-- Page Content --> <div class=\"container\"> <div class=\"row\"> <div class=\"col-lg-12\"> <h1 class=\"page-header\">Search results for: "
				+ q + "</h1> </div>";
		for (String s : resultMap.keySet()) {
			sB.append("<div class=\"col-lg-3 col-md-4 col-xs-6 thumb\"> <a class=\"thumbnail\" href=\"/watch?videoId="
					+ s
					+ "\"> <img src=\"content/"
					+ s
					+ "/cover.jpg\"> </a> </div>");
		}
		String last = "</div> <hr> </div> </body> </html>";
		return first + sB.toString() + last;
	}

	@RequestMapping("/browse")
	public String browse(HttpServletRequest req) {
		if (!CookieManager.hasValidLoginCookie(req))
			return CookieManager.reauth();
		return " <html> <head> <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"> <script type=\"text/javascript\" src=\"https://code.jquery.com/jquery-1.10.1.js\"></script> <link rel=\"stylesheet\" type=\"text/css\" href=\"https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css\"> <script type=\"text/javascript\" src=\"https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js\"></script> <script src=\"js/search.js\"></script> <title></title> <script type=\"text/javascript\">//<![CDATA[ $(window).load(function(){ });//]]> </script> <style type=\"text/css\"> body { background: #544f4f; color: white; } </style> </head> <nav class=\"navbar navbar-default\"> <div class=\"container-fluid\"> <a class=\"navbar-brand\" href=\"#\"> <img alt=\"Video Rental System\" src=\"index.png\"> </a> <div class=\"navbar-form navbar-right\"> <div class=\"form-group\"> <input id=\"searchBar\" type=\"text\" class=\"form-control\" placeholder=\"Search\"> </div> <button class=\"btn btn-default\" onClick=\"hi()\">Search</button> </div> </div> </nav> <br><br> <h2>Recently Watched</h2> <div id=\"topC\" class=\"carousel slide\"> <div class=\"carousel-inner\"> <div class=\"item active\"> <div class=\"row\"> "
				+ Misc.buildBrowseRow()
				+ " </div> </div> </div> <a class=\"left carousel-control\" href=\"#topC\" data-slide=\"prev\"><=</a> <a class=\"right carousel-control\" href=\"#topC\" data-slide=\"next\">=></a> </div> <h2>Trending</h2> <div id=\"middleC\" class=\"carousel slide\"> <div class=\"carousel-inner\"> <div class=\"item active\"> <div class=\"row\"> "
				+ Misc.buildBrowseRow()
				+ " </div> </div> </div> <a class=\"left carousel-control\" href=\"#middleC\" data-slide=\"prev\"><=</a> <a class=\"right carousel-control\" href=\"#middleC\" data-slide=\"next\">=></a> </div> <h2>Based on your viewing history</h2> <div id=\"bottomC\" class=\"carousel slide\"> <div class=\"carousel-inner\"> <div class=\"item active\"> <div class=\"row\"> "
				+ Misc.buildBrowseRow()
				+ " </div> </div> </div> <a class=\"left carousel-control\" href=\"#bottomC\" data-slide=\"prev\"><=</a> <a class=\"right carousel-control\" href=\"#bottomC\" data-slide=\"next\">=></a> </div> <h2>New releases</h2> <div id=\"lastC\" class=\"carousel slide\"> <div class=\"carousel-inner\"> <div class=\"item active\"> <div class=\"row\"> "
				+ Misc.buildBrowseRow()
				+ " </div> </div> </div> <a class=\"left carousel-control\" href=\"#lastC\" data-slide=\"prev\"><=</a> <a class=\"right carousel-control\" href=\"#lastC\" data-slide=\"next\">=></a> </div> </body> </html> ";
	}

	@RequestMapping("/loginRequest")
	public String login(@RequestParam String email,
			@RequestParam String password, HttpServletResponse response,
			HttpServletRequest req) {
		boolean success = false;
		try {
			ResultSet rS = WebSqlExecutor
					.selectSql("SELECT PASSWORD FROM VRS_USER_T WHERE EMAIL='"
							+ email.toUpperCase() + "'");
			String hashedPass = "";
			if (rS.next())
				hashedPass = rS.getString(1);
			else
				return "Account doesn't exist";

			if (hashedPass.equals(Hasher.hashPass(password)))
				success = true;
			else
				return "Invalid password";
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (success) {
			Cookie cookie = new Cookie("login", CookieManager.generateCookie());
			cookie.setMaxAge(3600); // set logins to last one hour
			response.addCookie(cookie);
			return "yes";
		}
		return "Error logging in - is the DB server running?";
	}

	@RequestMapping("/registerRequest")
	public String register(@RequestParam String email,
			@RequestParam String password, @RequestParam String fName,
			@RequestParam String lName, @RequestParam String phone,
			@RequestParam String streetAddr, @RequestParam String city,
			@RequestParam String state, @RequestParam String zip,
			@RequestParam String ccn, @RequestParam String ccsn,
			@RequestParam String planType) {
		System.out.println("Attempting to register: " + email);
		if (!email.contains("@"))
			return "Invalid email";
		if (password.length() < 5)
			return "Password too short";
		if (state.length() != 2)
			return "Invalid state";
		if (ccsn.length() > 4)
			return "Invalid cc sec number";

		try {
			phone = phone.replaceAll("-", "");
			phone = phone.replaceAll("(", "");
			phone = phone.replaceAll(")", "");
			ccn = ccn.replaceAll("-", "");
		} catch (Exception x) {

		}
		email = email.toUpperCase().trim();
		boolean success = false;
		try {
			success = WebSqlExecutor
					.insertSql("INSERT INTO VRS_USER_T (eMail, FirstName, LastName, Phone, Password, Street, City, State, Zip, CCNum, CCSecNum, PlanType) VALUES("
							+ "'"
							+ email
							+ "','"
							+ fName
							+ "','"
							+ lName
							+ "','"
							+ phone
							+ "','"
							+ Hasher.hashPass(password)
							+ "','"
							+ streetAddr
							+ "','"
							+ city
							+ "','"
							+ state
							+ "','"
							+ zip
							+ "','"
							+ ccn
							+ "','"
							+ ccsn
							+ "','"
							+ planType + "'" + ")");
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		if (success)
			return "yes";
		return "Issue registering account - email address may already exist";
	}

	@RequestMapping("/watch")
	public String watchPage(@RequestParam String videoId, HttpServletRequest req) {
		if (!CookieManager.hasValidLoginCookie(req))
			return CookieManager.reauth();
		ResultSet rS = null;
		try {
			rS = WebSqlExecutor.selectSql("SELECT * FROM MOVIE WHERE MOVIEID="
					+ videoId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rS == null)
			return "Error grabbing information from DB";
		String title, description, dateReleased, viewCount, ageRating, userRating;
		try {
			rS.next();
			title = rS.getString(2);
			description = rS.getString(3);
			dateReleased = dateFormat.format(rS.getDate(4));
			viewCount = rS.getInt(5) + "";
			ageRating = rS.getString(6);
			userRating = rS.getDouble(7) + "";
		} catch (Exception x) {
			x.printStackTrace();
			return "Error pulling information from DB. Make sure DB is configured correctly and valid video ID was provided.</br></br><br>Detailed info:<br>"
					+ x.getMessage();
		}

		StringBuilder sB = new StringBuilder();
		sB.append("<HTML>\n");
		sB.append("<head>\n");
		sB.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/video.css\">\n");
		sB.append("</head>\n");
		sB.append("<body>\n");
		// sB.append("hello world</br>\n");
		sB.append("<video id=\"video\" controls\n");
		sB.append("preload=\"auto\" width=\"1280\" height=\"720\" poster=\"content/"
				+ videoId + "/videoThumbnail.jpg\">\n");
		// sB.append("<source id = \"ebin\" src=\"http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_2mb.mp4\" type='video/mp4'>\n");
		sB.append("<source id = \"ebin\" src=\"content/" + videoId + "/"
				+ videoId + ".mp4\" type='video/mp4'>\n");
		sB.append("</video>\n");
		sB.append("<h1 id=\"movieTitle\">" + title + "</h1>\n");
		sB.append("<p id=\"movieDesc\">" + description + "</p>\n");
		sB.append("<p id=\"movieRating\">Movie rating: " + userRating
				+ "</p><p id=\"movieRating\">View count: " + viewCount
				+ "</p>\n");
		sB.append("<p id=\"movieRelease\">Release date: " + dateReleased
				+ "</p><p id=\"ageRating\">Age rating: " + ageRating + "</p>\n");
		// sB.append("<button onclick=\"myFunc()\">update</button>\n");
		sB.append("<script>\n");
		// sB.append("function myFunc(){\n");
		// sB.append("var s = \n");
		// sB.append("var video = document.getElementById(\"video\");\n");
		// sB.append("var src = document.createElement(\"source\");\n");
		// sB.append("src.setAttribute(\"src\",s[7].split(\".\")[0]+\".mp4\");\n");
		// sB.append("video.appendChild(src);\n");
		// sB.append("alert(s[7].split(\".\")[0]);\n");
		// sB.append("alert(window.location.href.split('=')[1]);\n");
		// sB.append("}\n");
		sB.append("</script>\n");
		sB.append("</body>\n");
		sB.append("</HTML>\n");
		WebSqlExecutor.updateViews(Integer.parseInt(videoId));
		return sB.toString();
	}

	/**
	 * Don't allow this class to be instantiated
	 */
	private PageServer() {

	}

}
