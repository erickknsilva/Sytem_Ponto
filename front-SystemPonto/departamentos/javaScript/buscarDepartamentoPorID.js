// // const content = document.querySelector('.content');
// // const inputSearch = document.querySelector("input[type='search']")

// // let items = [];

// // inputSearch.oninput = () => {
// //     const searchValue = inputSearch.value.trim().toLowerCase();
// //     content.innerHTML = "";

// //     if (searchValue !== "") {
// //         items
// //             .filter((departamento) => {
// //                 const id = departamento.id.toString().toLowerCase();
// //                 const nome = departamento.nome.toLowerCase();
// //                 const email = departamento.email.toLowerCase();
// //                 const nomeEmail = nome + ' ' + email;
// //                 const telefone = departamento.telefone.toString().toLowerCase();

// //                 return (
// //                     id.includes(searchValue) ||
// //                     nome.includes(searchValue) ||
// //                     email.includes(searchValue) ||
// //                     nomeEmail.includes(searchValue) ||
// //                     departamento.includes(searchValue) ||
// //                     salario.includes(searchValue)
// //                 );
// //             })
// //             .forEach((item) => addHTML(item));
// //     }
// // };

// // function addHTML(departamento) {
// //     const div = document.createElement("a");
// //     div.className = 'link-a';
// //     div.innerHTML = `
// //     <span>ID: ${departamento.id}</span>
// //     <span>Nome: ${departamento.nome} ${departamento.email}</span>
// //     <span>Responsavel: ${departamento.responsavel}</span>
// //     <span>Telefone: ${departamento.telefone}</span>
// //   `;
// //     div.href = `editarDepartamento.html?id=${departamento.id}`;
// //     content.append(div);
// // }

// // const findAll = async () => {
// //     try {
// //         const response = await fetch("http://localhost:8080/departamentos");

// //         const data = await response.json();
// //         items = data;

// //         data.forEach(async (departamento) => {
// //             addHTML(departamento);

// //         })

// //     } catch (error) {
// //         console.log('Ocorreu um erro');
// //     }
// // }

// // findAll();



const content = document.querySelector('.content');
const inputSearch = document.querySelector("input[type='search']");

let items = [];

inputSearch.oninput = () => {
    const searchValue = inputSearch.value.trim().toLowerCase();
    content.innerHTML = "";

    if (searchValue !== "") {
        items
            .filter((departamento) => {
                const id = departamento.id.toString().toLowerCase();
                const nome = departamento.nome.toLowerCase();
                const email = departamento.email.toLowerCase();
                const responsavel = departamento.responsavel.toLowerCase();
                const telefone = departamento.telefone.toString().toLowerCase();

                // Verificar se o campo de pesquisa contém apenas números (ID)
                const isSearchByID = /^\d+$/.test(searchValue);

                if (isSearchByID) {
                    return id === searchValue;
                } else {
                    return (
                        id.includes(searchValue) ||
                        nome.includes(searchValue) ||
                        email.includes(searchValue) ||
                        responsavel.includes(searchValue) ||
                        telefone.includes(searchValue)
                    );
                }
            })
            .forEach((item) => addHTML(item));
    }
};


function addHTML(departamento) {
    const div = document.createElement("a");
    div.className = 'link-a';
    div.innerHTML = `
    <span>ID: ${departamento.id}</span>
    <span>Nome: ${departamento.nome}</span>
    <span>Email:  ${departamento.email}</span>
    <span>Responsavel: ${departamento.responsavel}</span>
    <span>Telefone: ${departamento.telefone}</span>
  `;
    div.href = `exibirDepartamento.html?id=${departamento.id}`;
    content.append(div);
}

const findAll = async () => {
    try {
        const response = await fetch("http://localhost:8080/departamentos");

        const data = await response.json();
        items = data;

        data.forEach(async (departamento) => {
            addHTML(departamento);
        });
    } catch (error) {
        console.log('Ocorreu um erro', error);
    }
}

// Esperar o carregamento do DOM antes de chamar findAll
document.addEventListener("DOMContentLoaded", findAll);
