package org.carecampuspartner.screens.volunteer

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import carecampuspartner.shared.generated.resources.Res
import carecampuspartner.shared.generated.resources.volunteerimg
import org.carecampuspartner.theming.*
import org.jetbrains.compose.resources.painterResource

data class VolunteerProfileData(
    val id: String = "1",
    val fullName: String = "Rahul Sharma",
    val age: Int = 26,
    val gender: String = "Male",
    val city: String = "Mumbai",
    val state: String = "Maharashtra",
    val isVerified: Boolean = true,
    val experienceYears: Int = 3,
    val rescueMissions: Int = 42,
    val memberSince: String = "Oct 2021",
    val bio: String = "Passionate animal lover with a background in canine behavior. Dedicated to ensuring every stray finds a safe home and proper medical care.",
    val passion: String = "I believe every animal deserves a life free from pain and fear. My goal is to bridge the gap between rescue and rehabilitation.",
    val whyJoin: String = "Paro Wings has an incredible track record of transparency and impact. I want to contribute my skills to a team that truly prioritizes animal welfare.",
    val phone: String = "+91 98765 43210",
    val email: String = "rahul.sharma@example.com",
    val address: String = "B-402, Green Valley Apartments, Andheri West",
    val experienceDetails: List<ExperienceItem> = listOf(
        ExperienceItem("5 Years", "Canine Handling", Icons.Outlined.Pets),
        ExperienceItem("2 Years", "NGO Volunteer", Icons.Outlined.CorporateFare),
        ExperienceItem("Expert", "First Aid", Icons.Outlined.MedicalServices)
    ),
    val skills: List<String> = listOf("Dog Rescue", "Cat Rescue", "First Aid", "Driving", "Animal Transport", "Foster Care"),
    val availableDays: List<String> = listOf("Monday", "Wednesday", "Friday", "Saturday", "Sunday"),
    val preferredTime: List<String> = listOf("Morning", "Evening"),
    val preferredRole: String = "Animal Rescuer",
    val emergencyContact: EmergencyContact = EmergencyContact("Sunita Sharma", "Mother", "+91 98765 00000"),
    val documents: List<VolunteerDocument> = listOf(
        VolunteerDocument("Government ID", "Verified"),
        VolunteerDocument("Driving License", "Verified"),
        VolunteerDocument("Experience Cert", "Pending")
    ),
    val stats: List<StatData> = listOf(
        StatData("42", "Rescues", Icons.Outlined.Pets, PrimaryGreen),
        StatData("12", "Events", Icons.Outlined.Event, PrimaryBlue),
        StatData("4.8", "Rating", Icons.Outlined.Star, AppYellow),
        StatData("150+", "Hours", Icons.Outlined.Timer, PrimaryOrange)
    ),
    val recentActivities: List<ActivityItem> = listOf(
        ActivityItem("Assisted Dog Rescue", "2 days ago", Icons.Outlined.Pets),
        ActivityItem("Vaccination Camp", "1 week ago", Icons.Outlined.Vaccines),
        ActivityItem("Joined Awareness Drive", "2 weeks ago", Icons.Outlined.Campaign)
    )
)

data class ExperienceItem(val duration: String, val title: String, val icon: ImageVector)
data class EmergencyContact(val name: String, val relation: String, val phone: String)
data class VolunteerDocument(val name: String, val status: String)
data class StatData(val value: String, val label: String, val icon: ImageVector, val color: Color)
data class ActivityItem(val title: String, val time: String, val icon: ImageVector)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolunteerProfileScreen(
    onBack: () -> Unit = {},
    onAccept: () -> Unit = {},
    onReject: () -> Unit = {}
) {
    val volunteer = VolunteerProfileData() // Dummy data
    var showAcceptDialog by remember { mutableStateOf(false) }
    var showRejectDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Volunteer Profile",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = TextDark
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Outlined.ArrowBackIosNew, contentDescription = "Back", modifier = Modifier.size(20.dp))
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.MoreVert, contentDescription = "More")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BackgroundColor
                )
            )
        },
        bottomBar = {
            BottomActionArea(
                onAccept = { showAcceptDialog = true },
                onReject = { showRejectDialog = true }
            )
        },
        containerColor = BackgroundColor
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { HeroSection(volunteer) }
            item { SectionSpacer() }
            item { StatisticsSection(volunteer.stats) }
            item { SectionSpacer() }
            item { AboutSection(volunteer) }
            item { SectionSpacer() }
            item { ContactSection(volunteer) }
            item { SectionSpacer() }
            item { ExperienceSection(volunteer.experienceDetails) }
            item { SectionSpacer() }
            item { SkillsSection(volunteer.skills) }
            item { SectionSpacer() }
            item { AvailabilitySection(volunteer) }
            item { SectionSpacer() }
            item { EmergencyContactSection(volunteer.emergencyContact) }
            item { SectionSpacer() }
            item { DocumentsSection(volunteer.documents) }
            item { SectionSpacer() }
            item { RecentActivitiesSection(volunteer.recentActivities) }
        }
    }

    if (showAcceptDialog) {
        ConfirmationDialog(
            title = "Accept Volunteer?",
            message = "Are you sure you want to approve ${volunteer.fullName}'s request to join your NGO?",
            confirmText = "Accept",
            onConfirm = {
                showAcceptDialog = false
                onAccept()
            },
            onDismiss = { showAcceptDialog = false }
        )
    }

    if (showRejectDialog) {
        ConfirmationDialog(
            title = "Reject Request?",
            message = "This will decline ${volunteer.fullName}'s application. This action cannot be undone.",
            confirmText = "Reject",
            confirmColor = ErrorRed,
            onConfirm = {
                showRejectDialog = false
                onReject()
            },
            onDismiss = { showRejectDialog = false }
        )
    }
}

