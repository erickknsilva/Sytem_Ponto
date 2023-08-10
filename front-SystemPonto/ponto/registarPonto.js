const botaoWrapper = document.querySelector('.bt-wrapper');

const registrarPonto = async (matricula) => {
    try {

        const response = await fetch('http://localhost:8080/pontos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(matricula)
        })


        if (response.ok) {
            const mensagem = await response.text();
            mostrarMensagem(mensagem);
            mensagemDeSucesso(mensagem);
            console.log(mensagem);

            botaoWrapper.classList.add("green");


            // Remova a classe "green" do botão após 2 segundos (2000 milissegundos)
            setTimeout(() => {
                botaoWrapper.classList.remove("green");
            }, 2100);

        } else if (response.status === 404) {
            const mensagem = 'Matricula do funcionario não encontrada.';
            mostrarMensagem(mensagem);
            mensagemErroFlex(mensagem, "red");
        } else {
            console.log('Ocorreu um erro');
        }

    } catch (error) {
        console.log('Ocorreu um erro ', error);
    }
}


const mostrarMensagem = (mensagem) => {
    const mensagemElement = document.getElementById('nome-error');
    mensagemElement.textContent = mensagem;
}


const form = document.querySelector('.container');
form.addEventListener('submit', async (event) => {
    event.preventDefault();
    const matricula = document.getElementById('usu').value;




    if (!matricula.trim()) {
        document.getElementById('nome-error').textContent = 'Insira sua matricula';
        mensagemErroFlex('Insira sua matricula', "red");
        return;
    }

    document.getElementById('nome-error').textContent = '';


    await registrarPonto(matricula); // Adicionei "await" para aguardar a conclusão da função assíncrona antes de continuar

    // Limpar o campo de matrícula após o registro bem-sucedido
    document.getElementById('usu').value = '';
});

