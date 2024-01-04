$(document).ready(() => {

    //init ckeditor
    CKEDITOR.plugins.addExternal('video', '/ckeditor/video/', 'plugin.js');
    CKEDITOR.replace('content', {
        extraPlugins: 'video'
    });

    NiceSelect.bind(document.getElementById("categoryCode"));
    NiceSelect.bind(document.getElementById("accessCode"));
})

const handlePreviewButtonClick = (event) => {
    event.preventDefault();
    $('#iframe-container').removeClass("hidden");
    $('body').css('overflow', 'hidden');
}

const handlePreviewCloseButtonClick = (event) => {
    event.preventDefault();
    $('#iframe-container').addClass("hidden");
    $("body").css("overflow", "auto");
}

const getArticleDetailsFormData = (formSelector) => {
    let formData = $(formSelector).serializeArray();
    let contentData = CKEDITOR.instances.content.getData();
    let data = {};
    $.each(formData, (i, v) => data[v.name] = v.value);
    data["content"] = contentData;
    return data;
}

const handleArticleSaveButtonClick = (event, formSelector, saveAndClose, saveAndNew) => {
    event.preventDefault();
    let data = getArticleDetailsFormData(formSelector);
    let id = data["id"];
    console.log(id);
    handlePostRequest(id, '/api/v1/articles', data, result => {
        showSuccessAlert('Các thay đổi đã được lưu', () => {
            if (saveAndClose) location.replace('/admin/articles')
            else if (saveAndNew) location.replace('/admin/articles/new')
            else location.replace('/admin/articles/' + result.id)
        })
    }, (xhr, status, error) => {
        showErrorToast(getResponseTextAsJSON(xhr).message, 2000)
    })
}

const handleArticlePublishButtonClick = (event, id) => {
    event.preventDefault();
    handlePostRequest(id, '/api/v1/articles/' + id + '/publish', undefined, () => {
        showSuccessAlert('Đã gửi yêu cầu đăng tải bài viết', () => {
            location.reload()
        })
    }, (xhr, status, error) => {
        showErrorToast(getResponseTextAsJSON(xhr).message, 2000)
    })
}

const handleArticleUnpublishButtonClick = (event, id) => {
    event.preventDefault();
    handlePostRequest(id, '/api/v1/articles/' + id + '/unpublish', undefined, () => {
        showSuccessAlert('Đã hủy đăng tải bài viết', () => {
            location.reload()
        })
    }, (xhr, status, error) => {
        showErrorToast(getResponseTextAsJSON(xhr).message, 2000)
    })
}

const handleArticleApproveButtonClick = (event, id) => {
    event.preventDefault();
    showWarningAlert('Bài viết sẽ được đăng tải công khai?', result => {
        if (result.isConfirmed) {
            handlePostRequest(id, '/api/v1/articles/' + id + '/approve', undefined, () => {
                location.reload()
            }, (xhr, status, error) => {
                showErrorToast(getResponseTextAsJSON(xhr).message, 2000)
            })
        }
    })
}