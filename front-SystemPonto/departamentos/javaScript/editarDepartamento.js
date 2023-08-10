const url = new URLSearchParams(window.location.search);
const id = url.get('id');


const preencherForm = (departamento) => {

    document.getElementById('id').value = departamento.id;
    document.getElementById('nome').value = departamento.nome;
    document.getElementById('email').value = departamento.email;
    document.getElementById('responsavel').value = departamento.responsavel;
    document.getElementById('telefone').value = departamento.telefone;
}


const findByIdAPi = async (id) => {

    try {

        const response = await fetch(`http://localhost:8080/departamentos/${id}`);

        if (response.ok) {
            const departamento = await response.json();
            preencherForm(departamento);

        } else {
            console('Ocorreu um erro ao obter funcionario');
        }
    } catch (error) {
        console.log('Ocorreu um erro', error);
    }

}

findByIdAPi(id);

const updateAPI = async (departamento) => {
    const mensagem = `Departamento<br><br>

    <strong>Nome:</strong> ${departamento.nome}<br>
    <strong>Email:</strong> ${departamento.email}<br>
    <strong>Telefone:</strong> ${departamento.telefone}<br>
    <strong>Responsável:</strong> ${departamento.responsavel}`;


    exibirConfirmacao('Você realmente deseja alterar Departamento ?', mensagem)
        .then(async (result) => {
            if (result.isConfirmed) {

                try {

                    const response = await fetch(`http://localhost:8080/departamentos/atualizar/${id}`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(departamento)
                    });

                    if (response.ok) {
                        mensagemDeSucesso('Departamento alterado com sucesso.')
                        const dep = await response.json();
                        setInterval(() => {
                            // window.location.href = 'listarDepartamentos.html';
                            redirecionarPagina(dep);
                        }, 2000);
                    } else if (response.status === 400) {
                        const errors = await response.json();
                        console.log('Erros de validação:', errors);

                        mensagemErro(errors);
                    } else {
                        console.error('Falha ao atualizar o funcionário');
                    }

                } catch (error) {
                    console.log('Ocorreu um erro', error);
                }
            }
        });
}


const formEditar = document.getElementById('editDepartamento');

formEditar.addEventListener('submit', async (event) => {
    event.preventDefault();

    const departamento = {
        nome: document.getElementById('nome').value,
        email: document.getElementById('email').value,
        responsavel: document.getElementById('responsavel').value,
        telefone: document.getElementById('telefone').value,
    }

    updateAPI(departamento);
})


const redirecionarPagina = (departamento) => {

    const queryString = new URLSearchParams(departamento).toString();
    window.location.href = `exibirDepartamento.html?${queryString}`
}