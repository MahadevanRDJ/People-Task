package com.deva.peoplelist.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deva.peoplelist.ui.theme.Primary

@Composable
fun PaginationBar(
    currentPage: Int,
    totalPages: Int,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val visiblePageNumbers = generateVisiblePageNumbers(currentPage, totalPages)

    Row(
        modifier = modifier
            .height(48.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavigationButton(
            icon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            onClick = { if (currentPage > 1) onPageSelected(currentPage - 1) },
            enabled = currentPage > 1
        )

        visiblePageNumbers.forEach { pageNum ->
            if (pageNum == -1) {
                Text(
                    text = "...",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                PageButton(
                    page = pageNum,
                    isSelected = pageNum == currentPage,
                    onClick = { onPageSelected(pageNum) }
                )
            }
        }

        NavigationButton(
            icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            onClick = { if (currentPage < totalPages) onPageSelected(currentPage + 1) },
            enabled = currentPage < totalPages
        )
    }
}

@Composable
private fun PageButton(
    page: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(
                if (isSelected) Primary
                else Color.Transparent
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = page.toString(),
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
private fun NavigationButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    enabled: Boolean
) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (enabled) MaterialTheme.colorScheme.onSurface
            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        )
    }
}

private fun generateVisiblePageNumbers(currentPage: Int, totalPages: Int): List<Int> {
    if (totalPages <= 7) {
        return (1..totalPages).toList()
    }

    return buildList {
        add(1)
        if (currentPage > 3) add(-1)
        for (i in maxOf(2, currentPage - 1)..minOf(totalPages - 1, currentPage + 1)) {
            add(i)
        }
        if (currentPage < totalPages - 2) add(-1)
        add(totalPages)
    }
}
