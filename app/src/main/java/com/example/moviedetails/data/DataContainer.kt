package com.example.moviedetails.data

import com.example.moviedetails.R

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
            label_image = R.drawable.avengers,
            label_background_linear = R.drawable.label_background,
            pegi_info = "13+",
            ic_like = R.drawable.ic_like_picked,
            text_genre = "@string/action_adventure_drama",
            first_star_icon = R.drawable.ic_filled_star,
            second_star_icon = R.drawable.ic_filled_star,
            third_star_icon = R.drawable.ic_filled_star,
            fourth_star_icon = R.drawable.ic_filled_star,
            fifth_star_icon = R.drawable.ic_unfilled_star,
            reviews_quantity = "@string/_125_reviews",
            cast = listOf(downewJR, evansC, rufaloM, hemsworthC),


}