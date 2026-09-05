package com.heesoo.my_movie.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.heesoo.my_movie.data.mapper.DiscoverMapper
import com.heesoo.my_movie.data.remote.DiscoverRemoteDataSource
import com.heesoo.my_movie.domain.model.Movie
import kotlinx.coroutines.flow.first

class DiscoverPagingSource(
    private val discoverRemoteDataSource: DiscoverRemoteDataSource,
    private val language: String,
    private val sortBy: String,
    private val includeAdult: Boolean,
    private val includeVideo: Boolean
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: START_PAGE
        return try {
            val response = discoverRemoteDataSource.getDiscover(
                page = page,
                language = language,
                sortBy = sortBy,
                includeAdult = includeAdult,
                includeVideo = includeVideo
            ).first()
            LoadResult.Page(
                data = DiscoverMapper.responseToData(response),
                prevKey = if (page == START_PAGE) null else page - 1,
                nextKey = if (page >= response.totalPages) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val START_PAGE = 1
    }
}
