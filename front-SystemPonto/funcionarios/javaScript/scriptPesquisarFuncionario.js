const content = document.querySelector(".content");
const inputSearch = document.querySelector("input[type='search']");

let items = [];

inputSearch.oninput = () => {
    const searchValue = inputSearch.value.trim().toLowerCase();
    content.innerHTML = "";

    if (searchValue !== "") {
        items
            .filter((funcionario) => {
                const matricula = funcionario.matricula.toString().toLowerCase();
                const nome = funcionario.nome.toLowerCase();
                const sobrenome = funcionario.sobrenome.toLowerCase();
                const nomeSobrenome = nome + ' ' + sobrenome;
                const departamento = funcionario.departamento.nome.toLowerCase();
                const salario = funcionario.salario.toString().toLowerCase();
                
                const isSearchByID = /^\d+$/.test(searchValue);
                if (isSearchByID) {
                    return matricula === searchValue;
                } else {
                    return (
                        matricula.includes(searchValue) ||
                        nome.includes(searchValue) ||
                        sobrenome.includes(searchValue) ||
                        nomeSobrenome.includes(searchValue) ||
                        departamento.includes(searchValue) ||
                        salario.includes(searchValue)
                    );
                }
            })
            .forEach((item) => addHTML(item));
    }
};

function addHTML(funcionario) {
    const div = document.createElement("a");
    div.className = 'link-a';
    div.innerHTML = `
    <span>Matrícula: ${funcionario.matricula}</span>
    <span>Nome: ${funcionario.nome} ${funcionario.sobrenome}</span>
    <span>Salário: ${funcionario.salario}</span>
    <span>Departamento: ${funcionario.departamento.nome}</span>
  `;
    div.href = `exibirFuncionario.html?matricula=${funcionario.matricula}`;
    content.append(div);
}

fetch("http://localhost:8080/funcionarios")
    .then((response) => response.json())
    .then((data) => {
        items = data;
        data.forEach((funcionario) => {
            addHTML(funcionario);
        });
    })
    .catch((error) => {
        console.error('Ocorreu um erro', error);
    });
