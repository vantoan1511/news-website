const handleSelectAllCheckboxClick = (self, selector) => {
    $(selector).prop('checked', self.checked);
}

const handleSingleCheckboxClick = (parentSelector, otherSelector) => {
    $(parentSelector).prop('checked', $(otherSelector + ':checked').length === $(otherSelector).length);
}

const getIds = (selector) => {
    let ids = [];
    ids = $(selector).map(function () {
        return this.id;
    }).get();
    return ids;
}

const handleArticleTrashButtonClick = (event, selector) => {
    event.preventDefault();
    let ids = getIds(selector);
    if (ids.length === 0) {
        showErrorToast('Không có mục nào được chọn', 2000)
        return;
    }
    showWarningAlert(ids.length + ' mục sẽ được chuyển vào thùng rác trước khi bị xóa hoàn toàn', (result) => {
        if (result.isConfirmed)
            ajaxRequest('/api/v1/articles/trash', 'PUT', 'application/json', ids, 'json',
                result => {
                    showSuccessAlert('Đã chuyển ' + ids.length + ' mục vào thùng rác', () => {
                        location.reload()
                    })
                }, error => {
                    showErrorToast('Lỗi', 1500)
                });
    })
}

const handleSingleArticleTrashButtonClick = (event, id) => {
    event.preventDefault();
    showWarningAlert('Mục được chọn sẽ được chuyển vào thùng rác trước khi bị xóa hoàn toàn', (result) => {
        if (result.isConfirmed) {
            ajaxRequest('/api/v1/articles/' + id + '/trash', 'PUT', 'application/json', undefined, 'json',
                (result) => {
                    showSuccessAlert('Đã chuyển 1 mục vào thùng rác', () => {
                        location.reload()
                    })
                }, (error) => {
                    showErrorToast('Lỗi', 1500)
                });
        }
    })
}

const handleSingleArticleDeleteButtonClick = (event, id) => {
    event.preventDefault();
    showWarningAlert('Mục được chọn sẽ bị xóa vĩnh viễn', (result) => {
        if (result.isConfirmed) {
            ajaxRequest('/api/v1/articles/' + id, 'DELETE', 'application/json', undefined, 'json',
                (result) => {
                    showSuccessAlert('Đã xóa 1 mục', () => {
                        location.reload()
                    })
                }, (xhr, status, error) => {
                    showErrorToast(getResponseTextAsJSON(xhr).message, 2500)
                });
        }
    })
}

const handleSingleArticleRestoreButtonClick = (event, id) => {
    event.preventDefault();
    ajaxRequest('/api/v1/articles/' + id + '/restore', 'PUT', undefined, undefined, 'json', result => {
        showSuccessAlert('Đã khôi phục 1 mục', () => {
            location.reload()
        })
    }, (xhr, status, error) => {
        showErrorToast(getResponseTextAsJSON(xhr).message, 2500)
    })
}

const handleArticlesDeleteButtonClick = (event, selector) => {
    event.preventDefault();
    let ids = getIds(selector);
    if (ids.length === 0) {
        showErrorToast('Không có mục nào được chọn', 2000)
        return;
    }
    showWarningAlert(ids.length + ' mục sẽ bị xóa vĩnh viễn', (result) => {
        if (result.isConfirmed) {
            ajaxRequest('/api/v1/articles', 'DELETE', 'application/json', ids, 'json', result => {
                showSuccessAlert(ids.length + ' mục đã được xóa', () => {
                    location.reload()
                })
            }, (xhr, status, error) => {
                showErrorToast(getResponseTextAsJSON(xhr).message, 2500)
            })
        }
    })
}

const handleArticleRefuseButtonClick = (event, id) => {
    event.preventDefault();
    showWarningAlert('Từ chối yêu cầu đăng tải bài viết này?', result => {
        if (result.isConfirmed) {
            handlePostRequest(id, '/api/v1/articles/' + id + '/refuse', undefined, result => {
                showSuccessAlert('Đã từ chối yêu cầu', () => {
                    location.reload()
                })
            }, (xhr, status, error) => {
                showErrorToast(getResponseTextAsJSON(xhr).message, 2000)
            })
        }
    })
}