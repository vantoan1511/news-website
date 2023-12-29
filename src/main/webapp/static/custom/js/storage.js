$(document).ready(() => {
    $('#select-all').change(function () {
        $('.item-select').prop('checked', this.checked);
    });
    $('.item-select').change(function () {
        $('#select-all').prop('checked', $('.item-select:checked').length === $('.item-select').length)
    });
})

const handleUploadButtonClick = async (e) => {
    e.preventDefault();
    const {value: file} = await Swal.fire({
        title: "Chọn hình ảnh",
        input: "file",
        inputValidator: ((value) => {
            return new Promise((resolve) => {
                if (value !== null) {
                    resolve();
                } else {
                    resolve("Không có file nào được chọn");
                }
            });
        }),
        confirmButtonText: "Tiếp",
        inputAttributes: {
            "accept": "image/*",
            "aria-label": "Upload your profile picture"
        }
    });

    if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            Swal.fire({
                input: 'text',
                inputPlaceholder: 'Tiêu đề',
                inputValue: file.name,
                allowOutsideClick: false,
                imageUrl: e.target.result,
                imageAlt: "An image will be uploaded",
                showCancelButton: true,
                confirmButtonText: 'Tải lên'
            }).then((result) => {
                if (result.isConfirmed) {
                    const titleInput = $('#swal2-input').val();
                    const formData = new FormData();
                    formData.append('file', file);
                    formData.append('title', titleInput);
                    $.ajax({
                        url: '/api/v1/media',
                        type: 'POST',
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: (result) => {
                            showAlert({
                                title: 'Tải lên thành công!',
                                icon: 'success',
                                callback: () => {
                                    location.reload();
                                }
                            })
                        },
                        error: (xhr, status, error) => {
                            showToast({
                                position: 'top-end',
                                title: 'Tải lên thất bại',
                                timer: 2000
                            })
                        }
                    })
                }
            });
        };
        reader.readAsDataURL(file);
    }
}

const handleTrashButtonClick = (event, elements) => {
    event.preventDefault();
    if (elements.length === 0) {
        showErrorToast('Không có mục nào được chọn', 1500)
    } else {
        let ids = [];
        ids = elements.map(function () {
            return this.id;
        }).get();
        ajaxRequest('/api/v1/media', 'DELETE', 'application/json', ids, 'json',
            result => {
                showSuccessAlert('Đã chuyển' + ids.length + ' vào thùng rác')
            }, error => {
                showErrorToast('Lỗi', 1500)
            });
    }
}

const handleImageInspect = (event, url) => {
    event.preventDefault();
    Swal.fire({
        imageUrl: url,
        imageAlt: 'image'
    })
}

const handleCopyToClipboard = (event, url) => {
    event.preventDefault();
    navigator.clipboard.writeText(url).then(r => {
        showToast({
            position: 'top-end',
            text: 'Đã copy url ' + url,
            icon: 'success',
            timer: 1500
        })
    });
}