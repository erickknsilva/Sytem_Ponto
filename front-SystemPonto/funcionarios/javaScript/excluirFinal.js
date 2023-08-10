
const urlParams = new URLSearchParams(window.location.search);
const matricula = urlParams.get('matricula');

const excluirFuncionario = async (matricula) => {

    exibirConfirmacao(`Você realmente deseja excluir este funcionário?\nLembre-se essa é uma ação permanente.`)
        .then(async (result) => {
            if (result.isConfirmed) {
                try {
                    const response = await fetch(`http://localhost:8080/funcionarios/excluir/${matricula}`, {
                        method: 'DELETE'
                    });

                    if (response.ok) {
                        mensagemDeSucesso('Funcionario excluido com sucesso.');
                        // Encontra a linha correspondente na tabela e a remove
                        window.location.href = ' excluirFuncionario.html';
                    }
                    else {
                        throw new Error('Erro ao excluir funcionário');
                    }
                } catch (error) {
                    console.error('Ocorreu um erro:', error);
                }
            }
            else {
                window.location.href = ' excluirFuncionario.html';
            }
        });

}

excluirFuncionario(matricula);


function redirecionarPaginaEdicao(funcionario) {
    const queryString = new URLSearchParams(funcionario).toString();
    window.location.href = `pagina_edicao.html?${queryString}`;
}