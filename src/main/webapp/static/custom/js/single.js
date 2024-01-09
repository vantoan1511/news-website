$(document).ready(() => {
})

const handleReviewSubmitButtonClick = (event, formSelector) => {
    event.preventDefault();
    let data = getFormData(formSelector);
    console.log('Review >> ', data)
    /*handlePostRequest('/api/v1/reviews', data, () => {
        location.reload()
    }, (xhr, status, error) => {
        showBottomErrorToast('Có lỗi xảy ra', 2000)
    })*/
}