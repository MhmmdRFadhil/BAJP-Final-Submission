package com.ryz.moviecatalogue.utils

import com.ryz.moviecatalogue.R
import com.ryz.moviecatalogue.model.CatalogueEntity

object DataDummy {
    fun getMovie(): List<CatalogueEntity> {
        val movies = ArrayList<CatalogueEntity>()

        movies.add(
            CatalogueEntity(
                0,
                "A Star Is Born",
                "2018",
                "75%",
                "2h 16m",
                "Drama, Romance, Music",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                R.drawable.poster_a_start_is_born
            )
        )

        movies.add(
            CatalogueEntity(
                1,
                "Alita: Battle Angle",
                "2019",
                "72%",
                "2h 2m",
                "Action, Science Fiction, Adventure",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past",
                R.drawable.poster_alita
            )
        )

        movies.add(
            CatalogueEntity(
                2,
                "Aquaman",
                "2018",
                "69%",
                "2h 23m",
                "Action, Adventure, Fantasy",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                R.drawable.poster_aquaman
            )
        )

        movies.add(
            CatalogueEntity(
                3,
                "Bohemian Rhapsody",
                "2018",
                "80%",
                "2h 15m",
                "Music, Drama, History",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                R.drawable.poster_bohemian
            )
        )

        movies.add(
            CatalogueEntity(
                4,
                "Cold Pursuit",
                "2019",
                "57%",
                "1h 59m",
                "Action, Crime, Thriller",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                R.drawable.poster_cold_persuit
            )
        )

        movies.add(
            CatalogueEntity(
                5,
                "Creed II",
                "2018",
                "69%",
                "2h 10m",
                "Drama",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                R.drawable.poster_creed
            )
        )

        movies.add(
            CatalogueEntity(
                6,
                "Fantastic Beast: The Crimes of Grindelwald",
                "2018",
                "69%",
                "2h 14m",
                "Adventure, Fantasy, Drama",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                R.drawable.poster_crimes
            )
        )

        movies.add(
            CatalogueEntity(
                7,
                "Glass",
                "2019",
                "67%",
                "2h 9m",
                "Thriller, Drama, Science Fiction",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                R.drawable.poster_glass
            )
        )

        movies.add(
            CatalogueEntity(
                8,
                "How to Train Your Dragon: The Hidden World",
                "2019",
                "78%",
                "1h 44m",
                "Animation, Family, Adventure",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                R.drawable.poster_how_to_train
            )
        )

        movies.add(
            CatalogueEntity(
                9,
                "Avengers: Infinity Wars",
                "2018",
                "83%",
                "2h 29m",
                "Adventure, Action, Science Fiction",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                R.drawable.poster_infinity_war
            )
        )

        return movies
    }

    fun getTvShow(): List<CatalogueEntity> {
        val tvShow = ArrayList<CatalogueEntity>()

        tvShow.add(
            CatalogueEntity(
                0,
                "Arrow",
                "2012",
                "67%",
                "42m",
                "Crime, Drama, Mystery, Action & Adventure",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                R.drawable.poster_arrow
            )
        )

        tvShow.add(
            CatalogueEntity(
                1,
                "Doom Patrol",
                "2019",
                "77%",
                "49m",
                "Sci-Fi & Fantasy, Drama",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                R.drawable.poster_doom_patrol
            )
        )

        tvShow.add(
            CatalogueEntity(
                2,
                "Dragon Ball",
                "1986",
                "82%",
                "25m",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                R.drawable.poster_dragon_ball
            )
        )

        tvShow.add(
            CatalogueEntity(
                3,
                "Fairy Tail",
                "2009",
                "78%",
                "25m",
                "Action & Adventure, Animation, Comedy, Sci-Fi & Fantasy, Mystery",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                R.drawable.poster_fairytail
            )
        )

        tvShow.add(
            CatalogueEntity(
                4,
                "Family Guy",
                "1999",
                "71%",
                "22m",
                "Animation, Comedy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                R.drawable.poster_family_guy
            )
        )

        tvShow.add(
            CatalogueEntity(
                5,
                "The Flash",
                "2014",
                "78%",
                "44m",
                "Drama, Sci-Fi & Fantasy",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                R.drawable.poster_flash
            )
        )

        tvShow.add(
            CatalogueEntity(
                6,
                "Game of Throne",
                "2011",
                "84%",
                "1h",
                "Sci-Fi & Fantasy, Drama, Action & Adventure",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                R.drawable.poster_god
            )
        )

        tvShow.add(
            CatalogueEntity(
                7,
                "Gotham",
                "2014",
                "76%",
                "43m",
                "Drama, Crime, Sci-Fi & Fantasy",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                R.drawable.poster_gotham
            )
        )

        tvShow.add(
            CatalogueEntity(
                8,
                "Grey's Anatomy",
                "2005",
                "82%",
                "43m",
                "Drama",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                R.drawable.poster_grey_anatomy
            )
        )

        tvShow.add(
            CatalogueEntity(
                9,
                "Hanna",
                "2019",
                "75%",
                "50m",
                "Action & Adventure, Drama",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                R.drawable.poster_hanna
            )
        )

        return tvShow
    }
}