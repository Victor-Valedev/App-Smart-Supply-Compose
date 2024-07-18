package com.victor.smartsupplyapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.victor.smartsupplyapp.ui.theme.Orange
import com.victor.smartsupplyapp.ui.theme.SmartSupplyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartSupplyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppSmartSupply()
                }
            }
        }
    }
}

@Composable
fun AppSmartSupply() {

    var valorGasolina by remember { mutableStateOf("") }
    var valorAlcool by remember { mutableStateOf("") }
    var buttonIsClicked by remember { mutableStateOf(false) }
    var isDisplayDialog by remember { mutableStateOf(false) }
    var resultado by remember { mutableStateOf("") }
    var corResultado by remember { mutableStateOf(Color.Black) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(color = Color(0xF10C373D))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_icon),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .offset(y = (-40).dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ÁLCOOL OU GASOLINA?",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = valorGasolina,
                onValueChange = {
                    valorGasolina = it
                    buttonIsClicked = false
                    resultado = ""
                },
                label = {
                    Text(
                        text = "Gasolina (preço por litro):",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    )
                }
            )
            TextField(
                value = valorAlcool,
                onValueChange = {
                    valorAlcool = it
                    buttonIsClicked = false
                    resultado = ""
                },
                label = {
                    Text(
                        text = "Álcool (preço por litro):",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    )
                }
            )

            Button(
                onClick = {
                    buttonIsClicked = true

                    if (valorGasolina.isNotEmpty() && valorAlcool.isNotEmpty()) {
                        val melhorGasolina = valorAlcool.toDouble() / valorGasolina.toDouble() > 0.7

                        if (melhorGasolina) {
                            resultado = "É melhor optar por Gasolina"
                            corResultado = Color.Red
                        } else {
                            resultado = "É melhor optar por Álcool"
                            corResultado = Color.Green
                        }
                    } else {
                        Toast.makeText(context, "Preencha os campos", Toast.LENGTH_SHORT).show()
                        buttonIsClicked = false
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Orange),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "Calcular",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            if (buttonIsClicked && resultado.isNotEmpty()) {
                Text(
                    text = resultado,
                    style = TextStyle(
                        color = corResultado,
                        fontSize = 29.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }


            //Exibir dialog explicativo

            Button(
                onClick = { isDisplayDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = Orange),
                shape = RoundedCornerShape(5.dp)
            )
            {
                Text(
                    text = "Clique aqui para mais infomações sobre o cálculo",
                    style = TextStyle(
                        color = Color.White
                    ))
            }

            if (isDisplayDialog) {
                Dialog(onDismissRequest = { isDisplayDialog = false }) {
                    Column(
                        Modifier
                            .clip(RoundedCornerShape(10))
                            .size(245.dp)
                            .background(Color.White)
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Descrição do Cálculo",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.size(5.dp))

                        Text(text = "Para determinar se é melhor abastecer com álcool ou gasolina, \nnosso aplicativo utiliza um cálculo simples. \nSe o resultado for menor que 0,7, é mais econômico com álcool.\nCaso contrário é melhor optar por Gasolina",
                            style = TextStyle(
                                color = Color.Black
                            ))

                        Spacer(modifier = Modifier.size(10.dp))

                        Button(
                            onClick = { isDisplayDialog = false },
                            colors = ButtonDefaults.buttonColors(containerColor = Orange),
                            shape = RoundedCornerShape(5.dp)
                        )
                        {
                            Text(
                                text = "Entendido",
                                style = TextStyle(
                                    color = Color.White
                                ))
                        }
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun AppPreview() {
    AppSmartSupply()
}