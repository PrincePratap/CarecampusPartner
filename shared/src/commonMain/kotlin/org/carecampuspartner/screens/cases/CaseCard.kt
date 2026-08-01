package org.carecampuspartner.screens.cases

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CaseCard(
    case: RescueCase,
    onAccept: () -> Unit,
    onReject: () -> Unit,
    onViewDetails: () -> Unit,
    onNavigate: () -> Unit,
    onCall: () -> Unit
) {
    val borderColor = when (case.status) {
        CaseStatus.ACCEPTED -> CPrimary
        CaseStatus.REJECTED -> CDanger
        else -> CCardBorder
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .shadow(8.dp, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CSurface),
        border = BorderStroke(if (case.status == CaseStatus.PENDING) 1.dp else 2.dp, borderColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: Case ID and Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Case #${case.caseId}",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = CTextPrimary
                    )
                    CasePriorityChip(case.priority)
                }
                CaseStatusBadge(case.status)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Body: Animal Photo and Info
            Row(modifier = Modifier.fillMaxWidth()) {
                // Placeholder for Animal Photo
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(CCardBorder.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Pets,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = CTextSecondary.copy(alpha = 0.5f)
                    )
                    if (case.isEmergency) {
                        Surface(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(4.dp),
                            color = CDanger,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                "SOS",
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = case.animalType,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = CTextPrimary
                    )
                    Text(
                        text = case.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = CTextSecondary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = CPrimary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = case.distance,
                            fontSize = 12.sp,
                            color = CTextPrimary,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = case.address,
                            fontSize = 12.sp,
                            color = CTextSecondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = CCardBorder.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(16.dp))

            // Citizen Info
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(CPrimary.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = CPrimary
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(case.citizenName, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = CTextPrimary)
                    Text(case.phone, fontSize = 12.sp, color = CTextSecondary)
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Reported: 12:30 PM", // Mock time
                    fontSize = 11.sp,
                    color = CTextSecondary
                )
            }

            if (case.status == CaseStatus.ACCEPTED && case.estimatedArrivalTime != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    color = CPrimary.copy(alpha = 0.05f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Timer,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = CPrimary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Estimated Arrival: ${case.estimatedArrivalTime}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = CPrimary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                when (case.status) {
                    CaseStatus.PENDING -> {
                        OutlinedButton(
                            onClick = onReject,
                            modifier = Modifier.weight(1f).height(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, CDanger),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = CDanger)
                        ) {
                            Text("Reject", fontSize = 14.sp)
                        }
                        Button(
                            onClick = onAccept,
                            modifier = Modifier.weight(1f).height(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = CPrimary)
                        ) {
                            Text("Accept Case", fontSize = 14.sp)
                        }
                    }
                    CaseStatus.ACCEPTED -> {
                        Button(
                            onClick = onNavigate,
                            modifier = Modifier.weight(1.2f).height(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = CBlue)
                        ) {
                            Icon(
                                Icons.Default.Navigation,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Navigate", fontSize = 14.sp)
                        }
                        OutlinedButton(
                            onClick = onCall,
                            modifier = Modifier.weight(1f).height(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, CPrimary)
                        ) {
                            Icon(
                                Icons.Default.Phone,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = CPrimary
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Call", fontSize = 14.sp, color = CPrimary)
                        }
                    }
                    else -> {
                        Button(
                            onClick = onViewDetails,
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = CCardBorder),
                            elevation = null
                        ) {
                            Text("View Case Details", color = CTextPrimary, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}
