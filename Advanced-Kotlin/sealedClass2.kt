sealed class ApiResponse {
    object Idle : ApiResponse()
    object Loading : ApiResponse()
    data class Success(val data: List<String>) : ApiResponse()
    data class Failure(val error: String) : ApiResponse()
}

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Data(val items: List<String>) : UiState()
    data class Error(val msg: String) : UiState()
}

fun processApiResponse(response: ApiResponse): UiState {
    return when (response) {
        is ApiResponse.Loading -> UiState.Loading
        is ApiResponse.Idle -> UiState.Idle
        is ApiResponse.Success -> UiState.Data(response.data)
        is ApiResponse.Failure -> UiState.Error(response.error)
    }
}

fun main() {
    val responses = listOf(
        ApiResponse.Loading,
        ApiResponse.Idle,
        ApiResponse.Success(listOf("Item1", "Item2", "Item3")),
        ApiResponse.Failure("Network Error")
    )

    for (response in responses) {
        val uiState = processApiResponse(response)
        when (uiState) {
            is UiState.Idle -> println("UI is idle")
            is UiState.Loading -> println("UI is loading")
            is UiState.Data -> println("UI has data: ${uiState.items}")
            is UiState.Error -> println("UI error: ${uiState.msg}")
        }
    }
}