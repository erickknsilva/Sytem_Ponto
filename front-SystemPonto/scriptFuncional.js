const exibirConfirmacao = (titulo, mensagem) => {
    return Swal.fire({
        title: titulo,
        html: mensagem,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cancelar',
        customClass: {
            container: 'custom-swal-container',
            title: 'custom-swal-title',
            content: 'custom-swal-content'
        }
    });
}


function mensagemErro(errors) {
    // Exiba as mensagens de erro na tela HTML
    for (const field in errors) {
        const errorMessage = Array.isArray(errors[field]) ? errors[field].join(', ') : errors[field];
        const errorElement = document.getElementById(`${field}-error`);
        if (errorElement) {
            errorElement.textContent = errorMessage;
        }
    }

    // Crie uma nova lista com apenas as mensagens de erro
    const errorMessages = Object.values(errors).flat();

    // Exiba as mensagens de erro usando o SweetAlert2
    // Swal.fire({
    //     icon: 'error',
    //     title: 'Erro ao preencher formulario.',
    //     text: errorMessages.join(`\n`),
    //     position: 'top-end', // Define a posição do alerta no canto superior direito
    //     customClass: {
    //         container: 'custom-swal-error',
    //         icon: 'custom-swal-error-icon',
    //     },
    //     toast: true, // Define o estilo de alerta como toast
    //     showConfirmButton: false, // Remove o botão de confirmação
    //     timer: 5000 // Define o tempo de exibição do alerta em milissegundos (5 segundos)
    // });
    Toastify({
        text: errorMessages.join(`\n`),
        duration: 2000,
        close: true,
        gravity: 'top',
        position: 'right',
        backgroundColor: 'red',
        className: 'custom-toast',
        stopOnFocus: true
    }).showToast();

    return errorMessages;
}



function mensagemErroFlex(message, color) {
    return Toastify({
        text: message,
        duration: 2100,
        close: true,
        gravity: 'top',
        position: 'right',
        backgroundColor: color,
        className: 'custom-toast',
        stopOnFocus: true
    }).showToast();
}


function mensagemDeSucesso(message) {
    return Toastify({
        text: message,
        duration: 2100,
        close: true,
        gravity: 'top',
        position: 'right',
        backgroundColor: 'green',
        className: 'custom-toast',
        stopOnFocus: true
    }).showToast();
}

function formatarData(data) {
    const formatter = data.split('-');
    const dia = formatter[2];
    const mes = formatter[1];
    const ano = formatter[0];
    return `${dia}/${mes}/${ano}`;
}

function formataHora(hora) {
    const formatter = hora.split(':');
    const hours = formatter[0];
    const minuto = formatter[1];
    return `${hours}:${minuto}`;
}