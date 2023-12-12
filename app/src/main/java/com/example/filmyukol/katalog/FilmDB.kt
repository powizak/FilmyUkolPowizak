package com.example.filmyukol.katalog


import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.filmyukol.R
import com.example.filmyukol.repositories.FilmsRepository
import java.lang.Exception

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(kliknutiNaFilm : (Long) -> Unit) {
    val viewModel : FilmDBViewModel = viewModel()
    val seznamFilmu = viewModel.filmy.collectAsState()
    val checked = viewModel.naSirku.collectAsState()

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(text = stringResource(id = R.string.titulek_katalogu))
        Row {
            Checkbox(
                checked = checked.value,
                onCheckedChange = { viewModel.zmenNaSirku() })
            Text(text = stringResource(id = R.string.naSirku))
        }

        if (!viewModel.naSirku.collectAsState().value){
            Column {
                seznamFilmu.value.forEach { film ->
                    ZaznamFilmu(film = film, kliknutiNaFilm)
                }
            }
        }
        else{
            Column(modifier = Modifier.fillMaxWidth()) {
                seznamFilmu.value.forEach {
                    ZaznamFilmuHorizontalne(film = it)
                }
            }
        }
        Row{
            OutlinedTextField(
                value = viewModel.jmenoNovehoFilmu.collectAsState().value,
                onValueChange = { novaHodnota : String->
                    viewModel.jmenoNovehoFilmu.value = novaHodnota
                })
            Text(text = "Jméno filmu")
        }
        Row{
            OutlinedTextField(
                value = viewModel.jmenoNovehoRezisera.collectAsState().value,
                onValueChange = { novaHodnota : String->
                    viewModel.jmenoNovehoRezisera.value = novaHodnota
                })
            Text(text = "Jméno režiséra")
        }
        Row{
            OutlinedTextField(
                value = viewModel.popisNovehoFilmu.collectAsState().value,
                onValueChange = { novaHodnota : String->
                    viewModel.popisNovehoFilmu.value = novaHodnota
                })
            Text(text = "Popis")
        }
        Row {
            OutlinedTextField(value = viewModel.rokPremieryNovehoFilmu.collectAsState().value?.toString()
                ?: "",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { it ->
                    try {
                        viewModel.rokPremieryNovehoFilmu.value = it.toInt()
                    } catch (e: Exception) {
                        Log.e(ContentValues.TAG, "Chybný typ roku premiéry")
                    }
                })
            Text(text = "Rok premiéry")
        }

        Row{
            Button(onClick = {
                viewModel.pridejFilm()
            } ) {
                Text(text = "Přidej")
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZaznamFilmu(film: FilmsRepository.Film, kliknutiNaFilm: (Long) -> Unit) {
    val viewModel : FilmDBViewModel = viewModel()
    Row(modifier = Modifier
        .padding(all = 3.dp)
        .background(color = Color.LightGray)
        .clickable { kliknutiNaFilm(film.id) }) {
        Column(modifier = Modifier
            .padding(all = 3.dp)
            .weight(1f)) {
            Image(painter = painterResource(id = film.obrazekRes), contentDescription = "", modifier = Modifier.size(150.dp) )
        }
        Column(modifier = Modifier
            .padding(all = 4.dp)
            .padding(start = 10.dp)
            .padding(top = 4.dp)
            .weight(3f)) {
            Text(text = film.nazev, fontSize = 5.em)
            Text(text = film.rokPremiery.toString(), fontSize = 3.em)
            Text(text = film.jmenoRezisera, fontSize = 3.em)
            Button(onClick = {
                viewModel.removeFilm(film)
            } ) {
                Text(text = "Delete")
            }
        }
    }
}

@Composable
fun ZaznamFilmuHorizontalne(film : FilmsRepository.Film){
    val viewModel : FilmDBViewModel = viewModel()
    Column (modifier = Modifier
        .padding(all = 3.dp)
        .background(color = Color.LightGray)){
        Image(painter = painterResource(id = film.obrazekRes), contentDescription = "", modifier = Modifier.size(150.dp) )
        Text(text = film.nazev, fontSize = 5.em)
        Button(onClick = {
            viewModel.removeFilm(film)
        } ) {
            Text(text = "Delete")
        }
    }
}





