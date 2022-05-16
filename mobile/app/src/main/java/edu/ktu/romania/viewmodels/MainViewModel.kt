package edu.ktu.romania.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ktu.romania.api.model.Message
import edu.ktu.romania.api.model.Module
import edu.ktu.romania.api.model.User
import edu.ktu.romania.navigation.screens.password
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor()
    : ViewModel(){
    var modules = mutableStateListOf(
        Module(
            name = "Ekonomika",
            id = "ABC1",
            credits = "6"
        ),
        Module(
            name = "Matematika 2",
            id = "ABC2",
            credits = "9"
        ),
        Module(
            name = "Algoritmai ir duomenu strukturos",
            id = "ABC3",
            credits = "6"
        ),
        Module(
            name = "Ekonomika",
            id = "ABC1",
            credits = "6"
        ),
        Module(
            name = "Kuno kultura",
            id = "ABC2",
            credits = "9"
        ),
        Module(
            name = "Marketingas",
            id = "ABC1",
            credits = "6"
        ),
        Module(
            name = "Kompiuterine grafika",
            id = "ABC2",
            credits = "9"
        ),
        Module(
            name = "Matematika 1",
            id = "ABC1",
            credits = "6"
        ),
    )
    var chatMessages = mutableStateListOf(
        Message(
            name = "anonimas",
            content = "Komentaras"
        )
    )
    var users = mutableListOf(
        User(
            name = "romania@ktu.lt",
            password = "ktu123"
        )
    )
    var isLoggedIn = false

    fun AddComment(name: String, message :String){
        chatMessages.add(Message(name = name, content = message))
    }
    fun AddModule(id: String, name: String, credits: String){
        modules.add(Module(name = name, id = id, credits = credits))
    }
    fun deleteModule(module: Module){
        val iterator = modules.iterator()
        while(iterator.hasNext()){
            val mod = iterator.next()
            if(mod == module){
                iterator.remove()
            }
        }
    }
    fun editComment(comment: Message, updatedComment: Message){
        chatMessages.forEachIndexed {index, msg ->
            if(msg == comment){
                chatMessages[index] = updatedComment
            }
        }
    }

    fun logInUser(user: User, callback: () -> Unit, logIn: () -> Unit){
        users.forEach{
            if(it == user){
                isLoggedIn = true
                logIn()
            }
            else{
                callback()
            }
        }
    }
    fun logOutUser(){
        isLoggedIn = false
    }
    fun registerUser(user: User){
        users.add(user)
    }

}