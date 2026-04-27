package com.mad.campusconnect

import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvEvents: RecyclerView
    private lateinit var rvNews: RecyclerView
    private lateinit var etSearch: EditText
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
        NewsItem(
            title = "CIE 3",
            date = "Jan 8, 2026",
            body = "The CIE 3 is scheduled from 8th Jan"
        ),
        NewsItem(
            title = "Library Timings Extended",
            date = "Jan 10, 2026",
            body = "Library will remain open till 10 PM during exam week"
        ),
        NewsItem(
            title = "Placement Drive – Infosys",
            date = "Jan 15, 2026",
            body = "Infosys campus placement drive for 2026 batch on Jan 20"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Style the brand name with teal "CAMPUS"
        // (The TopBar TextView is static XML; for teal CAMPUS we'd use a SpannableString
        // but since we have two TextViews merged, this is handled in XML via two-color text)

        // Login button
        findViewById<android.widget.Button>(R.id.btnLogin).setOnClickListener {
            Toast.makeText(this, "Login screen coming soon!", Toast.LENGTH_SHORT).show()
        }

        // News RecyclerView
        rvNews = findViewById(R.id.rvNews)
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = NewsAdapter(newsList)

        // Events RecyclerView
        rvEvents = findViewById(R.id.rvEvents)
        rvEvents.layoutManager = LinearLayoutManager(this)
        eventAdapter = EventAdapter(this, allEvents) { event ->
            Toast.makeText(this, "Tapped: ${event.title} — detail screen coming soon!", Toast.LENGTH_SHORT).show()
        }
        rvEvents.adapter = eventAdapter

        // Search
        etSearch = findViewById(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase()
                val filtered = allEvents.filter {
                    it.title.lowercase().contains(query) ||
                            it.tags.any { tag -> tag.lowercase().contains(query) } ||
                            it.description.lowercase().contains(query)
                }
                eventAdapter.updateList(filtered)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}