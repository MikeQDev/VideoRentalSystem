$('.message a').click(function(){
   ayooo();});



if(document.URL.slice(-1)==='#'){
	ayooo();
}

function ayooo(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
}


	function createAccount(){
		var emailAddr = document.getElementsByTagName("input")[0].value;
		var password = document.getElementsByTagName("input")[1].value;
		var firstName = document.getElementsByTagName("input")[2].value;
		var lastName = document.getElementsByTagName("input")[3].value;
		var phone = document.getElementsByTagName("input")[4].value;
		var streetAddr = document.getElementsByTagName("input")[5].value;
		var city = document.getElementsByTagName("input")[6].value;
		var state = document.getElementsByTagName("input")[7].value;
		var zip = document.getElementsByTagName("input")[8].value;
		var cc = document.getElementsByTagName("input")[9].value;
		var ccSec = document.getElementsByTagName("input")[10].value;
		var planType = document.getElementsByTagName("input")[11].value;
		xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET","registerRequest?email="+emailAddr+"&password="+password+"&fName="+firstName+"&lName="+lastName+"&phone="+phone+"&streetAddr="+streetAddr+"&city="+city+"&state="+state+"&zip="+zip+"&ccn="+cc+"&ccsn="+ccSec+"&planType="+planType, false);
		xmlhttp.send();
		var resp = xmlhttp.responseText;
		if(resp==="yes")
			window.location.replace("/login.html");
		else
			alert("Registration failure: "+resp);

	}
	function login(){
		window.location.replace(window.location+'#'); //<-- it just werkz
		var email = document.getElementsByTagName("input")[12].value;
		var password = document.getElementsByTagName("input")[13].value;
			
		xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET","loginRequest?email="+email+"&password="+password, false);
		xmlhttp.send();

		var resp = xmlhttp.responseText;
		if(resp==="yes")
			window.location.replace("/videos");
		else
			alert('Login failure: '+resp);
		
	}