package com.ryz.moviecatalogue.utils

import com.ryz.moviecatalogue.data.source.entity.CatalogueEntity
import com.ryz.moviecatalogue.data.source.entity.DetailEntity
import com.ryz.moviecatalogue.data.source.remote.response.movie.GenreMoviesDetail
import com.ryz.moviecatalogue.data.source.remote.response.movie.MoviesDetailResponse
import com.ryz.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.ryz.moviecatalogue.data.source.remote.response.tvshow.GenreTvShowDetail
import com.ryz.moviecatalogue.data.source.remote.response.tvshow.TvShowDetailResponse
import com.ryz.moviecatalogue.data.source.remote.response.tvshow.TvShowResponse

object DataDummy {
    fun getMovie(): List<CatalogueEntity> {
        val movies = ArrayList<CatalogueEntity>()

        movies.add(
            CatalogueEntity(
                0,
                "Venom: Let There Be Carnage",
                "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg"
            )
        )

        movies.add(
            CatalogueEntity(
                1,
                "Alita: Battle Angle",
                "/xRWht48C2V8XNfzvPehyClOvDni.jpg"
            )
        )

        movies.add(
            CatalogueEntity(
                2,
                "Aquaman",
                "/xLPffWMhMj1l50ND3KchMjYoKmE.jpg"
            )
        )

        movies.add(
            CatalogueEntity(
                3,
                "Bohemian Rhapsody",
                "/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg"
            )
        )

        movies.add(
            CatalogueEntity(
                4,
                "Cold Pursuit",
                "/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg"
            )
        )

        movies.add(
            CatalogueEntity(
                5,
                "Creed II",
                "/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg"
            )
        )

        movies.add(
            CatalogueEntity(
                6,
                "Fantastic Beast: The Crimes of Grindelwald",
                "/fMMrl8fD9gRCFJvsx0SuFwkEOop.jpg"
            )
        )

        movies.add(
            CatalogueEntity(
                7,
                "Glass",
                "/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg"
            )
        )

        movies.add(
            CatalogueEntity(
                8,
                "How to Train Your Dragon: The Hidden World",
                "/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg"
            )
        )

        movies.add(
            CatalogueEntity(
                9,
                "Avengers: Infinity Wars",
                "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"
            )
        )

        return movies
    }

    fun getMovieDetail(): DetailEntity {
        return DetailEntity(
            0,
            "Venom: Let There Be Carnage",
            "2021-09-30",
            6.9,
            97,
            listOf("Science Fiction", "Action"),
            "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
            "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg"
        )
    }

    fun getTvShow(): List<CatalogueEntity> {
        val tvShow = ArrayList<CatalogueEntity>()

        tvShow.add(
            CatalogueEntity(
                0,
                "Meet",
                "/9X7FovF5n8NQUHUPJYYfxRlF3yp.jpg"
            )
        )

        tvShow.add(
            CatalogueEntity(
                1,
                "Doom Patrol",
                "/kOAn06LmRCg4YiSStwrGL6UOQ3a.jpg"
            )
        )

        tvShow.add(
            CatalogueEntity(
                2,
                "Dragon Ball",
                "/tZ0jXOeYBWPZ0OWzUhTlYvMF7YR.jpg"
            )
        )

        tvShow.add(
            CatalogueEntity(
                3,
                "Fairy Tail",
                "/jsYTctFnK8ewomnUgcwhmsTkOum.jpg"
            )
        )

        tvShow.add(
            CatalogueEntity(
                4,
                "Family Guy",
                "/9RBeCo8QSaoJLmmuzlwzVH3Hi12.jpg"
            )
        )

        tvShow.add(
            CatalogueEntity(
                5,
                "The Flash",
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"
            )
        )

        tvShow.add(
            CatalogueEntity(
                6,
                "Game of Throne",
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg"
            )
        )

        tvShow.add(
            CatalogueEntity(
                7,
                "Gotham",
                "/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg"
            )
        )

        tvShow.add(
            CatalogueEntity(
                8,
                "Grey's Anatomy",
                "/zPIug5giU8oug6Xes5K1sTfQJxY.jpg"
            )
        )

        tvShow.add(
            CatalogueEntity(
                9,
                "Hanna",
                "/iYUtjx1EN4SVTgxd2TB4cZTGSQb.jpg"
            )
        )

        return tvShow
    }

    fun getTvShowDetail(): DetailEntity {
        return DetailEntity(
            0,
            "Meet",
            "2021-08-23",
            3.2,
            21,
            listOf("Drama", "Soap"),
            "A remake of Zee Sarthak’s Sindura Bindu.\n\nDespite her efforts to provide for her family, Meets disregard for societal gender norms and her nonconformist job as a delivery agent make her an unsuitable girl in the eyes of her family.",
            "/9X7FovF5n8NQUHUPJYYfxRlF3yp.jpg"
        )
    }

