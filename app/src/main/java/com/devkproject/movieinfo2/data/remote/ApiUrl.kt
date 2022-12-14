package com.devkproject.movieinfo2.data.remote

object ApiUrl {
    private const val API_KEY = "4140785408ae0d33bd7a2220a28fa0e2"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val POSTER_URL = "https://image.tmdb.org/t/p/w342"

    const val NOW_PLAYING_MOVIE_LIST = "movie/now_playing?api_key=$API_KEY&language=ko"
    const val POPULAR_MOVIE_LIST = "movie/popular?api_key=$API_KEY&language=ko"
    const val TOP_RATED_MOVIE_LIST = "movie/top_rated?api_key=$API_KEY&language=ko"
    const val UP_COMING_MOVIE_LIST = "movie/upcoming?api_key=$API_KEY&language=ko"

    const val MOVIE_DETAIL ="movie/{movieId}?api_key=$API_KEY&language=ko"

    const val GENRE_LIST ="genre/movie/list?api_key=$API_KEY&language=ko"
    const val GENRE_MOVIES_BY_ID ="discover/movie?api_key=$API_KEY&language=ko"

    const val SEARCH_MOVIE ="search/movie?api_key=$API_KEY&language=ko&page=1&include_adult=false"

    const val MOVIE_VIDEO ="movie/{movieId}/videos?api_key=$API_KEY&language=ko"

    const val RECOMMENDED_MOVIE ="movie/{movieId}/recommendations?api_key=$API_KEY&language=ko"

    const val MOVIE_CREDIT ="movie/{movieId}/credits?api_key=$API_KEY&language=ko"
    const val ARTIST_DETAIL ="person/{personId}?api_key=$API_KEY&language=ko"

    //TV
    const val TV_POPULAR = "tv/popular?api_key=$API_KEY&language=ko"

    //Youtube
    const val YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v="
    const val YOUTUBE_THUMBNAIL_BASE_URL = "https://img.youtube.com/vi/"
    const val YOUTUBE_THUMBNAIL_URL_JPG = "/0.jpg"

    @JvmStatic
    fun getYoutubeVideoPath(key: String?): String {
        return YOUTUBE_BASE_URL + key
    }

    @JvmStatic
    fun getYoutubeVideoThumbnail(key: String?): String {
        return YOUTUBE_THUMBNAIL_BASE_URL + key + YOUTUBE_THUMBNAIL_URL_JPG
    }
}