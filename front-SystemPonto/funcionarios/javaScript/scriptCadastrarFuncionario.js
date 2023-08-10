const mensagemSucesso = () => {
    return Swal.fire({
        icon: 'success',
        title: 'Funcionario cadastrado com sucesso.',
        text: 'Funcionario atualizado com sucesso.',
        position: 'top-end', // Define a posição do alerta no canto superior direito
        toast: true, // Define o estilo de alerta como toast
        showConfirmButton: false, // Remove o botão de confirmação
        timer: 5000 // Define o tempo de exibição do alerta em milissegundos (5 segundos)
    });

}

const mensagemErro = (errors) => {
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

    // Exiba as mensagens de erro usando o SweetAlert2
    Swal.fire({
        icon: 'error',
        title: 'Erros de validação',
        text: errorMessages.join(`\n`),
        position: 'top-end', // Define a posição do alerta no canto superior direito
        toast: true, // Define o estilo de alerta como toast
        showConfirmButton: false, // Remove o botão de confirmação
        timer: 5000 // Define o tempo de exibição do alerta em milissegundos (5 segundos)
    });

    return errorMessages;
}

const saveAPI = async (funcionario) => {
    try {

        const response = await fetch("http://localhost:8080/funcionarios", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(funcionario)
        })

        if (response.ok) {
            console.log('Funcionario cadastrado com sucesso.');
            mensagemSucesso('Funcionario cadastrado com sucesso.');
            const funcionario = await response.json();
            setInterval(() => {
                window.location.href = `exibirFuncionario.html?matricula=${funcionario.matricula}`;
            }, 2000);
        } else if (response.status == 400) {
            const errors = await response.json();
            console.log('Falha ao cadastrar funcionario ', errors);
            mensagemErro(errors);
        } else {
            console.log('ocorreu um erro');
        }
    } catch (error) {
        console.error('Ocorreu um erro:', error.message());
    }
}


const form = document.getElementById('cadastrar-form');
form.addEventListener('submit', (event) => {
    event.preventDefault();

    const funcionario = {
        nome: document.getElementById('nome').value,
        sobrenome: document.getElementById('sobrenome').value,
        email: document.getElementById('email').value,
        salario: document.getElementById('salario').value,
        cargaDiaria: document.getElementById('cargaDiaria').value,
        cargaSemanal: document.getElementById('cargaSemanal').value,
        cargaMensal: document.getElementById('cargaMensal').value,
        tipoContrato: document.getElementById('tipoContrato').value,
        dataEntrada: document.getElementById('dataEntrada').value,
        departamento: document.getElementById('departamento').value,
    };
    saveAPI(funcionario);




});

