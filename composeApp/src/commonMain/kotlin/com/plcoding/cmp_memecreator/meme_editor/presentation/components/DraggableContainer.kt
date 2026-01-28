package com.plcoding.cmp_memecreator.meme_editor.presentation.components

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import cmpmemecreator.composeapp.generated.resources.Res
import com.plcoding.cmp_memecreator.meme_editor.presentation.MemeEditorAction
import com.plcoding.cmp_memecreator.meme_editor.presentation.MemeText
import com.plcoding.cmp_memecreator.meme_editor.presentation.TextBoxInteractionState
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin


@Composable
//the container where we can drag the textBox(es),
fun DraggableContainer(
    children : List<MemeText>,
    textBoxInteractionState: TextBoxInteractionState,
    onChildTransformChanged : (id : String, offset : Offset, rotation: Float,scale: Float) -> Unit,
    onChildClick : (id:String) -> Unit,
    onChildDoubleClick : (id:String) -> Unit,
    onChildTextChange:(id: String, text: String) -> Unit,
    onChildDeleteClick: (String) -> Unit,
    modifier : Modifier = Modifier
){

    //for converting pixel values to dp values
    val density = LocalDensity.current

    BoxWithConstraints(modifier) {
        //we need to know the dimensions the memeTextBox is allowed to spam , we use BoxWithConstaints which has a constainst property for min an d max height
        val parentWidth = constraints.maxWidth;
        val parentHeight  = constraints.maxHeight;

        children.forEach { child ->
            //we have to remember the cachedvalue for each child id
            var childWidth by remember(child.id){
                //we initially start with zero
                mutableStateOf(0)
            }
            var childHeight by remember(child.id){
                mutableStateOf(0)
            }
            //we use a transformableState library listen to rotation gestures , scaling gestures and then give us the new value
            val transformableState  = rememberTransformableState { scaleChange, panChange, rotationChange ->
                val newRotation = child.rotation + rotationChange
                //coreceIn provides min and max values to avoid scaling infinitly

                //the math needed for rotation
                val angle = newRotation * PI.toFloat() / 180f
                val cos = cos(angle)
                val sin = sin(angle)

                val rotatedPanX = panChange.x * cos - panChange.y *sin
                val rotatedPanY = panChange.x * sin + panChange.y * cos

                val newScale = (child.scale * scaleChange).coerceIn(0.5f,2f)

                //we also need to calculate the scale width of the bounding container
                //we take how wide the width actual is * what we've currently scaled
                val scaledWidth = childWidth * child.scale
                val scaledHeight = childHeight * child.scale

                //we also need to constrain the text inside an invisible bounding box to stop it from moving outside
                //we use the absolute value if its negative
                //this formula projects the invisible rectangles edges on the x and y axis
                val visualWidth = abs(scaledWidth * cos) + abs(scaledHeight * sin)
                val visualHeight = abs(scaledWidth * sin) + abs(scaledHeight * cos)

                //we do this since scaling the box also moves it
                val scaledOffsetX =  (scaledWidth - childWidth) / 2
                val scaledOffsetY =  (scaledHeight - childHeight) / 2
                //since rotation also changes the scale

                val rotationOffsetX  = (visualWidth  - scaledWidth) /2
                val rotationOffsetY  = (visualHeight  - scaledHeight) / 2

                //we then use all the calculations above to calculate max and min values of x and y (where our text is allowed to move)

                val minX = scaledOffsetX + rotationOffsetX
                val maxX = parentWidth  - childWidth - scaledOffsetX - rotationOffsetX
                val minY = scaledOffsetY + rotationOffsetY
                val maxY = parentHeight - childHeight - scaledOffsetY - rotationOffsetY


                val newOffset = Offset(
                    x = (child.offsetRatioX * parentWidth + child.scale * rotatedPanX).coerceIn(
                        minimumValue = minOf(minX,maxX),
                        maximumValue = maxOf(minX,maxX)
                    ),
                    y = (child.offsetRatioY * parentHeight + child.scale * rotatedPanY).coerceIn(
                        minimumValue = minOf(minY,maxY),
                        maximumValue = maxOf(minY,maxY)
                    )
                )

                onChildTransformChanged(
                    child.id,
                    newOffset,
                    newRotation,
                    newScale
                )
            }

            Box(
                modifier = Modifier
                    .onSizeChanged {
                        childWidth = it.width;
                        childHeight = it.height
                    }
                    //during transformation it is important to use the graphics layer , to skip some stages of recomposition
                    .graphicsLayer{
                        //we set how far our child has moved in the x and y axis,
                        //we multiply them with parent , to stop setting them as ablsolute values since our screen might rotate
                        translationX = child.offsetRatioX * parentWidth;
                        translationY = child.offsetRatioY * parentHeight;
                        rotationZ = child.rotation;
                        scaleX = child.scale;
                        scaleY = child.scale
                    }
                    .transformable(transformableState)
            ){
                //we then have the MemeTextBox inside here
                MemeTextBox(
                    memeText = child,
                    textBoxInteractionState = textBoxInteractionState,
                    // Pass the parent box's dimensions
                    maxWidth = with(density ){
                        parentWidth.toDp()
                    },
                    maxHeight = with(density ){
                        parentHeight.toDp()
                    },
                    modifier = Modifier
                    // You might need an .offset() here if your MemeText has x/y coordinates
                    // .offset(x = memeText.x, y = memeText.y)
                    ,
                    // Connect the events to your ViewModel actions
                    onClick = {
                        onChildClick(child.id)
                    },
                    onDoubleClick = {
                        onChildDoubleClick(child.id)
                    },
                    onTextChange = {
                        //it is the new text
                        onChildTextChange(child.id,it)

                    },
                    onDeleteClick = {
                        onChildDeleteClick(child.id)
                    }
                )

            }
        }
    }

}