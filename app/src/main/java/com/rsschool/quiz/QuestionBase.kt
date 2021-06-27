package com.rsschool.quiz

import java.io.Serializable

const val INVALID_VALUE = -1

data class Question(
    val textQuestion: String,
    val answers: Array<Pair<String, Int>>,
    val theme: Int,
    var userAnswer: Int = INVALID_VALUE
) : Serializable

object QuestionBase {
    val questions = arrayOf(
        Question(
            textQuestion = "Выберите верное утверждение",
            answers = arrayOf(
                "1) Кошки не чувствуют сладкого вкуса." to 20,
                "2) Кошки не чувствуют горького вкуса." to 0,
                "3) Кошки не чувствуют солёного вкуса." to 0,
                "4) Кошки не чувствуют острого вкуса." to 0,
                "5) Кошки не чувствуют вкуса плебейской еды. Только кролик по-французски, цыплёнок «маренго» и т.д." to 0,
            ),
            theme = R.style.Theme_Quiz_First
        ),
        Question(
            textQuestion = "Чем можно кормить кошку?",
            answers = arrayOf(
                "1) сырая рыба;" to 0,
                "2) молоко;" to 0,
                "3) свинина;" to 0,
                "4) говядина;" to 20,
                "5) бобовые." to 0,
            ),
            theme = R.style.Theme_Quiz_Second
        ),
        Question(
            textQuestion = "Сколько котов Авраам Линкольн держал в Белом доме?",
            answers = arrayOf(
                "1) одного;" to 0,
                "2) двух;" to 0,
                "3) трёх;" to 0,
                "4) четырёх;" to 20,
                "5) у него были собаки." to 0,
            ),
            theme = R.style.Theme_Quiz_Third
        ),
        Question(
            textQuestion = "Зачем котам вибриссы?",
            answers = arrayOf(
                "1) чтобы выяснить качество молока;" to 0,
                "2) чтобы выяснить, могут ли они протиснуться или нет;" to 20,
                "3) чтобы завоевать даму сердца;" to 0,
                "4) чтобы узнать направление ветра;" to 0,
                "5) для красоты." to 0,
            ),
            theme = R.style.Theme_Quiz_Fourth
        ),
        Question(
            textQuestion = "Все любят кошек.",
            answers = arrayOf(
                "1) Все" to 0,
                "2) Все" to 0,
                "3) Все" to 0,
                "4) Все" to 0,
                "5) Все (правильный ответ)." to 20,
            ),
            theme = R.style.Theme_Quiz_Fifth
        ),
    )

    fun clearResult() {
        questions.forEach {
            it.userAnswer = INVALID_VALUE
        }
    }
}