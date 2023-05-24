/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package edu.mx.ti.dsm.ddi.practica03_200559.presentation

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import edu.mx.ti.dsm.ddi.practica03_200559.R
import edu.mx.ti.dsm.ddi.practica03_200559.presentation.theme.Practica03_200559Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp("Android")
        }
    }
}

@Composable
fun WearApp(greetingName: String) {
    Practica03_200559Theme {
        /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
         * version of LazyColumn for wear devices with some added features. For more information,
         * see d.android.com/wear/compose.
         */
        ImagenText(
            painterResource(id = R.drawable.agile_trello),
            stringResource(id = R.string.hello_world)
        )
    }
}

@Composable
fun ImagenText(
    imagen: Painter,
    texto: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        /*Image(
            painter = imagen,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )*/
        PintarImagen(imagen = painterResource(id = R.drawable.puebla), tamano = Modifier.fillMaxSize())
        var visible by remember { mutableStateOf(false) }
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier.align(Alignment.TopCenter).width(5.dp).height(5.dp) ,
            onClick = {
                visible = !visible

            },
        ){
            PintarTexto(".", Color.Black)
        }

        AnimatedVisibility(
            visible = visible,
            enter = expandVertically(expandFrom = Alignment.Top) { 20 },

            exit = shrinkVertically(animationSpec = tween()) { fullHeight ->
                fullHeight / 2
            }
        ) {
            PintarTexto(texto, Color.White)
        }

    }
}

/*Imagen de fondo*/
@Composable
fun PintarImagen(
    imagen: Painter,
    tamano: Modifier
){
    Image(painter = imagen, contentDescription = null, modifier = tamano)
}

@Composable
fun PintarTexto(texto:String, color: Color = Color.Red) {
    /*Tipo de fuente*/
    val fuente = FontFamily(Font(R.font.kanitbold))
    Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = color,
            text = texto,
            fontFamily = fuente
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}