@Composable
private fun HeroSection(volunteer: VolunteerProfileData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            Image(
                painter = painterResource(Res.drawable.volunteerimg),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(4.dp, Color.White, CircleShape),
                contentScale = ContentScale.Crop
            )
            if (volunteer.isVerified) {
                Surface(
                    color = SuccessGreen,
                    shape = CircleShape,
                    modifier = Modifier.size(32.dp).offset(x = (-4).dp, y = (-4).dp),
                    border = BorderStroke(2.dp, Color.White)
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "Verified",
                        tint = Color.White,
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = volunteer.fullName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )

        Text(
            text = "${volunteer.age} Years • ${volunteer.gender} • ${volunteer.city}, ${volunteer.state}",
            fontSize = 14.sp,
            color = TextGray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            InfoChip(label = "⭐ ${volunteer.experienceYears} Years Exp", color = CardBlue, textColor = PrimaryBlue)
            InfoChip(label = "🐾 ${volunteer.rescueMissions} Rescues", color = CardGreen, textColor = PrimaryGreen)
            InfoChip(label = "📅 Since ${volunteer.memberSince}", color = CardOrange, textColor = PrimaryOrange)
        }
    }
}

@Composable
private fun StatisticsSection(stats: List<StatData>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        stats.forEach { stat ->
            Surface(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(20.dp),
                color = Color.White,
                shadowElevation = 0.5.dp,
                border = BorderStroke(1.dp, BorderLight)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        stat.icon,
                        contentDescription = null,
                        tint = stat.color,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(stat.value, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextDark)
                    Text(stat.label, fontSize = 10.sp, color = TextGray)
                }
            }
        }
    }
}

@Composable
private fun AboutSection(volunteer: VolunteerProfileData) {
    SectionCard(title = "About Volunteer") {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            AboutItem(title = "Short Bio", content = volunteer.bio)
            AboutItem(title = "Passion for animal welfare", content = volunteer.passion)
            AboutItem(title = "Why join our NGO?", content = volunteer.whyJoin)
        }
    }
}

@Composable
private fun AboutItem(title: String, content: String) {
    Column {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark)
        Spacer(modifier = Modifier.height(4.dp))
        Text(content, fontSize = 13.sp, color = TextGray, lineHeight = 20.sp)
    }
}

@Composable
private fun ContactSection(volunteer: VolunteerProfileData) {
    SectionCard(title = "Contact Information") {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            ContactRow(Icons.Outlined.Phone, volunteer.phone)
            ContactRow(Icons.Outlined.Email, volunteer.email)
            ContactRow(Icons.Outlined.LocationOn, "${volunteer.city}, ${volunteer.state}")
            ContactRow(Icons.Outlined.Home, volunteer.address)
        }
    }
}

@Composable
private fun ContactRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
            modifier = Modifier.size(36.dp),
            shape = CircleShape,
            color = BackgroundLight
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = PrimaryGreen, modifier = Modifier.size(18.dp))
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(text, fontSize = 14.sp, color = TextDark)
    }
}

