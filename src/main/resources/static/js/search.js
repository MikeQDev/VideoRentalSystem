
function hi(){

var q = document.getElementById('searchBar').value.trim();

if(!(q===""))

	window.location.href='search?q='+q;
}