package com.example.quiz

object QuizHandler {
    fun getQuestion(): ArrayList<Question> {
        return arrayListOf(
            Question(
                "Ibukota negara Indonesia saat ini adalah...",
                "Surabaya",
                "Jakarta",
                "Nusantara",
                "Yogyakarta",
                "Jakarta"
            ),
            Question(
                "Indonesia terdiri dari berapa provinsi setelah pemekaran Papua?",
                "34",
                "36",
                "38",
                "40",
                "38"
            ),
            Question(
                "Hari Kemerdekaan Indonesia diperingati setiap tanggal...",
                "17 Juni 1945",
                "17 Agustus 1945",
                "17 Agustus 1950",
                "10 November 1945",
                "17 Agustus 1945"
            ),
            Question(
                "Semboyan resmi negara Indonesia yang tertulis pada lambang Garuda adalah...",
                "Bhinneka Tunggal Rasa",
                "Bhinneka Tunggal Bangsa",
                "Bhinneka Tunggal Ika",
                "Bersatu Kita Teguh",
                "Bhinneka Tunggal Ika"
            ),
            Question(
                "Gunung tertinggi di Indonesia adalah...",
                "Gunung Semeru",
                "Gunung Kerinci",
                "Gunung Rinjani",
                "Puncak Jaya",
                "Puncak Jaya"
            )
        )

    }
}