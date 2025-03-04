package com.example.garden.screens.widgets

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.core.Preview
import androidx.camera.core.takePicture
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.garden.R
import com.example.garden.screens.widgets.text.AlertConfirmText
import com.example.garden.screens.widgets.text.AlertDataTextField
import com.example.garden.screens.widgets.text.AlertDismissText
import com.example.garden.screens.widgets.text.AlertTextField
import com.example.garden.screens.widgets.text.AlertTitle
import com.example.garden.ui.theme.DarkGreen
import com.example.garden.ui.theme.FontBlackColor
import com.example.garden.ui.theme.Gray
import com.example.garden.ui.theme.White
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun ImageAlertDialog(
    onDismiss: () -> Unit,
    onConfirmation: (Bitmap) -> Unit,
) {
    var shouldShowCamera by remember { mutableStateOf(false) }
    //var photoBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val cameraExecutor = Executors.newSingleThreadExecutor()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray.copy(0.5f))

    ) {
        if (!shouldShowCamera)
            Popup(
                onDismissRequest = { },
                alignment = Alignment.Center,
                properties = PopupProperties(
                    focusable = true,
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false,
                    excludeFromSystemGesture = true,
                )
            ) {

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20))
                        .fillMaxWidth()
//                        .offset(y = 64.dp)
//                        .shadow(elevation = 4.dp)
                        .background(White)
                        .padding(top = 5.dp, start = 5.dp, end = 5.dp, bottom = 20.dp)
                ) {
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(5))
                                .background(Gray)
                                .size(85.dp, 100.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.image),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(
                                        start = 12.dp,
                                        end = 12.dp,
                                        top = 15.dp,
                                        bottom = 30.dp
                                    ),

                                )
                            Text(
                                text = stringResource(R.string.alert_gallery),
                                color = FontBlackColor,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(vertical = 10.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(5))
                                .background(Gray)
                                .size(85.dp, 100.dp)
                                .clickable {
                                    shouldShowCamera = true
                                }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.camera),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(
                                        start = 12.dp,
                                        end = 12.dp,
                                        top = 15.dp,
                                        bottom = 30.dp
                                    )
                            )
                            Text(
                                text = stringResource(R.string.alert_camera),
                                color = FontBlackColor,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(vertical = 10.dp)
                            )
                        }


                    }

                }
            }
//    if(photoBitmap!=null){
//        Box(modifier = Modifier.fillMaxSize()){
//            Image(
//                bitmap = photoBitmap!!.asImageBitmap(),
//                contentDescription = null
//            )
//        }
//    }
        if (shouldShowCamera)
            CameraView(

                executor = cameraExecutor,
                onImageCaptured =
                {
                    shouldShowCamera = false
//                photoBitmap = it
                    onConfirmation(it)

                    // Log.d("kilo", "saved image")
                },
            )
    }

}


@Composable
fun CameraView(
    executor: Executor,
    onImageCaptured: (Bitmap) -> Unit,
) {

    val lensFacing = CameraSelector.LENS_FACING_BACK
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()

    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )

        preview.setSurfaceProvider(previewView.surfaceProvider)
    }


    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize())

        IconButton(
            modifier = Modifier.padding(bottom = 100.dp),
            onClick = {

                takePhoto(
                    imageCapture = imageCapture,
                    onImageCaptured = onImageCaptured,
                    executor = executor,
                )


            },
            content = {
                Icon(
                    imageVector = Icons.Sharp.AddCircle,
                    contentDescription = "Take picture",
                    tint = White,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(1.dp)
                        .border(1.dp, White, CircleShape)
                )
            }
        )
    }
}

fun takePhoto(
    imageCapture: ImageCapture,
    executor: Executor,
    onImageCaptured: (Bitmap) -> Unit
) {
    //  Log.d("kilo","take")
//    val photoFile = File(
//        outputDirectory,
//        SimpleDateFormat(filenameFormat, Locale.US).format(System.currentTimeMillis()) + ".jpg"
//    )

    // val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
    imageCapture.takePicture(executor,
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                //   Log.d("kilo","captureImage")
                super.onCaptureSuccess(image)
                onImageCaptured(image.toBitmap())
                image.close()
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
            }
        })
    //   imageCapture.takePicture(onPostviewBitmapAvailable = onImageCaptured)
//    imageCapture.takePicture( executor, object: ImageCapture.OnImageSavedCallback {
//        override fun onError(exception: ImageCaptureException) {
//            //Log.e("kilo", "Take photo error:", exception)
//            //onError(exception)
//        }
//
//        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
//
//            val savedUri = Uri.fromFile(photoFile)
//            onImageCaptured(savedUri)
//        }
//    })
}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider =
    suspendCoroutine { continuation ->
        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
            cameraProvider.addListener({
                continuation.resume(cameraProvider.get())
            }, ContextCompat.getMainExecutor(this))
        }
    }


@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun ImagePrev() {
    Box(modifier = Modifier.fillMaxSize()) {
        ImageAlertDialog({}, {})
    }

}
