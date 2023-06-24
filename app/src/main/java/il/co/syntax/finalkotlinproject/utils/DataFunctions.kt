package il.co.syntax.finalkotlinproject.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

fun <T> performFetching(remoteDbFetch: suspend () -> Resource<T>): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val fetchResource = remoteDbFetch()

        if (fetchResource.status is Success) {
            emit(Resource.success(fetchResource.status.data!!))
        } else if (fetchResource.status is Error) {
            emit(Resource.error(fetchResource.status.message))
        }
    }
