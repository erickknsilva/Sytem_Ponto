
const excluir = async (id) => {

    exibirConfirmacao(`Você realmente deseja excluir este departamento ?\nLembre-se essa é uma ação permanente.`)
        .then(async (result) => {
            if (result.isConfirmed) {
                try {
                    const response = await fetch(`http://localhost:8080/departamentos/excluir/${id}`, {
                        method: 'DELETE'
                    });

                    if (response.ok) {
                        console.log('Excluido com sucesso.')
                        window.location.href = 'excluirDepartamento.html';
                    } else if (response.status === 400) {
                        console.log('Não foi possivel excluir.')
                    }


                } catch (error) {
                    console.log('Ocorreu um error ', error);
                }
            } else {
                window.location.href = 'excluirDepartamento.html';
            }
        });
}

const url = new URLSearchParams(window.location.search);
const id = url.get('id');

excluir(id);




