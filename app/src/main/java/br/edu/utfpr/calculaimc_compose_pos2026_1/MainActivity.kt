package br.edu.utfpr.calculaimc_compose_pos2026_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.utfpr.calculaimc_compose_pos2026_1.model.ImcViewModel
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
fun CalculaIMCScreen(
    modifier: Modifier = Modifier,
    viewModel: ImcViewModel = viewModel()
) {

    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
    ) {

        OutlinedTextField(
            value = viewModel.peso,
            onValueChange = {viewModel.onPesoChange(it)},
            label = {
                Text("Peso em Kg")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = viewModel.altura,
            onValueChange = {viewModel.onAlturaChange(it)},
            label = {
                Text("Altura em m")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )


        if (viewModel.resultado != "0.00") {
            PanelResult(viewModel.resultado)
        }

        PanelButtons(
            calcularIMC = {viewModel.calcularImc()},
            limparTela = {
                viewModel.limparTela()
                focusRequester.requestFocus()
            }
        )

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sobre o Desenvolvedor")
        }
    }
}

@Composable
fun PanelResult(resultado: String) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Resultado: ",
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = resultado,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
private fun PanelButtons(
    calcularIMC: () -> Unit,
    limparTela: () -> Unit
) {
    Row {
        Button(
            onClick = calcularIMC,
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        ) {
            Text("Calcular")
        }

        Button(
            onClick = limparTela,
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            colors = ButtonDefaults
                .buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )

        ) {
            Text("Limpar")
        }
    }
}

@Composable
fun DeveloperScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Desenvolvido por"
        )
        Text(
            text = "posmoveis-pb@utfpr.edu.br",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview
@Composable
private fun DeveloperScreenPreview() {
    CalculaIMCComposepos20261Theme {
        DeveloperScreen()
    }
}



@Preview
@Composable
private fun PanelButtonsPreview() {
    CalculaIMCComposepos20261Theme {
        PanelButtons(
            calcularIMC = {},
            limparTela = {}
        )
    }
}


@Preview
@Composable
private fun PanelResultPreview() {
    CalculaIMCComposepos20261Theme {
        PanelResult("27.56")
    }
}

@Preview(showBackground = true)
@Composable
fun CalculaIMCScreenPreview() {
    CalculaIMCComposepos20261Theme {
        CalculaIMCScreen()
    }
}