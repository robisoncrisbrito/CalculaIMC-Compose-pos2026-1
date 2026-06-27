package br.edu.utfpr.calculaimc_compose_pos2026_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.utfpr.calculaimc_compose_pos2026_1.ui.theme.CalculaIMCComposepos20261Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculaIMCComposepos20261Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculaIMCScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CalculaIMCScreen( modifier: Modifier = Modifier) {

    var peso by rememberSaveable { mutableStateOf( "" ) }
    var altura by rememberSaveable { mutableStateOf( "" ) }
    var resultado by rememberSaveable { mutableStateOf( "0.00" ) }

    val calcularIMC = {
        val pesoDouble = peso.toDoubleOrNull() ?: 0.0
        val alturaDouble = altura.toDoubleOrNull() ?: 0.0

        if ( pesoDouble != 0.0 && alturaDouble != 0.0 ) {
            val imc = pesoDouble / (alturaDouble * alturaDouble)
            resultado = "%.2f".format(imc)
        }
    }


    Column(
        modifier = modifier
    ) {

        OutlinedTextField(
            value = peso,
            onValueChange = {peso = it},
            label = {
                Text( "Peso em Kg" )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = altura,
            onValueChange = {altura = it},
            label = {
                Text( "Altura em m" )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

        )

        Text(
            text = "Resultado: ",
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = resultado,
            modifier = Modifier.padding(8.dp)
        )

        Row {
            Button(
                onClick = {calcularIMC()},
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text("Calcular")
            }

            Button(
                onClick = { peso = ""; altura = ""; resultado = "0.00"},
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text("Limpar")
            }
        }



    }
}

@Preview(showBackground = true)
@Composable
fun CalculaIMCScreenPreview() {
    CalculaIMCComposepos20261Theme {
        CalculaIMCScreen()
    }
}