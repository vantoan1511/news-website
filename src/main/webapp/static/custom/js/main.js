$(document).ready(() => {
    const currentPath = location.pathname + location.search
    console.log(currentPath)
    const navLinks = document.querySelectorAll('.nav-link');
    navLinks.forEach(link => {
        const href = link.getAttribute('href');
        if (href === currentPath) {
            link.classList.add('active');
        }
    });
});

function handlePostRequest(id, url, data, successCallback, errorCallback) {
    if (id !== 0) {
        handleRestRequest({
            url: url,
            type: 'PUT',
            data: data,
            successCallback: successCallback,
            errorCallback: errorCallback
        })
    } else {
        handleRestRequest({
            url: url,
            type: 'POST',
            data: data,
            successCallback: successCallback,
            errorCallback: errorCallback
        })
    }
}

function handleRestRequest({url, type, data, successCallback, errorCallback}) {
    ajaxRequest(url, type, 'application/json', data, 'json', successCallback, errorCallback);
}

function ajaxRequest(url, type, contentType, data, dataType, successCallback, errorCallback) {
    $.ajax({
        url: url,
        type: type,
        contentType: contentType,
        data: JSON.stringify(data),
        dataType: dataType,
        success: successCallback,
        error: errorCallback
    });
}

function showToast({position, title, text, icon, callback, timer, timerProgressBar}) {
    showAlert({
        toast: true,
        position: position,
        title: title,
        text: text,
        icon: icon,
        callback: callback,
        timer: timer,
        timerProgressBar: timerProgressBar,
        showConfirmButton: false
    })
}

function showAlert({
                       toast = false,
                       position = 'center',
                       title,
                       text,
                       icon,
                       callback,
                       showCancelButton = false,
                       showConfirmButton = true,
                       confirmButtonText = 'OK',
                       confirmButtonColor = "#3085d6",
                       cancelButtonColor = "#d33",
                       timer = undefined,
                       timerProgressBar = true
                   }) {
    Swal.fire({
        toast: toast,
        position: position,
        title: title,
        text: text,
        timer: timer,
        timerProgressBar: timerProgressBar,
        icon: icon,
        showCancelButton: showCancelButton,
        showConfirmButton: showConfirmButton,
        confirmButtonText: confirmButtonText,
        confirmButtonColor: confirmButtonColor,
        cancelButtonColor: cancelButtonColor
    }).then(callback);
}

