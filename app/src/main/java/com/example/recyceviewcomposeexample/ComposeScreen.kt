package com.example.recyceviewcomposeexample

sealed class ComposeScreen(val route: String) {
    object home: ComposeScreen("Home")
    object  Detail : ComposeScreen("Details")

}