package com.example.moviedetails.data

import com.example.moviedetails.ui.R

object DataContainer {

    //avengers cast
    private val downewJR = Actor(1, "Robert Downey Jr.", R.drawable.stark)
    private val evansC = Actor(2, "Chris Evans", R.drawable.evans)
    private val rufaloM = Actor(3, "Mark Ruffalo", R.drawable.ruffulo)
    private val hemsworthC = Actor(4, "Chris Hemsworth", R.drawable.hemsword)

    // tenet cast
    private val washingtonJD = Actor(5, "John David Washington")
    private val pattinsonR = Actor(6, "Robert Pattinson")
    private val debickiE = Actor(7, "Elizabeth Debicki")

    // black widow cast
    private val johanssonS = Actor(8, "Scarlett Johansson")
    private val pughF = Actor(9, "Florence Pugh")
    private val weiszR = Actor(10, "Rachel Weisz")

    //wonder woman cast
    private val pascalP = Actor(11, "Pedro Pascal")
    private val gadotG = Actor(12, "Gal Gadot")
    private val nielsenC = Actor(13, "Connie Nielsen")

    private val moviesMap = mapOf(
        1L to Movie(
            id = 1,
            movie_name = "Avengers:End Game",
            label_image = R.drawable.avengers,
            label_background_linear = R.drawable.label_background,
            movie_background_gradient = R.drawable.icon_background,
            movie_background_image = R.drawable.orig,
            pegi_info = "13+",
            ic_like = R.drawable.ic_like,
            text_genre = "Action, Adventure, Drama",
            first_star_icon = R.drawable.ic_filled_star,
            second_star_icon = R.drawable.ic_filled_star,
            third_star_icon = R.drawable.ic_filled_star,
            fourth_star_icon = R.drawable.ic_filled_star,
            fifth_star_icon = R.drawable.ic_unfilled_star,
            rating_bar = 4,
            story_line = "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos actions and restore balance to the universe",
            reviews_quantity = "125 reviews",
            cast = listOf(downewJR, evansC, rufaloM, hemsworthC),
            duration = "137 min"
        ),
        2L to Movie(
            id = 2,
            movie_name = "Tenet",
            label_image = R.drawable.tenet,
            label_background_linear = R.drawable.label_background,
            movie_background_gradient = R.drawable.icon_background,
            movie_background_image = R.drawable.orig,
            pegi_info = "16+",
            ic_like = R.drawable.ic_like_picked,
            text_genre = "Action, Sci-Fi, Thriller",
            first_star_icon = R.drawable.ic_filled_star,
            second_star_icon = R.drawable.ic_filled_star,
            third_star_icon = R.drawable.ic_filled_star,
            fourth_star_icon = R.drawable.ic_filled_star,
            fifth_star_icon = R.drawable.ic_filled_star,
            rating_bar = 5,
            story_line = "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
            reviews_quantity = "98 reviews",
            cast = listOf(washingtonJD, pattinsonR, debickiE),
            duration = "97 min"
        ),
        3L to Movie(
            id = 3,
            movie_name = "Black Widow",
            label_image = R.drawable.widow,
            label_background_linear = R.drawable.label_background,
            movie_background_gradient = R.drawable.icon_background,
            movie_background_image = R.drawable.orig,
            pegi_info = "13+",
            ic_like = R.drawable.ic_like,
            text_genre = "Action, Adventure, Sci-Fi",
            first_star_icon = R.drawable.ic_filled_star,
            second_star_icon = R.drawable.ic_filled_star,
            third_star_icon = R.drawable.ic_filled_star,
            fourth_star_icon = R.drawable.ic_filled_star,
            fifth_star_icon = R.drawable.ic_unfilled_star,
            rating_bar = 4,
            story_line = "Following the events of Captain America: Civil War,Natasha Romanoff finds herself alone and forced to confront a dangerous conspiracy with ties to her past.",
            reviews_quantity = "38 reviews",
            cast = listOf(johanssonS, pughF, weiszR),
            duration = "102 min"

        ),
        4L to Movie(
            id = 4,
            movie_name = "Wonder Woman 1984",
            label_image = R.drawable.wonder,
            label_background_linear = R.drawable.label_background,
            movie_background_gradient = R.drawable.icon_background,
            movie_background_image = R.drawable.orig,
            pegi_info = "13+",
            ic_like = R.drawable.ic_like,
            text_genre = "Action, Adventure, Fantasy",
            first_star_icon = R.drawable.ic_filled_star,
            second_star_icon = R.drawable.ic_filled_star,
            third_star_icon = R.drawable.ic_filled_star,
            fourth_star_icon = R.drawable.ic_filled_star,
            fifth_star_icon = R.drawable.ic_filled_star,
            rating_bar = 5,
            story_line = "In 1984, during the Cold War, Diana Prince comes into conflict with two formidable foes—media businessman Maxwell Lord and friend-turned-enemy Barbara Ann Minerva while reuniting with her love interest Steve Trevor.",
            reviews_quantity = "74 reviews",
            cast = listOf(gadotG, pascalP, nielsenC),
            duration = "120 min"
        )
    )

    fun getMovie(id: Long): Movie = moviesMap[id] ?: error("Cannot find such movie ID=$id")

    fun getAllMovies(): List<Movie> {
        return moviesMap.map { it.value }
    }
}