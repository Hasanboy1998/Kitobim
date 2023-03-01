package uz.gita.khasanboy_kitobim.data.remote.responses

import uz.gita.khasanboy_kitobim.data.stateResponseData.BaseData

sealed interface GetAllDataResponse {
    object Loading : GetAllDataResponse
    class Error(val message: String) : GetAllDataResponse
    class Success(val data: List<BaseData>) : GetAllDataResponse
}