@Composable
private fun ExperienceSection(experience: List<ExperienceItem>) {
    SectionCard(title = "Experience") {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            experience.forEach { item ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        modifier = Modifier.size(40.dp),
                        shape = RoundedCornerShape(12.dp),
                        color = CardIndigo
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(item.icon, null, tint = Color(0xFF3F51B5), modifier = Modifier.size(20.dp))
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(item.title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark)
                        Text(item.duration, fontSize = 12.sp, color = TextGray)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SkillsSection(skills: List<String>) {
    SectionCard(title = "Skills") {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            skills.forEach { skill ->
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = BackgroundLight,
                    border = BorderStroke(1.dp, PrimaryGreen.copy(alpha = 0.2f))
                ) {
                    Text(
                        skill,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        fontSize = 12.sp,
                        color = PrimaryGreen,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
private fun AvailabilitySection(volunteer: VolunteerProfileData) {
    SectionCard(title = "Availability") {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Available Days", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark)
            
            val days = listOf("M", "T", "W", "T", "F", "S", "S")
            val fullDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                fullDays.forEachIndexed { index, day ->
                    val isSelected = volunteer.availableDays.contains(day)
                    DayCircle(days[index], isSelected)
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Preferred Time", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark)
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        volunteer.preferredTime.forEach { time ->
                            InfoChip(time, CardBlue, PrimaryBlue)
                        }
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Preferred Role", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark)
                    Spacer(modifier = Modifier.height(8.dp))
                    InfoChip(volunteer.preferredRole, CardGreen, PrimaryGreen)
                }
            }
        }
    }
}

@Composable
private fun DayCircle(day: String, isSelected: Boolean) {
    Surface(
        modifier = Modifier.size(36.dp),
        shape = CircleShape,
        color = if (isSelected) PrimaryGreen else Color.Transparent,
        border = if (isSelected) null else BorderStroke(1.dp, BorderMedium)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                day,
                color = if (isSelected) Color.White else TextGray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun EmergencyContactSection(contact: EmergencyContact) {
    SectionCard(title = "Emergency Contact") {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = CardPink
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Outlined.ContactPhone, null, tint = DeepRed, modifier = Modifier.size(24.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(contact.name, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextDark)
                Text("${contact.relation} • ${contact.phone}", fontSize = 13.sp, color = TextGray)
            }
        }
    }
}

@Composable
private fun DocumentsSection(documents: List<VolunteerDocument>) {
    SectionCard(title = "Documents") {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            documents.forEach { doc ->
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = BackgroundSurface,
                    border = BorderStroke(1.dp, BorderLight)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Outlined.Description, null, tint = TextGray, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(doc.name, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark)
                            Text(doc.status, fontSize = 11.sp, color = if(doc.status == "Verified") SuccessGreen else WarningOrange)
                        }
                        IconButton(onClick = {}) { Icon(Icons.Outlined.Visibility, null, tint = PrimaryGreen, modifier = Modifier.size(20.dp)) }
                        IconButton(onClick = {}) { Icon(Icons.Outlined.Download, null, tint = PrimaryBlue, modifier = Modifier.size(20.dp)) }
                    }
                }
            }
        }
    }
}

@Composable
private fun RecentActivitiesSection(activities: List<ActivityItem>) {
    SectionCard(title = "Recent Activities") {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            activities.forEachIndexed { index, activity ->
                Row {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Surface(
                            modifier = Modifier.size(12.dp),
                            shape = CircleShape,
                            color = PrimaryGreen
                        ) {}
                        if (index != activities.size - 1) {
                            Box(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(40.dp)
                                    .background(BorderMedium)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.offset(y = (-2).dp)) {
                        Text(activity.title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextDark)
                        Text(activity.time, fontSize = 12.sp, color = TextGray)
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomActionArea(onAccept: () -> Unit, onReject: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onReject,
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, ErrorRed),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = ErrorRed)
            ) {
                Text("Reject Request", fontWeight = FontWeight.Bold)
            }
            
            Button(
                onClick = onAccept,
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
            ) {
                Text("Accept Volunteer", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun SectionCard(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            text = title,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            color = Color.White,
            shadowElevation = 0.5.dp,
            border = BorderStroke(1.dp, BorderLight)
        ) {
            Box(modifier = Modifier.padding(20.dp)) {
                content()
            }
        }
    }
}

@Composable
private fun InfoChip(label: String, color: Color, textColor: Color) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = color
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}

@Composable
private fun SectionSpacer() {
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun ConfirmationDialog(
    title: String,
    message: String,
    confirmText: String,
    confirmColor: Color = PrimaryGreen,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title, fontWeight = FontWeight.Bold) },
        text = { Text(message) },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = confirmColor)
            ) {
                Text(confirmText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = TextGray)
            }
        },
        shape = RoundedCornerShape(28.dp),
        containerColor = Color.White
    )
}
