package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.myapplication.ui.theme.NewsReaderTheme
import com.example.myapplication.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = remember { ThemeViewModel() }
            NewsReaderTheme(viewModel) {
                NewsApp(viewModel)
            }
        }
    }
}

@Composable
fun NewsApp(viewModel: ThemeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "newsFeed") {
        composable("newsFeed") { NewsFeedScreen(navController, viewModel) }
        composable("newsDetail/{newsTitle}") { backStackEntry ->
            val newsTitle = backStackEntry.arguments?.getString("newsTitle") ?: stringResource(id = R.string.no_title)
            NewsDetailScreen(newsTitle, viewModel)
        }
    }
}

@Composable
fun NewsFeedScreen(navController: NavController, viewModel: ThemeViewModel) {
    val newsList = listOf(
        stringResource(id = R.string.news1),
        stringResource(id = R.string.news2),
        stringResource(id = R.string.news3),
        stringResource(id = R.string.news4),
        stringResource(id = R.string.news5),
        stringResource(id = R.string.news6),
        stringResource(id = R.string.news7),
        stringResource(id = R.string.news8),
        stringResource(id = R.string.news9),
        stringResource(id = R.string.news10)
    )

    Scaffold(
        topBar = { TopBar(title = stringResource(id = R.string.news_feed), viewModel = viewModel) }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(newsList) { news ->
                NewsCard(newsTitle = news) {
                    navController.navigate("newsDetail/$news")
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(newsTitle: String, viewModel: ThemeViewModel) {
    val isBookmarked by remember { derivedStateOf { viewModel.isBookmarked(newsTitle) } }

    val newsDetails = mapOf(
        stringResource(id = R.string.news1) to Pair(stringResource(id = R.string.news1_content), "March 14, 2025"),
        stringResource(id = R.string.news2) to Pair(stringResource(id = R.string.news2_content), "March 12, 2025"),
        stringResource(id = R.string.news3) to Pair(stringResource(id = R.string.news3_content), "March 10, 2025"),
        stringResource(id = R.string.news4) to Pair(stringResource(id = R.string.news4_content), "March 8, 2025"),
        stringResource(id = R.string.news5) to Pair(stringResource(id = R.string.news5_content), "March 7, 2025"),
        stringResource(id = R.string.news6) to Pair(stringResource(id = R.string.news6_content), "March 6, 2025"),
        stringResource(id = R.string.news7) to Pair(stringResource(id = R.string.news7_content), "March 5, 2025"),
        stringResource(id = R.string.news8) to Pair(stringResource(id = R.string.news8_content), "March 4, 2025"),
        stringResource(id = R.string.news9) to Pair(stringResource(id = R.string.news9_content), "March 3, 2025"),
        stringResource(id = R.string.news10) to Pair(stringResource(id = R.string.news10_content), "March 2, 2025")
    )

    val content = newsDetails[newsTitle]?.first ?: stringResource(id = R.string.default_content)
    val publishedDate = newsDetails[newsTitle]?.second ?: "March 1, 2025"

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.news_detail),
                viewModel = viewModel,
                isBookmarked = isBookmarked,
                onBookmarkClick = { viewModel.toggleBookmark(newsTitle) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(text = newsTitle, style = Typography.titleLarge)
            Text(text = stringResource(id = R.string.published_on) + " $publishedDate", style = Typography.bodyMedium)
            Text(
                text = content,
                style = Typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}


@Composable
fun NewsCard(newsTitle: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = newsTitle,
            style = Typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, viewModel: ThemeViewModel, isBookmarked: Boolean = false, onBookmarkClick: (() -> Unit)? = null) {
    TopAppBar(
        title = { Text(title, style = Typography.titleMedium) },
        actions = {
            onBookmarkClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = stringResource(id = R.string.bookmark)
                    )
                }
            }
            ThemeToggleSwitch(viewModel)
        }
    )
}

@Composable
fun ThemeToggleSwitch(viewModel: ThemeViewModel) {
    Switch(
        checked = viewModel.isDarkMode,
        onCheckedChange = { viewModel.toggleTheme() }
    )
}

class ThemeViewModel : ViewModel() {
    var isDarkMode by mutableStateOf(false)
        private set

    private var bookmarkedArticles = mutableStateListOf<String>()

    fun toggleTheme() {
        isDarkMode = !isDarkMode
    }

    fun toggleBookmark(newsTitle: String) {
        if (bookmarkedArticles.contains(newsTitle)) {
            bookmarkedArticles.remove(newsTitle)
        } else {
            bookmarkedArticles.add(newsTitle)
        }
    }

    fun isBookmarked(newsTitle: String): Boolean {
        return bookmarkedArticles.contains(newsTitle)
    }
}
