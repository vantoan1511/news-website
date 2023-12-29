function showSuccessAlert(text, callback) {
    Swal.fire({
        icon: 'success',
        title: 'Thành công',
        text: text,
        allowOutsideClick: false,
    }).then(callback);
}

function showWarningAlert(text, callback) {
    Swal.fire({
        icon: 'warning',
        title: 'Lưu ý',
        text: text,
        allowOutsideClick: false,
        showCancelButton: true,
        cancelButtonText: 'Hủy'
    }).then(callback);
}

function showErrorToast(text, timer) {
    Swal.fire({
        toast: true,
        position: 'top-end',
        icon: 'error',
        showConfirmButton: false,
        text: text,
        timer: timer
    })
}

const getResponseTextAsJSON = (XmlHttpRequest) => {
    return JSON.parse(XmlHttpRequest.responseText);
}