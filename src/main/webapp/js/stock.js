$("#excelDown").on("click",function(){
	post("/stock/extracion")
})

function post(path) {
	const form = document.createElement('form');
	form.method = 'post';
	form.action = path;
	form.acceptCharset='UTF-8';

	document.body.appendChild(form);
	form.submit();
}