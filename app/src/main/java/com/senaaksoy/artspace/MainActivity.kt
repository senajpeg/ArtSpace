package com.senaaksoy.artspace
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senaaksoy.artspace.ui.theme.ArtSpaceTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                       ArtSpaceLayout()
                }
            }
        }
    }
}
@Composable
fun ArtSpaceLayout(modifier: Modifier=Modifier){
    var imageResult by remember { mutableIntStateOf(1)  }
    var textResult by remember{ mutableIntStateOf(1) }
    var artResult by remember{ mutableIntStateOf(1) }
    var yearResult by remember{ mutableIntStateOf(1) }
    val imageResource =when(imageResult){
        1->R.drawable.monalisa
        2->R.drawable.incikupelikadin
        else->R.drawable.starrynight
    }
    val artistResource=when(textResult){
        1->R.string.mona_artist
        2->R.string.girl_artist
        else ->R.string.starry_artist
    }
    val artResource=when(artResult){
        1->R.string.mona_lisa
        2->R.string.pearl_girl
        else->R.string.starry_night
    }
    val yearResource=when(yearResult){
        1->R.string.mona_year
        2->R.string.girl_year
        else->R.string.starry_year
    }
    fun incrementValues(){
        if(imageResult%3!=0){
        imageResult++
        textResult++
        artResult++
        yearResult++} else{
            imageResult=1
            textResult=1
            artResult=1
            yearResult=1
        }
    }
    fun decrementValues(){
        if(imageResult%3!=1){
        imageResult--
        textResult--
        artResult--
        yearResult--} else{
            imageResult=3
            textResult=3
            artResult=3
            yearResult=3
        }
    }
  Column(
      modifier
          .fillMaxSize()
          .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
  Box(
      modifier
          .shadow(elevation = 12.dp)
          .border(24.dp, Color.White)){
          EditImageField(label = imageResource)
  }
    Spacer(modifier = Modifier.height(80.dp))
    Column(
        modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color(0xFF9E9D9D))
            .padding(start = 12.dp),  verticalArrangement = Arrangement.Center) {
        EditTextField(text = artResource,size=24.sp)
        Row {
            EditTextField(text=artistResource,bold=true)
            EditTextField(text = yearResource)
              }
    }
    Spacer(modifier = Modifier.height(80.dp))
        Row {
            Button(onClick = { decrementValues()},
                modifier
                    .height(30.dp)
                    .padding(start = 10.dp)
                    .weight(0.5f)) {
                Text(text = "Previous")
            }
            Spacer(modifier = modifier.width(70.dp))
            Button(onClick = { incrementValues()},
                modifier
                    .height(30.dp)
                    .padding(end = 10.dp)
                    .weight(0.5f)) {
                Text(text = "Next")
            }
        }

   }
}
@Composable
fun EditImageField(@DrawableRes label:Int,modifier: Modifier=Modifier){
    Image(painter = painterResource(id = label) , contentDescription = null,modifier.size(300.dp))
}
 @Composable
 fun EditTextField( @StringRes text:Int,modifier: Modifier=Modifier,bold:Boolean=false, size: TextUnit = TextUnit.Unspecified){
     Text(text = stringResource(id = text),modifier=modifier, fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,fontSize = size)
 }
@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
     ArtSpaceLayout()
    }
}