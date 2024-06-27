package com.example.mtgcards.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = koinViewModel(),
    advancedSearch: () -> Unit,
    normalSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val state = viewModel.viewState.observeAsState()
    state.value?.let {
        when (it) {
            is HomeViewState.AdvancedSearch -> {
                viewModel.clearState()
                advancedSearch()
            }
            is HomeViewState.NormalSearch -> {
                viewModel.clearState()
                normalSearch(it.cardName)
            }
        }
    }

    var search by remember { mutableStateOf("") }
    Scaffold(
        topBar = { HomeToolBar(scrollBehavior = scrollBehavior) },
        modifier = modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = modifier
                .padding(top = padding.calculateTopPadding())
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Buscar por nome", fontSize = 24.sp)
            TextField(
                value = search,
                onValueChange = { search = it },
                label = { Text(text = "Nome da carta") },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Button(
                onClick = { viewModel.onNormalSearch(search)},
                modifier = modifier.padding(top = 16.dp, bottom = 8.dp)
            ) {
                Text(text = "Procurar")
            }
            TextButton(onClick = { viewModel.onAdvancedSearch() }) {
                Text(text = "Busca Avançada")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeToolBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = { Text(text = "MTG Cards") },
        modifier = modifier,
        scrollBehavior = scrollBehavior
    )
}
