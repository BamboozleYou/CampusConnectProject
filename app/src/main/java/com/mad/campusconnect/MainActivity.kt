package com.mad.campusconnect

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView
    private lateinit var eventAdapter: EventAdapter

    private val allEvents = listOf(
        Event(
            id = 1,
            title = "Intro-Trek",
            description = "First-years exclusive!! Join us on this amazing and therapeutic trek to the outskirts of Namma Bengaluru",
            date = "Sun, Jan 4, 2026",
            time = "06:00 AM",
            status = "Completed",
            price = "₹500",
            tags = listOf("Social", "Sports"),
            exclusiveTag = "EXCLUSIVELY FOR 1ST YEARS"
        ),
        Event(
            id = 2,
            title = "Hackathon 2026",
            description = "Build innovative solutions in 24 hours. Open to all branches. Form teams of 3-4 and register now!",
            date = "Sat, Feb 14, 2026",
            time = "09:00 AM",
            status = "Upcoming",
            price = "Free",
            tags = listOf("Tech", "Competitive"),
            exclusiveTag = ""
        ),
        Event(
            id = 3,
            title = "Cultural Fest – Utsav",
            description = "The biggest cultural festival of the year. Dance, music, drama, art — come celebrate creativity!",
            date = "Fri, Mar 7, 2026",
            time = "04:00 PM",
            status = "Upcoming",
            price = "₹200",
            tags = listOf("Cultural", "Music", "Arts"),
            exclusiveTag = ""
        ),
        Event(
            id = 4,
            title = "IEEE Workshop: AI & ML",
            description = "Hands-on workshop on machine learning fundamentals. Bring your laptops. Certificates provided.",
            date = "Wed, Jan 22, 2026",
            time = "10:00 AM",
            status = "Completed",
            price = "₹150",
            tags = listOf("Tech", "Workshop"),
            exclusiveTag = "EXCLUSIVELY FOR 3RD & 4TH YEARS"
        ),
        Event(
            id = 5,
            title = "Inter-College Cricket Tournament",
            description = "Annual cricket tournament. Register your department team. Prizes worth ₹10,000 to be won!",
            date = "Sun, Mar 15, 2026",
            time = "08:00 AM",
            status = "Upcoming",
            price = "₹100",
            tags = listOf("Sports", "Competitive"),
            exclusiveTag = ""
        )
    )

    private val newsList = listOf(
        NewsItem("CIE 3", "Jan 8, 2026", "The CIE 3 is scheduled from 8th Jan"),
        NewsItem("Library Timings Extended", "Jan 10, 2026", "Library will remain open till 10 PM during exam week"),
        NewsItem("Placement Drive – Infosys", "Jan 15, 2026", "Infosys campus placement drive for 2026 batch on Jan 20")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Push content below status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout)) { view, insets ->
            val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            view.setPadding(0, statusBarHeight, 0, 0)
            insets
        }
        findViewById<android.widget.Button>(R.id.btnLogin).setOnClickListener {
            Toast.makeText(this, "Login screen coming soon!", Toast.LENGTH_SHORT).show()
        }

        rvMain = findViewById(R.id.rvMain)
        val layoutManager = LinearLayoutManager(this)
        rvMain.layoutManager = layoutManager
        rvMain.setHasFixedSize(false)
        rvMain.isNestedScrollingEnabled = true

        eventAdapter = EventAdapter(
            context = this,
            items = allEvents,
            newsList = newsList,
            onEventClick = { event ->
                Toast.makeText(this, "Tapped: ${event.title} — detail screen coming soon!", Toast.LENGTH_SHORT).show()
            },
            onSearchChanged = { query ->
                val filtered = allEvents.filter {
                    it.title.lowercase().contains(query.lowercase()) ||
                            it.tags.any { tag -> tag.lowercase().contains(query.lowercase()) } ||
                            it.description.lowercase().contains(query.lowercase())
                }
                eventAdapter.updateList(filtered)
            }
        )

        rvMain.adapter = eventAdapter
        rvMain.post {
            rvMain.scrollToPosition(0)
        }
    }
}