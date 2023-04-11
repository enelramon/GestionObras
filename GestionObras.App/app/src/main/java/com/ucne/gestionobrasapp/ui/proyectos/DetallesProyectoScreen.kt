package com.ucne.gestionobrasapp.ui.proyectos



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ucne.gestionobrasapp.ui.theme.Shapes
import kotlinx.coroutines.launch
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.ucne.gestionobrasapp.ui.theme.DEFAULT_PADDING
import com.ucne.gestionobrasapp.util.colorMatrix
import com.ucne.gestionobrasapp.util.navigation.*
import com.ucne.gestionobrasapp.util.times
import com.ucne.gestionobrasapp.util.transform
import java.util.*
import kotlin.math.PI
import kotlin.math.sin

@RequiresApi(Build.VERSION_CODES.S)
fun getRenderEffect(): RenderEffect {
    val blurEffect = RenderEffect
        .createBlurEffect(80f, 80f, Shader.TileMode.MIRROR)

    val alphaMatrix = RenderEffect.createColorFilterEffect(
        ColorMatrixColorFilter(colorMatrix)
    )

    return RenderEffect
        .createChainEffect(alphaMatrix, blurEffect)
}

@Composable
fun DetallesProyectoScreen(navController: NavController) {
    val isMenuExtended = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.padding(20.dp))
        Icon(
            imageVector = Icons.TwoTone.ArrowCircleLeft,
            contentDescription = null,
            tint = Color(0xCDFFA185),
            modifier = Modifier
                .size(50.dp, 50.dp)
                .clickable {
                    scope.launch {
                        navController.navigate(ScreenModuleProyectos.ProyectoList.route)
                    }
                }
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Nombres",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF000000),
                    modifier = Modifier.weight(7f)
                )
                Text(
                    text = "Adelanto",
                    //text = proyecto_Adelanto,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF000000),
                    modifier = Modifier.weight(7f)
                )
                Text(
                    text = "Pago",
                    //text = proyecto_Pago,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF000000),
                    modifier = Modifier.weight(7f)
                )
            }
        }
        val fabAnimationProgress by animateFloatAsState(
            targetValue = if (isMenuExtended.value) 1f else 0f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )

        val clickAnimationProgress by animateFloatAsState(
            targetValue = if (isMenuExtended.value) 1f else 0f,
            animationSpec = tween(
                durationMillis = 400,
                easing = LinearEasing
            )
        )

        val renderEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            getRenderEffect().asComposeRenderEffect()
        } else {
            null
        }

        MainScreen(
            renderEffect = renderEffect,
            fabAnimationProgress = fabAnimationProgress,
            navController = navController,
            clickAnimationProgress = clickAnimationProgress
        ) {
            isMenuExtended.value = isMenuExtended.value.not()
        }
    }

}

@Composable
fun MainScreen(
    renderEffect: androidx.compose.ui.graphics.RenderEffect?,
    fabAnimationProgress: Float = 0f,
    clickAnimationProgress: Float = 0f,
    navController: NavController,
    toggleAnimation: () -> Unit = { }
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 24.dp),
        contentAlignment = Alignment.BottomCenter
    ) {

        Circle(
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            animationProgress = 0.5f
        )

        FabGroup(
            renderEffect = renderEffect,
            animationProgress = fabAnimationProgress,
            navController = navController
        )

        FabGroup(
            renderEffect = null,
            animationProgress = fabAnimationProgress,
            toggleAnimation = toggleAnimation,
            navController = navController
        )

        Circle(
            color = Color.White,
            animationProgress = clickAnimationProgress
        )
    }
}

@Composable
fun Circle(color: Color, animationProgress: Float) {
    val animationValue = sin(PI * animationProgress).toFloat()

    Box(
        modifier = Modifier
            .padding(DEFAULT_PADDING.dp)
            .size(56.dp)
            .scale(2 - animationValue)
            .border(
                width = 2.dp,
                color = color.copy(alpha = color.alpha * animationValue),
                shape = CircleShape
            )
    )
}

@Composable
fun FabGroup(
    animationProgress: Float = 0f,
    renderEffect: androidx.compose.ui.graphics.RenderEffect? = null,
    navController: NavController,
    toggleAnimation: () -> Unit = { }
) {
    val fastOutSlowIn = remember { FastOutSlowInEasing }
    val linear = remember { LinearEasing }

    Box(
        Modifier
            .fillMaxSize()
            .graphicsLayer { this.renderEffect = renderEffect }
            .padding(bottom = DEFAULT_PADDING.dp),
        contentAlignment = Alignment.BottomCenter,
    ) {

        AnimatedFab(
            icon = Icons.Default.StickyNote2,
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 180.dp,
                    ) * fastOutSlowIn.transform(0.1f, 0.9f, animationProgress)
                ),
            onClick = {
                navController.navigate(ScreenModuleNominas.Nominas.route)
            },
            opacity = linear.transform(0.3f, 0.8f, animationProgress),
            backgroundColor = Color.Cyan
        )

        AnimatedFab(
            icon = Icons.Default.Person,
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 72.dp,
                        end = 210.dp
                    ) * fastOutSlowIn.transform(0f, 0.5f, animationProgress)
                ),
            onClick = {
                navController.navigate(ScreenModulePersonas.Personas.route)
            },
            opacity = linear.transform(0.2f, 0.7f, animationProgress),
            backgroundColor = Color.Yellow
        )

        AnimatedFab(
            icon = Icons.Default.PriceChange,
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 88.dp,
                    ) * fastOutSlowIn.transform(0.1f, 0.9f, animationProgress)
                ),
            onClick = {
                navController.navigate(ScreenModuleAdelantos.Adelantos.route)
            },
            opacity = linear.transform(0.3f, 0.8f, animationProgress),
            backgroundColor = Color.Green
        )

        AnimatedFab(
            icon = Icons.Default.LocalAtm,
            modifier = Modifier
                .padding(
                PaddingValues(
                    bottom = 72.dp,
                    start = 210.dp
                ) * fastOutSlowIn.transform(0.2f, 1.0f, animationProgress)
            ),
            onClick = { navController.navigate(ScreenModulePagos.Pagos.route) },
            opacity = linear.transform(0.4f, 0.9f, animationProgress),
            backgroundColor = Color.Blue
        )

        AnimatedFab(
            modifier = Modifier
                .scale(1f - linear.transform(0.5f, 0.85f, animationProgress))
        )

        AnimatedFab(
            icon = Icons.Default.RadioButtonChecked,
            modifier = Modifier
                .rotate(
                    225 * fastOutSlowIn
                        .transform(0.35f, 0.65f, animationProgress)
                ),
            onClick = toggleAnimation,
            backgroundColor = Color.Transparent
        )
    }
}

@Composable
fun AnimatedFab(
    modifier: Modifier,
    icon: ImageVector? = null,
    opacity: Float = 1f,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),
        containerColor = backgroundColor,
        modifier = modifier
            .scale(1.25f)
            .clip(Shapes.extraLarge)
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = Color.White.copy(alpha = opacity)
            )
        }
    }
}