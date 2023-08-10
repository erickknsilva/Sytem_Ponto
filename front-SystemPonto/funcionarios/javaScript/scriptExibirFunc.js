const urlParams = new URLSearchParams(window.location.search);
const matricula = urlParams.get('matricula');



function preencherHtml(funcionario) {

    document.getElementById('matricula').value = funcionario.matricula;
    document.getElementById('nome').value = funcionario.nome;
    document.getElementById('sobrenome').value = funcionario.sobrenome;
    document.getElementById('departamento').value = funcionario.departamento.id;
    document.getElementById('email').value = funcionario.email;
    document.getElementById('salario').value = funcionario.salario;
    document.getElementById('cargaDiaria').value = formataHora(funcionario.cargaDiaria)
    document.getElementById('cargaSemanal').value = funcionario.cargaSemanal
    document.getElementById('cargaMensal').value = funcionario.cargaMensal
    document.getElementById('tipoContrato').value = funcionario.tipoContrato
    document.getElementById('dataEntrada').value = funcionario.dataEntrada

}

async function atualizarFuncionario(funcionario) {

    exibirConfirmacao(`Você realmente deseja alterar este funcionário?\nLembre-se essa é uma ação permanente.`)
        .then(async (result) => {

            if (result.isConfirmed) {
                try {
                    const response = await fetch(`http://localhost:8080/funcionarios/atualizar/${matricula}`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(funcionario)
                    });


                    if (response.ok) {
                        console.log('Funcionário atualizado com sucesso');
                        mensagemDeSucesso('Funcionario alterado com sucesso.');
                        setInterval(() => {
                            window.location.href = 'listarFuncionario.html';

                        }, 2000);

                    } else if (response.status === 400) {
                        const errors = await response.json();
                        console.log('Erros de validação:', errors);

                        mensagemErro(errors);

                    } else {
                        console.error('Falha ao atualizar o funcionário');
                    }
                } catch (error) {
                    console.error('Ocorreu um erro', error);
                }
            }
        });
}

function mensagemDeErro(errors) {
    // Exiba as mensagens de erro na tela HTML
    for (const field in errors) {
        const errorMessage = errors[field].join(', ');
        const errorElement = document.getElementById(`${field}-error`);
        if (errorElement) {
            errorElement.textContent = errorMessage;
        }
    }

    // Crie uma nova lista com apenas as mensagens de erro
    const errorMessages = Object.values(errors).flat();
    const errorMessageWithLineBreaks = errorMessages.join('<br>');

    // Exiba as mensagens de erro usando o SweetAlert2
    Swal.fire({
        icon: 'error',
        title: 'Erros de validação',
        text: errorMessages.join('\n'),
        position: 'top-end', // Define a posição do alerta no canto superior direito
        toast: true, // Define o estilo de alerta como toast
        showConfirmButton: false, // Remove o botão de confirmação
        timer: 5000 // Define o tempo de exibição do alerta em milissegundos (5 segundos)
    });

    return errorMessages;
}



function mensagemSucesso() {
    return Swal.fire({
        icon: 'success',
        title: 'Funcionario atualizado',
        text: 'Funcionario atualizado com sucesso.',
        position: 'top-end', // Define a posição do alerta no canto superior direito
        toast: true, // Define o estilo de alerta como toast
        showConfirmButton: false, // Remove o botão de confirmação
        timer: 5000 // Define o tempo de exibição do alerta em milissegundos (5 segundos)
    });

}

function formataHora(hora) {
    const formatter = hora.split(':');
    const hours = formatter[0];
    const minuto = formatter[1];
    return `${hours}:${minuto}`;
}


fetch(`http://localhost:8080/funcionarios/${matricula}`)
    .then(response => response.json())
    .then(funcionario => {
        preencherHtml(funcionario);
    })
    .catch(error => {
        console.error('Falha ao obter os dados do funcionário', error);

    })   // Manipular o envio do formulário

const form = document.getElementById('editarFuncionarioForm');
form.addEventListener('submit', (event) => {
    event.preventDefault();

    const funcionario = {
        nome: document.getElementById('nome').value,
        sobrenome: document.getElementById('sobrenome').value,
        email: document.getElementById('email').value,
        // Obtenha os valores dos demais campos do formulário conforme necessário
        departamento: parseInt(document.getElementById('departamento').value),
        salario: document.getElementById('salario').value,
        cargaDiaria: document.getElementById('cargaDiaria').value,
        cargaSemanal: document.getElementById('cargaSemanal').value,
        cargaMensal: document.getElementById('cargaMensal').value,
        tipoContrato: document.getElementById('tipoContrato').value,
        dataEntrada: document.getElementById('dataEntrada').value
    };

    atualizarFuncionario(funcionario);
});



