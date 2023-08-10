

const findAllPageable = async (page, size) => {

    try {

        const response = await fetch(`http://localhost:8080/departamentos/${page}/${size}`);
        const data = await response.json();

        if (Array.isArray(data)) {
            displayData(data);
        }
        else if (data && Array.isArray(data.content)) {
            clearTable();
            displayData(data.content);
            updatePagination(data.number, data.totalPages);
        }

    } catch (error) {
        console.log('Ocorreu um erro ', error);
    }
}

let currentPage = 0;
const pageSize = 10;

findAllPageable(currentPage, pageSize);


const displayData = (data) => {
    const table = document.querySelector('#myTable-dep tbody');


    data.forEach(element => {
        const row = document.createElement('tr');

        const id = document.createElement('td');
        id.textContent = element.id
        row.appendChild(id);

        const nome = document.createElement('td');
        nome.textContent = element.nome;
        row.appendChild(nome);

        const email = document.createElement('td');
        email.textContent = element.email;
        row.appendChild(email);

        const responsavel = document.createElement('td');
        responsavel.textContent = element.responsavel;
        row.appendChild(responsavel);

        const telefone = document.createElement('td');
        telefone.textContent = element.telefone;
        row.appendChild(telefone);

        const idDep = element.id;
        row.appendChild(buttonEditar(idDep));

        row.appendChild(buttomExcluir(idDep));
        {/* <i class="fa-solid fa-hammer-crash"></i> */ }
        table.appendChild(row);
    });

};

function updatePagination(currentPage, totalPages) {
    const pageNumber = document.querySelector('#pageNumber');
    pageNumber.textContent = `Página ${currentPage + 1} de ${totalPages}`;

    const previousBtn = document.querySelector('#previousBtn');
    previousBtn.disabled = currentPage === 0;
    previousBtn.addEventListener('click', () => {
        if (currentPage > 0) {
            currentPage--;
            findAllPageable(currentPage, pageSize);
        }
    });

    const nextBtn = document.querySelector('#nextBtn');
    nextBtn.disabled = currentPage === totalPages - 1;
    nextBtn.addEventListener('click', () => {
        if (currentPage < totalPages - 1) {
            currentPage++;
            findAllPageable(currentPage, pageSize);
        }
    });
}

function clearTable() {
    const tableBody = document.querySelector('#myTable-dep tbody');
    tableBody.innerHTML = '';
}



const buttonEditar = (id) => {
    const botao = document.createElement('td');
    botao.style.textAlign = 'center';

    const inputDep = document.createElement('input');
    inputDep.type = 'hidden';
    inputDep.value = id;


    const btnEditar = document.createElement('button');
    btnEditar.innerHTML = '<i class="fas fa-solid fa-user-gear"></i>';
    btnEditar.className = 'botao-editar';

    botao.appendChild(inputDep);
    botao.appendChild(btnEditar);

    btnEditar.addEventListener('click', async () => {
        const identificao = inputDep.value;

        try {

            const response = await fetch(`http://localhost:8080/departamentos/${identificao}`);

            if (response.ok) {
                const departamento = await response.json();
                redirecionarPaginaEdicao(departamento);
            } else {
                console.error('Ocorreu um erro', error);
            }

        } catch (error) {
            console.log('Ocorreu um erro ', error);
        }
    });

    return botao;
};

const redirecionarPaginaEdicao = (departamento) => {
    const queryString = new URLSearchParams(departamento).toString();
    window.location.href = `editarDepartamento.html?${queryString}`;
}


const buttomExcluir = (id) => {

    const tableAdd = document.createElement('td');
    tableAdd.style.textAlign = 'center';

    const inputDep = document.createElement('input');
    inputDep.type = 'hidden';
    inputDep.value = id;

    const btnExcluir = document.createElement('button');
    btnExcluir.innerHTML = '<i class="fas fa-thin fa-trash"></i>';
    btnExcluir.className = 'botao-excluir';


    tableAdd.appendChild(inputDep);
    tableAdd.appendChild(btnExcluir);

    btnExcluir.addEventListener('click', async () => {
        const id = inputDep.value;



        exibirConfirmacao(`Você realmente deseja excluir este departamento?\nLembre-se essa é uma ação permanente.`)
            .then(async (result) => {

                if (result.isConfirmed) {

                    try {
                        const response = await fetch(`http://localhost:8080/departamentos/excluir/${id}`, {
                            method: 'DELETE',
                        });

                        if (response.ok) {
                            console.log('Departamento excluido com sucesso.');
                            mensagemDeSucesso('Departamento excluido com sucesso.')

                            const rowRemove = btnExcluir.closest('tr');
                            rowRemove.remove();

                        } else if (response.status === 400) {

                            throw new Error('Erro ao excluir funcionário');
                        }

                    } catch (error) {
                        console.log('Ocorreu um erro', error);
                    }


                }
            });
    });//fim do botao

    return tableAdd;
}











