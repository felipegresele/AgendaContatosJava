/**
 * 
 */

 function validar() {
	 let nome = frmContato.nome.value
	 let fone = frmContato.fone.value
	 
	 if (nome === "") {
		 //se tiver vazio
		 alert('campo vazio, preencha o campo nome')
		 frmContato.nome.focus()
		 return false //nn ia enviar as informações
	 } else if (fone === "") {
		  alert('campo vazio, preencha o campo telefone')
		  frmContato.fone.focus()
		  return false
	 } else {
		 // se tudo der certo ele vai passar os dados para a camada controller
		 document.forms["frmContato"].submit()
	 }
	 
	
	 
 }
 