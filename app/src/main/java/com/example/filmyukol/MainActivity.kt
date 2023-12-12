package com.example.filmyukol

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filmyukol.detail.DetailFilmu
import com.example.filmyukol.katalog.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HlavniNavigace()
        }
    }
}


@Composable
fun HlavniNavigace(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "katalogFilmu"){
        composable("katalogFilmu"){
            Screen { idFilmu ->
                navController.navigate("detailFilmu/$idFilmu")
            }
        }
        composable("detailFilmu/{idFilmu}", arguments = listOf(navArgument("idFilmu"){
            type = NavType.LongType
        })){
            val idFilmu = it.arguments?.getLong("idFilmu")
            DetailFilmu(idFilmu)
        }
    }
}