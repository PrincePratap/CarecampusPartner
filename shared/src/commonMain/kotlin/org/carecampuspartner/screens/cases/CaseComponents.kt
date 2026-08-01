package org.carecampuspartner.screens.cases

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Theme Colors ---
val CPrimary = Color(0xFF4CAF50)
val CPrimaryDark = Color(0xFF2E7D32)
val CSecondary = Color(0xFF66BB6A)
val CAccent = Color(0xFF81C784)
val CSuccess = Color(0xFF22C55E)
val CWarning = Color(0xFFF59E0B)
val CDanger = Color(0xFFEF4444)
val CBackground = Color(0xFFF8FAFC)
val CSurface = Color(0xFFFFFFFF)
val CCardBorder = Color(0xFFE5E7EB)
val CTextPrimary = Color(0xFF1F2937)
val CTextSecondary = Color(0xFF6B7280)
val CBlue = Color(0xFF3B82F6)
val CPurple = Color(0xFF8B5CF6)
val COrange = Color(0xFFF97316)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CasesTopBar(
    onBack: () -> Unit,
    onNotify: () -> Unit,
    onSearch: () -> Unit,
    onFilter: () -> Unit
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    "Rescue Requests",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = CTextPrimary
                )
                Text(
                    "Manage all incoming rescue requests",
                    style = MaterialTheme.typography.bodySmall,
                    color = CTextSecondary
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = CTextPrimary)
            }
        },
        actions = {
            IconButton(onClick = onSearch) {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = CTextPrimary)
            }
            IconButton(onClick = onFilter) {
                Icon(Icons.Default.FilterList, contentDescription = "Filter", tint = CTextPrimary)
            }
            IconButton(onClick = onNotify) {
                Icon(Icons.Default.NotificationsNone, contentDescription = "Notifications", tint = CTextPrimary)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = CBackground)
    )
}

@Composable
fun SummaryCards(stats: CaseStats) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SummaryCard(
                Modifier.weight(1f),
                "Pending",
                stats.pendingCount,
                Icons.Default.HourglassEmpty,
                Brush.verticalGradient(listOf(COrange, CWarning))
            )
            SummaryCard(
                Modifier.weight(1f),
                "Accepted",
                stats.acceptedCount,
                Icons.Default.CheckCircle,
                Brush.verticalGradient(listOf(CPrimary, CSecondary))
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SummaryCard(
                Modifier.weight(1f),
                "Completed",
                stats.completedCount,
                Icons.Default.DoneAll,
                Brush.verticalGradient(listOf(CBlue, CPurple))
            )
            SummaryCard(
                Modifier.weight(1f),
                "Emergency",
                stats.emergencyCount,
                Icons.Default.Warning,
                Brush.verticalGradient(listOf(CDanger, Color(0xFFFF8A80)))
            )
        }
    }
}

@Composable
fun SummaryCard(
    modifier: Modifier,
    label: String,
    value: Int,
    icon: ImageVector,
    background: Brush
) {
    val animatedValue by animateIntAsState(
        targetValue = value,
        animationSpec = tween(1500, easing = FastOutSlowInEasing),
        label = "Count"
    )

    Card(
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .padding(12.dp)
        ) {
            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text(
                    text = "$animatedValue",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = label,
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
            Icon(
                icon,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.3f),
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
fun CaseSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        placeholder = { Text("Search by Animal, ID, Citizen...", color = CTextSecondary) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = CTextSecondary) },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = CPrimary,
            unfocusedBorderColor = CCardBorder,
            focusedContainerColor = CSurface,
            unfocusedContainerColor = CSurface
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
    )
}

@Composable
fun CaseStatusBadge(status: CaseStatus) {
    val (text, color) = when (status) {
        CaseStatus.PENDING -> "Pending" to COrange
        CaseStatus.ACCEPTED -> "Accepted" to CPrimary
        CaseStatus.ASSIGNED -> "Assigned" to CBlue
        CaseStatus.ON_THE_WAY -> "On Way" to CAccent
        CaseStatus.RESCUED -> "Rescued" to CSuccess
        CaseStatus.COMPLETED -> "Completed" to CPurple
        CaseStatus.REJECTED -> "Rejected" to CDanger
    }

    Surface(
        color = color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color.copy(alpha = 0.5f))
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}

@Composable
fun CasePriorityChip(priority: CasePriority) {
    val (text, color) = when (priority) {
        CasePriority.HIGH -> "High Priority" to CDanger
        CasePriority.MEDIUM -> "Medium" to CWarning
        CasePriority.LOW -> "Low" to CBlue
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = color)
    }
}

@Composable
fun AcceptDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Accept Rescue Request?", fontWeight = FontWeight.Bold) },
        text = {
            Text("You are about to accept this rescue request. The citizen will be notified immediately.")
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = CSuccess)
            ) {
                Text("Accept")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = CTextSecondary)
            }
        },
        shape = RoundedCornerShape(24.dp),
        containerColor = CSurface
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RejectBottomSheet(
    onReject: (String, String) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedReason by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    val reasons = listOf(
        "Outside Service Area",
        "No Rescue Team Available",
        "Duplicate Request",
        "Invalid Report",
        "Animal Already Rescued",
        "Other"
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = CSurface,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .padding(bottom = 32.dp)
        ) {
            Text("Reject Request", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            
            Text("Select a reason:", fontWeight = FontWeight.SemiBold, color = CTextSecondary)
            Spacer(modifier = Modifier.height(8.dp))
            
            reasons.forEach { reason ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedReason = reason }
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (selectedReason == reason),
                        onClick = { selectedReason = reason },
                        colors = RadioButtonDefaults.colors(selectedColor = CDanger)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(reason)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Additional notes (optional)") },
                shape = RoundedCornerShape(12.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f).height(56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Cancel")
                }
                Button(
                    onClick = { if (selectedReason.isNotEmpty()) onReject(selectedReason, notes) },
                    modifier = Modifier.weight(1f).height(56.dp),
                    enabled = selectedReason.isNotEmpty(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CDanger)
                ) {
                    Text("Reject Request")
                }
            }
        }
    }
}

@Composable
fun LoadingState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(5) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(CCardBorder.copy(alpha = 0.5f))
            )
        }
    }
}

@Composable
fun EmptyState(onRefresh: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.ContentPasteSearch,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = CTextSecondary.copy(alpha = 0.3f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("No Rescue Requests", fontWeight = FontWeight.Bold, color = CTextPrimary)
        Text("All incoming rescue requests will appear here.", color = CTextSecondary)
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onRefresh,
            colors = ButtonDefaults.buttonColors(containerColor = CPrimary),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Refresh Cases")
        }
    }
}
