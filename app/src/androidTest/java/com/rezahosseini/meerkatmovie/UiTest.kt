package com.rezahosseini.meerkatmovie

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun text_shouldBeDisplayed() {

        composeTestRule.setContent {
            Text("Hello")

        }

        composeTestRule
            .onNodeWithText("Hello")
            .assertIsDisplayed()
    }

    @Test
    fun createAndTestButton(){
        var clicked = false

        composeTestRule.setContent {
            Button(
                onClick = {
                    clicked = true
                }
            ) {
                Text("Click")
            }
        }
        composeTestRule
            .onNodeWithText("Click")
            .performClick()

        assertTrue(clicked)
    }

//    @Test
//    fun createAndTextField(){
//        var text by remember {
//            mutableStateOf("GTA V")
//        }
//        composeTestRule.setContent {
//
//
//            TextField(
//                value = text,
//                onValueChange = {
//                    text = it
//                }
//            )
//        }
//        composeTestRule
//            .onNode(isEnabled())
//            .performTextInput("GTA V")
//    }
//    it is not state just for plan
    @Test
    fun createLoadingState(){
        composeTestRule.setContent {
            Text("Loading...")
        }
        composeTestRule
            .onNodeWithText("Loading...")
            .assertIsDisplayed()
    }
}