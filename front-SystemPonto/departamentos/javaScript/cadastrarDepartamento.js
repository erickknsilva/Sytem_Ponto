
const form = document.getElementById('saveForm');
form.addEventListener('submit', (event) => {
    event.preventDefault();

    const departamento = {

        nome: document.getElementById('nome').value,
        email: document.getElementById('email').value,
        responsavel: document.getElementById('responsavel').value,
        telefone: document.getElementById('telefone').value,

    }
    saveAPI(departamento);

})


const saveAPI = async (departamento) => {

    try {
        const response = await fetch("http://localhost:8080/departamentos/salvar", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(departamento)
        })

        if (response.ok) {
            console.log('Departamento cadastrado com sucesso.');
            mensagemDeSucesso('Funcionario cadastrado com sucesso.');

        } else if (response.status === 400) {
            console.log('Ocorreu um erro ao cadastrar departamento.');
            const errors = await response.json();
            mensagemErro(errors);

        } else {
            console.log('Ocorreu uma falha.');
        }
    } catch (error) {
        console.log('Ocorreu um erro');
    }

}

