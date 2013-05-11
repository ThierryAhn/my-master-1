function scenario() {
	var element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "Scenario");
	element.setAttribute("size", "30");
	element.setAttribute("maxlength", "30");
	element.setAttribute("style", "margin-left:200px;");
	var sp2 = document.getElementById("scen");
	var parentDiv = sp2.parentNode;
	var br = document.createElement("br");
	parentDiv.insertBefore(element, sp2.nextSibling);
	parentDiv.insertBefore(br, sp2.nextSibling);
}
function dessin() {
	var element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "Dessin");
	element.setAttribute("size", "30");
	element.setAttribute("maxlength", "30");
	element.setAttribute("style", "margin-left:200px;");
	var sp2 = document.getElementById("dess");
	var parentDiv = sp2.parentNode;
	var br = document.createElement("br");
	parentDiv.insertBefore(element, sp2.nextSibling);
	parentDiv.insertBefore(br, sp2.nextSibling);
}
function color() {
	var element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "Couleur");
	element.setAttribute("size", "30");
	element.setAttribute("maxlength", "30");
	element.setAttribute("style", "margin-left:200px;");
	var sp2 = document.getElementById("col");
	var parentDiv = sp2.parentNode;
	var br = document.createElement("br");
	parentDiv.insertBefore(element, sp2.nextSibling);
	parentDiv.insertBefore(br, sp2.nextSibling);
}
function editeur() {
	var element = document.createElement("input");
	element.setAttribute("type", "text");
	element.setAttribute("name", "Editeur");
	element.setAttribute("size", "30");
	element.setAttribute("maxlength", "30");
	element.setAttribute("style", "margin-left:200px;");
	var sp2 = document.getElementById("editor");
	var parentDiv = sp2.parentNode;
	var br = document.createElement("br");
	parentDiv.insertBefore(element, sp2.nextSibling);
	parentDiv.insertBefore(br, sp2.nextSibling);
}
