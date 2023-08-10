
const findAllAPI = async () => {
    try {

        const response = await fetch("http://localhost:8080/departamentos");
        const data = await response.json();
        displayData(data);

    } catch (error) {
        console.error('Ocorreu um erro', error.message);
    }
}

const displayData = (data) => {
    const departamentoSelect = document.querySelector('#departamento');
    data.forEach(departamento => {
        const option = document.createElement('option');
        option.value = departamento.id;
        option.textContent = departamento.nome;

        departamentoSelect.appendChild(option);
    });
}

findAllAPI();