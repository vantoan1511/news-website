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