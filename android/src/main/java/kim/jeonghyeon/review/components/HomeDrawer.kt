package kim.jeonghyeon.review.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import kim.jeonghyeon.androidlibrary.compose.widget.SpacerH
import kim.jeonghyeon.androidlibrary.compose.widget.SpacerW
import kim.jeonghyeon.client.Navigator
import kim.jeonghyeon.base.HomeViewModel
import kim.jeonghyeon.review.R

@Composable
fun HomeDrawer(closeDrawer: () -> Unit) {
    val homeViewModel = Navigator.backStack.lastOrNull {
        it is HomeViewModel
    } as? HomeViewModel ?: HomeViewModel()

    val closeAndNavigateToHome: () -> Unit = {
        closeDrawer()

        if (!Navigator.backUpTo(homeViewModel)) {
            Navigator.navigate(homeViewModel)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SpacerH(24.dp)
        Logo(homeViewModel.title, Modifier.padding(16.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))

        DrawerButton(
            icon = null,
            label = "Word Book",
            isSelected = false,
            action = { closeAndNavigateToHome() }

        )
    }
}

@Composable
private fun Logo(title: String, modifier: Modifier = Modifier) {
    Row(modifier) {
        Image(
            imageVector = vectorResource(R.drawable.ic_app),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
        )
        SpacerW(16.dp)
        Text(title)
    }
}

@Composable
private fun DrawerButton(
    icon: ImageVector?,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val imageAlpha = if (isSelected) 1f else 0.6f
    val textIconColor =
        if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
    val backgroundColor =
        if (isSelected) MaterialTheme.colors.primary.copy(alpha = 0.12f) else MaterialTheme.colors.surface

    Surface(
        modifier = modifier
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .fillMaxWidth(),
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        TextButton(
            onClick = action,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                icon?.let {
                    Image(
                        imageVector = icon,
                        colorFilter = ColorFilter.tint(textIconColor),
                        alpha = imageAlpha
                    )
                    SpacerW(16.dp)
                }

                Text(
                    text = label,
                    modifier = Modifier.fillMaxWidth(),
                    color = textIconColor,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainDrawer() {
    HomeDrawer {}
}