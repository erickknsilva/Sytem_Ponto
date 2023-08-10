
const preencherForm = (departamento) => {
    document.getElementById('id').value = departamento.id;
    document.getElementById('nome').value = departamento.nome;
    document.getElementById('email').value = departamento.email;
    document.getElementById('responsavel').value = departamento.responsavel;
    document.getElementById('telefone').value = departamento.telefone;
}


const findAllAPI = async (id) => {

    try {

        const response = await fetch(`http://localhost:8080/departamentos/${id}`);

        const departamento = await response.json();
        preencherForm(departamento);
    }
    catch (error) {
        console.log('Ocorreu um erro');
    }
}
const url = new URLSearchParams(window.location.search);
const id = url.get('id');

findAllAPI(id);