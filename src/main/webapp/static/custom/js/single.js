$(document).ready(() => {

})

const handleReplyButton = (self) => {
    let id = $(self).data('item-id');
    let replyText = $('#' + id + ' .description').text()
    console.log('Reply to >> ', replyText)
    $('#reply-text').text(replyText)
    $('input[name=rootId]').attr('value', id);
    $('#reply-to-text').removeClass('hidden')
}

const handleReviewSubmitButton = (event, formSelector) => {
    event.preventDefault();
    let data = getFormData(formSelector);
    console.log('Review >> ', data)
    handlePostRequest('/api/v1/reviews', data, () => {
        showSuccessAlert('Đã đăng bình luận', () => {
            location.reload()
        })
    }, (xhr, status, error) => {
        let message = getResponseTextAsJSON(xhr).message
        $.each(message, (key, value) => {
            console.log('Error: ' + key + ' >> ' + value)
        })
        showBottomErrorToast(message.text, 2000)
    })
}

const handleLoadMoreReviews = (event, id, page, limit) => {
    event.preventDefault();
    let pageInput = $('input[name=page]');
    let limitInput = $('input[name=limit]');
    let $page = parseInt(pageInput.val())
    let $nextPage = $page + 2
    let $limit = limitInput.val()

    let url = '/api/v1/articles/' + id + '/reviews' + '?page=' + $nextPage + '&limit=' + $limit

    $.get(url, function (data) {
        console.log(data.content)
        if (data.last) {
            $('#load-more-review-btn').hide()
        }
        if (data.content.length > 0) {
            data.content.forEach(function (review) {
                cloneAndReplace(review)
            })

            pageInput.attr('value', $nextPage - 1)
        }
    })
}
const cloneAndReplace = (data) => {
    let item = $('.item:first').clone();
    item.attr('id', data.id);
    item.find('.reply-button').attr('data-item-id', data.id)
    item.find('.name').text(data.username);
    let date = new Date(data.createdDate)
    let formattedDate = date.toLocaleDateString('en-US', {month: 'short', day: 'numeric', year: 'numeric'});
    item.find('.time').text(formattedDate);
    item.find('.description').html(data.text);

    $('.comment-list').append(item);
}