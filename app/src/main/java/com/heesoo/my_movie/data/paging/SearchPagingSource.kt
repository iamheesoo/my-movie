package com.heesoo.my_movie.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.heesoo.my_movie.data.constants.NetworkingConstants
import com.heesoo.my_movie.data.mapper.MovieListMapper
import com.heesoo.my_movie.data.remote.SearchRemoteDataSource
import com.heesoo.my_movie.domain.model.Movie
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.first

class SearchPagingSource(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val query: String,
    private val language: String,
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: NetworkingConstants.START_PAGE
        return runCatching {
            searchRemoteDataSource.getSearch(
                query = query,
                language = language,
                page = page
            ).first()
        }.fold(
            onSuccess = { response ->
                LoadResult.Page(
                    data = MovieListMapper.responseToData(response),
                    prevKey = if (page == NetworkingConstants.START_PAGE) null else page - 1,
                    nextKey = if (page >= response.totalPages) null else page + 1
                )
            },
            onFailure = { throwable ->
                if (throwable is CancellationException) throw throwable
                LoadResult.Error(throwable)
            }
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}