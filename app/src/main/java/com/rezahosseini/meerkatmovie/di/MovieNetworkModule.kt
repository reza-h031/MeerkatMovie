package com.rezahosseini.meerkatmovie.di

import com.rezahosseini.meerkatmovie.model.network.web.MovieService
import com.rezahosseini.meerkatmovie.model.network.web.WebService
import com.rezahosseini.meerkatmovie.model.network.web.mapper.MovieWebMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieNetworkModule {
    @Provides
    fun provideMovieService(webService: WebService):MovieService{
        return webService.getMovieService()
    }
    @Singleton
    @Provides
    fun provideMovieMapper():MovieWebMapper{
        return MovieWebMapper()
    }
    @Provides
    @Singleton
    fun provideWebService(retrofit: Retrofit): WebService {
        return WebService(retrofit)
    }
}