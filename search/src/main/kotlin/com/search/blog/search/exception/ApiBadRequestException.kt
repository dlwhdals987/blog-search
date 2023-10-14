package com.search.blog.search.exception


class ApiBadRequestException(message: String?) :
    RuntimeException(String.format(MESSAGE_FORMAT, message)) {
    companion object {
        private const val MESSAGE_FORMAT = "올바르지 않은 요청입니다 [message=%s]"
    }
}