    fun getMovieResponse(): List<MoviesResponse> {
        val movies = ArrayList<MoviesResponse>()

        movies.add(
            MoviesResponse(
                0,
                "Venom: Let There Be Carnage",
                "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg"
            )
        )

        movies.add(
            MoviesResponse(
                1,
                "Alita: Battle Angle",
                "/xRWht48C2V8XNfzvPehyClOvDni.jpg"
            )
        )

        movies.add(
            MoviesResponse(
                2,
                "Aquaman",
                "/xLPffWMhMj1l50ND3KchMjYoKmE.jpg"
            )
        )

        movies.add(
            MoviesResponse(
                3,
                "Bohemian Rhapsody",
                "/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg"
            )
        )

        movies.add(
            MoviesResponse(
                4,
                "Cold Pursuit",
                "/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg"
            )
        )

        movies.add(
            MoviesResponse(
                5,
                "Creed II",
                "/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg"
            )
        )

        movies.add(
            MoviesResponse(
                6,
                "Fantastic Beast: The Crimes of Grindelwald",
                "/fMMrl8fD9gRCFJvsx0SuFwkEOop.jpg"
            )
        )

        movies.add(
            MoviesResponse(
                7,
                "Glass",
                "/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg"
            )
        )

        movies.add(
            MoviesResponse(
                8,
                "How to Train Your Dragon: The Hidden World",
                "/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg"
            )
        )

        movies.add(
            MoviesResponse(
                9,
                "Avengers: Infinity Wars",
                "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"
            )
        )

        return movies
    }

    fun getRemoteDetailMovies(): MoviesDetailResponse {
        return MoviesDetailResponse(
            "Venom: Let There Be Carnage",
            genres = listOf(
                GenreMoviesDetail(
                    "Science Fiction",
                    878
                ),
                GenreMoviesDetail(
                    "Action",
                    28
                ),
            ),
            0,
            "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
            97,
            "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
            "2021-09-30",
            6.9
        )
    }

    fun getTvShowResponse(): List<TvShowResponse> {
        val tvShow = ArrayList<TvShowResponse>()

        tvShow.add(
            TvShowResponse(
                0,
                "Meet",
                "/9X7FovF5n8NQUHUPJYYfxRlF3yp.jpg"
            )
        )

        tvShow.add(
            TvShowResponse(
                1,
                "Doom Patrol",
                "/kOAn06LmRCg4YiSStwrGL6UOQ3a.jpg"
            )
        )

        tvShow.add(
            TvShowResponse(
                2,
                "Dragon Ball",
                "/tZ0jXOeYBWPZ0OWzUhTlYvMF7YR.jpg"
            )
        )

        tvShow.add(
            TvShowResponse(
                3,
                "Fairy Tail",
                "/jsYTctFnK8ewomnUgcwhmsTkOum.jpg"
            )
        )

        tvShow.add(
            TvShowResponse(
                4,
                "Family Guy",
                "/9RBeCo8QSaoJLmmuzlwzVH3Hi12.jpg"
            )
        )

        tvShow.add(
            TvShowResponse(
                5,
                "The Flash",
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"
            )
        )

        tvShow.add(
            TvShowResponse(
                6,
                "Game of Throne",
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg"
            )
        )

        tvShow.add(
            TvShowResponse(
                7,
                "Gotham",
                "/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg"
            )
        )

        tvShow.add(
            TvShowResponse(
                8,
                "Grey's Anatomy",
                "/zPIug5giU8oug6Xes5K1sTfQJxY.jpg"
            )
        )

        tvShow.add(
            TvShowResponse(
                9,
                "Hanna",
                "/iYUtjx1EN4SVTgxd2TB4cZTGSQb.jpg"
            )
        )
        return tvShow
    }

    fun getRemoteDetailTvShow(): TvShowDetailResponse {
        return TvShowDetailResponse(
            listOf(
                GenreTvShowDetail(
                    "Drama",
                    18
                ),
                GenreTvShowDetail(
                    "Soap",
                    10766
                )
            ),
            0,
            "2021-08-23",
            "A remake of Zee Sarthak’s Sindura Bindu.\n\nDespite her efforts to provide for her family, Meets disregard for societal gender norms and her nonconformist job as a delivery agent make her an unsuitable girl in the eyes of her family.",
            "/9X7FovF5n8NQUHUPJYYfxRlF3yp.jpg",
            3.2,
            "Meet",
            listOf(21)
        )
    }
}