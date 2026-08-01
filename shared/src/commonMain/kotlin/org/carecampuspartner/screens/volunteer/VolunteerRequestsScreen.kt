package org.carecampuspartner.screens.volunteer

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import carecampuspartner.shared.generated.resources.Res
import carecampuspartner.shared.generated.resources.volunteerimg
import org.carecampuspartner.theming.*
import org.jetbrains.compose.resources.painterResource

data class VolunteerRequestItem(
    val id: String,
    val name: String,
    val experience: String,
    val role: String,
    val location: String,
    val status: String, // "New", "Verified", "Experienced"
    val skills: List<String>,
    val availability: String,
    val dateApplied: String,
    val imageUrl: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolunteerRequestsScreen(
    onBack: () -> Unit,
    onVolunteerClick: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }
    val filters = listOf("All", "New", "Experienced", "Verified", "Today", "This Week")

    val dummyRequests = remember {
        listOf(
            VolunteerRequestItem("1", "Rahul Sharma", "3 Years", "Animal Rescuer", "Mumbai, MH", "Verified", listOf("Dog Rescue", "Cat Rescue", "First Aid", "Driving"), "Weekends", "24 Oct 2023"),
            VolunteerRequestItem("2", "Priya Patel", "1 Year", "Shelter Volunteer", "Delhi, DL", "New", listOf("Cat Rescue", "Feeding", "Social Media"), "Weekdays", "25 Oct 2023"),
            VolunteerRequestItem("3", "Amit Kumar", "5+ Years", "Medical Support", "Bangalore, KA", "Experienced", listOf("Surgery Asst", "First Aid", "Bird Rescue"), "Everyday", "23 Oct 2023"),
            VolunteerRequestItem("4", "Sanya Mirza", "2 Years", "Foster Parent", "Hyderabad, TS", "Verified", listOf("Cat Rescue", "Puppy Care"), "Weekends", "22 Oct 2023")
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "Volunteer Requests",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = TextDark
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            color = PrimaryGreen.copy(alpha = 0.1f),
                            shape = CircleShape
                        ) {
                            Text(
                                "28",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryGreen
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Outlined.ArrowBackIosNew, contentDescription = "Back", modifier = Modifier.size(20.dp))
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.FilterList, contentDescription = "Filter")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BackgroundColor,
                    titleContentColor = TextDark,
                    navigationIconContentColor = TextDark,
                    actionIconContentColor = TextDark
                )
            )
        },
        containerColor = BackgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            VolunteerSearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it }
            )

            VolunteerFilterChips(
                filters = filters,
                selectedFilter = selectedFilter,
                onFilterSelected = { selectedFilter = it }
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(dummyRequests) { request ->
                    VolunteerCard(
                        request = request,
                        onClick = { onVolunteerClick(request.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun VolunteerSearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        placeholder = { Text("Search volunteer by name", fontSize = 14.sp, color = TextGray) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextGray) },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(Icons.Default.Clear, contentDescription = "Clear", tint = TextGray)
                }
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedBorderColor = PrimaryGreen.copy(alpha = 0.5f),
            unfocusedBorderColor = BorderLight,
            cursorColor = PrimaryGreen
        ),
        singleLine = true
    )
}

@Composable
fun VolunteerFilterChips(
    filters: List<String>,
    selectedFilter: String,
    onFilterSelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters) { filter ->
            val isSelected = filter == selectedFilter
            FilterChip(
                selected = isSelected,
                onClick = { onFilterSelected(filter) },
                label = { Text(filter) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = PrimaryGreen,
                    selectedLabelColor = Color.White,
                    containerColor = Color.White,
                    labelColor = TextGray
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = isSelected,
                    borderColor = BorderLight,
                    selectedBorderColor = PrimaryGreen,
                    borderWidth = 1.dp
                ),
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}

@Composable
fun VolunteerCard(
    request: VolunteerRequestItem,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 0.5.dp,
        border = BorderStroke(1.dp, BorderLight)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile Image
                Image(
                    painter = painterResource(Res.drawable.volunteerimg),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Info
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = request.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = TextDark,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        VolunteerStatusChip(request.status)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    DetailItem(Icons.Outlined.Star, "${request.experience} Experience", AppYellow)
                    DetailItem(Icons.Outlined.MedicalServices, request.role, PrimaryBlue)
                    DetailItem(Icons.Outlined.LocationOn, request.location, PrimaryGreen)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Skills
            VolunteerSkillsRow(request.skills)

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider(color = DividerColor, thickness = 1.dp)

            Spacer(modifier = Modifier.height(12.dp))

            // Bottom Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.EventAvailable, null, tint = TextGray, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(request.availability, fontSize = 12.sp, color = TextGray)
                }
                Text("Applied: ${request.dateApplied}", fontSize = 12.sp, color = TextSecondary)
            }
        }
    }
}

@Composable
fun DetailItem(icon: ImageVector, text: String, tint: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 1.dp)
    ) {
        Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(14.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text(text, fontSize = 12.sp, color = TextGray)
    }
}

@Composable
fun VolunteerStatusChip(status: String) {
    val (bgColor, textColor) = when (status) {
        "New" -> CardBlue to PrimaryBlue
        "Verified" -> CardGreen to PrimaryGreen
        "Experienced" -> CardOrange to PrimaryOrange
        else -> BackgroundGray to TextGray
    }

    Surface(
        color = bgColor,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = status,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun VolunteerSkillsRow(skills: List<String>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val displaySkills = skills.take(3)
        displaySkills.forEach { skill ->
            SkillChip(skill)
        }
        if (skills.size > 3) {
            SkillChip("+${skills.size - 3}")
        }
    }
}

@Composable
fun SkillChip(label: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = BackgroundLight,
        border = BorderStroke(0.5.dp, BorderMedium)
    ) {
        Text(
            label,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            fontSize = 11.sp,
            color = TextDark,
            fontWeight = FontWeight.Medium
        )
    }
}
