package com.example.filmyukol.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailFilmu(idFilmu: Long?){
    val viewModel : DetailFilmuViewModel = viewModel()
    viewModel.nactiFilm(idFilmu)

    val film = viewModel.film.collectAsState().value
    if (film != null){
        Column (modifier = Modifier.padding(all = 4.dp)){
            Text(text = if(viewModel.oblibeny.collectAsState(false).value)
            {"Oblíbený"} else {"NEoblíbený"}
            )
            Button(onClick = {viewModel.pridejDoOblibenych()}) {
                Text(text = "Líbí se mi")

            }
            Button(onClick = {viewModel.odeberZOblibenych()}) {
                Text(text = "Nelíbí se mi")

            }
            Image(painter = painterResource(id = film.obrazekRes), contentDescription = "čičina")
            Text(text = film.nazev)
            Text(text = film.jmenoRezisera)
            Text(text = film.popis)

            val context = LocalContext.current

            Row (modifier = Modifier.padding(all = 4.dp)){
                Button(onClick = {
                    val trailer = film.trailerUrl

                    val appIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("vnd.youtube:$trailer")
                    )
                    val webIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(trailer)
                    )
                    try{
                        context.startActivity(appIntent)
                    }
                    catch (ex: ActivityNotFoundException){
                        context.startActivity(webIntent)
                    }
                }) {
                    Text(text = "Trailer")
                }
                Button(onClick = {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(film.csfdUrl)
                        )
                    )
                }) {
                    Text(text = "Web")
                }
            }
        }
    }
}