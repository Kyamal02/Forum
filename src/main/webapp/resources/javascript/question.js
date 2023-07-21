function like(answerId) {
    $.ajax({
        url: '/answer/like',
        method: 'post',
        dataType: 'json',
        data: {
            "answerId": answerId,
        },
        success: function (answer) {
            if (answer == null) {
                return;
            }

            document.getElementById('like_' + answerId).textContent = 'Like ' + answer.likes.length;
            document.getElementById('dislike_' + answerId).textContent = 'Dislike ' + answer.dislikes.length;
        },
        error: function (response) {
        }
    })
}

function dislike(answerId) {
    $.ajax({
        url: '/answer/dislike',
        method: 'post',
        dataType: 'json',
        data: {
            "answerId": answerId,
        },
        success: function (answer) {
            if (answer == null) {
                return;
            }

            document.getElementById('like_' + answerId).textContent = 'Like ' + answer.likes.length;
            document.getElementById('dislike_' + answerId).textContent = 'Dislike ' + answer.dislikes.length;
        },
        error: function (response) {
        }
    })
}