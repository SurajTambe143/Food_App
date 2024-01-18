package com.example.dummyapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dummyapp.data.remote.api.HomeMainService
import com.example.dummyapp.data.remote.dto.HomeScrollResponse
import com.example.dummyapp.domain.model.entities.remote.homescroll.Store
import com.example.dummyapp.utils.Body
import com.example.dummyapp.utils.getHeaders

class HomeScrollDetailsPagingSource(val homeHomeService: HomeMainService) :
    PagingSource<Int, Store>() {

    override fun getRefreshKey(state: PagingState<Int, Store>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Store> {
        return try {
            val position = params.key ?: 1
            val response = homeHomeService.getHomeScrollDetails(
                getHeaders(),
                Body(
                    33.341658,
                    44.416270,
                    "en",
                    position,
                    "5ab0d991bfe73957dc8ea53d",
                    "5abcd381d761ca635c980349"
                )
            )
            LoadResult.Page(
                data = response.stores?: emptyList(),
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.store_count) null else position + 1
            )
        } catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}