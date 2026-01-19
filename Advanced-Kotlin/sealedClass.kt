sealed class NetworkState {
    object Loading : NetworkState()
    data class Success(val data: String) : NetworkState()
    data class Error(val message: String) : NetworkState()
}

fun handleNetworkState(state: NetworkState) {
    when (state) {
        is NetworkState.Loading -> println("Loading...")
        is NetworkState.Success -> println("Data received: ${state.data}")
        is NetworkState.Error -> println("Error occurred: ${state.message}")
    }
}

fun main() {
    val loadingState: NetworkState = NetworkState.Loading
    val successState: NetworkState = NetworkState.Success("Kotlin is awesome!")
    val errorState: NetworkState = NetworkState.Error("Network error")

    handleNetworkState(loadingState)
    handleNetworkState(successState)
    handleNetworkState(errorState)
}