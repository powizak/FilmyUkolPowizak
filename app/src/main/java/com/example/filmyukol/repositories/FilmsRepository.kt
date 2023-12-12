package com.example.filmyukol.repositories

import com.example.filmyukol.R

object FilmsRepository {

    class Film(
        val id: Long,
        var nazev: String,
        var popis: String,
        var jmenoRezisera: String,
        var rokPremiery: Int?,
        var herci: List<String>,
        var obrazekRes: Int,
        var trailerUrl : String?,
        var csfdUrl : String?
    ) {
        fun infoOFilmu() {
            println("Název: " + nazev)
            println("Popis: " + popis)
            println("Jméno režiséra: " + jmenoRezisera)
            println("Rok premiéry: " + rokPremiery)
            print("Herci: ")
            herci.forEach {
                print(it + ",")
            }
            println()
            println("---------------------------")
        }

        fun jeNovejsiNez(film2: Film): Boolean {

            return film2.rokPremiery?.let { it > rokPremiery!! } ?: false
        }
    }

    private val filmy : MutableList<Film> =
        listOf(
            Film(
                1,
                "Vykoupení z věznice Shawshank",
                "Mladý bankéř Andy Dufresne (Tim Robbins) je v roce 1947 odsouzen za vraždu své ženy a jejího milence. Přesto, že tento čin popírá, čeká na něj dvojnásobný doživotní trest v obávané věznici Shawshank.",
                "Frank Darabont",
                1994,
                listOf("Tim Robbins", "Morgan Freeman", "Bob Gunton", "William Sadler", "Clancy Brown"),
                R.drawable.ic_shawsank,
                "https://www.youtube.com/watch?v=iorHgfCOhK8&pp=ygUndnlrb3VwZW7DrSB6IHbEm3puaWNlIHNoYXdzaGFuayB0cmFpbGVy",
                "https://www.csfd.cz/film/2294-vykoupeni-z-veznice-shawshank/"
            ),
            Film(
                2,
                "Forrest Gump",
                "Poněkud prostoduchý Forrest Gump, vyzbrojen užitečnými radami své matky, se vydává na pouť životem, který mu připraví nejedno překvapení. ",
                "Robert Zemeckis",
                1994,
                listOf("Tom Hanks", "Robin Wright", "Gary Sinise", "Mykelti Williamson", "Sally Field"),
                R.drawable.ic_forrest,
                "https://www.youtube.com/watch?v=bLvqoHBptjg&pp=ygUUZm9ycmVzdCBndW1wIHRyYWlsZXI%3D",
                "https://www.csfd.cz/film/10135-forrest-gump/"
            ),
            Film(
                3,
                "Zelená míle",
                "Paul Edgecomb se vrací ve vzpomínkách do roku 1935, kdy byl zaměstnán v louisianské věznici jako hlavní dozorce. Tenkrát se tam setkal s výjimečným, byť duchem prostým mužem, který byl obdařen nejen velkým srdcem, ale také nadpozemskými schopnostmi.",
                "Frank Darabont",
                1999,
                listOf(
                    "Tom Hanks",
                    "David Morse",
                    "Bonnie Hunt",
                    "Michael Clarke Duncan",
                    "James Cromwell"
                ),
                R.drawable.ic_zelena_mile,
                "https://www.youtube.com/watch?v=Ki4haFrqSrw&pp=ygUVemVsZW7DoSBtw61sZSB0cmFpbGVy",
                "https://www.csfd.cz/film/2292-zelena-mile/"
            ),
            Film(
                4,
                "Sedm",
                "Dva policisté (Brad Pitt a Morgan Freeman) jsou na stopě geniálního vraha, zodpovědného za sérii děsivých vražd, jejichž oběti spojuje sedm smrtelných hříchů.",
                "David Fincher",
                1995,
                listOf(
                    "Brad Pitt",
                    "Morgan Freeman",
                    "Gwyneth Paltrow",
                    "Richard Roundtree",
                    "R. Lee Ermey"
                ),
                R.drawable.ic_sedm,
                "https://www.youtube.com/watch?v=znmZoVkCjpI&pp=ygUMc2VkbSB0cmFpbGVy",
                "https://www.csfd.cz/film/2671-sedm/"
            ),
            Film(
                5,
                "Přelet nad kukaččím hnízdem",
                "Předlohou k filmu Přelet nad kukaččím hnízdem byl režiséru Miloši Formanovi román Kena Keseyho, který ho psal při působení v „nemocnici pro veterány“.",
                "Miloš Forman",
                1975,
                listOf(
                    "Jack Nicholson",
                    "Louise Fletcher",
                    "William Redfield",
                    "Will Sampson",
                    "Brad Dourif",
                    "Sydney Lassick",
                    "Danny DeVito"
                ),
                R.drawable.ic_prelet,
                "https://www.youtube.com/watch?v=HqPy3QgGvrM&pp=ygUocMWZZWxldCBuYWQga3VrYcSNxI3DrW0gaG7DrXpkZW0gdHJhaWxlcg%3D%3D",
                "https://www.csfd.cz/film/2982-prelet-nad-kukaccim-hnizdem/"
            ),
            Film(
                6,
                "Schindlerův seznam",
                "Předlohou k filmu Přelet nad kukaččím hnízdem byl režiséru Miloši Formanovi román Kena Keseyho, který ho psal při působení v „nemocnici pro veterány“.",
                "Steven Spielberg",
                1993,
                listOf(
                    "Liam Neeson",
                    "Ben Kingsley",
                    "Ralph Fiennes",
                    "Caroline Goodall",
                    "Jonathan Sagall"
                ),
                R.drawable.ic_schindler,
                "https://www.youtube.com/watch?v=gG22XNhtnoY&pp=ygUbc2NoaW5kbGVyxa92IHNlem5hbSB0cmFpbGVy",
                "https://www.csfd.cz/film/8653-schindleruv-seznam/"
            )
        ).toMutableList()

    fun nactiFilmy() = filmy

    fun pridejFilm(film : Film){
        filmy.add(film)
    }

    fun removeFilm(film : Film){
        filmy.remove(film)
    }

    fun nactiFilm(idFilmu: Long?) : Film? =
        filmy.firstOrNull { film -> film.id == idFilmu }

}