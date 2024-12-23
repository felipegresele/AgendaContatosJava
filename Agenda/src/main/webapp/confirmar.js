/**
 * 
 */

 function confirmar(idcon) {
	 let resposta =  confirm("Tem certeza que deseja deletar este contato ?")
	 if (resposta === true) {
		 //alert (idcon)
		 window.location.href = "delete?idcon=" + idcon
	 }
 }
 