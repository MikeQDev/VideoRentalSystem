$('.message a').click(function(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});




	function createAccount(){
		var emailAddr = document.getElementsByTagName("input")[0].value;
		var password = document.getElementsByTagName("input")[1].value;
		var firstName = document.getElementsByTagName("input")[2].value;
		var lastName = document.getElementsByTagName("input")[3].value;
		var phone = document.getElementsByTagName("input")[4].value;
		var streetAddr = document.getElementsByTagName("input")[5].value;
		var city = document.getElementsByTagName("input")[6].value;
		var zip = document.getElementsByTagName("input")[7].value;
		var state = document.getElementsByTagName("input")[8].value;
		var cc = document.getElementsByTagName("input")[9].value;
		var ccSec = document.getElementsByTagName("input")[10].value;
		var planType = document.getElementsByTagName("input")[11].value;
		alert(emailAddr+':...:'+planType);
	}
	function login(){
		var email = document.getElementsByTagName("input")[12].value;
		var password = document.getElementsByTagName("input")[13].value;

		xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET","memes?email="+email, false);
		xmlhttp.send();
		alert('hi--->'+xmlhttp.responseText);
	}