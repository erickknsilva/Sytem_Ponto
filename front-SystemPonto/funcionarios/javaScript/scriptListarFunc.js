
async function fetchFuncionarios(page, size) {
    try {
        const response = await fetch(`http://localhost:8080/funcionarios/${page}/${size}`);

        const data = await response.json();
        if (Array.isArray(data)) {
            displayData(data);
        } else if (data && Array.isArray(data.content)) {
            clearTable();
            displayData(data.content);
            updatePagination(data.number, data.totalPages);
        } else {
            console.error('Resposta inválida da API');
        }
    } catch (error) {
        console.error('Ocorreu um erro', error);
    }
}

let currentPage = 0;
const pageSize = 10;

fetchFuncionarios(currentPage, pageSize);

function updatePagination(currentPage, totalPages) {
    const pageNumber = document.querySelector('#pageNumber');
    pageNumber.textContent = `Página ${currentPage + 1} de ${totalPages}`;

    const previousBtn = document.querySelector('#previousBtn');
    previousBtn.disabled = currentPage === 0;
    previousBtn.addEventListener('click', () => {
        if (currentPage > 0) {
            currentPage--;
            fetchFuncionarios(currentPage, pageSize);
        }
    });

    const nextBtn = document.querySelector('#nextBtn');
    nextBtn.disabled = currentPage === totalPages - 1;
    nextBtn.addEventListener('click', () => {
        if (currentPage < totalPages - 1) {
            currentPage++;
            fetchFuncionarios(currentPage, pageSize);
        }
    });
}

function clearTable() {
    const tableBody = document.querySelector('#myTable tbody');
    tableBody.innerHTML = '';
}

function displayData(data) {
    const tableBody = document.querySelector('#myTable tbody');

    data.forEach(element => {
        const row = document.createElement('tr');


        const matricula = document.createElement('td');
        matricula.textContent = element.matricula;
        matricula.style.textAlign = 'center';
        row.appendChild(matricula);

        const nomeSobrenome = element.nome.concat(' ', element.sobrenome);

        const nome = document.createElement('td');
        nome.textContent = nomeSobrenome;
        row.appendChild(nome);

        const departamento = document.createElement('td');
        departamento.textContent = element.departamento.nome;
        row.appendChild(departamento);

        const salario = document.createElement('td');
        salario.textContent = 'R$' + element.salario;
        salario.style.textAlign = 'center';
        row.appendChild(salario);


        const cargaDiaria = document.createElement('td');
        cargaDiaria.textContent = formataHora(element.cargaDiaria);
        cargaDiaria.style.textAlign = 'center';
        row.appendChild(cargaDiaria);

        const tipoContrato = document.createElement('td');
        tipoContrato.textContent = element.tipoContrato;
        row.appendChild(tipoContrato);

        const email = document.createElement('td');
        email.textContent = element.email;
        row.appendChild(email);

        const dataEntrada = document.createElement('td');
        dataEntrada.textContent = formatarData(element.dataEntrada);
        row.appendChild(dataEntrada);


        const editar = element.matricula;
        row.appendChild(editarFuncionario(editar));

        const matriculaExcluir = element.matricula;
        row.appendChild(excluirFuncionario(matriculaExcluir));

        tableBody.appendChild(row);
    });

}


function editarFuncionario(matricula) {
    const editar = document.createElement('td');
    editar.style.textAlign = 'center';

    const inputMatricula = document.createElement('input');
    inputMatricula.type = 'hidden';
    inputMatricula.value = matricula;

    const botaoEditar = document.createElement('button');
    botaoEditar.innerHTML = '<i class="fas fa-solid fa-user-gear"></i>';
    botaoEditar.className = 'botao-editar';

    editar.appendChild(inputMatricula);
    editar.appendChild(botaoEditar);

    botaoEditar.addEventListener('click', async () => {
        const matricula = inputMatricula.value;
        try {

            const response = await fetch(`http://localhost:8080/funcionarios/${matricula}`);
            if (response.ok) {
                const funcionario = await response.json();
                redirecionarPaginaEdicao(funcionario);
            } else {
                console.error('Falha ao obter os dados do funcionário');
            }

        } catch (error) {
            console.error("Ocorreu um erro", error);
        }
    });
    return editar;
}

function redirecionarPaginaEdicao(funcionario) {
    const queryString = new URLSearchParams(funcionario).toString();
    window.location.href = `pagina_edicao.html?${queryString}`;
}


function excluirFuncionario(matricula) {
    const excluir = document.createElement('td');
    excluir.style.textAlign = 'center';

    const inputMatricula = document.createElement('input');
    inputMatricula.type = 'hidden';
    inputMatricula.value = matricula;

    const botaoExcluir = document.createElement('button');
    botaoExcluir.innerHTML = '<i class="fa fa-thin fa-trash "></i>';
    botaoExcluir.className = 'botao-excluir';

    botaoExcluir.addEventListener('click', async () => {
        const matricula = inputMatricula.value;


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
                            const tableRow = botaoExcluir.closest('tr');
                            tableRow.remove();
                        } else {
                            throw new Error('Erro ao excluir funcionário');
                        }
                    } catch (error) {
                        console.error('Ocorreu um erro:', error);
                    }
                }
            });
    });


    excluir.appendChild(inputMatricula);
    excluir.appendChild(botaoExcluir);

    return excluir;
}




